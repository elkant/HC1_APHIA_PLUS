/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SCRIPTS;

import hc.dbConn;
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

/**
 *
 * @author Manuel
 */
public class RemoveDuplicates extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    //get the dupliactes that could be in the same form.
    
    
    String target="16";
    String getdupliactes="Select member_details.member_id, first_name,mname,sur_name, age,sex,form_number from member_details"
            + " join (groups "
            + " ) on member_details.group_id=groups.group_id "
            + " join (register_attendance  "
            + " join forms on register_attendance.form_id=forms.form_id) on member_details.member_id=register_attendance.member_id"
           
            + " where target_pop_id='"+target+"' and form_number!=''  and member_details.sessions_attended>=1 group by member_details.member_id order by first_name";
    
    System.out.println(getdupliactes);
    
    dbConn conn= new dbConn();
    
    conn.rs=conn.st.executeQuery(getdupliactes);
    int count=1;
    ArrayList nondeletes= new ArrayList();
    while(conn.rs.next()){

        
        String fname=conn.rs.getString("member_details.first_name");
        String mname=conn.rs.getString("member_details.mname");
        String sname=conn.rs.getString("member_details.sur_name");
        String age=conn.rs.getString("member_details.age");
        String form=conn.rs.getString("form_number").trim();
        String memberid=conn.rs.getString("member_details.member_id");
    System.out.println(" ~~"+fname+" "+mname+" "+sname+" "+age);
           
      String getdupliactes2="Select member_details.member_id, first_name,mname,sur_name, age,sex,form_number from member_details "
            + " join ( groups "
            + ") on member_details.group_id=groups.group_id "
            + " join (register_attendance  "
            + " join forms on register_attendance.form_id=forms.form_id) on member_details.member_id=register_attendance.member_id"
           
            + " where target_pop_id='"+target+"' and form_number!='' and "
              + " member_details.member_id !='"+conn.rs.getString("member_details.member_id")+"' and member_details.first_name='"+fname+"'"
              + " and member_details.mname='"+mname+"'  and member_details.sur_name='"+sname+"' and form_number LIKE  '%"+form+"%' and age='"+age+"'"
              + " group by member_details.member_id ";
    
    
      conn.rs1=conn.st1.executeQuery(getdupliactes2);
      
      while(conn.rs1.next()){
          
          nondeletes.add(memberid);
          
 
      
      System.out.println(count+"  form duplicate "+conn.rs1.getString(2)+" "+conn.rs1.getString(3)+" "+conn.rs1.getString(4));
      //now you can delete
      if(!nondeletes.contains(conn.rs1.getString("member_details.member_id"))){
      
          System.out.println("delete  "+ conn.rs1.getString("member_details.member_id")+" a duplicate of "+memberid);
           count++;
           
           
    String deletefrommembers="delete from member_details where member_id='"+conn.rs1.getString("member_details.member_id")+"'";
    String deletefromregattendance="delete from register_attendance where member_id='"+conn.rs1.getString("member_details.member_id")+"'";
    
    conn.st2.executeUpdate(deletefrommembers);
   conn.st2.executeUpdate(deletefromregattendance);
      
      }
      else {
          
         System.out.println("DONT delete  "+ conn.rs1.getString("member_details.member_id"));
      
      }
      
      
                             }//end of inner while
      
               }//end of outer while
    
    
    PrintWriter out = response.getWriter();
    try {
       
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(RemoveDuplicates.class.getName()).log(Level.SEVERE, null, ex);
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
