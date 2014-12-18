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
public class getcurs extends HttpServlet {
String current_curs;
  HttpSession session;  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");

       session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    }          

               current_curs="";
               
               String districts="Select * from curriculum where target_id='"+session.getAttribute("s_target_pop")+"'";
               
               dbConn conn=new dbConn();
               
               conn.rs=conn.st.executeQuery(districts);
               
               //add all the districts to the 
              
               while(conn.rs.next()){
                   session.setAttribute("cur_curriculum", conn.rs.getString("curriculum_id"));
              current_curs=current_curs+"<option value=\""+conn.rs.getString("curriculum_id")+"\">"+conn.rs.getString("curriculum_name")+"</option>";
}

               PrintWriter out = response.getWriter();
                
                out.println("<html>");
                out.println("<head>");           
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>" +current_curs+"</h1>");
                out.println("</body>");
                out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(getcurs.class.getName()).log(Level.SEVERE, null, ex);
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
