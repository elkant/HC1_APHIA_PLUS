<%-- 
    Document   : newClerk
    Created on : Aug 8, 2013, 9:49:59 AM
    Author     : SIXTYFOURBIT
--%>

<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%@page import="hc.dbConn1"%>
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
        <title>Backup data</title>
      
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
    
 
   
function backupdata(){


if (window.XMLHttpRequest)
{// code for IE7+, Firefox, Chrome, Opera, Safari
xmlhttp=new XMLHttpRequest();
}
else
{// code for IE6, IE5
xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
xmlhttp.onreadystatechange=function()
{
if (xmlhttp.readyState==4 && xmlhttp.status==200)
{
    
    

var n = noty({text: '<%=session.getAttribute("datasend")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});


}
}
xmlhttp.open("POST","BackUp",true);
xmlhttp.send();
}//end of filter districts 
</script>
    </head>
    <body onload="">
       
        
        
        
        <div id="container"  >
       
            <%      if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}
    
      else{%>
       <%@include file="/menu/clerkmenu.jsp"%>
      <%}
   %>
            <br/>
            <h3 style="text-align: center; background-color: orange;">BACK UP DATA</h3>
            <h5 style="text-align: center; ">Creates a backup and sends it to M&E person via email.</h5>

 
            
              <div id="header" align="center" style="" >
                  <br/>
                 
    <%

dbConn1 con=new dbConn1();
if (!session.getAttribute("username").equals("m&e")){
 String ch1 = "SHOW COLUMNS FROM mande_mail LIKE 'county'";
            con.rs = con.st.executeQuery(ch1);
            if (con.rs.next()) {
                
               
            //check whether all the details have been updated
                String getdata="select * from mande_mail where (county='' or county is NULL or partner='' or usermail='') and mailid='1' ";
            con.rs1=con.st1.executeQuery(getdata);
            
            if(con.rs1.next()){
            
                   session.setAttribute("newmessage","<font color=\"orange\">New !! Please Update all the fields in this form before beginning any new data entry</font>");
                       response.sendRedirect("mandemail.jsp");
            }
                       else {
                       
                       session.setAttribute("newmessage",null);
                       
                       }
            
            }
                       else{
                
                session.setAttribute("newmessage","<font color=\"orange\">New !! Please Update all the fields in this form before beginning any new data entry</font>");
                       response.sendRedirect("mandemail.jsp");
                       
                       }}


%>

<!------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------>
<!--------------------------M&E CODE TO ASK FOR AN UPDATE---------------------------------------------------->
<!------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------>




<%

if (session.getAttribute("username").equals("m&e")){

 String ch2 = "SHOW COLUMNS FROM mande_mail LIKE 'county'";
            con.rs = con.st.executeQuery(ch2);
            if (con.rs.next()) {
                
               
            //check whether all the details have been updated
                String getdata="select * from mande_mail where (county='' or county is NULL or partner='' or usermail='') and mailid='2' ";
            con.rs1=con.st1.executeQuery(getdata);
            
            if(con.rs1.next()){
            
                   session.setAttribute("newmessage","<font color=\"orange\">New !! Please Update all the fields in this form before beginning any new activities</font>");
                       response.sendRedirect("update_sdm_email.jsp");
            }
                       else {
                       
                       session.setAttribute("newmessage",null);
                       
                       }
            
            }
                       else{
                
                session.setAttribute("newmessage","<font color=\"orange\">New !! Please Update all the fields in this form before beginning any new activities</font>");
                       response.sendRedirect("update_sdm_email.jsp");
                       
                       }}


%>


           
                    
                     <br/>
                    
                    
                    
              </div>
            
            
            <div id="content" style="width:1000px; margin-left: 150px; ">
                
              
                <div id="midcontent" style="margin-left:130px ;">
                    
                    
                   
                        
                   
                   
                         <%if (session.getAttribute("datasend") != null) {
                         
    
    
    %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("datasend")%>',
                        layout: 'center',
                          type: 'Success',
 
                         timeout: 1800});
                    
                                    </script> 
                <%
                session.removeAttribute("datasend");
                            }

                        %>
                        <!--creating random user id-->
                         
                        
                        
                        
                        
                   
                   <br/><br/>
                    
                    <form action="BackUp" method="post" style="height:90px;">
                        <br/>
                        <input type="submit" onclick="" value="Send Backup" style="height:50px;" >
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
