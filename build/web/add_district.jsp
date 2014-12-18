<%-- 
    Document   : add_district
    Created on : Sep 4, 2013, 9:54:49 PM
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
  <link rel="shortcut icon" href="images/hc_logo.png"/>
    <link href="menu/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="menu/iconic.css" media="screen" rel="stylesheet" type="text/css" />
<title>Add Districts.</title>
</head>
<body>

<div id="container" >
 <%   if (session.getAttribute("userid").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>
            <br/>
            <h3 style="text-align: center; background-color: orange;">ADDING DISTRICT</h3>
<div id="header" align="center">
</div>
<div id="content" style="height:520px;">
<div id="midcontent" style="margin-left:130px ;">
    <br/><br/>
<h4>Select County to add Districts.</h4>
<br><br>
   <form action="add_dist_session" name="" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
    <br><br>

  County<font color="red">*</font>
  <br><br>
 
  <Select  required ="true" name="county" class="textbox2" style="background: #cc99ff">
<option value="">Choose County</option>  
<option value="4,Baringo">Baringo</option>
<option value="5,Kajiado">Kajiado</option>      
<option value="2,Laikipia">Laikipia</option>
<option value="1,Nakuru">Nakuru</option>
<option value="3,Narok">Narok</option>
</select>

<br><br>
<input type="submit" name="sub" value="Add District" class="textbox1" style="background: #cc99ff; color: #0000ff" >
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