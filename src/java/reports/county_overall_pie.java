/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import hc.dbConn;
import java.io.IOException;
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

/**
 *
 * @author Geofrey Nyabuto
 */
public class county_overall_pie extends HttpServlet {
HttpSession session;
    ArrayList alist = new ArrayList();
String county_name,county_id,population_name,population_id;
int completed, incomplete,maxSessions;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        session=request.getSession();
        dbConn conn = new dbConn();
        alist.clear();
        
        
        population_name="";
        String county_n="SELECT * FROM county WHERE county_id='"+county_id+"'";
        conn.rs=conn.st.executeQuery(county_n);
        if(conn.rs.next()==true){
       county_name=conn.rs.getString(2);
       county_id=conn.rs.getString(1);
        }
       String target1_selector="select DISTINCT population_name from target_population";
 conn.rs=conn.st.executeQuery(target1_selector);
 while(conn.rs.next()){
    alist.add(conn.rs.getString(1)); 
 }
 for(int i=0;i<=alist.size();i++){
     completed=incomplete=0;
  population_name=alist.get(i).toString();
 String target_pop_selector="SELECT * FROM target_population WHERE population_name='"+population_name+"'";
 conn.rs=conn.st.executeQuery(target_pop_selector);
 while(conn.rs.next()){
     maxSessions=0;
     population_id=conn.rs.getString(1);
     String curriculum_selector="SELECT no_of_lessons FROM curriculum WHERE target_id='"+population_id+"'";
     conn.rs1=conn.st1.executeQuery(curriculum_selector);
     if(conn.rs1.next()==true){
         maxSessions=conn.rs.getInt(1);
     }
       String dist_selector="SELECT * from district WHERE county_id='"+county_id+"'";
       conn.rs1=conn.st1.executeQuery(dist_selector);
       while(conn.rs1.next()){
           String district_id=conn.rs1.getString(1);
        String groups_selector="SELECT COUNT(member_id) FROM member_details JOIN groups ON member_details.group_id=groups.group_id WHERE district_id='"+district_id+"' && target_pop_id='"+population_id+"' && member_details.sessions_attended='"+maxSessions+"'";
        conn.rs2=conn.st2.executeQuery(groups_selector);
        if(conn.rs2.next()){
            completed+=conn.rs2.getInt(1);
        }
        String incomplete_selector="SELECT COUNT(member_id) FROM member_details JOIN groups ON member_details.group_id=groups.group_id WHERE district_id='"+district_id+"' && target_pop_id='"+population_id+"' && member_details.sessions_attended<='"+maxSessions+"'";
        conn.rs2=conn.st2.executeQuery(incomplete_selector);
        if(conn.rs2.next()){
            incomplete+=conn.rs2.getInt(1);
        }   
           
           
       }

    }
 
 
 
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
            Logger.getLogger(county_overall_pie.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(county_overall_pie.class.getName()).log(Level.SEVERE, null, ex);
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
