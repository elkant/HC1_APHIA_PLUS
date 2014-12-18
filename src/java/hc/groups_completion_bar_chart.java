/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.*;
import org.jfree.data.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.plot.*;
/**
 *
 * @author Geofrey Nyabuto
 */
public class groups_completion_bar_chart extends HttpServlet {
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
        int no_of_sessions=0,current_sessions=0;
String group_name="";
 int completed=0,partial=0,incomplete=0, total_sessions=0,sessions_attended=0,counter=0;
 double all_per=0, partial_per=0,incomplete_par=0,all=0,attended_per=0;
 String path,path2;
 String partner_name="",target_name="";
 String name="" ;          
 String start_date="";
String end_date ="" ;                 
                   
        
OutputStream out = response.getOutputStream();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dbConn conn=new dbConn();
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
 int i=4;
            String groups_selector="select * from groups where period_id='4'";
            conn.rs=conn.st.executeQuery(groups_selector);
            while(conn.rs.next()){
                group_name=conn.rs.getString("group_name");
//                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                ^^^^^^^^^^^^^^^^^Get total sessions per target population^^^^^^^^^^^^^^^^^^^^^^^
                String curriculum_selector="select * from curriculum where target_id='"+conn.rs.getString("target_pop_id") +"'";
                conn.rs0=conn.st0.executeQuery(curriculum_selector);
                conn.rs0.next();
                no_of_sessions=Integer.parseInt( conn.rs0.getString("no_of_lessons").toString());
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
//                ^^^^^^^^^^^^^^^^^^^^^Get Each sessions attended for every member^^^^^^^^^^^^^^^^^^^^^^^
             String participant_selector="select * from member_details where group_id='"+conn.rs.getString("group_id") +"'"; 
             conn.rs0=conn.st0.executeQuery(participant_selector);
             while(conn.rs0.next()){
              counter++;
            current_sessions=Integer.parseInt( conn.rs0.getString("sessions_attended").toString());
              sessions_attended=sessions_attended+ current_sessions;
      if(current_sessions==no_of_sessions){
          completed++;
      }
      if(current_sessions>=(no_of_sessions/2) && current_sessions<no_of_sessions){
          partial++;
      }
      if(current_sessions<(no_of_sessions/2) && no_of_sessions>=0){
         incomplete++;
      }     
                 
        }
    	dataset.setValue(completed, "Completed All", group_name);
	dataset.setValue(partial, "Over 50% n not all", group_name );
	dataset.setValue(incomplete, "Less than 50%", group_name);
                    completed=0;
            partial=0;
            incomplete=0;
            }
    JFreeChart chart = ChartFactory.createBarChart3D("Groups Attendance Bar chart","Group Name", "No of Peers", dataset, PlotOrientation.VERTICAL, true,true, false);
	chart.setBackgroundPaint(Color.yellow);
	chart.getTitle().setPaint(Color.blue); 
	response.setContentType("image/png");
  ChartUtilities.writeChartAsPNG(out,chart, 900, 550);
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
            Logger.getLogger(groups_completion_bar_chart.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(groups_completion_bar_chart.class.getName()).log(Level.SEVERE, null, ex);
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
