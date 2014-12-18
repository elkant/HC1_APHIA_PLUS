/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

import hc.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manuel
 */
public class backuphistory extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           
          dbConn conn= new dbConn();
          
          
         
         //now receive the parameters passed
         String category="";
     
         
       
     
         
   String table=" <thead>"
		            +" <tr>"
                           +" <th>Backup Date</th>"
		      
                           +" <th>Backup Sent via internet</th>"
                          
                            +" </tr>"		                
		            +" </thead>"
		            +" <tbody>";
                                
                     

                                
		              
         
         
         
          String getdupsnameandage=" select * from timestamp";
          
     System.out.println(getdupsnameandage);       
          
          conn.rs=conn.st.executeQuery(getdupsnameandage);
     int count=0;     
    while(conn.rs.next()){
    count++;
    table+="<tr id='"+conn.rs.getString("timestampid")+"'>"
            + "<td style='text-align:center;'>"+conn.rs.getString("timestamp")+"</td>"
            + "<td style='align:center;'>"+conn.rs.getString("datasend")+"</td>"           
            + "</tr>";
    
   // System.out.println(count+"~~"+table);
    
    
    
    
    
    
    }    
    
      table+=" </tbody>"; 
      
      System.out.println(table);
            out.println(table);   
            
        } catch (SQLException ex) {
            Logger.getLogger(backuphistory.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        finally { 
            
            out.close();
        }
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
}
