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
public class edit_tp_session extends HttpServlet {
HttpSession session; 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn=new dbConn();
            
              session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
 String partner=request.getParameter("partner_name");

String tp_namer=request.getParameter("tp");

String partner_name_selector="SELECT partner_name from partner WHERE partner_id='"+partner+"'";
conn.rs=conn.st.executeQuery(partner_name_selector);
conn.rs.next();

String partner_name=conn.rs.getString(1);


String target_pop_selector="SELECT population_name,age_group_id FROM target_population WHERE target_id='"+tp_namer+"'";
conn.rs=conn.st.executeQuery(target_pop_selector);
conn.rs.next();

String population_name=conn.rs.getString(1);
String age_group_id=conn.rs.getString(2);

session.setAttribute("partner_id", partner);
session.setAttribute("partner_name", partner_name);

String age_grouper="SELECT * FROM age_groups WHERE age_group_id='"+age_group_id+"'";
conn.rs=conn.st.executeQuery(age_grouper);
conn.rs.next();

String age_name=conn.rs.getString(2);

session.setAttribute("ager", age_name);
session.setAttribute("ager_id", age_group_id);

session.setAttribute("tp_id", tp_namer);
session.setAttribute("tp_name", population_name);
response.sendRedirect("edit_target_population2.jsp");
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
            Logger.getLogger(edit_tp_session.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_tp_session.class.getName()).log(Level.SEVERE, null, ex);
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
