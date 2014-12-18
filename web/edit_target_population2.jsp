<%-- 
    Document   : edit_target_population
    Created on : Sep 6, 2013, 8:40:07 AM
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
<%@include file="/menu/adminmenu.jsp"%>
<div id="header" align="center">
</div>
<div id="content" style="height:530px;">
<div id="midcontent" style="margin-left:130px ;">
    <br/><br/>
<h4>Edit and Save Details.</h4>
<br><br>
   <form action="edit_tp" name="" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
    <br><br>
    Choose The Implementing Partner
    <br><br>
    <select name="partner" id="partner" class="textbox4" style=" height: 30px">
        <option value="<%=session.getAttribute("partner_id")%>" selected="true"><%=session.getAttribute("partner_name")%> </option> 
      <%
      dbConn conn=new dbConn();
      
String partner_selector="SELECT * FROM partner WHERE partner_id!='"+session.getAttribute("partner_id").toString()+"'";
  conn.rs=conn.st.executeQuery(partner_selector);
while(conn.rs.next())     
     {
  %>
  
  <option value="<%=conn.rs.getString(1)%>"><%=conn.rs.getString(2)%></option>
    <%}%>    
    </select>
  <br><br>
  Choose Age Group
   <br><br>
  <select name="age_id" id="age_id" class="textbox4" style=" height: 30px">
      
          <option value="<%=session.getAttribute("ager_id")%>" selected="true"><%=session.getAttribute("ager")%> </option> 
      <%
      
String age_selector="SELECT * FROM age_groups WHERE age_group_id!='"+session.getAttribute("ager_id").toString()+"'";
  conn.rs=conn.st.executeQuery(age_selector);
while(conn.rs.next())     
     {
  %>
  
  <option value="<%=conn.rs.getString(1)%>"><%=conn.rs.getString(2)%></option>
    <%}%>     
      
      
  </select>
  <br><br>
Target Population Name
<br><br> 
<input type="text" name="tp_name" value="<%=session.getAttribute("tp_name")%>" class="textbox">
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
               <p align="center"> &copy Aphia Plus | USAID <%=year%></p>
</div>
</div>    

</body>
</html>

