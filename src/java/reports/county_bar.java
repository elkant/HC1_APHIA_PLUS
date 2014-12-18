/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import hc.dbConn;
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
public class county_bar extends HttpServlet {
HttpSession session;
String yea,pop_name,pe2,county_id, county_name,period,target_id;
String periods2[];
int max_lessons,completed,partial,incomplete,total_participants;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
      session=request.getSession();
        dbConn conn = new dbConn();
         OutputStream out = response.getOutputStream();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        total_participants=0;
        
        if(session.getAttribute("period")!=null){
  periods2=session.getAttribute("period").toString().split(",");
  yea=session.getAttribute("year").toString();
  pop_name=session.getAttribute("target").toString();   
   int prev_year=Integer.parseInt(yea)-1;         
   String pe=",";
      for(int i=0;i<periods2.length;i++)  {
          
          if(periods2[i]!=null && !periods2[i].equals("")){
pe+=periods2[i]+",";
          }
      
      }
      pe2="   Period  :   ";
      if(pe.contains("1")&& pe.contains("2") && pe.contains("3") && pe.contains("4")){pe2+="Oct "+prev_year+" - Sept";}
     else if(!pe.contains("1")&& pe.contains("2") && pe.contains("3") && pe.contains("4")){pe2+="Jan - Sept";}
     else if(!pe.contains("1")&& !pe.contains("2") && pe.contains("3") && pe.contains("4")){pe2+="April - Sept";}
     else if(!pe.contains("1")&& !pe.contains("2") && !pe.contains("3") && pe.contains("4")){pe2+="July - Sept";}
      
     else if(pe.contains("1")&& pe.contains("2") && pe.contains("3") && !pe.contains("4")){pe2+="Oct "+prev_year+" - June";}
     else if(pe.contains("1")&& pe.contains("2") && !pe.contains("3") && !pe.contains("4")){pe2+="Oct "+prev_year+" - April";}
     else if(pe.contains("1")&& !pe.contains("2") && !pe.contains("3") && !pe.contains("4")){pe2+="Oct - Dec "+prev_year+"";}
    
     else if(!pe.contains("1")&& pe.contains("2") && pe.contains("3") && !pe.contains("4")){pe2+="Jan - June";}
     
     
     
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
     
      
      
 
 
 String county_selector="SELECT * FROM county";
 conn.rs2=conn.st2.executeQuery(county_selector);
 while(conn.rs2.next()){
     max_lessons=completed=partial=incomplete=0;
county_id=conn.rs2.getString(1);
county_name=conn.rs2.getString(2);
String target_selector="select * from target_population where population_name='"+pop_name+"'" ;
  conn.rs1=conn.st1.executeQuery(target_selector);
  while(conn.rs1.next())
  { 
target_id=conn.rs1.getString(1);
  
    String curriculum_selector="select * from curriculum where target_id='"+target_id+"'";
  conn.rs0=conn.st0.executeQuery(curriculum_selector);
 if(conn.rs0.next()==true){
  max_lessons=conn.rs0.getInt("no_of_lessons");
 }
 System.out.println(" max sess   " +max_lessons);
 

               String district_selector="SELECT district_id FROM district WHERE county_id='"+county_id+"'";
               conn.rs3=conn.st3.executeQuery(district_selector);
               while(conn.rs3.next()){
                   
  String select_group="select * from groups where target_pop_id='"+target_id+"' && district_id='"+conn.rs3.getString(1) +"'" ;
  conn.rs0=conn.st0.executeQuery(select_group);
     while(conn.rs0.next()) {
         
      //     ^^^^^^^^^^^^^^^^^^^^^ GET TOTAL LESSONS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         
   if(periods2!=null) {
       for ( int j=0;j<periods2.length;j++){
           if(periods2[j]!=null && !periods2[j].equals("")){
               period=periods2[j];
//  ^^^^^^^^^^^^^^^^^^^^GET NUMBER OF SESSIONS ATTENDED BY EACH MEMBER^^^^^^^^^^^^^^^^^

  String participant_selector1="select count(member_id) from member_details where group_id='"+conn.rs0.getString("group_id") +"' && sessions_attended='"+max_lessons+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector1);
if(conn.rs4.next()==true){
          completed+=conn.rs4.getInt(1);
}
  String participant_selector2="select count(member_id) from member_details where group_id='"+conn.rs0.getString("group_id") +"' && sessions_attended<'"+max_lessons+"' && sessions_attended>='"+(max_lessons/2)+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector2);
if(conn.rs4.next()==true){
          partial+=conn.rs4.getInt(1);
}
  String participant_selector3="select count(member_id) from member_details where group_id='"+conn.rs0.getString("group_id") +"' && sessions_attended<'"+(max_lessons/2)+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector3);
if(conn.rs4.next()==true){
          incomplete+=conn.rs4.getInt(1);
}       
         
         
  }
      
      
   }
   } 
     }
               
               
               } 
            
  }  
 total_participants+=completed+incomplete+partial;  
  int all=completed+incomplete+partial;
  county_name=county_name+"("+completed+","+partial+","+incomplete+")";
	if(all>0){
        dataset.setValue(completed, "Completed All Sessions", county_name);
        dataset.setValue(partial, "Attended 50% and over but not all sessions", county_name );
	dataset.setValue(incomplete, " Attended Less than 50% of total Sessions", county_name);
 }
  System.out.println("COUNTY  :   :  "+county_name+"     completed   "+completed+"   partial   ;  "+partial+"    incomplete    :  "+incomplete);
  completed=partial=incomplete=0;   
 
 }// END OF COUNTY LOOP 
 
 JFreeChart chart = ChartFactory.createBarChart3D("Target Population :"+pop_name+"   "+pe2+"     Year : "+yea+"           Total Individuals  : "+total_participants,"County Name", "No of Individuals", dataset, PlotOrientation.VERTICAL, true,true, false);
	chart.setBackgroundPaint(Color.yellow);
	chart.getTitle().setPaint(Color.blue); 
	response.setContentType("image/png");
  ChartUtilities.writeChartAsPNG(out,chart, 1100, 650);
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
            Logger.getLogger(county_bar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(county_bar.class.getName()).log(Level.SEVERE, null, ex);
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
