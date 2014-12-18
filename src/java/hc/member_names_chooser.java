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
public class member_names_chooser extends HttpServlet {

    String district,current_schools;
   HttpSession session; 
    ArrayList dist=new ArrayList();
     String sch;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {try {
            response.setContentType("text/html;charset=UTF-8");
           
            
            
            session=request.getSession(); 
            
            //reset the session values
         //   session.setAttribute("sch_names","");
            
        
           sch="Select sur_name from member_details";
         
           current_schools="";
           
            
           
           dbConn conn=new dbConn();
           
           conn.rs=conn.st.executeQuery(sch);
           
           //add all the districts to the 
          
           current_schools="";
           
           while(conn.rs.next()){
               
         //dist arraylist stays in the session
          //dist.add(conn.rs.getString("school_name")); 
          
          
          //dynamically add districts to the string array
          
          current_schools=current_schools+"<option value=\""+conn.rs.getString("sur_name")+"\">";
       // session.setAttribute("sch_names",dist);
         //12767711
        //3840
       //      1994
       
         
           }
               
        
           
           
           PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h6>" +current_schools+"</h6>");
            out.println("</body>");
            out.println("</html>");
             
           
           System.out.println(""+current_schools);
           
           
        
          // response.sendRedirect("myajax.html");
        } catch (SQLException ex) {
            Logger.getLogger(member_names_chooser.class.getName()).log(Level.SEVERE, null, ex);
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
