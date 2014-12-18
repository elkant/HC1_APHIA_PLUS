<%-- 
    Document   : edit_target_population
    Created on : Sep 6, 2013, 8:45:09 AM
    Author     : Geofrey Nyabuto
--%>
<%
//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

        if (session.getAttribute("level")==null || session.getAttribute("level").equals("")){
        response.sendRedirect("index.jsp");
    }
    

    if (!session.getAttribute("level").equals("0")) {
        response.sendRedirect("index.jsp");
    } 
%>
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
<title>Edit Districts.</title>
<script type="text/javascript">
    
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
xmlhttp.open("POST","load_all_partners?",true);
xmlhttp.send();
}

    function ager(){
    
   
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
document.getElementById("age_group").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","age_group_chooser?age_grp="+ager,true);
xmlhttp.send();
}//end of count picker
    
    
    
function filter_tp(){

        var partner=document.getElementById("partner_name").value;        
var xmlhttp;    
if (partner=="")
{
//filter the districts    



document.getElementById("tp").innerHTML="<option value=\"\">Choose Target Population</option>";
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
document.getElementById("tp").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","population_chooser?partner="+partner,true);
xmlhttp.send();
}//end of filter districts
</script>
</head>
<body onload="return filter_partner();">
<%
session.removeAttribute("age_id");
session.removeAttribute("age_name");
session.removeAttribute("tp_id");
session.removeAttribute("tp_name");

%>
<div id="container" >
<%   if (session.getAttribute("userid").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>
            <br/>
            <h3 style="text-align: center; background-color: orange;">EDIT TARGET POPULATION</h3>
<div id="header" align="center">
</div>
<div id="content" style="height:530px;">
<div id="midcontent" style="margin-left:130px ;">
    <br/><br/>
     <%if (session.getAttribute("tp_success") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("tp_success")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("tp_success");
                            }

                        %>
<h4>Choose Age Group and Target Population to Edit Details.</h4>
<br><br>
   <form action="edit_tp_session" name="" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
    <br><br>

 Implementing Partner<font color="red">*</font>
  <br><br>
 
  <Select  required ="true" name="partner_name"  id ="partner_name" class="textbox4" style="background: #cc99ff; height: 30px" onchange="return filter_tp();">
<option value="">Choose Partner</option>  
</select>
<br><br>
Choose Target Population<font color="red">*</font>
<br><br>
 <Select  required ="true" name="tp" id="tp" class="textbox2" style="background: #cc99ff">
<option value="">Choose Target Pop</option>  
</select>
<br><br>
<input type="submit" name="sub" value="Edit Target Pop" class="textbox1" style="background: #cc99ff; color: #0000ff" >
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