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
import java.util.Date;
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
public class groups_completion_rate extends HttpServlet {
HttpSession session;
int max_lessons=0,current_sessions=0;
String group_name="";
 int completed=0,partial=0,incomplete=0, over=0, total_sessions=0,sessions_attended=0,counter=0;
 double all_per=0, partial_per=0,incomplete_par=0,all=0,attended_per=0,over_per=0;
 String path,path2;
 String partner_name="",target_name="";
    private String nextpage;
     String start_date="";
String end_date ="",name="" ;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
 Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);

String dates=year+"-"+month+"-"+date+"-"+hour+"-"+min+"-"+sec;
 String facilitator_name="" ;          
 String start_date="";
String end_date ="" ;                 
            
            
            session=request.getSession();
            dbConn conn=new dbConn();
            
            String period,yea;
            period=session.getAttribute("period").toString();
            yea=session.getAttribute("year").toString();
//            ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
             HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet();
  HSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)24);
    font.setFontName("Arial Black");
//    font.setItalic(true);
    font.setBoldweight((short)24);
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    
     HSSFFont font2=wb.createFont();
 font2.setFontHeightInPoints((short)18);
    font2.setFontName("Arial Black");
//    font.setItalic(true);
    font2.setBoldweight((short)24);
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
    
//  HSSFSheet sheet1 = wb.getSheetAt(0);
    shet1.setColumnWidth(0, 4000); 
    shet1.setColumnWidth(1, 7000); 
    shet1.setColumnWidth(2, 6000); 
    shet1.setColumnWidth(3, 6000); 
  HSSFRow rw1=shet1.createRow(1);
  HSSFCell cell;
  cell=rw1.createCell(1);
  cell.setCellValue("Overall Groups Attendance Report");
   cell.setCellStyle(style);
  rw1.setHeightInPoints(30);
  
  
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,1,3));
            
   HSSFRow rw4=shet1.createRow(3);
    rw4.setHeightInPoints(25);
    rw4.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
    HSSFCell cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13;
    
   cell1=rw4.createCell(0);
   cell2=rw4.createCell(1);
   cell3=rw4.createCell(2);
   cell4=rw4.createCell(3);
   cell5=rw4.createCell(4);
   cell6=rw4.createCell(5);
   cell7=rw4.createCell(6);
   cell8=rw4.createCell(7);
   cell9=rw4.createCell(8);
   cell10=rw4.createCell(9);
   cell11=rw4.createCell(10);
   cell12=rw4.createCell(11);
//   cell13=rw4.createCell(12);
          
         cell1 .setCellValue("Partner Name");
   cell2.setCellValue("Target Population");
 cell3.setCellValue("Group Name");
cell4.setCellValue("Facilitator");
 cell5.setCellValue("# of Peers");
 cell6.setCellValue("End Date");
  cell7.setCellValue("Start Date");
 cell8.setCellValue("Over 100%");
cell9.setCellValue("Attended All Sessions");
 cell10.setCellValue("over 50% attendance");  
 cell11.setCellValue("less than 50% attendance"); 
 cell12.setCellValue("Overall Attendance"); 
 
 HSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.LIME.index);
stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

HSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);

cell1.setCellStyle(stylex);
cell2.setCellStyle(stylex);
cell3.setCellStyle(stylex);
cell4.setCellStyle(stylex);
cell5.setCellStyle(stylex);
cell6.setCellStyle(stylex);
cell7.setCellStyle(stylex);
cell8.setCellStyle(stylex);
cell9.setCellStyle(stylex);
cell10.setCellStyle(stylex);
cell11.setCellStyle(stylex);
cell12.setCellStyle(stylex);

 
 int i=4;
            String groups_selector="select * from groups order by group_name";
            conn.rs=conn.st.executeQuery(groups_selector);
            while(conn.rs.next()){
                group_name=conn.rs.getString("group_name").toUpperCase();
//                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                ^^^^^^^^^^^^^^^^^Get total sessions per target population^^^^^^^^^^^^^^^^^^^^^^^
                String curriculum_selector="select * from curriculum where target_id='"+conn.rs.getString("target_pop_id") +"'";
                conn.rs0=conn.st0.executeQuery(curriculum_selector);
                conn.rs0.next();
                max_lessons=conn.rs0.getInt("no_of_lessons");
//                ^^^^^^^^^^^^^^^GET PARTNER NAME^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
       String partner_selector="select * from partner where partner_id='"+conn.rs.getString("partner_id") +"' order by partner_name";
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
//  ^^^^^^^^^^^^^^^^^^^^GET NUMBER OF SESSIONS ATTENDED BY EACH MEMBER^^^^^^^^^^^^^^^^^
  String participant_selector="select count(member_id),SUM(sessions_attended) from member_details where group_id='"+conn.rs.getString("group_id") +"' && sessions_attended>'"+max_lessons+"' && year='"+yea+"' && period='"+period+"' GROUP BY sessions_attended";
  conn.rs4=conn.st4.executeQuery(participant_selector);
if(conn.rs4.next()==true){
          over+=conn.rs4.getInt(1);
           sessions_attended+=conn.rs4.getInt(2);
}
  String participant_selector1="select count(member_id),SUM(sessions_attended) from member_details where group_id='"+conn.rs.getString("group_id") +"' && sessions_attended='"+max_lessons+"' && year='"+yea+"' && period='"+period+"' GROUP BY sessions_attended";
  conn.rs4=conn.st4.executeQuery(participant_selector1);
if(conn.rs4.next()==true){
          completed+=conn.rs4.getInt(1);
           sessions_attended+=conn.rs4.getInt(2);
}
  String participant_selector2="select count(member_id),SUM(sessions_attended) from member_details where group_id='"+conn.rs.getString("group_id") +"' && sessions_attended<'"+max_lessons+"' && sessions_attended>='"+max_lessons/2+"' && year='"+yea+"' && period='"+period+"' GROUP BY sessions_attended";
  conn.rs4=conn.st4.executeQuery(participant_selector2);
if(conn.rs4.next()==true){
          partial+=conn.rs4.getInt(1);
           sessions_attended+=conn.rs4.getInt(2);
}
  String participant_selector3="select count(member_id),SUM(sessions_attended) from member_details where group_id='"+conn.rs.getString("group_id") +"' && sessions_attended<'"+max_lessons/2+"' && year='"+yea+"' && period='"+period+"' GROUP BY sessions_attended";
  conn.rs4=conn.st4.executeQuery(participant_selector3);
if(conn.rs4.next()==true){
          incomplete+=conn.rs4.getInt(1);
          sessions_attended+=conn.rs4.getInt(2);
}
all=completed+partial+incomplete;

       if(all*max_lessons==0){
         session.setAttribute("saved_path", "Failed to generate the excell sheet: Divide by Zero Error");
         nextpage="groups_completion_rate.jsp";
       } 
       if((all*max_lessons)>0){
            attended_per=(sessions_attended*100)/(all*max_lessons); 
            all_per=(completed*100)/all;
partial_per=(partial*100)/all;
incomplete_par=(incomplete*100)/all;
String aller=String.format("%.0f", all_per);
String aller_partial=String.format("%.0f", partial_per);
String aller_incomplete=String.format("%.0f", incomplete_par);
String attended=String.format("%.0f", attended_per);
String over_100=String.format("%.0f", over_per);
//try
System.out.println("Group name "+group_name);
System.out.println("percentage over "+over_100);
System.out.println("percentage completed "+aller);
System.out.println("partially completed "+aller_partial);
System.out.println("Percentage Incomplete "+aller_incomplete);
System.out.println("Overall Group Attendance "+attended);
System.out.println("");
System.out.println("");
String member_det_selector="select * from member_details where group_id='"+conn.rs.getString("group_id") +"'&& year='"+yea+"' && period='"+period+"' ";
conn.rs1=conn.st1.executeQuery(member_det_selector);
String member_id="";
if(conn.rs1.next()==true){
member_id=conn.rs1.getString("member_id");
}
String register_selector="select * from register_attendance where member_id='"+member_id+"'";
conn.rs2=conn.st2.executeQuery(register_selector);
while(conn.rs2.next()){
String facilitator_id=conn.rs2.getString("facilitator_id");

String facilitator_selector="select * from facilitator_details where facilitator_id='"+facilitator_id+"'";
conn.rs3=conn.st3.executeQuery(facilitator_selector);
String sname="",fname="";
if(conn.rs3.next()==true){
fname=conn.rs3.getString("first_name").toUpperCase();
sname=conn.rs3.getString("sur_name").toUpperCase();
}
 facilitator_name=fname.toUpperCase()+" "+sname.toUpperCase();
String date_selector="select * from new_topic where facilitator_id='"+facilitator_id+"'";
conn.rs3=conn.st3.executeQuery(date_selector);
if(conn.rs3.next()==true){
String start_date1=conn.rs3.getString("start_date");
String[] dater1=start_date1.split("/");
String mn1=dater1[0];
String dy1=dater1[1];
String yr1=dater1[2];
start_date=dy1+"/"+mn1+"/"+yr1;
String end_date1=conn.rs3.getString("end_date");
String[] dater2=end_date1.split("/");
String mn2=dater2[0];
String dy2=dater2[1];
String yr2=dater2[2];
end_date=dy2+"/"+mn2+"/"+yr2;
}
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//^^^^^^^^^^^^^^^OUTPUT TO EXCELL^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
if(all>0){
 HSSFRow rwi=shet1.createRow(i);       
//   rwi.createCell(1).setCellValue(i-3);
  rwi.setHeightInPoints(20);
 rwi.createCell(0).setCellValue(partner_name);
 rwi.createCell(1).setCellValue(target_name);
 rwi.createCell(2).setCellValue(group_name);
 rwi.createCell(3).setCellValue(facilitator_name);
  rwi.createCell(4).setCellValue(all);
 rwi.createCell(5).setCellValue(start_date); 
 rwi.createCell(6).setCellValue(end_date);
 rwi.createCell(7).setCellValue(over_100+"%");
 rwi.createCell(8).setCellValue(aller+"%");
 rwi.createCell(9).setCellValue(aller_partial+"%");
  rwi.createCell(10).setCellValue(aller_incomplete+"%");
 rwi.createCell(11).setCellValue(attended+"%");



i++;

}

completed=partial=incomplete=total_sessions=0;
all_per=partial_per=incomplete_par=all=0;
attended_per=0;
sessions_attended=counter=0;
over=0;
over_per=0;
            }
            }
    
   }
            
      Date dat= new Date();
      
     String  dat1=dat.toString().replace(" ","_");
// write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=Overall_Groups_Completion_Rate"+dat1+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
//response.sendRedirect("index.jsp");
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
            Logger.getLogger(groups_completion_rate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(groups_completion_rate.class.getName()).log(Level.SEVERE, null, ex);
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
