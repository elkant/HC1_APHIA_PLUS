<%-- 
    Document   : edit_clerk
    Created on : Aug 15, 2013, 11:18:53 AM
    Author     : Nyabuto Geofrey
--%>

<%@page import="java.util.Calendar"%>
<%@page import="hc.dbConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/divCss.css"/>
<link rel="shortcut icon" href="images/hc_logo.png"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script>
		
		<script src="menu/prefix-free.js"></script> 
<title>Edit Clerk Details</title>
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
            <h2 style="text-align: center; background-color: orange;">EDITING USERS</h2>
    <div id="header">
        <br/>
        
         </div>
<div id="content" style="height:750px;">


<div id="midcontent" style="margin-left:130px ;">
<h6>Edit User Details</h6>


  <%
                            if (session.getAttribute("success_edit") != null)  { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("success_edit")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 1800});
                    
                </script> <%
                
                session.removeAttribute("success_edit");
                            }

                        %>


<h4> 
          <%
         dbConn conn=new dbConn();
  int counter=1;
  String editor="select * from clerks join users on clerks.clerk_id=users.userid";
  conn.rs=conn.st.executeQuery(editor);%>
</h4>
<table cellpadding="2px" cellspacing="3px" border="0px" class="cart_style" style="width:800px;">
<tr>
    <th>No.</th>
 <th>First Name</th>   
  <th>Last name</th> 
   <th>Username</th>
    <th>Phone no</th> 
     <th>Edit</th> 
     
</tr>
<%
 while(conn.rs.next()){  
%>
<tr>
    <td><%=counter++%></td>

    <%String userid=conn.rs.getString("userid");%>
    <td><%=conn.rs.getString("first_name")%></td>  

    <td><%=conn.rs.getString("sur_name")%></td> 
    <td><%=conn.rs.getString("username")%></td> 
    <td><%=conn.rs.getString("phone")%></td>  
    <td> 
        <form action="edit_clerk2.jsp" method="post" id="tableform">
<input type="hidden" name="userid" value="<%=userid%>"/>
<input type="submit" name="Submit" value="Edit" class="textbox3"/>                   
   </form></td>
</tr>
    <%}%>
</table>

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

