<%-- 
    Document   : transfer_one_teacher
    Created on : Sep 12, 2019, 9:21:36 AM
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

<%@page import="java.util.Random"%>
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
      <link href="css/multiselect.css" media="screen" rel="stylesheet" type="text/css">
      <link href="css/bootstrap.css" media="screen" rel="stylesheet" type="text/css">
      <link href="css/theme.css" media="screen" rel="stylesheet" type="text/css">
      <link href="css/application.css" media="screen" rel="stylesheet" type="text/css">       

      
        <script src="js/bootstrap.js" type="text/javascript"></script>
    <script src="js/jquery.tinysort.js" type="text/javascript"></script>
    <script src="js/jquery.quicksearch.js" type="text/javascript"></script>
    <script src="js/jquery.multi-select.js" type="text/javascript"></script>
    <script src="js/rainbow.js" type="text/javascript"></script>
    <script src="js/application.js" type="text/javascript"></script>
      
<title>Edit Facilitator</title>

<script>
       
         $('#groups').multiSelect({
  selectableHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='try \"12\"'>",
  selectionHeader: "<input type='text' class='search-input' autocomplete='off' placeholder='try \"4\"'>",
  afterInit: function(ms){
    var that = this,
        $selectableSearch = that.$selectableUl.prev(),
        $selectionSearch = that.$selectionUl.prev(),
        selectableSearchString = '#'+that.$container.attr('id')+' .ms-elem-selectable:not(.ms-selected)',
        selectionSearchString = '#'+that.$container.attr('id')+' .ms-elem-selection.ms-selected';

    that.qs1 = $selectableSearch.quicksearch(selectableSearchString)
    .on('keydown', function(e){
      if (e.which === 40){
        that.$selectableUl.focus();
        return false;
      }
    });

    that.qs2 = $selectionSearch.quicksearch(selectionSearchString)
    .on('keydown', function(e){
      if (e.which == 40){
        that.$selectionUl.focus();
        return false;
      }
    });
  },
  afterSelect: function(){
    this.qs1.cache();
    this.qs2.cache();
  },
  afterDeselect: function(){
    this.qs1.cache();
    this.qs2.cache();
  }
});
      </script>


<script type="text/javascript">
function group_them(){
    var partner_id=document.getElementById("partner").value; 
    var fname=document.getElementById("fname").value;
    var mname=document.getElementById("mname").value;
    var lname=document.getElementById("lname").value;
    var phone=document.getElementById("phone").value;
   
   
        
                    $.ajax({
                        url:"group_loader?partner_id="+partner_id+"&fname="+fname+"&mname="+mname+"&lname="+lname+"&phone="+phone,                         
                        type:'post',  
                        dataType: 'json',  
                    success: function(data) {
                      $('.selector').dialog('option', 'dataParser', data);  
                     //document.getElementById("groups").innerHTML="aaw=Solong";
                   //  alert(data);
                     
//                             var n = noty({text:data,
//                        layout: 'top',
//                        type: 'Success',
//                        timeout: 1800,
//        animation: {
//        open: {height: 'toggle'}, // jQuery animate function property object
//        close: {height: 'toggle'}, // jQuery animate function property object
//        easing: 'swing', // easing
//        speed: 500 // opening & closing animation speed
//    }            
//        }); 
                    }
                        
                         });
        
//    alert(partner_id);
       
}




</script> 

</head>
<body  onload="return group_them();">

<div id="container" >
<%@include file="/menu/clerkmenu.jsp"%>
<div id="header" align="center">
<br/>

</div>


<div id="content" style="height:750px;">


<div id="midcontent" style="margin-left:130px ;">
<h4 style="background-color: orange">Edit Current Facilitator.</h4><br>

<h2 style="background-color: greenyellow;">NB: Ensure you press the control button before selecting a new group. THIS is to avoid removing the facilitator from the current list of groups associated to.</h2>
<br>
<%
String facilitator_id,fname,mname,lname,phone,partner_name,groups,partner_id;
facilitator_id=request.getParameter("facilitator_id");
fname=request.getParameter("fname");
mname=request.getParameter("mname");
lname=request.getParameter("lname");
phone=request.getParameter("phone");
partner_name=request.getParameter("partner_name");
groups=request.getParameter("groups");
partner_id=request.getParameter("partner_id");
%>
 <form action="transfer_facilitator" name="" method="post">
    
    <br><br>

  First Name
  <br>
  <input type="text" id="fname" name="fname" value="<%=fname%>" class="textbox" readonly="true" style="background: #cccccc">
   <input type="hidden" name="facilitator_id" id="facilitator_id" value="<%=facilitator_id%>" class="textbox">
   <br><br>
    Middle Name
  <br>
 <input type="text" id="mname" name="mname" value="<%=mname%>" class="textbox" readonly="true" style="background: #cccccc">
<br><br>
Last Name
<br>
<input type="text" id="lname" name="lname" value="<%=lname%>" class="textbox" readonly="true" style="background: #cccccc">
      <br><br>
      Phone Number
  <br>
 <input type="text" id="phone" name="phone" value="<%=phone%>" class="textbox" readonly="true" style="background: #cccccc"> 
  <br><br>
Partner<font color="red">*</font>
<br>
<input type="text" name="part" id="part"value="<%=partner_name%>" class="textbox" readonly="true"style="background: #cccccc">&nbsp;
<input type="hidden" name="grp" id="grp"value="<%=groups%>" class="textbox" readonly="true"style="background: #cccccc"/>&nbsp;

<select name="partner" id="partner" class="textbox1" onchange="return group_them();">
    <option value="<%=partner_id%>"><%=partner_name%></option>
    <% String selector ="select * from partner where partner_id!='"+partner_id+"'";
    dbConn conn=new dbConn();
    conn.rs=conn.st.executeQuery(selector);

while(conn.rs.next()){
%>
<option value="<%=conn.rs.getString("partner_id")%>"><%=conn.rs.getString("partner_name")%></option>
<%}%>
</select>
<br><br>
Groups<font color="red">*</font>
<br>

<p style="background: #cccccc; width: 200px; height: 70px" class="textbox2" style=""><%=groups%></p>


    <br/>
   
			<div class="">
                            <dd>
    <select name="groups[]" title="." id="groups" class="multiselect" multiple="multiple"  style="height:140px;" required="true" >
<option value='elem_3'>we</option>
      <option value='elem_4'>elem 4</option>
</select> </dd>

	


    		</div>
			
	 
		
    </p>   
<input type="submit" name="sub" value="Save" class="textbox1" style="background: #cc99ff; color: #0000ff" >








       <br><br>
</form>   
</div>
</div>




</div>    


</body>
</html>

