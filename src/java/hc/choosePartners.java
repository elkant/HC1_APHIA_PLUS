/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class choosePartners extends HttpServlet {

    String current_districts,dist;
   HttpSession session; 
   
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
           
            
            
           
            
            //reset the session values
       
            
            dbConn conn=new dbConn();
           dist=request.getParameter("dist"); 
        //   System.out.println(" County:"+ county_name); 
           current_districts="";
           String district_name="";
           String district_name_selector="select * from district where district_id='"+dist+"'";
           conn.rs0=conn.st0.executeQuery(district_name_selector);
           conn.rs0.next();
           district_name=conn.rs0.getString("district_name");
           String districts="Select * from partners where district_id='"+dist+"'";
           System.out.println("district "+district_name);
           
           
           conn.rs=conn.st.executeQuery(districts);
           
           //add all the districts to the 
          
           current_districts="<option value=\"\">choose partner</option>";
           
           while(conn.rs.next()){
               
         
          
          current_districts=current_districts+"<option value=\""+conn.rs.getString("partner_id")+"\">"+conn.rs.getString("partner_name")+"-"+district_name+"</option>";
       
         
           }
               
         
           
           PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +current_districts+"</h1>");
            out.println("</body>");
            out.println("</html>");
             
           
           
           
           
        
          // response.sendRedirect("myajax.html");
        } catch (SQLException ex) {
            Logger.getLogger(districtchooser.class.getName()).log(Level.SEVERE, null, ex);
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
