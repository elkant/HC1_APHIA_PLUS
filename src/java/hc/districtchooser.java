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
 * @author Geofrey Nyabuto
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


public class districtchooser extends HttpServlet {

    String county_id,current_districts;
   HttpSession session; 
    ArrayList dist=new ArrayList();
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
           response.setContentType("text/html;charset=UTF-8");
           session=request.getSession();
           county_id=request.getParameter("county_id");
           System.out.println(county_id);
        //   System.out.println(" County:"+ county_name); 
           current_districts="";
           
           String districts="Select * from district where county_id='"+county_id+"'";
           
           dbConn conn=new dbConn();
           
           conn.rs=conn.st.executeQuery(districts);
           
           //add all the districts to the 
          
           current_districts="<option value=\"\">Choose sub-county</option>";
           
           while(conn.rs.next()){

current_districts=current_districts+"<option value=\""+conn.rs.getString("district_id")+","+conn.rs.getString("district_name") +"\">"+conn.rs.getString("district_name")+"</option>";



           }
           PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +current_districts+"</h1>");
            out.println("</body>");
            out.println("</html>");
             
           
  
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

