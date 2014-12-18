/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AJAX;

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
public class groupdetails extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    
    String groupname=request.getParameter("grp").trim();
    
    
    String get="select * from groups left join district on groups.district_id=district.district_id left join partner on groups.partner_id=partner.partner_id where group_name LIKE ?";
    
    dbConn conn= new dbConn();
    conn.pst=conn.conne.prepareCall(get);
    
    conn.pst.setString(1, groupname);
    conn.rs=conn.pst.executeQuery();
    String mytable="<tr><td>Group Name</td><td>District</td><td>Partner</td></tr>";
    while(conn.rs.next()){
   mytable+="<tr><td>"+conn.rs.getString("group_name") +"</td><td>"+conn.rs.getString("district_name")+"</td><td>"+conn.rs.getString("partner_name")+"</td></tr>"; 
    
    }
    
    if(mytable.equals("<tr><td>Group Name</td><td>District</td><td>Partner</td></tr>")){
    
    mytable="";
    }
    
    
    PrintWriter out = response.getWriter();
    try {
        
        out.println(mytable);
     
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(groupdetails.class.getName()).log(Level.SEVERE, null, ex);
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
