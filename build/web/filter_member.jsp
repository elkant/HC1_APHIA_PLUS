<%-- 
Document   : teacher_reg
Created on : Aug 6, 2013, 12:38:52 PM
Author     : SIXTYFOURBIT
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

    if (session.getAttribute("userid") == null) {
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
                
                
                
<title>add members</title>
<script type="text/javascript">

//manage drop down list for primary and secondary schools
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
     

//*****************************group chooser***************
         function filter_gr(){
var dist=document.getElementById("district").value;
var dis=dist.split(",", 2);
var id_dist=dis[0];
var popu=document.getElementById("target_pop").value;

if(id_dist!="" && popu!=""){
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
document.getElementById("group_name").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_grps?distr="+id_dist+"&&popul="+popu,true);
xmlhttp.send();
}
         }
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


   <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script> 


</head>
<body onload=" filter_partner(); get_years(); get_period();">

<div id="container" >
 <%   if(session.getAttribute("level").equals("2")){ 
            %>
<%@include file="/menu/clerkmenu.jsp"%>
<%} else{%>
<%@include file="/menu/adminmenu.jsp"%>
<%}%>

 <br/>
            <h3 style="text-align: center; background-color: orange;">ADDING PARTICIPANTS</h3>


<div id="header" align="center">
<br/>

<%!
public int generateRandomNumber(int start, int end ){
Random random = new Random();
long fraction = (long) ((end - start + 1 ) * random.nextDouble());
return ((int)(fraction + start));
}
%>  





</div>


<div id="content" style="height:490px;">


<div id="midcontent" style="margin-left:130px ;">
<h4>Specify member details appropriately</h4>

<h4>




</h4>
<p><font color="red">*</font> indicates must fill fields</p>
<form action="session_holder" method="get">
<table cellpadding="2px" cellspacing="3px" border="0px">





<tr>
<td class="align_button_right" style="width: 300px;">County<font color="red">*</font></td>
<td><Select id="pdt_cat" class="textbox6" onchange="filter_districts(this);"  required ="true" name="county" >

<option value="">Choose County</option>  
<option value="4">Baringo</option>
<option value="5">Kajiado</option>      
<option value="2">Laikipia</option>
<option value="1">Nakuru</option>
<option value="3">Narok</option>

</select></td>


</tr>   



<!--district-->


<tr>  
<td class="align_button_right">District<font color="red">*</font></td>
<td><Select id="district" class="textbox6"  required ="true" onchange="filter_gr();" name="district" >

<option value="">Choose District</option>  
</select></td><td><input type="hidden" id="hidden_dist" name="hidden_dist"  /></td></tr>



<tr>  
<td class="align_button_right">Implementing Partner<font color="red">*</font></td>
<td><Select id="partner_name" class="textbox6"  required ="true" onchange="filter_target_pop(this);" name="partner_name" >

<option value="">Choose partner</option>  
</select></td><td><input type="hidden" id="hidden_partner" name="hidden_partner" /></td></tr>

<tr> <td class="align_button_right">Target Population <font color="red">*</font></td>
<td><Select id="target_pop" class="textbox6" onchange="filter_gr();"   required ="true" name="target_pop" >

<option value="">target population</option>  


</select></td>

</tr>





<tr> <td class="align_button_right"></td>
<td><Select style="display: none;" id="group_name" class="textbox6"    required ="true" name="group_name" >

<option value="">Choose Group</option>  


</select></td>

</tr>

<tr> <td class="align_button_right">Choose Year <font color="red">*</font></td>
<td><Select name="year" id="year" class="textbox6"   required ="true">
 <option value="">Choose Year</option> 


</select></td>
</tr>

<tr> <td class="align_button_right">Choose Period<font color="red">*</font></td>
<td><Select name="period" id="period" class="textbox6"   required ="true">
 <option value="">Choose Period</option> 


</select></td>
</tr>

<tr><td class="align_button_right">Number of participants<font color="red">*</font></td><td><input type="text" onkeypress="return numbers(event);" name="number_of_members" required class="textbox" /></td></tr>


<tr><td></td><td><input type="submit" value="Next" class="submit"/></td></tr>

</table>
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
