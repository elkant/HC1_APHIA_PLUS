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
import java.util.Calendar;
import java.util.Date;
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
public class transfer_facilitator extends HttpServlet {
String id,partner_id,group_ids,inserter,updater,selector,fname,lname;
String  groups[];
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String groups_all="";
          session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
     dbConn conn=new dbConn();
lname=request.getParameter("lname");
fname=request.getParameter("fname");
id=request.getParameter("facilitator_id");
partner_id=request.getParameter("partner");
groups=request.getParameterValues("groups");
for(int i=0; i<groups.length;i++){
groups_all=groups_all+groups[i]+",";
}
selector="SELECT * from facilitator_details WHERE facilitator_id='"+id+"'";
conn.rs=conn.st.executeQuery(selector);
while(conn.rs.next()){
inserter="INSERT INTO transfer_facilitator SET tf_id='"+uniqueid()+"', facilitator_id='"+id+"',partner_id='"+conn.rs.getString("partner_id") +"',groups_ids='"+conn.rs.getString("groups_id") +"'";
conn.st2.executeUpdate(inserter);
}


 Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
               // timestamp='"+mdate+"',


updater="UPDATE facilitator_details set timestamp='"+mdate+"', groups_id='"+groups_all+"', partner_id='"+partner_id+"' WHERE facilitator_id='"+id+"'";
conn.st.executeUpdate(updater);
session.setAttribute("edit_facilitator","<font color=\"Blue\">"+ fname+" "+lname+"</font> Details Transferred Successfully.");
response.sendRedirect("edit_facilitatorbn");
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
            Logger.getLogger(transfer_facilitator.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(transfer_facilitator.class.getName()).log(Level.SEVERE, null, ex);
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


//====================random id functions================================ 

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
