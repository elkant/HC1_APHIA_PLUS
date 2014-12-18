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


<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>		
                
                
                
<title>Targets Maintenance module</title>
<script type="text/javascript">
    
  function nullchecker(){
      
      
      var no_of_rws=parseInt(document.getElementById("all_rows").value);
      var rtn=true;
      var i=1;
      
           var found="";
         while(i<=no_of_rws){
        
         var county=document.getElementById("county"+i).value;
         var partner=document.getElementById("partner"+i).value;
         var year=document.getElementById("year"+i).value;
         var target=document.getElementById("target"+i).value;
         var sex=document.getElementById("sex"+i).value;
var totalItems="",issued="";
//totalItems=document.getElementById("rem"+i).value;
//issued=document.getElementById("quantity"+i).value;
//alert("total   :   "+totalItems+"   isued    :     "+issued)
    if(county!=""||partner!="" || target!="" || sex!="" || year!="")
        
        
     if((county!=""|| partner!="" || sex!="" || target!="") && ( year=="") ) {
        alert("Select year  at line  : "+i);
        $('#year'+i).slideToggle("fast",function() {});
        $('#year'+i).slideToggle("fast",function() {});   
        $('#year'+i).focus();
        found="yes";
          rtn=false; 
           break;
        }
    
        
         if((partner!=""|| year!="" || sex!="" || target!="") && ( county=="") ) {
        alert("Select County  at line  : "+i);
        $('#county'+i).slideToggle("fast",function() {});
        $('#county'+i).slideToggle("fast",function() {});   
        $('#county'+i).focus();
        
        found="yes";
          rtn=false; 
           break;
        }
        
         if((county!=""||year!="" || sex!="" ||  target!="") && ( partner=="") ) {
        alert("Select Partner  at line  : "+i);
        $('#partner'+i).slideToggle("fast",function() {});
        $('#partner'+i).slideToggle("fast",function() {});   
        $('#partner'+i).focus();
        found="yes";
          rtn=false; 
           break;
        }
        
       if((county!=""|| partner!="" || target!="" || year!="") && ( sex=="") ) {
        alert("Select sex  at line  : "+i);
         $('#target'+i).slideToggle("fast",function() {});
        $('#target'+i).slideToggle("fast",function() {});   
        $('#target'+i).focus();
        found="yes";
          rtn=false; 
           break;
        } 
        
        
        if((county!=""|| partner!="" || sex!="" || year!="") && ( target=="") ) {
        alert("Select target  at line  : "+i);
         $('#target'+i).slideToggle("fast",function() {});
        $('#target'+i).slideToggle("fast",function() {});   
        $('#target'+i).focus();
        found="yes";
          rtn=false; 
           break;
        }
        
//        if(date!="" && (name=="" || quantity=="") ) {
//        alert("Enter all details at line  : "+i);
//          rtn=false; 
//          break;
//        }
       
        if(target!="" && county!="" && year!="" && partner!="" && sex!="" ){
                  found="yes";   
                                                 }
        
 
    
    
    
          i++; 
     
 

}


if(found==""){
alert(" Can not save an empty set Please add Items..")
rtn=false;    
}



return rtn;
     }
//      
function deleteRow()
{
    var all_rows=document.getElementById("all_rows").value;
   
     if(all_rows=="1"){
        
    }
    else{
    var rws2=--all_rows
    if(rws2<0){
       rws2=0 
    }
document.getElementById('tb').deleteRow(all_rows)
document.getElementById("all_rows").value=rws2;
}
}
function insRow()
{
var all_rows=document.getElementById("all_rows").value;
    var rws2=++all_rows

   document.getElementById("all_rows").value=rws2;
var tbl = document.getElementById('tb');
var lastRow = tbl.rows.length;
var x=document.getElementById('tb').insertRow(lastRow)
var a=x.insertCell(0);
var y=x.insertCell(1);
var z=x.insertCell(2);
var v=x.insertCell(3);
var b=x.insertCell(4);
var n=x.insertCell(5);
var m=x.insertCell(6);



var hi = 1;
y.innerHTML="<select id='year"+rws2+"' name='year"+rws2+"' class='textbox2' style='width: 170px;border-color: green' onchange='loadtargets("+rws2+");'></select>";
z.innerHTML="<select id='county"+rws2+"' name='county"+rws2+"' class='textbox2' style='width: 170px;border-color: green' onchange='loadtargets("+rws2+");'></select>";
b.innerHTML="<select id='targetpop"+rws2+"' name='targetpop"+rws2+"' class='textbox2' style='width: 170px;border-color: green' onchange=' loadtargets("+rws2+");'><option value=''>select target pop</option></select>";
v.innerHTML="<select id='partner"+rws2+"' name='partner"+rws2+"' class='textbox2' style='width: 150px;border-color: green' onchange=' loadtargetpop("+rws2+"); loadtargets("+rws2+");'></select>";
m.innerHTML="<input type='text' value='' name='target"+rws2+"' id='target"+rws2+"' onkeypress='return numbers(event)' class='textbox'style='width: 150px;border-color: green'>";
n.innerHTML="<select id='sex"+rws2+"' name='sex"+rws2+"' class='textbox2' style='width: 170px;border-color: green' onchange=' loadtargets("+rws2+");'><option value=''>choose gender</option><option value='female'>female</option><option value='male'>male</option></select>";
a.innerHTML=""+rws2+""
get_years();
load_partners();
}   


function load_partners(){
//    alert("here")
 var current_row=document.getElementById("all_rows").value;
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
    //var current_row=document.getElementById("all_rows").value;

document.getElementById("partner"+current_row).innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_all_partners",true);
xmlhttp.send();   
    
}

    
    
    
    
    
    function rws(){
    var all_rows=0;
   
    document.getElementById("all_rows").value=all_rows;
    for( var i=0; i<=0; i++){
  insRow(); 
    }
}
    
    function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
} 
     
    
function get_years(){

var current_row=document.getElementById("all_rows").value;

//document.getElementById("partner"+current_row).innerHTML=xmlhttp.responseText;     
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
document.getElementById("year"+current_row).innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","years_loader",true);
xmlhttp.send();

  $.ajax({
    url:"countychooser1",
    type:'post',
    dataType:'html',
    success:function(data){
//        alert(data);
        document.getElementById("county"+current_row).innerHTML=data;
        
    }
});

}
//set the partner


//load target pop



function loadtargetpop(current_row){
var partner=document.getElementById("partner"+current_row).value;

 $.ajax({
    url:"population_chooser3?partner="+partner,
    type:'post',
    dataType:'html',
    success:function(data){
//        alert(data);
        document.getElementById("targetpop"+current_row).innerHTML=data;
        
    }
});


}

function loadtargets( id ){


var year=document.getElementById("year"+id).value;
var county=document.getElementById("county"+id).value;
var partner=document.getElementById("partner"+id).value;
var sex=document.getElementById("sex"+id).value;
var targetpop=document.getElementById("targetpop"+id).value;
if(year!=""&&county!=""&&partner!=""&&sex!=""){

  $.ajax({
    url:"loadtargets?county="+county+"&partner="+partner+"&year="+year+"&sex="+sex+"&targetpop="+targetpop,
    type:'post',
    dataType:'html',
    success:function(data){
        //alert(data);
        document.getElementById("target"+id).value=data;
        
    }
});

}

}



</script>

</head>
<body onload="rws();">

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
            <h3 style="text-align: center; background-color: orange;">Adding/Editing Targets</h3>
 
<div id="header" align="center">
<br/>






</div>


<div id="content" style="height:450px;">


<div id="midcontent" style="margin-left:0px ;">
   
<% if (session.getAttribute("addedtargets") != null) { %>
                                <script type="text/javascript"> 
                    var n = noty({text: '<%=session.getAttribute("addedtargets")%>',
                        
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 9800});
                    
                </script> <%
                session.removeAttribute("addedtargets");
                            }
%>
    
    
    
<h4>Specify details appropriately</h4>

<h4>




</h4>
<p><font color="red">*</font> indicates must fill fields</p>
  <form style="  width:1180px; margin-left: -90px;" id="contact-form"  method="post" action="savetargets" onsubmit="return nullchecker();">
       
          
          <br><input type="hidden" id="all_rows" name="all_rows" value="0" >
    <input type="button" onclick="insRow()" value="Add Row" class="submit" style=" height: 45px;  margin-left: 300px;">
        <input type="button" onclick="deleteRow()" value="Delete row" class="submit" style=" height: 45px;  margin-left: 100px;">
<br><br>
   <table cellpadding="2px" cellspacing="3px" border="1px" class="viewpdt"  style="margin-left: 10px;background: #ccccff; width:1070px; margin-bottom: 0px;">
    
                       <tr>
                      <th style="width:1.2%"># <font color="red"></font></th>
                      <th style="width:16.46%">Year<font color="red">*</font></th>
                      <th style="width:16.46%">County<font color="red">*</font></th>
                     <th style="width:16.46%">Partner<font color="red">*</font></th>
                     <th style="width:16.46%">Target Pop.<font color="red">*</font></th>
                     <th style="width:16.46%">Sex<font color="red">*</font></th>
                     <td style="width:16.46%">Value<font color="red">*</font></td>
                      </tr>
</table>
  <table cellpadding="2px" cellspacing="3px" border="1px" class="viewpdt" id="tb"  style="margin-left: 10px; background: #ccccff; width:950px;margin-top: 0px;">
</table>
         
    <br>
        <input type="submit" name="sub" value="Save" class="submit" style="height: 45px; width: 100px; margin-left: 400px;" >
     
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
