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

/**
 *
 * @author Manuel
 */
public class newupdatemember extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
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
        if (columnName.equals("First name")) {
            try {
               
  query1 = "UPDATE member_details SET timestamp='"+mdate+"', first_name='" + value.trim().replace("'","") + "' WHERE member_id like '" + id + "'";
               
System.out.println(query1);

                conn.st.executeUpdate(query1);
                //           out.println("Saved, Refresh page");
            } catch (SQLException ex) {
                Logger.getLogger(newupdatemember.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

        //update the manager sms__
        
         if (columnName.equals("Middle Name")) {
            try {
             value=value.trim().replace("'","");   
             value=value.replace("'","");   
  query1 = "UPDATE member_details SET timestamp='"+mdate+"', mname='" + value+ "' WHERE member_id='" + id + "'";
               
System.out.println(query1);


                conn.st.executeUpdate(query1);
                //           out.println("Saved, Refresh page");
            } catch (SQLException ex) {
                Logger.getLogger(newupdatemember.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
 //update the min quantity sms__
        
         if (columnName.equals("Last Name")) {
            try {
                       value=value.trim().replace("'","");   
             value=value.replace("'","");   
  query1 = "UPDATE member_details SET timestamp='"+mdate+"', sur_name='" + value+ "' WHERE member_id='" + id + "'";
               
System.out.println(query1);

                conn.st.executeUpdate(query1);
                //           out.println("Saved, Refresh page");
            } catch (SQLException ex) {
                Logger.getLogger(newupdatemember.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }       
        
        //update the min quantity sms__
        
         if (columnName.equals("Sex")) {
            try {
                  value=value.trim().replace("'","");   
             value=value.replace("'","");   
  query1 = "UPDATE member_details SET timestamp='"+mdate+"', sex='" + value+ "' WHERE member_id='" + id + "'";
             System.out.println(query1);  
                conn.st.executeUpdate(query1);
                //           out.println("Saved, Refresh page");
            } catch (SQLException ex) {
                Logger.getLogger(newupdatemember.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
         
          //update the distributor phone number__
        
         if (columnName.equals("Age")) {
            try {
                  value=value.trim().replace("'","");   
             value=value.replace("'","");   
           query1 = "UPDATE member_details SET timestamp='"+mdate+"', age='" + value+ "' WHERE member_id='" + id + "'";
               
System.out.println(query1);

                conn.st.executeUpdate(query1);
                //           out.println("Saved, Refresh page");
            } catch (SQLException ex) {
                Logger.getLogger(newupdatemember.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
         
         
          if (columnName.equals("Group Name")) {
            try {
                 value=value.trim().replace("'","");   
             value=value.replace("'","");   
  query1 = "UPDATE member_details SET timestamp='"+mdate+"', group_id='" + value+ "' WHERE member_id='" + id + "'";
               
System.out.println(query1);

                conn.st.executeUpdate(query1);
                //           out.println("Saved, Refresh page");
            } catch (SQLException ex) {
                Logger.getLogger(newupdatemember.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
      
           if (columnName.equals("Year")) {
            try {
                 value=value.trim().replace("'","");   
             value=value.replace("'","");   
  query1 = "UPDATE member_details SET timestamp='"+mdate+"', year='" + value+ "' WHERE member_id='" + id + "'";
               
System.out.println(query1);

                conn.st.executeUpdate(query1);
                //           out.println("Saved, Refresh page");
            } catch (SQLException ex) {
                Logger.getLogger(newupdatemember.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
          
          if (columnName.equals("Month")) {
            try {
                 value=value.trim().replace("'","");   
             value=value.replace("'","");   
  query1 = "UPDATE member_details SET timestamp='"+mdate+"', month='" + value+ "' WHERE member_id='" + id + "'";
 //set the quaters              
String quarters="";
System.out.println(query1);
if(value.equals("10")||value.equals("11")||value.equals("12")){ quarters="1";}
else if(value.equals("1")||value.equals("2")||value.equals("3")){ quarters="2";}
else if(value.equals("4")||value.equals("5")||value.equals("6")){ quarters="3";}
else if(value.equals("7")||value.equals("8")||value.equals("9")){ quarters="4";}
else{quarters="0";}

                conn.st.executeUpdate(query1);
          //update the quarters
                  query2 = "UPDATE member_details SET timestamp='"+mdate+"', period='" + quarters+ "' WHERE member_id='" + id + "'";

               conn.st.executeUpdate(query2);   
                
                //           out.println("Saved, Refresh page");
            } catch (SQLException ex) {
                Logger.getLogger(newupdatemember.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }  
        
          
          
//        PrintWriter out = response.getWriter();
//        try {
//           
//        } finally {            
//            out.close();
//        }
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
