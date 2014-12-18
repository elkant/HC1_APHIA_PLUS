/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataimport;

import hc.dbConn;
import hc.tempdbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author Manuel
 */
public class Middlemanchecking extends HttpServlet {

    
    HttpSession session=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        session=request.getSession();
        
        try {
            
        //*********************************************************************
        //*********************************************************************
        //this code compares the existing data against new data that is in the database for mainly the member details 
        //get a query of the  memberdetails including the names, age and formnumber.
        
         String checkdbname="SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'temphc'";
           
          dbConn conn= new dbConn();  
          tempdbConn tempconn= new tempdbConn();  
       String duplicates[]={"FIRSTNAME","MIDDLENAME","LASTNAME","AGE","SEX","GROUP","FORM ID","FACILITATOR","SESSIONS ATTENDED"}; 
          
               HSSFWorkbook wb = new HSSFWorkbook();
              HSSFSheet duplicatessheet = wb.createSheet("HC1 Duplicates");
           HSSFRow anccolumnheader =duplicatessheet.createRow(0);
          
           //anccolumnheader.setHeightInPoints(22);
          
           HSSFCell cell=null;
           for(int a=0;a<duplicates.length;a++){
           
           cell=anccolumnheader.createCell(a);
           cell.setCellValue(duplicates[a]);
           
           }
         
           
           
           
           
           
           //now create the cell values 
           
           
           int countrows=0;
           
           
           HSSFRow rw=null;
           
          
          session.setAttribute("duplicateids","noduplicates");
          //if a temp hc db exists, then do a middle man compariso
          conn.rs=conn.st.executeQuery(checkdbname);
          
         ArrayList dups= new ArrayList(); 
             int duplicatescount=0;
          if(conn.rs.next()){
          //now you can do middle man comparison
          
              
                
            String getdupliactes="Select  member_details.first_name,member_details.mname,member_details.sur_name, member_details.age,sex,group_name,form_number,facilitator_details.first_name as FACILFNAME, facilitator_details.sur_name as FACILSNAME, facilitator_details.phone as PHONE, target_pop_id , member_details.member_id from member_details"
            + " join (groups "
            + " ) on member_details.group_id=groups.group_id "
            + " join (register_attendance  "
            + " join forms on register_attendance.form_id=forms.form_id"
                    + " join facilitator_details on register_attendance.facilitator_id=facilitator_details.facilitator_id ) on member_details.member_id=register_attendance.member_id"
           
            + " where  form_number!=''  group by member_details.member_id order by first_name ";
            
            tempconn.rs1=tempconn.st1.executeQuery(getdupliactes);
            while (tempconn.rs1.next()){
            
        String fname= tempconn.rs1.getString("member_details.first_name").trim();
        String mname= tempconn.rs1.getString("member_details.mname").trim();
        String sname= tempconn.rs1.getString("member_details.sur_name").trim();
        String age=   tempconn.rs1.getString("member_details.age").trim();
        String form=  tempconn.rs1.getString("form_number").trim();
        String memberid= tempconn.rs1.getString("member_details.member_id").trim(); 
        String targetid= tempconn.rs1.getString("target_pop_id").trim(); 
        String facilfname= tempconn.rs1.getString("FACILFNAME").trim();      
        String facilsname= tempconn.rs1.getString("FACILSNAME").trim();      
        String facilphone= tempconn.rs1.getString("PHONE").trim();      
                
                // now get the existing values in the database 
             String comparewithexistingvalues
             =" Select  member_details.first_name,member_details.mname,member_details.sur_name, member_details.age,sex,group_name,form_number,facilitator_details.first_name as FACILFNAME, facilitator_details.sur_name as FACILSNAME, facilitator_details.phone as PHONE, target_pop_id , member_details.member_id from member_details"
            + " join (groups "
            + " ) on member_details.group_id=groups.group_id "
            + " join ( register_attendance  "
            + " join forms on register_attendance.form_id=forms.form_id "
                     + "join facilitator_details on register_attendance.facilitator_id=facilitator_details.facilitator_id) on member_details.member_id=register_attendance.member_id"
           
           + " where target_pop_id='"+targetid+"' and form_number!='' and "
              + " member_details.member_id !='"+memberid+"' and member_details.first_name LIKE'"+fname+"'"
              + " and member_details.mname LIKE '"+mname+"'  and member_details.sur_name LIKE  '"+sname+"' and  form_number LIKE '"+form+"' and age='"+age+"'"
              + " group by member_details.member_id ";  
                
              //now create a csv file with the results of the possible duplicates  
              //add the duplicates to an arraylist 
             conn.rs2=conn.st2.executeQuery(comparewithexistingvalues);
         
             while(conn.rs2.next()){
              duplicatescount++;   
                 
             dups.add(conn.rs2.getString("member_details.member_id")); 
           //add  
             rw=duplicatessheet.createRow(countrows);
             
             for(int b=0;b<duplicates.length;b++){
             HSSFCell cell1=rw.createCell(b);
             //cell1.setCellValue(conn.rs);
             
             
             
             }
             
             }
            }
    
            
              
          }
          else {
              String message=" temphc database does not exist . An error occured during data import!!";
              
              session.setAttribute("feedbackmsg", message);
              
          System.out.println(message);
          
          }
          
          
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Middlemanchecking.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
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
