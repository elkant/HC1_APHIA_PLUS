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

    if (!session.getAttribute("level").equals("0")) {
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
      <script type="text/javascript">
            
            function dates_loader(){
         
         
        var xmlhttp;    
    
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
    document.getElementById("dates").innerHTML=xmlhttp.responseText;
   
  }
  }
xmlhttp.open("POST","getDates",true);
xmlhttp.send();

 
           }
           //
           function start_end_date(id){
               
               var row=id.value;
               if(row=="0"){
                   document.getElementById("start_date").type="text";
                     document.getElementById("end_date").type="text";
               }
               
               else{
                    document.getElementById("start_date").type="hidden";
                     document.getElementById("end_date").type="hidden";
                   
               }
               
               
           }
           
           
           
           
           
           
  //the calender functions         
           
           $(function() {
$( "#start_date" ).datepicker();
$( "#end_date" ).datepicker();
});
           
           
        
        </script> 
     <script type="text/javascript">
  function date_validator(){
     var start_date,end_date,dateObject,day,month,year,current_date;
    start_date=document.getElementById("start_date").value;
    end_date=document.getElementById("end_date").value;
    
    //created the date object
    dateObject =new Date();
    day=dateObject.getDate();
    month=dateObject.getMonth()+1;
    year=dateObject.getFullYear();
    //fully year separated by backward slash
   current_date=month+"/"+day+"/"+year;     
    //we compare the date
    if(new Date(start_date) > new Date(current_date)){
     alert("Start date cannot be greater than current date");   
    return false;    
    }
    
    //we compare the current date and the preselected date
        if(new Date(start_date) > new Date(end_date)){
     alert("Start date cannot be greater than end date.");   
       return false; 
    }
    
            if(new Date(end_date) > new Date(current_date)){
     alert("End date cannot be greater than current date.");   
       return false; 
    }
//            if(start_date=="" || end_date==""){
//     alert("Choose start and end dates.");   
//       return false; 
//    }
  }  
    
    
</script>   
    </head>
    <body onload="dates_loader();">
       
     <div id="container" >

              <div id="header" align="center">
                  <br/>
                    <%@include file="/menu/adminmenu.jsp" %>
      
              </div>
            
            
            <div id="content" style="height:500px;">
                
              
                <div id="midcontent" style="margin-left:110px ; width: 800px">
                    <h4>Select Date and Group To Generate Report.</h4>
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
                       
                    <p></p>
                    <form action="group_report" method="" onsubmit="return date_validator();">
                        <br/>
                        <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                            <tr><td><td> <Select id="dates" required="true"  onchange="start_end_date(this);" name="dates" class="textbox6">
   <option value="">choose date</option>  
                                    </select></td><td><input class="textbox" id="start_date" placeholder="start date" type="hidden" name="start_date" readonly="true"/>
                                    </td><td></td><td><input type="hidden" placeholder="end date" id="end_date" class="textbox" name="end_date" readonly="true"/>
                                    
                                    </td>
                           
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
<input type="submit" value="generate Reports" class="textbox" onclick="return actioner()"/>      
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
