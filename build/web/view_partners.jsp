<%-- 
    Document   : view_partners
    Created on : Sep 16, 2013, 12:10:06 PM
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
<title>View Partners.</title>

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
<div id="container" >
<%@include file="/menu/guest_menu.jsp"%>
<div id="header" align="center">
</div>
<div id="content">
    
     <%if (session.getAttribute("missing_partner") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("missing_partner")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("missing_partner");
                            }

                        %>
    
    <br>
<div id="midcontent" style="margin-left:10px ; width: 750px;">
                              
    <table cellpadding="2px" cellspacing="3px" border="0px" class="table_style" style="width:870px; background: #3366ff" >
        <tr>
     <form action="view_partners" method="post">

    <th colspan="4">
        <select name="district_id" class="textbox1" style="background: #9999ff; width: 150px" >
            <option value="">Choose District</option>
            <%
   String selector="select * from district order by district_name" ;
dbConn conn=new dbConn();
conn.rs=conn.st.executeQuery(selector);
while(conn.rs.next()){        
%>
<option value="<%=conn.rs.getString("district_id")%>"><%=conn.rs.getString("district_name")%></option>

<%}%>
        </select>
        <input type="submit" value="search..." class="textbox" style="height: 30px; background: #666600; width: 80px"></th>
    </tr>
        <tr>      
      <th>Number</th>
      <th>District Name</th>
     <th>Partner Name</th>
      
  </tr>  
   <c:forEach items="${plist}" var="details"> 
 <tr>   
 <td>   
  ${details.count}
 </td>
  <td>   
  ${details.district_name}
 </td> 
 <td>  
  ${details.partner_name}
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