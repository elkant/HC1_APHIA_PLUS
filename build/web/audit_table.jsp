<%-- 
    Document   : audit_table
    Created on : Sep 4, 2013, 3:44:00 PM
    Author     : SIXTYFOURBIT
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
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         <% dbConn conn= new dbConn();

String adt="select * from audit";

conn.rs=conn.st.executeQuery(adt);




%>
    </head>
    <body>
        <table  cellpadding="0px" cellspacing="0px" border="1px" style="width:840px;" id="viewpdt">
<tr><th>Action</th><th>Time</th><th>User</th><th>Host Machine</th></tr>
<%
while(conn.rs.next()){
    
    String users="Select * from users where userid='"+conn.rs.getString(4) +"'";
    
    conn.rs2=conn.st2.executeQuery(users);
    conn.rs2.next();
%>
<tr><td> <%=conn.rs.getString(2)%> </td><td> <%=conn.rs.getString(3)%></td><td> <%=conn.rs2.getString(2)%></td><td> <%=conn.rs.getString(5)%></td></tr>



<%
}
%>

                    </table>
    </body>
</html>
