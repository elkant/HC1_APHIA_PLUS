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
public class partner_chooser1 extends HttpServlet {
String current_districts,dist;
   HttpSession session; 
   
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
           
            
            session=request.getSession();
           
            
         String partnerid= session.getAttribute("partner_id").toString();
            
            //reset the session values
       
              
            dbConn conn=new dbConn();
           dist=request.getParameter("dist"); 
        //   System.out.println(" County:"+ county_name); 
           current_districts="";
           String district_name="";
            current_districts="<option value=\"\">Choose Partner</option>";
           
//           String partner_id=conn.rs0.getString("partner_id");
//           district_name=conn.rs0.getString("district_name");
           String districts="Select * from partner ";
       
          conn.rs=conn.st.executeQuery(districts);
           
           //add all the districts to the 

           while(conn.rs.next()){
           if(partnerid.equals(conn.rs.getString("partner_id"))){
          current_districts=current_districts+"<option selected value=\""+conn.rs.getString("partner_id")+"\">"+conn.rs.getString("partner_name")+"</option>";
           }
           else {
            current_districts=current_districts+"<option value=\""+conn.rs.getString("partner_id")+"\">"+conn.rs.getString("partner_name")+"</option>";
           
           
           }
           }
               
   
           
           PrintWriter out = response.getWriter();
           System.out.println("HERE ARE THE GROUPS: "+current_districts) ;
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +current_districts+"</h1>");
            out.println("</body>");
            out.println("</html>");
             
           
           
           
           
        
          // response.sendRedirect("myajax.html");
        } catch (SQLException ex) {
            Logger.getLogger(partner_chooser1.class.getName()).log(Level.SEVERE, null, ex);
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
