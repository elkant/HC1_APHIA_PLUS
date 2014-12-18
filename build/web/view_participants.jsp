<%-- 
    Document   : view_participants
    Created on : Sep 17, 2013, 12:11:40 PM
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<title>View Participants.</title>
    </head>
      <body>      
                        
                        <div id="container" >
<%@include file="/menu/guest_menu.jsp"%>
<div id="header" align="center">
</div>
<div id="content">
    <br>
<div id="midcontent" style="margin-left:10px ; width: 750px;">
                              
    <table cellpadding="2px" cellspacing="3px" border="0px" class="table_style" style="width:870px; background: #3366ff" >
        <tr><td colspan="8"><form action="view_participants"   method="post"  style="width:860px;" id="et" onsubmit="return nullness();" bgcolor="plum">
                                  <table >
                    
                                      <tr >
                 
                                          <th>  <input type="text" list="allnames" name="name" id="sname" value="" placeholder="Enter Name" maxlength="25" class="textbox1" style="background: "/>
                        <datalist id="allnames">
  
</datalist>
                        </th> 
                       <th colspan="2"> <select  id="partner" name="group_id" class="textbox2" onchange="return group_them();">
                     <option value="" selected="true">Choose Group </option>
<%
String selector="select * from groups";
dbConn conn=new dbConn();
conn.rs=conn.st.executeQuery(selector);
while(conn.rs.next())
{

%>
<option value="<%=conn.rs.getString("group_id")%>"><%=conn.rs.getString("group_name")%></option>
                  <%}%>
                 </select></th> 

                    
             <th colspan="2">  <input type="submit"  name="sub" value="Search..." class="textbox1"/>   </th>  
      
                </tr>     
</table>
    </form></td><tr>
        <tr> 
      <th>Number</th>
     <th>Group Name</th>
      <th>First Name</th>
     <th>Middle Name</th>
      <th>Last Name</th>
      <th>Sex</th>
      <th>Age</th>
      <th>sessions Attended</th>
  </tr>  
  <c:forEach items="${mlist}" var="details"> 
 <tr>
  <td>   
  ${details.count}  
 </td>    
  <td>   
  ${details.group_name}  
 </td>   
 <td>   
  ${details.fname}  
 </td>
  <td>   
  ${details.mname}  
 </td> 
 <td>   
 ${details.lname}   
 </td>   
  <td>   
  ${details.sex}  
 </td>  
 <td>   
  ${details.age}  
 </td>   
  <td>   
  ${details.sessions_attended}  
 </td>
 </tr> 
  </c:forEach>    
    
    
    </table>  
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
