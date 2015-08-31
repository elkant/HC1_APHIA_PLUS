<%-- 
    Document   : student_reg2
    Created on : Aug 10, 2013, 11:35:53 AM
    Author     : Elkant
--%>
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
<%@page import="hc.*"%>
<%@page language="java"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="css/divCss.css"/>
        <link rel="shortcut icon" href="images/hc_logo.png"/>

        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

        <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->

        <script type="text/javascript" src="js/noty/themes/default.js"></script>

        <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
        <link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
        <script src="prefix-free.js"></script>
        
        
        <style>
    #content{
        
        font-family: cambria;
        font-size: 17px;
    }
    
</style> 
        
        <title>Add new Members</title>
               <script type="text/javascript" language="en">
                   
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}

var errors=[];

function validateAge(targetpop,age){
    
    if(targetpop=='FEMALES (15 to 24)'){
        
                    if((document.getElementById(age).value >=25||document.getElementById(age).value<=14)&&document.getElementById(age).value!=""){
                        
                        alert('Age of Partcipants for Females (15 to 24) should be between 15 to 24 years.');
                        $("#"+age).css({'background-color' : 'red'}); 
                        $("#"+age).focus(); 
                       errors.push(age);
                       // alert(errors.length);
                        // document.getElementById(age).select();
                    }
                    else {
                        
                        $("#"+age).css({'background-color' : 'white'}); 
                        
                        
                       if($.inArray(age, errors)>-1){
                           
                       var removeItem = age;

errors = jQuery.grep(errors, function(value) {
  return value != removeItem;
                         });  
                       } 
                    }
                    // alert(errors.length);
                   
        
    }
    
   
//check size now and show error if the errors array has elements


    
}


//-->
</script> 
        <script type="text/javascript">
            
            function vali(){
                var ret=true;
            
            for(var a=1;a<=parseInt(document.getElementById("noofrows").value);a++){
                
                if(newvali(a)==false){
                    ret=false;
                    break;
                    
                }
                else if(errors.length>0){
                    
                     ret=false;
                     
                      alert('The age fields marked in  red should be between 15 to 24 years.');
                      $("#"+errors[0]).focus(); 
                     
                    break;
                    
                }
                
                else {
                    
                    ret= true;
                }
            }
            return ret;
            }
            
            function newvali(id){
                var returned=true;
            var fname=document.getElementById("fname"+id);
            var mname=document.getElementById("mname"+id);
            var lname=document.getElementById("lname"+id);
            var sex=document.getElementById("sex"+id);
            var age=document.getElementById("age"+id); 
            
             if((fname.value!="" ||mname.value!="" ||lname.value!="" ||sex.value!="" ||age.value!="") && 
                    (fname.value=="" ||lname.value=="" ||sex.value=="" ||age.value=="")){
                    if(fname.value==""){
                        alert("Enter First name in row number " +id);
                        fname.focus();
                        returned= false;}
                   else if(lname.value==""){
                        alert("Enter last name in row number " +id);
                        lname.focus();
                        returned= false;}
                  else  if(sex.value==""){
                        alert("Select gender in row number " +id);
                        sex.focus();
                        returned= false;}
                   else if(age.value==""){
                        alert("Enter Age in row number " +id);
                        age.focus();
                        returned= false;}

                }
               
                return returned;
               
            }
            
            
            
        function isuseradded(){
            
            var ispartisadded=document.getElementById("ispartisadded").value;
            
            if(ispartisadded=="0"){
                
                var text="Proceed without adding members?";  
                try{
                    
                    addalert("Welcome")  ;
                    
                }   
                catch(err){
                    if(confirm(text)){
                        window.location.href="sessions_session_holder";
                        return true;
                    }
                    else{
                        return false;  
                    }
                }       
                
            }
            
            
        }
           
    
    function applydefault(id){
        
        //get all the options and define a default one
        var defsex="";
        
        defsex=$("#def").val();
       
        $("select").val(defsex);
       var allsexes = new Array();
//$('#sex1 option').each(function(){
//    allsexes.push(this.value);
//    
//    
//    
//});
        
        
    }
    
            
            //
        </script>
    </head>
    <body >     
        <%
            String class_name, school_name, district = "";

        //session.getAttribute("school");
        //session.getAttribute("clas");


        %>





        <div id="container" >
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
      
     <a href="FormWizard_members.jsp" style="margin-right: 250px;" class="linkstyle"> << Back</a>
 <%}%>
                
                ADDING PARTICIPANTS
            
                    <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     &nbsp; &nbsp; Page 5/7 &nbsp;<a onclick=" return isuseradded();" href="sessions_session_holder" style="margin-left: 250px;" class="linkstyle"> >> Next</a>
 <%}%>
            
            
            </h3>

            <div id="header" align="center">
                <br/>

                <%
    if (session.getAttribute("success") != null) {%>
                <script type="text/javascript"> 
                    
                                    var n = noty({text: '<%=session.getAttribute("success")%>',
                                        layout: 'center',
                                        type: 'Success',
 
                                        timeout: 4900});
                    
                </script> <%
                        session.removeAttribute("success");
                    }
                %>


                <%
                            if (session.getAttribute("failed") != null) {%>
                <script type="text/javascript"> 
                    
                                    var n = noty({text: '<%=session.getAttribute("failed")%>',
                                        layout: 'center',
                                        type: 'Success',
 
                                        timeout: 4900});
                    
                </script> <%
                        session.removeAttribute("failed");
                    }
                %>
<!--                <h4 style="text-align: center;">Participants Registration Page</h4> -->
                
                
            
                <!--Create unique userid codes-->




<br/>

            </div>


            <div id="content" style="height:750px;">


                <div id="midcontent" style="margin-left:80px ;">

                    
                        <% if (district.equals("")) {%>


                <form action="#" style="margin-left: -70px; width:1000px;">
                    <table cellpadding="2px" cellspacing="1px" border="0px" width="1000px">
                        <tr>
                            <td><font color="white"><b>District:</b></font> <%out.println(session.getAttribute("cc_district"));%></td>&nbsp;&nbsp;&nbsp;&nbsp;
                             <td><font color="white"><b>Target Population:</b></font> <%out.println(session.getAttribute("cc_target_pop"));%> </td>&nbsp;&nbsp;&nbsp;&nbsp; 
                            <td><font color="white"><b> Group Name:</b> </font><%out.println(session.getAttribute("cc_group_name"));%>. </td>
                            <td>
                                
                                <%if(session.getAttribute("prevpage")==null){%>
                                <a href="filter_member.jsp" title="Go Back" class="linkstyle">Change Group</a></d>
                               <%
 }                               
%>
                        </tr>   
                    </table>
                </form>
                <%}%>
                    
                    
                    
                    

                    <h4>
                        <font color="red">*</font> Indicates must fill/select. 

                    </h4>
                    <form action="members_reg" method="post" onsubmit="return vali()" style=" width: 1000px; margin-left: -70px;">
                        <table cellpadding="5px"  cellspacing="2px" id="memberstable" border="0px" width="1000px">
                   <%=session.getAttribute("memberscreatedtable")%>
                   
                   <%if(session.getAttribute("ispartisadded")!=null){ 
                       
                   }
else{

session.setAttribute("ispartisadded","0");
}
%> 


                   <input type="hidden" value="<%=session.getAttribute("ispartisadded")%>" id="ispartisadded" />
                            <input type="hidden" name="district" value='<%out.println(session.getAttribute("district"));%>'  placeholder="First Name"/>
                            <input type="hidden" name="target_pop" value='<%out.println(session.getAttribute("cc_target_pop"));%>'  placeholder="First Name"/>
                            <input type="hidden" name="group_name" value='<%out.println(session.getAttribute("cc_group_name"));%>'  placeholder="First Name"/>
                            <input type="hidden" name="group_id" value='<%out.println(session.getAttribute("c_group_name"));%>'  placeholder="First Name"/>
                           
                   
                        </table>
                    </form>
                </div>
            </div>



            <div id="footer">
                <%
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);

                %>
                <p align="center"></p>
            </div>
        </div>

    </body>
</html>
