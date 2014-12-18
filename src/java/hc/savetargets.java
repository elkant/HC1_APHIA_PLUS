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
 * @author Manuel
 */
public class savetargets extends HttpServlet {

   HttpSession session;
String county,year,partner,found,target,sex,timestamp,targetpop;
int total,addquantity;
double unitPrice;
String added="";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        session=request.getSession();
       dbConn conn = new dbConn();
//        userid=session.getAttribute("userid").toString();
       
        added="";
        year=county=partner=target=targetpop=sex="";
        
       total=Integer.parseInt(request.getParameter("all_rows")) ;
       for(int i=1;i<=total;i++){
           year=county=partner=target=sex="";
          if(!request.getParameter("year"+i).equals("")){
          
        
          year=request.getParameter("year"+i);
           county=request.getParameter("county"+i);
          partner=request.getParameter("partner"+i);
          targetpop=request.getParameter("targetpop"+i);
          target=request.getParameter("target"+i);
          sex=request.getParameter("sex"+i);
          String uniqid=uniqueid().trim();
           addquantity=0;
           
           
           if(!year.equals("")){
                    try {
                        String checkexistance="SELECT * FROM targets WHERE year='"+year+"' and countyid='"+county+"' and partnerid='"+partner+"' and targetpop='"+targetpop+"' and sex='"+sex+"'";
                
                        conn.rs=conn.st.executeQuery(checkexistance);
                 
                 //===if values for a target exists, update the values . otherwise  do insert 
                 
                 Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
                 
                 if(conn.rs.next()){
                  added+=i+" Target  "+target+" updated successfully .<br>";
                  
                  String upd="update targets set target='"+target+"', timestamp='"+mdate+"' where targetid='"+conn.rs.getString(1)+"'";
                  conn.st1.executeUpdate(upd);
                  
                 }else {
                    String inserter="INSERT INTO targets (targetid,countyid,partnerid,year,sex,target,targetpop) "
                            + "VALUES (?,?,?,?,?,?,?)";
                    conn.pst2=conn.conne.prepareStatement(inserter);
                    conn.pst2.setString(1, uniqid);
                    conn.pst2.setString(2, county);
                    conn.pst2.setString(3, partner);
                    conn.pst2.setString(4, year);
                    conn.pst2.setString(5, sex);
                    conn.pst2.setString(6, target);
                    conn.pst2.setString(7, targetpop);
         //           conn.pst2.setString(7, timestamp);
                    
                    conn.pst2.executeUpdate();
                    
                     added+=" Target  "+target+" saved successfully .<br>";
                 }
         //        
                  
                    } catch (SQLException ex) {
                        Logger.getLogger(savetargets.class.getName()).log(Level.SEVERE, null, ex);
                    }
           }
           
           }  
       }
       session.setAttribute("addedtargets", "<font color=\"black\">The following targets have been added :</font> <br><font color=\"green\">"+added+"</font>");
      response.sendRedirect("addtargets.jsp");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    
    //====================random id functions================================ 
    public String uniqueid() {

        Calendar cal = Calendar.getInstance();

        int yr = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int milsec = cal.get(Calendar.MILLISECOND);


        return yr + "" + month + "" + date + hour + min + sec + milsec + generateRandomNumber(800, 9000);
    }

    public int generateRandomNumber(int start, int end) {
        Random random = new Random();
        long fraction = (long) ((end - start + 1) * random.nextDouble());
        return ((int) (fraction + start));
    }
//==========================================================================
    
    
    
}
