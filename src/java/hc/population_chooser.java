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
public class population_chooser extends HttpServlet {

  String district, currentgroups;
  HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
          try {
            response.setContentType("text/html;charset=UTF-8");
           
            
            
               session=request.getSession();
//     if (session.getAttribute("userid") == null) {
//        response.sendRedirect("index.jsp");
//    }  
    String partner_id="";        
            //reset the session values
        dbConn conn=new dbConn();    
   String partner=request.getParameter("partner");         
    String partner_chooser="select * from partner where partner_id='"+partner+"'"; 
    conn.rs=conn.st.executeQuery(partner_chooser);
    while(conn.rs.next()){
  String partner_id_chooser="select * from partner where partner_name='"+conn.rs.getString("partner_name") +"'" ;
  conn.rs1=conn.st1.executeQuery(partner_id_chooser);
  conn.rs1.next();
  partner_id=conn.rs1.getString("partner_id");
        
        
        
    }
          
        //   System.out.println(" County:"+ county_name); 
           currentgroups="";
           
           String districts="Select * from target_population where partner_id='"+partner_id+"'";
           
         
           
           conn.rs=conn.st.executeQuery(districts);
           
           //add all the districts to the 
          
           currentgroups="<option value=\"\">Choose Target Population</option>";
           
           while(conn.rs.next()){
               
         //dist arraylist stays in the session
         
          
          
          //dynamically add districts to the string array
          
          currentgroups=currentgroups+"<option value=\""+conn.rs.getString("target_id")+"\">"+conn.rs.getString("population_name")+"</option>";
       
         
           }
               
           
          //  System.out.println("size:" +dist.size());
      
          // System.out.println("My districts:"+current_districts);
           
           
           PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +currentgroups+"</h1>");
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
