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
 * @author Geofrey Nyabuto
 */
public class edit_curriculum extends HttpServlet {
String curriculum_id,new_name,updater;
String curriculum,curriculum_name,target_id;
HttpSession session;
int found;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
     session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
     found=0;
dbConn conn =new dbConn();
  curriculum=request.getParameter("curriculum");
            String[] curr=curriculum.split(",");
            curriculum_id=curr[0];
            curriculum_name=curr[1];

new_name=request.getParameter("new_curriculum");
new_name=new_name.toUpperCase();
//CONFIRM Existence

String other_curr="SELECT * FROM curriculum WHERE curriculum_id='"+curriculum_id+"'";
conn.rs=conn.st.executeQuery(other_curr);
if(conn.rs.next()==true){
    target_id=conn.rs.getString("target_id");
}
System.out.println("target  ;  "+target_id);
String count_existence="SELECT COUNT(curriculum_id) FROM curriculum WHERE curriculum_id!='"+curriculum_id+"' && curriculum_name='"+new_name+"' && target_id='"+target_id+"'";
System.out.println(count_existence);
conn.rs=conn.st.executeQuery(count_existence);
if(conn.rs.next()==true){
  found=conn.rs.getInt(1);  
}
if(found==0){
updater="update curriculum set curriculum_name='"+new_name+"' where curriculum_id='"+curriculum_id+"'";
conn.st.executeUpdate(updater);
session.setAttribute("edit_curriculum","<font color=\"green\">"+new_name+" Curriculum Edited Successfully</font>");
}
if(found>0){
 session.setAttribute("edit_curriculum","<font color=\"red\">"+new_name+" Curriculum was not saved. A curriculum with the similar name exist under the same target population.</font>");   
}
System.out.println("found  ;  "+found);
response.sendRedirect("edit_curriculum.jsp");
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
            Logger.getLogger(edit_curriculum.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_curriculum.class.getName()).log(Level.SEVERE, null, ex);
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
