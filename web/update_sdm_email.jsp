<%-- 
    Document   : newClerk
    Created on : Aug 8, 2013, 9:49:59 AM
    Author     : SIXTYFOURBIT
--%>

<%@page import="hc.dbConn1"%>
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
        <link rel="stylesheet" type="text/css" href="css/css2014.css"/>
        <link rel="shortcut icon" href="images/hc_logo.png"/>
        <!--        <link rel="stylesheet"  type="text/css" href="menu/adminmenu_files/css3menu1/style.css" />-->
        <!--menu-->
        <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
        <link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
        <script src="prefix-free.js"></script>
        <title>MandE_email</title>

       
        
        
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
        <link href="js/css/flick/jquery-ui-1.10.4.custom.css" rel="stylesheet"/>	
   
        <script type="text/javascript" src="js/jquery-ui.js"></script>   


        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->

        <script type="text/javascript" src="js/noty/themes/default.js"></script>

        <!--clerk menu-->
         

        <link rel="stylesheet" type="text/css" href="menu/css/clerkmenucss.css"/>
        <script type="text/javascript">
      $(function() {

$( document ).tooltip();


}); 
</script>
    </head>
    <body onload="">




        <div id="container"  >

            <%      if (session.getAttribute("level").equals("0")) {%>
            <%@include file="/menu/adminmenu.jsp" %>
            <%} else {%>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%}
            %>
            <br/>
           <h3 style="text-align: center; background-color: orange;"> <img src="images/blguide.png" title="This page helps users to change information about data receeiver, their partner, usermail and county in which they are working for." /> Senior Data Manager Email</h3>
            <h5 style="text-align: center; "></h5>



            <div id="header" align="center" style="" >
                <br/>




                <br/>



            </div>


            <div id="content" style="width:1000px; margin-left: 30px; height: 500px; ">


                <div id="midcontent" style="margin-left:130px ;">






                    <%if (session.getAttribute("mailchanged") != null) {



                    %>
                    <script type="text/javascript"> 
                    
                                       var n = noty({
                                           text: '<%=session.getAttribute("mailchanged")%>',
                                           layout: 'center',
                                           type: 'Success'});
                    
                    </script>
                    <%
                            session.removeAttribute("mailchanged");
                        }

                    %>
                    <!--creating random user id-->


<% 
 if(session.getAttribute("newmessage")!=null) { %> 
         <h2> <%=session.getAttribute("newmessage")%> </h2>
        
        <% } else { %>

<h3> Edit any field in the form below</h3>

<%}%>



                    <br/><br/>

                    <form action="save_sdm_mail" method="post" style="height:230px;width:930px;">
                        <br/>

                        <%

                            dbConn1 conn = new dbConn1();
                           



String countiesar[]={"Baringo","Kajiado","Laikipia","Nakuru","Narok" };   


String partnersar[]={"GSK","NOPE","ICL","KNOTE","CCS","HI","FAIR","NADINEF","FHOK","M&E OFFICER"};                                                                              
                          //if the table has no all columns  
                         String addcolumns="ALTER TABLE `mande_mail` ADD COLUMN `county` VARCHAR(45) NULL  AFTER `mail` , ADD COLUMN `partner` VARCHAR(45) NULL  AFTER `county` , ADD COLUMN `usermail` VARCHAR(45) NULL  AFTER `partner` ";
                           
                         String ch1 = "SHOW COLUMNS FROM mande_mail LIKE 'county'";
            conn.rs = conn.st.executeQuery(ch1);
            if (conn.rs.next()) {
//do nothing 
            }else{
                         conn.st.executeUpdate(addcolumns);
                                                 }
                            
                            conn.rs = conn.st.executeQuery("select * from mande_mail where mailid='2'");

                            String mandemail = "enter mail";
                            String county="<option value=\"\">select county</option>";
                            String partner="<option value=\"\">select partner</option>";
                            String usermail="";
                            if (conn.rs.next()) {

                                mandemail = conn.rs.getString(2);
                                String countyst = conn.rs.getString(3);
                                String partnerst = conn.rs.getString(4);
                                usermail = conn.rs.getString(5);
                              
if(conn.rs.getString(5)==null){usermail="";}
if(conn.rs.getString(3)==null){countyst="";}
if(conn.rs.getString(4)==null){partnerst="";}
                                
                                for(int a=0;a<countiesar.length;a++){
                                
                                if(countyst.equalsIgnoreCase(countiesar[a])){
                                
                                    county+="<option selected value=\""+countiesar[a]+"\">"+countiesar[a]+"</option>";
                                
                                          }
                                  county+="<option  value=\""+countiesar[a]+"\">"+countiesar[a]+"</option>";
                                
                                
                                }
                                
                             

                //============================================PARTNER FOR LOOP===========================
                    for(int a=0;a<partnersar.length;a++){
                                
                                if(partnerst.equalsIgnoreCase(partnersar[a])){
                                
                                    partner+="<option selected value=\""+partnersar[a]+"\">"+partnersar[a]+"</option>";
                                
                                          }
                                  partner+="<option  value=\""+partnersar[a]+"\">"+partnersar[a]+"</option>";
                                
                                
                                }                                                                               
                                
                                
                            } else {
                                mandemail = "no email";

                            }


                            conn.st.close();

                        %>
                        <table id="viewpdt" style="width: 850px" > 
                            <tr><th><font color="red">*</font>Data Receiver Mail</th><th><font color="red">*</font>Your County</th><th>Partner working for</th><th><font color="red">*</font>Your email address</th></tr>
                            <tr>          
     <td><input type="text" title="to add more than one data receiver, use a comma between email address e.g. user1@gmail.com,user2@gmail.com" name="mandemail" required  value="<%=mandemail%>" class="textbox1" style="height:40px;" /></td>
     
     <td><select required name="county" id="county"> <%=county%> </select></td>
     <td><select title="which partner are you working for?" required name="partner" id="partner"><%=partner%>  </select></td>
     <td><input type="text" title="This email will be used to send you duplicates analysis report for correction" name="usermail" required  value="<%=usermail%>" class="textbox1" style="height:40px;" /></td>                 
                            </tr>
                                <tr>
                                <td colspan="4">
                                    <input type="submit" value="Update" style="height:40px;"/>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
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
