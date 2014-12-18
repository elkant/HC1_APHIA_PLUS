/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class target_pop_bar_chart extends HttpServlet {
String partner_id="",partner_name="";
int part=0,i=0,total_participants,max_lessons;
int all=0,aller=0;
String target_id="";
    ArrayList alist= new ArrayList();
    String pop_name="";
    int k=0;
    int less=0;
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       session=request.getSession();
        alist.clear();
      max_lessons= k=total_participants=0;
        String group_name="";
      int a=5;  
 int unusual=0, completed=0,partial=0,incomplete=0, total_sessions=0,sessions_attended=0,counter=0;
String period,yea;
            period=session.getAttribute("period").toString();
            yea=session.getAttribute("year").toString();
 dbConn conn=new dbConn();
 OutputStream out = response.getOutputStream();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
 String target1_selector="select DISTINCT population_name from target_population";
 conn.rs4=conn.st4.executeQuery(target1_selector);
 while(conn.rs4.next()){
    alist.add(conn.rs4.getString(1)); 
 }
  System.out.println("This is the size   :    "+alist.size());      
 while(k<alist.size())  
  {   
     pop_name=alist.get(k).toString();
      
  String target_selector="select * from target_population where population_name='"+pop_name+"'" ;
  conn.rs1=conn.st1.executeQuery(target_selector);
  while(conn.rs1.next())
  {
//    System.out.println("Here within ppop   :  "+pop_name);  
target_id=conn.rs1.getString(1);
  String select_group="select * from groups where target_pop_id='"+target_id+"'" ;
  conn.rs0=conn.st0.executeQuery(select_group);
     while(conn.rs0.next()) {
         
      //     ^^^^^^^^^^^^^^^^^^^^^ GET TOTAL LESSONS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  String curriculum_selector="select * from curriculum where target_id='"+target_id+"'";
  conn.rs4=conn.st4.executeQuery(curriculum_selector);
  conn.rs4.next();
  max_lessons=Integer.parseInt(conn.rs4.getString("no_of_lessons").toString());
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
  
  
  all=completed+partial+incomplete;
aller+=all;
pop_name=pop_name+"("+completed+","+partial+","+incomplete+")";
if(all>0){
  dataset.setValue(completed, "Completed All sessions", pop_name);
	dataset.setValue(partial, "Attended 50% and over but not all sessions", pop_name );
	dataset.setValue(incomplete, "Attended Less than 50% of total sessions", pop_name);
}
   total_participants+=all;
  completed=0;
  
  partial=0;
  incomplete=0; 

     
     completed=0;
  less=0;
  partial=0;
  incomplete=0; 
  part=0;
  k++;
  
  System.out.println("Pop name  :  "+pop_name+" Total: "+all);
  all=0;
  }
  String period_name="";
String quarter_selector="SELECT period_name FROM period WHERE id='"+period+"'";
conn.rs=conn.st.executeQuery(quarter_selector);
if(conn.rs.next()==true){
    period_name=conn.rs.getString(1);
}
     JFreeChart chart = ChartFactory.createBarChart3D("Target Population Report: ("+period_name+")"+yea+"                                          No of Individuals: "+total_participants,"Target Population", "No of Individuals", dataset, PlotOrientation.VERTICAL, true,true, false);
	chart.setBackgroundPaint(Color.yellow);
	chart.getTitle().setPaint(Color.blue); 
	response.setContentType("image/png");
  ChartUtilities.writeChartAsPNG(out,chart, 1100, 550);
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
            Logger.getLogger(target_pop_bar_chart.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(target_pop_bar_chart.class.getName()).log(Level.SEVERE, null, ex);
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
