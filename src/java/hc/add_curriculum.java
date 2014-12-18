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
public class add_curriculum extends HttpServlet {
String curriculum1,curriculum2,curriculum3,curriculum4;
String session1,session2,session3,session4;
String query0,query1,query2,query3,query4,nextpage="";
HttpSession session;
String curriculum,sessions,found_curriculums="",inserted_curriculums="";
int found=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
      session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
   dbConn conn=new dbConn();
  found_curriculums=inserted_curriculums=""; 
   for (int k=1;k<=4;k++){
       curriculum=sessions="";
     curriculum=request.getParameter("curriculum"+k);  
     sessions=request.getParameter("sessions"+k);
     if(!"".equals(curriculum) || sessions!=""){
         curriculum=curriculum.toUpperCase();
     System.out.println("the curriculum is  : "+curriculum+" and no of lessons : "+sessions);
   query0="select * from curriculum WHERE curriculum_name='"+curriculum+"' && no_of_lessons='"+sessions+"'";
 conn.rs=conn.st.executeQuery(query0);
 while(conn.rs.next()){
 found++; 
 found_curriculums+=curriculum+",";
 }
 if(found==0){
 query1="insert into curriculum set curriculum_name='"+curriculum+"',no_of_lessons='"+sessions+"',target_id='"+session.getAttribute("target_pop_id") +"'";    
  conn.st.executeUpdate(query1);  
  inserted_curriculums+=curriculum+",";
 } 
 found=0;
   }
   }
   if(found_curriculums.length()>0){
       session.setAttribute("alread_exist", "<font color=\"red\">"+found_curriculums+" Curriculums Already Exist</font>");
   }
   
   if(inserted_curriculums.length()>0){
       session.setAttribute("added_curriculum", "<font color=\"green\">"+inserted_curriculums+" Curriculums were added successfully.</font>");
   }
   
response.sendRedirect("add_curriculum.jsp");
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
            Logger.getLogger(add_curriculum.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_curriculum.class.getName()).log(Level.SEVERE, null, ex);
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
