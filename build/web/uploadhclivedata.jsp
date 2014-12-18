<%-- 
    Document   : newClerk
    Created on : Aug 8, 2013, 9:49:59 AM
    Author     : SIXTYFOURBIT
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("level")!=null) {}
       else{
        response.sendRedirect("index.jsp");
    } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
            <link rel="shortcut icon" href="images/hc_logo.png"/>
<!--        <link rel="stylesheet"  type="text/css" href="menu/adminmenu_files/css3menu1/style.css" />-->
       <!--menu-->
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
<script src="prefix-free.js"></script>
        <title>Upload HC1 LIVE data</title>
        
     <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>   
   

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>

<!--clerk menu-->

<link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>
<script type="text/javascript">
    
   
</script>
    </head>
    <body onload="getDates();">
       
        
        
        
        <div id="container"  >
       
            <%      if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}
    
      else{%>
       <%@include file="/menu/clerkmenu.jsp"%>
      <%}
   %>
            <br/>
            <h3 style="text-align: center; background-color: orange;">UPLOAD HC LIVE DATA</h3>

 
            
              <div id="header" align="center" style="" >
                  <br/>
                 
    
           
                    
                     <br/>
                    
                    
                    
              </div>
            
            
            <div id="content" style="width:1000px; margin-left: 150px; ">
                
              
                <div id="midcontent" style="margin-left:130px ;">
                    
                    
                   
                        
                   
                   
           <% if (session.getAttribute("feedbackmsg") != null) {
              
               System.out.println(session.getAttribute("feedbackmsg"));          
    
    
           %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("feedbackmsg")%>.<br>Click here  to close this notice',
                        layout: 'center',
                        type: 'Success'});
                    
                </script> 
                <%
                session.removeAttribute("feedbackmsg");
                            }

                        %>
                        <!--creating random user id-->
                       
    
                        
                        
                        
                        
                   
                   <br/><br/>
                    
                    <form action="PULLDB" method="post" style="height:170px;"  enctype="multipart/form-data">
                        <br/>
                       <input type="file" value="Import Data" name="filename" id="filename" style="height:50px; width:400px;" />
                      <br/>
                       <input type="submit" value="Merge" name="filename" id="filename" style="height:50px; " />
                    </form>
                </div>
            </div>

            

             <div id="footer">
              <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->
              
               <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    
        
    </body>
</html>
