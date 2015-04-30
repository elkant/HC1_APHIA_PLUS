<%-- 
    Document   : clerkmenu
    Created on : Mar 6, 2014, 4:08:56 PM
    Author     : SIXTYFOURBIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		
		<meta charset="utf-8">
		<title>Pure CSS3 Menu</title>
		<link href="style.css" media="screen" rel="stylesheet" type="text/css" />
		<link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
		<script src="prefix-free.js"></script>
	</head>

<body>
	
	
	<nav>
		<ul class="menu">

                    
                    
			<li><a href="FormWizard.jsp">HC Form Entry</a>
				
			</li>
                    

			<li><a href="#"> <img src="images/group.png"/> Edit Entries</a>
				<ul>
<!--                                    <li><a href="add_group.jsp"><span class="iconic plus-alt"></span>add group</a></li>-->
					<li><a href="edit_group.jsp"><img src="images/edit.png"/>Edit Group</a></li>
                                  <li><a href="edit_facilitatorbn"><img src="images/edit.png"/>Edit Facilitator</a></li>
                                  <li><a href="geMemDetails"><img src="images/edit.png"/>Edit  Participant</a></li>
                                </ul>
			</li>
                        
<!--                        <li><a href="#"> <img src="images/group.png"/> Facilitators</a>
				<ul>
                                   
                                   <li><a href="add_facilitator.jsp"><span class="iconic plus-alt"></span>add facilitator</a></li>
					<li><a href="edit_facilitatorbn"><img src="images/edit.png"/>Edit facilitator</a></li>
                                        
                                </ul>
			</li>-->
                      
                        
                        
                        
<!--                        <li><a href="#"> <img src="images/group.png"/> Participants</a>
				<ul>
                                   
                                        <li><a href="filter_member.jsp"><span class="iconic plus-alt"></span>add a participant</a></li>
					<li><a href="geMemDetails"><img src="images/edit.png"/>edit a participant</a></li>
                                </ul>
			</li>
                        -->
                        
			<li><a href="#"><img src="images/mark.png" width="24px"/>Attendance</a>
				<ul>
					
					<li><a href="filter_session.jsp"><img src="images/edit.png"/>Edit Attendance</a></li>
                                        
				</ul>
			</li>
                       
                        <li><a href="#"><img src="images/rpt.png" width="24px"/> Reports</a>
            <ul>
   
                <!--<li><a href="group_reports.jsp">Group Reports(Individual)</a></li>--> 
                
                  <li ><a href="kePMS_excel_report.jsp"><font color="red">PEPFAR Report (excel)</font></a></li>
                   <li><a href="groups_overall.jsp"><font color="red">Groups Completion rate (excel)</font></a></li>
                   <li><a href="RawData.jsp"><font color="red">Raw Data (excel)</font></a></li>                   
                   <li><a href="agebasedkepms.jsp"><font color="red">kepms report by age (excel)</font></a></li>
                   <li><a href="newagebasedkepms.jsp"><font color="red">(New) kepms report by age (excel)</font></a></li>
            <li><a href="partnerbasedreport.jsp"><font color="red">Kepms report by Target Pop. (excel)</font></a></li>
               
                  <li><a href="overall_target_bar.jsp">Target Populations Completion Rates (bar)</a></li>
                  <li><a href="kePMS_bar.jsp">PEPFAR Report (bar)</a></li>
                  <li><a href="county_bar.jsp">County Completion Rate (bar)</a></li>
                  
                        <li><a href="target_pie.jsp"><font color="blue">Target Population Completion Rate (pie)</font></a></li>
                    </ul>   
                      </li>
                        
                    <li><a href="#"><img src="images/backup.png" width="24px"/>
                             Data</a>
                    <ul>
                        <%if (session.getAttribute("username").equals("m&e")){%>
                        <li><a href="mergedata.jsp"><img src="images/import.png" width="24px"/>
                            Merge Data</a> </li> 
                        
                        <li><a href="backupdata.jsp" ><img src="images/backup_send.png" width="24px"/>
                            Send County Backup</a> </li>  
                             <%}%>
                        <%if (!session.getAttribute("username").equals("m&e")){%>     
                     <li><a href="localbackup.jsp" ><img src="images/backup.png"  width="24px"/>
                            Send Backup to M&E Ofr.</a> </li>  <%}%>
                       <%if (session.getAttribute("username").equals("m&e")){%>
                     <li><a href="update_sdm_email.jsp"> Senior data Manager Mail</a></li>
                     <%}%>
                     <li><a href="mandemail.jsp"><img src="images/edit.png"/>
                           Edit M&E officer email</a> </li>  
                          
                    </ul>
                    </li>
                    
                     <li><a href="#"><img src="images/backup.png" width="24px"/>
                             Management</a>
                    <ul>
                       <%if (session.getAttribute("username").equals("m&e")){%>
                     <li><a href="newClerk.jsp"><img src="images/group.png"/> Add User</a></li>
                     <%}%>
                      
<!--                     <li><a href="edit_ur_details.jsp">edit user details</a> </li>  -->
                          
                    </ul>
                    </li>
                    
                    
                     <li><a href="#"><img src="images/backup.png" width="24px"/>
                             DQA</a>
                    <ul>
                   
                      
                     <li><a href="dqa_invaliddata.jsp">check invalid dates</a> </li>  
                     <li><a href="dqa_duplicateshome.jsp">check duplicates</a> </li>  
                     <li><a href="dqa_unmarkeddata.jsp">check incomplete records</a> </li> 
                     <li><a href="dqanullform.jsp">check null form number</a> </li>  
                     <li><a href="backuphistory.jsp">backup history</a> </li>  
                          
                    </ul>
                    </li>
                    
                     
                    <li><a href="help/HC1.pdf"><img src="images/help.png" width="24px"/> help</a></li>
                        <li><a href="logout.jsp"><img src="images/logout.png"/> logout</a></li>
                        
                        
                        <li>
                   
               
                  <%if(session.getAttribute("username")!=null){
      out.println("<a style=\"background-color:white;\"><font color=\"orange\">Hi "+session.getAttribute("username")+"</font></a>");                  
    
    %>    
                
                  
                 <% } %> 
                     
                </li>
                        
		</ul>
		<div class="clearfix"></div>
	</nav>

</body>

</html>