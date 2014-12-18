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
 * @author SIXTYFOURBIT
 */
public class save_sdm_mail extends HttpServlet {

    String mail="",usermail="",county="",partner="";;
    
    HttpSession session;
    
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        try {
    response.setContentType("text/html;charset=UTF-8");
    
    session=request.getSession();
    PrintWriter out = response.getWriter();
    
    
    if(request.getParameter("mandemail")!=null){
        
    mail=request.getParameter("mandemail");
    
    }
    
    if(request.getParameter("usermail")!=null){
        
    usermail=request.getParameter("usermail");
    
    }
    
    
    if(request.getParameter("county")!=null){
        
    county=request.getParameter("county");
    
    }
    
    if(request.getParameter("partner")!=null){
        
    partner=request.getParameter("partner");
    
    }
    
    dbConn1 conn=new dbConn1();
    
    
    if(conn.st.executeUpdate("update mande_mail set mail='"+mail+"'  , county='"+county+"',partner='"+partner+"' , usermail='"+usermail+"'  where mailid='2'")==1){
    
        
       
   session.setAttribute("mailchanged", "<h3><font color=\"green\">Details changed successfully.You may now Continue with other activities </font></h3>");     
    }
    
    else if(conn.st.executeUpdate("insert into mande_mail set mail='"+mail+"', county='"+county+"',partner='"+partner+"' , usermail='"+usermail+"',  mailid='2'")==1){
    
        
       
    session.setAttribute("mailchanged", "<h3><font color=\"green\">Details changed successfully.You may now Continue with other activities </font></h3>");     
    }
    
    else{
        
        
        
    
         session.setAttribute("mailchanged", "<font color=\"red\">details NOT changed. Try again</font>"); 
        
    }
   
    response.sendRedirect("update_sdm_email.jsp");
    
    
    try {
       
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(editmail.class.getName()).log(Level.SEVERE, null, ex);
            
            
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
