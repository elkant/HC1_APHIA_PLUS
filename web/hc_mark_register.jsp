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
        <link rel="shortcut icon" href="images/hc_logo.png"/>


        <!menu css>
    <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
    <link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
    <script src="prefix-free.js"></script>

    <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <!--calender-->

    <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>	
    <script src="js/js/jquery-ui-1.10.3.custom.js"></script>

    <%int formers = 0;%>         
    <script type="text/javascript" src="js/noty/jquery.noty.js"></script>
    <script type="text/javascript" src="js/noty/layouts/top.js"></script>
    <script type="text/javascript" src="js/noty/layouts/center.js"></script>
    <!-- You can add more layouts if you want -->
    <script type="text/javascript" src="js/noty/themes/default.js"></script>
    <script type="text/javascript">  
        
        
     
        
        
        function forms_calc(nos2){
                    //if(nos2!=""){
                    if(nos2=="1"){
                       
                   var    nos=nos2;
                   }
                    else {
            var nos=nos2.value;
                         }
            //alert(nos);
                   // }
                   
                   
                    var mymax=5;
                    //alert(nos);
            //document.getElementById("id_checker").value=nos;
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
                    document.getElementById("all").innerHTML=xmlhttp.responseText;
    
                }
            }
            
            
            
           
            xmlhttp.open("POST","forms_calculator?nos="+nos,true);
            xmlhttp.send();
           
            //filter_nhfs(dist);
        }
        
        
        
        
        
        
        
        
    </script>

    
    
       <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57)){
return false;
}

else{
 


 
return true;
}
}
//-->
</script> 

    <title>mark Register (7/7)</title>
   
    
    <script type="text/javascript">

        
           function setcolor (val,id) {
               
               
               
           //alert("cb"+id);
           //alert("value "+val.value);
           
          if(val.value==1)     
               {
                   if(document.getElementById("cb"+id)!=null){
                   document.getElementById("cb"+id).color = "green";
                   }
               }
               else{
                  if(document.getElementById("cb"+id)!=null){ 
                   document.getElementById("cb"+id).style.Color ="red";   
                     
                  }
               }
           }
    
        function validatedates(){
        for(var a=0;a<=11;a++){
            
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

    <script type="text/javascript">
       
       //forms_calc('1');
       
    </script>
</head>
<body onload="forms_calc('1');">
    <div id="container" style="height:100%;" >
     <%      if(session.getAttribute("level").equals("0")){ %>
   <%@include file="/menu/adminmenu.jsp" %>
 <%}
    
      else{%>
       <%@include file="/menu/clerkmenu.jsp"%>
      <%}
   %>
  
             <h3 style="text-align: center; background-color: orange;">
                 
                  <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     <a href="topic_start_end_date.jsp" style="margin-left: -400px;margin-right: 250px;" class="linkstyle"> << Back</a>
 <%}%>
                 
                 MARKING REGISTER
             
                   <%if(session.getAttribute("prevpage")!=null&&session.getAttribute("prevpage").equals("FormWizard.jsp")){%>
      
     &nbsp; &nbsp; Page 7/7 &nbsp;
 <%}%>
             
             </h3>
        <div id="header">
            <br/>


            <%
          //System.out.println("session:"+session.getAttribute("hc_no_students"));  

                if (session.getAttribute("hc_no_students") != null) {%>
            <script type="text/javascript"> 
                    
                                    var n = noty({text: '<%=session.getAttribute("hc_no_students")%>',
                                        layout: 'center',
                                        type: 'Success', 
                                        timeout: 3800});
                    
            </script> <%
//                System.out.println("session:"+session.getAttribute("hc_no_students")); 
                    session.removeAttribute("hc_no_students");
                }

            %>


        </div>



    

            <form action="#" method="POST" style="width:1150px;margin-left: 15px;" id="register">





                <%
                //get the  topics      
                    dbConn conn = new dbConn();



                    String topics = session.getAttribute("hc_all_topic_ids").toString();
                //System.out.println(""+topics);
                    ArrayList all_joined_topic_names_AL = new ArrayList();


                    if (all_joined_topic_names_AL.size() > 0) {

                        all_joined_topic_names_AL.clear();
                    }

                    String all_joined_topic_names_array[], all_joined_topic_names = "";
                    String alltopicids[] = topics.split("_");


                    //set to session the topic ids    
                    session.setAttribute("hc_mr_topic_ids_arraylist", alltopicids);
                    //for loop to differentiate lessons taught at different sessions.

                    for (int a = 0; a < alltopicids.length; a++) {

                        String[] currenttids = alltopicids[a].split(",");
                        //for loop to get all topic names taught at each session
                        //sessions
                        //inner for loop
                        String topicnames_per_session = "";
                        for (int p = 0; p < currenttids.length; p++) {

                            if (!currenttids[p].equals("")) {

                                //System.out.println(""+currenttids[p]);  
                                String gettopic_names = "select * from topics where topic_id='" + currenttids[p] + "'";

                                conn.rs4 = conn.st4.executeQuery(gettopic_names);
                                conn.rs4.next();
                                topicnames_per_session = topicnames_per_session + conn.rs4.getString(2) + "<font color=\"blue\"><b>,</b></font><br/>";

                            }//end of if
                            //add the topic name to an arraylist

                        }//end of inner for loop

                        // System.out.println(""+currenttids[p]);                 
                        all_joined_topic_names_AL.add(topicnames_per_session);
                        topicnames_per_session = "";

                        // all_joined_topic_names=all_joined_topic_names+topicnames_per_session+"_";

                    }//end of outer for loop

                %>






                <table cellpadding="0px" cellspacing="0px" border="1px" style="width:1120px;" id="viewpdt">

                    <tr><th>Facilitator</th><th> Phone No.</th><th>District</th><th>Partner</th><th>Target<br/> Population</th><th>Group Name</th><th>Start date</th>
                        <th>End date</th><th>Curriculum</th></tr>
                    <tr><td><%=session.getAttribute("nt_facilitator_name")%></td>
                        <td><%=session.getAttribute("nt_facilitator_phone")%></td>
                        <td ><%=session.getAttribute("sc_district")%>
                        </td>
                        <td ><%=session.getAttribute("sc1_partner")%></td>
                        <td><%=session.getAttribute("sc_target_pop")%></td>
                        <td ><%=session.getAttribute("sc_group_name")%></td>
                        <td><%=session.getAttribute("hc_start_date")%></td>
                        <td><%=session.getAttribute("hc_end_date")%></td>
                        <td><%=session.getAttribute("hc_curriculum")%></td>
                    </tr>
                </table>
            </form>















            <form action="hc_save_register" method="POST" style="margin-left: 15px;width:1150px;">  

                <div style="width:1150px; overflow-x:auto; ">   
                    <table cellpadding="2px" cellspacing="2px" border="0"  id="viewpdt" style="width:1120px;">


                        <!--Form number-->

                        <tr><th ></th><th colspan="4">FORM NUMBER:<br><br> <input type="hidden" maxlength="1" value="" name="form_numbers" id="nos" placeholder="Enter Number of forms"  class="textbox" style="width: auto" onkeypress="return numbers(event,this);" onkeyup="forms_calc(this);" pattern="[0-9]{1,2}" title="If you are marking data for more than one form, enter the number of forms here." required/> </th><td colspan="<% out.println(session.getAttribute("hc_e_lessons"));%>" id="all">
                            </td></tr>




                        <tr><th rowspan="2">A</th><th rowspan="2" colspan="4">Session and Topic</th><%
                            int ses = Integer.parseInt("" + session.getAttribute("hc_e_lessons"));

                            for (int w = 1; w <= ses; w++) {
                            %>
                            <th>S<%out.println(w);%></th>           
                            <% }%>

                        </tr>
                        <tr>
                            <%

                                for (int w = 0; w < ses; w++) {

                            %> 

                            <td><%=all_joined_topic_names_AL.get(w)%></td>

                            <%}
               //set the all topics arrylist to a session
               //session.setAttribute("",all_joined_topic_names_AL);

                            %>
                        </tr>


                        <tr><th rowspan="1">B</th><th rowspan="1" colspan="4">Methods Used <font color="red"><b>*</b></font></th><%

                            String meth = "method";


                            String methods = "select * from teaching_method";
                            conn.rs = conn.st.executeQuery(methods);

                            for (int w = 1; w <= ses; w++) {

                                %>
                            <td><Select name="<%=meth + "" + w%>" multiple class="multiselect"><%while (conn.rs.next()) {
                                    %>

                                    <option title="<%=conn.rs.getString(2)%>" value="<%=conn.rs.getString(1)%>" <%if (conn.rs.getString(1).equals("6")) {%>selected<%}%>><%=conn.rs.getString(1) + " " + conn.rs.getString(2)%></option>    
                                    <%
                                        }//end of while
                                        conn.rs = conn.st.executeQuery(methods);
                                    %></select></td>           
                                    <% }%>

                        </tr>



                        <tr><th rowspan="1">C</th><th rowspan="1" colspan="4">Date Of Session<font color="red"><b>*</b></font></th><%

                            String datepicker = "datepicker";



                            for (int w = 1; w <= ses; w++) {

                                %>
                            <td style="padding:1px;"><input type="text" required placeholder="mm/dd/yyyy" name="<%=datepicker + "" + w%>" id="<%=datepicker + "" + w%>"class="textbox"/>



                                <%
                              //end of while

                                %></td>           
                                <% }%>

                        </tr>




                        <!--                                    TIME TAKEN IN MINUTES   -->         

                        <tr><th rowspan="1">D</th><th rowspan="1" colspan="4">Time Taken ( minutes )<font color="red"><b>*</b></font></th><%

                            String mins = "time";



                            for (int w = 1; w <= ses; w++) {

                                %>
                            <td><input type="text" required name="<%=mins + "" + w%>" id="<%=mins + "" + w%>"class="textbox"/>



                                <%


                                %></td>           
                                <% }%>

                        </tr>  


                        <!--                     No of male Condoms                 -->   



                        <tr><th rowspan="1">E</th><th rowspan="1" colspan="4">Number of Male condoms distributed</th><%

                            String malecondoms = "malecondoms";



                            for (int w = 1; w <= ses; w++) {

                            %>
                            <td><input type="text" name="<%=malecondoms + "" + w%>" id="<%=malecondoms + "" + w%>"class="textbox"/>



                                <%


                                %></td>           
                                <% }%>

                        </tr>  




                        <!--   number of female condoms-->





                        <tr><th rowspan="1">F</th><th rowspan="1" colspan="4">Number of Female condoms distributed</th><%

                            String femalecondoms = "femalecondoms";



                            for (int w = 1; w <= ses; w++) {

                            %>
                            <td><input type="text" name="<%=femalecondoms + "" + w%>" id="<%=femalecondoms + "" + w%>"class="textbox"/>



                                <%


                                %></td>           
                                <% }%>

                        </tr>  



                        <tr> <th style="background-color:yellow;">First<br/> name</th><th style="background-color:yellow;">Middle <br/>Name</th><th style="background-color:yellow;">Last <br/>Name</th><th style="background-color:yellow;">Age</th><th style="background-color:yellow;">Sex</th><th style="background-color:yellow;" colspan="<% out.println(session.getAttribute("hc_e_lessons"));%>">Availability<font color="green">     (choose member status by clicking the drop down)</font><br/><font color="black">NB: The total sessions for each participant is auto calculated and saved by the system</font></th></tr>

                        <c:forEach items="${allpartisAL}" var="student">

                            <c:set var="count" value="${student.count}"></c:set>
                            <c:set var="sid" value="${student.userid}"></c:set>

                                <tr>

                                    <td>
                                    ${student.fname}

                                </td>
                                <td>
                                    ${student.mname}

                                </td>
                                <td>
                                    ${student.lname}

                                </td>
                                <td>
                                    ${student.age}

                                </td>
                                <td>

                                    ${student.sex}
                                </td>







                                <!--
                                
                                -->

                                <%
                                    //receive the set no of lessons as per the chosen teacher
                                    int less = Integer.parseInt(session.getAttribute("hc_e_lessons").toString());




                                %>



                                <%

                                    String checkb = "cb";

                                    String cbs = "select * from attendance_signs ORDER BY sign_id ASC";
                                    conn.rs = conn.st.executeQuery(cbs);

                                    for (int w = 1; w <= ses; w++) {

                                %><td>
                                    <Select  name="<%="cb"%><%=w%><%=pageContext.getAttribute("count")%>" onchange="setcolor (this,'<%=w%><%=pageContext.getAttribute("count")%>');" required class="smallselect">
                                        <%while (conn.rs.next()) {
                                        %>

                                      <%if (conn.rs.getString(1).equals("1")) {%>  <option  style="color:green;" value="<%=conn.rs.getString(1)%>" ><%=conn.rs.getString(2)%></option>   <%}%> 
                                      <%if (conn.rs.getString(1).equals("2")) {%>  <option  style="color:orange;" value="<%=conn.rs.getString(1)%>" ><%=conn.rs.getString(2)%></option>   <%}%> 
                                       
                                      <%
                                            }//end of while
                                            conn.rs = conn.st.executeQuery(cbs);
                                        %></select>  </td>           
                                        <%   }//end of for %>



                                <td> 

                                    <input type="hidden" value="<%=pageContext.getAttribute("sid")%>" name="<%="sid"%><%=pageContext.getAttribute("count")%>" />         </td>                                 













                            </tr><!--end of row-->

                        </c:forEach>  

                        <tr><th>Reviewed By:</th><td><input type="text" name="reviewer"  /></td>
                            <th>date of Submission</th><td><input  type="text" name="submission_date" placeholder="mm/dd/yyyy" class="textbox" required id="datepicker11"/></td></tr>



                        <%int mycolspan = 5 + Integer.parseInt(session.getAttribute("hc_e_lessons").toString());%>
                        <tr><th colspan="<%=mycolspan%>" >
                                <input type="submit" onmouseover="validatedates();" title="saves all the changes in the entire page" style="height:35px;width:100px;background: orange;" name="save" value="SAVE"/></th></tr>

                    </table>

                </div>

            </form>
       



        <div id="footer">

        </div>
    </div>
                                
                                
                                <script>
                                    
                                    
                                        
        $(function() {
            
               
         var mindate="";
    
    
    
    $.ajax({
                        url:'loadMinDate',                         
                        type:'post',  
                        dataType: 'html',  
                        success: function(data) {
                            
                            mindate=data;
                        }
                        
                         });
            
            $( "#datepicker0" ).datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker1" ).datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker2" ).datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker3" ).datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker4" ).datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker5" ).datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker6" ).datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker7" ).datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker8" ).datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker9" ).datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker10").datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $( "#datepicker11").datepicker({changeMonth: true,changeYear: true,minDate:mindate, maxDate: new Date()});
            $(document).tooltip();
            
            
         
            
        });  
         
                                    
                                </script>
</body>

</html>
