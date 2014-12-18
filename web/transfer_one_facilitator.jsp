<%-- 
    Document   : transfer_one_teacher
    Created on : Sep 12, 2019, 9:21:36 AM
    Author     : Geofrey Nyabuto
--%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page import="hc.dbConn"%>
<%@page import="java.util.Calendar"%>

<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/divCss_2.css"/>
    <link href="menu/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="menu/iconic.css" media="screen" rel="stylesheet" type="text/css" />
         <link rel="shortcut icon" href="images/hc_logo.png"/>
     <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
<title>Edit Facilitator</title>
<script type="text/javascript">
function group_them(){
    var partner_id=document.getElementById("partner").value; 
    var fname=document.getElementById("fname").value;
    var mname=document.getElementById("mname").value;
    var lname=document.getElementById("lname").value;
    var phone=document.getElementById("phone").value;
    
//    alert(partner_id);
  var xmlhttp;
if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
document.getElementById("groups").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","group_loader?partner_id="+partner_id+"&fname="+fname+"&mname="+mname+"&lname="+lname+"&phone="+phone,true);
xmlhttp.send();     
}
</script> 

</head>
<body  onload="return group_them();">

<div id="container" >
<%@include file="/menu/clerkmenu.jsp"%>
<div id="header" align="center">
<br/>

</div>


<div id="content" style="height:750px;">


<div id="midcontent" style="margin-left:130px ;">
<h4 style="background-color: orange">Edit Current Facilitator.</h4><br>

<h2 style="background-color: greenyellow;">NB: Ensure you press the control button before selecting a new group. THIS is to avoid removing the facilitator from the current list of groups associated to.</h2>
<br>
<%
String facilitator_id,fname,mname,lname,phone,partner_name,groups,partner_id;
facilitator_id=request.getParameter("facilitator_id");
fname=request.getParameter("fname");
mname=request.getParameter("mname");
lname=request.getParameter("lname");
phone=request.getParameter("phone");
partner_name=request.getParameter("partner_name");
groups=request.getParameter("groups");
partner_id=request.getParameter("partner_id");
%>
 <form action="transfer_facilitator" name="" method="post">
     <table cellpadding="2px" cellspacing="3px" border="0px" >
    <br><br>

  First Name
  <br>
  <input type="text" id="fname" name="fname" value="<%=fname%>" class="textbox" readonly="true" style="background: #cccccc">
   <input type="hidden" name="facilitator_id" id="facilitator_id" value="<%=facilitator_id%>" class="textbox">
   <br><br>
    Middle Name
  <br>
 <input type="text" id="mname" name="mname" value="<%=mname%>" class="textbox" readonly="true" style="background: #cccccc">
<br><br>
Last Name
<br>
<input type="text" id="lname" name="lname" value="<%=lname%>" class="textbox" readonly="true" style="background: #cccccc">
      <br><br>
      Phone Number
  <br>
 <input type="text" id="phone" name="phone" value="<%=phone%>" class="textbox" readonly="true" style="background: #cccccc"> 
  <br><br>
Partner<font color="red">*</font>
<br>
<input type="text" name="part" id="part"value="<%=partner_name%>" class="textbox" readonly="true"style="background: #cccccc">&nbsp;
<input type="hidden" name="grp" id="grp"value="<%=groups%>" class="textbox" readonly="true"style="background: #cccccc"/>&nbsp;

<select name="partner" id="partner" class="textbox1" onchange="return group_them();">
    <option value="<%=partner_id%>"><%=partner_name%></option>
    <% String selector ="select * from partner where partner_id!='"+partner_id+"'";
    dbConn conn=new dbConn();
    conn.rs=conn.st.executeQuery(selector);

while(conn.rs.next()){
%>
<option value="<%=conn.rs.getString("partner_id")%>"><%=conn.rs.getString("partner_name")%></option>
<%}%>
</select>
<br><br>
Groups<font color="red">*</font>
<br>

<p style="background: #cccccc; width: 200px; height: 70px" class="textbox2" style=""><%=groups%></p>

<p style="background: #006699; width: 200px; height: 70px" class="textbox3">
    <select name="groups" title="Ensure you press the control button before selecting a new group. THIS is to removing the facilitator from the current list of groups associated to." id="groups" class="textbox3" style="height:140px;" required="true" multiple>
<option value=""></option>
</select>
    </p>
<input type="submit" name="sub" value="Save" class="textbox1" style="background: #cc99ff; color: #0000ff" >
</table>
       <br><br>
</form>   
</div>
</div>



<div id="footer">
 <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center"> &copy Aphia Plus | USAID <%=year%></p>
</div>
</div>    

</body>
</html>

