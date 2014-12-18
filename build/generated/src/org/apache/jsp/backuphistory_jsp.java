package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Calendar;
import java.util.Random;
import hc.dbConn1;

public final class backuphistory_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/menu/adminmenu.jsp");
    _jspx_dependants.add("/menu/clerkmenu.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

//this is code to make sure the browser does not cache the pages
//and once logged out, session invalidated, send to login page
    response.setHeader("Cache-Control", "no-store"); //HTTP 1.1
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
    response.setDateHeader("Expires", 0); //prevents caching at the proxy server

    if (session.getAttribute("level")!=null) {}
       else{
        response.sendRedirect("index.jsp");
    } 

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/divCss_1.css\"/>\n");
      out.write("            <link rel=\"shortcut icon\" href=\"images/hc_logo.png\"/>\n");
      out.write("<!--        <link rel=\"stylesheet\"  type=\"text/css\" href=\"menu/adminmenu_files/css3menu1/style.css\" />-->\n");
      out.write("       <!--menu-->\n");
      out.write("<link href=\"css/style.css\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<link href=\"iconic.css\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("<script src=\"prefix-free.js\"></script>\n");
      out.write("        <title>Backup data</title>\n");
      out.write("      \n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-1.9.1.js\"></script>\n");
      out.write("    <script type=\"text/javascript\" src=\"js/jquery-ui.js\"></script>   \n");
      out.write("   \n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"js/noty/jquery.noty.js\"></script>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"js/noty/layouts/top.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/noty/layouts/center.js\"></script>\n");
      out.write("<!-- You can add more layouts if you want -->\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"js/noty/themes/default.js\"></script>\n");
      out.write("\n");
      out.write("<!--clerk menu-->\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"menu/css/clerkmenucss.css\"/>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("    \n");
      out.write(" \n");
      out.write("   \n");
      out.write("function backupdata(){\n");
      out.write("\n");
      out.write("\n");
      out.write("if (window.XMLHttpRequest)\n");
      out.write("{// code for IE7+, Firefox, Chrome, Opera, Safari\n");
      out.write("xmlhttp=new XMLHttpRequest();\n");
      out.write("}\n");
      out.write("else\n");
      out.write("{// code for IE6, IE5\n");
      out.write("xmlhttp=new ActiveXObject(\"Microsoft.XMLHTTP\");\n");
      out.write("}\n");
      out.write("xmlhttp.onreadystatechange=function()\n");
      out.write("{\n");
      out.write("if (xmlhttp.readyState==4 && xmlhttp.status==200)\n");
      out.write("{\n");
      out.write("    \n");
      out.write("    \n");
      out.write("\n");
      out.write("var n = noty({text: '");
      out.print(session.getAttribute("datasend"));
      out.write("',\n");
      out.write("                        layout: 'center',\n");
      out.write("                        type: 'Success',\n");
      out.write(" \n");
      out.write("                         timeout: 4800});\n");
      out.write("\n");
      out.write("\n");
      out.write("}\n");
      out.write("}\n");
      out.write("xmlhttp.open(\"POST\",\"BackUp\",true);\n");
      out.write("xmlhttp.send();\n");
      out.write("}//end of filter districts \n");
      out.write("</script>\n");
      out.write("    </head>\n");
      out.write("    <body onload=\"\">\n");
      out.write("       \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <div id=\"container\"  >\n");
      out.write("       \n");
      out.write("            ");
      if(session.getAttribute("level").equals("0")){ 
      out.write("\n");
      out.write("   ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this template, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>admin</title>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>\n");
      out.write("       <link href=\"style.css\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("\t\t<link href=\"iconic.css\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("\t\t<script src=\"prefix-free.js\"></script>\n");
      out.write("        \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("       \n");
      out.write("\n");
      out.write("\n");
      out.write("<nav>\n");
      out.write("\n");
      out.write("    <ul class=\"menu\">\n");
      out.write("\n");
      out.write("<!--             <li><a href=\"#\">Excel</a>   \n");
      out.write("                 <ul> \n");
      out.write("                 <li><a href=\"group_reports.jsp\">Group Reports(Individual)</a></li> \n");
      out.write("                  <li><a href=\"groups_overall.jsp\">Groups Completion rate</a></li>\n");
      out.write("                  <li><a href=\"kePMS_excel_report.jsp\">kePMS report</a></li>\n");
      out.write("                \n");
      out.write("                    </ul>  \n");
      out.write("                    \n");
      out.write("                </li>\n");
      out.write("                 <li><a href=\"#\">Bar Graphs</a>   \n");
      out.write("                 <ul> \n");
      out.write("                 <li><a href=\"overall_partner_bar.jsp\">Partner Bar Chart</a></li> \n");
      out.write("                 <li><a href=\"overall_target_bar.jsp\"> By Target</a></li>\n");
      out.write("                  <li><a href=\"kePMS_bar.jsp\">kePMS Report</a></li>\n");
      out.write("                \n");
      out.write("                    </ul>  \n");
      out.write("                    \n");
      out.write("                </li>-->\n");
      out.write("<!--                 <li><a href=\"audit.jsp\">Audit</a></li>    -->\n");
      out.write("                <li><a href=\"#\">Users</a>\n");
      out.write("                 <ul>\n");
      out.write("                     <li><a href=\"newClerk.jsp\"> Add User</a></li>\n");
      out.write("                     <li><a href=\"edit_clerk.jsp\"> Edit User</a>\n");
      out.write("                     <li><a href=\"#\"> Remove User</a>\n");
      out.write("                         \n");
      out.write("                     \n");
      out.write("                     </li>\n");
      out.write("                     \n");
      out.write("                 </ul>  \n");
      out.write("                </li>\n");
      out.write("                                                          <li><a href=\"#\"><img src=\"images/entries.png\" width=\"24px\"/>Entries</a>\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("                                         <li><a href=\"add_district.jsp\">add District</a></li>\n");
      out.write("                                         <li><a href=\"edit_district.jsp\">Edit District</a></li>\n");
      out.write("                                        <li><a href=\"add_partner2.jsp\"><span class=\"iconic plus-alt\"></span>add Partner</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"edit_partner.jsp\"><img src=\"images/edit.png\"/>Edit Partner</a></li>\n");
      out.write("                                        <li><a href=\"add_target_pop1.jsp\">Add Target Population</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"edit_target_population.jsp\"><img src=\"images/edit.png\"/>Edit target Population</a></li> \n");
      out.write("\t\t\t\t\t<li><a href=\"addtargets.jsp\">Add Annual Targets</a></li> \n");
      out.write("\t\t\t\t\t \n");
      out.write(" \n");
      out.write("\t\t\t\t</ul>\n");
      out.write("\t\t\t</li>\n");
      out.write("                        \n");
      out.write("\t\t\t     <li><a href=\"#\"><img src=\"images/facil.png\"/>Curriculum</a>\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("                                      \n");
      out.write("\t\t\t\t\t<li><a href=\"add_curr.jsp\">Add curriculum</a></li>\n");
      out.write("                                      <li><a href=\"edit_curriculum.jsp\">Edit curriculum</a></li>\n");
      out.write("                                      <li><a href=\"add_topic.jsp\">Add Topic</a></li>\n");
      out.write("                                      <li><a href=\"edit_topic.jsp\">Edit Topic</a></li>\n");
      out.write("\t\t\t\t</ul>\n");
      out.write("\t\t\t</li> \n");
      out.write("                        \t\t\t<li><a href=\"#\"> <img src=\"images/group.png\"/> Groups Entries</a>\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("                                   <li><a href=\"FormWizard.jsp\">HC Form Entry</a></li>\n");
      out.write("<!--                                    <li><a href=\"add_group.jsp\"><span class=\"iconic plus-alt\"></span>add group</a></li>-->\n");
      out.write("\t\t\t\t\t<li><a href=\"edit_group.jsp\"><img src=\"images/edit.png\"/>Edit Group</a></li>\n");
      out.write("<!--                                   <li><a href=\"add_facilitator.jsp\"><span class=\"iconic plus-alt\"></span>add facilitator</a></li>-->\n");
      out.write("\t\t\t\t\t<li><a href=\"edit_facilitatorbn\"><img src=\"images/edit.png\"/>Edit facilitator</a></li>\n");
      out.write("<!--                                        <li><a href=\"filter_member.jsp\"><span class=\"iconic plus-alt\"></span>add a participant</a></li>-->\n");
      out.write("\t\t\t\t\t<li><a href=\"geMemDetails\"><img src=\"images/edit.png\"/>Edit a participant</a></li>\n");
      out.write("                                </ul>\n");
      out.write("\t\t\t</li>\n");
      out.write("\t\t\t<li><a href=\"#\"><img src=\"images/mark.png\" width=\"24px\"/>Attendance</a>\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("\t\t\t\t\t<li><a href=\"filter_session.jsp\">Mark Attendance</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"filter_session.jsp\"><img src=\"images/edit.png\"/>Edit Attendance</a></li>\n");
      out.write("                                        \n");
      out.write("\t\t\t\t</ul>\n");
      out.write("\t\t\t</li>\n");
      out.write("<!--               <li><a href=\"#\">Deletes</a>\n");
      out.write("                 <ul>\n");
      out.write("                  \n");
      out.write("                    <li><a href=\"DeleteTeacher\">Delete Facilitator</a></li>\n");
      out.write("                  <li><a href=\"delete_student.jsp\">Delete Participant</a></li>\n");
      out.write("                     \n");
      out.write("                 </ul>  \n");
      out.write("                </li>-->\n");
      out.write("                \n");
      out.write("                \n");
      out.write("<!--                 <li><a href=\"#\">Help</a>-->\n");
      out.write("\n");
      out.write("<li><a href=\"#\"> <img src=\"images/rpt.png\" width=\"24px\"/> Reports</a>\n");
      out.write("             <ul>\n");
      out.write("   \n");
      out.write("                <!--<li><a href=\"group_reports.jsp\">Group Reports(Individual)</a></li>--> \n");
      out.write("                \n");
      out.write("            <li ><a href=\"kePMS_excel_report.jsp\"><font color=\"red\">PEPFAR Report (excel)</font></a></li>\n");
      out.write("            <li><a href=\"groups_overall.jsp\"><font color=\"red\">Groups Completion rate (excel)</font></a></li>\n");
      out.write("            <li><a href=\"RawData.jsp\"><font color=\"red\">Raw Data (excel)</font></a></li>                   \n");
      out.write("                   <li><a href=\"agebasedkepms.jsp\"><font color=\"red\">Kepms report by age (excel)</font></a></li>\n");
      out.write("                   <li><a href=\"partnerbasedreport.jsp\"><font color=\"red\">Kepms report by Target Pop. (excel)</font></a></li>\n");
      out.write("                <li><a href=\"overall_target_bar.jsp\">Target Populations Completion Rates (bar)</a></li>\n");
      out.write("                  <li><a href=\"kePMS_bar.jsp\">PEPFAR Report (bar)</a></li>\n");
      out.write("                  <li><a href=\"county_bar.jsp\">County Completion Rate (bar)</a></li>\n");
      out.write("                  \n");
      out.write("                        <li><a href=\"target_pie.jsp\"><font color=\"blue\">Target Population Completion Rate (pie)</font></a></li>\n");
      out.write("                       \n");
      out.write("                    </ul>  \n");
      out.write("                      </li>\n");
      out.write("\n");
      out.write("               <li><a href=\"#\"><img src=\"images/backup.png\" width=\"24px\"/>\n");
      out.write("                             Data</a>\n");
      out.write("                    <ul>\n");
      out.write("                     <li><a href=\"backupdata.jsp\" ><img src=\"images/backup_send.png\" width=\"24px\"/>\n");
      out.write("                           Send County Backup </a> </li>  \n");
      out.write("                     <li><a href=\"localbackup.jsp\" ><img src=\"images/backup.png\" width=\"24px\"/>\n");
      out.write("                            Send Backup to M&E Ofr.</a> </li>  \n");
      out.write("                     <li><a href=\"mergedata.jsp\"><img src=\"images/import.png\" width=\"24px\"/>\n");
      out.write("                            Merge Data</a> </li>  \n");
      out.write("                      <li><a href=\"uploadhclivedata.jsp\"><img src=\"images/import.png\" width=\"24px\"/>\n");
      out.write("                            UPLOAD HC LIVE Data</a> </li>  \n");
      out.write("                    </ul>\n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                      <li><a href=\"#\"><img src=\"images/backup.png\" width=\"24px\"/>\n");
      out.write("                             Management</a>\n");
      out.write("                    <ul>\n");
      out.write("                       <li><a href=\"mandemail.jsp\"><img src=\"images/import.png\" width=\"24px\"/>\n");
      out.write("                            M&E Ofr. email</a> </li>  \n");
      out.write("                        <li><a href=\"update_sdm_email.jsp\">Snr.Data Manager email</a></li>\n");
      out.write("                      \n");
      out.write("                     <li><a href=\"edit_ur_details.jsp\">edit user details</a> </li>  \n");
      out.write("                          \n");
      out.write("                    </ul>\n");
      out.write("                    </li>\n");
      out.write("                     <li><a href=\"#\"><img src=\"images/backup.png\" width=\"24px\"/>\n");
      out.write("                             DQA</a>\n");
      out.write("                    <ul>\n");
      out.write("                   \n");
      out.write("                      \n");
      out.write("                     <li><a href=\"dqa_invaliddata.jsp\">check invalid dates</a> </li>  \n");
      out.write("                     <li><a href=\"dqa_duplicateshome.jsp\">check duplicates</a> </li>  \n");
      out.write("                      <li><a href=\"dqa_unmarkeddata.jsp\">check incomplete records</a> </li>       \n");
      out.write("                      <li><a href=\"dqanullform.jsp\">check null form number</a> </li>       \n");
      out.write("                    </ul>\n");
      out.write("                    </li>\n");
      out.write("                 <li><a href=\"logout.jsp\">logout</a>\n");
      out.write("                   \n");
      out.write("                </li>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                 <li>\n");
      out.write("                   \n");
      out.write("                  ");
if(session.getAttribute("username")!=null){
      out.println("<a style=\"background-color:white;\"><font color=\"orange\">Hi "+session.getAttribute("username")+"</font></a>");                  
    
    
      out.write("\n");
      out.write("                  \n");
      out.write("                \n");
      out.write("                  \n");
      out.write("                 ");
 } 
      out.write(" \n");
      out.write("                     \n");
      out.write("                </li>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("            </ul>\n");
      out.write("       <div class=\"clearfix\"></div> \n");
      out.write("        \n");
      out.write("</nav>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write('\n');
      out.write(' ');
}
    
      else{
      out.write("\n");
      out.write("       ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\t<head>\n");
      out.write("\t\t\n");
      out.write("\t\t<meta charset=\"utf-8\">\n");
      out.write("\t\t<title>Pure CSS3 Menu</title>\n");
      out.write("\t\t<link href=\"style.css\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("\t\t<link href=\"iconic.css\" media=\"screen\" rel=\"stylesheet\" type=\"text/css\" />\n");
      out.write("\t\t<script src=\"prefix-free.js\"></script>\n");
      out.write("\t</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("\t\n");
      out.write("\t\n");
      out.write("\t<nav>\n");
      out.write("\t\t<ul class=\"menu\">\n");
      out.write("\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("\t\t\t<li><a href=\"FormWizard.jsp\">HC Form Entry</a>\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t</li>\n");
      out.write("                    \n");
      out.write("\n");
      out.write("\t\t\t<li><a href=\"#\"> <img src=\"images/group.png\"/> Edit Entries</a>\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("<!--                                    <li><a href=\"add_group.jsp\"><span class=\"iconic plus-alt\"></span>add group</a></li>-->\n");
      out.write("\t\t\t\t\t<li><a href=\"edit_group.jsp\"><img src=\"images/edit.png\"/>Edit Group</a></li>\n");
      out.write("                                  <li><a href=\"edit_facilitatorbn\"><img src=\"images/edit.png\"/>Edit Facilitator</a></li>\n");
      out.write("                                  <li><a href=\"geMemDetails\"><img src=\"images/edit.png\"/>Edit  Participant</a></li>\n");
      out.write("                                </ul>\n");
      out.write("\t\t\t</li>\n");
      out.write("                        \n");
      out.write("<!--                        <li><a href=\"#\"> <img src=\"images/group.png\"/> Facilitators</a>\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("                                   \n");
      out.write("                                   <li><a href=\"add_facilitator.jsp\"><span class=\"iconic plus-alt\"></span>add facilitator</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"edit_facilitatorbn\"><img src=\"images/edit.png\"/>Edit facilitator</a></li>\n");
      out.write("                                        \n");
      out.write("                                </ul>\n");
      out.write("\t\t\t</li>-->\n");
      out.write("                      \n");
      out.write("                        \n");
      out.write("                        \n");
      out.write("                        \n");
      out.write("<!--                        <li><a href=\"#\"> <img src=\"images/group.png\"/> Participants</a>\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("                                   \n");
      out.write("                                        <li><a href=\"filter_member.jsp\"><span class=\"iconic plus-alt\"></span>add a participant</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"geMemDetails\"><img src=\"images/edit.png\"/>edit a participant</a></li>\n");
      out.write("                                </ul>\n");
      out.write("\t\t\t</li>\n");
      out.write("                        -->\n");
      out.write("                        \n");
      out.write("\t\t\t<li><a href=\"#\"><img src=\"images/mark.png\" width=\"24px\"/>Attendance</a>\n");
      out.write("\t\t\t\t<ul>\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<li><a href=\"filter_session.jsp\"><img src=\"images/edit.png\"/>Edit Attendance</a></li>\n");
      out.write("                                        \n");
      out.write("\t\t\t\t</ul>\n");
      out.write("\t\t\t</li>\n");
      out.write("                       \n");
      out.write("                        <li><a href=\"#\"><img src=\"images/rpt.png\" width=\"24px\"/> Reports</a>\n");
      out.write("            <ul>\n");
      out.write("   \n");
      out.write("                <!--<li><a href=\"group_reports.jsp\">Group Reports(Individual)</a></li>--> \n");
      out.write("                \n");
      out.write("                  <li ><a href=\"kePMS_excel_report.jsp\"><font color=\"red\">PEPFAR Report (excel)</font></a></li>\n");
      out.write("                   <li><a href=\"groups_overall.jsp\"><font color=\"red\">Groups Completion rate (excel)</font></a></li>\n");
      out.write("                   <li><a href=\"RawData.jsp\"><font color=\"red\">Raw Data (excel)</font></a></li>                   \n");
      out.write("                   <li><a href=\"agebasedkepms.jsp\"><font color=\"red\">kepms report by age (excel)</font></a></li>\n");
      out.write("            <li><a href=\"partnerbasedreport.jsp\"><font color=\"red\">Kepms report by Target Pop. (excel)</font></a></li>\n");
      out.write("               \n");
      out.write("                  <li><a href=\"overall_target_bar.jsp\">Target Populations Completion Rates (bar)</a></li>\n");
      out.write("                  <li><a href=\"kePMS_bar.jsp\">PEPFAR Report (bar)</a></li>\n");
      out.write("                  <li><a href=\"county_bar.jsp\">County Completion Rate (bar)</a></li>\n");
      out.write("                  \n");
      out.write("                        <li><a href=\"target_pie.jsp\"><font color=\"blue\">Target Population Completion Rate (pie)</font></a></li>\n");
      out.write("                    </ul>   \n");
      out.write("                      </li>\n");
      out.write("                        \n");
      out.write("                    <li><a href=\"#\"><img src=\"images/backup.png\" width=\"24px\"/>\n");
      out.write("                             Data</a>\n");
      out.write("                    <ul>\n");
      out.write("                        ");
if (session.getAttribute("username").equals("m&e")){
      out.write("\n");
      out.write("                        <li><a href=\"mergedata.jsp\"><img src=\"images/import.png\" width=\"24px\"/>\n");
      out.write("                            Merge Data</a> </li> \n");
      out.write("                        \n");
      out.write("                        <li><a href=\"backupdata.jsp\" ><img src=\"images/backup_send.png\" width=\"24px\"/>\n");
      out.write("                            Send County Backup</a> </li>  \n");
      out.write("                             ");
}
      out.write("\n");
      out.write("                        ");
if (!session.getAttribute("username").equals("m&e")){
      out.write("     \n");
      out.write("                     <li><a href=\"localbackup.jsp\" ><img src=\"images/backup.png\"  width=\"24px\"/>\n");
      out.write("                            Send Backup to M&E Ofr.</a> </li>  ");
}
      out.write("\n");
      out.write("                       ");
if (session.getAttribute("username").equals("m&e")){
      out.write("\n");
      out.write("                     <li><a href=\"update_sdm_email.jsp\"> Senior data Manager Mail</a></li>\n");
      out.write("                     ");
}
      out.write("\n");
      out.write("                     <li><a href=\"mandemail.jsp\"><img src=\"images/edit.png\"/>\n");
      out.write("                           Edit M&E officer email</a> </li>  \n");
      out.write("                          \n");
      out.write("                    </ul>\n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                     <li><a href=\"#\"><img src=\"images/backup.png\" width=\"24px\"/>\n");
      out.write("                             Management</a>\n");
      out.write("                    <ul>\n");
      out.write("                       ");
if (session.getAttribute("username").equals("m&e")){
      out.write("\n");
      out.write("                     <li><a href=\"newClerk.jsp\"><img src=\"images/group.png\"/> Add User</a></li>\n");
      out.write("                     ");
}
      out.write("\n");
      out.write("                      \n");
      out.write("<!--                     <li><a href=\"edit_ur_details.jsp\">edit user details</a> </li>  -->\n");
      out.write("                          \n");
      out.write("                    </ul>\n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                     <li><a href=\"#\"><img src=\"images/backup.png\" width=\"24px\"/>\n");
      out.write("                             DQA</a>\n");
      out.write("                    <ul>\n");
      out.write("                   \n");
      out.write("                      \n");
      out.write("                     <li><a href=\"dqa_invaliddata.jsp\">check invalid dates</a> </li>  \n");
      out.write("                     <li><a href=\"dqa_duplicateshome.jsp\">check duplicates</a> </li>  \n");
      out.write("                     <li><a href=\"dqa_unmarkeddata.jsp\">check incomplete records</a> </li> \n");
      out.write("                     <li><a href=\"dqanullform.jsp\">check null form number</a> </li>  \n");
      out.write("                          \n");
      out.write("                    </ul>\n");
      out.write("                    </li>\n");
      out.write("                    \n");
      out.write("                     \n");
      out.write("                    <li><a href=\"help/HC1.pdf\"><img src=\"images/help.png\" width=\"24px\"/> help</a></li>\n");
      out.write("                        <li><a href=\"logout.jsp\"><img src=\"images/logout.png\"/> logout</a></li>\n");
      out.write("                        \n");
      out.write("                        \n");
      out.write("                        <li>\n");
      out.write("                   \n");
      out.write("               \n");
      out.write("                  ");
if(session.getAttribute("username")!=null){
      out.println("<a style=\"background-color:white;\"><font color=\"orange\">Hi "+session.getAttribute("username")+"</font></a>");                  
    
    
      out.write("    \n");
      out.write("                \n");
      out.write("                  \n");
      out.write("                 ");
 } 
      out.write(" \n");
      out.write("                     \n");
      out.write("                </li>\n");
      out.write("                        \n");
      out.write("\t\t</ul>\n");
      out.write("\t\t<div class=\"clearfix\"></div>\n");
      out.write("\t</nav>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("\n");
      out.write("</html>");
      out.write("\n");
      out.write("      ");
}
   
      out.write("\n");
      out.write("            <br/>\n");
      out.write("            <h3 style=\"text-align: center; background-color: orange;\">VIEW BACKUP HISTORY</h3>\n");
      out.write("            <h5 style=\"text-align: center; \">Shows dates of Backup.</h5>\n");
      out.write("\n");
      out.write(" \n");
      out.write("            \n");
      out.write("              <div id=\"header\" align=\"center\" style=\"\" >\n");
      out.write("                  <br/>\n");
      out.write("                 \n");
      out.write(" \n");
      out.write("\n");
      out.write("<!------------------------------------------------------------------------------>\n");
      out.write("<!------------------------------------------------------------------------------>\n");
      out.write("<!------------------------------------------------------------------------------>\n");
      out.write("<!--------------------------BACKUP HISTORY CODE---------------------------------------------------->\n");
      out.write("<!------------------------------------------------------------------------------>\n");
      out.write("<!------------------------------------------------------------------------------>\n");
      out.write("<!------------------------------------------------------------------------------>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("           \n");
      out.write("                    \n");
      out.write("                     <br/>\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("              </div>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            <div id=\"content\" style=\"width:1000px; margin-left: 150px; \">\n");
      out.write("                \n");
      out.write("              \n");
      out.write("                <div id=\"midcontent\" style=\"margin-left:130px ;\">\n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                   \n");
      out.write("                        \n");
      out.write("                   \n");
      out.write("                   \n");
      out.write("                         ");
if (session.getAttribute("datasend") != null) {
                         
    
    
    
      out.write("\n");
      out.write("                                <script type=\"text/javascript\"> \n");
      out.write("                    \n");
      out.write("                    var n = noty({text: '");
      out.print(session.getAttribute("datasend"));
      out.write("',\n");
      out.write("                        layout: 'center',\n");
      out.write("                          type: 'Success',\n");
      out.write(" \n");
      out.write("                         timeout: 1800});\n");
      out.write("                    \n");
      out.write("                                    </script> \n");
      out.write("                ");

                session.removeAttribute("datasend");
                            }

                        
      out.write("\n");
      out.write("                        <!--creating random user id-->\n");
      out.write("                         \n");
      out.write("                        \n");
      out.write("                        \n");
      out.write("                        \n");
      out.write("                        \n");
      out.write("                   \n");
      out.write("                   <br/><br/>\n");
      out.write("                    \n");
      out.write("                    <form action=\"BackUp\" method=\"post\" style=\"height:90px;\">\n");
      out.write("                        <br/>\n");
      out.write("                        <input type=\"submit\" onclick=\"\" value=\"Send Backup\" style=\"height:50px;\" >\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            \n");
      out.write("\n");
      out.write("             <div id=\"footer\">\n");
      out.write("              <!--  <h2 align=\"left\"> <img src=\"images/Coat of arms.JPG\" alt=\"logo\" height=\"76px\" /></h2>-->\n");
      out.write("              \n");
      out.write("               ");

Calendar cal = Calendar.getInstance();
int year= cal.get(Calendar.YEAR);              


      out.write("\n");
      out.write("               <p align=\"center\">HC1 &copy Aphia Plus | USAID ");
      out.print(year);
      out.write("</p>\n");
      out.write("            </div>\n");
      out.write("        </div>    \n");
      out.write("        \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
