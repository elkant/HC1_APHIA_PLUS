/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  import java.io.*;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author Geofrey Nyabuto
 */
public class group_completion_rate extends HttpServlet {
HttpSession session;
int max_lessons=0,current_sessions=0;
String group_name="";
 int completed=0,partial=0,incomplete=0, total_sessions=0,sessions_attended=0,counter=0;
 double all_per=0, partial_per=0,incomplete_par=0,all=0,attended_per=0;
 String path,path2;
 String partner_name="",target_name="";
    private String nextpage;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("application/vnd.ms-excell");
       
        PrintWriter out = response.getWriter();
//        outputStream out1=null;
        try {
//            ^^^^^^^^^^^^^^^^^^^^GET CALENDAR INSTANCE^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);

String dates=year+"-"+month+"-"+date+"-"+hour+"-"+min+"-"+sec;
            
         
            
            
            session=request.getSession();
            dbConn conn=new dbConn();
            
            
            String period,yea;
            period=session.getAttribute("period").toString();
            yea=session.getAttribute("year").toString();
            
//            ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
             HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet();
  HSSFRow rw1=shet1.createRow(1);
  HSSFCell cell;
  cell=rw1.createCell(4);
  cell.setCellValue("Overall Groups Attendance Report");
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,4,12));
       
         HSSFFont defaultFont= wb.createFont();
    defaultFont.setFontHeightInPoints((short)10);
    defaultFont.setFontName("Arial");
    defaultFont.setColor(IndexedColors.BLACK.getIndex());
//    defaultFont.setBold(false);
    defaultFont.setItalic(false);
  
   HSSFRow rw4=shet1.createRow(3);
 rw4.createCell(1).setCellValue("Number");
  rw4.createCell(2).setCellValue("Partner Name");
   rw4.createCell(3).setCellValue("Target Population");
 rw4.createCell(4).setCellValue("Group Name");
rw4.createCell(5).setCellValue("Facilitator");
 rw4.createCell(6).setCellValue("# of Peers");
 rw4.createCell(7).setCellValue("End Date");
  rw4.createCell(8).setCellValue("Start Date");
 rw4.createCell(9).setCellValue("Attended All Sessions");
 rw4.createCell(10).setCellValue("over 50% attendance");  
 rw4.createCell(11).setCellValue("less than 50% attendance"); 
 rw4.createCell(12).setCellValue("Overall Attendance");  

 
 HSSFFont font= wb.createFont();
    font.setFontHeightInPoints((short)10);
    font.setFontName("Arial");
    font.setColor(IndexedColors.WHITE.getIndex());
    font.setBoldweight((short)10);
    font.setItalic(false);
 
  HSSFCellStyle stylex=rw4.getRowStyle();
    stylex.setFillForegroundColor(HSSFColor.ORANGE.index);
    stylex.setFillPattern(CellStyle.SOLID_FOREGROUND);
    stylex.setAlignment(CellStyle.ALIGN_CENTER);
    stylex.setFont(font);
 
 
 int i=4;
            String groups_selector="select * from groups";
            conn.rs=conn.st.executeQuery(groups_selector);
            while(conn.rs.next()){
                group_name=conn.rs.getString("group_name");
//                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                ^^^^^^^^^^^^^^^^^Get total sessions per target population^^^^^^^^^^^^^^^^^^^^^^^
                String curriculum_selector="select * from curriculum where target_id='"+conn.rs.getString("target_pop_id") +"'";
                conn.rs0=conn.st0.executeQuery(curriculum_selector);
                conn.rs0.next();
                max_lessons=Integer.parseInt( conn.rs0.getString("no_of_lessons").toString());
//                ^^^^^^^^^^^^^^^GET PARTNER NAME^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
       String partner_selector="select * from partners where partner_id='"+conn.rs.getString("partner_id") +"'";
                conn.rs0=conn.st0.executeQuery(partner_selector);
                conn.rs0.next();
                partner_name=conn.rs0.getString("partner_name");
//         ^^^^^^^^^^TARGET NAME^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^       
        String target_selector="select * from target_population where target_id='"+conn.rs.getString("target_pop_id") +"'";
                conn.rs0=conn.st0.executeQuery(target_selector);
                conn.rs0.next();
                target_name=conn.rs0.getString("population_name");
//                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//  ^^^^^^^^^^^^^^^^^^^^GET NUMBER OF SESSIONS ATTENDED BY EACH MEMBER^^^^^^^^^^^^^^^^^
  String participant_selector1="select count(member_id) from member_details where group_id>='"+conn.rs3.getString("group_id") +"' && sessions_attended='"+max_lessons+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector1);
if(conn.rs4.next()==true){
          completed+=conn.rs4.getInt(1);
}
  String participant_selector2="select count(member_id) from member_details where group_id='"+conn.rs3.getString("group_id") +"' && sessions_attended<'"+max_lessons+"' && sessions_attended>='"+max_lessons/2+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector2);
if(conn.rs4.next()==true){
          partial+=conn.rs4.getInt(1);
}
  String participant_selector3="select count(member_id) from member_details where group_id='"+conn.rs3.getString("group_id") +"' && sessions_attended<'"+max_lessons/2+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector3);
if(conn.rs4.next()==true){
          incomplete+=conn.rs4.getInt(1);
}
       if(counter*max_lessons==0){
         session.setAttribute("saved_path", "Failed to generate the excell sheet: Divide by Zero Error");
         nextpage="groups_completion_rate.jsp";
       } 
       else{
            attended_per=(sessions_attended*100)/(counter*max_lessons); 
            all=completed+partial+incomplete;
            all_per=(completed*100)/all;
partial_per=(partial*100)/all;
incomplete_par=(incomplete*100)/all;
String aller=String.format("%.0f", all_per);
String aller_partial=String.format("%.0f", partial_per);
String aller_incomplete=String.format("%.0f", incomplete_par);
String attended=String.format("%.0f", attended_per);

//try
System.out.println("Group name "+group_name);
System.out.println("percentage completed "+aller);
System.out.println("partially completed "+aller_partial);
System.out.println("Percentage Incomplete "+aller_incomplete);
System.out.println("Overall Group Attendance "+attended);
System.out.println("");
System.out.println("");
String member_det_selector="select * from member_details where group_id='"+conn.rs.getString("group_id") +"'&& year='"+yea+"' && period='"+period+"' ";
conn.rs1=conn.st1.executeQuery(member_det_selector);
if(conn.rs1.next()==true){
String member_id=conn.rs1.getString("member_id");
String register_selector="select * from register_attendance where member_id='"+member_id+"'";
conn.rs2=conn.st2.executeQuery(register_selector);
conn.rs2.next();
String facilitator_id=conn.rs2.getString("facilitator_id");
String facilitator_selector="select * from facilitator_details where facilitator_id='"+facilitator_id+"'";
conn.rs2=conn.st2.executeQuery(facilitator_selector);
conn.rs2.next();
String fname=conn.rs2.getString("first_name");
String sname=conn.rs2.getString("sur_name");
String name=fname+" "+sname;

String date_selector="select * from new_topic where facilitator_id='"+facilitator_id+"'";
conn.rs2=conn.st2.executeQuery(date_selector);
conn.rs2.next();
String start_date1=conn.rs2.getString("start_date");
String[] dater1=start_date1.split("/");
String mn1=dater1[0];
String dy1=dater1[1];
String yr1=dater1[2];
String start_date=dy1+" - "+mn1+" - "+yr1;
String end_date1=conn.rs2.getString("end_date");
String[] dater2=end_date1.split("/");
String mn2=dater2[0];
String dy2=dater2[1];
String yr2=dater2[2];
String end_date=dy2+" - "+mn2+" - "+yr2;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//^^^^^^^^^^^^^^^OUTPUT TO EXCELL^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

 HSSFRow rwi=shet1.createRow(i);       
   rwi.createCell(1).setCellValue(i-3);
 rwi.createCell(2).setCellValue(partner_name);
 rwi.createCell(3).setCellValue(target_name);
 rwi.createCell(4).setCellValue(group_name);
 rwi.createCell(5).setCellValue(name);
  rwi.createCell(6).setCellValue(counter);
 rwi.createCell(7).setCellValue(start_date); 
 rwi.createCell(8).setCellValue(end_date);
rwi.createCell(9).setCellValue(aller+"%");
 rwi.createCell(10).setCellValue(aller_partial+"%");
  rwi.createCell(11).setCellValue(aller_incomplete+"%");
 rwi.createCell(12).setCellValue(attended+"%");

}
i++;





completed=partial=incomplete=total_sessions=0;
all_per=partial_per=incomplete_par=all=0;
attended_per=0;
sessions_attended=counter=0;

            }
             File[] roots = File.listRoots();

    /* Check what file roots exist and use either of them to create paths */
    for (File root : roots) {
    //  System.out.println("File system root: " + root.getAbsolutePath());
      //if drive c exists, then use it to store the file
      
      if(root.getAbsolutePath().equals("C:\\")){
       path="C:\\HC_REPORTS\\GROUP";
       path2="C:\\HC_REPORTS";
        session.setAttribute("saved_path", "<font color=\"grey\">The report has neeb saved in path: <a href=\""+path+"\">"+path+"</a></font><br/> ");
        break;
      }
         new File(path).mkdirs();
    }
       response.setHeader("Content-Disposition", "attachment;filename=sample.xls");
       wb.write(response.getOutputStream()); // Write workbook to response.

            }
//            wb.close();
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(group_completion_rate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(group_completion_rate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
