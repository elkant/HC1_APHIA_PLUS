<%-- 
    Document   : edit_group
    Created on : Sep 8, 2013, 8:14:10 PM
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
        <link rel="stylesheet" type="text/css" href="css/divCss_1.css"/>
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


        <title>Edit Group.</title>
        <script type="text/javascript">
            
            
            function searchgrp(){
                
                var groupname=document.getElementById("grpname").value;    
            
            $.ajax({
                url:"groupdetails?grp="+groupname,
                type:'post',
                dataType:'html',
                success:function(data){
                    //alert(data);
                document.getElementById("viewpdt").innerHTML=data;   
                }
                
                
                
            });
            
            }
            
     
            
            function county(){

                var dist=district.value;    
   
                var xmlhttp;    
                if (dist=="")
                {
                    //filter the counties    



                    //document.getElementById("county").innerHTML="<option value=\"\">choose district</option>";
                    //return;
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
                        document.getElementById("county").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST","countychooser?county="+county,true);
                xmlhttp.send();
                
                document.getElementById("county").innerHTML="<option value=\"\">loading..</option>";
            }//end of count picker
    
    
    
            function filter_districts(){

                var county_id=document.getElementById("county").value;        
                var xmlhttp;    
                if (county_id=="")
                {
                    //filter the districts    



                    document.getElementById("district").innerHTML="<option value=\"\">Choose district</option>";
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
                        document.getElementById("district").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST","districtchooser?county_id="+county_id,true);
                xmlhttp.send();
                
                document.getElementById("district").innerHTML="<option value=\"\">loading..</option>";
                
            }//end of filter districts


            function partners(){

                var district=document.getElementById("district").value;    
                //   alert (district);
                var xmlhttp;    
                if (district=="")
                {
                    //filter the partners   



                    document.getElementById("partner").innerHTML="<option value=\"\">Choose Partner</option>";
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
                        document.getElementById("partner").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST","partner_chooser_new?dist="+district,true);
                xmlhttp.send();
                
                  document.getElementById("partner").innerHTML="<option value=\"\">loading..</option>";
                
            }//end of partner picker

            function grouper(){

                var partner_id=document.getElementById("partner").value;    
                var xmlhttp;    
                if (partner_id=="")
                {
                    //filter the partners   

                    document.getElementById("grp").innerHTML="<option value=\"\">Choose Group</option>";
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
                        document.getElementById("grp").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST","group_loader?partner_id="+partner_id,true);
                xmlhttp.send();
                  document.getElementById("grp").innerHTML="<option value=\"\">loading..</option>";
                
            }//end of grouper picker
        </script>
        <script type="text/javascript">
            function group_them_all(){
                var partner_id=document.getElementById("partner2").value;  
                document.getElementById("groups_2").hidden=false; 
                alert(partner_2);
                //    alert(partner_id);
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
                        document.getElementById("groups_2").innerHTML=xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST","group_loader?partner_id="+partner_id,true);
                xmlhttp.send();  
                
            }
        </script> 
    </head>
    <body onload="return county();">
        <%
            session.removeAttribute("county_id");
            session.removeAttribute("county_name");
            session.removeAttribute("district_id");
            session.removeAttribute("district_name");
            session.removeAttribute("partner_id");
            session.removeAttribute("partner_name");
            session.removeAttribute("group_id");
            session.removeAttribute("group_name");
        %>
        <div id="container" style="font-size: 17px;">
           <%   if(session.getAttribute("level").equals("2")){ 
            %>
<%@include file="/menu/clerkmenu.jsp"%>
<%} else{%>
<%@include file="/menu/adminmenu.jsp"%>
<%}%>
            <div id="header" align="center">
            </div>
            <br/>


            <h3 style="text-align: center; background-color: orange;">EDITING GROUPS</h3>




            <div id="content" style="height:530px;">
                <div id="midcontent" style="margin-left:130px ;">
                    <br/><br/>
                    <%
                        session.removeAttribute("county_id");
                        session.removeAttribute("county_name");
                        session.removeAttribute("district_id");
                        session.removeAttribute("district_name");
                        session.removeAttribute("partner_id");
                        session.removeAttribute("partner_name");
                        session.removeAttribute("target_id");
                        session.removeAttribute("population_name");
                    %>
                    <h4>Select County, District and Partner to edit Group.<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4>

                    <br><br>
                    <%if (session.getAttribute("edit_group") != null) {%>
                    <script type="text/javascript"> 
                    
                                    var n = noty({text: '<%=session.getAttribute("edit_group")%>',
                                        layout: 'center',
                                        type: 'Success',
 
                                        timeout: 3800});
                    
                    </script> <%
                            session.removeAttribute("edit_group");
                        }

                    %>
                    <form action="edit_group_session" name="" method="post">
                    
                            <br/><br/>
                            <div id="dialog" title="Edit Groups Help." style=" font-size: 15px;">
                                <%  String message1 = "";
                                    dbConn conn = new dbConn();

                                    String message_selector = "SELECT * FROM help WHERE help_id='3'";
                                    conn.rs = conn.st.executeQuery(message_selector);
                                    conn.rs.next();
                                    message1 = conn.rs.getString(3);
                                    out.println(message1);
                                %>
                            </div>
                                <table cellspacing="4px">
                           <tr><td>
                            County<font color="red">*</font>
                            </td><td>

                            <Select  required ="true" name="county"  id ="county" class="textbox2" style="background: #ffffff;" onchange="return filter_districts();">
                                <option value="">Choose County</option>  
                            </select>
                            </td></tr>
                           <tr><td> District<font color="red">*</font>
                            </td><td>
                            <Select  required  name="district" id="district" class="textbox2" style="background: #ffffff;" onchange="return partners();">
                                <option value="">Choose District</option>  
                            </select>
                            </td></tr>

                            <tr><td>Partners Name<font color="red">*</font>
                            </td><td>
                            <Select   name="partner" id="partner" class="textbox2" style="background: #ffffff" onchange=" return grouper();" required>
                                <option value="">Choose Partner</option>  
                            </select>
                            </td></tr>
                           <tr> <td>Group<font color="red">*</font>
                            </td><td>

                            <Select  name="group"  id ="grp" class="textbox2" style="background: #ffffff" required>
                                <option value="">Choose Group</option>
                            </select>
                            </td></tr>

                           <tr><td></td><td colspan="1"> <input type="submit" style="height: 38px;width: 130px;" name="sub" value="Edit Group" class="textbox1" style="color:#0000ff;" ></td></tr>
                        </table>
                        
                    </form>
                            <br/>
                            
                            <form>
                            <br/>
                                <table><tr><td>    <h5>Search groups details by typing the group name here  : </h5></td>
                                        <td><input list="grups" oninput="searchgrp();" class="textbox" name="grpname" id="grpname"/> 
                                            <datalist id="grups">

                                 <% 
                                 
                                 conn.rs2=conn.st2.executeQuery("select group_name from groups");
                                while (conn.rs2.next()){                                 

%>
<option value="<%=conn.rs2.getString(1)%>" >
                                 
    <%}%>
                                            </datalist></td></tr></table>
                               
                             
                            
                            <br/> 
                              <table id="viewpdt" name="viewpdt" style="margin-left: 100px; width:500px;"></table>  
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
