<%-- 
    Document   : index
    Created on : Aug 5, 2013, 9:03:18 PM
    Author     : SIXTYFOURBIT
--%>

<%@page import="hc.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Health Communication System</title>
         <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
         <link rel="shortcut icon" href="images/hc_logo.png"/>
          <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>


<!--tooltip-->
  <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
	
	<script src="js/js/jquery-ui-1.10.3.custom.js"></script>
<link rel="stylesheet" href="js/demos.css" />

<script type="text/javascript">
      $(function() {

$( document ).tooltip();
$( "#accordion" ).accordion();

}); 









</script>
    </head>
    
    <!-- Body page -->
    
       
       
    <body>
        <div id="container">

            <div id="header">
                
            </div>

            <div id="leftBar" >
                
            </div>

            <div id="content">
                 <!--- NOW CHECK THE UPDATES  --->
                 <%
                            if (1==1)  { %>
                                <script type="text/javascript"> 
                    
                    
                    $.ajax({
                        url:'checkbackup',                         
                    type:'post',  
                    dataType: 'html',  
                    success: function(data) {
                     
                             var n = noty({text:data,
                        layout: 'top',
                        type: 'Success',
                        timeout: 7800,
        animation: {
        open: {height: 'toggle'}, // jQuery animate function property object
        close: {height: 'toggle'}, // jQuery animate function property object
        easing: 'swing', // easing
        speed: 500 // opening & closing animation speed
    }            
        }); 
                    }
                        
                         });
                    
              
              
       //======================Create autobackup==========================



  
                    $.ajax({
                        url:'AutoBackup',                         
                        type:'post',  
                        dataType: 'html',  
                    success: function(data) {
                     
//                             var n = noty({text:data,
//                        layout: 'top',
//                        type: 'Success',
//                        timeout: 1800,
//        animation: {
//        open: {height: 'toggle'}, // jQuery animate function property object
//        close: {height: 'toggle'}, // jQuery animate function property object
//        easing: 'swing', // easing
//        speed: 500 // opening & closing animation speed
//    }            
//        }); 
                    }
                        
                         });
              
                    
                </script> <%
                
                session.removeAttribute("backupsms");
                            }

                        %> 

                       
                        
                        
                        
                        
                        <%
                            if (session.getAttribute("guest_success") != null)  { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("guest_success")%>',
                        layout: 'center',
                        type: 'Success', 
                         timeout: 1800});
                    
                </script> <%
                
                session.removeAttribute("guest_success");
                            }

                        %> 
                        
                        
                        
                <div id="midcontent">
                    
                    <h4 align="center"><img src="images/hclogo.png" alt="Health Communication System" align="centre"/></h4>
                    
                    <br/><br/>
                    <div id="loginform">
                    <form action="login"  method="post" style="margin-left: 135px; height:250px; width:360px;">
                       <p align="center">Login</p>
                        <table style="margin-left:90px; width:150px;" cellpadding="8px" cellspacing="6px">
                            <tr>
                                <td></td>
                                <td style="text-align: right">
                                    
                                    <!--  username -->
                                    <input id="username" type=text required name=uname placeholder="Username" autofocus class="textbox"/></td>
                                
                            </tr>
                            <tr>
                                <td></td>
                                
                                <!--password-->
                                <td><input id="password"  type=password required name=pass placeholder="Password" class="textbox"></td>
                            </tr>
                            <tr>
                                <td style="text-align: right;"> </td>
                                <td style="text-align: center;"><input type="submit" style="height:36px;width: 120px;background-color: orange;" value="Log In"/>
                                </td></tr>
                                 <tr>
                                   <td style="text-align: left" colspan="2"  class="linkstyle">
<!--                                    <a href="add_guest.jsp" style="">Register as a Guest</a>-->
                                </td> 
                            </tr>
                        </table>
                       <h4>
                        <%
                            if (session.getAttribute("error_login") != null)  { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("error_login")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 1800});
                    
                </script> <%
                
                session.removeAttribute("error_login");
                            }

                        %>
                        </h4>
                    </form>
                        </div>
                </div>
            </div>

            <div id="rightBar">
               
            </div>

                                 <%
            
if(session.getAttribute("level")!=null){
    
    response.sendRedirect("FormWizard.jsp");
    

}
System.out.println(session.getAttribute("level"));
%>
                        
            <div id="footer">
              <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->
               <h3 align="center"> <img src="images/aphia_logo.png" alt="logo" height="86px" width="270px"/></h3>
               <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);  
dbConn conn=new dbConn();

String versionupdate="ALTER TABLE `version` ADD COLUMN `date` VARCHAR(45)  NULL DEFAULT  '2015_08_11'  AFTER `version_name` , ADD COLUMN `updateslink` VARCHAR(45)  NULL DEFAULT  'HC1_VERSION_1.8'  AFTER `date` ";
 String ch1 = "SHOW COLUMNS FROM version LIKE 'date'";
            conn.rs = conn.st.executeQuery(ch1);
            if (!conn.rs.next()) {
                
                conn.st1.executeUpdate(versionupdate);
                
                  }
         else { conn.st1.executeUpdate("update version set date='2015_08_11' ,version_name='HC1_VERSION_1.8' ,updateslink='http://1drv.ms/1HIqIzZ' where version_id='1' "); }


conn.rs=conn.st.executeQuery("select version_name , date from version");          
conn.rs.next();
%>
                 <div id="versionChecker" style="font-weight: bolder; text-align:center;">
                         </div><br>
               <p align="center"> &copy <a href="" title="<%=conn.rs.getString(1)%> created on <%=conn.rs.getString(2)%>"><%=conn.rs.getString(1)%> compiled on <%=conn.rs.getString(2)%></a> Aphia Plus | USAID <%=year%></p>
            
   
            
            </div>
        </div>



<%

String locktableexists="SHOW TABLES LIKE 'lock_data'";


conn.rs=conn.st.executeQuery(locktableexists);
if(!conn.rs.next()){

//now create the table
    
  conn.st1.executeUpdate("CREATE TABLE `lock_data` (  `id` int(11) NOT NULL AUTO_INCREMENT,  `lock_date` varchar(45) DEFAULT NULL,  `min_date` varchar(45) DEFAULT NULL COMMENT 'This date is entered in the following format, month/day/ year e.g 08/28/2015 for August 28 2015.',  `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8"); 
 

}
else {


%>
<script>
    
                   $.ajax({
                        url:'setSystemDate',                         
                        type:'post',  
                        dataType: 'html',  
                        success: function(data) {}
                        
                         });
                         
                         
                         //check system update
                         
                         
                                           if(!('contains' in String.prototype)) {
       String.prototype.contains = function(str, startIndex) {
                return -1 !== String.prototype.indexOf.call(this, str, startIndex);
       };
 }  
                         
                          function checkVersion(){
//    CHECK Version------------------- 
$("#versionChecker").html("<p>Checking for newer Version...</p>");
var versionText="",daysRemaining,warningText="",sentOn="",version_name="";
 $.ajax({
                    url:"version",
                    type:'post',
                    dataType:'html',
                    success:function (data){
                        if(data.trim()==="no_internet"){
                          $("#versionChecker").html  ("<p style='color: blue; font-size:10px;'>Unable to check if there is a newer version of DIC system due to limited or no internet connection.</p>");
      setInterval(function(){ checkVersion(); }, 60000);          
        }
                        else{
                            
        
                      if(data.contains("outdated version")){
                          $("#loginform").css("display","none");  
                      
                      }
$("#versionChecker").html(data);
                        }
  }  
  });   
      }
 
                         
   checkVersion();                
               
</script>    

<%
}
%>
         <%
conn.rs.close();
conn.st.close();

          
%>
    </body>
    
    
</html>
