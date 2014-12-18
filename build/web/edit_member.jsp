<%-- 
    Document   : teacher
    Created on : Aug 6, 2013, 10:30:16 AM
    Author     : SIXTYFOURBIT
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
<%@page import="java.util.Calendar"%>
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



        <link href="js/css/start/jquery-ui-1.10.3.custom.css" rel="stylesheet"/>

        <script src="js/js/jquery-ui-1.10.3.custom.js"></script>

        <script type="text/javascript">
    
    
            $(function() {

                $( document ).tooltip();
                $( "#accordion" ).accordion();

            }); 
    
    
    
        </script>

        <link rel="stylesheet" href="js/demos.css" />

        <title>Edit member details</title>
        <script type="text/javascript">
            function filter_phoneno(schl){
               
                // var dist=school.value;    
                var school=schl.value;     
                var xmlhttp;    
                if (school=="")
                {
                    //filter the districts    
      
      
  
                    document.getElementById("p_id").innerHTML="<option value=\"\"></option>";
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
                        document.getElementById("p_id").innerHTML=xmlhttp.responseText;
                        filter_nhfs(dist);
                    }
                }
                xmlhttp.open("POST","phonenochooser?school_id="+school,true);
                xmlhttp.send();    
            
            } 
            
            function nullness() {
                var sname,sid,phoneno;
                sname=document.getElementById("sname").value;
                var fname=document.getElementById("fname").value;
                var mname=document.getElementById("mname").value;
                sid=document.getElementById("s_id").value;
                phoneno=document.getElementById("p_id").value;
                if(sname=="" && sid=="" && phoneno==""&&mname==""&&fname==""){
            
                    alert("Choose At least one category");  
            
                    return false;  
                }
          
            }   
            function getNames(){
                //  alert("called");
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
                        document.getElementById("allnames").innerHTML=xmlhttp.responseText;
     
                    }
                }
                xmlhttp.open("POST","member_names_chooser",true);
                xmlhttp.send();   
          
            }
            function editmember(memberid,names){
                
                
                //alert(names);
                
                
                $.ajax({
                    url:"deletemember?member_id="+memberid+"&names="+names,
                    type:'post',
                    dataType:'html',
                    success:function (data){
                        
                             var n = noty({text: data,
                                         layout: 'center',
                                           type: 'Success', 
                                        timeout: 1800,
                                       callback:{
                                            afterShow:function(){
                                                  //var win=window.open("geMemDetails",'_self');
                                                    //win.focus();
                                              },
                                           afterClose:function(){           
                                               //window.close("edit_member.jsp");
                                           } 
                                             
                                                   
                                      }
                                 });
                                    
                                    
                
                    }
                    
                    
                    
                });
                
            }
            
        </script>


    </head>
    <body>
        <div id="container" style="height: auto">
            <%   if (session.getAttribute("level").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>

            <br/>
            <h3 style="text-align: center; background-color: orange;">EDITING PARTICIPANTS</h3>

            <div id="header">
                <br/>



            </div>



       



                <form action="geMemDetails"   method="post"  style="width:980px;margin-left: 100px;"  onsubmit="return nullness();">
                    <table cellpadding="2px" cellspacing="3px" border="0px" class="table_form1" style="width:980px;" >




                        <tr>

                               <td>  <input type="text"  title="search participant by their FIRSTNAME " onclick="" name="fname" id="fname" value="" placeholder="First Name" maxlength="25" class="textbox1"/>
         
                            </td>
                               <td>  <input type="text"  title="search participant by their MIDDLENAME " onclick="" name="mname" id="mname" value="" placeholder="Middle Name" maxlength="25" class="textbox1"/>
         
                            </td>
                            
                            <td>  <input type="text" list="allnames" title="search participant by their SURNAME " onclick="getNames();" name="sur_name" id="sname" value="" placeholder="Sur Name" maxlength="25" class="textbox1"/>
                                <datalist id="allnames">

                                </datalist>
                            </td>
                            
                          
                            
                            <td colspan="2"> <select title="serch participants by their phone number"  id="s_id" name="group_id" class="textbox2" onchange="filter_phoneno(this);">
                                    <option value="" selected="true">Choose group</option>
                                    <%
                                        dbConn conn = new dbConn();
                                        String all = "select distict group_name from groups order by group_name";
                                        conn.rs = conn.st.executeQuery(all);
                                        while (conn.rs.next()) {
                                    %>
                                    <option value="<%=conn.rs.getString("group_name")%>"><%=conn.rs.getString("group_name")%></option>
                                    <%}%>
                                </select></td> 

                            <td colspan="2"> <select title="search participant by their age then click search button"  name="age" class="textbox2" id="p_id">
                                    <option value="" selected="true">Choose Age. </option>

                                    <%
                                        String pn = "select distinct age from member_details";
                                        conn.rs = conn.st.executeQuery(pn);
                                        while (conn.rs.next()) {
                                    %>
                                    <option value="<%=conn.rs.getString(1)%>"><%=conn.rs.getString(1)%></option>
                                    <%}%>
                                </select></td> 



                            <td colspan="2">  <input type="submit"  name="sub"  value="Search..." class="submit"/>   </td>  


                        </tr>     
                    </table>
                </form>






                <%if (session.getAttribute("member_edit") != null) {%>
                <script type="text/javascript"> 
                    
                                    var n = noty({text: '<%=session.getAttribute("member_edit")%>',
                                        layout: 'center',
                                        type: 'Success',
 
                                        timeout: 4800});
                    
                </script> <%
                        session.removeAttribute("member_edit");
                    }

                %>

                <form action="editTeacherAction" method="POST" style="width:980px;margin-left: 100px;" id="et">
                    <table cellpadding="2px" cellspacing="2px" border="none" id="viewpdt" style="width:970px;">
                        <tr><th>First name</th><th>Middle Name</th><th>Last Name</th><th>Sex</th><th>Group Name</th><th>Age</th><th colspan="1">Edit Member</th> <% if(session.getAttribute("level").equals("0")||session.getAttribute("username").equals("m&e")){%><th>Delete Member</th><%}%></tr>
                        <tr></tr> 
                        <c:forEach items="${allmembersAL}" var="teacher">
                            <c:set var="fname" value="fname"></c:set>

                                <tr>

                                    <td>
                                    ${teacher.fname}

                                </td>
                                <td>
                                    ${teacher.mname}

                                </td>
                                <td>
                                    ${teacher.lname}

                                </td>
                                <td>
                                    ${teacher.sex}

                                </td>
                                <td>
                                    ${teacher.group}

                                </td>


                                <td>
                                    ${teacher.age}

                                </td>

                                <td>

<!--<button onclick="edit_teacher(${teacher.count});" class="linkstyle">Save</button>-->
                                    <a  href="editOneMember.jsp?userid=${teacher.userid}" title="click to edit current row" class="linkstyle" > Edit</a>
                                </td>

                               <% if(session.getAttribute("level").equals("0") || session.getAttribute("username").equals("m&e")) {%> <td>
 <a onclick="editmember('${teacher.userid}','${teacher.fname}');" href="#" title="click to delete this member from the system" class="linkstyle" > Delete</a>

                                                                          </td><%}%>
                            </tr>
                        </c:forEach>

                    </table>



                </form>
           

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
