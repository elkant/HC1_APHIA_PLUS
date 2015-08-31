/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SCRIPTS;

import hc.IdGenerator;
import hc.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
public class setSystemDate extends HttpServlet {
HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
     
        
        String currentDate,maxDate;
int maxID,months,date;
maxID=1;
months=0;
String status = "",newLockDate,newMinDate;
Date lock_date = null,minDate = null;
int addMonths,lockStatus;
        
        PrintWriter out = response.getWriter();
        try {
        session=request.getSession();
        dbConn conn = new dbConn();
        IdGenerator IG = new IdGenerator();
        addMonths=lockStatus=0;
        String getMaxId="SELECT MAX(id) FROM lock_data"; 
        conn.rs=conn.st.executeQuery(getMaxId);
        if(conn.rs.next()==true){
        maxID=conn.rs.getInt(1);
        }
        String getMaxDate="SELECT period_diff(date_format(now(), '%Y%m'), date_format(STR_TO_DATE(lock_date,'%m/%d/%Y'), '%Y%m')) as Months,STR_TO_DATE(lock_date,'%m/%d/%Y'),STR_TO_DATE(min_date,'%m/%d/%Y') FROM lock_data WHERE id = '"+maxID+"'";
        conn.rs=conn.st.executeQuery(getMaxDate);
        if(conn.rs.next()==true){
        months=conn.rs.getInt(1);
        lock_date=conn.rs.getDate(2);
        minDate=conn.rs.getDate(3);
        System.out.println(" date is :  "+lock_date+" months : "+months);
       }
      
       if(months>=1){
//         Check todays date if it is 5th or more
      date=IG.date;
      
      if(months==1){
      if(date>=10){
//      lock data called    
      lockStatus++;    
          
      }
      else{
//          do nothing
      }
      }
      else{
      if(date>=10){
//      lock data called    
       lockStatus++;   
      }
      else{
//          do nothing
    months--;
    lockStatus++;
     }     
          
      }
      
      if(lockStatus>=1){
//      addMonths=(months/3)*3;
      addMonths=months;
    
      
//      CODE TO LOCK DATA HERE............................................................................

        Calendar calLockDate = Calendar.getInstance();
        Calendar calMinDate = Calendar.getInstance();
        calLockDate.setTime(lock_date);
        calLockDate.add(Calendar.MONTH, addMonths); //minus number would decrement the days
        
        calMinDate.setTime(minDate);
        calMinDate.add(Calendar.MONTH, addMonths); //minus number would decrement the days
        
        
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
      newLockDate = formatter.format(calLockDate.getTime());
      newMinDate = formatter.format(calMinDate.getTime());
     
      System.out.println("Months are : "+months+" new lock date : "+newLockDate+" new min date is : "+newMinDate);
      
//      UPDATE NEW LOCK DATES
      
      String updator="INSERT INTO lock_data (lock_date,min_date) VALUES('"+newLockDate+"', '"+newMinDate+"')";
      conn.st.executeUpdate(updator);
     status="Lock updated";
      
       }
      else{
           System.out.println("Few days to closing.....");
       }
       }
       
      else if(months<0){
         status="Wrong current date";  
       }
      else if (months>=0 && months<3){
           System.out.println("We cannot lock your data................."+months);
           status="data is ok";
       }
        out.println(status);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
        Logger.getLogger(setSystemDate.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
        Logger.getLogger(setSystemDate.class.getName()).log(Level.SEVERE, null, ex);
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
