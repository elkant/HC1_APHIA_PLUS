/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class edit_facilitator extends HttpServlet {
String fname,lname,mname,phone,id,updater,nextpage,name;
HttpSession session;
int found,added;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
                response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
     found=added=0;
        dbConn conn= new dbConn();
        fname=request.getParameter("fname");
        mname=request.getParameter("mname");
        lname=request.getParameter("lname");
        phone=request.getParameter("phone");
        id=request.getParameter("facilitator_id");
      System.out.println(id);  
                   String check_phone="SELECT * FROM facilitator_details WHERE phone='"+phone+"' && (first_name!='"+fname+"'&& middle_name!='"+mname+"' && sur_name!='"+lname+"') ";
            conn.rs=conn.st.executeQuery(check_phone);
            if(conn.rs.next()==true){
              found=2; 
             phone=conn.rs.getString("phone");
            name=conn.rs.getString("first_name")+" "+conn.rs.getString("sur_name");
            } 
            if(found==0){
                
                  Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
               // timestamp='"+mdate+"',
  updater="UPDATE facilitator_details set timestamp='"+mdate+"', first_name='"+fname+"', middle_name='"+mname+"',sur_name='"+lname+"',phone='"+phone+"' WHERE facilitator_id='"+id+"'" ;     
added= conn.st.executeUpdate(updater);  
 session.setAttribute("edit_facilitator","<font color=\"blue\">"+ fname+" "+lname+"</font><font color=\"green\"> Details Edited Successfully</font>");
 nextpage="edit_facilitatorbn";
            }
if(found>0){
  session.setAttribute("edit_facilitator", "<font color=\"black\"> Phone Number : "+phone+"</font><font color=\"red\"> has been registered to</font><font color=\"black\">   :  "+name+". if they are the same person, edit one field at go and not the three names at once..</font> ");
 nextpage="edit_facilitatorbn";
}
found=0;
System.out.println("SESSION  :  "+session.getAttribute("edit_facilitator").toString());
 
 response.sendRedirect(nextpage);
  } finally {            
            out.close();
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
            Logger.getLogger(edit_facilitator.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_facilitator.class.getName()).log(Level.SEVERE, null, ex);
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
