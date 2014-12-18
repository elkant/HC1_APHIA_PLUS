<%-- 
    Document   : newClerk
    Created on : Aug 8, 2013, 9:49:59 AM
    Author     : SIXTYFOURBIT
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

    if (session.getAttribute("level") != null) {
    } else {
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
        <title>Edit your details</title>

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
           
         
           
           
            function checkPasswords() {
                var password = document.getElementById('password');
                var conf_password = document.getElementById('conf_password');

                if (password.value != conf_password.value) {
                    conf_password.setCustomValidity('Passwords do not match');
                } else {
                    conf_password.setCustomValidity('');
                }}
$(function() {
$( "#datepicker" ).datepicker();
});

            
        
        </script>
    </head>
    <body >




        <div id="container"  >

            <%      if (session.getAttribute("level").equals("0")) {%>
            <%@include file="/menu/adminmenu.jsp" %>
            <%} else {%>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%}
            %>
            <br/>
            <h3 style="text-align: center; background-color: orange;">EDIT USER DETAILS</h3>

           
            <div id="header" align="center" style="" >
                <br/>




                <br/>



            <%
     String fname="",mname="",lname="",phone="",usern="";
//     String userid=session.getAttribute("userid").toString();
       String userid="";
     
     if(session.getAttribute("userid")!=null){
       userid=session.getAttribute("userid").toString();
                                             }
     dbConn conn = new dbConn();
//out.println("useri id  "+userid);
     String select_user_details="SELECT * FROM clerks join users on clerks.clerk_id=users.userid WHERE clerks.clerk_id='"+userid+"'";
     conn.rs=conn.st.executeQuery(select_user_details);
    if(conn.rs.next()==true){
        fname=conn.rs.getString("first_name"); 
//        mname=conn.rs.getString("mname");
        lname=conn.rs.getString("sur_name");        
         usern=conn.rs.getString("username");
         phone=conn.rs.getString("phone");
//         out.println(fname);
     }
             
conn.st.close();
%>

</div> 
       

            <div id="content" style="width:1200px;height:560px; margin-left: 10px;z-index: -99px; ">


<div style="  margin-left:  300px; width: 600px; background: #ffffff; padding-top:70px;">
<!--    <h3><p align="center">Enter All your details Approriately.</p></h3> -->
    <!--<h5><p align="center">The Fields marked with <font color="red">*</font> are editable fields.</p></h5>-->
    <br><br>
    <form action="save_ur_details" method="POST">
        <input type="hidden" name="userid" value="<%=userid%>">
<table style="margin-left: 30px; font-size: 18px; width: 400px;">
    <tr><td>First Name</td><td><input type="text" name="fname" id="fname" value="<%=fname%>" required></td></tr>
<tr><td> </td><td></td></tr>
<!--<tr><td>Middle Name</td><td><input type="text" name="mname" id="mname" value="<%=mname%>" ></td></tr>
<tr><td> </td><td></td></tr>-->
<tr><td>Last Name</td><td><input type="text" name="lname" id="lname" value="<%=lname%>" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Phone No.</td><td><input type="text" name="phone" id="phone" value="<%=phone%>"></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>Username</td><td><input type="text" <%if (session.getAttribute("username").equals("m&e")){%> disabled <%}%> name="username" id="username" value="<%=usern%>" required></td></tr>
<tr><td> </td><td></td></tr>
<tr><td>New Password</td><td><input type="password" name="pass" id="password" value="" oninput="checkPasswords()" required></td></tr>
<tr><td></td><td></td></tr>
<tr><td>Confirm Password</td><td><input type="password" name="pass2" id="conf_password" oninput="checkPasswords()" value="" required></td></tr>
<tr><td></td><td></td></tr>
<tr><td></td><td></td></tr>
<tr><td colspan="2"><input type="submit" value="Save" style="margin-left: 130px;height:35px;width:130px;background-color: orange;"></td></tr>
</table>
       
    </form>
                </div>
            



            <div id="footer">
                <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->

                <%
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);

                %>
                <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    

    </body>
</html>
