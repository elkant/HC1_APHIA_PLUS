/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SCRIPTS;

import hc.dbConn;
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
 * @author MANUEL
 */
public class checkbackup extends HttpServlet {

    HttpSession session=null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             session=request.getSession();
      String msg=""; 
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        /**  Get the last backed up timestamp   */
      
          
         String getbackup=" select max(timestamp) from timestamp where datasend like 'no'";  
            
         dbConn conn= new dbConn();   
            try {
                String lasttimebackuptime="";
                conn.rs= conn.st.executeQuery(getbackup);
                while(conn.rs.next()){
                lasttimebackuptime=conn.rs.getString(1);
                
                } 
               
                
                // Now get todays date
                
                //convert date to the same format as that of the timestamp table               
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String mdate;

            Date mydate = new Date();
            mdate = formatter.format(mydate);
            
          //now count if there are around 20 unsaved records
            //check also if 5 days have passed since you lastly created a backup
            
            String is20recordsunsaved=" select count(*) as records from member_details where timestamp >= '"+lasttimebackuptime+"'";
            
            System.out.println(is20recordsunsaved);
            
            conn.rs1=conn.st1.executeQuery(is20recordsunsaved);
            
            if(conn.rs1.next()){
            
            //count the number of records reached 
                int recordi=conn.rs1.getInt(1);
                //if recodi is more than 20, 
                
                //if rekodi >= 20 and date diff is more than one week
                
                String sikungapi="select datediff('"+mdate+"','"+lasttimebackuptime+"')";
                
                System.out.println(sikungapi);
                
                conn.rs2=conn.st3.executeQuery(sikungapi);
                
                int days=0;
                
                if(conn.rs2.next()){
                
                //
                    days=conn.rs2.getInt(1);
                  
                    if(days>=5&&recordi>=20){
                    
                    msg="<font color='green'><h3>Note: </font> <font color='orange'>You have not backed your data for <b>"+days+"</b> days.</font><br/> <font color='green'>Kindly Log into the system,<br/> On the systems menu,point <br/> >> Data........................... <br/> >>Send Backup to M&E Officer <br/>>> Send Backup.................. </h3></font>";
                    
                    }
                    
                
                }
                
            
            }
                
            } catch (SQLException ex) {
                Logger.getLogger(checkbackup.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       session.setAttribute("backupsms",msg);   
       
       out.println(msg);
      if(msg.equals("")){
              
       session.setAttribute("backupsms",null);   
      
      }      
            
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
        processRequest(request, response);
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
        processRequest(request, response);
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
