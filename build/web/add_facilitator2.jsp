<%-- 
    Document   : add_facilitator2.jsp
    Created on : Sep 6, 2013, 11:52:57 AM
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

        <title>Add Facilitator Details.</title>
        <script type="text/javascript">
            
             function vali(){
                 
            //alert("called");
            for(var a=1;a<=parseInt(document.getElementById("no_of_facils").value);a++){
                
                if(newvali(a)==false){
                   
                    break;
                   
                }
                
                
            }
            
            // phonenumber(); 
            
            validat();
            }
            
            function newvali(id){
                
            var group=document.getElementById("groups"+id);
            var fname=document.getElementById("fname"+id);
            var mname=document.getElementById("mname"+id);
            var lname=document.getElementById("lname"+id);
            var phone=document.getElementById("phone"+id);
          
            
             if((fname.value!="" ||mname.value!="" ||lname.value!="" ||phone.value!="" ||group.value!="") && 
                    (fname.value=="" ||lname.value=="" ||phone.value=="" ||group.value=="")){
                    if(fname.value==""){
                        alert("Enter First name in row number " +id);
                        fname.focus();
                        return false;}
                    if(lname.value==""){
                        alert("Enter last name in row number " +id);
                        lname.focus();
                        return false;}
                    if(phone.value==""){
                        //alert("Enter phonenumber in row number " +id);
                        phone.focus();
                        }
                    if(group.value==""){
                        alert("Select group in row number " +id);
                        group.focus();
                        return false;}

                }
               
                
               
            }
            
            
           
        </script> 
        <script>
            function validat(){
      
      //alert(document.getElementById("no_of_groups").value);
        var rows=parseInt(document.getElementById("no_of_facils").value);
        
        var isblank=0;
         // alert("rows"+rows);
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
               document.getElementById("fname1").focus
                return false; 
                 }
             }   
            else{
                
                return true;
                
            }
            
            
            
        }
        
        
    }
    
    
    
    function avoidblanks(id){
        
          var group=document.getElementById("groups"+id).value;
            var fname=document.getElementById("fname"+id).value;
            var mname=document.getElementById("mname"+id).value;
            var lname=document.getElementById("lname"+id).value;
            var phone=document.getElementById("phone"+id).value;
          
        var total=0;
        if(group.trim()!=""||fname.trim()!=""||mname.trim()!=""||lname.trim()!=""||phone.trim()!="" ){
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
        
        <script type="text/javascript">
                function phonenumber() {
  
} 



    function isfacilsadded(){
       
            
            var isfacilsadded=document.getElementById("isfacilsadded").value;
            
            if(isfacilsadded=="0"){
                
                var text="Proceed without adding any Facilitators?";  
                try{
                    
                    addalert("Welcome")  ;
                    
                }   
                catch(err){
                    if(confirm(text)){
                        window.location.href="FormWizard_members";
                        return true;
                    }
                    else{
                        return false;  
                    }
                }       
                
            }
            
            
        }



        </script>
        
                 <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>  

    </head>
    <body onload="">

        <div id="container" style="font-size: 17px;height:1100px;overflow-y:auto;" >
             <%      if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}
    
      else{%>
       <%@include file="/menu/clerkmenu.jsp"%>
      <%}
   %>
       



            <br/>

            <h3 style="text-align: center; background-color: orange;">
                
                	      <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     <a href="FormWizard_facils.jsp" style="margin-right: 250px;" style="margin-right:250px;" class="linkstyle"> << Back</a>
 <%}%>
                
                ADDING FACILITATORS
            
            
            <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
            &nbsp; &nbsp; Page 3/7 &nbsp;<a onclick=" return isfacilsadded();" href="FormWizard_members.jsp" style="margin-left: 250px;" class="linkstyle"> >> Next</a>
 <%}%>
            
            
            </h3>


            <div id="header" align="center">
            </div>
            <div id="content" style="height:950px;">
                <div id="midcontent" style="margin-left:50px ; width: 930px; ">
                    <br/><br/>

                    <%if (session.getAttribute("success") != null) {%>
                    <script type="text/javascript"> 
                    
                                        var n = noty({text: '<%=session.getAttribute("success")%>',
                                            layout: 'center',
                                            type: 'Success',
 
                                            timeout: 4800});
                    
                    </script> <%
                            session.removeAttribute("success");
                        }

                    %>
                    <%if (session.getAttribute("fail") != null) {%>
                    <script type="text/javascript"> 
                    
                                        var n = noty({text: '<%=session.getAttribute("fail")%>.<br>Click here to close notice.',
                                            layout: 'center',
                                            type: 'Success'});
                    
                    </script> <%
                            session.removeAttribute("fail");
                        }

                    %>

                    <h4>Add Facilitator Details.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4>
                    <div id="dialog" title="Add Facilitator Help." style=" font-size: 15px; font-family: sans-serif;">
                        <%  String message1 = "";
                            dbConn conn = new dbConn();

                            String message_selector = "SELECT * FROM help WHERE help_id='6'";
                            conn.rs = conn.st.executeQuery(message_selector);
                            conn.rs.next();
                            message1 = conn.rs.getString(3);
                            out.println(message1);
                        %>
                    </div>

                    <br><br>
                    <form action="add_facilitator" name="form"  method="post" >
                        <br>

                      <h4 style="color: #330033; height: 25px;">District Name: <%=session.getAttribute("district_name").toString()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; || &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Partner Name: <%=session.getAttribute("partner_name").toString()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ||&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Target Population: <%=session.getAttribute("target_pop_name").toString()%>  </h4>
   
                        <table cellpadding="2px" cellspacing="3px" border="1px" class="table_style">
                            <tr> 
                                <th>Row:</th>
                                <th >Select Group(s):</th>
                                <th>First Name:</th>
                                <th>Middle Name:</th>
                                <th>Last Name:</th>
                                <th>Phone No:</th>

                            </tr> 
                              

                    <%=session.getAttribute("createdfacilitatorstable").toString()%>
                            
                          
                            <tr>
                                <td colspan="6">   
                                    <input type="submit" value="Save and Continue" class="" onmouseover="return vali(); " style="height:50px;width:130px;background-color: orange;" /> 
                                </td>  
                            </tr>

                            

  
                            
                            
                        </table>
                    
                    <%if(session.getAttribute("isfacilsadded")!=null){



}
                                   else{
               
               session.setAttribute("isfacilsadded","0");
               }
%>                    
                    
                          <input type="hidden" value="<%=session.getAttribute("isfacilsadded")%>" id="isfacilsadded" />
                    
                        <br><br>
                    </form>
                </div>
            </div>


            <div id="footer">
                <%
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);

                %>
                <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
            </div>
        </div>    

    </body>
</html>