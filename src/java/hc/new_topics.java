/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.Calendar;
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
public class new_topics extends HttpServlet {

    String start_date, end_date, current_time, expected_no_of_lessons, facil, co_facil, joinedtopicids = "", nextpage;
    int no_of_lessons;
    String sel1[], sel2[], sel3[], sel4[], sel5[], sel6[], sel7[], sel8[], sel9[], sel10[];
    String topicids[];
    HttpSession session;
    String year2, period;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");

            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int date = cal.get(Calendar.DATE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            int microsec = cal.get(Calendar.MILLISECOND);
            String yr, mnth, dater, hr, mn, sc, action = "";

            yr = Integer.toString(year);
            mnth = Integer.toString(month);
            dater = Integer.toString(date);
            hr = Integer.toString(hour);
            mn = Integer.toString(min);
            sc = Integer.toString(sec);
            current_time = yr + "-" + mnth + "-" + dater + "-" + hr + ":" + mn + ":" + sc+":"+microsec;
            dbConn conn = new dbConn();
            session = request.getSession();
            
//============THIS PART WAS ADDED AFTER NAROK DEPLOYMENT===============
            String selected_month = "";


            if (session.getAttribute("selected_month") != null) {


                selected_month = session.getAttribute("selected_month").toString();

            }
//=====================================================================


            if (session.getAttribute("userid") == null) {
                nextpage = "index.jsp";
            } else {
                facil = request.getParameter("facil");

                start_date = request.getParameter("start_date");
                end_date = request.getParameter("end_date");
                String curr = request.getParameter("curriculum");
                String curriculum = "select * from curriculum where curriculum_id='" + curr + "'";
                conn.rs0 = conn.st0.executeQuery(curriculum);
                conn.rs0.next();
                session.setAttribute("hc_curriculum", conn.rs0.getString("curriculum_name"));

String colfacilitatorarray[]=null;
                
 //co facilitator is now an arraylist

                if (request.getParameter("co_facil") != null) {
                    //co_facil = request.getParameter("co_facil");
                    colfacilitatorarray=request.getParameterValues("co_facil");
                    
                    for(int a=0;a<colfacilitatorarray.length;a++){
                    
                    co_facil+=colfacilitatorarray[a];
                    if(a<colfacilitatorarray.length-1){ co_facil+=",";}
                    
                    }
                    
                } else {
                    co_facil = "";

                }

                
                

 //________________________UNIQUE PRIMARY KEY TO BE USED AS  IN BOTH SESSION TABLE, REGISTER_ATTENDANCE AND _______________________________             


                session.setAttribute("nt_unique_marking_date", current_time);
                session.setAttribute("hc_ssh_marked_date", current_time);
                //_________________________________________________________________________________________________    



//___________________________________________________________________________________________________

                String facilnames = "Select * from facilitator_details where facilitator_id='" + facil + "'";

                conn.rs2 = conn.st2.executeQuery(facilnames);

                conn.rs2.next();

                session.setAttribute("nt_facilitator_name", conn.rs2.getString(2) + " " + conn.rs2.getString(3));

                session.setAttribute("nt_facilitator_phone", conn.rs2.getString(4));

                session.setAttribute("hc_start_date", start_date);

                session.setAttribute("hc_end_date", end_date);



                String new_end_date = "";
                new_end_date = end_date.replace('/', '-');
                //get the startdate and enddate into a session


                session.setAttribute("hc_nt_end_date", new_end_date);

                expected_no_of_lessons = request.getParameter("expected_no_of_lessons");

                session.setAttribute("hc_e_lessons", expected_no_of_lessons);

                no_of_lessons = Integer.parseInt(expected_no_of_lessons);
                //get all the selected topic ids for multiple select boxes and add them to their respective array


                //this is accomodating only ten fields

                for (int a = 1; a <= no_of_lessons; a++) {
                    if (request.getParameterValues("select1") != null && a == 1) {
                        String select = "";
                        sel1 = request.getParameterValues("select" + a);

                        for (int p = 0; p < sel1.length; p++) {
                            select = select + "," + sel1[p];

                        }
                        joinedtopicids = joinedtopicids + select + "_";

                    }

                    if (request.getParameterValues("select2") != null && a == 2) {
                        String select = "";
                        sel2 = request.getParameterValues("select" + a);
                        for (int p = 0; p < sel2.length; p++) {
                            select = select + "," + sel2[p];

                        }
                        joinedtopicids = joinedtopicids + select + "_";


                    }

                    if (request.getParameterValues("select3") != null && a == 3) {
                        String select = "";
                        sel3 = request.getParameterValues("select" + a);
                        for (int p = 0; p < sel3.length; p++) {
                            select = select + "," + sel3[p];

                        }
                        joinedtopicids = joinedtopicids + select + "_";

                    }
                    if (request.getParameterValues("select4") != null && a == 4) {
                        String select = "";
                        sel4 = request.getParameterValues("select" + a);
                        for (int p = 0; p < sel4.length; p++) {
                            select = select + "," + sel4[p];

                        }
                        joinedtopicids = joinedtopicids + select + "_";

                    }

                    if (request.getParameterValues("select5") != null && a == 5) {
                        String select = "";
                        sel5 = request.getParameterValues("select" + a);
                        for (int p = 0; p < sel5.length; p++) {
                            select = select + "," + sel5[p];

                        }
                        joinedtopicids = joinedtopicids + select + "_";

                    }

                    if (request.getParameterValues("select6") != null && a == 6) {
                        String select = "";
                        sel6 = request.getParameterValues("select" + a);
                        for (int p = 0; p < sel6.length; p++) {
                            select = select + "," + sel6[p];

                        }
                        joinedtopicids = joinedtopicids + select + "_";

                    }


                    if (request.getParameterValues("select7") != null && a == 7) {
                        String select = "";
                        sel7 = request.getParameterValues("select" + a);
                        for (int p = 0; p < sel7.length; p++) {
                            select = select + "," + sel7[p];

                        }
                        joinedtopicids = joinedtopicids + select + "_";

                    }

                    //8     

                    if (request.getParameterValues("select8") != null && a == 8) {
                        String select = "";
                        sel8 = request.getParameterValues("select" + a);
                        for (int p = 0; p < sel8.length; p++) {
                            select = select + "," + sel8[p];

                        }
                        joinedtopicids = joinedtopicids + select + "_";

                    }



                    if (request.getParameterValues("select9") != null && a == 9) {
                        String select = "";
                        sel9 = request.getParameterValues("select" + a);
                        for (int p = 0; p < sel9.length; p++) {
                            select = select + "," + sel9[p];

                        }
                        joinedtopicids = joinedtopicids + select + "_";

                    }


                    //10   
                    if (request.getParameterValues("select10") != null && a == 10) {
                        String select = "";
                        sel10 = request.getParameterValues("select" + a);
                        for (int p = 0; p < sel10.length; p++) {
                            select = select + "," + sel10[p];

                        }
                        joinedtopicids = joinedtopicids + select + "_";

                    }



                }//end of for loop

                //topicids=joinedtopicids.split("_");

                session.setAttribute("hc_all_topic_ids", joinedtopicids);
                session.setAttribute("hc_nt_facil", facil);

                if (session.getAttribute("year") != null && session.getAttribute("period") != null) {
                    year2 = session.getAttribute("year").toString();
                    period = session.getAttribute("period").toString();
                }

                String query1;
                if (!joinedtopicids.equals("") && session.getAttribute("year") != null && session.getAttribute("period") != null) {
                    
                    String maxtopicid=uniqueid(); 
                    
                    query1 = "insert into new_topic (new_topic_id, marking_status, facilitator_id, topic_id, expected_sessions,start_date,end_date,year,marking_date,year2,period,month)values('" +maxtopicid+ "','no','" + facil + "','" + joinedtopicids + "','" + expected_no_of_lessons + "','" + start_date + "','" + end_date + "','" + new_end_date + "','" + current_time + "','" + year2 + "','" + period + "','" + selected_month + "')";
                    conn.st.executeUpdate(query1);

                    //session.setAttribute("topics_added", "<font color=\"green\">topics added </font>");
                    joinedtopicids = "";


                    //*************************************SELECT THE MAXIMUM TOPIC ID IN THE TABLE
                    conn.rs1 = conn.st1.executeQuery("Select MAX(new_topic_id) from new_topic");
                    conn.rs1.next();
                    session.setAttribute("HC_MAXIMUM_T_ID", maxtopicid);
                    String max = conn.rs1.getString(1);
                    String com = InetAddress.getLocalHost().getHostName();
                    String ip = InetAddress.getLocalHost().getHostAddress();

                    String inserter = "insert into audit set host_comp='" + com + " " + ip + "' ,  action='Added new topic with new topic id :" + conn.rs1.getString(1) + "',time='" + current_time + "',actor_id='" + session.getAttribute("userid") + "'";
                    conn.st3.executeUpdate(inserter);



                    nextpage = "atten_getMember_details";
                } else {
                    session.setAttribute("topics_added", "<font color=\"red\">topics <b>NOT</b> added </font>");


                    nextpage = "topic_start_end_date.jsp";

                }

            }


        } catch (SQLException ex) {
            Logger.getLogger(new_topics.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect(nextpage);

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

        Calendar cal1 = Calendar.getInstance();

        int year1 = cal1.get(Calendar.YEAR);
        int month1 = cal1.get(Calendar.MONTH) + 1;
        int date1 = cal1.get(Calendar.DAY_OF_MONTH);
        int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
        int min1 = cal1.get(Calendar.MINUTE);
        int sec1 = cal1.get(Calendar.SECOND);
        int milsec = cal1.get(Calendar.MILLISECOND);


        return generateRandomNumber(800, 8000) + year1 + "" + month1 + "" + date1 + hour1 + min1 + sec1 + milsec;
    }

    public int generateRandomNumber(int start, int end) {
        Random random = new Random();
        long fraction = (long) ((end - start + 1) * random.nextDouble());
        return ((int) (fraction + start));
    }
//==========================================================================
}
