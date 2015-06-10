<%-- 
    Document   : teacher
    Created on : Aug 6, 2013, 10:30:16 AM
    Author     : SIXTYFOURBIT
--%>

<%@page import="java.util.Calendar"%>
<%@page import="hc.dbConn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>


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
        
        <script src="scripts/jquery-1.8.3.js" type="text/javascript"></script> 
        
        <script src="scripts/jquery.dataTables.js" type="text/javascript"></script>      
            <script src="scripts/jquery.jeditable_1.js" type="text/javascript"></script> 
            <script src="scripts/jquery-ui.min.js" type="text/javascript"></script> 
            
         <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>      
         <script src="scripts/dataTables.tableTools.js" type="text/javascript"></script>
         <script src="scripts/jquery.dataTables.columnFilter.js" type="text/javascript"></script>
         <link href="media/dataTables/jquery.dataTables.css" rel="stylesheet" type="text/css" />
         <link href="scripts/dataTables.tableTools.css" rel="stylesheet" type="text/css" />          
        <link href="media/dataTables/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="media/dataTables/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="media/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" media="all" />
        <link href="media/themes/smoothness/jquery-ui-1.7.2.custom.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="themes/base/jquery.ui.all.css">
         <script src="scripts/jquery.jeditable.datepicker.js" type="text/javascript"></script>
         <script src="js/datepicker.js" type="text/javascript"></script>
        <link rel="stylesheet" href="themes/base/jquery.ui.datepicker.css">
        

               
                   

        
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
            
            
         
        });
        
        
        
        function loadgrps(cnt){
          document.getElementById("members").innerHTML="<tr><td colspan='11'><h3 color='green'>Loading  Groups... <font color='green'>Please wait.. </font> </h3><img src='images/loading.gif'></td></tr>";
       var county=cnt.value;
 var editor="";
           $.ajax({  
                    url:"loadgroups?county="+county,  
                    type:'post',  
                    dataType: 'json',  
                    success: function(dat) {
                   
            document.getElementById("members").innerHTML=dat.tbl;
            
          
         var  table=  $("#members").dataTable({
              
             //paging: false,
               destroy: true,
              "dom": 'T<"clear">lfrtip',
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
             "sRowSelect": "multi"
            
        },
              
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            }).makeEditable({
                 sUpdateURL: "newupdategroup",            
                            
                 
                "aoColumns": [ {
                            event: 'mouseover',
                            indicator: 'Saving...',
                            tooltip:'doubleclick to edit',                           
                            type: 'text',
                            width:'110%',
                            
                            submit:'Save',
                            callback : function(value, settings)
                            { 
                               
                            },
                            fnOnCellUpdated: function(sStatus, sValue,value, row, column, settings){
                        }
                                                                                              
                        },null,null,{
                            
                            
                            event: 'mouseover',
                            indicator: 'Saving...',
                             tooltip:'doubleclick to edit',                                                           					tooltip: 'Click to edit ',
                            type: 'select',
                            data:dat.wards,
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

                        },null
                        ]
            }
            
            
            
            
            );//end of makeEditable
              
              
       
              
               table.destroy();
             table=$("#members").dataTable({ });  
            

              }//end of ajax succes
                    
                               
        });//end of ajax
         
            }//end of load groups function
         
        
        
        </script>
        
       

        <link rel="stylesheet" href="js/demos.css" />

        <title>Edit Group</title>
        <script type="text/javascript">
           
         
            function editmember(memberid,names){
                
                
                //alert(names);
                
                
              
                
            }
            
            
            
            
            function loadduplicates(id){
            
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

<style>
    
    
    .sbmit {
   border-top: 1px solid #96d1f8;
   background: #65a9d7;
   background: -webkit-gradient(linear, left top, left bottom, from(#3e779d), to(#65a9d7));
   background: -webkit-linear-gradient(top, #3e779d, #65a9d7);
   background: -moz-linear-gradient(top, #3e779d, #65a9d7);
   background: -ms-linear-gradient(top, #3e779d, #65a9d7);
   background: -o-linear-gradient(top, #3e779d, #65a9d7);
   padding: 5px 10px;
   -webkit-border-radius: 8px;
   -moz-border-radius: 8px;
   border-radius: 8px;
   -webkit-box-shadow: rgba(0,0,0,1) 0 1px 0;
   -moz-box-shadow: rgba(0,0,0,1) 0 1px 0;
   box-shadow: rgba(0,0,0,1) 0 1px 0;
   text-shadow: rgba(0,0,0,.4) 0 1px 0;
   color: white;
   font-size: 14px;
   font-family: Georgia, serif;
   text-decoration: none;
   vertical-align: middle;
   }
.sbmit:hover {
   border-top-color: #28597a;
   background: #28597a;
   color: #ccc;
   }
.sbmit:active {
   border-top-color: #1b435e;
   background: #1b435e;
   }
    
</style>
    </head>
    <body>
        <div id="container" style="height: 850px;width:1400px;">
            <%   if (session.getAttribute("level").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>

            <br/>
            <h3 style="text-align: center; background-color: orange;">Edit Group Details<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h3>
                    <div id="dialog" title="Edit Group Help." style=" font-size: 17px;">

To Edit a group's ward or Name, (1)Search a group name, <br/>(2) double click on the group name / ward column <br/> (3) choose the correct ward / group name <br/> (4) Press save. <br/><br/>
<b>To edit The Partner, Target population and Sub county, click the edit button at the last colum of every row</b>
                  
                    </div></h3>

            <div id="header">
                <br/>


            </div>



       





                <%if (session.getAttribute("member_edit") != null) {%>
                <script type="text/javascript"> 
                    
                </script> <%
                        session.removeAttribute("member_edit");
                    }

                %>

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
                
                
                <!--------------------Select Group to load its data--------------------------->
                
                <table cellpadding="2px" cellspacing="3px" border="0px" class="table_form1" style="width:980px;" >




                        <tr>

                               
                          
                            
                          
                            
                            <td colspan="2"> <select title=""  id="s_id" name="group_id" class="textbox2" style="border-color: black;" onchange="loadgrps(this);">
                                    <option value="" selected="true">Select county</option>
                                    <%
                                        dbConn conn = new dbConn();
                                        String all = "select * from county  order by county_name";
                                        conn.rs = conn.st.executeQuery(all);
                                        while (conn.rs.next()) {
                                    %>
                                    <option value="<%=conn.rs.getString(1)%>,<%=conn.rs.getString(2)%>"><%=conn.rs.getString(2).toString().toUpperCase()%></option>
                                    <%}%>
                                </select></td> 

                            <td style="background-color:black ;color: white;">Filter group by County</td>



<!--                            <td colspan="2">  <input type="submit"  name="sub"  value="Search..." class="submit"/>   </td>  -->


                        </tr>     
                    </table>
                
               
                <br/>
                <br/>
                
                
                <!--------------------Select Group to load its data--------------------------->
                
                
                
                
                
               <div id="demo_jui">
                 
		        <table id="members"  class="display">
		            
		            <tbody>
                                
                     <tr><th colspan='5'><h2>Select County to load Groups</h2></th></tr>

                                
		            </tbody>
		        </table>
                                
		    </div>

           
                
        </div>
    </body>

</html>
