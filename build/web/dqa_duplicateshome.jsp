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
           <script src="scripts/jquery.js" type="text/javascript"></script> 
       <script src="scripts/jquery.dataTables.min.js" type="text/javascript"></script>
         <script src="scripts/jquery.dataTables.editable.js" type="text/javascript"></script>
         <script src="scripts/jquery.jeditable.js" type="text/javascript"></script>
<!--          <script src="scripts/jquery-ui.js" type="text/javascript"></script>-->
          <script src="scripts/jquery.validate.js" type="text/javascript"></script>
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
            
            
             $.ajax({
    url:"uniquetargetpop",
    type:'post',
    dataType:'html',
    success:function(data){
//        alert(data);
        document.getElementById("targetpop").innerHTML="<option value=''>Select target population</option>"+data;
        
    }
});  
            
           // loadduplicates1('0');
         
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
                           
                 sDeleteURL: "deletemember"
                 
                 ,
                "aoColumns": [{type: "text"},null,null,null,null,null,null,null,null,null,null
                        ]
            }
            
            ).columnFilter({"aoColumns": [{type: "text"},{},{},{},{},{},{},{},{},{},{} ]});
         
        });
        
        </script>
        
       

        <link rel="stylesheet" href="js/demos.css" />

        <title>Duplicates analysis</title>
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
            
            
             var table=null;
            
            function loadduplicates(id){
            document.getElementById("members").innerHTML="<tr><td colspan='11'><h3 color='green'>Analysing Data...Please wait.. </h3><img src='images/loading.gif'></td></tr>";
      var groupid=null;
      if(id!=0){   groupid=id.value; }
      
           
             $.ajax({
                    url:"loadduplicates?targetpop="+groupid,
                    type:'post',
                    dataType:'html',
                    success:function (data){
                         document.getElementById("members").innerHTML=data;
                        //window.location.reload();
                        
                               
            document.getElementById("members").innerHTML=data;
          
            
            
// $('#members tbody').on( 'click', 'tr', function () {
//        $(this).toggleClass('selected');
//    } );
              // document.getElementById("loading").innerHTML="";
                        
                        
                                    
                
                    }
                    
                    
                    
                });
            
            }
            
          
          
          
          
          
          
          
//======================================================================================================          
//======================================================================================================          
//======================================================================================================          
//======================================================================================================          
//======================================================================================================



function loadduplicates1(id){
            document.getElementById("members").innerHTML="<tr><td colspan='11'><h3 color='green'>Analysing Data...Please wait.. </h3><img src='images/loading.gif'></td></tr>";
      var groupid=null;
      if(id!=0){   groupid=id.value; }
      
           
             $.ajax({
                    url:"loadduplicates?targetpop="+groupid,
                    type:'post',
                    dataType:'html',
                    success:function (data){           
                    //alert("worked");    
                        
                      window.location.reload();                
                
                    }
                    
                    
                    
                });
            
            }




          
          
            
        </script>


    </head>
    <body>
        <div id="container" style="height: 800px;width:1400px;">
            <%   if (session.getAttribute("level").equals("2")) {
            %>
            <%@include file="/menu/clerkmenu.jsp"%>
            <%} else {%>
            <%@include file="/menu/adminmenu.jsp"%>
            <%}%>

            <br/>
            <h3 style="text-align: center; background-color: orange;">Possible Duplicates<img src="images/help.png" id="opener" title="Click Here to view Help For this Page." alt=" Help Image " style=" width: 40px; height: 40px;"></h4>
                    <div id="dialog" title="Editing Participants Help." style=" font-size: 17px;">


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
                
                
                
                <form action="loadduplicates"   method="post"  style="width:1100px;margin-left: 100px;"  onsubmit="return nullness();">
                   <p id="loading"></p>
                    <table cellpadding="2px" cellspacing="3px" border="0px" class="table_form1" style="width:980px;" >


                        <tr><td>Show data where the participant's </td>

                               
                          
                            
                            <td colspan="1"> <select title="select the category of duplicates analysis"  id="duplicatecategory" name="duplicatecategory" class="textbox2">
                                   
                                    
                                   <option value="name_age">Name and age</option>
                                 
                                </select></td> 
                                <td> is duplicating and the target Population is </td>
                                <td><select name="targetpop" id="targetpop" class="textbox2" onchange="loadduplicates1(this);"> </select></td>
                                

             



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
                
                  <%}else {%><h3 style="color:red;"> Note: To delete a participant, you need an administrator or an M&E Officer account </h3><%}%>
		        <table id="members" class="display">
		          
                            <%if(session.getAttribute("loadedtable")!=null){
                                
                           
                                out.println(session.getAttribute("loadedtable"));
                                
                                 }
            
            %>
                            
                            
		        </table>
                                
		    </div>

           
                
        </div>
    </body>

</html>
