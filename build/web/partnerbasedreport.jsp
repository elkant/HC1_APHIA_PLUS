<%-- 
    Document   : groups_overall
    Created on : Mar 2, 2014, 11:55:55 AM
    Author     : Geofrey Nyabuto
--%>
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
    

   
  //if (!session.getAttribute("level").equals("0")&&!session.getAttribute("level").equals("2") && !session.getAttribute("level").equals("5")) {
      //  response.sendRedirect("index.jsp");
    //} 
%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
<link rel="shortcut icon" href="images/hc_logo.png"/>
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
 <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

		
                
                
                
<title>Select Year and Period</title>
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

  $.ajax({
      
    url:"uniquetargetpop",
    type:'post',
    dataType:'html',
    success:function(data){
//        alert(data);
        document.getElementById("targetpop").innerHTML=data;
        
    }
});

}
//set the partner


//load target pop


function targetsperyear(){
var yr=document.getElementById("year").value;

document.getElementById("targetpop").innerHTML='<option value=\"\">loading targets..</option>';
 $.ajax({
    url:"targetpopnames_yearly?year="+yr,
    type:'post',
    dataType:'html',
    success:function(data){
//        alert(data);
        document.getElementById("targetpop").innerHTML=data;
        
    }
});



}



</script>

</head>
<body onload="get_years();">

<div id="container" >
  <%if(session.getAttribute("level")!=null){ 
      if(session.getAttribute("level")!=null){    if(session.getAttribute("level").equals("5")){  %>
  <%@include file="/menu/guest_menu.jsp" %>
  <%} if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}if(session.getAttribute("level").equals("2")){ %>
   <%@include file="/menu/clerkmenu.jsp" %>
 <%}
  } }
    
     else{ %>
       
         <%@include file="/menu/guest_menu.jsp" %>
       
      <%    }
    
 %>
 
     <br/>
            <h3 style="text-align: center; background-color: orange;">Kepms Format Monthly Report Per Partner</h3>
 
<div id="header" align="center">
<br/>






</div>


<div id="content" style="height:450px;">


<div id="midcontent" style="margin-left:130px ;">
<h4>Specify details appropriately</h4>

<h4>




</h4>
<p><font color="red">*</font> indicates must fill fields</p>
<form action="partnerbasedkepms" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
<tr> <td class="align_button_right" style="width:300px;">Choose PEPFAR Year <font color="red">*</font></td>
<td><Select name="year" id="year" class="textbox" onchange="targetsperyear();"   required ="true" style="width:200px;">
 <option value="">Choose Year</option> </select></td></tr>

<tr> <td class="align_button_right" style="width:300px;">Choose Target Population <font color="red">*</font></td>
<td><Select name="targetpop" id="targetpop" class="textbox6" style="width:200px;height:150px;" multiple >
 <option value="">all Target Populations</option> </select></td></tr>

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
               <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
            </div>
</div>    

</body>
</html>
