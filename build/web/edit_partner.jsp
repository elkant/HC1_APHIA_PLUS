<%-- 
    Document   : edit_partner
    Created on : Sep 13, 2019, 8:40:57 AM
    Author     : Geofrey Nyabuto
--%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
    if (session.getAttribute("level")==null) {
        response.sendRedirect("index.jsp");
    } 
    if (!session.getAttribute("level").equals("0")) {
        response.sendRedirect("index.jsp");
    } 
%>
<%@page import="hc.dbConn"%>
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
<title>Edit Partner.</title>
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


    function part_ners(){
//        alert("here");
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
document.getElementById("partner").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","load_all_partners",true);
xmlhttp.send();
}//end of partner picker
</script>
<script type="text/javascript">
    function null_confirmer(){
//        alert("Enter new partner name");
        var new_partner=document.getElementById("new_partner").length;
       return !/[^\s]/.test(new_partner);
if(new_partner==0){
            alert("Enter new partner name");
            return false;
        }              
    }
    function isWhitespaceOrEmpty() {
   return !/[^\s]/.test(text);
}
</script>
</head>
<body onload="return part_ners();">
<div id="container" >
 <%   if (session.getAttribute("userid").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>
            <br/>
            <h3 style="text-align: center; background-color: orange;">EDITING PARTNERS</h3>
<div id="header" align="center">
</div>
<div id="content" style="height:530px;">
<div id="midcontent" style="margin-left:130px ;">
    <br/><br/>

<h4>Select Partner to edit Details.</h4>
<br><br>
<form action="edit_partner" name="" method="post" onsubmit="null_confirmer();">
<table cellpadding="2px" cellspacing="3px" border="0px">
    <br><br>
<%if (session.getAttribute("partner_editor") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("partner_editor")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("partner_editor");
                            }

                        %>

<font color="black" id="parter">Partner</font>
<br><br>
<Select  required ="true" name="partner" id="partner" class="textbox2" style="background: #cc99ff">
<option value="">Choose Partner</option>  
</select>
<br><br>
<font color="black" id="parter2" hidden="true">New Partner Name<font color="red">*</font></font>
<br><br>
<input type="text" required=" true" name="new_partner" id="new_partner" class="textbox" placeholder="Enter new Partner name">
<br><br>
<input type="submit" name="sub" value="Save" class="textbox1" style="background: #cc99ff; color: #0000ff" >
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
