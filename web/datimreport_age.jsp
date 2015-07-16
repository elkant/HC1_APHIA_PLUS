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

		
        <style>
            .targetpop{
                
                display: none;
                
            }
            
            </style>
                
                
<title>Select Year and Period</title>
<script type="text/javascript">
    
  function showhidden(){

      if(document.getElementById("usetargetpop").checked===true)
         {
       
      //document.getElementsByClassName("targetpop").style.visibility='visible';
      $('.targetpop').show();
         }
  else   {

   //  document.getElementsByClassName("targetpop").style.visibility='hidden'; 
     $('.targetpop').hide();
          }
      
  }
     
    
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
      
    url:"uniquetargetpops2s",
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
    url:"uniquetargetpops2s?year="+yr,
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
            <h3 style="text-align: center; background-color: orange;">Datim Format Quarterly Report Per Age Bracket</h3>
 
<div id="header" align="center">
<br/>






</div>


<div id="content" style="height:490px;">


<div id="midcontent" style="margin-left:130px ;">
<h4>Specify details appropriately</h4>

<h4>




</h4>
<p><font color="red">*</font> indicates must fill fields</p>
<form action="datim_age" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
<tr> <td class="align_button_right" style="width:300px;">Choose PEPFAR Year <font color="red">*</font></td>
<td><Select name="year" id="year" class="textbox" onchange="targetsperyear();"   required ="true" style="width:200px;">
 <option value="">Choose Year</option> </select></td></tr>

<tr> <td class="quarters" style="text-align: right;"><b>Choose Quarter</b> <font color="red">*</font></td>
<td  style="text-align: left;"><Select name="quarter" id="quarter" required title="" class="quarters" style="width:200px;height:80px;" multiple >
 <option  value="1">Oct- Dec</option> 
 <option value="2">Jan- March</option> 
 <option value="3">Apr- Jun</option> 
 <option value="4">Jul- Sept</option> 
    </select></td></tr>

<tr>
<td class="align_button_right" style="width:300px;">Specify Target Population <i>(Optional)</i></td>
<td><input type="checkbox" checked onclick="showhidden();"  id="usetargetpop" name="usetargetpop"></td></tr>
<tr class="targetpop"> <td class="align_button_right" style="width:300px;">Choose Target Population <font color="red">*</font></td>
<td><Select name="targetpop" id="targetpop" class="textbox6" style="width:200px;height:150px;" multiple >
<option value="FEMALES (15 to 24)">FEMALES (15 to 24)</option>
</select></td></tr>

<tr><td></td><td><input type="submit" value="Generate" class="submit"/></td></tr>

</table>
</form>
</div>
</div>
            <script>
                
                showhidden();
                
            </script>
<div id="footer">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               
            </div>
</div>    

</body>
</html>
