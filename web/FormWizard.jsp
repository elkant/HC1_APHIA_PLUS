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
dbConn con1=new dbConn();
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





String wardexists="SHOW TABLES LIKE 'ward'";


con1.rs=con1.st.executeQuery(wardexists);
if(!con1.rs.next()){

//now create the table
    
  con1.st1.executeUpdate("INSERT INTO `district` (`district_id`, `county_id`, `district_name`) VALUES ('3', '1', 'BAHATI')"); 
  con1.st1.executeUpdate("CREATE TABLE `ward` (  `wardid` int(11) NOT NULL AUTO_INCREMENT,  `wardname` varchar(45) DEFAULT NULL,  `district_id` varchar(45) DEFAULT NULL,  PRIMARY KEY (`wardid`)) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8"); 
  con1.st1.executeUpdate("INSERT INTO `ward` VALUES (1,'Dundori','3'),(2,'Kabatini','3'),(3,'Kiamaina','3'),(4,'Lanet/umoja','3'),(5,'Bahati','3'),(6,'Gilgil','4'),(7,'Kiptororo','5'),(8,'Nyota','5'),(9,'Sirikwa','5'),(10,'Kamara','5'),(11,'Amalo','5'),(12,'Keringet','5'),(13,'Kiptagich','5'),(14,'Tinet','5'),(15,'Mariashoni','6'),(16,'Elburgon','6'),(17,'Turi','6'),(18,'Molo','6'),(19,'Hells gate','7'),(20,'Lake view','7'),(21,'Mai mahiu','7'),(22,'Maiella','7'),(23,'Olkaria','7'),(24,'Naivasha east','7'),(25,'Viwandani','7'),(26,'Elementaita','4'),(27,'Mbaruk/eburu','7'),(28,'Malewa west','4'),(29,'Murindati','4'),(30,'Biashara','7'),(31,'Mau narok','10'),(32,'Mauche','10'),(33,'Kihingo','10'),(34,'Nessuit','10'),(35,'Lare','10'),(36,'Njoro','10'),(37,'Menengai west','11'),(38,'Soin','11'),(39,'Visoi','11'),(40,'Mosop','11'),(41,'Waseges','12'),(42,'Kabazi','12'),(43,'Narok Town','14'),(44,'Nanyuki','29'),(45,'Menengai','38'),(46,'Barut','38'),(47,'London','38'),(48,'Kaptembwo','38'),(49,'Kapkures','38'),(50,'Rhoda','38'),(51,'Shaabab','38'),(52,'Kivumbini','38'),(53,'Flamingo','38'),(54,'Nakuru east','38'),(55,'Igwamiti','30')"); 
  System.out.println("inserting ward table"); 

}



//also check if wardid exists in groups
 String ch3 = "SHOW COLUMNS FROM groups LIKE 'wardid'";
            con1.rs = con1.st.executeQuery(ch3);
            if (!con1.rs.next()) {
           con1.st1.executeUpdate("ALTER TABLE groups ADD COLUMN wardid VARCHAR(45) NULL AFTER timestamp");
           con1.st1.executeUpdate("UPDATE district SET district_name='NAKURU EAST' WHERE district_id='32'");
           con1.st1.executeUpdate("UPDATE ward SET district_id='32' WHERE wardid='54'");
           con1.st1.executeUpdate("UPDATE ward SET district_id='32' WHERE wardid='53'");         
           con1.st1.executeUpdate("UPDATE ward SET district_id='32' WHERE wardid='52'");
           con1.st1.executeUpdate("UPDATE ward SET district_id='4' WHERE wardid='29'");//elementaita
           con1.st1.executeUpdate("UPDATE ward SET district_id='4' WHERE wardid='26'");////murindati
           con1.st1.executeUpdate("UPDATE ward SET district_id='4' WHERE wardid='28'");//malewa west
           con1.st1.executeUpdate("REPLACE INTO ward (wardid,wardname,district_id) VALUES ('55','Igwamiti', '30')");
             

         
            }
            
            else {
            //check if there is a group with a ward that contains a name
            //
           con1.rs3=con1.st3.executeQuery("SELECT group_id,wardid FROM groups where wardid like '%,%'");
           while(con1.rs3.next()){
            String wardid=con1.rs3.getString("wardid");
              if(wardid.contains(",")){ 
              String wards[]=wardid.split(",");
              wardid=wards[0];
                  
                                      }
           
           con1.st4.executeUpdate("update groups set wardid='"+wardid+"' where group_id='"+con1.rs3.getString("group_id")+"'");
           
           }     
            
            }
                      
            
          
            
            
%>





</div>
 <h3 style="text-align: center; background-color: orange;">Add/Select Group   PAGE 1/7</h3>
 <h5 style="text-align: center;"> </h5>

<div id="content" style="height:530px;">


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

      <style>
    #content{
        
        font-family: cambria;
        font-size: 17px;
    }
    select{
        
        width:170px;
    }
    
</style> 
<p><font color="red">*</font> indicates must fill fields</p>

<form id="loginform" action="add_group_session" method="get">
<table cellpadding="2px" cellspacing="3px" border="0px">





<tr>
<td class="align_button_right">County<font color="red">*</font></td>
<td><Select id="pdt_cat" style="width:150px;" class="textbox6" onchange="filter_districts(this);"  required ="true" name="county" >

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
<td class="align_button_right">Sub-County<font color="red">*</font></td>
<td><Select id="district" class="textbox6" style="width:150px;" required ="true" onchange="filter_partner(this);loadward();" name="district" >

<option value="">Choose Sub-county</option>  
</select></td><td></td></tr>


<tr>  
<td class="align_button_right">Ward<font color="red">*</font></td>
<td><Select id="ward" style="width:150px;" class="textbox6"  required ="true" onchange="" name="ward" >

<option value="">Choose Ward</option>  
</select></td><td></td></tr>


<tr>  
<td class="align_button_right" style="width:300px;">Implementing Partner<font color="red">*</font></td>
<td><Select id="partner_name" style="width:150px;" class="textbox6"  required ="true" onchange="filter_target_pop(this);" name="partner_name" >

<option value="">Choose partner</option>  
</select></td><td ><input type="hidden" id="hidden_partner" name="hidden_partner" /></td></tr>

<tr> <td class="align_button_right">Target Population <font color="red">*</font></td>
<td><Select id="target_pop" style="width:150px;" class="textbox6" onchange="filter_gr(this);"   required ="true" name="target_pop" >

<option value="">target population</option>  


</select></td>
</tr>


<tr> <td class="align_button_right">Group Category<font color="red">*</font></td>
<td><Select id="grpcat" style="width:150px;" class="textbox6" onchange="grptype(this);"   required ="true" name="grpcat" >

<option value="">category</option>  
<option value="new">New Group</option>  
<option value="existing">Existing Group</option>  


</select></td>
</tr>

<tr> 
    
    <td class="align_button_right"><p id="gname" style="display: none;">Group Name <font color="red">*</font><p></td>
<td><Select style="display:none;width:150px;" id="group_name" class="textbox6"  required ="true" name="group_name" >

<option value="">Choose Group</option>  


</select></td>

</tr>


<!-------Enter group name-------->

<tr> 
    
    <td class="align_button_right"><p id="newgname" style="display: none;">Enter Group Name <font color="red">*</font><p></td>
    <td><input type="text" style="display:none;width:150px;"  oninput="checkgrpname();" id="newgroup_name" class="textbox"  required  name="newgroup_name" />

</td>

</tr>


<tr><td class="align_button_right"></td><td><input type="hidden" style="width:150px;" name="no_of_groups" readonly="true" value="1" required class="textbox" /></td></tr>

<tr><td></td><td>
        <input type="hidden" name="prevpage" value="FormWizard.jsp"/>
        <input type="submit" value="Next" style="background-color: orange; height:35px;width:150px;"/></td></tr>

</table>
</form>
<div id="versionChecker" style="font-weight: bolder; text-align:center;">
                         </div>
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


<%

String locktableexists="SHOW TABLES LIKE 'lock_data'";


con1.rs=con1.st.executeQuery(locktableexists);
if(!con1.rs.next()){

//now create the table
    
  con1.st1.executeUpdate("CREATE TABLE `lock_data` (  `id` int(11) NOT NULL AUTO_INCREMENT,  `lock_date` varchar(45) DEFAULT NULL,  `min_date` varchar(45) DEFAULT NULL COMMENT 'This date is entered in the following format, month/day/ year e.g 08/28/2015 for August 28 2015.',  `updated_on` timestamp NULL DEFAULT CURRENT_TIMESTAMP,  PRIMARY KEY (`id`)) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8"); 
  con1.st1.executeUpdate("insert into lock_data(lock_date,min_date) values ('07/10/2015','07/01/2015')"); 
 

}
else {


%>
<script>
    
                   $.ajax({
                        url:'setSystemDate',                         
                        type:'post',  
                        dataType: 'html',  
                        success: function(data) {}
                        
                         });
                   
               
               
                 function checkVersion(){
//    CHECK Version------------------- 
//$("#versionChecker").html("<p>Checking for newer Version...</p>");
var versionText="",daysRemaining,warningText="",sentOn="",version_name="";
 $.ajax({
                    url:"version",
                    type:'post',
                    dataType:'html',
                    success:function (data){
                        if(data.trim()==="no_internet"){
   // $("#versionChecker").html  ("<p style='color: blue; font-size:10px;'>Unable to check if there is a newer version of HC1 system due to limited or no internet connection.</p>");
      setInterval(function(){ checkVersion(); }, 60000);          
                                                       }
                        else{
                            
        
                      if(data.contains("outdated version")){
                          $("#loginform").css("display","none");  
                      
                      }
$("#versionChecker").html(data);
                        }
  }  
  });   
      }
 
                         
   checkVersion();                
               
</script>    

<%
}


  if(con1.rs!=null){con1.rs.close();}
            if(con1.rs3!=null){con1.rs3.close();}
            if(con1.st!=null){con1.st.close();}
            if(con1.st1!=null){con1.st1.close();}
            if(con1.st3!=null){con1.st3.close();}
            
            if(con.rs!=null){con.rs.close();}
            if(con.st!=null){con.st.close();}
            if(con.st1!=null){con.st1.close();}
            if(con.st4!=null){con.st4.close();}



%>
</body>
</html>
