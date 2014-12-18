<%-- 
    Document   : newClerk
    Created on : Aug 8, 2013, 9:49:59 AM
    Author     : SIXTYFOURBIT
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
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("level")==null) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
         <link rel="shortcut icon" href="images/hc_logo.png"/>
        <title>Add New Clerk</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		
		<script src="menu/prefix-free.js"></script>  
   <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
      <script type="text/javascript">
           
         
           
           
            function checkPasswords() {
                var password = document.getElementById('password');
                var conf_password = document.getElementById('conf_password');

                if (password.value != conf_password.value) {
                    conf_password.setCustomValidity('Passwords do not match');
                } else {
                    conf_password.setCustomValidity('');
                }
                
          
        
            }
            
            
            /**
             calculating date of birth
             **/
     

//var age = getAge('08/08/2000');
//alert(age);
        
     
$(function() {
$( "#datepicker" ).datepicker();
});

            
        </script> 
        
    </head>
    <body onload="no_of_classes(this);">
       
     <div id="container" >
<!--          <br/>-->
 <%   if(session.getAttribute("level").equals("2")){ 
            %>
<%@include file="/menu/clerkmenu.jsp"%>
<%} else{%>
<%@include file="/menu/adminmenu.jsp"%>
<%}%>
              <div id="header" align="center">
                 
                   
              
           
                  
           
                    
                    
                    
                    
                    
              </div>
            
              <h3 style="text-align: center; background-color: orange;">ADDING A USER</h3>
            <div id="content" style="height:600px;margin-left:100px;">
                
              
                <div id="midcontent" style="margin-left:250px ;">
                    
                      
                        
                        
                    <h4 style="text-align: center;">Enter user details</h4>
                    
                  
                    
                   
                        
                   
                   
                         <%if (session.getAttribute("clerk_added") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("clerk_added")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 1800});
                    
                </script> <%
                session.removeAttribute("clerk_added");
                            }

                        %>
                        <!--creating random user id-->
                         <%!
    public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
%>  
                        
                        
                        
                        
                   
                   
                    <p><font color="red">*</font> indicates must fill fields</p>
                    <form action="saveClerk" method="post">
                        <br/>
                        <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:150px ;">
                       
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">Userid<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text readonly value="<%=generateRandomNumber(1000,10888)%>" required name=userid class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">Surname<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text required name="s_name" student_name class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">First Name<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text required name="f_name" student_name class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                           
                            
                            
                            <tr>
                               
                            <td class="align_button_left"><label for="email">Phone Number</label></td>
                            <td><input  type="text" name="phoneno" pattern="[0-9]{10,10}" class="textbox" /></td>
                            
                           
                            
                            </tr>
                            <tr>
                                <td>
                                    
                                    
                                                           
                                </td>
                                 
                            </tr>
                          
                     

                            
                         
                           <tr> <td class="align_button_left"><label for="town">Username <font color="red">*</font></label></td>
                            <td><input id="town" type=text required name=username class="textbox"/></td></tr>
                            
                           <tr>
                            <td class="align_button_left"><label for="password">Password<font color="red">*</font></label></td>
                            <td><input id="password" type=password required name=pass oninput="checkPasswords()" class="textbox"/></td>
                           </tr>
                           <tr>
                           
                                <td class="align_button_left"><label for="conf_password">Confirm Password<font color="red">*</font></label></td>
                                <td ><input id="conf_password" type=password required name=conf_password oninput="checkPasswords()" class="textbox"/></td>
                                <td></td>
                           </tr>      
            
                             <tr>
                           
                                <td class="align_button_left"><label for="conf_password">User Level<font color="red">*</font></label></td>
                                <td ><select id="ulevel"  required name="ulevel"  style="height:30px;">
                                    <option value="">user level</option>
                                    <option value="0">Administrator</option>
                                    <option value="2">System User</option>
                                    </select></td>
                                <td></td>
                           </tr>   
                           <tr><td></td></tr>
                           
                           <tr> 
                               <td class="align_button_left"><input  size="12px"  type="reset" value="clear" /></td> <td class="align_button_right">
                               <input type="submit" style="height:35px;width:120px;background-color: orange;" value="Add" onmouseover=""/></td>
                            </tr>
                        </table>
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
