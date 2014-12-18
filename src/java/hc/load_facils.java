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
public class load_facils extends HttpServlet {
String current_curs;
  HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    }          
System.out.println("in java");
               current_curs="<option value=\"\">Choose Facilitator</option>";
               
               String districts="Select * from facilitator_details where partner_id='"+session.getAttribute("sc_partner") +"' and groups_id LIKE '%"+session.getAttribute("added_group_id")+"%'";
                 //String districts="Select * from facilitator_details where partner_id='4'";
                
               dbConn conn=new dbConn();
               
               conn.rs=conn.st.executeQuery(districts);
               
               //add all the districts to the 
              
               while(conn.rs.next()){
             System.out.println(conn.rs.getString("facilitator_id"));      
              current_curs=current_curs+"<option value=\""+conn.rs.getString("facilitator_id")+"\">"+conn.rs.getString("first_name")+" "+conn.rs.getString("middle_name")+" "+conn.rs.getString("sur_name")+" ( "+conn.rs.getString("phone")+" ) </option>";
}System.out.println(current_curs);
            
 if(current_curs.equals("<option value=\"\">Choose Facilitator</option>")){
               current_curs="<option value=\"\">No added facilitators </option>";
               }

                out.println("<h1>" +current_curs+"</h1>");
            
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
            Logger.getLogger(load_facils.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_facils.class.getName()).log(Level.SEVERE, null, ex);
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
