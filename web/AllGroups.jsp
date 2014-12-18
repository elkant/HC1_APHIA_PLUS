<%-- 
    Document   : AllGroups
    Created on : Mar 9, 2014, 8:57:09 PM
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

//        if (session.getAttribute("level")==null || session.getAttribute("level").equals("")){
//        response.sendRedirect("index.jsp");
//    }
//    
//
//    if (!session.getAttribute("level").equals("0") && !session.getAttribute("level").equals("5")) {
//        response.sendRedirect("index.jsp");
//    } 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
    <link href="menu/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="menu/iconic.css" media="screen" rel="stylesheet" type="text/css" />
         <link rel="shortcut icon" href="images/hc_logo.png"/>
     <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
    <script type="text/javascript">
function get_years(){

// window.open("districtchooser?county="+dist.value);     
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
document.getElementById("year").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","years_loader",true);
xmlhttp.send();
}
//set the partner
function get_period(){
// window.open("districtchooser?county="+dist.value);     
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
document.getElementById("period").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","period_loader",true);
xmlhttp.send();
}

</script>  
<title>All Groups Report</title>

    </head>
    <body onload="get_years(); get_period();">
       
     <div id="container" >
 
     <%     if(session.getAttribute("level").equals("5")){  %>
  <%@include file="/menu/guest_menu.jsp" %>
  <% } if(session.getAttribute("level").equals("0")){ %>
  <%@include file="/menu/adminmenu.jsp" %>
 <% }%>
              <div id="header" align="center">
                  <br/>
                
      
              </div>
            
                    <%session.removeAttribute("county_id");
            %>
            <div id="content" style="height:500px;">
                
              
                <div id="midcontent" style="margin-left:310px ; width: 400px">
                    <br><br>
                    <h4>Select Year and period(s) Generate attendance excel Report.</h4>
                    <br>
 <%if (session.getAttribute("county_select") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("county_select")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("county_select");
                            }

                        %>
                       
                    <p></p>
                    <form action="overall_session" method="" onsubmit="return date_validator();">
                        <br><br>
                        <input type="hidden" name="src" value="excel_groups_completion">
                        <table cellpadding="2px" cellspacing="3px" border="0px" style="margin-left:0px ;">
                                                        <tr>
                              
                            </tr>
                            
<tr> <td class="align_button_right">Choose Year <font color="red">*</font></td>
<td><Select name="year" id="year" class="textbox6"   required ="true">
 <option value="">Choose Year</option> 


</select></td>
</tr>
<tr><td>-</td></tr>
<tr> <td class="align_button_right">Choose Period<font color="red">*</font></td>
<td><Select name="period" id="period" class="textbox6"   required ="true" style="height: 100px;" multiple>
<!-- <option value="">Choose Period</option> -->
 <option value="">Jan - March</option> 
 <option value="">April - June</option> 
 <option value="">July - Sept</option> 
 <option value="">Oct - Dec</option> 

</select></td>
</tr><tr><td>-</td></tr>
<tr> <td class="align_button_right" colspan="2"><input type="submit" value="Generate Pie Chart" class="textbox" onclick="return actioner()"/>     </td>
</tr>

                        </table><br><br>
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

