<%-- 
    Document   : edit_district
    Created on : Sep 5, 2013, 11:32:43 AM
    Author     : Geofrey Nyabuto
--%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

        if (session.getAttribute("level")==null || session.getAttribute("level").equals("")){
        response.sendRedirect("index.jsp");
    }
    

    if (!session.getAttribute("level").equals("0")) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/divCss_2.css"/>
    <link href="menu/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="menu/iconic.css" media="screen" rel="stylesheet" type="text/css" />
         <link rel="shortcut icon" href="images/hc_logo.png"/>
     <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
<title>Edit Districts.</title>
<script type="text/javascript">
    function county(){

var dist=district.value;    
   
var xmlhttp;    
if (dist=="")
{
//filter the counties    



//document.getElementById("county").innerHTML="<option value=\"\">choose district</option>";
//return;
}
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
document.getElementById("county").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","countychooser?county="+county,true);
xmlhttp.send();
}//end of count picker
    
    
    
function filter_districts(){

        var county_id=document.getElementById("county").value;        
var xmlhttp;    
if (county_id=="")
{
//filter the districts    



document.getElementById("district").innerHTML="<option value=\"\">Choose district</option>";
return;
}
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
document.getElementById("district").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","districtchooser?county_id="+county_id,true);
xmlhttp.send();
}//end of filter districts
</script>
</head>
<body onload="return county();">
<%
session.removeAttribute("county_id");
session.removeAttribute("county_name");
session.removeAttribute("district_id");
session.removeAttribute("district_name");

%>
<div id="container" >
 <%   if (session.getAttribute("userid").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>
            <br/>
            <h3 style="text-align: center; background-color: orange;">EDITING DISTRICT</h3>
<div id="header" align="center">
</div>
<div id="content" style="height:530px;">
<div id="midcontent" style="margin-left:130px ;">
    <br/><br/>
     <%if (session.getAttribute("dist_success") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("dist_success")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("dist_success");
                            }

                        %>
<h4>Select County and District to edit details.</h4>
<br><br>
   <form action="edit_dist_session" name="" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
    <br><br>

  County<font color="red">*</font>
  <br><br>
 
  <Select  required ="true" name="county"  id ="county" class="textbox2" style="background: #cc99ff" onchange="return filter_districts();">
<option value="">Choose County</option>  
</select>
<br><br>
District<font color="red">*</font>
<br><br>
 <Select  required ="true" name="district" id="district" class="textbox2" style="background: #cc99ff">
<option value="">Choose District</option>  
</select>
<br><br>
<input type="submit" name="sub" value="Edit District" class="textbox1" style="background: #cc99ff; color: #0000ff" >
</table>
       <br><br>
</form>
</div>
</div>


<div id="footer">
 <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
</div>
</div>    

</body>
</html>