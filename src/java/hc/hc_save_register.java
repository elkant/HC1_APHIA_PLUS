/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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
public class hc_save_register extends HttpServlet {

    String status, partis_id, topic, ipath, formnumber, reviewer, submission_date, nextpage = "";
    String[] methodid;
    int no_of_students, no_of_lessons, sessions_complete;
    String sel1[], sel2[], sel3[], sel4[], sel5[], sel6[], sel7[], sel8[], sel9[], sel10[];
   
    int form_numbers = 0;
    int i;
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession();
    ArrayList methods = new ArrayList();
    ArrayList datepicker = new ArrayList();
    ArrayList time = new ArrayList();
    ArrayList malecondoms = new ArrayList();
    ArrayList femalecondoms = new ArrayList();
    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
        try {
            response.setContentType("text/html;charset=UTF-8");


            //============THIS PART WAS ADDED AFTER NAROK DEPLOYMENT===============
            String selected_month = "";


            if (session.getAttribute("selected_month") != null) {


                selected_month = session.getAttribute("selected_month").toString();

            }
//=====================================================================



            dbConn conn = new dbConn();
            formnumber = "";
            
           // This form number was changed on 30th March 2014 
          //  form_numbers = Integer.parseInt(request.getParameter("form_numbers").toString());
            form_numbers = 1;
            i = 0;
            while (i < form_numbers) {
                formnumber = formnumber + request.getParameter("number" + i + "") + "-";
                i++;
            }

            System.out.println("String of form numbers:" + formnumber);

            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
            int day = now.get(Calendar.DAY_OF_MONTH);
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int min = now.get(Calendar.MINUTE);
            int sec = now.get(Calendar.SECOND);
            String marking_date = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;


            if (session.getAttribute("userid") == null && session.getAttribute("year") == null) {
                nextpage = "index.jsp";
            } else {
                //get the no of lessons and the no of students which determine the looping 
                no_of_students = Integer.parseInt(session.getAttribute("hc_reg_no_of_participants").toString());

                no_of_lessons = Integer.parseInt(session.getAttribute("hc_e_lessons").toString());


                if (methods.size() > 0) {
                    methods.clear();
                }


                if (datepicker.size() > 0) {
                    datepicker.clear();
                }

                if (time.size() > 0) {
                    time.clear();
                }
                if (malecondoms.size() > 0) {
                    malecondoms.clear();
                }

                if (femalecondoms.size() > 0) {
                    femalecondoms.clear();
                }

                //get the values to be saved in the session 
                for (int x = 1; x <=no_of_lessons; x++) {
                    //methods
                    if (request.getParameterValues("method" + x) != null && !request.getParameterValues("method" + x).equals("")) {

                        String mtds[] = request.getParameterValues("method" + x);
                        String mtds_str = "";
                        for (int p = 0; p < mtds.length; p++) {
                            mtds_str = mtds_str + mtds[p] + ",";
                        }

                        methods.add(mtds_str);

                        mtds_str = "";
                    } else {

                        methods.add("6");

                    }

                    //datepicker

                    if (request.getParameter("datepicker" + x) != null && !request.getParameter("datepicker" + x).equals("")) {

                        datepicker.add(request.getParameter("datepicker" + x));

                    } else {
                        //default date
                        datepicker.add("09/09/2013");

                    }


                    //time

                    if (request.getParameter("time" + x) != null && !request.getParameter("time" + x).equals("")) {

                        time.add(request.getParameter("time" + x));

                    } else {
                        //default time
                        time.add("60");

                    }


                    //male condoms


                    if (request.getParameter("malecondoms" + x) != null && !request.getParameter("malecondoms" + x).equals("")) {

                        malecondoms.add(request.getParameter("malecondoms" + x));
                        
                        System.out.println("\n Male Condoms is ___"+request.getParameter("malecondoms" + x));
                        

                    } else {
                        //default time
                        malecondoms.add("0");
 System.out.print("\n Male Condoms is DEFAULT ___");
                    }





                    //female condoms


                    if (request.getParameter("femalecondoms" + x) != null && !request.getParameter("femalecondoms" + x).equals("")) {

                        femalecondoms.add(request.getParameter("femalecondoms" + x));

                    } else {
                        //default time
                        femalecondoms.add("0");

                    }



                }//end of for loop


                String alltopicidsarr[] = (String[]) session.getAttribute("hc_mr_topic_ids_arraylist");

//saving all the session contents to the database      
                //rem here we are not concerned with what session worked and where 
                
                String qr3="select * from session where marking_status='yes' and marked_date='"+session.getAttribute("nt_unique_marking_date")+"' and group_id='" + session.getAttribute("s_group_name") + "' and year='" + session.getAttribute("year") + "' and period='" + session.getAttribute("period") + "' and month='" + selected_month + "'  ";
                
                
                System.out.println("CHECK IF THAT GROUP HAS GOT A MARKED SESSION"+qr3);
                
                conn.rs_5 = conn.st_5.executeQuery(qr3);


                if (!conn.rs_5.next()) {
                    for (int t = 0; t < no_of_lessons; t++) {

 String mdate2;

                    Date mydate2 = new Date();
                    mdate2 = formatter.format(mydate2);

                        String savetosession = "insert into session (session_id,marking_status,marked_date,end_date,date,duration,male_cds,female_cds,topic_id,group_id,method_id,year,period,month,timestamp) values "
                                + "('" + uniqueid().trim() + "','no','" + session.getAttribute("nt_unique_marking_date").toString() + "','" + session.getAttribute("hc_nt_end_date") + "','" + datepicker.get(t) + "','" + time.get(t) + "','" + malecondoms.get(t) + "','" + femalecondoms.get(t) + "','" + alltopicidsarr[t] + "','" + session.getAttribute("s_group_name") + "','" + methods.get(t) + "','" + session.getAttribute("year") + "','" + session.getAttribute("period") + "','" + selected_month + "','"+mdate2+"')";



                        conn.st3.executeUpdate(savetosession);


                    }
                }
                else{
                
                
                System.out.println("Records already exist for that month ");
                
                }

                //begin my loop
                //
//           formnumber=request.getParameter("form_number");

                String formuniqid=uniqueid();
                
          
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
               // timestamp='"+mdate+"',
                
                
                
                conn.st4.executeUpdate("insert into forms(form_id,form_number,year,period,month,timestamp) value('" + formuniqid + "','" + formnumber.trim() + "','" + session.getAttribute("year") + "','" + session.getAttribute("period") + "','" + selected_month + "','"+mdate+"')");
//get the just saved form id
                
                //this part should only work if we used an autoincrement id
           //====================not being used=================     
                conn.rs4 = conn.st4.executeQuery("Select MAX(form_id) FROM forms");
                conn.rs4.next();
                
                
    ///set the form numbers and the form id into a session

          session.setAttribute("formid",formuniqid);
          session.setAttribute("formname",formnumber);


                int sessions_complete_count = 0;

                boolean datainserted=false;

                String firstid=request.getParameter("sid"+(no_of_students-1));
                
 String qr4 = "select * from register_attendance where member_id='" + firstid + "' and session_id='" + session.getAttribute("hc_all_topic_ids").toString() + "' and end_date='" + session.getAttribute("hc_nt_end_date") + "' ";
   
System.out.println("REGISTER ATTENDANCE CHECKER"+qr4);


conn.rs_6 = conn.st_6.executeQuery(qr4);

//if the first student exists, dont add 

                   if (!conn.rs_6.next()) {
                   
                  
                
                
                
                    for (int row = 1; row <= no_of_students - 1; row++) {

                        for (int col = 1; col <= no_of_lessons; col++) {
                            try {
                                //if the checkbox is not marked  
                                if (request.getParameter("cb" + col + "" + row).equals("2")) {
                                    status = "2";
                                    ipath = "images/absent.png";

                                } else if (request.getParameter("cb" + col + "" + row).equals("3")) {
                                    status = "3";
                                    ipath = "images/ABPRE.png";
                                    sessions_complete_count++;
                                } else {
                                    status = "1";
                                    ipath = "images/present.png";
                                    sessions_complete_count++;
                                }//end of else


                                //this session is used to open the recently marked group
                                session.setAttribute("hc_ssh_marked_date", marking_date);
                                session.setAttribute("hc_ssh_number_of_sessions", no_of_lessons);






                                partis_id = request.getParameter("sid" + row);
                                topic = session.getAttribute("hc_all_topic_ids").toString();

                                
                                
                                
                                
                                
                                reviewer = request.getParameter("reviewer");
                                submission_date = request.getParameter("submission_date");

                                 String mdate1;

                    Date mydate1 = new Date();
                    mdate1 = formatter.format(mydate1);

                                String save = "insert into register_attendance (register_id, reviewer_name,submission_date,form_id,marked_date,facilitator_id,image_path,member_id,end_date,session_id,availability,timestamp) "
                                        + "values('" + uniqueid().trim() + "','" + reviewer + "','" + submission_date + "','" +formuniqid + "','" + session.getAttribute("nt_unique_marking_date").toString() + "','" + session.getAttribute("hc_nt_facil").toString() + "','" + ipath + "','" + partis_id + "','" + session.getAttribute("hc_nt_end_date") + "','" + topic + "','" + status + "','"+mdate1+"')";

                                // System.out.println(" check box :"+request.getParameter("cb"+col+row));
                                System.out.println(save);
                                //execute update

       //set a boolean that will be used in the query below to manage data
                                
                                datainserted=true;
                                

                                conn.st.executeUpdate(save);


                                String noc_sessions = " update member_details set sessions_attended='" + sessions_complete_count + "' where member_id='" + partis_id + "'";
                                conn.st3.executeUpdate(noc_sessions);
                                              
                   
               


                            } //end of  while
                            catch (SQLException ex) {
                                Logger.getLogger(hc_save_register.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }//end of column

                        sessions_complete_count = 0;
                    }//end of row
                   }//end of if
                   
                   else {
                   
                   
                   }


                    if(datainserted){
                        
                    //*************************************UPDATE THE STATUS OF THE MARKING_STATUS IN TABLE NEW_TOPIC AND SESSION TO YES 
                    //new topic table
                    conn.st1.executeUpdate("update new_topic set marking_status='yes' where new_topic_id='" + session.getAttribute("HC_MAXIMUM_T_ID") + "'");
                    //session table
                    
                    
                    conn.st1.executeUpdate("update session set marking_status='yes' where marked_date='" + session.getAttribute("nt_unique_marking_date").toString() + "'");
                    String com = InetAddress.getLocalHost().getHostName() + " " + InetAddress.getLocalHost().getHostAddress();


                    conn.st1.executeUpdate("insert into audit (action,time,actor_id,host_comp)values('marked register for group " + session.getAttribute("sc_group_name") + "','" + marking_date + "','" + session.getAttribute("userid") + "','" + com + "')");
                    
                    
                    System.out.print("NEW TOPIC and Session tables where updated..audit table was also updated");
                    
                    
                    }
                
                conn.st.close();
                conn.st1.close();
                conn.st3.close();
                conn.st4.close();





                session.setAttribute("hc_register_marked", "<font color=\"green\">Register Marked succesfully.</font>");

                //set a message to display in the filter topic page
                session.setAttribute("hc_group_added", "<font color=\"green\">Last marked group: </font><font color=\"white\">" + session.getAttribute("sc_group_name") + "<br></font>"
                        + "</font><a href=\"hc_loadAttendance\" class=\"linkstyle\">VIEW</a>");

                nextpage = "filter_session.jsp";
            }
            response.sendRedirect(nextpage);
        } catch (SQLException ex) {
            Logger.getLogger(hc_save_register.class.getName()).log(Level.SEVERE, null, ex);
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

//====================random id functions================================ 
    public String uniqueid() {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int milsec = cal.get(Calendar.MILLISECOND);


        return year + "" + month + "" + date + hour + min + sec + milsec + generateRandomNumber(800, 9000);
    }

    public int generateRandomNumber(int start, int end) {
        Random random = new Random();
        long fraction = (long) ((end - start + 1) * random.nextDouble());
        return ((int) (fraction + start));
    }
//==========================================================================
}
