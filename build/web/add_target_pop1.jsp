<%-- 
    Document   : add_target_pop1
    Created on : Sep 17, 2013, 4:57:40 PM
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
<title>Add target Population.</title>
<script type="text/javascript">
   
    function partners(){

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
xmlhttp.open("POST","load_all_partners",true);
xmlhttp.send();
}//end of partner picker
</script>
</head>
<body onload="return partners();">
<%
session.removeAttribute("partner_id");
session.removeAttribute("partner_name");

%>
<div id="container" >
 <%   if (session.getAttribute("userid").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>
            <br/>
            <h3 style="text-align: center; background-color: orange;">ADDING TARGET POPULATIONS</h3>
<div id="header" align="center">
</div>
<div id="content" style="height:530px;">
<div id="midcontent" style="margin-left:130px ;">
    <br/><br/>

<h4>Select Partner to add Target Population.</h4>
<br><br>
   <form action="add_target_po_session" name="" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
    <br><br>
Partners Name<font color="red">*</font>
<br><br>
 <Select  required ="true" name="partner" id="partner" class="textbox2" style="background: #cc99ff">
<option value="">Choose Partner</option>  
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
               <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
</div>
</div>    

</body>
</html>
