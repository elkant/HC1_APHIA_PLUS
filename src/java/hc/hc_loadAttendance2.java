/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class hc_loadAttendance2 extends HttpServlet {

    String student = "", stude, nextpage;
    ArrayList attendance = new ArrayList();
    ArrayList sessionS_AL = new ArrayList();
    HttpSession session;
    String date;
    int total_no_of_lessons;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            //for the case of the search 

            student = request.getParameter("student");
            //  System.out.println(student);
            response.setContentType("text/html;charset=UTF-8");
            session = request.getSession();
            if (session.getAttribute("userid") == null) {
                response.sendRedirect("index.jsp");
            }

            if (request.getParameter("selected_date") != null) {
                date = request.getParameter("selected_date");
                session.setAttribute("l_e_d", date);
            } else if (request.getParameter("selected_date") == null && session.getAttribute("l_e_d") != null) {

                session.setAttribute("l_e_d", session.getAttribute("l_e_d"));

            } else {

                session.setAttribute("l_e_d", "Today");
            }
            if (attendance.size() > 0) {
                attendance.clear();
            }




            if (sessionS_AL.size() > 0) {
                sessionS_AL.clear();
            }
            dbConn conn = new dbConn();


System.out.println("__INSIDE HC_LOADATTENDANCE2");


            //============THIS PART WAS ADDED AFTER NAROK DEPLOYMENT===============
            String selected_month = "";


            if (session.getAttribute("selected_month") != null) {


                selected_month = session.getAttribute("selected_month").toString();

            }
            
            
            //if the formid session is empty i.e. "", then make a qry for 
            
            String formqry="";
            
            if(session.getAttribute("formid")!=null&&!session.getAttribute("formid").equals("")){
            
              formqry=" and register_attendance.form_id='"+session.getAttribute("formid") +"' ";
                
            
               }
            
            
//=====================================================================



//            ----------------------GET FACILITATOR NAME----------------------------------------




            //the final query to be adapted
            // String to check if the name entered is the first name
            String studchecker = "select * from register_attendance join member_details on register_attendance.member_id=member_details.member_id where member_details.group_id='" + session.getAttribute("hc_ssh_group_id") + "' AND  register_attendance.marked_date='" + session.getAttribute("hc_ssh_marked_date") + "'"
                    + " AND  ( member_details.sur_name='" + student + "' OR member_details.first_name='" + student + "' OR member_details.mname='" + student + "') AND member_details.year='" + session.getAttribute("year") + "' && member_details.period='" + session.getAttribute("period") + "' && member_details.month='" +selected_month+ "' "+formqry+"";

            String loadavailability = "select * from register_attendance join member_details on register_attendance.member_id=member_details.member_id where member_details.group_id='" + session.getAttribute("added_group_id") + "' AND member_details.year='" + session.getAttribute("year") + "' && member_details.period='" + session.getAttribute("period") + "' && member_details.month='" +selected_month + "' "+formqry+"  order by order_id";
            //String loadavailability = "select * from register_attendance join member_details on register_attendance.member_id=member_details.member_id where register_attendance.marked_date='" + session.getAttribute("hc_ssh_marked_date") + "'AND member_details.year='" + session.getAttribute("year") + "' && member_details.period='" + session.getAttribute("period") + "' && member_details.month='" +selected_month + "'  order by order_id";

            // String loadavailability="select * from register_details join student_registration on register_details.student_id=student_registration.studentid where register_details.topic='Self Awareness'";



System.out.println("\n\n"+loadavailability);



//set the values to be displayed
//________________________________________________________________________________________________________

            String session_content = "Select * from session where group_id='" + session.getAttribute("hc_ssh_group_id") + "' AND marked_date='" + session.getAttribute("hc_ssh_marked_date") + "' ";

            conn.rs4 = conn.st4.executeQuery(session_content);

            int sess_count = 1;
            while (conn.rs4.next()) {

                session_content_bean sc = new session_content_bean();

                sc.setSession_id(conn.rs4.getString(1));
                sc.setDate(conn.rs4.getString(2));
                sc.setTime(conn.rs4.getString(3));
                sc.setMcondoms(conn.rs4.getString(4));
                sc.setFcondoms(conn.rs4.getString(5));
                sc.setTopic_id(conn.rs4.getString(6));
                sc.setMethod(conn.rs4.getString(8));
                sc.setSess_count(sess_count);
                sess_count++;
                sessionS_AL.add(sc);

            }//end of count

            session.setAttribute("session_count", sess_count - 1);
            session.setAttribute("sesion_AL", sessionS_AL);




//=========================================================================================================           













            // session.setAttribute("reg_no_of_lessons", "4");

            //String dateChecker="select * from register_details where date='"+date+"'"; session.setAttribute("hc_ssh_marked_date", marking_date);

            if (session.getAttribute("hc_ssh_number_of_sessions") != null) {
                total_no_of_lessons = Integer.parseInt(session.getAttribute("hc_ssh_number_of_sessions").toString());
            }
            int count = 1;
            int l_no = 1;
            /** conn.rs1=conn.st1.executeQuery(dateChecker);
            while(conn.rs1.next())
            {**/
            //excequte querry to get all teachers details
            if (request.getParameter("student") == null) {
                conn.rs = conn.st.executeQuery(loadavailability);
            } else if (request.getParameter("student") != null) {
                System.out.println("Entered here");
                conn.rs = conn.st.executeQuery(studchecker);

            }

            while (conn.rs.next()) {
                //add the values in the database to a bean
                hc_mark_register_bean tb = new hc_mark_register_bean();
                String selector_facili = "select * from facilitator_details where facilitator_id='" + conn.rs.getString("facilitator_id") + "'  ";
                conn.rs2 = conn.st2.executeQuery(selector_facili);
                conn.rs2.next();
                String full_name = conn.rs2.getString("first_name") + " " + conn.rs2.getString("middle_name") + " " + conn.rs2.getString("sur_name");
                String phone = conn.rs2.getString("phone");
                tb.setFacilitator_name(full_name);
                tb.setFacilitator_phone(phone);
                session.setAttribute("nt_facilitator_name", full_name);
                session.setAttribute("nt_facilitator_phone", phone);
//                ---------------START AND END DATE------------------------------------------------
                
                
                //======================THIS PART WAS MODIFIED ON 16th JULY 2014, and marked_date....=====================================
                String selector_dater = "select * from new_topic where facilitator_id='" + conn.rs.getString("facilitator_id") + "' and marking_date='"+conn.rs.getString("register_attendance.marked_date") +"'";
                conn.rs2 = conn.st2.executeQuery(selector_dater);
                conn.rs2.next();
                String start_date1 = conn.rs2.getString("start_date");
                String[] dater1 = start_date1.split("/");
                String mn1 = dater1[0];
                String dy1 = dater1[1];
                String yr1 = dater1[2];
                String start_date = dy1 + "/" + mn1 + "/" + yr1;
                String end_date1 = conn.rs2.getString("end_date");
                String[] dater2 = end_date1.split("/");
                String mn2 = dater2[0];
                String dy2 = dater2[1];
                String yr2 = dater2[2];
                String end_date = dy2 + "/" + mn2 + "/" + yr2;
                session.setAttribute("hc_start_date", start_date);
                session.setAttribute("hc_end_date", end_date);
                
                //======================THIS PART WAS MODIFIED ON 16th JULY 2014  newtopicid date=====================================
               
                
                session.setAttribute("newtopicid",conn.rs2.getString("new_topic_id"));
                session.setAttribute("hc_start_date1", conn.rs2.getString("start_date"));
                session.setAttribute("hc_end_date1", conn.rs2.getString("end_date"));
                
                tb.setAvailability(conn.rs.getString("availability"));
                tb.setDate(conn.rs.getString("end_date"));
                tb.setSession_id(conn.rs.getString("session_id"));
                tb.setMember_id(conn.rs.getString("register_attendance.member_id"));

                // System.out.println("Member Id"+conn.rs.getString("register_attendance.member_id"));

                tb.setCount(count);
                tb.setReg_id(conn.rs.getString("register_id"));
                tb.setIpath(conn.rs.getString("image_path"));
                tb.setF_name(conn.rs.getString("first_name"));
                tb.setM_name(conn.rs.getString("mname"));
                tb.setL_name(conn.rs.getString("sur_name"));
                tb.setSex(conn.rs.getString("sex"));

                tb.setAge(conn.rs.getString("age"));
                tb.setLesson_no(l_no);
                if (l_no == total_no_of_lessons) {
                    l_no = 0;
                }

                //add the bean object to an arraylist
                attendance.add(tb);
                count++;

                //make the lesson no not pass 4

                l_no++;
                session.setAttribute("fnamer", conn.rs.getString("first_name"));
                // System.out.println( session.getAttribute("fnamer"));  

            }


            conn.st.close();
            conn.st2.close();
            conn.st4.close();

            //end of while
            session.setAttribute("hc_attendanceBean", attendance);
            //the no of rows
            session.setAttribute("hc_upd_reg_no_of_student", count - 1);
            if (count == 1) {
                session.setAttribute("respo", "<font color=\"red\">No student was found under that Name</font>");
                nextpage = "filter_session.jsp";

            } //this is the session that is being used to determine the row and column of the users
            // session.setAttribute("reg_topic","Self Awareness");
            else {
                //   session.setAttribute("hc_register_updated","<font color=\"green\">Register updated succesfully</font>");

                nextpage = "hc_editRegister2.jsp";
            }
            response.sendRedirect(nextpage);

        } catch (SQLException ex) {
            Logger.getLogger(hc_loadAttendance2.class.getName()).log(Level.SEVERE, null, ex);
        }





    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
