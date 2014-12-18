/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import hc.dbConn;
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
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Geofrey Nyabuto
 */
public class county_completion_rate extends HttpServlet {
HttpSession session;
String county_names="", county="", target_pop="",pop_id="",pop_name="";
int completed=0, total_peers=0;
String [] ct;
int max_lesson=0,k;
String year="",period="";
String [] period2;
ArrayList tplist=new ArrayList();
ArrayList tp=new ArrayList();
ArrayList complete=new ArrayList();
String NewCounty="",NewPeriods="";
String ct2="",pe2="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
          OutputStream out = response.getOutputStream();
        try{
          
          dbConn conn = new dbConn();
       
       ct=request.getParameterValues("county");
       year=request.getParameter("year");
       ct2=pe2="";
       
       for(int i=0;i<ct.length;i++)  {
          county=ct[i];
          if(county!=null && !county.equals("")){
              String ct_name="SELECT * FROM county WHERE county_id='"+county+"'";
              conn.rs=conn.st.executeQuery(ct_name);
              if(conn.rs.next()==true){
                  if(i+1<ct.length){
           ct2+= conn.rs.getString(2)+", "; 
                  }
                 if(i+1==ct.length){
           ct2+= conn.rs.getString(2); 
                  }
          }}
          }
//       period=request.getParameter("period");
       period2=request.getParameterValues("period");
       
              for(int i=0;i<period2.length;i++)  {
          String pe=period2[i];
          if(pe!=null && !pe.equals("")){
              String ct_name="SELECT * FROM period WHERE id='"+pe+"'";
              conn.rs=conn.st.executeQuery(ct_name);
              if(conn.rs.next()==true){
                  if(i+1<period2.length){
           pe2+= conn.rs.getString(2)+", "; 
                  }
                 if(i+1==period2.length){
           pe2+= conn.rs.getString(2); 
                  }
          }}
          }
              
      tplist.clear();
      tp.clear();
      complete.clear();
       k=0;
       String target1_selector="select DISTINCT population_name from target_population";
 conn.rs4=conn.st4.executeQuery(target1_selector);
 while(conn.rs4.next()){
    tplist.add(conn.rs4.getString(1)); 
 }
   DefaultPieDataset county_chart = new DefaultPieDataset();    
 while(k<tplist.size())  
  {   
     pop_name=tplist.get(k).toString();
         
       String pop_selector="SELECT * FROM target_population WHERE population_name='"+pop_name+"'";
       conn.rs1=conn.st1.executeQuery(pop_selector);
       while(conn.rs1.next()){
           pop_id=conn.rs1.getString(1);
            max_lesson=0;
           String cur_selector="SELECT * FROM curriculum WHERE target_id='"+pop_id+"'";
          conn.rs=conn.st.executeQuery(cur_selector);
          if(conn.rs.next()){max_lesson=conn.rs.getInt(3);}
       if(ct!=null){
//               System.out.println("COUNTY SIZE IS   :   "+ct.length);
         for(int i=0;i<ct.length;i++)  {
          county=ct[i];
          if(county!=null && !county.equals("")){
             String district_selector="SELECT * FROM district WHERE county_id='"+county+"'";
             conn.rs=conn.st.executeQuery(district_selector);
             while(conn.rs.next()){
                 String district_id=conn.rs.getString(1);
             String group_selector="SELECT * FROM groups WHERE district_id='"+district_id+"' && target_pop_id='"+pop_id+"'" ;
             conn.rs2=conn.st2.executeQuery(group_selector);
             while(conn.rs2.next()){
              
                 if(period2!=null){
//                     System.out.println("PERIOD SIZE IS   :   "+period2.length);
                     for (int j=0;j<period2.length;j++){
                         period=period2[j];
                         if(period!=null && !period.equals("")){
                 String completed_counter="SELECT COUNT(member_id) FROM member_details WHERE group_id='"+conn.rs2.getString(1)+"' && sessions_attended>='"+max_lesson+"' && year='"+year+"' && period='"+period+"'";
             conn.rs3=conn.st3.executeQuery(completed_counter);
             if(conn.rs3.next()==true){
              completed+=conn.rs3.getInt(1);
            
             }}}}
              }}}}}}
     System.out.println("Target_pop   :  "+pop_name+"     completed  :  "+completed); 
     tp.add(pop_name);
     complete.add(completed);
     
county_chart.setValue(pop_name+",Completed  :"+completed, completed);
      completed=0;
     k++;
    }
 
 JFreeChart chart = ChartFactory.createPieChart("Target Population Completion For the year  :"+year+" in county(s) "+ct2+"  and period(s) "+pe2,county_chart,true,true,false);
 response.setContentType("image/png");
  ChartUtilities.writeChartAsPNG(out,chart, 950, 600);
// for()
 
 } 
        finally {            
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
            Logger.getLogger(county_completion_rate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(county_completion_rate.class.getName()).log(Level.SEVERE, null, ex);
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
