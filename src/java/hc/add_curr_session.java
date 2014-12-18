/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Geofrey Nyabuto
 */
public class add_curr_session extends HttpServlet {
String county,district,county_id,district_id,county_name,district_name,partner,partner_name,partner_id;
String [] all_county,all_district,all_partner;
HttpSession session;
String groups,selector,target_pop_name;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

     session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
partner_id=request.getParameter("partner_name");
String target_pop=request.getParameter("target_pop");
System.out.println("county: "+county_id);
System.out.println("district: "+district_id);
System.out.println("partner: "+partner_id);
System.out.println("target_pop: "+target_pop);


//get partner name
String selector="select * from partner where partner_id='"+partner_id+"'";
dbConn conn= new dbConn();
conn.rs=conn.st.executeQuery(selector);
while (conn.rs.next()){
partner_name=conn.rs.getString("partner_name").toString();

}
//get target pop name
String selector3="select * from target_population where target_id='"+target_pop+"'";
conn.rs=conn.st.executeQuery(selector3);
while (conn.rs.next()){
target_pop_name=conn.rs.getString("population_name").toString();
}
session.setAttribute("target_pop_id", target_pop);
session.setAttribute("district_id", district_id);
session.setAttribute("district_name", district_name );
session.setAttribute("partner_id", partner_id);
session.setAttribute("partner_name", partner_name);
session.setAttribute("target_pop_name", target_pop_name);
  response.sendRedirect("add_curriculum.jsp");
        } finally {            
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
            Logger.getLogger(add_curr_session.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_curr_session.class.getName()).log(Level.SEVERE, null, ex);
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
