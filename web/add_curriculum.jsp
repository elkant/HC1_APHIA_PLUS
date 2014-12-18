<%-- 
    Document   : add_curriculum
    Created on : Sep 8, 2013, 9:52:44 PM
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
<title>Add Curriculum.</title>
<script type="text/javascript">
  function nullchecker(){
  var curriculum1,curriculum2,curriculum3,curriculum4; 
  var session1,session2,session3,session4;
  curriculum1=document.getElementById('curriculum1').value;    
  curriculum2=document.getElementById("curriculum2").value;
  curriculum3=document.getElementById("curriculum3").value;
  curriculum4=document.getElementById("curriculum4").value;
  session1=document.getElementById("sessions1").value;
  session2=document.getElementById("sessions2").value;
  session3=document.getElementById("sessions3").value;
  session4=document.getElementById("sessions4").value;
if((curriculum1=="" && session1!="")|| (curriculum1!="" && session1=="")){
    alert("enter all data for row 1");
    return false;
}
if((curriculum2=="" && session2!="")|| (curriculum2!="" && session2=="")){
    alert("enter all data for row 2");
    return false;
}
if((curriculum3=="" && session3!="" )|| (curriculum3!="" && session3=="")){
    alert("enter all data for row 3");
    return false;
}
if((curriculum4=="" && session4!="") || (curriculum4!="" && session4=="")){
    alert("enter all data for row 4");
    return false;
}
//  alert(dist1);
  if(curriculum1=="" && curriculum2=="" && curriculum3=="" && curriculum4==""){  
      alert("Register Atleast one Curriculum.");
      return false;
  }
   else{
       
       
       return true;
   }   
      
      
  }  
</script> 
</head>
<body>

<div id="container" >
<%@include file="/menu/adminmenu.jsp"%>
<br/>
            <h3 style="text-align: center; background-color: orange;">ADDING CURRICULUM</h3>
<div id="header" align="center">
</div>
<div id="content" style="height:520px;">
<div id="midcontent" style="margin-left:130px ; width: 750px;">
    <br/><br/>
    
    <%if (session.getAttribute("added_curriculum") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("added_curriculum")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("added_curriculum");
                            }

                        %>
                        
                        
                         <%if (session.getAttribute("alread_exist") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("alread_exist")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("alread_exist");
                            }

                        %>
<h5>Enter Curriculum Names.</h5>
<br><br>

<form action="add_curriculum" name="form" onsubmit="return nullchecker();" method="post" >
    <br>
     <h4 style="color: #330033; height: 25px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; || &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Partner Name: <%=session.getAttribute("partner_name").toString()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ||&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Target Population: <%=session.getAttribute("target_pop_name").toString()%>  </h4>

       <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style">
        <tr><th>No.</th><th>Curriculum Name</th><th>No Of Lessons</th></tr>
           <tr>
              <td> 1</td>
               <td>
                   <input type="text" name="curriculum1"  value="" id="curriculum1" placeholder="Enter Curriculum Name" class="textbox" style="background: #6699ff"/></td>
            <td>
            <input type="text" name="sessions1" id="sessions1" pattern="[0-9]{1,2}" title="Enter numbers only" value="" placeholder="Enter number of sessions" class="textbox" style="background: #6699ff"/>    
        </td>
           </tr>
            <tr>
                 <td> 2</td>
               <td>
                   <input type="text" name="curriculum2"  value="" id="curriculum2" placeholder="Enter Curriculum Name" class="textbox" style="background: #6699ff"/></td>
            <td>
            <input type="text" name="sessions2" id="sessions2" value="" pattern="[0-9]{1,2}" title="Enter numbers only" placeholder="Enter number of sessions" class="textbox" style="background: #6699ff"/>    
        </td>
           </tr>
            <tr>
                 <td> 3</td>
               <td>
                   <input type="text" name="curriculum3"  value="" id="curriculum3" placeholder="Enter Curriculum Name" class="textbox" style="background: #6699ff"/></td>
            <td>
            <input type="text" name="sessions3" id="sessions3" value="" pattern="[0-9]{1,2}" title="Enter numbers only" placeholder="Enter number of sessions" class="textbox" style="background: #6699ff"/>    
        </td>
           </tr>
           
                      <tr>
                           <td> 4</td>
               <td>
                   <input type="text" name="curriculum4"  value="" id="curriculum4" placeholder="Enter Curriculum Name" class="textbox" style="background: #6699ff"/></td>
            <td>
                <input type="text" name="sessions4" id="sessions4" value="" pattern="[0-9]{1,2}" title="<font color='red' size='12'>Enter numbers only</font>" placeholder="Enter number of sessions" class="textbox" style="background: #6699ff"/>    
        </td>
           </tr>
  
           
                      <tr><td colspan="3"><input type="submit" name="sub" value="Add Curriculum" class="textbox1" style="background: #cc99ff; color: #0000ff"></td></tr>
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
               <p align="center"> &copy Aphia Plus | USAID <%=year%></p>
</div>
</div>    

</body>
</html>

