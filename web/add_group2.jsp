<%-- 
    Document   : add_group2
    Created on : Sep 8, 2013, 6:15:26 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="hc.dbConn"%>
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
                    

<title>Add Groups.</title>
<script type="text/javascript">
    
    function validat(){
      
      //alert(document.getElementById("no_of_groups").value);
        var rows=parseInt(document.getElementById("no_of_groups").value);
        
        var isblank=0;
        //  alert("rows"+rows);
        for(var a=1;a<=rows;a++ ){
            
          //  alert(a);
            if(avoidblanks(a)==1){
              isblank=1;  
            }
            else{
                
            }
             if(avoidblanks(a)==0&&isblank==0){
                 if(a==rows){
               alert("Enter atleast one group Name"); 
               document.getElementById("grp1").focus
                return false; 
                 }
             }   
            else{
                
                return true;
                
            }
            
            
            
        }
        
        
    }
    
    
    
    function avoidblanks(id){
        
        var group= document.getElementById("grp"+id).value;
        var total=0;
        if(group.trim()!=""&&group!=null ){
       total=1;
      //alert("true"); 
       return 1;
        }
        else{
          //  alert("false");
            return 0;
        }
        
    }
    
    
 
</script> 
</head>
<body>

<div id="container" style=" font-size: 17px;">
 <%   if(session.getAttribute("level").equals("2")){ 
            %>
<%@include file="/menu/clerkmenu.jsp"%>
<%} else{%>
<%@include file="/menu/adminmenu.jsp"%>
<%}%>
<div id="header" align="center">
</div>
<br/>
 <h3 style="text-align: center; background-color: orange;"> 
     
       <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     <a href="FormWizard.jsp" style="margin-right: 250px;" class="linkstyle"> << Back</a>
 <%}%>
 
     
     
     <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style="width: 24px; "> ADDING GROUPS 
 
     <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     &nbsp; &nbsp; Page 2/8 &nbsp;<a href="FormWizard_facils.jsp" style="margin-left: 250px;" class="linkstyle"> >> Next</a>
 <%}%>
 </h3>


<div id="content" style="height:520px;">
<div id="midcontent" style="margin-left:130px ; width: 750px;">
    <br/><br/>
    
    <%if (session.getAttribute("added_groups") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("added_groups")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("added_groups");
                            }

                        %>
                        
                         <%if (session.getAttribute("existing_groups") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("existing_groups")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 3800});
                    
                </script> <%
                session.removeAttribute("existing_groups");
                            }

                        %>
                        
<h4>
     <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     If the group  you want to mark its attendance is already entered into the system, click Next.
 <%}%>
    
 </h4>
                    <div id="dialog" title="Add Groups Help." style=" font-size: 13px;">
<%  String message1="";
 dbConn conn= new dbConn();
 
String message_selector="SELECT * FROM help WHERE help_id='2'";
conn.rs=conn.st.executeQuery(message_selector);
conn.rs.next();
message1=conn.rs.getString(3);
out.println(message1);
%>
</div>
<br><br>
<form action="add_group" name="form" onsubmit="return validat();"  method="post" style="width: 800px;" >
    <br>
   <h4 style="color: #330033; height: 25px;">District Name: <%=session.getAttribute("district_name").toString()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; || &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Partner Name: <%=session.getAttribute("partner_name").toString()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ||&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Target Population: <%=session.getAttribute("target_pop_name").toString()%>  </h4>
     
       <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style"  style="background: #000000; width:780px;">
          <%=session.getAttribute("created_groups").toString()%>
       </table>
       <br><br>
       <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
  
 <%}%>
</form>

<br/>
       <div id="footer" style="">
 <%
Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              

%>
               <p align="center"> HC1 &copy Aphia Plus | USAID <%=year%></p>
</div>
</div>
       
</div>



</div>    

</body>
</html>
