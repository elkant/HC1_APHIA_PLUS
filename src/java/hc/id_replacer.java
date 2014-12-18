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

/**
 *
 * @author Geofrey Nyabuto
 */
public class id_replacer extends HttpServlet {
String id,name;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

            dbConn conn=new dbConn();
            String county_id_selector="select * from districts";
            conn.rs=conn.st.executeQuery(county_id_selector);
while(conn.rs.next()){
    if(conn.rs.getString("countyID").equals("1")){
     System.out.println("Nakuru"); 
     String updater="UPDATE districts SET countyID='1' WHERE countyID='1'";
     conn.st1.executeUpdate(updater);
    }
    if(conn.rs.getString("countyID").equals("2")){
     System.out.println("Narok");
          String updater="UPDATE districts SET countyID='3' WHERE countyID='2'";
     conn.st1.executeUpdate(updater);
    }
        if(conn.rs.getString("countyID").equals("3")){
     System.out.println("Baringo"); 
          String updater="UPDATE districts SET countyID='4' WHERE countyID='3'";
     conn.st1.executeUpdate(updater);
    }
            if(conn.rs.getString("countyID").equals("4")){
     System.out.println("Kajiado"); 
          String updater="UPDATE districts SET countyID='5' WHERE countyID='4'";
     conn.st1.executeUpdate(updater);
    }
                if(conn.rs.getString("countyID").equals("5")){
     System.out.println("Laikipia"); 
          String updater="UPDATE districts SET countyID='2' WHERE countyID='5'";
     conn.st1.executeUpdate(updater);
    }
  
}
System.out.println("Done with dishing out the system.");
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
            Logger.getLogger(id_replacer.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(id_replacer.class.getName()).log(Level.SEVERE, null, ex);
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
