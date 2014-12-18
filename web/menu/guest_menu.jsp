<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
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
	
	
	<nav >
		<ul class="menu" >
			
<li style="margin-left: 400px;"><a href="#"><img src="images/rpt.png" width="24px"/> Reports</a>
           <ul>
   
                <!--<li><a href="group_reports.jsp">Group Reports(Individual)</a></li>--> 
                
                   <li ><a href="kePMS_excel_report.jsp"><font color="red">PEPFAR Report (excel)</font></a></li>
                   <li><a href="groups_overall.jsp"><font color="red">Groups Completion rate (excel)</font></a></li>
                   <li><a href="agebasedkepms.jsp"><font color="red">Kepms report by age (excel)</font></a></li>
                   <li><a href="partnerbasedreport.jsp"><font color="red">Kepms report by Target Pop. (excel)</font></a></li>
                   <li><a href="RawData.jsp"><font color="red">Raw Data (excel)</font></a></li>
                   <li><a href="overall_target_bar.jsp">Target Populations Completion Rates (bar)</a></li>
                  <li><a href="kePMS_bar.jsp">PEPFAR Report (bar)</a></li>
                  <li><a href="county_bar.jsp">County Completion Rate (bar)</a></li>
                  
                        <li><a href="target_pie.jsp"><font color="blue">Target Population Completion Rate (pie)</font></a></li>
                    </ul>   
                    
                </li>
<!--                 <li><a href="#">Bar Graphs</a>   
                 <ul> 
                 <li><a href="overall_partner_bar.jsp">Partner Bar Chart</a></li> 
                 <li><a href="overall_target_bar.jsp"> By Target</a></li>
                  <li><a href="kePMS_bar.jsp">kePMS Report</a></li>
                
                    </ul>  
                    
                </li>-->
                        
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
