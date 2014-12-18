<%-- 
    Document   : teacher
    Created on : Aug 6, 2013, 10:30:16 AM
    Author     : SIXTYFOURBIT
--%>
<%@page import="hc.dbConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
        <link rel="stylesheet" type="text/css" href="css/divCss.css"/>
        <link rel="shortcut icon" href="images/hc_logo.png"/>
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->
<script type="text/javascript" src="js/noty/themes/default.js"></script>


<!--menu-->
<link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
<link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
<script src="prefix-free.js"></script>


<!--calender-->

<link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>
	
	<script src="js/js/jquery-ui-1.10.3.custom.js"></script>
<link rel="stylesheet" href="js/demos.css" />

    <title>Participants Details</title>
    <script type="text/javascript">
function select_image(id){
    //alert(id);
 //alert(document.getElementById(id).value);
var cb=document.getElementById(id).value;
if(cb=="PREsent"||cb=="PRE_sent"){
 

document.getElementById(id).value="AB_sent";
//alert(""+document.getElementById(id).value);
}
else{
  document.getElementById(id).value="PRE_sent";
  // alert(""+document.getElementById(id).value);
}
}



    
       $(function() {
$( "#datepicker0" ).datepicker();
$( "#datepicker1" ).datepicker();
$( "#datepicker2" ).datepicker();
$( "#datepicker3" ).datepicker();
$( "#datepicker4" ).datepicker();
$( "#datepicker5" ).datepicker();
$( "#datepicker6" ).datepicker();
$( "#datepicker7" ).datepicker();
$( "#datepicker8" ).datepicker();
$( "#datepicker9" ).datepicker();
$( document ).tooltip();
$( "#accordion" ).accordion();

}); 

   function validatedates(){
        for(var a=0;a<=9;a++){
            
          if (compare(a)==false){
              
              break;
              
          }  
            
        }
        }
        
        
          function compare(suffix){
                var inputdate = new Date($("#datepicker"+suffix).val());

if(inputdate!=""){

                var dateOBj= new Date();

                var currdate=dateOBj.getDate();
                var curmonth=dateOBj.getMonth()+1;
                var curyear=dateOBj.getFullYear();
        
                var today=curyear+"/"+curmonth+"/"+currdate;    
                var todate = new Date(today); 

                //alert(todate+"  "+inputdate);

                if (inputdate > todate && inputdate!=null && inputdate!="")

                {

                    // Do something
                    alert("Date should not be greater than today ");
                    $("#datepicker"+suffix).focus();
                    return false;

                }
                
                }
        
            } 
        
        

</script>
        
        
    </head>
    <body  >
        <div id="container" style="height:100%;" >
     <%      if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}
    
      else{%>
       <%@include file="/menu/clerkmenu.jsp"%>
      <%}
   %>
   
     <h3 style="text-align: center; background-color: orange;">EDITING REGISTER</h3>
   
            <div id="header">
                <br/>
               
                
                
            </div>

            

            <div id="customcontent ">
                
<form action="#" method="POST" style="width:1170px;margin-left: 30px;" id="register">
   
 
  <%if (session.getAttribute("hc_register_updated") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("hc_register_updated")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 2800});
                    
                </script> <%
                session.removeAttribute("hc_register_updated");
                            }

                        %>
                        
                                                  <%if (session.getAttribute("respo") != null) { %>
                                <script type="text/javascript"> 
                    
                    var n = noty({text: '<%=session.getAttribute("respo")%>',
                        layout: 'center',
                        type: 'Success',
 
                         timeout: 2400});
                    
                </script> <%
                session.removeAttribute("respo");
                            }

    
    dbConn conn=new dbConn();
    
                        %>


    <table cellpadding="0px" cellspacing="0px" border="1px" style="width:1150px;" id="viewpdt">

<tr><th>Facilitator</th><th> Phone No.</th><th>District</th><th>Partner</th><th>Target<br/> Population</th><th>Group Name</th><th>Start date</th>
    <th>End date</th><th>Horizontal view</th></tr>
<tr><td><%=session.getAttribute("nt_facilitator_name")%></td>
<td><%=session.getAttribute("nt_facilitator_phone")%></td>
<td ><%=session.getAttribute("sc_district")%>
</td>
<td ><%
if(session.getAttribute("sc_partner")!=null){
conn.rs=conn.st.executeQuery("select * from partner where partner_id='"+session.getAttribute("sc_partner")+"'");
if(conn.rs.next()){
out.println(conn.rs.getString("partner_name"));

}
else{
out.println("null");
}
}
%></td>
<td><%=session.getAttribute("sc_target_pop")%></td>
<td ><%=session.getAttribute("sc_group_name")%></td>
<td><%=session.getAttribute("hc_start_date")%></td>
<td><%=session.getAttribute("hc_end_date")%></td>

<td><a href="filter_session.jsp" title="view another group" class="linkstyle" id="makechangebtn">next</a></td>
<td><a href="hc_loadAttendance2" title=" get a horizontal view of the members attendance register"><img src="images/horizontal.png"/></a></td>
</tr>
</table>
</form>
<br>
<form action="hc_loadAttendance" method="post" style="width:1170px;margin-left:30px;" align="center">
   
    <table ><tr><td>
                <input type="text" title="search member using first , middle or last name"  name="student" value="" required="true" class="textbox" placeholder="search by first,last or middle name">
           </td><td><input type="submit" value="search..." class="submit">
           </td>
           <td></td>
           <td></td>
           <td><a href="hc_loadAttendance" class="linkstyle">Refresh</a></td></tr>
   </table>
    
    
</form>

     <form action="hc_updateRegister" method="POST" style="width:1170px;margin-left: 30px;"> 
         
        <div style="width:1170px; overflow-x:auto; ">
                <table cellpadding="1px" cellspacing="1px" border="none" id="viewpdt" style="width:1150px;">
                    
                    
                    
                    
                    
                    
                     <tr><th rowspan="2">A</th><th rowspan="2" colspan="4">Session and Topic</th><%
int less=Integer.parseInt(session.getAttribute("hc_ssh_number_of_sessions").toString());

for (int w=1;w<=less;w++){       
%>
  <th>S<%out.println(w);%></th>           
          <% } %>
          
          </tr>
          <tr><%

      String topics="topic";        
         

String tpcs="select * from topics";
 conn.rs1=conn.st1.executeQuery(tpcs);
              
//for (int w=1;w<=less;w++){
         
%>
<c:forEach items="${sesion_AL}" var="time">
    <c:set var="sesscount" value="${time.sess_count}"></c:set>
     <c:set var="tname" value="topic${time.sess_count}"></c:set>
<c:set var="ctopic" value="${time.topic_id}"></c:set>
<td><Select name="${tname}" class="multiselect" multiple><%while(conn.rs1.next()){
%>


<option title="<%=conn.rs1.getString(2)%>" <%if(pageContext.getAttribute("ctopic").toString().contains(conn.rs1.getString(1))){%> selected  <%}%> value="<%=conn.rs1.getString(1)%>"><%=conn.rs1.getString(2)%></option>    
  <%  
}//end of while
conn.rs1=conn.st1.executeQuery(tpcs);
%></select></td>           
          <% //} %>
</c:forEach>
          </tr>
             
             
              <tr><th rowspan="1">B</th><th rowspan="1" colspan="4">Methods Used</th><%

      String meth="method";        
         

String methods="select * from teaching_method";
 conn.rs=conn.st.executeQuery(methods);
              
//for (int w=1;w<=less;w++){
         
%>
<c:forEach items="${sesion_AL}" var="time">
    <c:set var="sesscount" value="${time.sess_count}"></c:set>
     <c:set var="tname" value="method${time.sess_count}"></c:set>
<c:set var="cmeth" value="${time.method}"></c:set>
<td><Select name="${tname}" class="multiselect" multiple><%while(conn.rs.next()){
%>

<option value="<%=conn.rs.getString(1)%>" <%if(pageContext.getAttribute("cmeth").toString().contains(conn.rs.getString(1))){%> selected  <%}%> ><%=conn.rs.getString(1)+" "+conn.rs.getString(2)%></option>    
  <%  
}//end of while
conn.rs=conn.st.executeQuery(methods);
%></select></td>           
          <% //} %>
</c:forEach>
          </tr>
             
           
          
          
          
                     <tr><th rowspan="1">C</th><th rowspan="1" colspan="4">Date Of Session</th><%

      String datepicker="datepicker";        
         

              
//for (int w=1;w<=less;w++){
         
%>
<c:forEach items="${sesion_AL}" var="time">
    <c:set var="sesscount" value="${time.sess_count}"></c:set>
     <c:set var="dpname" value="datepicker${time.sess_count}"></c:set>
     <td><input type="text" title="${time.date}" value="${time.date}" required name="<%=pageContext.getAttribute("dpname")%>" id="<%=pageContext.getAttribute("dpname")%>" class="textbox"/>


    
  <%  


%></td>           
          <% //} %>
          </c:forEach>        
          </tr>
          
          </tr>
          
          
          
          
 <!--                                    TIME TAKEN IN MINUTES   -->         
          
         <tr><th rowspan="1">D</th><th rowspan="1" colspan="4">Time Taken In minutes</th><%

      String mins="time";        
         

              
//for (int w=1;w<=less;w++){
         
%>
<c:forEach items="${sesion_AL}" var="time">
    <c:set var="sesscount" value="${time.sess_count}"></c:set>
<td><input type="text" value="${time.time}" required name="<%=mins+""+pageContext.getAttribute("sesscount")%>" class="textbox"/>

<input type="hidden" value="${time.session_id}" required name="<%="regid"+pageContext.getAttribute("sesscount")%>" class="textbox"/>
    
  <%  


%></td>           
          <% //} %>
          </c:forEach>        
          </tr>  
          
          
       <!--                     No of male Condoms                 -->   
          
          
          
        <tr><th rowspan="1">E</th><th rowspan="1" colspan="4">Number of Male condoms distributed</th><%

      String malecondoms="malecondoms";        
         
//for (int w=1;w<=less;w++){
         
%>
<c:forEach items="${sesion_AL}" var="time">
    <c:set var="sesscount" value="${time.sess_count}"></c:set>
<td><input type="text" value="${time.mcondoms}" required name="<%=malecondoms+""+pageContext.getAttribute("sesscount")%>" class="textbox"/>


    
  <%  


%></td>           
          <% //} %>
          </c:forEach>        
          </tr> 
       
       
          
          
          <!--   number of female condoms-->
          
          
          
          
          
            <tr><th rowspan="1">F</th><th rowspan="1" colspan="4">Number of Female condoms distributed</th><%

      String femalecondoms="femalecondoms";        
         

//for (int w=1;w<=less;w++){
         
%>
<c:forEach items="${sesion_AL}" var="time">
    <c:set var="sesscount" value="${time.sess_count}"></c:set>
<td><input type="text" value="${time.fcondoms}" required name="<%=femalecondoms+""+pageContext.getAttribute("sesscount")%>" class="textbox"/>


    
  <%  


%></td>           
          <% //} %>
          </c:forEach>        
          </tr>
           <%int mycolspan=5+less;%>
           <tr><th colspan="<%=mycolspan%>" ><input type="submit" onmouseover="validatedates();"  title="saves all the changes in the entire page" class="submit" name="save" value="SAVE"/></th></tr>
  
                    
                </table>  




          <table cellpadding="1px" cellspacing="1px" border="none" id="viewpdt" style="width:1150px;">
                           
                    
                    
                    
                    
                    <tr> <th>First name</th><th>Middle Name</th><th>Last Name</th><th>Gender</th><th>Age</th> <th >status</th><th>image</th><th>Session</th></tr>
  
   <c:forEach items="${hc_attendanceBean}" var="student">
      
   <c:set var="count" value="${student.count}"></c:set>
   <c:set var="total_lessons" value="reg_no_of_lessons"></c:set>
 
   <c:set var="less_no" value="S${student.lesson_no}"></c:set>
   <c:set var="sid" value="${student.member_id}"></c:set>
   <c:set var="rid" value="${student.reg_id}"></c:set>
   <c:set var="avail" value="${student.availability}"/>
   <c:set var="present" value="images/present.png"></c:set>
    <c:set var="absent" value="images/absent.png"></c:set>
                                <tr>
                                    
                                    <td>
                                        ${student.f_name}
                                
                                    </td>
                                    <td>
                                            ${student.m_name}
                                      
                                    </td>
									<td>
                                           ${student.l_name}
                                      
                                    </td>
									<td>
                                           ${student.sex}
                                      
                                    </td>
									<td>
                                           ${student.age}
                                      
                                    </td>
					
			                             
                                    
         
         					
                                 
                           
                                    <!--
                                    
                                    -->
                              
                                         <%
                                   //receive the set no of lessons as per the chosen teacher
                                     
                                     dbConn con=new dbConn();
                                           
                                           
                                           %>
                                          
                                               
                                               
                                       <%
   

        String checkb="cb";    
         
String cbs="select * from attendance_signs";
 con.rs=con.st.executeQuery(cbs);
              

           
%><td>
<Select name="<%="cb"%><%=pageContext.getAttribute("count")%>" class="smallselect">
    <%while(con.rs.next()){
%>

<option value="<%=con.rs.getString(1)%>" <%if(pageContext.getAttribute("avail").equals(con.rs.getString(1))){%> selected<%}%>><%=con.rs.getString(2)%></option>    
  <%  
}//end of while
 con.rs=con.st.executeQuery(cbs);
%></select>  </td>           
          <%   //end of for %>
          
         
        
                                    
         <td><img src="${student.ipath}" alt="status"  /></td>                              
          <td>${less_no}<input type="hidden" value="<% out.println(""+pageContext.getAttribute("rid")); %>" name="<%="rid"%><%=pageContext.getAttribute("count")%>" />
          <input type="hidden" value="<% out.println(""+pageContext.getAttribute("sid")); %>" name="<%="memid"%><%=pageContext.getAttribute("count")%>" />
          </td>                          
                                    
   
    
   	
                                </tr><!--end of row-->
                          
                                
<%
int mcount=Integer.parseInt(""+pageContext.getAttribute("count"));



if(mcount%less==0&&mcount>=less){  %>
<tr ><td style="background:cornflowerblue;" colspan="8"><input type="submit" onmouseover="validatedates();" title="saves all the changes in the entire page" class="submit" name="save" value="SAVE"/></td></tr> 

<% 

//System.out.println("mcount: "+mcount+" less"+less);
} %>
                                
                           </c:forEach>      
                                <tr><th colspan="8" ></th></tr>
  
                </table>
        </div>  
</div>

         </div>

</form>
           

           
 </div>
            <div id="footer">
               
            </div>
        </div>
    </body>

</html>