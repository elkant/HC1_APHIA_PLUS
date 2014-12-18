<%-- 
    Document   : edit_topic
    Created on : Sep 10, 2013, 9:01:10 PM
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
<title>Edit Topic.</title>
<script type="text/javascript">
function curriculum(){

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
document.getElementById("curriculum").innerHTML=xmlhttp.responseText;
document.getElementById("curriculum2").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","curriculum_loader",true);
xmlhttp.send();
}//end of partner picker

function topics_returner(){ 
var curriculum_id=document.getElementById("curriculum").value;
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
document.getElementById("topics").innerHTML=xmlhttp.responseText;
}
}
xmlhttp.open("POST","topic_loader?curriculum_id="+curriculum_id,true);
xmlhttp.send();
}//end of topic picker

function edit_topic(topics){ 
var topic=topics.value;
  var valuer=document.getElementById("topics").value;
    if(valuer!=""){
    document.getElementById("curriculum2").hidden=false;
    document.getElementById("txt").hidden=false;
    document.getElementById("texter").hidden=false;
    document.getElementById("new_name").value=topic;    
    document.getElementById("new_name").hidden=false; 
}
else{
    document.getElementById("new_name").hidden=true; 
} 

}//end of topic picker

function edit_activator(){
   
}
</script>
</head>
<body onload="return curriculum();">
<div id="container" >
 <%   if (session.getAttribute("userid").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>
            <br/>
            <h3 style="text-align: center; background-color: orange;">EDITING TOPIC</h3>
<div id="header" align="center">
</div>
<div id="content" style="height:530px;">
<div id="midcontent" style="margin-left:130px ;">
    <br/><br/>

<h4>Select Topic to edit Details.</h4>
<br><br><%if (session.getAttribute("topic_edit") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("topic_edit")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("topic_edit");
                            }

                        %>
                        
   <form action="edit_topic" name="" method="post">
<table cellpadding="2px" cellspacing="3px" border="0px">
    <br><br>

  Curriculum<font color="red">*</font>
  <br><br>
 
  <Select  required ="true" name="curriculum"  id ="curriculum" class="textbox2"  onchange="return topics_returner();">
<option value="">Choose Curriculum</option>  
</select>
   <br><br>
    Topic <font color="red">*</font>
  <br><br>
 
  <Select  required ="true" name="topics"  id ="topics" class="textbox2" onchange="return edit_topic(this);">
<option value="">Choose Topic</option>  
</select>
<br><br>
<p id="txt" hidden="true" required>Select new curriculum</p>
<br><br>
<Select name="curriculum2"  id ="curriculum2" class="textbox2"  onchange="" hidden="true">
      <br><br>
      <option value="" selected="true">Choose Curriculum</option>  
</select>
  <br><br>
<p id="texter" hidden="true">Enter New Topic Name</p>
<br><br>
<input type="text" name="new_topic" value="" id="new_name" required="true" class="textbox" placeholder="Enter New Curriculum Name" hidden="true"/>
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
