<%-- 
    Document   : adminmenu
    Created on : Apr 15, 2014, 10:30:05 AM
    Author     : SIXTYFOURBIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>css3menu.com</title>
        <!-- Start css3menu.com HEAD section -->
        <link rel="stylesheet" href="clerkmenu_files/css3menu1/style.css" type="text/css" /><style type="text/css">._css3m{display:none}</style>
        <!-- End css3menu.com HEAD section -->


    </head>
    <body style="background-color:#EBEBEB">
        <!-- Start css3menu.com BODY section -->
        <ul id="css3menu1" class="topmenu">
            <li class="topmenu"><a href="cohort_home.jsp" style="width:90px;height:21px;line-height:21px;"><span>Home</span></a>
                </li>
                
                  <li class="topmenu"><a href="adduser.jsp" style="width:101px;height:21px;line-height:21px;"><span>Add User</span></a>
                </li>
                
            <li class="topmenu"><a href="#" style="width:91px;height:21px;line-height:21px;"><span>Form Entry</span></a>
                <ul>

                    <li style="width:121px;height:31px;line-height:21px;" class="sublast"><a href="enterdata.jsp">Enter/Edit data</a></li>
                  
                </ul></li>
           
 <li class="topmenu"><a href="#" style="width:91px;height:21px;line-height:21px;"><span>Entries</span></a>
                <ul>

                    <li style="width:121px;height:31px;line-height:21px;" ><a href="add_edit_target.jsp">Add/Edit Targets</a></li>
                    <li style="width:121px;height:31px;line-height:21px;" ><a href="addfacilities.jsp">Add Facilities</a></li>
                    <li style="width:121px;height:31px;line-height:21px;" class="sublast"><a href="editfacilities.jsp">Edit Facilities</a></li>
                  
                </ul></li>
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
                <li class="topmenu"><a href="../PPT/HEI_GUIDE.pdf" style="width:81px;height:21px;line-height:21px;">Help</a></li>
            <li class="topmenu"><a href="logout.jsp" style="width:81px;height:21px;line-height:21px;">Log out</a></li>
   <li>
                   
               
                  <%if(session.getAttribute("username")!=null){
      out.println("<a style=\"background-color:white;\"><font color=\"orange\">Hi "+session.getAttribute("username")+"</font></a>");                  
    
    %>    
                
                  
                 <% } %> 
                     
                </li>

    </body>
</html>