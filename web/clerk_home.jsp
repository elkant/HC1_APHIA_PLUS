<%-- 
    Document   : index
    Created on : Aug 5, 2013, 9:03:18 PM
    Author     : SIXTYFOURBIT
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>clerk Home</title>
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         <link rel="shortcut icon" href="images/hc_logo.png"/>
          <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
    <link href="menu/style.css" media="screen" rel="stylesheet" type="text/css" />
		<link href="menu/iconic.css" media="screen" rel="stylesheet" type="text/css" />
		<script src="menu/prefix-free.js"></script>    
    </head>
    
    <!-- Body page -->
    
       
       
    <body>
        <div id="container">

         
             <%@include file="/menu/clerkmenu.jsp" %>
            
            
            <div id="header">
                
            </div>

            <div id="leftBar">
                
            </div>

            <div id="content">
                

                <div id="midcontent">
                    
                  
                    
                    <br/><br/>
                  
                </div>
            </div>

            <div id="rightBar">
               
            </div>

            <div id="footer">
              <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->
               
               <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center"> &copy Aphia Plus | USAID <%=year%></p>
            </div>
        </div>
    </body>
    
    
</html>
