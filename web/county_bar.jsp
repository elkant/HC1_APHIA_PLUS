<%-- 
    Document   : county_bar
    Created on : Mar 13, 2014, 10:59:28 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    
    
 if(session.getAttribute("level")!=null){
  if (!session.getAttribute("level").equals("0")&&!session.getAttribute("level").equals("2") && !session.getAttribute("level").equals("5")) {
        response.sendRedirect("index.jsp");
    } }
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
<link rel="shortcut icon" href="images/hc_logo.png"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
		<link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
		<script src="prefix-free.js"></script>
                
                
                
<title>Generate report</title>
<script type="text/javascript">

//manage drop down list for primary and secondary schools



 $(function() {
$( "#datepicker0" ).datepicker();
$( "#datepicker1" ).datepicker();
$( "#datepicker2" ).datepicker();
$( "#datepicker3" ).datepicker();
$( "#datepicker4" ).datepicker();
$( "#datepicker5" ).datepicker();
$( "#datepicker6" ).datepicker();
$( "#datepicker7" ).datepicker();
$( "#datepicker8" ).datepicker();
$( "#datepicker9" ).datepicker();
$( document ).tooltip();
$( "#accordion" ).accordion();

}); 




// a function that filters the districts in the passed county as per the county dropdown.

function filter_districts(district){

var dist=district.value;    

// window.open("districtchooser?county="+dist.value);     
var xmlhttp;    
if (dist=="")
{
//filter the districts    



document.getElementById("district").innerHTML="<option value=\"\">choose district</option>";
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
xmlhttp.open("POST","districtchooser?county_id="+dist,true);
xmlhttp.send();
}//end of filter districts

//*******************filter target populations*************************

//_______________________________________________________________FITER PARTNER_____________________________




              function filter_partner(){
                  var target=document.getElementById("target").value;
//                  alert("ids are  :  "+target)
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
document.getElementById("partner_name").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_partner_pie?ids="+target,true);
xmlhttp.send();
}
//set the partner
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
function load_targets(){
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
document.getElementById("target").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_target_pie",true);
xmlhttp.send();
}

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
function load_quarters(yr){
        var year=yr.value;

        var prev_year=year-1;
    if(year!=""){
    document.getElementById("period").innerHTML="<select id='period' name='period' style='width: 120px;' required><option value=''>Choose Period</option><option value='1'>Oct-Dec ("+prev_year+")</option><option value='2'>Jan-March ("+year+")</option><option value='3'>April-June ("+year+")</option><option value='4'>July-Sept("+year+")</option></select>";
 }
}
</script>

</head>
<body onload="get_years();load_targets();">

    <div id="container" style="height: 740px;">
  <%   if(session.getAttribute("level")!=null){   if(session.getAttribute("level").equals("5")){  %>
  <%@include file="/menu/guest_menu.jsp" %>
  <%} if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}if(session.getAttribute("level").equals("2")){ %>
   <%@include file="/menu/clerkmenu.jsp" %>
 <%}}
    
    
     else{ %>
       
         <%@include file="/menu/guest_menu.jsp" %>
       
      <%    }
  
 %>
 <br/>
  <h3 style="text-align: center; background-color: orange;">Target Population Completion Rate.</h3>
<div id="header" align="center">

<%!
public int generateRandomNumber(int start, int end ){
Random random = new Random();
long fraction = (long) ((end - start + 1 ) * random.nextDouble());
return ((int)(fraction + start));
}
%>  




</div>


<div id="content" style="height:570px;">
    <%session.removeAttribute("partner_id"); %>

<div id="midcontent" style="margin-left:230px ;">
<h4>Specify Partner details appropriately to generate a pie chart completion report.</h4>
<p><font color="red">*</font> indicates must select fields</p>
<form action="overall_session" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">

 <input type="hidden" name="src" value="county_bar2.jsp">




<%if (session.getAttribute("partner_select") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("partner_select")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("partner_select");
                            }

                        %>
  
  <tr>  
<td class="align_button_right">Choose Target Population<font color="red">*</font></td>
<td><select id="target" class="textbox6"  required ="true" name="target">

<option value="">Choose target population </option>  
</select></td></tr>                      
                        
  <input type="hidden" name="src" value="county_bar2.jsp">                       
<tr>  

<tr> <td class="align_button_right">Choose PEPfar Year <font color="red">*</font></td>
    <td><Select name="year" id="year" class="textbox6"   required ="true" onchange=" return load_quarters(this);">
 <option value="">Choose Year</option> 


</select></td>
</tr>
<tr> <td class="align_button_right">Choose Period<font color="red">*</font></td>
<td><Select name="period" id="period" class="textbox6"   required ="true" style="height: 100px;" multiple>
 <option value="">Choose Period</option> 


</select></td>
</tr>
<tr><td></td><td><input type="submit" value="Generate" class="submit"/></td></tr>

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

