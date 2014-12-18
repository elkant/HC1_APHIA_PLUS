<%-- 
    Document   : partner_pie_chart
    Created on : Sep 21, 2013, 10:10:40 PM
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

        if (session.getAttribute("level")==null || session.getAttribute("level").equals("")){
        response.sendRedirect("index.jsp");
    }
    

    if (!session.getAttribute("level").equals("0") && !session.getAttribute("level").equals("5")) {
        response.sendRedirect("index.jsp");
    } 
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


      function filter_target_pop(prt){
         
               
       // document.getElementById("hidden_dist").value=dist.value;     
        
            var part=prt.value;
  var partner_name=document.getElementById("partner_name").value;
     document.getElementById("hidden_partner").value=part;
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
    document.getElementById("target_pop").innerHTML=xmlhttp.responseText;
    
    }
  }
xmlhttp.open("POST","population_chooser?partner="+partner_name,true);
xmlhttp.send();
//filter_nhfs(dist);
           }//end of filter population

//_______________________________________________________________FITER PARTNER_____________________________




              function filter_partner(){

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
xmlhttp.open("POST","load_all_partners",true);
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

</head>
<body onload="get_years(); get_period();filter_partner();">

    <div id="container" style="height: 600px;">
         <%     if(session.getAttribute("level").equals("5")){  %>
  <%@include file="/menu/guest_menu.jsp" %>
  <%} if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}%>
<div id="header" align="center">
<br/>

<%!
public int generateRandomNumber(int start, int end ){
Random random = new Random();
long fraction = (long) ((end - start + 1 ) * random.nextDouble());
return ((int)(fraction + start));
}
%>  
<%
session.removeAttribute("target_pop_id");
session.removeAttribute("county_id");
session.removeAttribute("district_id");
session.removeAttribute("district_name");
session.removeAttribute("partner_id");
session.removeAttribute("partner_name");
session.removeAttribute("target_pop_name");
%>




</div>


<div id="content" style="height:450px;">
    <%session.removeAttribute("partner_id"); %>

<div id="midcontent" style="margin-left:230px ;">
<h4>Specify Partner details appropriately to generate a pie chart report</h4>
<p><font color="red">*</font> indicates must fill fields</p>
<form action="partner_completion_rate" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">

 <input type="hidden" name="src" value="partner_pie_chart2.jsp">




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
<td class="align_button_right">Implementing Partner<font color="red">*</font></td>
<td><Select id="partner_name" class="textbox6"  required ="true" name="partner" >

<option value="">Choose partner</option>  
</select></td><td><input type="hidden" id="hidden_partner" name="hidden_partner" /></td></tr>
<!--<tr>  
<td class="align_button_right">District<font color="red">*</font></td>
<td><Select id="district" class="textbox6"  required ="true" onchange="filter_partner(this);" name="district" >

<option value="">Choose District</option>  


</select></td><td><input type="hidden" id="hidden_dist" name="hidden_dist"  /></td></tr>-->
<tr>
<td class="align_button_right">County<font color="red">*</font></td>
<td><Select id="pdt_cat" class="textbox6" onchange="filter_districts(this);"  required ="true" name="county" style="height: 100px;" multiple>
<!--
<option value="">Choose County</option>  -->
<option value="4">Baringo</option>
<option value="5">Kajiado</option>      
<option value="2">Laikipia</option>
<option value="1">Nakuru</option>
<option value="3">Narok</option>

</select></td></tr>
<tr> <td class="align_button_right">Choose Year <font color="red">*</font></td>
<td><Select name="year" id="year" class="textbox6"   required ="true">
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

