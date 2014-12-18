<%-- 
    Document   : newClerk
    Created on : Aug 8, 2013, 9:49:59 AM
    Author     : SIXTYFOURBIT
--%>

<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         
         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
        <link rel="shortcut icon" href="images/hc_logo.png"/>
        <title>Guest</title>
      
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.js"></script>
   
   
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
        </script> 
        
    </head>
    <body>
       
     <div id="container" >
              <div id="header" align="center">
                  <br/>
       
              </div>
            
            
            <div id="content" style="height:750px;">
                
              
                <div id="midcontent" style="margin-left:130px ;">
                    <h3>Enter all your details.</h3>
                    
                    <h4>
                        
                     <%
                            if (session.getAttribute("clerk_added") != null) {
                                out.println(session.getAttribute("clerk_added"));
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
                        
                        
                        
                        
                   
                     </h4>
                    <p><font color="red">*</font> indicates must fill fields</p>
                    <form action="add_guest" method="post">
                        <br/>
                        <table cellpadding="2px" cellspacing="3px" border="0px">
                       
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">User id<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text readonly value="<%=generateRandomNumber(1000,10888)%>" required name=userid class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">Surname<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text required name="lname" student_name class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">First Name<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text required name="fname" student_name class="textbox"/></td>
                                <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                <td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                <td><a href="index.jsp" class="linkstyle">Go Back</a></td></tr>
                            <tr>

                               
                            </tr>
                            
                            <tr>
                                <td class="align_button_left"><label for="first_name">Middle Name<font color="red">*</font></label></td>
                                <td><input id="first_name" type=text required name="mname" student_name class="textbox"/></td></tr><tr>

                               
                            </tr>
                            
                            
                            <tr>
                               
                            <td class="align_button_left"><label for="email">Position</label></td>
                            <td><input  type="text" name="position"  class="textbox" /></td>
                            
                           
                            
                            </tr>
                            <tr>
                                <td>
                                    
                                    
                                                           
                                </td>
                                 
                            </tr>
                          
                     

                            
                         
                           <tr> <td class="align_button_left"><label for="town">Username <font color="red">*</font></label></td>
                            <td><input id="town" type=text required name=username class="textbox"/></td></tr>
                            
                           <tr>
                            <td class="align_button_left"><label for="password">Password<font color="red">*</font></label></td>
                            <td><input id="password" type=password required name=password oninput="checkPasswords()" class="textbox"/></td>
                           </tr>
                           <tr>
                           
                                <td class="align_button_left"><label for="conf_password">Confirm Password<font color="red">*</font></label></td>
                                <td ><input id="conf_password" type=password required name=conf_password oninput="checkPasswords()" class="textbox"/></td>
                                <td></td>
                           </tr>      
            
                            
                           
                           <tr> 
                               <td class="align_button_left"></td> <td class="align_button_right">
                               <input type="submit" class="submit" value="Register"/></td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>

            

            <div id="footer">
              <p align="center"> &copy lse 2013</p>
            </div>
        </div>    
        
    </body>
</html>
