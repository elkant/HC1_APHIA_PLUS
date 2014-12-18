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
 * @author Nyabuto Geofrey
 */
public class edit_clerk extends HttpServlet {
String query1,query2,qr3,userid,fname,mname,sname,username,phoneno;
HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         dbConn conn=new dbConn();
       session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
userid=request.getParameter("userid");
fname=request.getParameter("fname");

sname=request.getParameter("sname");
username=request.getParameter("username");
phoneno=request.getParameter("phoneno");
System.out.println(userid);      
 System.out.println(fname);
 System.out.println(mname);
 System.out.println(sname);
 System.out.println(username);
 System.out.println(phoneno);
query1="UPDATE clerks SET first_name='"+fname+"',sur_name='"+sname+"', phone='"+phoneno+"' where clerk_id='"+userid+"'";
qr3="UPDATE users SET username='"+username+"' where userid='"+userid+"'";

conn.st.executeUpdate(query1);
conn.st1.executeUpdate(qr3);
if(conn.st.executeUpdate(query1)==1){
session.setAttribute("success_edit","<font color=\"green\">"+ fname+"s details edited Successfully</font>");
}
else{

session.setAttribute("success_edit", "Clerk Edit failed");
}
System.out.println(session.getAttribute("success_edit"));
response.sendRedirect("edit_clerk.jsp");

        }
        finally {            
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
            Logger.getLogger(edit_clerk.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_clerk.class.getName()).log(Level.SEVERE, null, ex);
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
