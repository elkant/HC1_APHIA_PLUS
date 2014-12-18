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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
/**
 *
 * @author Geofrey Nyabuto
 */
public class group_report extends HttpServlet {
 String group_id="",nextpage="",group_name="",partner_name="",partner_id="";
 String population_name,form_number;
HttpSession session;
String path,path2; 
int total_sessions;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
session=request.getSession();            
            dbConn conn=new dbConn();
     group_id=session.getAttribute("group_report_id").toString();
    String period,yea;
            period=session.getAttribute("period").toString();
            yea=session.getAttribute("year").toString();
   String target="select * from groups where group_id='"+group_id+"'";
                conn.rs=conn.st.executeQuery(target);
                if (conn.rs.next()==true)
                {
                    group_name=conn.rs.getString("group_name");
              partner_id=conn.rs.getString("partner_id") ;    
              String partnerSelector="select * from partner where partner_id='"+partner_id+"'";   
              conn.rs3=conn.st3.executeQuery(partnerSelector);
              if(conn.rs3.next()==true){
                  partner_name=conn.rs3.getString("partner_name");
              }
//              ^^^^^^^^^^^^^^^^^^^^Get the target population name^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//              ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
              String pop_select="select * from target_population where target_id='"+conn.rs.getString("target_pop_id") +"'";   
              conn.rs3=conn.st3.executeQuery(pop_select);
              while(conn.rs3.next()){
                  population_name=conn.rs3.getString("population_name");
              }
//             ^^^^^^^^^^^^^^^^^^^^ Get the facilitator details for display^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//             ^^^^^^^^^^^^^^^^^^^^^ ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
              
      String facil_select="select * from facilitator_details where target_id='"+conn.rs.getString("target_pop_id") +"'";   
              conn.rs3=conn.st3.executeQuery(pop_select);
              while(conn.rs3.next()){
                  population_name=conn.rs3.getString("population_name");
              }
                    
              String  target2="select * from curriculum where target_id='"+conn.rs.getString("target_pop_id") +"'";
              System.out.println("target id:1 "+conn.rs.getString("target_pop_id"));
              conn.rs2=conn.st2.executeQuery(target2);
              while(conn.rs2.next())
              {
          String no_of_s=conn.rs2.getString("no_of_lessons").toString();  
              System.out.println("no of  sessions:1 "+conn.rs2.getString("no_of_lessons"));   
            total_sessions=Integer.parseInt(no_of_s); 
             System.out.println("no of  sessions:2 "+total_sessions); 
                  } 
                
                } 
                
//                ^^^^^^^^^^^^^^^^^CREATING THE WORKBOOK^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet();
  HSSFRow rw1=shet1.createRow(0);
  HSSFCell cell;
  cell=rw1.createCell(4);
  cell.setCellValue("Group Attendance Report");
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(0,0,4,12));
 
//^^^^^^^^^^^^^^^^^^^^  Create the second row    ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  HSSFRow rw2=shet1.createRow(2) ;
 rw2.createCell(2).setCellValue("Implementing Partner");
 rw2.createCell(7).setCellValue(" Group Name" );
 rw2.createCell(11).setCellValue("Total Sessions");
 
 
// ^^^^^^^^^^^^^^^Create the third row^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 HSSFRow rw3=shet1.createRow(3);
 rw3.createCell(2).setCellValue(partner_name);
 rw3.createCell(7).setCellValue(group_name);
 rw3.createCell(11).setCellValue(total_sessions);
                
 HSSFRow rw4=shet1.createRow(5);
 rw4.createCell(3).setCellValue("Number");
 rw4.createCell(4).setCellValue("First Name");
 rw4.createCell(5).setCellValue("Middle Name");
 rw4.createCell(6).setCellValue("Last Name");
 rw4.createCell(8).setCellValue("Sex");
 rw4.createCell(10).setCellValue("Age in years");
 rw4.createCell(12).setCellValue("Sessions Attended");
 
 int rws=0;
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
// Count the number of rows with the specific attributes   ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 String selecto="select * from member_details where group_id='"+group_id+"'";
conn.rs=conn.st.executeQuery(selecto);
 while(conn.rs.next()){
 String namer=conn.rs.getString("first_name");    
 rws++;    
     
 }
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
// ^^^^^^^^^^^^^^^^^^^^^^^^^lOOP THROUGH THE DATABASE AND DISPLAY ^^^^^^^^^^^^^^^^^^^^^^^^
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 String selector="select * from member_details where group_id='"+group_id+"' && year='"+yea+"' && period='"+period+"' ORDER BY sessions_attended";
conn.rs=conn.st.executeQuery(selector);
 int rws2=6+rws;
 System.out.println(rws);
  System.out.println(rws2);
  int i=6;
 while(conn.rs.next()){
        System.out.println(" i values are"+i);
    HSSFRow rwi=shet1.createRow(i);       
   rwi.createCell(3).setCellValue(i-5);
 rwi.createCell(4).setCellValue(conn.rs.getString("first_name"));
 rwi.createCell(5).setCellValue(conn.rs.getString("mname"));
 rwi.createCell(6).setCellValue(conn.rs.getString("sur_name")); 
 rwi.createCell(8).setCellValue(conn.rs.getString("sex"));
 rwi.createCell(10).setCellValue(Integer.parseInt(conn.rs.getString("age")));
 rwi.createCell(12).setCellValue(Integer.parseInt(conn.rs.getString("sessions_attended")));
 System.out.println("The member id is:"+conn.rs.getString("member_id"));
String select_form="select * from register_attendance where member_id='"+conn.rs.getString("member_id")+"'";
conn.rs2=conn.st2.executeQuery(select_form);
conn.rs2.next();
String form_id=conn.rs2.getString("form_id");
String select_form_name="select * from forms where form_id='"+form_id+"'"; 
conn.rs3=conn.st3.executeQuery(select_form_name);
conn.rs3.next();
form_number=conn.rs3.getString("form_number");
System.out.println("The form number is:"+form_number);
  i++;  
 }
 rw2.createCell(5).setCellValue("Target Population" );
  rw2.createCell(13).setCellValue("Form Number" );
  rw3.createCell(5).setCellValue(population_name);
  rw3.createCell(13).setCellValue(form_number);
 
//   ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//  ^^^^^^^^^^^^^^^^^^^^Make the data downloadable by the user^^^^^^^^^^^^^^^^^^^^^^^^^^
// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
   group_name=group_name.replace(" ", "_");
  ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename="+group_name.trim()+"_Group_Completion_Rate.xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
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
            Logger.getLogger(group_report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(group_report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
