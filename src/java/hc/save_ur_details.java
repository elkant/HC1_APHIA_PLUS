/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
public class save_ur_details extends HttpServlet {
String fname,mname,lname,username,phone,password,userid;
int counter;
    MessageDigest m;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            dbConn conn = new dbConn();
            counter=0;
            session=request.getSession();
            
           userid=request.getParameter("userid");
           fname=request.getParameter("fname");
   //        mname=request.getParameter("mname");
           lname=request.getParameter("lname");
           if(request.getParameter("username")!=null){
           username=request.getParameter("username");
           }else {
        username="m&e";
        }
           
           
           phone=request.getParameter("phone");
           password=request.getParameter("pass");
           
           
            //encrypt password
               
                   m = MessageDigest.getInstance("MD5");
                   m.update(password.getBytes(), 0, password.length());
                   String pw = new BigInteger(1, m.digest()).toString(16);
           
           
           
           if(userid!=null && !userid.equals("")){
               
              String check_userid_existence="SELECT COUNT(userid) FROM users WHERE userid='"+userid+"'";
              conn.rs=conn.st.executeQuery(check_userid_existence);
              if(conn.rs.next()==true){
                  counter=conn.rs.getInt(1);
                 }
               
               if(counter>0){
                  String updateusers="UPDATE users SET username='"+username+"',password='"+pw+"' WHERE userid='"+userid+"'";
                  String updator="UPDATE clerks SET sur_name='"+lname+"',first_name='"+fname+"',phone='"+phone+"' WHERE clerk_id='"+userid+"'";
            int update= conn.st.executeUpdate(updator);
            int updateclerks= conn.st.executeUpdate(updateusers);
         
            if(update>0){
            session.setAttribute("username", username); 
               }
            
               }
           }
    conn.rs.close();
    conn.st.close();
           response.sendRedirect("edit_ur_details.jsp");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(save_ur_details.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_ur_details.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(save_ur_details.class.getName()).log(Level.SEVERE, null, ex);
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
