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
public class edit_topic extends HttpServlet {
String old_name,new_name,selector,curriculum,curriculum1,curriculum2;
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
            dbConn conn=new dbConn();
          String  curri=request.getParameter("curriculum");
            String [] curr=curri.split(",");
            curriculum1=curr[0];
            String curri2=request.getParameter("curriculum2");
              String [] curr2=curri2.split(",");
              curriculum2=curr2[0];
            if(!curriculum2.equals("") && curriculum2!=null){
                  System.out.println("new curri");
            curriculum=curriculum2;
            }
            if(curriculum2.equals("") || curriculum2==null){
                  System.out.println("only topic");
            curriculum=curriculum1;
            
            }
            System.out.println(curriculum);
            old_name=request.getParameter("topics");
            new_name=request.getParameter("new_topic");
            new_name=new_name.toUpperCase();
            
            String checker="SELECT COUNT(topic_id) FROM topics WHERE topic_name='"+new_name+"' && curriculum_id='"+curriculum+"'";
            conn.rs=conn.st.executeQuery(checker);
            if(conn.rs.next()==true){
                found=conn.rs.getInt(1);
            }
            if(found>0){
                 session.setAttribute("topic_edit", "<font color=\"red\">"+new_name+" Topic Already Exist. Hence not saved</font>");
            }
            if(found==0){
      selector="update topics set topic_name='"+new_name+"',curriculum_id='"+curriculum+"' WHERE topic_name='"+old_name+"'" ;
      conn.st.executeUpdate(selector);
      session.setAttribute("topic_edit", "<font color=\"green\">"+new_name+" Topic Edited Successfully.</font>");
            }
     response.sendRedirect("edit_topic.jsp"); 
     curriculum="";
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
            Logger.getLogger(edit_topic.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_topic.class.getName()).log(Level.SEVERE, null, ex);
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
