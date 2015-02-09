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
          document.getElementById("members").innerHTML="<tr><td colspan='11'><h3 color='green'>Analyzing Data... <font color='green'>Please wait.. </font> </h3><img src='images/loading.gif'></td></tr>";
       
 
           $.ajax({  
                    url:"invaliddate",  
                    type:'post',  
                    dataType: 'html',  
                    success: function(data) {
                   
            document.getElementById("members").innerHTML=data;
          var table=  $("#members").dataTable({
              
             
              
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
             "sRowSelect": "single"
            
        },
              
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            }).makeEditable({
                           
                 sUpdateURL: "updatesessiondates"
                 
                 ,
                "aoColumns": [ {  type:   'datepicker',
                                         datepicker: {
                                 dateFormat: 'dd/mm/yy',
                                 changeMonth:true,
                                 changeYear:true
                                          },
                                        sSuccessResponse: "IGNORE"
                                     },{  type:   'datepicker',
                                         datepicker: {
                                 dateFormat: 'dd/mm/yy',
                                 changeMonth:true,
                                 changeYear:true
                                          },
                                        sSuccessResponse: "IGNORE"
                                     },null,null,null,null,null,null,null,null,null
                        ]
            }
            
            );
            
            
// $('#members tbody').on( 'click', 'tr', function () {
//        $(this).toggleClass('selected');
//    } );
              // document.getElementById("loading").innerHTML="";
              }
                    
                               
        });
         
        });
        
        </script>
        
       

        <link rel="stylesheet" href="js/demos.css" />

        <title>DQA analysis invalid dates</title>
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
            
            
            
            
            function loadduplicates(targetpop,category){
            
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
        <div id="container" style="height: 850px;width:1400px;">
            <%   if (session.getAttribute("level").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>

            <br/>
            <h3 style="text-align: center; background-color: orange;">Invalid Dates DQA<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4>
                    <div id="dialog" title="Editing Participants Help." style=" font-size: 17px;">

The data shown in this page is in wrong start and end date format.<br><br/>
A Form's start date and end date is expected to be in the format <b>dd/mm/yyyy</b>.
<br/><b>NB:</b> date or month which is a one figure number e.g. 1 instead of 01 is considered to be of invalid format.


<br/>
<b>Cleaning up dates:</b>
<br/>
To clean up the shown dates, double click on each of the cells under start date or end date column .<br>A date picker (calender) will appear allowing one to choose the correct date format. After editing the dates, Click anywhere outside that cell. Your date is now updated.
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

                
                
                
                <!--------------------Select Group to load its data--------------------------->
                
                
                
               
                <br/>
                <br/>
                
                
                <!--------------------Select Group to load its data--------------------------->
                
                
                
                
                
               <div id="demo_jui">
                 
		        <table id="members" class="display">
		            
		            <tbody>
                                
                     

                                
		            </tbody>
		        </table>
                                
		    </div>

           
                
        </div>
    </body>

</html>
