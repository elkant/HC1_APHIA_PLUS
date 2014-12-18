/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * @author Nyabuto Geofrey
 */
public class Edit_member_action extends HttpServlet {

    String query1, query2, studentid, fname, mname, lname, sex, age, group, current_time, group_id;
    HttpSession session;
    int sessions_attended, found;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session = request.getSession();
            if (session.getAttribute("userid") == null) {
                response.sendRedirect("index.jsp");
            }
            sessions_attended = found = 0;
            group_id = "";
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int date = cal.get(Calendar.DATE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            String yr, mnth, dater, hr, mn, sc, action = "";
            yr = Integer.toString(year);
            mnth = Integer.toString(month);
            dater = Integer.toString(date);
            hr = Integer.toString(hour);
            mn = Integer.toString(min);
            sc = Integer.toString(sec);
            current_time = yr + "-" + mnth + "-" + dater + "-" + hr + ":" + mn + ":" + sc;
            int num = 0;
            dbConn conn = new dbConn();
            studentid = request.getParameter("member_id");
            fname = request.getParameter("fname");
            mname = request.getParameter("mname");
            lname = request.getParameter("lname");
            sex = request.getParameter("sex");
            age = request.getParameter("age");
            group = request.getParameter("group");

            fname = fname.toUpperCase().replaceAll("\\s+", "");
            mname = mname.toUpperCase().replaceAll("\\s+", "");
            lname = lname.toUpperCase().replaceAll("\\s+", "");
            System.out.println(studentid);
            String check_marking_status = "SELECT * FROM member_details WHERE member_id='" + studentid + "'";
            conn.rs = conn.st.executeQuery(check_marking_status);
            if (conn.rs.next() == true) {
                sessions_attended = conn.rs.getInt("sessions_attended");
                group_id = conn.rs.getString("group_id");
            }
            String existence = "SELECT COUNT(member_id) FROM member_details WHERE first_name='" + fname + "'&& mname='" + mname + "' && sur_name='" + lname + "' && age='" + age + "' && group_id='" + group + "' && member_id!='" + studentid + "'";
            conn.rs = conn.st.executeQuery(existence);
            if (conn.rs.next() == true) {
                found = conn.rs.getInt(1);
            }
            System.out.println(existence + "found    :  " + found);
            
              Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
               // timestamp='"+mdate+"',
            
            
            if (found > 0) {
                session.setAttribute("member_edit", "<font color=\"red\">Details not Saved. This member details has been registered to a different member.</font>");
            }

            if (!group.equals(group_id) && sessions_attended > 0) {
                session.setAttribute("member_edit", "<font color=\"red\">Group not Changed but other details have been saved successfully.</font>");
                query2 = "UPDATE member_details SET timestamp='"+mdate+"', first_name='" + fname + "',mname='" + mname + "',sur_name='" + lname + "',sex='" + sex + "',age='" + age + "' WHERE member_id='" + studentid + "'";
                conn.st.executeUpdate(query2);
            }

            if ((found == 0 && (group.equals(group_id) || (!group.equals(group_id) && sessions_attended == 0)))) {
                query2 = "UPDATE member_details SET timestamp='"+mdate+"', first_name='" + fname + "',mname='" + mname + "',sur_name='" + lname + "',sex='" + sex + "',age='" + age + "',group_id='" + group + "' WHERE member_id='" + studentid + "'";
                num = conn.st.executeUpdate(query2);
                session.setAttribute("st_id", studentid);
                System.out.println("worked here" + conn.st.executeUpdate(query2));
                if (num > 0) {
                    String ms = "<font color=green>" + fname + ",  Details Edited Successfully.</font>";
                    String com = InetAddress.getLocalHost().getHostName() + " " + InetAddress.getLocalHost().getHostAddress();


                    String inserter = "insert into audit set host_comp='" + com + "', action='Edited  :" + fname + " " + " " + lname + " details" + "',time='" + current_time + "',actor_id='" + session.getAttribute("userid") + "'";
                    conn.st3.executeUpdate(inserter);
                    session.setAttribute("member_edit", ms);
                }
                System.out.println(num);
                System.out.println(session.getAttribute("stud_edit"));
            }



//if(!group.equals(group_id) && found>0){
//  session.setAttribute("member_edit", "<font color=\"red\">You Cant Change this individual's group. The attendance has already been marked</font>");  
//}
            response.sendRedirect("geMemDetails");

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
            Logger.getLogger(Edit_member_action.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Edit_member_action.class.getName()).log(Level.SEVERE, null, ex);
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
}
