<%-- 
    Document   : edit_one_facilitator
    Created on : Sep 9, 2013, 4:10:34 PM
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

</script> 
                 <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>  
</head>
<body  onload="">

<div id="container" >
 <%   if(session.getAttribute("level").equals("2")){ 
            %>
<%@include file="/menu/clerkmenu.jsp"%>
<%} else{%>
<%@include file="/menu/adminmenu.jsp"%>
<%}%>
    <h3 style="text-align: center; background-color: orange;">EDITING THIS FACILITATOR'S DETAILS.</h3>
<div id="header" align="center">
<br/>

</div>


<div id="content" style="height:750px;">


<div id="midcontent" style="margin-left:130px ;">
<h4>Edit Current Facilitator.</h4><br>

<p > (<font color="red">*</font>) Shows fields which can be edited.</p>
<br>
<%
String facilitator_id,fname,mname,lname,phone,partner_name,groups;
facilitator_id=request.getParameter("facilitator_id");





fname=request.getParameter("fname");
mname=request.getParameter("mname");
lname=request.getParameter("lname");
phone=request.getParameter("phone");
partner_name=request.getParameter("partner_name");
groups=request.getParameter("groups");
%>
 <form action="edit_facilitator" name="" method="post">
     <table cellpadding="2px" cellspacing="3px" border="0px" >
    <br><br>

  First Name<font color="red">*</font>
  <br>
  <input type="text" name="fname" value="<%=fname%>" class="textbox">
   <input type="hidden" name="facilitator_id" value="<%=facilitator_id%>" class="textbox">
   <br><br>
    Middle Name<font color="red">*</font>
  <br>
 <input type="text" name="mname" value="<%=mname%>" class="textbox">
<br><br>
Last Name<font color="red">*</font>
<br>
<input type="text" name="lname" value="<%=lname%>" class="textbox">
      <br><br>
      Phone Number<font color="red">*</font> 
  <br>
 <input type="text" name="phone" onkeypress="return numbers(event)" pattern=".{10,10}" maxlength="10" value="<%=phone%>" class="textbox"> 
  <br><br>
Partner
<br>
<input type="text" name="partner" value="<%=partner_name%>" class="textbox" readonly="true" style="background: #cccccc">
<br><br>
Groups
<br>

<p style="background: #cccccc; width: 300px; height: 90px" class="textbox"><%=groups%></p>
<br><br>
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
