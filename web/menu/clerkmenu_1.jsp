<%-- 
    Document   : clerkmenu
    Created on : Apr 15, 2014, 10:26:42 AM
    Author     : SIXTYFOURBIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
    <head>
       
       
        <link rel="stylesheet" href="clerkmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>

    </head>
    <body style="background-color:#EBEBEB">
  
        <ul id="css3menu1" class="topmenu">
            <li class="topmenu"><a href="cohort_home.jsp" style="width:100px;height:21px;line-height:21px;"><span>Home</span></a>
            </li>

            <li class="topmenu"><a href="adduser.jsp" style="width:101px;height:21px;line-height:21px;"><span>Add User</span></a>
            </li>

            <li class="topmenu"><a href="#" style="width:101px;height:21px;line-height:21px;"><span>Form Entry</span></a>
                <ul>

                    <li style="width:141px;height:35px;line-height:21px;"><a href="enterdata.jsp">Enter/Edit Data</a></li>

                </ul></li>
            <li class="topmenu"><a href="add_edit_target.jsp" style="width:112px;height:21px;line-height:21px;">Edit Targets</a></li>

            <li class="topmenu"><a href="#" style="width:91px;height:21px;line-height:21px;"><span>User Profile</span></a>
                <ul>
<!--                    <li class="sublast"><a href="edit_ur_details.jsp">Edit user details</a></li>-->
                </ul></li>


            <li class="topmenu"><a href="#"  style="width:102px;height:21px;line-height:21px;">Data</a>
                <ul>
                    <li><a href="backupdata.jsp" title="internet connection is needed to complete backup succesfully" style="width:81px;height:21px;line-height:21px;">Data Backup</a></li>
                    <li><a href="datareceiver.jsp" style="width:81px;height:21px;line-height:21px;">Data Receiver</a></li>
                    
                    
                </ul>
                
                </li>
            <li class="topmenu"><a href="PPT/HEI_GUIDE.pdf" style="width:81px;height:21px;line-height:21px;">Help</a></li>
            <li class="topmenu"><a href="logout.jsp" style="width:81px;height:21px;line-height:21px;">Log out</a></li>
            <li>
                   
               
                  <%if(session.getAttribute("username")!=null){
      out.println("<a style=\"background-color:white; width:40%;margin-right:10px;\"><font color=\"orange\">Hi "+session.getAttribute("username")+"</font></a>");                  
    
    %>    
                
                  
                 <% } %> 
                     
            </li>
        
</ul>
     </body>
     
</html>
