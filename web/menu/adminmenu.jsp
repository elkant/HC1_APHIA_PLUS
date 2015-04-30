<%-- 
    Document   : adminmenu
    Created on : Mar 6, 2014, 3:50:46 PM
    Author     : SIXTYFOURBIT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->

<html>
    <head>
        <title>admin</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
       <link href="style.css" media="screen" rel="stylesheet" type="text/css" />
		<link href="iconic.css" media="screen" rel="stylesheet" type="text/css" />
		<script src="prefix-free.js"></script>
        
    </head>
    <body>
       


<nav>

    <ul class="menu">

<!--             <li><a href="#">Excel</a>   
                 <ul> 
                 <li><a href="group_reports.jsp">Group Reports(Individual)</a></li> 
                  <li><a href="groups_overall.jsp">Groups Completion rate</a></li>
                  <li><a href="kePMS_excel_report.jsp">kePMS report</a></li>
                
                    </ul>  
                    
                </li>
                 <li><a href="#">Bar Graphs</a>   
                 <ul> 
                 <li><a href="overall_partner_bar.jsp">Partner Bar Chart</a></li> 
                 <li><a href="overall_target_bar.jsp"> By Target</a></li>
                  <li><a href="kePMS_bar.jsp">kePMS Report</a></li>
                
                    </ul>  
                    
                </li>-->
<!--                 <li><a href="audit.jsp">Audit</a></li>    -->
                <li><a href="#">Users</a>
                 <ul>
                     <li><a href="newClerk.jsp"> Add User</a></li>
                     <li><a href="edit_clerk.jsp"> Edit User</a>
                     <li><a href="#"> Remove User</a>
                         
                     
                     </li>
                     
                 </ul>  
                </li>
                                                          <li><a href="#"><img src="images/entries.png" width="24px"/>Entries</a>
				<ul>
                                         <li><a href="add_district.jsp">add District</a></li>
                                         <li><a href="edit_district.jsp">Edit District</a></li>
                                        <li><a href="add_partner2.jsp"><span class="iconic plus-alt"></span>add Partner</a></li>
					<li><a href="edit_partner.jsp"><img src="images/edit.png"/>Edit Partner</a></li>
                                        <li><a href="add_target_pop1.jsp">Add Target Population</a></li>
					<li><a href="edit_target_population.jsp"><img src="images/edit.png"/>Edit target Population</a></li> 
					<li><a href="addtargets.jsp">Add Annual Targets</a></li> 
					 
 
				</ul>
			</li>
                        
			     <li><a href="#"><img src="images/facil.png"/>Curriculum</a>
				<ul>
                                      
					<li><a href="add_curr.jsp">Add curriculum</a></li>
                                      <li><a href="edit_curriculum.jsp">Edit curriculum</a></li>
                                      <li><a href="add_topic.jsp">Add Topic</a></li>
                                      <li><a href="edit_topic.jsp">Edit Topic</a></li>
				</ul>
			</li> 
                        			<li><a href="#"> <img src="images/group.png"/> Groups Entries</a>
				<ul>
                                   <li><a href="FormWizard.jsp">HC Form Entry</a></li>
<!--                                    <li><a href="add_group.jsp"><span class="iconic plus-alt"></span>add group</a></li>-->
					<li><a href="edit_group.jsp"><img src="images/edit.png"/>Edit Group</a></li>
<!--                                   <li><a href="add_facilitator.jsp"><span class="iconic plus-alt"></span>add facilitator</a></li>-->
					<li><a href="edit_facilitatorbn"><img src="images/edit.png"/>Edit facilitator</a></li>
<!--                                        <li><a href="filter_member.jsp"><span class="iconic plus-alt"></span>add a participant</a></li>-->
					<li><a href="geMemDetails"><img src="images/edit.png"/>Edit a participant</a></li>
                                </ul>
			</li>
			<li><a href="#"><img src="images/mark.png" width="24px"/>Attendance</a>
				<ul>
					<li><a href="filter_session.jsp">Mark Attendance</a></li>
					<li><a href="filter_session.jsp"><img src="images/edit.png"/>Edit Attendance</a></li>
                                        
				</ul>
			</li>
<!--               <li><a href="#">Deletes</a>
                 <ul>
                  
                    <li><a href="DeleteTeacher">Delete Facilitator</a></li>
                  <li><a href="delete_student.jsp">Delete Participant</a></li>
                     
                 </ul>  
                </li>-->
                
                
<!--                 <li><a href="#">Help</a>-->

<li><a href="#"> <img src="images/rpt.png" width="24px"/> Reports</a>
             <ul>
   
                <!--<li><a href="group_reports.jsp">Group Reports(Individual)</a></li>--> 
                
            <li ><a href="kePMS_excel_report.jsp"><font color="red">PEPFAR Report (excel)</font></a></li>
            <li><a href="groups_overall.jsp"><font color="red">Groups Completion rate (excel)</font></a></li>
            <li><a href="RawData.jsp"><font color="red">Raw Data (excel)</font></a></li>                   
                   <li><a href="agebasedkepms.jsp"><font color="red">Kepms report by age (excel)</font></a></li>
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
                     <li><a href="backupdata.jsp" ><img src="images/backup_send.png" width="24px"/>
                           Send County Backup </a> </li>  
                     <li><a href="localbackup.jsp" ><img src="images/backup.png" width="24px"/>
                            Send Backup to M&E Ofr.</a> </li>  
                     <li><a href="mergedata.jsp"><img src="images/import.png" width="24px"/>
                            Merge Data</a> </li>  
                      <li><a href="uploadhclivedata.jsp"><img src="images/import.png" width="24px"/>
                            UPLOAD HC LIVE Data</a> </li>  
                    </ul>
                    </li>
                    
                      <li><a href="#"><img src="images/backup.png" width="24px"/>
                             Management</a>
                    <ul>
                       <li><a href="mandemail.jsp"><img src="images/import.png" width="24px"/>
                            M&E Ofr. email</a> </li>  
                        <li><a href="update_sdm_email.jsp">Snr.Data Manager email</a></li>
                      
                     <li><a href="edit_ur_details.jsp">edit user details</a> </li>  
                          
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
                 <li><a href="logout.jsp">logout</a>
                   
                </li>
                
                
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
