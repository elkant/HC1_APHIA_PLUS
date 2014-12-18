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
public class loadtargets extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String foundtarget="";
        try {
           
            
            String year=request.getParameter("year");
            String partner=request.getParameter("partner");
            String targetpop=request.getParameter("targetpop");
            String county=request.getParameter("county");
            String sex=request.getParameter("sex");
            String gettargets="select target from targets where year='"+year+"' and targetpop='"+targetpop+"' and countyid='"+county+"' and sex='"+sex+"' and partnerid='"+partner+"'";
            
           System.out.println(gettargets); 
            
            dbConn conn= new dbConn();
            conn.rs=conn.st.executeQuery(gettargets);
            
            if(conn.rs.next()){
                
            foundtarget=conn.rs.getString(1);
            
            }
          
            System.out.println("foundtarget is :"+foundtarget);
            //only show the value when some data is found in the database.
           // if(!foundtarget.equals("")){
            out.println(foundtarget);
            
            //} 
            
        } catch (SQLException ex) {
            Logger.getLogger(loadtargets.class.getName()).log(Level.SEVERE, null, ex);
            
            
           
            
        } finally {   
            
            out.close();
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
