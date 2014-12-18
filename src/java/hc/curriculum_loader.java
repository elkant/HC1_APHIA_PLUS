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

/**
 *
 * @author Geofrey Nyabuto
 */
public class curriculum_loader extends HttpServlet {
String curriculum_names,selector;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn= new dbConn();
            curriculum_names="<option value=\"\">Choose Curriculum.</option>";
            selector="select * from curriculum";
            conn.rs=conn.st.executeQuery(selector);
            while(conn.rs.next()){
                System.out.println("INSIDE CURRICULUM LOOP ");
                
                String targ_selector="SELECT * FROM  target_population WHERE target_id='"+conn.rs.getString("target_id") +"'";
                conn.rs2=conn.st2.executeQuery(targ_selector);
                if(conn.rs2.next()){
                
                String partner_selector=" SELECT * FROM partner WHERE partner_id='"+conn.rs2.getString("partner_id") +"'";
                conn.rs3=conn.st3.executeQuery(partner_selector);
                
                if (conn.rs3.next()){
                
            curriculum_names=curriculum_names+"<option value=\""+conn.rs.getString("curriculum_id") +","+conn.rs.getString("curriculum_name") +"\">"+conn.rs.getString("curriculum_name") +" ("+conn.rs3.getString(2) +" - "+conn.rs2.getString(2) +")</option>";
                }}
            }
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet curriculum_loader</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+curriculum_names+ "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            Logger.getLogger(curriculum_loader.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(curriculum_loader.class.getName()).log(Level.SEVERE, null, ex);
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
