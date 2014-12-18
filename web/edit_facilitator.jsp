<%-- 
    Document   : edit_facilitator
    Created on : Sep 9, 2013, 11:11:58 AM
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
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <title>Edit Facilitator.</title>

        <script type="text/javascript">
            function target_pop_grp(){
        
                // document.getElementById("hidden_dist").value=dist.value;     
                var partner_name=document.getElementById("partner").value;
                // window.open("districtchooser?county="+dist.value);     
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
                        document.getElementById("target_pop").innerHTML=xmlhttp.responseText;
    
                    }
                }
                xmlhttp.open("POST","population_chooser?partner="+partner_name,true);
                xmlhttp.send();
                //filter_nhfs(dist);
            }//end of filter population
        </script> 
    </head>
   
    <div id="container" style=" width: 1450px; height:620px;" >
        <%   if (session.getAttribute("level").equals("2")) {
        %>
        <%@include file="/menu/clerkmenu.jsp"%>
        <%} else {%>
        <%@include file="/menu/adminmenu.jsp"%>
        <%}%>

        <div id="header" align="center">
        </div>
        <br/>
            <h3 style="text-align: center; background-color: orange;">EDITING FACILITATORS</h3>
<br/>
            <p style="text-align: center;">Search a facilitator by either Name,phone number, partner, target population then click search</p>
            
                <table cellpadding="2px" cellspacing="3px" border="0px" class="table_style" style="width:1300px; background: #3366ff" >
                    <tr><td colspan="8">
              <form action="edit_facilitatorbn"   method="post"  style="width:1400px;" id="et" onsubmit="return nullness();" bgcolor="plum">
                                <table   style="width:1400px;">
 <%if (session.getAttribute("edit_facilitator") != null) {%>
    <script type="text/javascript"> 
                    
                                    var n = noty({text: '<%=session.getAttribute("edit_facilitator")%>',
                                        layout: 'center',
                                        type: 'Success',
 
                                        timeout: 4700});
                    
    </script> <%
            session.removeAttribute("edit_facilitator");
        }

    %>
                                    <tr >

                                        <th>  <input type="text" list="allnames" name="sname" id="sname" value="" placeholder="Enter Sur name" maxlength="25" class="textbox1" style="background: "/>
                                            <datalist id="allnames">

                                            </datalist>
                                        </th> 
                                        <th colspan="2"> <select  id="partner" name="partner" class="textbox2" onchange="return target_pop_grp();">
                                                <option value="" selected="true">Choose Partner </option>
                                                <%
                                                    String selector = "select * from partner";
                                                    dbConn conn = new dbConn();
                                                    conn.rs = conn.st.executeQuery(selector);
                                                    while (conn.rs.next()) {

                                                %>
                                                <option value="<%=conn.rs.getString("partner_id")%>"><%=conn.rs.getString("partner_name")%></option>
                                                <%}%>
                                            </select></th> 

                                        <th colspan="2"> <select  name="target_pop" class="textbox2" id="target_pop">
                                                <option value="" selected="true">Choose Target Population </option>
                                            </select>
                                            <input type="number" name="phone" value="" class="textbox1" placeholder="Phone number">         

                                        </th> 



                                        <th colspan="2">  <input type="submit"  name="sub" value="Search..." class="textbox1"/>   </th>  


                                    </tr>     
                                </table>
                            </form>
                                            
    <form action="edit_facilitatorbn"   method="post"  style="width:1300px;" id="et" onsubmit="return nullness();" bgcolor="plum">                                          
                                            <table  style="width:1200px;">
                    <tr>      
                        <th>First Name</th>
                        <th>Middle Name</th>
                        <th>Last Name</th>
                        <th>Phone No.</th>
                        <th>Partner</th>
                        <th>Groups</th>
                        <th>Edit</th>
                        <th>Transfer</th>
                    </tr>  
                    <c:forEach items="${alist}" var="details"> 
                        <tr>   
                            <td>   
                                ${details.fname}  
                            </td>
                            <td>   
                                ${details.mname}  
                            </td> 
                            <td>   
                                ${details.lname}   
                            </td>   
                            <td>   
                                ${details.phone}  
                            </td>  
                            <td>   
                                ${details.partner_name}  
                            </td>   
                            <td style="width:80px;">   
                                ${details.group_name}  
                            </td>   
                            <td>   
                                <a href="edit_one_facilitator.jsp?facilitator_id=${details.id}&phone=${details.phone}&fname=${details.fname}&lname=${details.lname}&mname=${details.mname}&partner_name=${details.partner_name}&groups=${details.group_name}" class="linkstyle">Edit</a>
                            </td>   
                            <td>   
                                <a href="transfer_one_facilitator.jsp?facilitator_id=${details.id}&partner_id=${details.partner_id}&phone=${details.phone}&fname=${details.fname}&lname=${details.lname}&mname=${details.mname}&partner_name=${details.partner_name}&groups=${details.group_name}" class="linkstyle">Transfer</a>
                            </td>
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