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
public class checkgroup extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        try {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    dbConn conn= new dbConn();
    
    String grpname=request.getParameter("groupname");
    
    String qr="select group_name,partner_name,population_name from groups join partner on groups.partner_id=partner.partner_id join target_population on groups.target_pop_id=target_population.target_id where group_name LIKE '"+grpname.trim()+"'";
    
    conn.rs=conn.st.executeQuery(qr);
    
    
    if(conn.rs.next()){
    
         try {
       
        //out.println("yes~"+conn.rs.getString(2)+"~"+conn.rs.getString(3));
        out.println("<b> <font color='green'>"+conn.rs.getString(1)+" </font></b> group has already been added under <font color='green'> <b>"+conn.rs.getString(2)+"</b></font> partner and target population <font color='green'> <b>"+conn.rs.getString(3)+"<b> </font>. <i><font color='black'>                Change your group name slightly by adding a suffix of the partner name e.g.  </font> <font color='orange'>"+conn.rs.getString(1)+"-"+conn.rs.getString(2)+"</i></font>");
        
        
        
      System.out.println("group already exists");
       
    } finally {            
        out.close();
    }
    
    
    }
    else {
    
    out.println("");
    }
    
    
    
   
}       catch (SQLException ex) {
            Logger.getLogger(checkgroup.class.getName()).log(Level.SEVERE, null, ex);
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
