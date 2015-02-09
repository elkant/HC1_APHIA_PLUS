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
  <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>	
        <script src="js/js/jquery-ui-1.10.3.custom.js"></script>
        <link rel="stylesheet" href="js/demos.css" />
		
                
                
                
<title>Select Year and Period</title>
<script type="text/javascript">
    
   $(function () {

                $("#startdate").datepicker({changeMonth: true, changeYear: true, yearRange: '2008:2015', dateFormat: 'yy-mm-dd'});
                $("#enddate").datepicker({changeMonth: true, changeYear: true, yearRange: '2008:2015', dateFormat: 'yy-mm-dd'});



            });




function displayduration(){
       
         var stdate=document.getElementById("startdate").value; 
         var edate=document.getElementById("enddate").value;     
        
        var stdate1=stdate.replace(/-/g, ",");
        var edate1=edate.replace(/-/g, ",");
       
        var monthsbetween=miezi
        ( new Date(edate1), new Date(stdate1));
        if(monthsbetween==1){ monthsbetween=monthsbetween+" month"}
        if(monthsbetween>1){ monthsbetween=monthsbetween+" months"}
        
        if(monthsbetween==0){
            
            monthsbetween=days( new Date(stdate1) , new Date(edate1)) +" days";
            
        }
        
        $("#startdate").css("border-color","#E0E0D1");   
        $("#enddate").css("border-color","#E0E0D1");  
        var edatecopy=" _____________ ";
        if(edate1!==""){
        edatecopy=edate1;
    }
        
        if(stdate===""&&edate===""){
       
   document.getElementById('notice').innerHTML="<font color='green'> Select appropriate parameters to generate a report. </font>";   
                
        }
        if(stdate!==""||edate!==""){
        
        document.getElementById('notice').innerHTML="<font color='green'> Raw data between date  </font><font color='orange'>"+stdate+"</font> <font color='green'>  and date </font> <font color='orange'>"+edatecopy+" </font>"; 
        }
        
        //if both date are not blank, then show wahts the difference between the periods in monts 
        if(stdate!==""&&edate!==""){
        document.getElementById('notice').innerHTML="<font color='green'> Raw data between date  </font><font color='orange'>"+stdate+" </font> <font color='green'> and date </font> <font color='orange'> "+edate+".  </font> [ <b> "+monthsbetween+" </b>  ]"; 
            
            
        }
        
        $("#notice").css("border-color","#00FF00");
        $("#notice").css("background-color","#FFFFFF");
        
        
        }




function miezi(firstDate, secondDate) {
        
       
        var fm = firstDate.getMonth();
        var fy = firstDate.getFullYear();
        var sm = secondDate.getMonth();
        var sy = secondDate.getFullYear();
        var months = Math.abs(((fy - sy) * 12) + fm - sm);
        var firstBefore = firstDate > secondDate;
        firstDate.setFullYear(sy);
        firstDate.setMonth(sm);
        firstBefore ? firstDate < secondDate ? months-- : "" : secondDate < firstDate ? months-- : "";
    
        return months;
       
}

 
        
        
        function days (d1, d2) {
        var t2 = d2.getTime();
        var t1 = d1.getTime();
 
        return parseInt((t2-t1)/(24*3600*1000));
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

function loadqtrs(){

        var prevyr=((document.getElementById("year").value)-(1));

        document.getElementById("quarter").innerHTML=" <option  value='1'>Oct- Dec ( "+prevyr+" ) </option> <option value='2'>Jan- March</option><option value='3'>Apr- Jun</option><option value='4'>Jul- Sept</option> ";


}


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


  function toggleduration(){
      if (document.getElementById("usedate").checked == true) {
       
    document.getElementsByClassName('dates1')[0].style.display='block';
    document.getElementsByClassName('dates1')[1].style.display='block';
    document.getElementsByClassName('dates2')[0].style.display='block';
    document.getElementsByClassName('dates2')[1].style.display='block';
    document.getElementById("startdate").required=true;
    document.getElementById("enddate").required=true;
       //show the date range and hide the quarter   
      document.getElementsByClassName('quarters')[0].style.display='none';     
      document.getElementsByClassName('quarters')[1].style.display='none';     
        document.getElementById("quarter").removeAttribute("required"); 
            document.getElementById("datetype").value="startdate_enddate";
       
      }
      else {
     document.getElementsByClassName('dates1')[0].style.display='none';
     document.getElementsByClassName('dates1')[1].style.display='none';
    document.getElementsByClassName('dates2')[0].style.display='none';
    document.getElementsByClassName('dates2')[1].style.display='none';
     document.getElementById("startdate").removeAttribute("required");
     document.getElementById("enddate").removeAttribute("required");
       //show the date range and hide the quarter   
      document.getElementsByClassName('quarters')[0].style.display='block';      
      document.getElementsByClassName('quarters')[1].style.display='block';  
       document.getElementById("quarter").required=true;
       document.getElementById("datetype").value="quarter";   
      }
  
  
  
  
  
  
  }     

</script>

</head>
<body onload="get_years();">

<div id="container" style="height:700px;" >
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
            <h3 style="text-align: center; background-color: orange;">Raw Data</h3>
 
<div id="header" align="center">
<br/>






</div>
  <h2 id="notice" style="background-color: yellowgreen;text-align: center;">  </h2>

<div id="content" style="height:450px;">


<div id="midcontent" style="margin-left:130px ;">
    

                   
    
    
<h4>Specify details appropriately</h4>


<p><font color="red">*</font> indicates must fill fields</p>
<form action="RawData" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
<tr> <td class="align_button_right" style="width:300px;"><b>Choose PEPFAR Year</b> <font color="red">*</font></td>
<td><Select name="year" id="year" class="textbox6" onchange="loadqtrs();targetsperyear();"   required ="true" style="width:200px;height:40px;" >
 <option value=""><b>Choose Year</b></option> </select></td></tr>


<tr><td class="align_button_right"><b>Use date rage (instead of quarter)</b></td>
    <td class="align_button_left"><input type="checkbox" onclick="toggleduration();" name="usedate" id="usedate"/></td></tr>
 
<tr ><td class="dates1" style="text-align: right;;"><b>Start Date</b> :</td><td ><input type="text" class="dates1" required="true" onchange="displayduration();" readonly name="startdate" id="startdate" /></td>
    </tr><tr > <td class="dates2" style="text-align: right;"><b>End Date</b>:</td><td  ><input type="text" class="dates2" required="true" onchange="displayduration();"  readonly  name="enddate" id="enddate" /></td></tr>

    
    
    <tr > <td class="quarters" style="text-align: right;"><b>Choose Quarter</b> <font color="red">*</font></td>
<td  style="text-align: left;"><Select name="quarter" id="quarter" required title="" class="quarters" style="width:200px;height:80px;" multiple >
 <option  value="1">Oct- Dec</option> 
 <option value="2">Jan- March</option> 
 <option value="3">Apr- Jun</option> 
 <option value="4">Jul- Sept</option> 
    </select></td></tr>


<tr> <td class="align_button_right" style="width:300px;"><b>Choose Target Population</b> <font color="red">*</font></td>
<td><Select name="targetpop" id="targetpop" title=" To get Data for all target populations,leave all options unselected." class="textbox6" style="width:200px;height:150px;" multiple >
 <option value="">all Target Populations</option> </select></td></tr>

<tr><td></td><td><input type="submit" value="Generate" class="submit" />
    <input type="hidden" name="datetype" id="datetype" /> </td></tr>

</table>
</form>
</div>
</div>
<script>
toggleduration();
</script>
<div id="footer">
<%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center"></p>
            </div>
</div>    

</body>
</html>
