<%-- 
    Document   : edit_student2
    Created on : Aug 12, 2013, 4:47:52 PM
    Author     : Nyabuto Geofrey
--%>
<%@page import="java.util.Calendar"%>
<%@page import="hc.dbConn"%>
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
                       <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script> 
        <title>Edit Member Details</title>
    </head>
    <body>

        <div id="container" >
            <%   if (session.getAttribute("level").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>
            <br/>
              <h3 style="text-align: center; background-color: orange;">EDITING PARTICIPANTS</h3>
            
            <div id="content" style="height:550px;">


                <div id="midcontent" style="margin-left:150px ;">
                    
                    <h4 align="center">Edit Participant Details</h4>
                    
                    <table cellpadding="2px" cellspacing="3px" border="0px" style="width: 600px;" bgcolor="cornflowerblue">
                        <h4> 
                            <%
                                String member_id = request.getParameter("userid");


                                int counter = 1;
                                String editor = "select * from member_details WHERE member_id='" + member_id + "'";
                                dbConn conn = new dbConn();
                                conn.rs = conn.st.executeQuery(editor);

                            %>
                        </h4>

                        <tr>
                            <td>First Name</td>

                        </tr>
                        <%
                            while (conn.rs.next()) {
                        %>
                        <tr>
                        <form action="Edit_member_action" method="post">
                            <input type="hidden" name="member_id" value="<%=conn.rs.getString("member_id")%>"/>
                            <tr> <td class="format_cell"><input type="text" name="fname" value="<%=conn.rs.getString("first_name")%>" required class="textbox"/></td> </tr> 

                            <tr>
                                <td>Middle Name</td>

                            </tr>
                            <tr>  <td class="format_cell"><input type="text" name="mname" value="<%=conn.rs.getString("mname")%> " class="textbox"/> </td> </tr>
                            <tr>
                                <td>Last Name</td>

                            </tr>

                            <tr> <td class="format_cell"><input type="text" name="lname" value="<%=conn.rs.getString("sur_name")%>"required class="textbox"/>  </td> </tr>

                            <tr>
                                <td>Age</td>

                            </tr>
                            <tr> <td class="format_cell"><input type="number" pattern="[0-9]{1,2}" onkeypress="return numbers(event)" maxlength="2" autofocus 
                                                                title="The field must have atleast 1 integer and atmost 2 intergers " name="age" value="<%=conn.rs.getString("age")%>" required class="textbox3"/>  </td> </tr>

                            <tr>
                                <td> Gender</td>

                            </tr>
                            <tr> 

                                <td>
                                    <select name="sex" class="textbox2">
                                        <option value="<%=conn.rs.getString("sex")%>" selected><%=conn.rs.getString("sex")%></option>
                                        <%
                                            String currentsex = conn.rs.getString("sex");

                                            String editor2 = "select * from gender WHERE gender_name!='" + currentsex + "'";
                                            conn.rs2 = conn.st2.executeQuery(editor2);
                      while (conn.rs2.next()) {%>  
                                        <option value="<%=conn.rs2.getString(2)%>"><%=conn.rs2.getString(2)%></option>
                                        <% break;
                   }%>
                                    </select>
                                </td>

                            </tr>



                            <tr>
                                <td>Group name</td>

                            </tr>
                            <td>
                                <%
                                //select all the groups
                                    conn.rs4 = conn.st4.executeQuery("Select group_name from groups where group_id='" + conn.rs.getString("group_id") + "'");
                                    conn.rs4.next();
                                    String selected_group_name = conn.rs4.getString("group_name");


                                %>
                                <select name="group" class="textbox2" required="true">
                                    <option value="<%=conn.rs.getString("group_id")%>"><%=selected_group_name%></option>


                                    <%
                                        String other_groups = "";

                                        String og = "select * from groups WHERE group_id !='" + conn.rs.getString("group_id") + "'";
                                        conn.rs3 = conn.st3.executeQuery(og);
       while (conn.rs3.next()) {%>  
                                    <option value="<%=conn.rs3.getString(1)%>"><%=conn.rs3.getString(2)%></option>
                                    <% }%>



                                </select>
                            </td>


                            <tr>
                                <td></td>

                            </tr>
                            <tr>
                                <td></td>

                            </tr>
                            <tr> <td>  
                                    <input type="submit" name="Submit" value="Save" class="textbox1"/>             
                        </form>
                        </td> 

                        </tr>
                        <%}%>
                        <tr>
                            <td></td>

                        </tr>
                    </table>

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

