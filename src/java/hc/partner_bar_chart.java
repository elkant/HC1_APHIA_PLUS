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
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author Geofrey Nyabuto
 */
public class partner_bar_chart extends HttpServlet {
String partner_id="",partner_name="";
int part=0,i=0;
int all=0,aller=0,max_lessons,total_participants;
String target_id="";
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       session=request.getSession();
       max_lessons=total_participants=0;
       String period,yea;
            period=session.getAttribute("period").toString();
            yea=session.getAttribute("year").toString();
        String group_name="";
 int unusual=0, completed=0,partial=0,incomplete=0, total_sessions=0,sessions_attended=0,counter=0;

 dbConn conn=new dbConn();
 OutputStream out = response.getOutputStream();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
 
 String partner_selector="select * from partner";
 conn.rs=conn.st.executeQuery(partner_selector);
 while(conn.rs.next()){
 partner_id=conn.rs.getString(1);
 partner_name=conn.rs.getString(2);
  part++;   
  if(part>0)   
  {   
  String target_selector="select * from target_population where partner_id='"+partner_id+"'" ;
  conn.rs1=conn.st1.executeQuery(target_selector);
  while(conn.rs1.next())
  {
    
            //     ^^^^^^^^^^^^^^^^^^^^^ GET TOTAL LESSONS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  String curriculum_selector="select * from curriculum where target_id='"+target_id+"'";
  conn.rs4=conn.st4.executeQuery(curriculum_selector);
if(conn.rs4.next()){
  max_lessons=Integer.parseInt(conn.rs4.getString("no_of_lessons").toString());
}
      target_id=conn.rs1.getString(1);
  String select_group="select * from groups where partner_id='"+partner_id+"' && target_pop_id='"+target_id+"'" ;
  conn.rs0=conn.st0.executeQuery(select_group);
     while(conn.rs0.next()) {
         

//  ^^^^^^^^^^^^^^^^^^^^GET NUMBER OF SESSIONS ATTENDED BY EACH MEMBER^^^^^^^^^^^^^^^^^

  String participant_selector1="select count(member_id) from member_details where group_id='"+conn.rs0.getString("group_id") +"' && sessions_attended>='"+max_lessons+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector1);
if(conn.rs4.next()==true){
          completed+=conn.rs4.getInt(1);
}
  String participant_selector2="select count(member_id) from member_details where group_id='"+conn.rs0.getString("group_id") +"' && sessions_attended<'"+max_lessons+"' && sessions_attended>='"+max_lessons/2+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector2);
if(conn.rs4.next()==true){
          partial+=conn.rs4.getInt(1);
}
  String participant_selector3="select count(member_id) from member_details where group_id='"+conn.rs0.getString("group_id") +"' && sessions_attended<'"+max_lessons/2+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector3);
if(conn.rs4.next()==true){
          incomplete+=conn.rs4.getInt(1);
}       
         
         
  }
      
      
      
  }
  }
  all=completed+partial+incomplete;
aller+=all;
  dataset.setValue(completed, "Completed All", partner_name);
	dataset.setValue(partial, "Over 50% n not all", partner_name );
	dataset.setValue(incomplete, "Less than 50%", partner_name);
   total_participants+=all;
  completed=0;
  all=0;
  partial=0;
  incomplete=0; 
  part=0;
  }  
 String period_name="";
String quarter_selector="SELECT period_name FROM period WHERE id='"+period+"'";
conn.rs=conn.st.executeQuery(quarter_selector);
if(conn.rs.next()==true){
    period_name=conn.rs.getString(1);
}
     JFreeChart chart = ChartFactory.createBarChart3D("Partner Attendance Bar chart: ("+period_name+")"+yea+"             No of Peers: "+total_participants,"Partner Name", "No of Peers", dataset, PlotOrientation.VERTICAL, true,true, false);
	chart.setBackgroundPaint(Color.yellow);
	chart.getTitle().setPaint(Color.blue); 
	response.setContentType("image/png");
  ChartUtilities.writeChartAsPNG(out,chart, 900, 550);
  i=0;
  aller=0;
    
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
            Logger.getLogger(partner_bar_chart.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(partner_bar_chart.class.getName()).log(Level.SEVERE, null, ex);
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
