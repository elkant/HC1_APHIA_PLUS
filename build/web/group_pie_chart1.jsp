<%-- 
    Document   : group_pie_chart1
    Created on : Sep 19, 2013, 1:40:11 PM
    Author     : Geofrey Nyabuto
--%>
<%-- 
    Document   : group_reports
    Created on : Sep 18, 2013, 2:58:25 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="hc.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

        if (session.getAttribute("level")==null || session.getAttribute("level").equals("")){
        response.sendRedirect("index.jsp");
    }
    

    if (!session.getAttribute("level").equals("0") && !session.getAttribute("level").equals("5")) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Group Reports</title>
 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/noty/jquery.noty.js"></script>
<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script>
        <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>	
    <script type="text/javascript" src="js/jquery-ui.js"></script>
        
        
        
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/hc_logo.png"/>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  
    </head>
    <body onload="dates_loader();">
       
     <div id="container" >
         <%     if(session.getAttribute("level").equals("5")){  %>
  <%@include file="/menu/guest_menu.jsp" %>
  <%} if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}%>
              <div id="header" align="center">
                  <br/>
                  
      
              </div>
         
            
            <div id="content" style="height:500px;">
                
              
                <div id="midcontent" style="margin-left:310px ; width: 400px">
                    <h4>Select Group To Generate a pie chart Report.</h4>
 <%if (session.getAttribute("#") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("#")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 1800});
                    
                </script> <%
                session.removeAttribute("#");
                            }

                        %>
                        
                        <% //session.removeAttribute("group_id")%>  
                    <p></p>
                    <form action="group_pie_chart_session" method="" onsubmit="return date_validator();">
                        <br/>
                        <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                            <tr style="position: relative; left: 200px">  
                  <td>
   <Select id="group_id" required   onchange="" name="group_id" class="textbox6">
   <option value="">Choose Group</option> 
<%
  String selector="select * from groups";
dbConn conn=new dbConn();
conn.rs=conn.st.executeQuery(selector);
while (conn.rs.next())
{

%>
<option value="<%=conn.rs.getString("group_id")%>"><%=conn.rs.getString("group_name")%></option>

<%}%>
   </select>
                            </td>   
                            <td>
<input type="submit" value="Generate Pie Chart" class="textbox" onclick="return actioner()"/>      
                            </td>
                            
                            
                            </tr>
                            
                            <tr>
                              
                            </tr>
                            
                            
                            <tr>
                               
                            </tr>
                          
                            <tr>
                            
                            </tr>
                            <tr>
                                <td>
                                    
                                    
                                                           
                                </td>
                                 
                            </tr>
                          
                     

                            
                         
                           <tr></tr>
                            
                           <tr>
                             </tr>
                           <tr>
                           
                           </tr>      
            
                            
                           
                           <tr> 
                              
                            </tr>
                        </table>
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
