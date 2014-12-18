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

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_edit_attendance_ajax extends HttpServlet {
String group, year,period,month;
String leveler="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
          try {
            response.setContentType("text/html;charset=UTF-8");
            
        dbConn conn = new dbConn();
      
        leveler="";
group=request.getParameter("group").trim();
year=request.getParameter("year");
period=request.getParameter("period");
month=request.getParameter("month");

System.out.println(" group is : "+group);
System.out.println(" year is : "+year);
System.out.println(" period is : "+period);
System.out.println(" Month is : "+month);
//ensure that all details are selected have been selected before starting to check..



String db_checker="SELECT * FROM member_details WHERE group_id='"+group+"' and year='"+year+"' and period='"+period+"' and month='"+month+"'";
conn.rs=conn.st.executeQuery(db_checker);
if(conn.rs.next()){
   leveler="found";
   System.out.println(" found");
}
else{
    leveler="<font color=\"red\">No Participants have been added in that group for the chosen period and year.Add participants  <a href=\"filter_member.jsp\" class=\"linkstyle\" target=\"_blank\" >here</a></font>";
     System.out.println(" missing");
}
           System.out.println(leveler);
           PrintWriter out = response.getWriter();
            
           if(!period.equals("")&&!year.equals("")&&period!=null&&year!=null){
            out.println(leveler);
              }
      
          // response.sendRedirect("myajax.html");
        } catch (SQLException ex) {
            Logger.getLogger(load_edit_attendance_ajax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(load_edit_attendance_ajax.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(load_edit_attendance_ajax.class.getName()).log(Level.SEVERE, null, ex);
        }
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