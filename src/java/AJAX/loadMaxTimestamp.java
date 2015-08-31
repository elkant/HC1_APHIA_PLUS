/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Emmanuel E
 */
public class loadMaxTimestamp extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            
            dbConn conn= new dbConn();
            PrintWriter out = response.getWriter();
            
            int maxid=1;
            String getmax="select Max(timestampid) as id from timestamp";
            
            conn.rs=conn.st.executeQuery(getmax);
            if(conn.rs.next()){
                maxid=conn.rs.getInt(1);
            }
            
            //now get the maximum no.
            
            String lasttimestamp="yyyy-mm-dd";
            String maxtimestamp="select timestamp from timestamp where timestampid='"+maxid+"'";
           
            conn.rs=conn.st.executeQuery(maxtimestamp);
            while(conn.rs.next()){
            
            lasttimestamp=conn.rs.getString(1);
                
            
            }
            try {
                /* TODO output your page here. You may use following sample code. */
                
                out.println(lasttimestamp);
            } finally {
                out.close();
                
                
                conn.rs.close();
                conn.st.close();
            }
        }   catch (SQLException ex) {
            Logger.getLogger(loadMaxTimestamp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
