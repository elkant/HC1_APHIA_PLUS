<%--
Document : transfer_one_teacher
Created on : Sep 12, 2019, 9:21:36 AM
Author : Geofrey Nyabuto
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
<link rel="stylesheet" type="text/css" href="css/new_hc_css.css"/>

<link href="menu/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/bootstrap.min.css" media="screen" rel="stylesheet" type="text/css" />
<link href="css/bootstrap-multiselect.css" media="screen" rel="stylesheet" type="text/css" />

<!--<link href="menu/iconic.css" media="screen" rel="stylesheet" type="text/css" />-->
<link rel="shortcut icon" href="images/hc_logo.png"/>
<script type="text/javascript" src="js/jquery-2.1.1.js"></script>


<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-multiselect.js"></script>


<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script>


<style>
    
    td{
        padding:4px;
        
    }   
    
</style>

<title>Edit Facilitator</title>



  <script type="text/javascript">
        $(document).ready(function() {
           
        });
    </script>


<script type="text/javascript">
    
    
 
    
function group_them(){
var partner_id=document.getElementById("partner").value;
var fname=document.getElementById("fname").value;
var mname=document.getElementById("mname").value;
var lname=document.getElementById("lname").value;
var phone=document.getElementById("phone").value;
// alert(partner_id);
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
     $('#groups').html('');
$('#groups').append(xmlhttp.responseText);
                   // $('#groups').multiselect('deselectAll', false);
                   // $('#groups').multiselect('updateButtonText');
                   $('#groups').multiselect({
                includeSelectAllOption: true,
                enableFiltering: true,
                checkboxName: 'multiselect[]',
                buttonWidth: '225px',
                maxHeight: 300
            });
                   
   

//alert(xmlhttp.responseText);
 //$('#groups').multiselect('dataprovider', xmlhttp.responseText); 
  
 
          
}
}
xmlhttp.open("POST","group_loader?partner_id="+partner_id+"&fname="+fname+"&mname="+mname+"&lname="+lname+"&phone="+phone,true);
xmlhttp.send();
}








</script>
</head>
<body onload="return group_them();">
<div id="container" >
<%@include file="/menu/clerkmenu.jsp"%>
<div id="header" align="center">
<br/>
</div>
<div id="content" style="height:750px;">
<div id="midcontent" style="margin-left:130px ;">
<h4 style="background-color: orange">Edit Facilitator Groups.</h4><br>

<%
String facilitator_id,fname,mname,lname,phone,partner_name,groups,partner_id;
facilitator_id=request.getParameter("facilitator_id");
fname=request.getParameter("fname");
mname=request.getParameter("mname");
lname=request.getParameter("lname");
phone=request.getParameter("phone");
partner_name=request.getParameter("partner_name");
groups=request.getParameter("groups");
//groups=groups.replace(",","<br/>");
partner_id=request.getParameter("partner_id");
%>
<form action="transfer_facilitator" name="" method="post">
<table cellpadding="4px" cellspacing="4px" border="0px" >
<tr><td style="text-align:right;"><b>
First Name:</b>
</td><td>
<input type="text" id="fname" name="fname" value="<%=fname%>" class="textbox" readonly="true" style="background: #cccccc">
<input type="hidden" name="facilitator_id" id="facilitator_id" value="<%=facilitator_id%>" class="textbox">
</td></tr>
<tr><td style="text-align:right;"><b>
Middle Name:</b>
</td><td>
<input type="text" id="mname" name="mname" value="<%=mname%>" class="textbox" readonly="true" style="background: #cccccc">
</td></tr>
<tr><td style="text-align:right;"><b>
Last Name:</b>
</td><td>
<input type="text" id="lname" name="lname" value="<%=lname%>" class="textbox" readonly="true" style="background: #cccccc">
</td></tr>
<tr><td style="text-align:right;"><b>
Phone Number: </b>
</td><td>
<input type="text" id="phone" name="phone" value="<%=phone%>" class="textbox" readonly="true" style="background: #cccccc">
</td></tr>
<tr>
<td style="text-align:right;">
<b>Current Partner:<font color="red">*</font></b>
</td>
<td>
<input type="text" name="part" id="part"value="<%=partner_name%>" class="textbox" readonly="true"style="background: #cccccc">
<input type="hidden" name="grp" id="grp"value="<%=groups%>" class="textbox" readonly="true"style="background: #cccccc"/>
</td></tr>
<tr><td style="text-align:right;">
<b>Select New Partner:</b>
</td><td>
<select name="partner" style="width:225px;height:38px" id="partner" class="textbox1" onchange="group_them();">
   
<option value="<%=partner_id%>"><%=partner_name%></option>
<% String selector ="select * from partner where partner_id!='"+partner_id+"'";
dbConn conn=new dbConn();
conn.rs=conn.st.executeQuery(selector);
while(conn.rs.next()){
%>
<option value="<%=conn.rs.getString("partner_id")%>"><%=conn.rs.getString("partner_name")%></option>
<%}%>
</select>
</td></tr>
<tr>
<td style="text-align:right;"><b>Select Group:</b></td><td>
<select name="groups"  id="groups" class="" style="width:220px;height:30px;" required="true"  multiple="multiple">
<option value="">loading groups...</option>
</select>
    
</td></tr>
<tr><td></td>
<td >
<input type="submit" name="sub" value="Save"  style="background: #cc99ff; color: #0000ff;height:36px;width:120px;" >
</td></tr>
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
