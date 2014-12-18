/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
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
public class add_topic extends HttpServlet {
HttpSession session;
String topic,insert,already_exist;
int found,inserted,exist1;
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
     topic=insert=already_exist="";
     found=inserted=exist1=0;
//receive parameters
session.getAttribute("curriculum_id").toString();
for(int i=1;i<=12;i++){
topic=request.getParameter("dist"+i);
System.out.println("partner  :  "+topic);
if(!topic.equals("")){
    topic=topic.toUpperCase();
String check_existence="SELECT COUNT(topic_id) FROM topics WHERE topic_name='"+topic+"' && curriculum_id='"+session.getAttribute("curriculum_id").toString()+"'";
 conn.rs=conn.st.executeQuery(check_existence) ;
 if(conn.rs.next()==true){
 found=conn.rs.getInt(1);
 }
if(found>0){
exist1++;
already_exist+=topic+",";
}
if(found==0){
 String   query1="insert into topics set topic_id='"+uniqueid().trim()+"', topic_name='"+topic+"', curriculum_id='"+session.getAttribute("curriculum_id").toString()+"' ";
 inserted+=conn.st.executeUpdate(query1);  
 insert+=topic+",";
}

found=0;
}
if(inserted>0){
  session.setAttribute("dist_exist", "<font color=\"Green\">"+insert+" Added Successfully.</font>");   
}
if(exist1>0){
  session.setAttribute("dist_exist2", "<font color=\"red\">"+already_exist+" Not added. Already exist in the system for this curriculum</font>");   
}
}

response.sendRedirect("add_topic2.jsp");
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
            Logger.getLogger(add_topic.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_topic.class.getName()).log(Level.SEVERE, null, ex);
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




 public String uniqueid() {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int milsec=cal.get(Calendar.MILLISECOND);
        
        
        return generateRandomNumber(800, 8000)+year+""+month+""+date+hour+min+sec+milsec;
    }

 
 
   public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
 
//==========================================================================



}
