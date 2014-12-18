<%-- 
    Document   : teacher
    Created on : Aug 6, 2013, 10:30:16 AM
    Author     : SIXTYFOURBIT
--%>

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
<!--        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>-->
        
        <link href="css/style.css" media="screen" rel="stylesheet" type="text/css" />
           <script src="scripts/jquery.js" type="text/javascript"></script> 
       <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
         <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
         <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
          <script src="scripts/jquery-ui.js" type="text/javascript"></script>
          <script src="scripts/jquery.validate.js" type="text/javascript"></script>
          <script src="scripts/dataTables.tableTools.js" type="text/javascript"></script>
          <script src="scripts/dataTables.scroller.js" type="text/javascript"></script>
          <script src="scripts/dataTables.colReorder.js" type="text/javascript"></script>
          <script src="scripts/jquery.dataTables.columnFilter.js" type="text/javascript"></script>
          <link href="media/dataTables/jquery.dataTables.css" rel="stylesheet" type="text/css" />
          <link href="scripts/dataTables.tableTools.css" rel="stylesheet" type="text/css" />
          
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
        <!---noty --->
         <script type="text/javascript" src="js/noty/jquery.noty.js"></script>

        <script type="text/javascript" src="js/noty/layouts/top.js"></script>
        <script type="text/javascript" src="js/noty/layouts/center.js"></script>
        <!-- You can add more layouts if you want -->

        <script type="text/javascript" src="js/noty/themes/default.js"></script>            
                   

        
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
        <script type="text/javascript" src="js/js/sisyphus.min.js"></script>
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
        
        
                   
        <script type="text/javascript">
        $(document).ready(function () {
         // document.getElementById("loading").innerHTML="<img src='images/loading.gif'>";
       
 
           $.ajax({  
                    url:"loadgroups_json",  
                    type:'post',  
                    dataType: 'html',  
                    success: function(data) {
                   chws=data;
            
          var table=  $("#members").dataTable({
              
             
              
              "dom": 'T<"clear">Rlfrtip',
              
        "tableTools": {
            "sSwfPath": "swf/copy_csv_xls_pdf.swf",
            "aButtons": [ {
                    "sExtends": "csv",
                    "sButtonText": "Save to csv"
                },
                {
                    "sExtends": "xls",
                    "sButtonText": "Save to xls"
                },
                {
                    "sExtends": "pdf",
                    "sButtonText": "Save to pdf"
                } ],
             "sRowSelect": "single"
            
        },
              
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            }).makeEditable({
                sUpdateURL: "newupdatemember",            
                 sDeleteURL: "deletemember"
                 
                 ,
                "aoColumns": [ {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            tooltip:'doubleclick to edit',                           
                            type: 'text',
                            width:'auto',
                            
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
                                //alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        },   

//============
                    									
                        {
                            event: 'mouseover',
                            indicator: 'Saving...',
                             tooltip:'doubleclick to edit',                                                           					tooltip: 'Click to edit ',
                            type: 'text',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
                                //alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        },
                         {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            tooltip:'doubleclick to edit',                                                            					tooltip: 'Click to edit ',
                            type: 'text',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
                                //alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        }
                        ,{
                            
                            
                            event: 'mouseover',
                            indicator: 'Saving...',
                             tooltip:' doubleclick to edit',                                                           					tooltip: 'Click to edit ',
                            type: 'select',
                            data:"{'male':'male','female':'female'}",
                            submit: 'Save changes',
                            callback: function(value, settings)
                            {
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue, value, row, column, settings) {
                                document.getElementById("msg").innerHTML = "(Cell Callback): is updated with value " + sValue;
                            }

                        },{
                            event: 'mouseover',
                            indicator: 'Saving...',
                            tooltip:'doubleclick to edit',                                                            					tooltip: 'Click to edit ',
                            type: 'text',
                            submit:'Save changes',
                            callback : function(value, settings)
                            { 
                                //alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                                document.getElementById("msg").innerHTML="(Cell Callback): is updated with value " + sValue;
                            }
                                                                                              
                        },
                        
                        {
                            
                            
                            event: 'mouseover',
                            indicator: 'Saving...',
                            //                                                            					tooltip: 'Click to edit ',
                            type: 'select',
                            data:data,
                            submit: 'Save changes',
                            callback: function(value, settings)
                            {
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue, value, row, column, settings) {
                                document.getElementById("msg").innerHTML = "(Cell Callback): is updated with value " + sValue;
                            }

                        }
                        ,null
                        
                        ,{
                            
                            
                            event: 'mouseover',
                            indicator: 'Saving...',
                             tooltip:' doubleclick to edit',                                                           					tooltip: 'Click to edit ',
                            type: 'select',
                            data:"{'2015':'2015','2014':'2014','2013':'2013'}",
                            submit: 'Save changes',
                            callback: function(value, settings)
                            {
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue, value, row, column, settings) {
                                document.getElementById("msg").innerHTML = "(Cell Callback): is updated with value " + sValue;
                            }

                        },
                        null
                        ,{
                            
                            
                            event: 'mouseover',
                            indicator: 'Saving...',
                             tooltip:' doubleclick to edit',                                                           					tooltip: 'Click to edit ',
                            type: 'select',
                            data:"{'1':'Jan','2':'Feb','3':'March','4':'Apr','5':'May','6':'Jun','7':'Jul','8':'Aug','9':'Sept','10':'Oct','11':'Nov','12':'Dec'}",
                            submit: 'Save changes',
                            callback: function(value, settings)
                            {
                                alert(value);
                                window.location.reload();
                                // settings involing plugin parameters
                                //                                                                                                                        alert('Element Changed : '+value);											
                                //alert(settings.cssclass);
                            },
                            fnOnCellUpdated: function(sStatus, sValue, value, row, column, settings) {
                                document.getElementById("msg").innerHTML = "(Cell Callback): is updated with value " + sValue;
                            }

                        }
                        ,null
                        ]
            }
            
            ).columnFilter({aoColumns: [{type: "text"},{},{},{},{},{},{},{},{},{} ]});
            
            
// $('#members tbody').on( 'click', 'tr', function () {
//        $(this).toggleClass('selected');
//    } );
              // document.getElementById("loading").innerHTML="";
              }
                    
                               
        });
         
        });
        
        </script>
        
       

        <link rel="stylesheet" href="js/demos.css" />

        <title>Participants</title>
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
                        
                            
                                    
                                    
                
                    }
                    
                    
                    
                });
                
            }
            
            
            
            
            function loadparticipants(id){
            
            var groupid=id.value;
             $.ajax({
                    url:"geMemDetails?group_id="+groupid,
                    type:'post',
                    dataType:'html',
                    success:function (data){
                        
                        window.location.reload();
                        
//                             var n = noty({text: "<font color='green'>data loaded..</font>",
//                                         layout: 'center',
//                                           type: 'Success', 
//                                        timeout: 1800,
//                                       callback:{
//                                            afterShow:function(){
//                                               // window.location.reload();
//                                                  //var win=window.open("geMemDetails",'_self');
//                                                    //win.focus();
//                                              },
//                                           afterClose:function(){           
//                                               //window.close("edit_member.jsp");
//                                           } 
//                                             
//                                                   
//                                      }
//                                 });                                    
                                    
                
                    }
                    
                    
                    
                });
            
            }
            
            
            
        </script>


    </head>
    <body>
        <div id="container" style="height: 750px">
            <%   if (session.getAttribute("level").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>

            <br/>
            <h3 style="text-align: center; background-color: orange;">EDITING PARTICIPANTS <img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4>
                    <div id="dialog" title="Editing Participants Help." style=" font-size: 17px;">
You can easily search and edit a participants details.<br/>
<b>Note</b>: The first time you open this form, only the last entered 1000 participants will be displayed.
<br/> This is to avoid slowing down the system.<br/>
<br/><b>Filter a group</b><br/>
<img src="images/selectgroups.PNG" >
<br>To edit participants for a specific group, select the group name in the select box shown in the first table </br>
<br/>
<br/> <b>Searching a Participant</b><br/>
To search a certain participant,Select the group in which the participant belongs,then type either of their details i.e first name, middle name ,group name, district e.t.c in the search field shown . 


<br/><br/>
<b>Editing particiants details</b>

To edit a participants details, double click on any cell in the table.
An input field/ select box will appear allowing you to make any changes.Click <b>save</b> to commit the changes per each cell.
<br><br/>Sorting columns</br>
To sort any column, click at any table column header 

                    </div></h3>

            <div id="header">
                <br/>



            </div>



       





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

                
                
                
                <!--------------------Select Group to load its data--------------------------->
                
                
                
                <form action="geMemDetails"   method="post"  style="width:980px;margin-left: 100px;"  onsubmit="return nullness();">
                   <p id="loading"></p>
                    <table cellpadding="2px" cellspacing="3px" border="0px" class="table_form1" style="width:980px;" >




                        <tr>

                               
                          
                            
                          
                            
                            <td colspan="2"> <select title="serch participants by their phone number"  id="s_id" name="group_id" class="textbox2" onchange="loadparticipants(this);">
                                    <option value="" selected="true">Select group</option>
                                    <%
                                        dbConn conn = new dbConn();
                                        String all = "select distinct group_name from groups order by group_name";
                                        conn.rs = conn.st.executeQuery(all);
                                        while (conn.rs.next()) {
                                    %>
                                    <option value="<%=conn.rs.getString("group_name")%>"><%=conn.rs.getString("group_name")%></option>
                                    <%}%>
                                </select></td> 

                            <td style="background-color:black ;color: white;">Filter data by group name</td>



<!--                            <td colspan="2">  <input type="submit"  name="sub"  value="Search..." class="submit"/>   </td>  -->


                        </tr>     
                    </table>
                </form>
                <br/>
                <br/>
                
                
                <!--------------------Select Group to load its data--------------------------->
                
                
                
                
                
               <div id="demo_jui">
                  <% if(session.getAttribute("level").equals("0") || session.getAttribute("username").equals("m&e")) {%>  
                  <button id="btnDeleteRow" value="cancel">Delete Participant</button>
                
                   <%}else {%> <h3 style="color:red;">Note: To delete a participant, you need an administrator or an M&E Officer account</h3> <%}%>
		        <table id="members" class="display">
		            <thead>
		            <tr>
		           <th>First name</th>
                           <th>Middle Name</th>
                           <th>Last Name</th>
                           <th>Sex</th>                          
                           <th>Age</th>
                            <th>Group Name</th>
                            <th>District</th>
                            <th>Year</th>
                            <th>Quarter</th>
                            <th>Month</th>
                            <th>Sessions Attended</th>
                            </tr>
		                
		            </thead>
		            <tbody>
                                
                                 <c:forEach items="${allmembersAL}" var="teacher">
                            <c:set var="fname" value="fname"></c:set>

                                <tr id="${teacher.userid}">
                                    <td>${teacher.fname}</td>
                                    <td>${teacher.mname}</td>
                                    <td>${teacher.lname}</td>
                                    <td>${teacher.sex}</td>                               
                                    <td>${teacher.age}</td>
                                    <td>${teacher.group}</td>
                                    <td>${teacher.district}</td>                                    
                                    <td>${teacher.year}</td>
                                    <td>${teacher.period}</td>
                                    <td>${teacher.month}</td>
                                    <td>${teacher.atten}</td>
                            </tr>
                        </c:forEach>

                                
		            </tbody>
		        </table>
                                
		    </div>

           
                
        </div>
    </body>

</html>
