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
public class edit_group extends HttpServlet {
String group_name,updater;
 HttpSession session; 
 String target,district,dist;
 int found=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        updater="";
        
        PrintWriter out = response.getWriter();
        try {
            dbConn conn =new dbConn();
             session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
     found=0;
            group_name=request.getParameter("group_name").trim().replace("'", "");
            dist=request.getParameter("district");
            String targetpopid=request.getParameter("targetpop");
            String partnerid=request.getParameter("partner");
            String get_dets="SELECT * FROM groups WHERE group_id='"+session.getAttribute("group_id") +"'";
            conn.rs=conn.st.executeQuery(get_dets);
            if(conn.rs.next()==true){
               target=conn.rs.getString("target_pop_id") ;
               district=conn.rs.getString("district_id");  
            }
          String checker="SELECT COUNT(group_id) FROM groups WHERE group_name='"+group_name+"' && group_id!='"+session.getAttribute("group_id") +"' "
                  + "&& district_id='"+district+"' && target_pop_id='"+target+"'";
          conn.rs=conn.st.executeQuery(checker);
          if(conn.rs.next()==true){
           found=conn.rs.getInt(1);
          }
            if(found==0){
             
                
                  Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
                
                group_name=group_name.toUpperCase();
            updater="update groups set group_name='"+group_name+"', district_id='"+dist+"', partner_id='"+partnerid+"',target_pop_id='"+targetpopid+"', timestamp='"+mdate+"' where group_id='"+session.getAttribute("group_id") +"'";
            conn.st.executeUpdate(updater);
            session.setAttribute("edit_group","<font color=\"green\">"+group_name+" Group details Edited successfully </font>");
            }
            if(found>0){
                 session.setAttribute("edit_group","<font color=\"red\">"+group_name+" group was not saved. This group already exist.</font>");
            }
            response.sendRedirect("edit_group.jsp");
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
            Logger.getLogger(edit_group.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_group.class.getName()).log(Level.SEVERE, null, ex);
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
