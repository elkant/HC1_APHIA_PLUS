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
         <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>         
<script type="text/javascript" src="js/js/sisyphus.min.js"></script>
                         
                
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


xmlhttp.open("POST","load_grps?distr=<%=session.getAttribute("district_id")%>&&popul=<%=session.getAttribute("target_id")%>",true);
xmlhttp.send();

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



function loadmonths(qtr){
    
    var quater=qtr.value;
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
document.getElementById("month").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","loadmonths?qtr="+quater,true);
xmlhttp.send();  
    
    
    
    
}




function loadqtrs(){

        var prevyr=((document.getElementById("year").value)-(1));
         var curyear=document.getElementById("year").value;
        document.getElementById("period").innerHTML="<option value=''>select Quarter</option> <option  value='1'>Oct- Dec  (  "+prevyr+" ) </option> <option value='2'>Jan- March (  "+curyear+" )</option><option value='3'>Apr- Jun (  "+curyear+" )</option><option value='4'>Jul- Sept(  "+curyear+" )</option> ";


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
<body onload="  get_years(); get_period();">

<div id="container" >
 <%   if(session.getAttribute("level").equals("2")){ 
            %>
<%@include file="/menu/clerkmenu.jsp"%>
<%} else{%>
<%@include file="/menu/adminmenu.jsp"%>
<%}%>

 <br/>
            <h3 style="text-align: center; background-color: orange;">
                <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     <a href="FormWizard_facils.jsp" style="margin-left: -450px;margin-right: 150px;" class="linkstyle"> << Back</a>
 <%}%>
                
                ADDING PARTICIPANTS
            
            
                <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     &nbsp; &nbsp; Page 4/7 &nbsp;
 <%}%>
            
            
            
            </h3>


<div id="header" align="center">
<br/>


                    <%if (session.getAttribute("success") != null) {%>
                    <script type="text/javascript"> 
                    
                                        var n = noty({text: '<%=session.getAttribute("success")%>',
                                            layout: 'center',
                                            type: 'Success',
 
                                            timeout: 4800});
                    
                    </script> <%
                            session.removeAttribute("success");
                        }

                    %>
                    <%if (session.getAttribute("fail") != null) {%>
                    <script type="text/javascript"> 
                    
                                        var n = noty({text: '<%=session.getAttribute("fail")%>.<br>Click here to close notice.',
                                            layout: 'center',
                                            type: 'Success'});
                    
                    </script> <%
                            session.removeAttribute("fail");
                        }

                    %> 





</div>


<div id="content" style="height:490px;">


<div id="midcontent" style="margin-left:130px ;">
<h5>Specify the Reporting Year,Reporting period and number of participants you wish to add.</h5>

<h4>




</h4>
<p><font color="red">*</font> indicates must fill fields</p>
<form action="session_holder" method="get">
<table cellpadding="2px" cellspacing="3px" border="0px">




           <%
Calendar cal = Calendar.getInstance();
int year1= cal.get(Calendar.YEAR);  %>



<tr> <td class="align_button_right">Choose PEPFAR Year <font color="red">*</font></td>
<td><Select name="year" id="year" class="textbox6"  onchange="loadqtrs();"  required ="true">
 <option value="">Choose Year</option> 
  

</select></td><td rowspan="5" style="background-color:white;color:green;"><h3><b> Note: </b> If you are entering data for Period <b>oct-Dec </b>,<br/> Choose  year as <%=year1+1%> </h3> </td>
</tr>

<tr> <td class="align_button_right">Choose Period<font color="red">*</font></td>
<td><Select name="period" id="period" class="textbox6" onchange="loadmonths(this);"  required ="true">
 <option value="">Choose Period</option> 


</select></td>
</tr>

<tr> <td class="align_button_right">Choose Month<font color="red">*</font></td>
<td><Select name="month" id="month" class="textbox6"   required ="true">
 <option value="">Choose Month</option> 


</select></td>
</tr>

<tr><td class="align_button_right">Number of participants<font color="red">*</font></td><td><input type="text" onkeypress="return numbers(event);" name="number_of_members" required class="textbox" /></td></tr>


<tr><td></td><td><input type="submit" value="Next" style="height: 35px;width: 130px; background-color: orange;"/></td></tr>

</table>
</form>
</div>
</div>

<div id="footer">
<%

int year= cal.get(Calendar.YEAR);              

%>
               <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
               <!--<script type="text/javascript" src="js/js/sisyphus.min.js"></script>-->
<!--            <script> $('form').sisyphus();</script>-->
               
            </div>
</div>    

</body>
</html>
