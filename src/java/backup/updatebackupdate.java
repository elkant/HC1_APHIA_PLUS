/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emmanuel E
 */
public class updatebackupdate extends HttpServlet {

 HttpSession session=null;   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        session=request.getSession();
        String backupdate="";
           String msg="<font color=\"red\">Date Not Updated</font>";   
        if(request.getParameter("backupdate")!=null){
        backupdate=request.getParameter("backupdate");
        }
        dbConn conn= new dbConn();
        
        if(!backupdate.equals("")){
        
            try {
                int maxid=1;
                String getmax="select Max(timestampid) as id from timestamp";
                
                conn.rs=conn.st.executeQuery(getmax);
                if(conn.rs.next()){
                    maxid=conn.rs.getInt(1);
                }
                
               String update="update timestamp set timestamp='"+backupdate+"' where timestampid='"+maxid+"' ";
         
               if(conn.st.executeUpdate(update)==1){
               
               msg="<font color=\"green\"> Date Updated Succesfully </font>"; 
               
               }
               
               if(conn.rs!=null){conn.rs.close();}
               if(conn.st!=null){conn.st.close();}
                        } catch (SQLException ex) {
                Logger.getLogger(updatebackupdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
         
            session.setAttribute("backupupdate",msg);
       
             response.sendRedirect("backdate.jsp"); 
       
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
