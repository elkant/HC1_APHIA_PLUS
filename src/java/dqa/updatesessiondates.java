/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dqa;

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

/**
 *
 * @author Manuel
 */
public class updatesessiondates extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       
        String id = request.getParameter("id").trim();// values passed from the ajax
        String value = request.getParameter("value").toUpperCase();// values passed from the ajax

        String columnName = request.getParameter("columnName");// values passed from the ajax
        String columnId = request.getParameter("columnId");// values passed from the ajax

        String columnPosition = request.getParameter("columnPosition");// values passed from the ajax

        String rowId = request.getParameter("rowId"); // values passed from the ajax 


        System.out.println("value" + value);
        System.out.println("columnName" + columnName);
        System.out.println(columnId);
        int CID = (Integer.parseInt(columnId));
        System.out.println("col " + columnPosition);
        System.out.println(rowId);
        response.getWriter().print(value);


//  String unique=(String)session.getAttribute("countyid");
        dbConn conn = new dbConn();
        String query1 = "";
        String query2 = "";

//                          <th>First name</th>
//                           <th>Middle Name</th>
//                           <th>Last Name</th>
//                           <th>Sex</th>                          
//                           <th>Age</th>
//                            <th>Group Name</th>
//                            <th>Year</th>
//                            <th>Quarter</th>
//                            <th>Month</th>
        
        
          Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
        
        
        
        // update the fname
        if (columnName.equals("Start Date")) {
            try {
               
  query1 = "UPDATE new_topic SET timestamp='"+mdate+"', start_date='" + value.trim().replace("'","") + "' WHERE new_topic_id like '" + id + "'";
               
System.out.println(query1);

                conn.st.executeUpdate(query1);
                //           out.println("Saved, Refresh page");
            } catch (SQLException ex) {
                Logger.getLogger(updatesessiondates.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

   
        
              if (columnName.equals("End Date")) {
            try {
               
  query1 = "UPDATE new_topic SET timestamp='"+mdate+"', start_date='" + value.trim().replace("'","") + "' WHERE new_topic_id like '" + id + "'";
               
System.out.println(query1);

                conn.st.executeUpdate(query1);
                //           out.println("Saved, Refresh page");
            } catch (SQLException ex) {
                Logger.getLogger(updatesessiondates.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
