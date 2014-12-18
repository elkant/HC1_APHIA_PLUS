<%-- 
    Document   : groups_overall
    Created on : Mar 2, 2014, 11:55:55 AM
    Author     : Geofrey Nyabuto
--%>
<%-- 
Document   : teacher_reg
Created on : Aug 6, 2013, 12:38:52 PM
Author     : SIXTYFOURBIT
--%>


<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
    

    if (!session.getAttribute("level").equals("0") && !session.getAttribute("level").equals("5")) {
        response.sendRedirect("index.jsp");
    } 
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
<link rel="shortcut icon" href="images/hc_logo.png"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		<link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
		<script src="prefix-free.js"></script>
                
                
                
<title>Select Year and Period</title>
<script type="text/javascript">
function get_years(){

// window.open("districtchooser?county="+dist.value);     
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
document.getElementById("year").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","years_loader",true);
xmlhttp.send();
}
//set the partner
function get_period(){
// window.open("districtchooser?county="+dist.value);     
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
document.getElementById("period").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","period_loader",true);
xmlhttp.send();
}

</script>

</head>
<body onload="get_years(); get_period();">

<div id="container" >
 <%     if(session.getAttribute("level").equals("5")){  %>
  <%@include file="/menu/guest_menu.jsp" %>
  <%} if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}%>
 
    <br/>
            <h3 style="text-align: center; background-color: orange;">PARTNER BAR GRAPHS</h3>
 
<div id="header" align="center">
<br/>

<%!
public int generateRandomNumber(int start, int end ){
Random random = new Random();
long fraction = (long) ((end - start + 1 ) * random.nextDouble());
return ((int)(fraction + start));
}
%>  





</div>


<div id="content" style="height:450px;">


<div id="midcontent" style="margin-left:130px ;">
<h4>Specify details appropriately</h4>

<h4>




</h4>
<p><font color="red">*</font> indicates must fill fields</p>
<form action="overall_session" method="get">
<table cellpadding="2px" cellspacing="3px" border="0px">
<input type="hidden" name="src" value="partner_bar_chart.jsp">
<tr> <td class="align_button_right" style="width:300px;">Choose Year <font color="red">*</font></td>
<td><Select name="year" id="year" class="textbox6"   required ="true">
 <option value="">Choose Year</option> 


</select></td>
</tr>

<tr> <td class="align_button_right">Choose Period<font color="red">*</font></td>
<td><Select name="period" id="period" class="textbox6"   required ="true">
 <option value="">Choose Period</option> 


</select></td>
</tr>

<tr><td></td><td><input type="submit" value="Next" class="submit"/></td></tr>

</table>
</form>
</div>
</div>

<div id="footer">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
            </div>
</div>    

</body>
</html>
