/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dqa;

import hc.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
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
public class dqa_unmarkeddata extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
          dbConn conn= new dbConn();
          
             String populationname="FISHERFOLK";  
          
          
        
          
         
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
		           +" <th>Participant</th>"
                           +" <th>Age</th>"
                           +" <th>Sex</th>"
                           +" <th>Group</th>" 
                           +" <th>Expected Sessions</th>" 
                           +" <th>Sessions Attended</th>" 
                          
                           +" <th>Target Population</th>"
                           +" <th>Partner</th>"
                            +" <th>District</th>"
                            +" <th>County</th>"
                            +" <th>Form Number</th>"
                            +" <th>Month</th>"
                            +" <th>Year</th>  "
                            +" </tr>"		                
		            +" </thead>"
		            +" <tbody>";
                                
                     

                                
		              
         
         
         
          String getdupsnameandage=" SELECT member_details.member_id as member_id,"
+" member_details.first_name as fname,"
+" member_details.mname as mname,"
+" member_details.sur_name as lname, member_details.age as age," 
+" form_number as Form,"
+" group_name,"
+" CASE "
+" when SEX LIKE 'female' THEN 'F'"
+" when SEX LIKE 'male' THEN 'M'"
+" ELSE 'NO SEX'"
+" END AS SEX"
+" , partner_name as PARTNER, "
+" population_name  as TARGET_POPULATION,district_name as district,county_name as COUNTY,"
+" sessions_attended as SESSIONS_ATTENDED,no_of_lessons as EXPECTED_SESSION,"
+" CONCAT(facilitator_details.first_name,\" \",facilitator_details.sur_name,\" (\",facilitator_details.phone,\")\") as FACILITATOR"
+" ,case "
+" when member_details.MONTH =1 THEN 'JAN'"
+" when member_details.MONTH =2 THEN 'FEB'"
+" when member_details.MONTH =3 THEN 'MAR'"
+" when member_details.MONTH=4 THEN 'APR'"
+" when member_details.MONTH=5 THEN 'MAY'"
+" when member_details.MONTH=6 THEN 'JUN'"
+" when member_details.MONTH=7 THEN 'JUL'"
+" when member_details.MONTH=8 THEN 'AUG'"
+" when member_details.MONTH=9 THEN 'SEP'"
+" when member_details.MONTH=10 THEN 'OCT'"
+" when member_details.MONTH=11 THEN 'NOV'"
+" when member_details.MONTH=12 THEN 'DEC'"
+" END AS MONTHS"
+" ,member_details.year as YEAR,member_details.period as QUARTER ,"
+ "start_date,new_topic.end_date as end_date,no_of_lessons "
+" FROM groups "
+" join ( member_details  left join " 
+" (register_attendance "
+" left join forms on register_attendance.form_id=forms.form_id"
+" left JOIN facilitator_details on register_attendance.facilitator_id=facilitator_details.facilitator_id"
+" join new_topic on register_attendance.marked_date=new_topic.marking_date "
+" ) "
+" on member_details.member_id = register_attendance.member_id )"
+" on member_details.group_id=groups.group_id "
+" join target_population on groups.target_pop_id=target_population.target_id"
+" join district on groups.district_id=district.district_id"
+" join partner on groups.partner_id=partner.partner_id "
+" join curriculum on groups.target_pop_id=curriculum.target_id,county"
+" where district.county_id=county.county_id  "
+" and   sessions_attended =0 and  !(form_number is Null) "
+"  "
+" group by member_details.member_id "
+" order by  group_name ASC ";
          
     System.out.println(getdupsnameandage);       
          
          conn.rs=conn.st.executeQuery(getdupsnameandage);
     int count=0;     
    while(conn.rs.next()){
    count++;
    table+="<tr id='"+conn.rs.getString("member_id")+"'>"
            + "<td style='text-align:center;'>"+conn.rs.getString("fname")+" "+conn.rs.getString("mname")+" "+conn.rs.getString("lname")+"</td>"
            + "<td style='align:center;'>"+conn.rs.getString("age")+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString("sex")+"</td>"
            + "<td >"+conn.rs.getString("group_name")+"</td>"       
            + "<td style=\"text-align:center;\" >"+conn.rs.getString("no_of_lessons")+"</td>"       
            + "<td style='text-align:center;'> 0 </td>"       
            + "<td style='align:center;'>"+conn.rs.getString("TARGET_POPULATION")+"</td>"
            + "<td style='align:center;'>"+conn.rs.getString("PARTNER")+"</td>"
            + "<td style='align:center;'>"+conn.rs.getString("district")+"</td>"
            + "<td style='align:center;'>"+conn.rs.getString("COUNTY")+"</td>"
            + "<td style='text-align:center;'>"+conn.rs.getString("Form")+"</td>"
            + "<td style='align:center;'>"+conn.rs.getString("MONTHS")+"</td>"
            + "<td style='align:center;'>"+conn.rs.getString("YEAR")+"</td>"
            + "</tr>";
    
   // System.out.println(count+"~~"+table);
    
    
    
    
    
    
    }    
    
      table+=" </tbody>"; 
      
      System.out.println(table);
            out.println(table);   
            
        } catch (SQLException ex) {
            Logger.getLogger(loadduplicates.class.getName()).log(Level.SEVERE, null, ex);
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
