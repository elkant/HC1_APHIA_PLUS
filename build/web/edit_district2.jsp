<%-- 
    Document   : edit_district2
    Created on : Sep 5, 2013, 12:46:17 PM
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
<%@page import="hc.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/divCss_2.css"/>
    <link href="menu/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="menu/iconic.css" media="screen" rel="stylesheet" type="text/css" />
<title>Edit Districts.</title>
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
            <h3 style="text-align: center; background-color: orange;">EDITING DISTRICT</h3>
<div id="header" align="center">
</div>
<div id="content" style="height:530px;">
<div id="midcontent" style="margin-left:130px ;">
    <br/><br/>
<h4>Edit and Save Details.</h4>
<br><br>
   <form action="edit_district" name="" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
    <br><br>
  County
  <br><br>
  <select name="county_id" class="textbox2">
      <option value="<%=session.getAttribute("county_id")%>" selected><%=session.getAttribute("county_name")%></option>
      
      
     <%String selector="select * from county WHERE county_id!='"+session.getAttribute("county_id")+"'";
     dbConn conn =new dbConn();
conn.rs=conn.st.executeQuery(selector);
while(conn.rs.next())
{
%>
     <option value="<%=conn.rs.getString(1)%>" ><%=conn.rs.getString(2)%></option>
     <%}%>
 </select>
  
<br><br>
District Name
<br><br> 
<input type="text" name="district_name" value="<%=session.getAttribute("district_name")%>" class="textbox">
<br><br>
<input type="submit" name="sub" value="Save Details" class="textbox1" style="background: #cc99ff; color: #0000ff" >
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
