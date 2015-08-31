<%-- 
    Document   : new_topic
    Created on : Aug 14, 2013, 8:00:26 AM
    Author     : Nyabuto Geofrey
--%>
<%@page import="hc.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="css/divCss.css"/>
        <link rel="shortcut icon" href="images/hc_logo.png"/>
        <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
        <link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
        <script src="prefix-free.js"></script>
        <title>Backdate Backupdate

        </title>
        <!--         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>-->
       <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
        <!--calender-->

        <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>	
        <script src="js/js/jquery-ui-1.10.3.custom.js"></script>
        
        <script type="text/javascript" src="js/js/sisyphus.min.js"></script>
          
        
        <script type="text/javascript">
    
    
    var mindate="";
    
    
    
   
    
            $(function() {
                $( "#datepicker1" ).datepicker({
                    dateFormat:'yy-mm-dd',
                                changeMonth: true,
                                changeYear: true
                                , maxDate: new Date()
                    
                });
               
                //$( document ).tooltip();




            });


           
        
        
            


        </script> 

        <script type="text/javascript">
            
       //===============call all the functions
     
            
            
            
            
        </script>
    </head>
    <body onload="">

        <div id="container" >
            <%      if (session.getAttribute("level").equals("0")) {%>
            <%@include file="/menu/adminmenu.jsp" %>
            <%} else {%>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%}
            %>

            <br/>
           
            <div id="header" align="center">
                <br/>

            </div>

                            <%
    if (session.getAttribute("backupupdate") != null) {%>
                <script type="text/javascript"> 
                    
                                    var n = noty({text: '<%=session.getAttribute("backupupdate")%>',
                                        layout: 'center',
                                        type: 'Success',
 
                                        timeout: 4900});
                    
                </script> <%
                        session.removeAttribute("backupupdate");
                    }
                %>
<h3 style="background:orange;text-align: center;">Change the backup date to a custom date</h3>

            <div id="content" style="height:450px;margin-left: 20px;">

              
 <form action="updatebackupdate" method="post" onsubmit="return number_validator();" style="width:1150px;">
 <table cellpadding="4px" cellspacing="3px" border="0px" align="center">
                        <tr align="left"><td>Begin Backup From Date:</td><td><input  type="text" value="" name="backupdate" id="datepicker1" class="textbox" required="true" /></td>    
                        </tr>
 
 <tr><td><input type="submit" value="Save" style="height:35px;" class="submit" ></td></tr>
 </table>

                </form>

            </div>



            <div id="footer">
                <%
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);

                %>
                <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
                
           
            <script> </script>
                
            </div>
        </div>    
<script>
    
     $.ajax({
                        url:'loadMaxTimestamp',                         
                        type:'post',  
                        dataType: 'html',  
                        success: function(data) {
                            
                        document.getElementById("datepicker1").value=data;
                       
                        }
                        
                         });
    
 
    
</script>
    </body>
</html>
