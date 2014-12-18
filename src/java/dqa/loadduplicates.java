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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Manuel
 */
public class loadduplicates extends HttpServlet {

  HttpSession session;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
            session=request.getSession();
            
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
         
          if(request.getParameter("targetpop")!=null  && !request.getParameter("targetpop").equals("null")){
         
         populationname=request.getParameter("targetpop");
         }
         
     
          System.out.println(populationname);
          
         
   String table=" <thead>"
		            +" <tr>"
		           +" <th>Participant</th>"
                           +" <th>Age</th>"
                           +" <th>Sex</th>"
                           +" <th>Group</th>     "                     
                           +" <th>Occurence</th>"
                           +" <th>Target Population</th>"
                            +" <th>District</th>"
                            +" <th>County</th>"
                            +" <th>Form Number</th>"
                            +" <th>Month</th>"
                            +" <th>Year</th>  "                          
                          
                            +" </tr>"		                
		            +" </thead>"
		            +" <tbody>";
                                
                     

                                
		              
         
         
         
          String getdupsnameandage=" select  u.member_id as id, "
+" concat(u.first_name,\" \",u.mname,\" \",u.sur_name,\" \",u.age) as participant,"
+" u.first_name,"
+" u.mname,"
+" u.sur_name,"
+" u.age,"
+" u.sex,"
+" u.month,u.year as yr,"
+" ocur ,"
+" case "
+" when ocur >1 then 'yes'"
+" else 'no'"
+" end as isduplicate,"
+" district_name,group_name,population_name as Target_Population,county_name,start_date,new_topic.end_date as enddate ,form_number,"
+" CONCAT(facilitator_details.first_name,\" \",facilitator_details.sur_name,\" (\",facilitator_details.phone,\")\") as FACILITATOR"
+" FROM member_details  u "
+" inner JOIN ("
+" select target_pop_id, member_id , first_name ,mname,sur_name,age , count(*) as ocur"
+" FROM member_details"
+" join (groups as f join target_population on f.target_pop_id=target_population.target_id) on member_details.group_id=f.group_id"
+" where (population_name LIKE '"+populationname+"' ) and sessions_attended>0 "
+" group by first_name,mname,sur_name ,age having ocur > 1"
+"  )  temp on u.first_name=temp.first_name "
+" and u.mname=temp.mname "
+" and u.sur_name=temp.sur_name "
+" and u.age=temp.age" 
+" join  (groups "
+" join (district join (county) on district.county_id=county.county_id ) on groups.district_id=district.district_id ) on u.group_id=groups.group_id"
+" join target_population on groups.target_pop_id=target_population.target_id "
+ "join ("
+" register_attendance "
       +" join new_topic on register_attendance.marked_date=new_topic.marking_date "
         +" join forms on register_attendance.form_id=forms.form_id"
           +" JOIN facilitator_details on register_attendance.facilitator_id=facilitator_details.facilitator_id) on u.member_id=register_attendance.member_id" 
+" where (population_name LIKE '"+populationname+"' ) and u.sessions_attended>0"
+" group by id order by participant ";
          
     System.out.println(getdupsnameandage);       
          
          conn.rs=conn.st.executeQuery(getdupsnameandage);
     int count=0;     
    while(conn.rs.next()){
    count++;
    table+="<tr id='"+conn.rs.getString("id")+"'>"
            + "<td>"+conn.rs.getString("participant")+"</td>"
            + "<td>"+conn.rs.getString("age")+"</td>"
            + "<td>"+conn.rs.getString("sex")+"</td>"
            + "<td>"+conn.rs.getString("group_name")+"</td>"
            + "<td>"+conn.rs.getString("ocur")+"</td>"
            + "<td>"+conn.rs.getString("Target_Population")+"</td>"
            + "<td>"+conn.rs.getString("district_name")+"</td>"
            + "<td>"+conn.rs.getString("county_name")+"</td>"
            + "<td>"+conn.rs.getString("form_number")+"</td>"
            + "<td>"+conn.rs.getString("u.month")+"</td>"
            + "<td>"+conn.rs.getString("yr")+"</td>"
            + "</tr>";
    
   // System.out.println(count+"~~"+table);
    
    
    }    
    
      table+=" </tbody>"; 
            out.println(table);   
            session.setAttribute("loadedtable",table);
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
