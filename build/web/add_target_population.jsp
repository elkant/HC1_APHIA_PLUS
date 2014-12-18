<%-- 
    Document   : add_target_population
    Created on : Sep 5, 2013, 3:10:52 PM
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
<title>Add Target Population.</title>
<script>
    function nullchecker(){
      var age1,age2,age3,age4,age5,tg1,tg2,tg3,tg4,tg5;
      age1=document.getElementById("age1").value;
      age2=document.getElementById("age2").value;
      age3=document.getElementById("age3").value;
      age4=document.getElementById("age4").value;
      age5=document.getElementById("age5").value;
      tg1=document.getElementById("tg1").value;
      tg2=document.getElementById("tg2").value;
      tg3=document.getElementById("tg3").value;
      tg4=document.getElementById("tg4").value;
      tg5=document.getElementById("tg5").value;
      if(age1=="" && age2== "" && age3=="" && age4=="" && age5=="" && tg1=="" && tg2=="" && tg3=="" &&tg4=="" &&tg5==""){
         alert("Choose Age group to Register and Target population to Register."); 
         return false;
      }
     if((age1=="" && tg1!="") ||(age1!="" && tg1=="") ){
//      alert("Enter All Data in Row 1");
      if(age1==""){
     alert("Select Age Group in Row 1");}
        if(tg1==""){
      alert("Enter Target Population in Row 1");}
      return false;   
     }
          if((age2=="" && tg2!="") ||(age2!="" && tg2=="") ){
         if(age2==""){
     alert("Select Age Group in Row 2");}
        if(tg2==""){
      alert("Enter Target Population in Row 2");}     
      return false;   
     }
                  if((age3=="" && tg3!="") ||(age3!="" && tg3=="") ){
            if(age3==""){
     alert("Select Age Group in Row 3");}
        if(tg3==""){
      alert("Enter Target Population in Row 3");}     
      return false;   
     }
               if((age4=="" && tg4!="") ||(age4!="" && tg4=="") ){
            if(age4==""){
     alert("Select Age Group in Row 4");}
        if(tg4==""){
      alert("Enter Target Population in Row 4");}      
      return false;   
     }
               if((age5=="" && tg5!="") ||(age5!="" && tg5=="") ){
           if(age5==""){
     alert("Select Age Group in Row 5");}
        if(tg5==""){
      alert("Enter Target Population in Row 5");}      
      return false;   
     }
        
    }
</script>
</head>
<body>
<%if (session.getAttribute("target_pop") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("target_pop")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("target_pop");
                            }

                        %>
<div id="container" style="height:770px;">
 <%   if (session.getAttribute("userid").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>
            <br/>
            <h3 style="text-align: center; background-color: orange;">ADDING TARGET POPULATION</h3>
<div id="header" align="center">
</div>
<div id="content" style="height:750px;">
<div id="midcontent" style="margin-left:130px ; width: 750px;">
    <br/>
<h5>Enter Target Population.</h5>
<br><br>
<form action="add_target_population" name="form" onsubmit="return nullchecker();" method="post" >
    <br>
     <h4 style="color: #330033; height: 25px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Partner Name: <%=session.getAttribute("partner_name").toString()%>  </h4>
    
       <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style">
           <tr><th style="width: 15px">Row</th>
               <th>Select Age Group</th>  
               <th> Enter Target Population Name</th>   
           </tr>
           <tr>
               <td>1</td>
               <td>
                   <select name="age1" id="age1" class="textbox4" multiple style="background: #6699ff;"> 
                      
                       <option value="1">10-14 </option>
                       <option value="2">15-19 </option>
                       <option value="3">20-24 </option>
                       <option value="4">25+ </option>
                       
                       
                </select>
               </td>
               <td>
            <input type="text" name="tg1" id="tg1" value="" placeholder="Enter Target Pop Name" class="textbox" style="background: #6699ff"/>    
    </td></tr>
    <tr></tr>
                       <tr> <td>2</td>
                           <td>
                    <select name="age2" id="age2" class="textbox4" multiple style="background: #6699ff"> 
                       
                       <option value="1">10-14 </option>
                       <option value="2">15-19 </option>
                       <option value="3">20-24 </option>
                       <option value="4">25+ </option>
                       
                       
                </select>
                           </td>
                           <td>
            <input type="text" name="tg2" id="tg2" value="" placeholder="Enter Target Pop Name" class="textbox" style="background: #6699ff"/>    
    </td></tr>
            <tr></tr>    
                      <tr><td>3</td>
                          <td>
  <select name="age3" id="age3" multiple class="textbox4" style="background: #6699ff"> 
                      
                       <option value="1">10-14 </option>
                       <option value="2">15-19 </option>
                       <option value="3">20-24 </option>
                       <option value="4">25+ </option>
                       
                       
                </select>
                          </td>
                          
                          <td>
            <input type="text" name="tg3" id="tg3" value="" placeholder="Enter Target Pop Name" class="textbox" style="background: #6699ff"/>    
    </td></tr>
                                 <tr><td>4</td>
  <td> <select name="age4" id="age4" multiple class="textbox4" style="background: #6699ff"> 
                 
                       <option value="1">10-14 </option>
                       <option value="2">15-19 </option>
                       <option value="3">20-24 </option>
                       <option value="4">25+ </option>
                       
                       
                </select> </td>
                                     <td>
            <input type="text" name="tg4" id="tg4" value="" placeholder="Enter Target Pop Name" class="textbox" style="background: #6699ff"/>    
    </td></tr>
                <tr><td>5</td><td> <select name="age5" id="age5" class="textbox4" multiple style="background: #6699ff"> 
                   
                       <option value="1">10-14 </option>
                       <option value="2">15-19 </option>
                       <option value="3">20-24 </option>
                       <option value="4">25+ </option>
                       
                       
                </select></td>
                    <td>
            <input type="text" name="tg5" id="tg5" value="" placeholder="Enter Target Pop Name" class="textbox" style="background: #6699ff"/>    
    </td></tr>
                      <tr><td colspan="3"><input type="submit" name="sub" value="Add Target Population" class="textbox" style="background: #cc99ff; color: #0000ff"></td></tr>
</table>
       <br/>
        <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
       <br>
</form>
</div>
</div>


<div id="footer" style="">

</div>
</div>    

</body>
</html>


