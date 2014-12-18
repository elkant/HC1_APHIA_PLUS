<%-- 
    Document   : add_facilitator
    Created on : Sep 6, 2013, 10:50:03 AM
    Author     : Geofrey Nyabuto
--%>
<%@page import="hc.dbConn"%>
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
                
     <!-- ANIMATE HELP    -->
   <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
	<script src="js/jquery-1.9.1.js"></script>
	<script src="ui/jquery.ui.core.js"></script>
	<script src="ui/jquery.ui.widget.js"></script>
	<script src="ui/jquery.ui.mouse.js"></script>
	<script src="ui/jquery.ui.draggable.js"></script>
	<script src="ui/jquery.ui.position.js"></script>
	<script src="ui/jquery.ui.resizable.js"></script>
	<script src="ui/jquery.ui.button.js"></script>
	<script src="ui/jquery.ui.dialog.js"></script>
	<script src="ui/jquery.ui.effect.js"></script>
	<script src="ui/jquery.ui.effect-blind.js"></script>
	<script src="ui/jquery.ui.effect-explode.js"></script>
	<link rel="stylesheet" href="ui-essentials/demos.css">
                <script>
	$(function() {
		$( "#dialog" ).dialog({
			autoOpen: false,
			show: {
				effect: "blind",
				duration: 500
			},
			hide: {
				effect: "explode",
				duration: 700
			}
		});

		$( "#opener" ).click(function() {
			$( "#dialog" ).dialog( "open" );
		});
	});
	</script>                   
                
<title>add facilitator</title>
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
<body onload=" filter_partner();">

<div id="container" style="font-size: 17px;">
 <%   if(session.getAttribute("level").equals("2")){ 
            %>
<%@include file="/menu/clerkmenu.jsp"%>
<%} else{%>
<%@include file="/menu/adminmenu.jsp"%>
<%}%>
<br/>

     <h3 style="text-align: center; background-color: orange;">ADDING FACILITATORS</h3>

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
//session.removeAttribute("target_pop_id");
//session.removeAttribute("county_id");
//session.removeAttribute("district_id");
//session.removeAttribute("district_name");
//session.removeAttribute("partner_id");
//session.removeAttribute("partner_name");
//session.removeAttribute("target_pop_name");

System.out.println("COUNTY ID ____SESSION"+session.getAttribute("county_id"));

%>




</div>


<div id="content" style="height:450px;">


<div id="midcontent" style="margin-left:130px ;">
<h4>Specify facilitator details appropriately <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4>
                    <div id="dialog" title="Add Facilitator Help." style=" font-size: 17px;">
<%  String message1="";
 dbConn conn= new dbConn();
 
String message_selector="SELECT * FROM help WHERE help_id='5'";
conn.rs=conn.st.executeQuery(message_selector);
conn.rs.next();
message1=conn.rs.getString(3);
out.println(message1);
%>
</div>


<h4>




</h4>
<p><font color="red">*</font> indicates must fill fields</p>
<form action="add_facilitator_session" method="get">
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
<td><Select id="district" class="textbox6"  required ="true" onchange="filter_partner(this);" name="district" >

<option value="">Choose District</option>  


</select></td><td><input type="hidden" id="hidden_dist" name="hidden_dist"  /></td></tr>



<tr>  
<td class="align_button_right">Implementing Partner<font color="red">*</font></td>
<td><Select id="partner_name" class="textbox6"  required ="true" onchange="filter_target_pop(this);" name="partner_name" >

<option value="">Choose partner</option>  
</select></td><td><input type="hidden" id="hidden_partner" name="hidden_partner" /></td></tr>
<!--



<!--
<tr> <td class="align_button_right">School level<font color="red">*</font></td>
<td><Select id="school_level" class="textbox6" onchange="filter_schools_by_level(this);"   required ="true" name="school_level" >

<option value="">choose School Level</option>  
<option value="primary">Primary</option>
<option value="secondary">Secondary</option>

</select></td>

</tr>
-->



<tr> <td class="align_button_right">Target Population <font color="red">*</font></td>
<td><Select id="target_pop" class="textbox6" onchange="filter_gr(this);"   required ="true" name="target_pop" >

<option value="">target population</option>  


</select></td>

</tr>
<tr><td class="align_button_right">Number of facilitators</td><td><input type="text" class="textbox" onkeypress=" return numbers(event);" name="no_of_facils" required></td></tr>
<tr><td></td><td><input type="submit" value="Next" class="submit"/></td></tr>

</table>
</form>
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
</div>
</div>

<div id="footer">

            </div>
</div>    

</body>
</html>
