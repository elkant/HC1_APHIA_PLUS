/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataimport;

import hc.dbConn;
import hc.dbConn1;
import hc.tempdbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SIXTYFOURBIT
 */
public class MergeData extends HttpServlet {

    HttpSession sess;
    int facilitators = 0, groups = 0, members = 0, sessions = 0, regattendance = 0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {

            facilitators = 0;
            groups = 0;
            members = 0;
            sessions = 0;
            regattendance = 0;

            sess = request.getSession();

//importdata im= new importdata();
//im.createtables();

            tempdbConn conn = new tempdbConn();
             dbConn conn1= new dbConn();
           // dbConn1 conn1 = new dbConn1();

            
            //============add months to the default db==================
            
           //=========================================================

//      CHECK IF THE COLUMNS EXIST IF NOT CREATE THEM AND LET THEM HAVE A DEFAULT VALUE OF 3.  
            String ch1 = "SHOW COLUMNS FROM member_details LIKE 'month'";
            conn.rs = conn.st.executeQuery(ch1);
            if (conn.rs.next()) {
//do nothing 
            } 
            else {
//    ADD A MONTH COLUMN FOR FORMS,MEMBER_DETAILS,SESSIONS AND NEW_TOPIC TABLES
                
                
                
                
                
                String query1 = "ALTER TABLE `member_details` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                
                conn.rs1 = conn.st1.executeQuery("SHOW COLUMNS FROM member_details LIKE 'month'");
                if(!conn.rs1.next()){
                conn.st.executeUpdate(query1);
                }

                String query2 = "ALTER TABLE `session` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                
                conn.rs1 = conn.st.executeQuery("SHOW COLUMNS FROM session LIKE 'month'");
        
                if(!conn.rs1.next()){
                conn.st.executeUpdate(query2);
                }



                String query3 = "ALTER TABLE `forms` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                conn.rs1 = conn.st1.executeQuery("SHOW COLUMNS FROM forms LIKE 'month'");
        
                if(!conn.rs1.next()){
                conn.st.executeUpdate(query3);
                }



                String query4 = "ALTER TABLE `new_topic` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                    conn.rs1 = conn.st1.executeQuery("SHOW COLUMNS FROM new_topic LIKE 'month'");
        
                if(!conn.rs1.next()){
                conn.st.executeUpdate(query4);
                }

            }
            //======================================================================================  

   
            
            
            
            
            
            
            
            //read data from the temp table
            String mergefacilitators="select * from facilitator_details";

            conn.rs = conn.st.executeQuery(mergefacilitators);

            while (conn.rs.next()) {
                
                //check in the master db whether data exists...
String existancechecker = "select * from facilitator_details where facilitator_id='" + conn.rs.getString(1) + "'  ";

   String insertdata = "insert into facilitator_details(facilitator_id,first_name,sur_name,phone,partner_id,groups_id,middle_name,timestamp) "
                        + "values ('" + conn.rs.getString(1) + "','" + conn.rs.getString(2) + "','" + conn.rs.getString(3) + "','" + conn.rs.getString(4) + "','" + conn.rs.getString(5) + "','" + conn.rs.getString(6) + "','" + conn.rs.getString(7) + "','" + conn.rs.getTimestamp(8) + "')";


                conn1.rs1 = conn1.st1.executeQuery(existancechecker);

                if (conn1.rs1.next()) {

              //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(conn1.rs1.getTimestamp("timestamp")+":::::"+conn.rs.getTimestamp("timestamp"));
                    
                    if(!conn1.rs1.getTimestamp("timestamp").toString().equals(conn.rs.getTimestamp("timestamp").toString())){
                    
                        String upd="update facilitator_details set first_name='"+conn.rs.getString(2)+"',sur_name='"+conn.rs.getString(3)+"',phone='"+conn.rs.getString(4)+"',partner_id='"+conn.rs.getString(5)+"',groups_id='"+conn.rs.getString(6)+"',middle_name='"+conn.rs.getString(7)+"',timestamp='"+conn.rs.getTimestamp(8)+"' where facilitator_id='"+conn.rs.getString(1)+"' ";
                        
                        conn1.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        facilitators++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } 
              //  else if(){}
                else {
                    facilitators++;
                    conn1.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:: " + insertdata);
                }


            }



            //=================================  FORMS============================================


            String mergeforms = "select * from forms";

            conn.rs = conn.st.executeQuery(mergeforms);

            while (conn.rs.next()) {

                String existancechecker = "select * from forms where form_id='" + conn.rs.getString(1) + "'";

                String insertdata = "insert into forms(form_id,form_number,year,period,timestamp,month) "
                        + "values ('" + conn.rs.getString(1) + "','" + conn.rs.getString(2) + "','" + conn.rs.getString(3) + "','" + conn.rs.getString(4) + "','" + conn.rs.getString(5) + "','" + conn.rs.getString(6) + "')";





                conn1.rs1 = conn1.st1.executeQuery(existancechecker);

                if (conn1.rs1.next()) {

                   //check if the timestamps are equal.. otherwise do an update
                    
                    
                    System.out.println(conn1.rs1.getTimestamp("timestamp")+":::::"+conn.rs.getTimestamp("timestamp"));
                    
                    if(!conn1.rs1.getTimestamp("timestamp").toString().equals(conn.rs.getTimestamp("timestamp").toString())){
                    
                        String upd="update forms set form_number='"+conn.rs.getString(2)+"',year='"+conn.rs.getString(3)+"',period='"+conn.rs.getString(4)+"',timestamp='"+conn.rs.getTimestamp(5)+"' where form_id='"+conn.rs.getString(1)+"' ";
                        
                        conn1.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                       
                        
                    }
                    else{
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                } else {

                    conn1.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED  " + insertdata);

                }


            }
            //=======================================GROUPS================================







            String mergegroups = "select * from groups";

            conn.rs = conn.st.executeQuery(mergegroups);

            while (conn.rs.next()) {

                String existancechecker = "select * from groups where group_id='" + conn.rs.getString(1) + "'";

                String insertdata = "insert into groups(group_id,group_name,partner_id,target_pop_id,district_id,timestamp) "
                        + "values ('" + conn.rs.getString(1) + "','" + conn.rs.getString(2) + "','" + conn.rs.getString(3) + "','" + conn.rs.getString(4) + "','" + conn.rs.getString(5) + "','" + conn.rs.getString(6) + "')";





                conn1.rs1 = conn1.st1.executeQuery(existancechecker);

                if (conn1.rs1.next()) {

                  
                    
                    if(!conn1.rs1.getTimestamp("timestamp").toString().equals(conn.rs.getTimestamp("timestamp").toString())){
                    
                        String upd="update groups set group_name='"+conn.rs.getString(2)+"',partner_id='"+conn.rs.getString(3)+"',target_pop_id='"+conn.rs.getString(4)+"',district_id='"+conn.rs.getString(5)+"',timestamp='"+conn.rs.getTimestamp(6)+"' where group_id='"+conn.rs.getString(1)+"' ";
                        
                        conn1.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        groups++;
                        
                    }
                    else 
                    {
                    
                    
                    System.out.println("Data Already Exists " + insertdata);
                    
                    
                    }

                }
                else {

                    groups++;
                    conn1.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED  " + insertdata);

                    }


            }
            //=======================================MEMBERS================================


            //read data from the existing hc table
            String mergemembers = "select * from member_details";

            conn.rs = conn.st.executeQuery(mergemembers);

            while (conn.rs.next()) {

                String existancechecker = "select * from member_details where member_id='" + conn.rs.getString(1) + "'";

                String insertdata = "insert into member_details(member_id,first_name,mname,sur_name,age,sex,group_id,sessions_attended,year,period,timestamp,month) "
                        + "values ('" + conn.rs.getString(1) + "','" + conn.rs.getString(2) + "','" + conn.rs.getString(3) + "','" + conn.rs.getString(4) + "','" + conn.rs.getString(5) + "','" + conn.rs.getString(6) + "','" + conn.rs.getString(7) + "','" + conn.rs.getString(8) + "','" + conn.rs.getString(9) + "','" + conn.rs.getString(10) + "','" + conn.rs.getString(11) + "','"+ conn.rs.getString(12) +"')";






                conn1.rs1 = conn1.st1.executeQuery(existancechecker);

                if (conn1.rs1.next()) {

                    //check if the timestamps are equal.. otherwise do an update
                       
                    if(!conn1.rs1.getTimestamp("timestamp").toString().equals(conn.rs.getTimestamp("timestamp").toString())){
                    
                        String upd="update member_details set first_name='"+conn.rs.getString(2)+"',mname='"+conn.rs.getString(3)+"',sur_name='"+conn.rs.getString(4)+"',age='"+conn.rs.getString(5)+"',sex='"+conn.rs.getString(6)+"',group_id='"+conn.rs.getString(7)+"',sessions_attended='"+conn.rs.getString(8)+"',year='"+conn.rs.getString(9)+"',period='"+conn.rs.getString(10)+"',timestamp='"+conn.rs.getTimestamp(11)+"',month='"+conn.rs.getString(12)+"' where member_id='"+conn.rs.getString(1)+"' ";
                        
                        conn1.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        members++;
                        
                    }
                    else{
                    
                    
                    System.out.println("Member details Data Already Exists " + insertdata);
                    
                    
                    }

                } else {
                    members++;
                    conn1.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED:::" + insertdata);
                }


            }



            //====================================NEW TOPIC TABLE======================================






            //read data from the existing hc table
            String mergenewtopics = "select * from new_topic";

            conn.rs = conn.st.executeQuery(mergenewtopics);

            while (conn.rs.next()) {

                String existancechecker = "select * from new_topic where new_topic_id='" + conn.rs.getString(1) + "'";

                String insertdata = "insert into new_topic (new_topic_id,topic_id,expected_sessions,start_date,end_date,marking_status,facilitator_id,year,marking_date,year2,period,timestamp,month) "
                        + "values ('" + conn.rs.getString(1) + "','" + conn.rs.getString(2) + "','" + conn.rs.getString(3) + "','" + conn.rs.getString(4) + "','" + conn.rs.getString(5) + "','" + conn.rs.getString(6) + "','" + conn.rs.getString(7) + "','" + conn.rs.getString(8) + "','" + conn.rs.getString(9) + "','" + conn.rs.getString(10) + "','" + conn.rs.getString(11) + "','" + conn.rs.getString(12) + "','" + conn.rs.getString(13) + "')";






                conn1.rs1 = conn1.st1.executeQuery(existancechecker);

                if (conn1.rs1.next()) {
    
                    if(!conn1.rs1.getTimestamp("timestamp").toString().equals(conn.rs.getTimestamp("timestamp").toString())){
                    
                        String upd="update new_topic set topic_id='"+conn.rs.getString(2)+"',expected_sessions='"+conn.rs.getString(3)+"',start_date='"+conn.rs.getString(4)+"',end_date='"+conn.rs.getString(5)+"',marking_status='"+conn.rs.getString(6)+"',facilitator_id='"+conn.rs.getString(7)+"',year='"+conn.rs.getString(8)+"',marking_date='"+conn.rs.getString(9)+"',year2='"+conn.rs.getString(10)+"',period='"+conn.rs.getString(11)+"', timestamp='"+conn.rs.getTimestamp(12)+"', month='"+conn.rs.getTimestamp(13)+"' where new_topic_id='"+conn.rs.getString(1)+"' ";
                        
                        conn1.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        
                    }
                    else{
                    
                    
                    System.out.println("New topics Data Already Exists " + insertdata);
                    
                    
                    }

                } else {

                    conn1.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED::: " + insertdata);
                }


            }


            //===================================Register Attendance======================================================






            //read data from the existing hc table
            String mergeregatten = "select * from register_attendance";

            conn.rs = conn.st.executeQuery(mergeregatten);

            while (conn.rs.next()) {

                String existancechecker = "select * from register_attendance where register_id='" + conn.rs.getString(1) + "'";

                String insertdata = "insert into register_attendance(register_id,member_id,session_id,facilitator_id,image_path,end_date,availability,marked_date,form_id,submission_date,reviewer_name,timestamp) "
                        + "values ('" + conn.rs.getString(1) + "','" + conn.rs.getString(2) + "','" + conn.rs.getString(3) + "','" + conn.rs.getString(4) + "','" + conn.rs.getString(5) + "','" + conn.rs.getString(6) + "','" + conn.rs.getString(7) + "','" + conn.rs.getString(8) + "','" + conn.rs.getString(9) + "','" + conn.rs.getString(10) + "','" + conn.rs.getString(11) + "','" + conn.rs.getString(12) + "')";







                conn1.rs1 = conn1.st1.executeQuery(existancechecker);

                if (conn1.rs1.next()) {

                    if(!conn1.rs1.getTimestamp("timestamp").toString().equals(conn.rs.getTimestamp("timestamp").toString())){
                    
                        String upd="update register_attendance set member_id='"+conn.rs.getString(2)+"',session_id='"+conn.rs.getString(3)+"',facilitator_id='"+conn.rs.getString(4)+"',image_path='"+conn.rs.getString(5)+"',end_date='"+conn.rs.getString(6)+"',availability='"+conn.rs.getString(7)+"',marked_date='"+conn.rs.getString(8)+"',form_id='"+conn.rs.getString(9)+"',submission_date='"+conn.rs.getString(10)+"',reviewer_name='"+conn.rs.getString(11)+"', timestamp='"+conn.rs.getTimestamp(12)+"' where register_id='"+conn.rs.getString(1)+"' ";
                        
                        conn1.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                        
                    }
                    else{
                    
                    
                    System.out.println("Register attendance Data Already Exists " + insertdata);
                    
                    
                    }

                } else {
                    regattendance++;
                    conn1.st2.executeUpdate(insertdata);
                    System.out.println("Inserted " + insertdata);
                }


            }


            //============================================================= SESSION =============================







            //read data from the existing hc table
            String mergesession = "select * from session";

            conn.rs = conn.st.executeQuery(mergesession);

            while (conn.rs.next()) {

                String existancechecker = "select * from session where session_id='" + conn.rs.getString(1) + "'";

                String insertdata = "insert into session(session_id,date,duration,male_cds,female_cds,topic_id,group_id,method_id,end_date,marked_date,marking_status,year,period,timestamp,month) "
                        + "values ('" + conn.rs.getString(1) + "','" + conn.rs.getString(2) + "','" + conn.rs.getString(3) + "','" + conn.rs.getString(4) + "','" + conn.rs.getString(5) + "','" + conn.rs.getString(6) + "','" + conn.rs.getString(7) + "','" + conn.rs.getString(8) + "','" + conn.rs.getString(9) + "','" + conn.rs.getString(10) + "','" + conn.rs.getString(11) + "','" + conn.rs.getString(12) + "','" + conn.rs.getString(13) + "','" + conn.rs.getString(14) + "','" + conn.rs.getString(15) + "')";







                conn1.rs1 = conn1.st1.executeQuery(existancechecker);

                if (conn1.rs1.next()) {

                  if(!conn1.rs1.getTimestamp("timestamp").toString().equals(conn.rs.getTimestamp("timestamp").toString())){
                    
                        String upd="update session set date='"+conn.rs.getString(2)+"',duration='"+conn.rs.getString(3)+"',male_cds='"+conn.rs.getString(4)+"',female_cds='"+conn.rs.getString(5)+"',topic_id='"+conn.rs.getString(6)+"',group_id='"+conn.rs.getString(7)+"',method_id='"+conn.rs.getString(8)+"',end_date='"+conn.rs.getString(9)+"',marked_date='"+conn.rs.getString(10)+"',marking_status='"+conn.rs.getString(11)+"',year='"+conn.rs.getString(12)+"',period='"+conn.rs.getString(13)+"', timestamp='"+conn.rs.getTimestamp(14)+"',month='"+conn.rs.getString(15)+"' where session_id='"+conn.rs.getString(1)+"' ";
                        
                        conn1.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                         sessions++;
                    }
                    else{
                    
                    
                    System.out.println("Session  Data Already Exists " + insertdata);
                    
                    
                    }

                } else {
                    sessions++;
                    conn1.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED " + insertdata);

                }


            }



            //======================================== TOPICS====================================






            //read data from the existing hc table
            String mergetopics = "select * from topics";

            conn.rs = conn.st.executeQuery(mergetopics);

            while (conn.rs.next()) {

                String existancechecker = "select * from topics where topic_id='" + conn.rs.getString(1) + "'";

                String insertdata = "insert into topics(topic_id,topic_name,curriculum_id,timestamp) "
                        + "values ('" + conn.rs.getString(1) + "','" + conn.rs.getString(2) + "','" + conn.rs.getString(3) + "','" + conn.rs.getString(4) + "')";







                conn1.rs1 = conn1.st1.executeQuery(existancechecker);

                if (conn1.rs1.next()) {

                      if(!conn1.rs1.getTimestamp("timestamp").toString().equals(conn.rs.getTimestamp("timestamp").toString())){
                    
                        String upd="update topics set topic_name='"+conn.rs.getString(2)+"',curriculum_id='"+conn.rs.getString(3)+"', timestamp='"+conn.rs.getTimestamp(4)+"' where topic_id='"+conn.rs.getString(1)+"' ";
                      //i disabled updating of topics 
                        
                        
                       // conn1.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                       
                    }
                    else{
                    
                    
                    System.out.println("Topics  Data Already Exists " + insertdata);
                    
                    
                    }

                } else {

                    conn1.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED " + insertdata);

                }


            }

            //=============================transter facilitator===========================================










            //read data from the existing hc table
            String mergetf = "select * from transfer_facilitator";

            conn.rs = conn.st.executeQuery(mergetf);

            while (conn.rs.next()) {

                String existancechecker = "select * from transfer_facilitator where tf_id='" + conn.rs.getString(1) + "'";

                String insertdata = "insert into transfer_facilitator(tf_id,facilitator_id,partner_id,groups_ids,timestamp) "
                        + "values ('" + conn.rs.getString(1) + "','" + conn.rs.getString(2) + "','" + conn.rs.getString(3) + "','" + conn.rs.getString(4) + "','" + conn.rs.getString(5) + "')";







                conn1.rs1 = conn1.st1.executeQuery(existancechecker);

                if (conn1.rs1.next()) {

                    if(!conn1.rs1.getTimestamp("timestamp").toString().equals(conn.rs.getTimestamp("timestamp").toString())){
                    
                        String upd="update transfer_facilitator set facilitator_id='"+conn.rs.getString(2)+"',partner_id='"+conn.rs.getString(3)+"',groups_ids='"+conn.rs.getString(4)+"', timestamp='"+conn.rs.getTimestamp(5)+"' where tf_id='"+conn.rs.getString(1)+"' ";
                        
                        conn1.st2.executeUpdate(upd);
                        
                        System.out.println(upd);
                    
                         
                    }
                    else{
                    
                    
                    System.out.println("Transfer facilitator  Data Already Exists " + insertdata);
                    
                    
                    }

                } else {

                    conn1.st2.executeUpdate(insertdata);
                    System.out.println("INSERTED " + insertdata);

                }


            }



//============================================================================


            String feedbackmsg = "";

            if (facilitators == 0 && groups == 0 && members == 0 && regattendance == 0 && sessions == 0) {

                feedbackmsg = "<font color=\"red\">No New Inserts/updates done</font>";

            } else {

                feedbackmsg += "<font color=\"green\">" + facilitators + " </font>New Facilitators imported<br/>";
                feedbackmsg += "<font color=\"green\">" + groups + " </font>New Groups imported<br/>";
                feedbackmsg += "<font color=\"green\">" + members + " </font>New Participants imported<br/>";
                feedbackmsg += "<font color=\"green\">" + regattendance + " </font>New Attendnace records imported<br/>";
                feedbackmsg += "<font color=\"green\">" + sessions + " </font>New session records imported<br/>";

            }

            sess.setAttribute("feedbackmsg", feedbackmsg);
            response.sendRedirect("mergedata.jsp");




        } catch (SQLException ex) {
            Logger.getLogger(MergeData.class.getName()).log(Level.SEVERE, null, ex);
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
