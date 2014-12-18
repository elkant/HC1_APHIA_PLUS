/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
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
public class getDates extends HttpServlet {

    String current_dates;
   HttpSession session; 
    
     String sch;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {try {
                  session=request.getSession();
//IF THE SESSIONS HAVE EXPIRED ,THE USER IS LOGGED OUT.
    if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    }
            response.setContentType("text/html;charset=UTF-8");
            Calendar cal= Calendar.getInstance();
            int year=cal.get(Calendar.YEAR);
            int lastyear=year-1;
            int month=cal.get(Calendar.MONTH)+1;
            int last_month=cal.get(Calendar.MONTH);
           
            
            //reset the session values
           
          
          sch="Select * from dates";
          
          
           current_dates="";
           
            
           
           dbConn conn=new dbConn();
           
           conn.rs1=conn.st1.executeQuery(sch);
           
           //add all the districts to the 
          
           current_dates="<option value=\"\">choose date</option>";
           
           while(conn.rs1.next()){
        
          //dynamically add districts to the string array
               String id=conn.rs1.getString("date_id");
               //this year
          if(id.equals("2")){
          current_dates=current_dates+"<option value=\""+month+"\">"+conn.rs1.getString("date_name")+"("+month+")"+"</option>";
          }
          //last year
          else if(id.equals("3")){
          current_dates=current_dates+"<option value=\""+last_month+"\">"+conn.rs1.getString("date_name")+"("+last_month+")"+"</option>";
          
          
          }
          //others
          else{
          current_dates=current_dates+"<option value=\""+conn.rs1.getString("date_id")+"\">"+conn.rs1.getString("date_name")+"</option>";
          
          
          }
         
           }
               
        System.out.println(current_dates);
           
           
           PrintWriter out = response.getWriter();
            
            out.println("<html>");
            out.println("<head>");           
            out.println("</head>");
            out.println("<body>");
            out.println("<h6>" +current_dates+"</h6>");
            out.println("</body>");
            out.println("</html>");
             
           
           
         // System.out.println(current_dates);
           
        
          // response.sendRedirect("myajax.html");
        } catch (SQLException ex) {
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
