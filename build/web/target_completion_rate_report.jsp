<%-- 
    Document   : target_completion_rate_report
    Created on : Nov 4, 2013, 11:57:47 AM
    Author     : Geofrey Nyabuto
--%>

<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    
    
if(session.getAttribute("level")!=null){
    if (!session.getAttribute("level").equals("0")&&!session.getAttribute("level").equals("2") && !session.getAttribute("level").equals("5")) {
        response.sendRedirect("index.jsp");
    }  }
%>
<%@page import="java.util.Calendar"%>
<%@page import="hc.dbConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Target Bar Chart</title>
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         <link rel="shortcut icon" href="images/icon.PNG"/>
          <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>


<!--menu-->
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
<script src="prefix-free.js"></script>

<script type="text/javascript">
    
   
</script>
        


    </head>
    
    <!-- Body page -->
    
       
       
    <body>
        <div id="container" style="height:750px; width: 1200px;">
         <% if(session.getAttribute("level")!=null){    if(session.getAttribute("level").equals("5")){  %>
  <%@include file="/menu/guest_menu.jsp" %>
  <%} if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}if(session.getAttribute("level").equals("2")){ %>
   <%@include file="/menu/clerkmenu.jsp" %>
 <%}   }
    
    else{ %>
       
         <%@include file="/menu/guest_menu.jsp" %>
       
      <%    }
   %>
      <br/>
            <h3 style="text-align: center; background-color: orange;">Overall Target Population Bar</h3>
            <div id="header">
                
            </div>

           

            <div id="content">
                

                <!--<div id="midcontent" style="width: 1300px;margin-left: -150px;">-->
                    
                    <img width="1000px" style="margin-left:25px;" height="600px"  src="target_pop_bar_chart">
                    
       
                    
                    
                 
                <!--</div>-->
            </div>

           

            <div id="footer">
                           </div>
        </div>
    </body>
    
    
</html>


