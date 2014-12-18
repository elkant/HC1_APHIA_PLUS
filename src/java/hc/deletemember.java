/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
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
 * @author Manuel
 */
public class deletemember extends HttpServlet {

   
   HttpSession session=null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
     session=request.getSession();   
        
        
        try {
    response.setContentType("text/html;charset=UTF-8");
    
    String memberid=request.getParameter("id"); 
    dbConn conn= new dbConn();
    
    
    
    
    
    
    
    
    
    
    

String  creatememberstable="CREATE TABLE IF NOT EXISTS `deleted_register_attendance` ("
  +" `register_id` varchar(200) NOT NULL,"
  +" `member_id` varchar(200) NOT NULL,"
  +" `session_id` varchar(1000) NOT NULL,"
  +" `facilitator_id` varchar(200) NOT NULL,"
  +" `image_path` varchar(45) NOT NULL,"
  +" `end_date` varchar(45) NOT NULL,"
  +" `availability` int(45) NOT NULL,"
  +" `marked_date` varchar(300) NOT NULL,"
  +" `form_id` varchar(1000) NOT NULL,"
  +" `submission_date` text NOT NULL,"
  +" `reviewer_name` text NOT NULL,"
  +" `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
  +" `order_id` int(11) NOT NULL AUTO_INCREMENT,"
  +" PRIMARY KEY (`register_id`),"
  +" UNIQUE KEY `order_id` (`order_id`),"
  +" KEY `member_id` (`member_id`),"
  +" KEY `facilitator_id` (`facilitator_id`),"
  +" KEY `availability` (`availability`),"
  +" KEY `form_id` (`form_id`(767)),"
  +" KEY `marking_date` (`marked_date`) "
+" ) ENGINE=InnoDB AUTO_INCREMENT=106188 DEFAULT CHARSET=latin1";
/*!40101 SET character_set_client = @saved_cs_client */;




String createdeletedtable="CREATE TABLE IF NOT EXISTS `deleted_member_details` ("
 +" `member_id` varchar(200) NOT NULL,"
 +" `first_name` varchar(50) NOT NULL,"
 +" `mname` varchar(45) NOT NULL,"
 +" `sur_name` varchar(50) NOT NULL,"
 +" `age` int(50) NOT NULL,"
 +" `sex` varchar(45) NOT NULL,"
 +" `group_id` varchar(200) NOT NULL,"
 +" `sessions_attended` int(11) DEFAULT '0',"
 +" `year` int(10) NOT NULL DEFAULT '0',"
 +" `period` int(10) NOT NULL DEFAULT '0',"
 +" `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"
 +" `month` varchar(100) NOT NULL,"
 +" PRIMARY KEY (`member_id`),"
 +" KEY `group_id` (`group_id`),"
 +" KEY `fname` (`first_name`,`mname`,`sur_name`,`age`),"
 +" KEY `month` (`month`)"
+") ENGINE=InnoDB DEFAULT CHARSET=latin1";
    
    
    
   conn.st.executeUpdate(creatememberstable); 
   conn.st.executeUpdate(createdeletedtable); 
    
    
    String deletefrommembers="delete from member_details where member_id='"+memberid+"'";
    String deletefromregattendance="delete from register_attendance where member_id='"+memberid+"'";
    System.out.println(deletefrommembers);
    
    String auditact="deleted member_id='"+memberid+"'";
    
    
    //insert the deleted records into the deleted tables
    
 String getmember=" select * from member_details where member_id LIKE '"+memberid+"'";   
 conn.rs=conn.st.executeQuery(getmember);
 while(conn.rs.next()){
 //now insert those records on the deleted table
     String quer = "INSERT INTO deleted_member_details (member_id,first_name,mname,sur_name,age,sex,group_id,sessions_attended,year,period,month)"
        + "values('" + conn.rs.getString(1)+ "','" + conn.rs.getString(2).replace("'","") + "','" + conn.rs.getString(3).replace("'","") + "','" + conn.rs.getString(4).replace("'","") + "','" + conn.rs.getString(5) + "','" + conn.rs.getString(6) + "','" + conn.rs.getString(7) + "','" + conn.rs.getString(8) + "','" + conn.rs.getString(9) + "','" + conn.rs.getString(10) + "','" + conn.rs.getString(11) + "')";

     System.out.print(quer);
     conn.st1.executeUpdate(quer); 


 }
 
 
 //insert the deleted records from register_attendance into deleted register attendance.
 String getregisterattendance="select * from register_attendance where member_id like '"+memberid+"'";
 
 conn.rs=conn.st.executeQuery(getregisterattendance);
 
 
 while (conn.rs.next()){
 
     
     
     
String save = "insert into deleted_register_attendance (register_id,member_id,session_id,facilitator_id,image_path,end_date,availability,marked_date,form_id, submission_date,reviewer_name) "
   + "values('" +conn.rs.getString(1) + "','" + conn.rs.getString(2)+ "','" + conn.rs.getString(3) + "','" +conn.rs.getString(4)+ "','" +conn.rs.getString(5) + "','" + conn.rs.getString(6) + "','" + conn.rs.getString(7) + "','" + conn.rs.getString(8) + "','" + conn.rs.getString(9) + "','" + conn.rs.getString(10) + "','" + conn.rs.getString(11) + "')";
System.out.println(save);
  conn.st1.executeUpdate(save);   
     
     
     
     
 
 }
 
    
    String com = InetAddress.getLocalHost().getHostName() + " " + InetAddress.getLocalHost().getHostAddress();
    Date dat= new Date();
    String current_time=dat.toString().replace(" ", "_");
    String inserter = "insert into audit set host_comp='" + com + "', action='"+auditact+"',time='" + current_time + "',actor_id='" + session.getAttribute("userid") + "'";
    
    //now officially delete the member details
    System.out.println("QUERY 1 RESULT "+conn.st.executeUpdate(deletefrommembers));
     System.out.println("QUERY 2 RESULT "+conn.st.executeUpdate(deletefromregattendance));
    
    
    
    
    PrintWriter out = response.getWriter();
    
    
    
    
    try {
      
    } finally {            
        out.close();
    }
    
    
    
}       catch (SQLException ex) {
            Logger.getLogger(deletemember.class.getName()).log(Level.SEVERE, null, ex);
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
