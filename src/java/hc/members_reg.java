/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import hc.dbConn;
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
 * @author Nyabuto Geofrey
 */
public class members_reg extends HttpServlet {

    HttpSession session;
    String fname1, mname1, lname1, sex1, age1;
    String district, target_pop, group;
    String sname = "", current_time;
    String year, period, sname2, noofrows;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session = request.getSession();
            if (session.getAttribute("userid") == null) {
                response.sendRedirect("index.jsp");
            }
            Calendar cal = Calendar.getInstance();
            int yer = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int date = cal.get(Calendar.DATE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            String yr, mnth, dater, hr, mn, sc, action = "";
            yr = Integer.toString(yer);
            mnth = Integer.toString(month);
            dater = Integer.toString(date);
            hr = Integer.toString(hour);
            mn = Integer.toString(min);
            sc = Integer.toString(sec);

            dbConn conn = new dbConn();
            sname2 = sname = "";
            current_time = yr + "-" + mnth + "-" + dater + "-" + hr + ":" + mn + ":" + sc;
            target_pop = request.getParameter("target_pop");
            group = request.getParameter("group_id").trim();
            district = request.getParameter("district");
            noofrows = request.getParameter("noofrows");

            int no_of_rows = 0;

            no_of_rows = Integer.parseInt(noofrows);
//==============================THIS PART CHANGED AFTER NAROK==========
            
String selected_month="";



if(session.getAttribute("selected_month")!=null){


selected_month=session.getAttribute("selected_month").toString();

}
//=====================================================================

System.out.println("SELECTED MONTH "+selected_month);


            if (district != null) {

//    session.setAttribute("school",school);
//    session.setAttribute("clas",clas);
                session.setAttribute("district", district);
                year = session.getAttribute("year").toString();
                period = session.getAttribute("period").toString();

                for (int i = 1; i <= no_of_rows; i++) {
                    int found = 0;
                    fname1 = request.getParameter("fname" + i);
                    mname1 = request.getParameter("mname" + i);
                    lname1 = request.getParameter("lname" + i);
                    sex1 = request.getParameter("sex" + i);
                    age1 = request.getParameter("age" + i);
                    if (!fname1.equals("")) {

                        fname1 = fname1.toUpperCase().replaceAll("\\s+", "");
                        mname1 = mname1.toUpperCase().replaceAll("\\s+", "");
                        lname1 = lname1.toUpperCase().replaceAll("\\s+", "");
                        
                        fname1 = fname1.replace("'", "");
                        mname1 = mname1.replace("'", "");
                        lname1 = lname1.replace("'", "");
                        
//            sex1=sex1.toUpperCase();
//          String check_existence="SELECT COUNT(member_id) FROM member_details WHERE first_name='"+fname1+"' && mname='"+mname1+"' "
//                  + "&& sur_name='"+lname1+"' && age='"+age1+"' && group_id='"+group+"'";
//          conn.rs=conn.st.executeQuery(check_existence);
//          if(conn.rs.next()==true){
//              found=conn.rs.getInt(1);
//          }
//          if(found==0){
                        String query1 = "INSERT INTO member_details (member_id,first_name,mname,sur_name,sex,age,group_id,sessions_attended,year,period,month)values('" + uniqueid() + "','" + fname1 + "','" + mname1 + "','" + lname1 + "','" + sex1 + "','" + age1 + "','" + group + "','0','" + year + "','" + period + "','"+selected_month+"')";

                        String chck_existence = "SELECT COUNT(member_id) FROM member_details WHERE first_name='" + fname1 + "'&& mname='" + mname1 + "'&& sur_name='" + lname1 + "'&& age='" + age1 + "' && group_id='" + group + "'&& year='" + year + "' && period='"+period+"' && month='" + selected_month + "'";
                        conn.rs = conn.st.executeQuery(chck_existence);
                        if (conn.rs.next() == true) {
                            found = conn.rs.getInt(1);
                        }
                        if ((!(fname1.equals(""))) && (found == 0)) {
                            
                            //==========record somewhere that members have been added for that group=======
                            
                            session.setAttribute("ispartisadded", "1");
                            
                            conn.st.executeUpdate(query1);
                            sname += fname1 + ",";
                        }
                        if ((!(fname1.equals(""))) && (found > 0)) {
                            sname2 += fname1 + ",";

                        }
                    }
                }
                if (!sname.equals("")) {
                    String com = InetAddress.getLocalHost().getHostName() + " " + InetAddress.getLocalHost().getHostAddress();

                    String inserter = "insert into audit set host_comp='" + com + "', action='Registered Peers.First Names :',time='" + current_time + "',actor_id='" + session.getAttribute("userid") + "'";
                    conn.st3.executeUpdate(inserter);
                    session.setAttribute("success", "<font color=\"green\">" + sname + " added Successfully.</font>");
                }
                if (!sname2.equals("")) {
                    session.setAttribute("failed", "<font color=\"red\">" + sname2 + " not added to the system. Details already exist.</font>");
                }
            }
            if (district == null) {
                session.setAttribute("failed", "<font color=\"red\"> No Participant was saved to the database. Log out and try again.</font>");
            }

            String nextpage="add_members.jsp";
             //if this session is active, it means that we are adding a long form.
           
           if(session.getAttribute("prevpage")!=null){
           nextpage="sessions_session_holder";
                                                     }
           
            
            
            
            System.out.println(session.getAttribute("failed"));
            response.sendRedirect(nextpage);
        } finally {
            out.close();
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(members_reg.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(members_reg.class.getName()).log(Level.SEVERE, null, ex);
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
