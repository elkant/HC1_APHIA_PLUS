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
public class edit_partner extends HttpServlet {
String updater,partner_id, new_partner,nextpage="";
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
dbConn conn= new dbConn();
partner_id=request.getParameter("partner");
new_partner=request.getParameter("new_partner");
System.out.println("new name"+new_partner);
if(new_partner==null){
    session.setAttribute("partner_editor","<font color=\"red\">Enter New Name</font>");
nextpage="edit_partner.jsp";
}
else{
  new_partner=new_partner.toUpperCase();  
//System.out.println("old partner id :"+partner_id+" and new Partner:"+new_partner);
updater="UPDATE partner set partner_name='"+new_partner+"' WHERE partner_id='"+partner_id+"'";
int count=conn.st.executeUpdate(updater);
if(count>0){
session.setAttribute("partner_editor","<font color=\"Blue\">"+new_partner+"</font><font color=\"green\"> partner name was saved successfully.</font>");
nextpage="edit_partner.jsp";
}
else{
    nextpage="edit_partner.jsp";
session.setAttribute("partner_editor","<font color=\"red\">Failed to change Partner name</font>");
}
response.sendRedirect(nextpage);
        } 
        
        }
        
        
        finally {            
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
            Logger.getLogger(edit_partner.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_partner.class.getName()).log(Level.SEVERE, null, ex);
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
