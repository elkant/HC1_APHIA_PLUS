<%-- 
    Document   : new_topic
    Created on : Aug 14, 2013, 8:00:26 AM
    Author     : Nyabuto Geofrey
--%>
<%@page import="hc.dbConn"%>
<%@page import="java.util.Calendar"%>
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
        <link rel="stylesheet" type="text/css" href="css/divCss.css"/>
        <link rel="shortcut icon" href="images/hc_logo.png"/>
        <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
        <link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
        <script src="prefix-free.js"></script>
        <title>Register a New Topic (6/7)

        </title>
        <!--         <link rel="stylesheet" type="text/css" href="js/jquery-ui.css"/>-->
       <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

<script type="text/javascript" src="js/noty/jquery.noty.js"></script>

<script type="text/javascript" src="js/noty/layouts/top.js"></script>
<script type="text/javascript" src="js/noty/layouts/center.js"></script>
<!-- You can add more layouts if you want -->

<script type="text/javascript" src="js/noty/themes/default.js"></script>
        <!--calender-->

        <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>	
        <script src="js/js/jquery-ui-1.10.3.custom.js"></script>
        
        <script type="text/javascript" src="js/js/sisyphus.min.js"></script>
          
        
        <script type="text/javascript">
    
    
    var mindate="";
    
    
    
    $.ajax({
                        url:'loadMinDate',                         
                        type:'post',  
                        dataType: 'html',  
                        success: function(data) {
                            
                            mindate=data;
                        }
                        
                         });
    
    
            function getfacil(){
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
                        document.getElementById("facil").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST","load_facils",true);
                xmlhttp.send();
            } 

            function getcofacil(){
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
                        document.getElementById("co_facil").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST","load_facils2",true);
                xmlhttp.send();
            } 
    
    
            function getcurs(){

        

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
                        document.getElementById("curriculum").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST","getcurs",true);
                xmlhttp.send();
            }

            function getlessons(){
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
    
                        //alert("yu");
                        document.getElementById("sessioner").value=xmlhttp.responseText;
                        load_sessions(xmlhttp.responseText);
                    }
                }
                xmlhttp.open("POST","lesson_loader",true);
                xmlhttp.send();

            }
    
    
            function  sethiddencur(hd){
                var cr=hd.value;
                document.getElementById("hidden_cur").value=cr;
                if(cr!=""){
                    document.getElementById("expected_lessons").disabled=false;
                }    
        
        
            }
    
    
    
    
    
    
    
    
    
            function load_sessions(no_of_lessons){

 
                var dist=no_of_lessons;
                var cur=document.getElementById("hidden_cur").value;

                //check_lessons(no_of_lessons);

                //alert(cur);
                // window.open("districtchooser?county="+dist.value);     
                var xmlhttp;    
                if (dist=="")
                {
                    //filter the districts    

                    document.getElementById("viewpdt").innerHTML="";
                    return ;
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
                        document.getElementById("viewpdt").innerHTML=xmlhttp.responseText;
                     
                    }
                }
                xmlhttp.open("POST","sessions_chooser?not="+dist+"&cur="+cur,true);
                xmlhttp.send();
            }
    
    
 
 
            function check_lessons(no_of_lessons){
     
     
                var dist=no_of_lessons.value;


                if(dist>10){
                    alert("cannot accept more than 10 lessons. Reduce The expected lessons value");
                    document.getElementById("expected_lessons").focus();
    
                    return false;
                }
                else{
    
                    load_sessions(no_of_lessons); 
                    return true;
                }
     
            }
 
 
    
            function number_validator(){
                var num;
                num=document.getElementById("expected_lessons").value;
                if(num<=0){
                    alert("No of lessons cant be less than 1");
                    return false; 
                }   

                if(num>=15){
                    alert("These Lessons Are more");
                    return false;
                }
        
                var start_date,end_date,dateObject,day,month,year,current_date;
                start_date=document.getElementById("datepicker1").value;
                end_date=document.getElementById("datepicker2").value;
    
                //created the date object
                dateObject =new Date();
                day=dateObject.getDate();
                month=dateObject.getMonth()+1;
                year=dateObject.getFullYear();
                //fully year separated by backward slash
                current_date=month+"/"+day+"/"+year;     
                //we compare the date
                if(new Date(start_date) > new Date(current_date)){
                    alert("Start date cannot be greater than current date");   
                    return false;    
                }
    
                //we compare the current date and the preselected date
                if(new Date(start_date) > new Date(end_date)){
                    alert("Start date cannot be greater than end date.");   
                    return false; 
                }
    
                if(new Date(end_date) > new Date(current_date)){
                    alert("End date cannot be greater than current date.");   
                    return false; 
                }
                if(start_date=="" || end_date==""){
                    alert("Choose start and end dates.");   
                    return false; 
                }

            }
            $(function() {
                $( "#datepicker1" ).datepicker({
                    
                                changeMonth: true,
                                changeYear: true
                                , maxDate: new Date(),
                                minDate:mindate
                    
                });
                $( "#datepicker2" ).datepicker({
                    
                                changeMonth: true,
                                changeYear: true,
                             maxDate: new Date(),
                             minDate:mindate
                });
                //$( document ).tooltip();




            });


            function validatedates(){
                for(var a=1;a<=2;a++){
            
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
            function date_checker(){
        
                var start_date,end_date,dateObject,day,month,year,current_date;
                start_date=document.getElementById("datepicker1").value;
                end_date=document.getElementById("datepicker2").value;
                alert(start_date);
                alert(end_date);
    
                //created the date object
                dateObject =new Date();
                day=dateObject.getDate();
                month=dateObject.getMonth()+1;
                year=dateObject.getFullYear();
                //fully year separated by backward slash
                current_date=month+"/"+day+"/"+year; 
                alert(current_date);    
                //we compare the date
                if(new Date(start_date) < new Date(current_date)){
                    alert("Start date cannot be greater than current date");   
                    return false;    
                }
    
                //we compare the current date and the preselected date
                if(new Date(start_date) > new Date(end_date)){
                    alert("Start date cannot be greater than end date.");   
                    return false; 
                }
    
                if(new Date(end_date) > new Date(current_date)){
                    alert("End date cannot be greater than current date.");   
                    return false; 
                }
    
            }
            
            
            
       //===============call all the functions
     
            
            
            
            
        </script>
    </head>
    <body onload="">

        <div id="container" >
            <%      if (session.getAttribute("level").equals("0")) {%>
            <%@include file="/menu/adminmenu.jsp" %>
            <%} else {%>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%}
            %>

            <br/>
            <h3 style="text-align: center; background-color: orange;">
                <%if (session.getAttribute("prevpage") != null && session.getAttribute("prevpage").equals("FormWizard.jsp")) {%>

                <a href="add_members.jsp" style="margin-left: -400px;margin-right: 250px;" class="linkstyle"> << Back</a>
                <%}%>
                REGISTERING TOPIC
                <%if (session.getAttribute("prevpage") != null && session.getAttribute("prevpage").equals("FormWizard.jsp")) {%>

                &nbsp; &nbsp; Page 6/7 &nbsp;
                <%}%>

            </h3>
            <div id="header" align="center">
                <br/>

            </div>

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
                

            <div id="content" style="height:750px;margin-left: 20px;">

                <%
                    String topic_name, school_name, clas, district, school_level;
                    topic_name = request.getParameter("topic");
                    school_name = request.getParameter("school_name");
                    clas = request.getParameter("Primary");
                    district = request.getParameter("district");
                    school_level = request.getParameter("school_level");
                %>

                <h5 style="text-align: center;"><font color="green">Note:  </font><font color="blue">Always refresh page by pressing F5 if sessions and topics do not appear at the lower section </font></h5>
                <h4 style="text-align: center;">Specify peer details appropriately to mark or edit attendance.
                    <img src="images/help.png" id="opener" title="Ensure that topics are added  under the curriculum that appears on the third row of this page." alt=" Help Image " style=" width: 40px; height: 40px;"/></h4>



                <p style="text-align: center;"><font color="red">*</font> indicates must fill fields</p>
                <form action="new_topics" method="post" onsubmit="return number_validator();" style="width:1150px;">
                    <table cellpadding="4px" cellspacing="3px" border="0px" align="center">

                        <tr align="left">
                            <td><label class="align_button_left"><font color="red">*</font>Start Date  (mm/dd/yyyy)</label>

                                <input type="hidden" name="school_level" value="<%=school_level%>" required="true"/>
                                <input type="hidden" name="school_name" value="<%=school_name%>" required="true"/>
                                <input type="hidden" name="clas" value="<%=clas%>" required="true"/>
                                <input type="hidden" name="topic_name" value="<%=topic_name%>" required="true"/>
                                <input type="hidden" name="district_name" value="<%=district%>" required="true"/>
                            </td>
                            <td><input  type="text" value="" name="start_date" id="datepicker1" class="textbox" required="true" /></td>    
                        </tr>

                        <tr align="left">
                            <td><label class="align_button_left"><font color="red">*</font>End Date  (mm/dd/yyyy)</label></td>

                            <td><input  type="text"  value=""  required name="end_date" id="datepicker2" class="textbox"  /></td>

                        </tr>


                        <tr align="left">  
                            <td ><label class="align_button_left"><font color="red">*</font>Curriculum</label></td>
                            <td><Select id="curriculum" class="textbox2" onchange="sethiddencur(this);" required ="true"  name="curriculum" >

                                    <option value="">Choose Curriculum</option>  


                                </select></td><td><input type="hidden" id="hidden_cur" name="hidden_cur" /></td> </tr>

                        <!-- facilitator-->
                        <tr align="left">  
                            <td ><label class="align_button_left"><font color="red">*</font>Facilitator</label></td>
                            <td><Select placeholder="facilitator names ( facilitator phone number)" id="facil" class="textbox2" required ="true"  name="facil" >

                                </select>
                            </td></tr>



                        <!--co facilitator-->




                        <tr align="left">  
                            <td ><label class="align_button_left">Co-facilitator</label></td>
                            <td><Select multiple id="co_facil" style="height:45px; width:220px; text-align: center;"  name="co_facil" > 
                                </select>
                            </td></tr>




                        <tr align="left">
                            <td><font color="red">*</font><label class="align_button_left">Expected No of Sessions</label></td>  
                            <td><input type="" readonly="true" class="textbox" name="expected_no_of_lessons" id="sessioner">
                            </td>
                        </tr>

                    </table>


                    <div style="width:1100px; overflow-x:auto;">
                        <p style="text-align: center;">Select the topics as taught in each day</p>

                        <table id="viewpdt" align="center" style=" width:1095px;overflow-x:scroll; " > </table>
                    </div>





                    <table cellpadding="4px" cellspacing="3px" border="0px" align="center" > 
                        <tr align="center">
                            <td></td>
                            <td class="align_button_left"><input onmouseover="validatedates();" type="submit" name="submit"  value="Next" class="submit"></td>



                        </tr> 
                    </table>
                </form>

            </div>



            <div id="footer">
                <%
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);

                %>
                <p align="center">HC1 &copy Aphia Plus | USAID <%=year%></p>
                
           
            <script> </script>
                
            </div>
        </div>    
<script>
    
    
    
    getlessons(); 
getcurs(); 
getfacil(); 
getcofacil();  
    
</script>
    </body>
</html>
