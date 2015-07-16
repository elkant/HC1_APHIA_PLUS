<%-- 
    Document   : edit_group2
    Created on : Sep 8, 2013, 8:58:45 PM
    Author     : Geofrey Nyabuto
--%>
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
<%@page import="hc.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
             <link rel="shortcut icon" href="images/hc_logo.png"/>
<link href="menu/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="menu/iconic.css" media="screen" rel="stylesheet" type="text/css" />

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
        
        
        
             
             function getcounty(){
                
                    
            
            $.ajax({
                url:"countychooser1",
                type:'post',
                dataType:'html',
                success:function(data){
                    //alert(data);
                document.getElementById("countysel").innerHTML=data;
                filter_districts();
                }
                
                
                
            });
            
            }  
        
        
        
        function loadward(){
    
        var dist=document.getElementById("district").value;
            $.ajax({
url:"loadward?dist="+dist,
type:'post',
dataType:'html',
success:function (data){
       $("#ward").html(data);
        
      // App.init();   
                       }

}); 
    
    
    
}
        
        
          function filter_districts(){

                var county_id=document.getElementById("countysel").value;        
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
                        
                        filter_partner();
                        loadward();
                    }
                }
                xmlhttp.open("POST","districtchooser1?county_id="+county_id,true);
                xmlhttp.send();
                
                document.getElementById("district").innerHTML="<option value=\"\">loading..</option>";
                
            }//end of filter districts


        
      
          function filter_partner(){

                var dist_id=document.getElementById("district").value;        
                var xmlhttp;    
                if (dist_id=="")
                {
                    //filter the districts    



                    document.getElementById("partner").innerHTML="<option value=\"\">Choose partner</option>";
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
                        document.getElementById("partner").innerHTML=xmlhttp.responseText;
                        filter_target_pop();
                    }
                }
                xmlhttp.open("POST","partner_chooser1?dist="+dist_id,true);
                xmlhttp.send();
                
                document.getElementById("partner").innerHTML="<option value=\"\">loading..</option>";
                
            }
        
        function filter_target_pop(){
         
               
       // document.getElementById("hidden_dist").value=dist.value;     
        
           
  var partner_name=document.getElementById("partner").value;
     
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
    document.getElementById("targetpop").innerHTML=xmlhttp.responseText;
    
    }
  }
xmlhttp.open("POST","population_chooser1?partner="+partner_name,true);
xmlhttp.send();
//filter_nhfs(dist);
           }//end of filter population
     
        
        
	</script>                
    
        
<title>Edit Group.</title>
    </head>
<body>

    <div id="container" style="font-size: 17px;">
        

               <%      if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}
    
      else{%>
       <%@include file="/menu/clerkmenu.jsp"%>
      <%}
   %>


<div id="header" align="center">
</div>
<br/>
     <h3 style="text-align: center; background-color: orange;">EDITING GROUPS</h3>

<div id="content" style="height:530px;">
<div id="midcontent" style="margin-left:130px ;">
    <br/><br/>
<h4>Edit and Save Details.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4>
                    
<br><br>
   <form action="edit_group" name="" method="post">
       
       <div id="dialog" title="Edit Group Help." style=" font-size: 16px;">
<%  String message1="";
 dbConn conn= new dbConn();
 
String message_selector="SELECT * FROM help WHERE help_id='4'";
conn.rs=conn.st.executeQuery(message_selector);
conn.rs.next();
message1=conn.rs.getString(3);
out.println(message1);
%>
     </div>
     <input type="hidden" value="<%=session.getAttribute("county_id")%>" name="county" id="county">
     
<table cellpadding="4" style="width:400px;" cellspacing="6"> <br/>
        <tr>
<td style="text-align: right;"><b> New County:</b></td><td><select style="width:200px;" onchange="filter_districts();" class="textbox10" name="countysel" required id="countysel"><option value="">select county</option> </select></td></tr>
    <tr>
        
            <tr>
                <td style="text-align: right;"> <b>New Sub-county:</b></td><td><select style="width:200px;" onchange="filter_partner();loadward();" required class="textbox10" name="district" id="district"><option value="">select sub-county</option> </select></td></tr>

 <tr>
  <td style="text-align: right;"><b> New Ward:</b></td><td><select style="width:200px;"   class="textbox10" name="ward" id="ward"><option value="">select ward</option> </select></td></tr>
       
<td style="text-align: right;"> <b>New Partner:</b></td><td><select style="width:200px;" class="textbox10" name="partner" onchange="filter_target_pop();" required id="partner"><option value="">select partner</option> </select></td></tr>
    <tr>
<td style="text-align: right;"><b>New Target Population:</b></td><td><select style="width:200px;" class="textbox10" required name="targetpop" id="targetpop"><option value="">select target population</option> </select></td></tr>

    
    

 <tr><td style="text-align: right;">

<b>Group Name:</b>

</td>
<td>
<input style="width:200px;" required type="text" name="group_name" value="<%=session.getAttribute("group_name")%>" class="textbox"/>
</td>

</tr>
<tr>
    <td></td><td><input type="submit" name="sub" value="Save Details" style="height:35px; width: 120px; background: orange; " ></td></tr>
</table>
       <br><br>
</form>
<script >  getcounty();</script>

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
