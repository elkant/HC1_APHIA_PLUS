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
 * @author Elkant
 */
public class loadward extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         
           String dist=""; 
            
          dist=request.getParameter("dist");  
          
         
          
         
           String distid="";
           String distname="";
         
           if(request.getParameter("dist").split(",").length>1){
            String distar[]=dist.split(",");
             distid=distar[0];
             distname=distar[1];
           }
           else {
           distid= request.getParameter("dist");
           }
           
          dbConn conn= new dbConn();
          
          String wards="<option value=''>Select Ward</option>";
          
          String getwards="select * from ward where district_id='"+distid+"'";
          
          conn.rs=conn.st.executeQuery(getwards);
          
          while(conn.rs.next()){
          
          wards+="<option value='"+conn.rs.getString(1)+","+conn.rs.getString(2).toUpperCase()+"'> "+conn.rs.getString(2).toUpperCase()+" </option>";
                               }
          
          
          if(wards.equals("<option value=''>Select Ward</option>")){
              
              
              if(distname.equals("")){distname="this";}
          wards="<option value=''>No added wards for "+distname+" Subcounty</option>";
          }
           out.println(wards);
          
           } catch (SQLException ex) {
            Logger.getLogger(loadward.class.getName()).log(Level.SEVERE, null, ex);
       
          
           
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
