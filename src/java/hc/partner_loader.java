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
public class partner_loader extends HttpServlet {
String district_det,district_id,selector,partners;
String district[];
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try { 
          district_det=request.getParameter("district");  
          district=district_det.split(",");
          district_id=district[0];
    String      district_name=district[1];
System.out.println(district_det);
            dbConn conn = new dbConn();
            partners="<option value=\"\">Choose Partner</option>";
            selector="select * from partners where district_id='"+district_id+"'";
            conn.rs=conn.st.executeQuery(selector);
            while(conn.rs.next())
            {
            
            partners=partners+"<option value=\""+conn.rs.getString("partner_id") +"\">"+conn.rs.getString("partner_name") +"-"+district_name+"</option>";
        }
        System.out.println("here it is");
         out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet partner_loader</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+partners+"</h1>");
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
            Logger.getLogger(partner_loader.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(partner_loader.class.getName()).log(Level.SEVERE, null, ex);
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
