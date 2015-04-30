<%-- 
    Document   : add_group
    Created on : Sep 8, 2013, 5:54:29 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="hc.dbConn1"%>
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
        <script type="text/javascript" src="js/js/sisyphus.min.js"></script>
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
                    
                
<title>FORM ENTRY(1/7)</title>
<script type="text/javascript">

//manage drop down list for primary and secondary schools



 

//filter group__

         function filter_gr(){
var dist=document.getElementById("district").value;
var dis=dist.split(",", 2);
var id_dist=dis[0];
var popu=document.getElementById("target_pop").value;

if(id_dist!="" && popu!=""){
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
xmlhttp.open("POST","load_grps?distr="+id_dist+"&&popul="+popu,true);
xmlhttp.send();
}
         }





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

//function get_years(){
//
//// window.open("districtchooser?county="+dist.value);     
//var xmlhttp;    
//if (window.XMLHttpRequest)
//{// code for IE7+, Firefox, Chrome, Opera, Safari
//xmlhttp=new XMLHttpRequest();
//}
//else
//{// code for IE6, IE5
//xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//}
//xmlhttp.onreadystatechange=function()
//{
//if (xmlhttp.readyState==4 && xmlhttp.status==200)
//{
//document.getElementById("year").innerHTML=xmlhttp.responseText;
//}
//}
//xmlhttp.open("POST","years_loader",true);
//xmlhttp.send();
//}
////set the partner
//function get_period(){
//// window.open("districtchooser?county="+dist.value);     
//var xmlhttp;    
//if (window.XMLHttpRequest)
//{// code for IE7+, Firefox, Chrome, Opera, Safari
//xmlhttp=new XMLHttpRequest();
//}
//else
//{// code for IE6, IE5
//xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
//}
//xmlhttp.onreadystatechange=function()
//{
//if (xmlhttp.readyState==4 && xmlhttp.status==200)
//{
//document.getElementById("period").innerHTML=xmlhttp.responseText;
//}
//}
//xmlhttp.open("POST","period_loader",true);
//xmlhttp.send();
//}



function grptype(val){
    
    
    var gtype=val.value;
    
    document.getElementById("grpstatus").innerHTML="";
    
    if(gtype=="existing"){
        
        
                document.getElementById("group_name").style.display='block';
                document.getElementById("group_name").required="true";
                document.getElementById("gname").style.display='block';
                
                
                
                  document.getElementById("newgroup_name").style.display='none';
                  document.getElementById("newgname").style.display='none';
                 document.getElementById("newgroup_name").value="";
                 document.getElementById("newgroup_name").removeAttribute("required");
    }
    else{
        
           document.getElementById("group_name").style.display='none';
           document.getElementById("group_name").removeAttribute("required");
           document.getElementById("gname").style.display='none';  
           
           
           document.getElementById("newgroup_name").style.display='block';
           document.getElementById("newgroup_name").required="true";
           document.getElementById("newgname").style.display='block';  
        
        
    }
    
    
}



function checkgrpname(){

 var grpname=document.getElementById("newgroup_name").value;    
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
var resp=xmlhttp.responseText; 

   
 document.getElementById("grpstatus").innerHTML=resp;
   


}
}
xmlhttp.open("POST","checkgroup?groupname="+grpname,true);
xmlhttp.send();
}

</script>

</head>
<body onload="filter_partner();  ">

<div id="container" style="font-size: 17px;">
 <%   if(session.getAttribute("level").equals("2")){ 
            %>
<%@include file="/menu/clerkmenu.jsp"%>
<%} else{%>
<%@include file="/menu/adminmenu.jsp"%>
<%}%>


<div id="header" align="center">
<br/>
 


 <%if (session.getAttribute("grpmsg") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("grpmsg")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("grpmsg");
                            }

                        %>
                        
<%

dbConn1 con=new dbConn1();
if (!session.getAttribute("username").equals("m&e")&&!session.getAttribute("username").equals("joel")){
 String ch1 = "SHOW COLUMNS FROM mande_mail LIKE 'county'";
            con.rs = con.st.executeQuery(ch1);
            if (con.rs.next()) {
                
               
            //check whether all the details have been updated
                String getdata="select * from mande_mail where (county='' or county is NULL or partner='' or usermail='') and mailid='1' ";
            con.rs1=con.st1.executeQuery(getdata);
            
            if(con.rs1.next()){
            
                   session.setAttribute("newmessage","<font color=\"orange\">New !! Please Update all the fields in this form before beginning any new data entry</font>");
                       response.sendRedirect("mandemail.jsp");
            }
                       else {
                       
                       session.setAttribute("newmessage",null);
                       
                       }
            
            }
                       else{
                
                session.setAttribute("newmessage","<font color=\"orange\">New !! Please Update all the fields in this form before beginning any new data entry</font>");
                       response.sendRedirect("mandemail.jsp");
                       
                       }}


%>

<!------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------>
<!--------------------------M&E CODE TO ASK FOR AN UPDATE---------------------------------------------------->
<!------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------>
<!------------------------------------------------------------------------------>




<%

if (session.getAttribute("username").equals("m&e")){

 String ch2 = "SHOW COLUMNS FROM mande_mail LIKE 'county'";
            con.rs = con.st.executeQuery(ch2);
            if (con.rs.next()) {
                
               
            //check whether all the details have been updated
                String getdata="select * from mande_mail where (county='' or county is NULL or partner='' or usermail='') and mailid='2' ";
            con.rs1=con.st1.executeQuery(getdata);
            
            if(con.rs1.next()){
            
                   session.setAttribute("newmessage","<font color=\"orange\">New !! Please Update all the fields in this form before beginning any new activities</font>");
                       response.sendRedirect("update_sdm_email.jsp");
            }
                       else {
                       
                       session.setAttribute("newmessage",null);
                       
                       }
            
            }
                       else{
                
                session.setAttribute("newmessage","<font color=\"orange\">New !! Please Update all the fields in this form before beginning any new activities</font>");
                       response.sendRedirect("update_sdm_email.jsp");
                       
                       }}


%>





</div>
 <h3 style="text-align: center; background-color: orange;">Add/Select Group   PAGE 1/7</h3>
 <h5 style="text-align: center;"> </h5>

<div id="content" style="height:510px;">


<div id="midcontent" style="margin-left:130px ;">
<h4>Specify details appropriately in each page.
    <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4>
                    <div id="dialog" title="Form wizard Help." style=" font-size: 17px;">
Takes you through the process of adding:<br/><br/> Groups<br/><br/>Facilitators<br/> <br/>Participants <br/> <br/>Marking attendance
</div>

<h4>

<%
if(session.getAttribute("prevpage")!=null){

    session.removeAttribute("prevpage");

}
%>
<%
if(session.getAttribute("ispartisadded")!=null){

    session.removeAttribute("ispartisadded");

}
%>
<%
if(session.getAttribute("isfacilsadded")!=null){

    session.removeAttribute("isfacilsadded");

}
%>


</h4>
<p><font color="red">*</font> indicates must fill fields</p>
<form action="add_group_session" method="get">
<table cellpadding="2px" cellspacing="3px" border="0px">





<tr>
<td class="align_button_right">County<font color="red">*</font></td>
<td><Select id="pdt_cat" class="textbox6" onchange="filter_districts(this);"  required ="true" name="county" >

<option value="">Choose County</option>  
<option value="4">Baringo</option>
<option value="5">Kajiado</option>      
<option value="2">Laikipia</option>
<option value="1">Nakuru</option>
<option value="3">Narok</option>

</select></td><td rowspan="6"><p id="grpstatus" style="color: red;background-color: white;"></p></td>


</tr>   



<!--district-->


<tr>  
<td class="align_button_right">District<font color="red">*</font></td>
<td><Select id="district" class="textbox6"  required ="true" onchange="filter_partner(this);" name="district" >

<option value="">Choose District</option>  
</select></td><td></td></tr>



<tr>  
<td class="align_button_right" style="width:300px;">Implementing Partner<font color="red">*</font></td>
<td><Select id="partner_name" class="textbox6"  required ="true" onchange="filter_target_pop(this);" name="partner_name" >

<option value="">Choose partner</option>  
</select></td><td ><input type="hidden" id="hidden_partner" name="hidden_partner" /></td></tr>

<tr> <td class="align_button_right">Target Population <font color="red">*</font></td>
<td><Select id="target_pop" class="textbox6" onchange="filter_gr(this);"   required ="true" name="target_pop" >

<option value="">target population</option>  


</select></td>
</tr>


<tr> <td class="align_button_right">Group Category<font color="red">*</font></td>
<td><Select id="grpcat" class="textbox6" onchange="grptype(this);"   required ="true" name="target_pop" >

<option value="">category</option>  
<option value="new">New Group</option>  
<option value="existing">Existing Group</option>  


</select></td>
</tr>

<tr> 
    
    <td class="align_button_right"><p id="gname" style="display: none;">Group Name <font color="red">*</font><p></td>
<td><Select style="display:none;" id="group_name" class="textbox6"  required ="true" name="group_name" >

<option value="">Choose Group</option>  


</select></td>

</tr>


<!-------Enter group name-------->

<tr> 
    
    <td class="align_button_right"><p id="newgname" style="display: none;">Enter Group Name <font color="red">*</font><p></td>
    <td><input type="text" style="display:none;"  oninput="checkgrpname();" id="newgroup_name" class="textbox"  required  name="newgroup_name" />

</td>

</tr>


<tr><td class="align_button_right"></td><td><input type="hidden" name="no_of_groups" readonly="true" value="1" required class="textbox" /></td></tr>

<tr><td></td><td>
        <input type="hidden" name="prevpage" value="FormWizard.jsp"/>
        <input type="submit" value="Next" style="background-color: orange; height:35px;width:100px;"/></td></tr>

</table>
</form
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
