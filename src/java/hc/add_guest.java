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
public class add_guest extends HttpServlet {
String userid,fname,mname,lname,position,username,password,level,nextpage;
HttpSession session;
MessageDigest m;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
              session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
            dbConn conn=new dbConn();
            userid=request.getParameter("userid");
           fname=request.getParameter("fname");
           mname=request.getParameter("mname");
           lname=request.getParameter("lname");
           position=request.getParameter("position");
           username=request.getParameter("username");
           password=request.getParameter("password");
           level="5";
//           System.out.println("userid="+userid+" fname="+fname+" mname="+mname+" lname="+lname+" position="+position+" username="+username+"password="+password);
                       m = MessageDigest.getInstance("MD5");
                            m.update(password.getBytes(), 0, password.length());
                            String pw = new BigInteger(1, m.digest()).toString(16);
String inserter="INSERT INTO guest (user_id,first_name,middle_name,last_name,position) values('"+userid+"','"+fname+"','"+mname+"','"+lname+"','"+position+"')";
int count=conn.st.executeUpdate(inserter);
if(count>0){
 String inserter2="INSERT INTO users(userid,username,password,level)values('"+userid+"','"+username+"','"+pw+"','"+level+"')" ;  
 int count2=conn.st2.executeUpdate(inserter2); 
 if(count2>0){
     session.setAttribute("guest_success", "Registered Successfully");
     nextpage="index.jsp";
 }
 else{
      session.setAttribute("guest_success", "Failed to register the clerk");
     nextpage="index.jsp";    
 }
}

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
                    try {
                        processRequest(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(add_guest.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(add_guest.class.getName()).log(Level.SEVERE, null, ex);
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
                    try {
                        processRequest(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(add_guest.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(add_guest.class.getName()).log(Level.SEVERE, null, ex);
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
