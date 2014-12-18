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
public class kePMS_barchart_report extends HttpServlet {
String kepms_name;
int completed, incomplete, less_50,all, max_lessons,lessons_attended;
HttpSession session;
String target_ids,period_name,period,yea="",pe2;
int total=0,total_participants=0;
int aller;
String periods2[];
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
session=request.getSession();
dbConn conn = new dbConn();
 OutputStream out = response.getOutputStream();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
            periods2=session.getAttribute("period").toString().split(",");
            yea=session.getAttribute("year").toString();
            System.out.println(" year is  :  "+yea+" period  :  "+session.getAttribute("period").toString());
            
             String pe=",";
      for(int i=0;i<periods2.length;i++)  {
          
          if(periods2[i]!=null && !periods2[i].equals("")){
pe+=periods2[i]+",";
          }
      
      }
      pe2="   Period  : ";
      if(pe.contains("1")&& pe.contains("2") && pe.contains("3") && pe.contains("4")){pe2+="Jan - Dec";}
     else if(!pe.contains("1")&& pe.contains("2") && pe.contains("3") && pe.contains("4")){pe2+="April - Dec";}
     else if(!pe.contains("1")&& !pe.contains("2") && pe.contains("3") && pe.contains("4")){pe2+="July - Dec";}
     else if(!pe.contains("1")&& !pe.contains("2") && !pe.contains("3") && pe.contains("4")){pe2+="Oct - Dec";}
      
     else if(pe.contains("1")&& pe.contains("2") && pe.contains("3") && !pe.contains("4")){pe2+="Jan - Sept";}
     else if(pe.contains("1")&& pe.contains("2") && !pe.contains("3") && !pe.contains("4")){pe2+="Jan - June";}
     else if(pe.contains("1")&& !pe.contains("2") && !pe.contains("3") && !pe.contains("4")){pe2+="Jan - March";}
    
     else if(!pe.contains("1")&& pe.contains("2") && pe.contains("3") && !pe.contains("4")){pe2+="April - Sept";}
     
     
     
      else{
          pe2="   Periods are  : ";
          for(int i=0;i<periods2.length;i++)  {
          String pe1=periods2[i];
          if(pe1!=null && !pe1.equals("")){
              String ct_name="SELECT * FROM period WHERE id='"+pe1+"'";
              conn.rs=conn.st.executeQuery(ct_name);
              if(conn.rs.next()==true){
                  if(i+1<periods2.length){
           pe2+= conn.rs.getString(2)+", "; 
                  }
                 if(i+1==periods2.length){
           pe2+= conn.rs.getString(2); 
                  }
          }}
          }
      }
      
            period_name=pe2;
total=aller=0;
String kepms_selector="select * from kepms";
conn.rs=conn.st.executeQuery(kepms_selector);
while(conn.rs.next())
{
 completed= incomplete= less_50=all= max_lessons=lessons_attended=0;   
    
kepms_name=conn.rs.getString(2);    
 target_ids=conn.rs.getString(3);
 System.out.println("tags   "+target_ids);
 String [] t_ids=target_ids.split(",");
 for(int i=0; i<t_ids.length;i++){
     max_lessons=0;
     int tagger=0;
     String tag_id=t_ids[i];
     if(tag_id.length()!=0){
     tagger=Integer.parseInt(tag_id);
     }
     if(tagger>0){
     String curriculum_selector="select no_of_lessons from curriculum where target_id='"+tag_id+"'";
     conn.rs1=conn.st1.executeQuery(curriculum_selector);
    if(conn.rs1.next()){
     max_lessons=conn.rs1.getInt(1);
    }
    
//     System.out.println("max lessons  :   "+max_lessons);
             
    if(max_lessons>0) {
 String group_selector="select group_id from groups where target_pop_id='"+tag_id+"'";
 conn.rs1=conn.st1.executeQuery(group_selector);
 while(conn.rs1.next()){
   String group_id=conn.rs1.getString(1); 
  //  ^^^^^^^^^^^^^^^^^^^^GET NUMBER OF SESSIONS ATTENDED BY EACH MEMBER^^^^^^^^^^^^^^^^^
 if(periods2!=null) {
       for ( int j=0;j<periods2.length;j++){
           if(periods2[j]!=null && !periods2[j].equals("")){
               period=periods2[j];
  String participant_selector1="select count(member_id) from member_details where group_id='"+group_id+"' && sessions_attended>='"+max_lessons+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector1);
if(conn.rs4.next()==true){
          completed+=conn.rs4.getInt(1);
}
  String participant_selector2="select count(member_id) from member_details where group_id='"+group_id+"' && sessions_attended<'"+max_lessons+"' && sessions_attended>='"+max_lessons/2+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector2);
if(conn.rs4.next()==true){
          incomplete+=conn.rs4.getInt(1);
}
  String participant_selector3="select count(member_id) from member_details where group_id='"+group_id+"' && sessions_attended<'"+max_lessons/2+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector3);
if(conn.rs4.next()==true){
          less_50+=conn.rs4.getInt(1);
} 
 
    
 }}
 }}
 }
}
 }
 total=completed+incomplete+less_50;
 total_participants+=total;
 String valu="  ("+completed+" , "+incomplete+" , "+less_50+")";
 total=0;
 kepms_name+=valu;
 dataset.setValue(completed, "Completed All", kepms_name);
	dataset.setValue(incomplete, "Over 50% and not all", kepms_name );
	dataset.setValue(less_50, "Less than 50%", kepms_name);
        kepms_name="";
} 

JFreeChart chart = ChartFactory.createBarChart3D("PEPFAR Report :("+period_name+") "+yea+"           Total Individuals  : "+total_participants,"", "No of Individuals", dataset, PlotOrientation.VERTICAL, true,true, false);
	chart.setBackgroundPaint(Color.yellow);
	chart.getTitle().setPaint(Color.blue); 
	response.setContentType("image/png");
  ChartUtilities.writeChartAsPNG(out,chart, 1100, 650);
 total_participants=0;
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
            Logger.getLogger(kePMS_barchart_report.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(kePMS_barchart_report.class.getName()).log(Level.SEVERE, null, ex);
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
