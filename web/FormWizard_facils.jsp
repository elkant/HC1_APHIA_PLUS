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
<link rel="stylesheet" type="text/css" href="css/divCss_2.css"/>
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
                
                
     <!-- ANIMATE HELP    -->
   <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
<!--	<script src="js/jquery-1.9.1.js"></script>-->
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


 function getfacil(){
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
                        if(xmlhttp.responseText.trim()==='<h1><option value="">No added facilitators </option></h1>'){
                           document.getElementById("no_of_facils").value="2"; 
                            
                        }
                        document.getElementById("facil").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST","load_facils",true);
                xmlhttp.send();
            } 
 


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

     <h3 style="text-align: center; background-color: orange;">
         
          <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     <a href="FormWizard.jsp" style="margin-left: -400px;margin-right: 250px;" class="linkstyle"> << Back</a>
 <%}%>
         
         
         ADDING FACILITATORS
     
      <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     &nbsp; &nbsp; Page 2/7 &nbsp;
 <%}%>
     
     </h3>

<div id="header" align="center">
<br/>

    
<%
//session.removeAttribute("target_pop_id");
//session.removeAttribute("county_id");
//session.removeAttribute("district_id");
//session.removeAttribute("district_name");
//session.removeAttribute("partner_id");
//session.removeAttribute("partner_name");
//session.removeAttribute("target_pop_name");
%>


<%if (session.getAttribute("grpmsg") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("grpmsg")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 4800});
                    
                </script> <%
                session.removeAttribute("grpmsg");
                            }

                        %>
                        


</div>


<div id="content" style="height:450px;">
<p style="text-align: center;"> Specify the number of facilitators to add then click next .</p>

<div id="midcontent" style="margin-left:130px ;">
<h4>
    
   
    
    
    <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4>
       <div id="dialog" title="Add Facilitator Help." style=" font-size: 17px;">
<p>Enter the number of facilitators you wish to add.<br/><br/>*If the facilitator and/or co-facilitators you are adding have already been added, Enter 0 as the number of facilitators to skip this section.</p>
</div>


<h4>




</h4>

<form action="add_facilitator_session" method="get">
<table cellpadding="2px" cellspacing="3px" border="0px">




<tr><td>facilitators preview</td><td><Select title="this section helps you to see the facilitators that have already been added to the group that you have just selected " placeholder="facilitator names ( facilitator phone number)" id="facil" class="textbox2" style="height:40px;width:220px;"  name="facil" >

                                </select>
                            </td></tr>
<tr><td class="align_button_right">Number of facilitators to add </td><td><input type="text" class="textbox" value="0" name="no_of_facils" id="no_of_facils" required></td></tr>
<tr><td></td><td><input type="submit" value="Next" style="height: 35px;width:100px; background-color: orange;"/></td></tr>


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
<script>
    
    getfacil();
    
</script>
</body>
</html>
