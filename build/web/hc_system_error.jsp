<%-- 
    Document   : index
    Created on : Aug 5, 2013, 9:03:18 PM
    Author     : SIXTYFOURBIT
--%>


<%@page import="hc.dbConn"%>
<%@page import="java.net.InetAddress"%>
<%@page import="java.util.Date"%>

<%@ page import="java.util.*, javax.mail.*, javax.mail.internet.*" %> 
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" import="java.io.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SYSTEM ERROR</title>
        <link rel="stylesheet" type="text/css" href="css/divCss.css"/>
        <link rel="shortcut icon" href="images/hclogo.png"/>
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->



          <!--menu-->
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
<script src="prefix-free.js"></script>
     



        <script type="text/javascript" src="js/noty/themes/default.js"></script>


        <!--tooltip-->
        <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>

        <script src="js/js/jquery-ui-1.10.3.custom.js"></script>
        <link rel="stylesheet" href="js/demos.css" />



    <script src="prefix-free.js"></script>


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
          <%      if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}
    
      else{%>
       <%@include file="/menu/clerkmenu.jsp"%>
      <%}
   %>
        
           <div id="header" style="text-align: center;">
        

           </div>
        
            <div class="ui-widget">
                
                <br/>
                <div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
                    <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
                        <strong></strong> HC1 System  ERROR!! <br/> 
                        An error has occured.
                        <br/>
                        <%String subj = "System Error";

          String myerror ="";
                            StringWriter stringWriter = new StringWriter();
                            PrintWriter printWriter = new PrintWriter(stringWriter);
                            exception.printStackTrace(printWriter);
                            //____________________COMPUTER NAME____________________________________
                            String computername = InetAddress.getLocalHost().getHostName();
                             myerror = stringWriter.toString().toLowerCase();
                            

                        %>
                         </p>
                    An error  notice has been send to HC1 System developers for immediate action.<br/>Press Browser back button to continue with other activities if the error persists.>
                    <%




                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH) + 1;
                        int date = cal.get(Calendar.DATE);
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        int min = cal.get(Calendar.MINUTE);
                        int sec = cal.get(Calendar.SECOND);
                        String yr, mnth, dater, hr, mn, sc, action = "";

                        Date dat = new Date();
                        dbConn conn = new dbConn();

                        String inserter = "insert into error_log set error_name='" + myerror + "' , time='" + dat + "', actor='" + session.getAttribute("userid") + "'";
                        conn.st3.executeUpdate(inserter);


                    %>


                    <% String host = "smtp.gmail.com";

                          String user = "elkaunda@gmail.com";
                        String pass = "70450289";
                        String to = "ekmanukaka5@gmail.com";
                        String from = "elkaunda@gmail.com";
                        String subject = computername + "HC SYSTEM ERROR";
                        String messageText = myerror;
                        boolean sessionDebug = false;
                        Properties props = System.getProperties();
                        props.put("mail.host", host);
                        //  props.put("mail.transport.protocol", "smtp");
                        //props.put("mail.smtp.auth", "true"); 
                        //props.put("mail.smtp.port", 465); 
            // Uncomment 5 SMTPS-related lines below and comment out 2 SMTP-related lines above and 1 below if you prefer to use SSL
                        props.put("mail.transport.protocol", "smtps");
                        props.put("mail.smtps.auth", "true");
                        props.put("mail.smtps.port", "465");
                        props.put("mail.smtps.ssl.trust", host);
                        Session mailSession = Session.getDefaultInstance(props, null);
                        mailSession.setDebug(sessionDebug);
                        Message msg = new MimeMessage(mailSession);
                        msg.setFrom(new InternetAddress(from));
                        InternetAddress[] address = {new InternetAddress(to)};
                        msg.setRecipients(Message.RecipientType.TO, address);
                        msg.setSubject(subject);
                        msg.setSentDate(new Date());
                        msg.setText(messageText);
            //Transport transport = mailSession.getTransport("smtp");
                        Transport transport = mailSession.getTransport("smtps");
                        transport.connect(host, user, pass);
                        transport.sendMessage(msg, msg.getAllRecipients());
                        transport.close();

                    %> 

                </div>
                   
        </div>

        

        <div id="content" style="height:500px ;">


            <div id="midcontent">



                <%//=exception.getMessage()%>


                <%
                   
                    printWriter.close();
                    stringWriter.close();
                %>





                <br/><br/>

            </div>
        </div>

      

        <div id="footer">
            <!--  <h2 align="left"> <img src="images/Coat of arms.JPG" alt="logo" height="76px" /></h2>-->
           
            <p align="center"> &copy <a href="#" title="">HC1 System</a> Aphia Plus | USAID <%=year%></p>
        </div>
    </div>
</body>


</html>
