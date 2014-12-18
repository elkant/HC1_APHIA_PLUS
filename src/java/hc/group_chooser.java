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
public class group_chooser extends HttpServlet {

    String current_groups,partner,population_id;
   HttpSession session; 
  String status=""; 
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
          
            
            
            //_____________________________________________
            
            
            //show status of added topics
            
            
            //____________________________________________
            
           //get all the group ids from a jint of two tables 
           String gpi_checker=" select  distinct group_id from member_details join register_attendance on member_details.member_id = register_attendance.member_id"; 
            
            
            
           population_id=request.getParameter("popu"); 
           partner=request.getParameter("dist");
         System.out.println(" County:"+ partner); 
           current_groups="";
           
           String districts="Select * from groups where target_pop_id='"+population_id+"'";
           
           dbConn conn=new dbConn();
           
           conn.rs=conn.st.executeQuery(districts);
           
           //add all the districts to the 
          
           current_groups="<option value=\"\">choose group</option>";
           
           while(conn.rs.next()){
               
         //dist arraylist stays in the session
         
          
          
          //dynamically add districts to the string array
          
               
               conn.rs1=conn.st2.executeQuery(gpi_checker);
               String statustitle="";
               while(conn.rs1.next()){
                   
               if(conn.rs.getString("group_id").equals(conn.rs1.getString("group_id"))){
               
               status="+";
               statustitle="This groups attendance is already MARKED. Click to update its attendance";
               }
               else{
               status="";
               statustitle=" Click group to mark its attendance";
               }
               
               }
               
               
          current_groups=current_groups+"<option title=\""+statustitle+"\"  value=\""+conn.rs.getString("group_id")+"\">"+status+" "+conn.rs.getString("group_name")+"</option>";
     status="";
     statustitle="";
         
           }
               
           
          //  System.out.println("size:" +dist.size());
      
          // System.out.println("My districts:"+current_districts);
           
           
           PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +current_groups+"</h1>");
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
