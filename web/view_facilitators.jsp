<%-- 
    Document   : view_facilitators
    Created on : Sep 16, 2013, 11:57:37 AM
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
<title>View Facilitator.</title>

<script type="text/javascript">
function group_them(){
    var partner_id=document.getElementById("partner").value;   
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
xmlhttp.open("POST","group_loader?partner_id="+partner_id,true);
xmlhttp.send();     
}
</script> 
</head>
  <%if (session.getAttribute("edit_facilitator") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("edit_facilitator")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 2400});
                    
                </script> <%
                session.removeAttribute("edit_facilitator");
                            }

                        %>

                        <body>      
                        
                        <div id="container" >
<%@include file="/menu/guest_menu.jsp"%>
<div id="header" align="center">
</div>
<div id="content">
    <br>
<div id="midcontent" style="margin-left:10px ; width: 750px;">
                              
    <table cellpadding="2px" cellspacing="3px" border="0px" class="table_style" style="width:870px; background: #3366ff" >
        <tr><td colspan="8"><form action="view_facilitators"   method="post"  style="width:860px;" id="et" onsubmit="return nullness();" bgcolor="plum">
                                  <table >
                    
                                      <tr >
                 
                                          <th>  <input type="text" list="allnames" name="sname" id="sname" value="" placeholder="Enter Name" maxlength="25" class="textbox1" style="background: "/>
                        <datalist id="allnames">
  
</datalist>
                        </th> 
                       <th colspan="2"> <select  id="partner" name="partner" class="textbox2" onchange="return group_them();">
                     <option value="" selected="true">Choose Partner </option>
<%
String selector="select * from partners";
dbConn conn=new dbConn();
conn.rs=conn.st.executeQuery(selector);
while(conn.rs.next())
{

%>
<option value="<%=conn.rs.getString("partner_id")%>"><%=conn.rs.getString("partner_name")%></option>
                  <%}%>
                 </select></th> 
                    
                 <th colspan="2"> <select  name="group" class="textbox2" id="groups" hidden="true">
                     <option value="" selected="true">Choose Group </option>
<%
String selector2="select * from groups";
conn.rs=conn.st.executeQuery(selector2);
while(conn.rs.next())
{

%>
<option value="<%=conn.rs.getString("group_id")%>"><%=conn.rs.getString("group_name")%></option>
                  <%}%>
                   
                 </select>
                  <input type="number" name="phone" value="" class="textbox1" placeholder="Phone number">         
                 
                 </th> 
                    

                    
             <th colspan="2">  <input type="submit"  name="sub" value="Search..." class="textbox1"/>   </th>  
                    
                
                </tr>     
</table>
    </form></td><tr>
        <tr>      
      <th>First Name</th>
     <th>Middle Name</th>
      <th>Last Name</th>
      <th>Phone No.</th>
      <th>Partner</th>
      <th>Groups</th>
  </tr>  
  <c:forEach items="${vlist}" var="details"> 
 <tr>   
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
  ${details.phone}  
 </td>  
 <td>   
  ${details.partner_name}  
 </td>   
 <td>   
  ${details.group_name}  
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
