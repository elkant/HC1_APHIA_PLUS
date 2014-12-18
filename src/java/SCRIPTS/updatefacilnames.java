/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SCRIPTS;

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
public class updatefacilnames extends HttpServlet {

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
    
    
    
    dbConn conn= new dbConn();
    
    
    String getallfacils="select * from facilitator_details where phone !='' ";
    
    conn.rs=conn.st.executeQuery(getallfacils);
    
    while(conn.rs.next()){
    //get the initial details of a facilitator
        String facilid=conn.rs.getString("facilitator_id");
        
        String phone=conn.rs.getString("phone");
        
        String fname=conn.rs.getString("first_name").toString().trim();
        
        String sur_name=conn.rs.getString("sur_name").toString().trim();
        
        //now get the facilitator whose phone is as shown above
        
        
        conn.rs1=conn.st1.executeQuery("select * from facilitator_details where phone LIKE '"+phone+"' and facilitator_id !='"+facilid+"' ");
        
        while(conn.rs1.next()){
        
        System.out.println("old details__"+fname+" "+sur_name +"  ==== new details "+conn.rs1.getString("first_name")+" "+conn.rs1.getString("sur_name"));
        
        
        //update the names
        
        conn.st2.executeUpdate("update facilitator_details set first_name='"+fname+"' , sur_name='"+sur_name+"' where facilitator_id='"+conn.rs1.getString("facilitator_id") +"'");
        
        }
        
    }
    
    
    PrintWriter out = response.getWriter();
    try {
        /* TODO output your page here
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet updatefacilnames</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Servlet updatefacilnames at " + request.getContextPath () + "</h1>");
        out.println("</body>");
        out.println("</html>");
         */
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(updatefacilnames.class.getName()).log(Level.SEVERE, null, ex);
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
