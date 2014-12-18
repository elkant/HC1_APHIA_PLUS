/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import hc.dbConn;
import java.io.IOException;
import java.io.OutputStream;
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
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Geofrey Nyabuto
 */
public class partner_completion_rate extends HttpServlet {
String partner_name,year,period,partner_id,pop_name,pop_id,county_name;
HttpSession session;
String period2 [],county [];
int max_lessons,completed;
String ct2="",pe2="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
         OutputStream out = response.getOutputStream();
        try{
            dbConn conn = new dbConn();
   partner_id=request.getParameter("partner");
   year=request.getParameter("year");
//   period=request.getParameter("period");
     period2=request.getParameterValues("period") ;
     county=request.getParameterValues("county");
     
            ct2=pe2="";
       
       for(int i=0;i<county.length;i++)  {
          if(county[i]!=null && !county[i].equals("")){
              String ct_name="SELECT * FROM county WHERE county_id='"+county[i]+"'";
              conn.rs=conn.st.executeQuery(ct_name);
              if(conn.rs.next()==true){
                  if(i+1<county.length){
           ct2+= conn.rs.getString(2)+", "; 
                  }
                 if(i+1==county.length){
           ct2+= conn.rs.getString(2); 
                  }
          }}
          }
//       period=request.getParameter("period");
       
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
     
      DefaultPieDataset partner_chart = new DefaultPieDataset();  
     String partner_name_selector="SELECT partner_name FROM partner WHERE partner_id='"+partner_id+"'";
       conn.rs=conn.st.executeQuery(partner_name_selector);
       while(conn.rs.next()){
        partner_name=conn.rs.getString(1);
       }
           String target_selector="SELECT * FROM target_population WHERE partner_id='"+partner_id+"'";
           conn.rs=conn.st.executeQuery(target_selector);
           while(conn.rs.next()){
              pop_name=conn.rs.getString(2);
              pop_id=conn.rs.getString(1);
              max_lessons=completed=0;
              String max_less_selector="SELECT no_of_lessons FROM curriculum WHERE target_id='"+pop_id+"'";
              conn.rs1=conn.st1.executeQuery(max_less_selector);
              if(conn.rs1.next()==true){
                  max_lessons=conn.rs1.getInt(1);
              }
              if(county!=null){
                  for (int i=0;i<county.length;i++){
                   String  county_id=county[i];
                   if(county_id!=null && !county_id.equals("")){
                   String ct_selector="SELECT county_name FROM county WHERE county_id='"+county_id+"'";
                   conn.rs1=conn.st1.executeQuery(ct_selector);
                   if(conn.rs1.next()==true){county_name=conn.rs1.getString(1)  ;}
                   
                 String district_selector="SELECT district_id FROM district WHERE county_id='"+county_id+"'" ;    
                  conn.rs1=conn.st1.executeQuery(district_selector);
                  while(conn.rs1.next()){
                    String district_id=conn.rs1.getString(1); 
                    
                    String group_selector="SELECT group_id FROM groups WHERE district_id='"+district_id+"' && target_pop_id='"+pop_id+"'";
                    conn.rs2=conn.st2.executeQuery(group_selector);
                    while(conn.rs2.next()){
                      String group_id=conn.rs2.getString(1);  
                       if(period2!=null){
//                     System.out.println("PERIOD SIZE IS   :   "+period2.length);
                     for (int j=0;j<period2.length;j++){
                         period=period2[j];
                         if(period!=null && !period.equals("")){  
                      String complete_counter="SELECT COUNT(member_id) FROM member_details WHERE year='"+year+"' && period='"+period+"' && group_id='"+group_id+"' && sessions_attended='"+max_lessons+"'" ;
                      conn.rs3=conn.st3.executeQuery(complete_counter);
                      if(conn.rs3.next()==true){
                        completed+=conn.rs3.getInt(1);}}}}}} }}}
           System.out.println("POP NAME  :  "+pop_name+"    complete   :  "+completed);
           
           partner_chart.setValue(pop_name+", Completed  :"+completed, completed);
           
           completed=0;
           }
           
            JFreeChart chart = ChartFactory.createPieChart("Completion Rate For Partner :  "+partner_name+" within the year : "+year+" and the periods : "+pe2,partner_chart,true,true,false);
 response.setContentType("image/png");
  ChartUtilities.writeChartAsPNG(out,chart, 950, 600);
        }
        
        finally{
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
            Logger.getLogger(partner_completion_rate.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(partner_completion_rate.class.getName()).log(Level.SEVERE, null, ex);
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
