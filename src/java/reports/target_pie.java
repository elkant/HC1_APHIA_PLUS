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
public class target_pie extends HttpServlet {

HttpSession session;
String county,partner,period,yea,pop_name,target_id,ct_id,pe2;
String ct[],part[],periods2[];
double completed,partial,incomplete,max_lessons,districts;
double comp,incomp,parti;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
  OutputStream out = response.getOutputStream();
session=request.getSession();
dbConn conn = new dbConn();

  DefaultPieDataset target_pop= new DefaultPieDataset(); 
comp=incomp=parti=0;
if(session.getAttribute("counties")!=null){
  ct=session.getAttribute("counties").toString().split(",");
  part=session.getAttribute("partners").toString().split(",");
  periods2=session.getAttribute("period").toString().split(",");
  yea=session.getAttribute("year").toString();
  int prev_year=Integer.parseInt(yea)-1;
  pop_name=session.getAttribute("target").toString();
  System.out.println("partner  ids  :  "+session.getAttribute("partners").toString());
  max_lessons=completed=partial=incomplete=0;
  partner=county=pe2="";
   if(part!=null) {
       System.out.println(" size of partner is  :  "+part.length);
       for ( int j=0;j<part.length;j++){
           if(part[j]!=null && !part[j].equals("")){
          String partner_id=part[j];
          System.out.println("partner id is  :  "+partner_id);
          String partner_names="SELECT * FROM partner WHERE partner_id='"+partner_id+"'";
          conn.rs=conn.st.executeQuery(partner_names);
          if(conn.rs.next()==true){
            if(part.length==1 && j+1==1){ partner=conn.rs.getString(2);}
            if((j+1==part.length) && part.length>1) {partner+=" and "+conn.rs.getString(2); }
            if((j+2==part.length) && part.length>1) {partner+=conn.rs.getString(2); }
            if(!partner.contains(conn.rs.getString(2))){
                partner+=conn.rs.getString(2)+", ";
            } 
          }
           }
       }
   }
   
     if(ct!=null) {
       for ( int k1=0;k1<ct.length;k1++){
           ct_id="";
      
           if(ct[k1]!=null && !ct[k1].equals("")){
               ct_id=ct[k1];
               String partner_names="SELECT * FROM county WHERE county_id='"+ct_id+"'";
          conn.rs=conn.st.executeQuery(partner_names);
          if(conn.rs.next()==true){
            if(ct.length==1 && k1+1==1){ county=conn.rs.getString(2);}
            if((k1+1==ct.length) && ct.length>1) {county+=" and "+conn.rs.getString(2); }
            if((k1+2==ct.length) && ct.length>1) {county+=conn.rs.getString(2); }
            if(!county.contains(conn.rs.getString(2))){
                county+=conn.rs.getString(2)+", ";
            } 
          }
               
               
           }}}
     String pe=",";
      for(int i=0;i<periods2.length;i++)  {
          
          if(periods2[i]!=null && !periods2[i].equals("")){
pe+=periods2[i]+",";
          }
      
      }
      pe2="   Period  : ";
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
     
 System.out.println("partners are   :  "+partner); 
  System.out.println("counties are   :  "+county); 
 
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
  if(ct!=null) {
       for ( int k=0;k<ct.length;k++){
           ct_id="";
      
           if(ct[k]!=null && !ct[k].equals("")){
               ct_id=ct[k];
               districts=0;
               String district_selector="SELECT district_id FROM district WHERE county_id='"+ct_id+"'";
               conn.rs3=conn.st3.executeQuery(district_selector);
               while(conn.rs3.next()){
                   districts++;
                   
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
               
               
               } // END OF COUNTY LOOP   
               System.out.println("COUNTY  :  ID :  "+ct_id+"     no of districts  :  "+districts);
               districts=0;
  }  
    
       }
  
  }
  
  }


    }
  System.out.println("completed  :"+completed+"    partial  :  "+partial+"      less 50  :  "+incomplete);  
    double all=completed+partial+incomplete;
    
    if(all>0){
 comp=Math.round(completed*100/all);
 incomp=Math.round(incomplete*100/all);
 parti=Math.round(partial*100/all);
    }
//    comp=12.235;
//    incomp=12.69;
//    parti=12.00;
     System.out.println("completed   :  "+comp+"  partial  :  "+parti+"   less  :  "+incomp);

 String cmp,incmp,partially,tot;
cmp=(Double.toString(comp)).replace(".0", "");
 incmp=(Double.toString(incomp)).replace(".0", "");
 partially=(Double.toString(parti)).replace(".0", "");
 tot=(Double.toString(all)).replace(".0", "");
      target_pop.setValue(cmp+"% of total Individuals Completed all Sessions", completed);
      target_pop.setValue(partially+"% of total Individuals Attended 50% and over But did not complete all Sessions.", partial);
      target_pop.setValue(incmp+"% of total Individuals Attended Less 50% of the total Sessions.", incomplete);
   
      
      JFreeChart chart = ChartFactory.createPieChart("Completion Rate for :  "+pop_name+"\n Partner(s)   "
              + ":  "+partner+"  Counties   :  "+county+"\n within the year : "+yea+" "+pe2+"   Total Individuals  :   "+tot,target_pop,true,true,false);
 response.setContentType("image/png");
  ChartUtilities.writeChartAsPNG(out,chart, 1100, 650);
  all=0;
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
            Logger.getLogger(target_pie.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(target_pie.class.getName()).log(Level.SEVERE, null, ex);
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
