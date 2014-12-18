<%-- 
    Document   : add_partner
    Created on : Sep 13, 2019, 8:08:33 AM
    Author     : Geofrey Nyabuto
--%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

        if (session.getAttribute("level")==null || session.getAttribute("level").equals("")){
        response.sendRedirect("index.jsp");
    }
    

    if (!session.getAttribute("level").equals("0")) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page import="java.util.Calendar"%>
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
<title>Add Partner.</title>
<script type="text/javascript">
    function county(){

var dist=district.value;    
   
var xmlhttp;    
if (dist=="")
{
//filter the counties    



//document.getElementById("county").innerHTML="<option value=\"\">choose district</option>";
//return;
}
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
document.getElementById("county").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","countychooser?county="+county,true);
xmlhttp.send();
}//end of count picker
    
    
    
function filter_districts(){

        var county_id=document.getElementById("county").value;        
var xmlhttp;    
if (county_id=="")
{
//filter the districts    



document.getElementById("district").innerHTML="<option value=\"\">Choose district</option>";
return;
}
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
document.getElementById("district").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","districtchooser?county_id="+county_id,true);
xmlhttp.send();
}//end of filter districts


    function partners(){

            var district=document.getElementById("district").value;    
//   alert (district);
var xmlhttp;    
if (district=="")
{
//filter the partners   



document.getElementById("partner").innerHTML="<option value=\"\">Partner</option>";
return;
}
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
document.getElementById("partner").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","partner_loader?district="+district,true);
xmlhttp.send();
}//end of partner picker
</script>
</head>
<body onload="return county();">
<%
session.removeAttribute("county_id");
session.removeAttribute("county_name");
session.removeAttribute("district_id");
session.removeAttribute("district_name");
%>
<div id="container" >
 <%   if (session.getAttribute("userid").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>
            <br/>
            <h3 style="text-align: center; background-color: orange;">ADDING PARTNERS</h3>
<div id="header" align="center">
</div>
<div id="content" style="height:530px;">
<div id="midcontent" style="margin-left:130px ;">
    <br/><br/>

<h4>Select County, District to add Partner.</h4>
<br><br>
   <form action="add_partner_session" name="" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
    <br><br>

  County<font color="red">*</font>
  <br><br>
 
  <Select  required ="true" name="county"  id ="county" class="textbox2" onchange="return filter_districts();">
<option value="">Choose County</option>  
</select>
<br><br>
District<font color="red">*</font>
<br><br>
<Select  required ="true" name="district" id="district" class="textbox2">
<option value="">Choose District</option>  
</select>
<br><br>
<input type="submit" name="sub" value="Next" class="textbox1" style="background: #cc99ff; color: #0000ff" >
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