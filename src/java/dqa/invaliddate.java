/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dqa;

import hc.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.String;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manuel
 */
public class invaliddate extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
          dbConn conn= new dbConn();
          
             String populationname="FISHERFOLK";  
          
          
          String lastenteredgroup="Select distinct(population_name) from groups left join target_population on groups.target_pop_id=target_population.target_id order by groups.timestamp desc limit 1";
          
         conn.rs=conn.st.executeQuery(lastenteredgroup);
         
         if(conn.rs.next()){
         
         
         populationname=conn.rs.getString(1);
         
         }
          
         
         //now receive the parameters passed
         String category="";
         if(request.getParameter("duplicatecategory")!=null){
         
         category=request.getParameter("duplicatecategory");
         }
         
          if(request.getParameter("targetpop")!=null){
         
         populationname=request.getParameter("targetpop");
         }
         
     
         
   String table=" <thead>"
		            +" <tr>"
                           +" <th>Start Date</th>"
                           +" <th>End Date</th>"
		           +" <th>Form</th>"                         
		           +" <th>Facilitator </th>"                         
                           +" <th>Group</th>     "
                           +" <th>Target Population</th>"
                           +" <th>Partner </th>"
                            +" <th>District</th>"
                            +" <th>County</th>"                           
                            +" <th>Year</th>  "                          
                            +" <th>Month</th>"
                            +" </tr>"		                
		            +" </thead>"
		            +" <tbody>";
                                
                     

                                
		              
         
         
         
String getdupsnameandage=" SELECT new_topic.new_topic_id as topic_id,"

+" form_number as Form,"
+" group_name"

+" , partner_name as PARTNER, "
+" population_name  as TARGET_POPULATION,county_name as COUNTY,district_name as district,"
+""
+" CONCAT(facilitator_details.first_name,\" \",facilitator_details.sur_name,\" (\",facilitator_details.phone,\")\") as FACILITATOR"
+" ,case "
+" when forms.MONTH =1 THEN 'JAN'"
+" when forms.MONTH =2 THEN 'FEB'"
+" when forms.MONTH =3 THEN 'MAR'"
+" when forms.MONTH=4 THEN 'APR'"
+" when forms.MONTH=5 THEN 'MAY'"
+" when forms.MONTH=6 THEN 'JUN'"
+" when forms.MONTH=7 THEN 'JUL'"
+" when forms.MONTH=8 THEN 'AUG'"
+" when forms.MONTH=9 THEN 'SEP'"
+" when forms.MONTH=10 THEN 'OCT'"
+" when forms.MONTH=11 THEN 'NOV'"
+" when forms.MONTH=12 THEN 'DEC'"
+" END AS MONTHS"
+" ,member_details.year as YEAR,forms.period as QUARTER ,start_date,new_topic.end_date as end_date "
+" FROM groups "
+" join ( member_details  left join " 
+" (register_attendance "
+" left join forms on register_attendance.form_id=forms.form_id"
+" left JOIN facilitator_details on register_attendance.facilitator_id=facilitator_details.facilitator_id"
+" join new_topic on register_attendance.marked_date=new_topic.marking_date ) "
+" on member_details.member_id = register_attendance.member_id )"
+" on member_details.group_id=groups.group_id "
+" join target_population on groups.target_pop_id=target_population.target_id"
+" join district on groups.district_id=district.district_id"
+" join partner on groups.partner_id=partner.partner_id "
+" join curriculum on groups.target_pop_id=curriculum.target_id,county"
+" where district.county_id=county.county_id  "
+" and (start_date NOT REGEXP '^..........$' ||  new_topic.end_date NOT REGEXP '^..........$')"
+" group by Form "
+" order by  PARTNER,TARGET_POPULATION,SEX DESC,QUARTER ASC ";
          
     System.out.println(getdupsnameandage);       
          
          conn.rs=conn.st.executeQuery(getdupsnameandage);
     int count=0;     
    while(conn.rs.next()){
    count++;
    table+="<tr id='"+conn.rs.getString("topic_id")+"'>"
            + "<td>"+conn.rs.getString("start_date")+"</td>"
            + "<td>"+conn.rs.getString("end_date")+"</td>"
            + "<td>"+conn.rs.getString("Form")+"</td>"
            + "<td>"+conn.rs.getString("FACILITATOR")+"</td>"
            + "<td>"+conn.rs.getString("group_name")+"</td>"
            + "<td>"+conn.rs.getString("TARGET_POPULATION")+"</td>"
            + "<td>"+conn.rs.getString("PARTNER")+"</td>"
            + "<td>"+conn.rs.getString("district")+"</td>"
            + "<td>"+conn.rs.getString("COUNTY")+"</td>"
           
            + "<td>"+conn.rs.getString("MONTHS")+"</td>"
            + "<td>"+conn.rs.getString("YEAR")+"</td>"
            + "</tr>";
    
  // System.out.println(count+"~~"+table);
    
    
    }    
    
      table+=" </tbody>"; 
            out.println(table);   
            
        } catch (SQLException ex) {
            Logger.getLogger(invaliddate.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        finally { 
            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
