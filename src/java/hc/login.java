/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class login extends HttpServlet {

    String uname, pass, error_login, nextPage, current_time;
    String computername;
    MessageDigest m;
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
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
            session = request.getSession();

//____________________COMPUTER NAME____________________________________
            computername = InetAddress.getLocalHost().getHostName();
            System.out.println("Computer name " + computername);
            session.setAttribute("computer_name", computername);





//current_time=sc+"-"+mn+"-"+hr+"-"+dater+"-"+mnth+"-"+yr;

//current_time=yr+"-"+mnth+"-"+dater+"-"+hr+":"+mn+":"+sc;

            current_time = yr + "-" + mnth + "-" + dater + "-" + hr + ":" + mn + ":" + sc;
            //get username and password

            uname = request.getParameter("uname");

            pass = request.getParameter("pass");






            //encrypt password

            m = MessageDigest.getInstance("MD5");
            m.update(pass.getBytes(), 0, pass.length());
            String pw = new BigInteger(1, m.digest()).toString(16);


            //connection to database class instance
            dbConn conn = new dbConn();


            dbConn1 conn1 = new dbConn1();




            //=========================================================
            String column_name = "";
//      CHECK IF THE COLUMNS EXIST IF NOT CREATE THEM AND LET THEM HAVE A DEFAULT VALUE OF 3.  
            String ch = "SHOW COLUMNS FROM member_details LIKE 'month'";
            conn.rs = conn.st.executeQuery(ch);
            if (conn.rs.next() == true) {
                column_name = conn.rs.getString(1);
            }
            if (column_name.equals("")) {
//    ADD A MONTH COLUMN FOR BOTH CLIENTS AND MONTH




                //define what default value will be added in the 
                String query1 = "ALTER TABLE `member_details` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                conn.st.executeUpdate(query1);

                String query2 = "ALTER TABLE `session` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                conn.st.executeUpdate(query2);



                String query3 = "ALTER TABLE `forms` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                conn.st.executeUpdate(query3);



                String query4 = "ALTER TABLE `new_topic` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                conn.st.executeUpdate(query4);




                String qr1 = "CREATE TABLE IF NOT EXISTS `months` (`monthid` int(11) NOT NULL AUTO_INCREMENT,`month` varchar(200) NOT NULL,`quarter` varchar(200) NOT NULL,PRIMARY KEY (`monthid`)) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ";

                conn.st.executeUpdate(qr1);
conn.rs_5=conn.st_5.executeQuery("select * from months");
                String qr2 = "INSERT INTO `months` (`monthid`, `month`, `quarter`) VALUES(1, 'January', '2'),(2, 'February', '2'),(3, 'March', '2'),(4, 'April', '3'),(5, 'May', '3'),(6, 'June', '3'),(7, 'July', '4'),(8, 'August', '4'),(9, 'September', '4'),(10, 'October', '1'),(11, 'November', '1'),(12, 'December', '1')";
if(!conn.rs_5.next()){
                conn.st.executeUpdate(qr2);
}
            }
            //======================================================================================  


//=========ADD A MONTH to the master db==============================================

            //=========================================================

//      CHECK IF THE COLUMNS EXIST IF NOT CREATE THEM AND LET THEM HAVE A DEFAULT VALUE OF 3.  
            String ch1 = "SHOW COLUMNS FROM member_details LIKE 'month'";
            conn1.rs = conn1.st.executeQuery(ch1);
            if (conn1.rs.next()) {
//do nothing 
            } else {
//    ADD A MONTH COLUMN FOR FORMS,MEMBER_DETAILS,SESSIONS AND NEW_TOPIC TABLES





                String query1 = "ALTER TABLE `member_details` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                conn1.st.executeUpdate(query1);

                String query2 = "ALTER TABLE `session` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                conn1.st.executeUpdate(query2);



                String query3 = "ALTER TABLE `forms` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                conn1.st.executeUpdate(query3);



                String query4 = "ALTER TABLE `new_topic` ADD  `month` VARCHAR( 100 ) NOT NULL DEFAULT '1'";
                conn1.st.executeUpdate(query4);



                String qr1 = "CREATE TABLE IF NOT EXISTS `months` (`monthid` int(11) NOT NULL AUTO_INCREMENT,`month` varchar(200) NOT NULL,`quarter` varchar(200) NOT NULL,PRIMARY KEY (`monthid`)) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ";

                conn1.st.executeUpdate(qr1);

                conn.rs_5=conn.st_5.executeQuery("select * from months");
                
                String qr2 = "INSERT INTO `months` (`monthid`, `month`, `quarter`) VALUES(1, 'January', '2'),(2, 'February', '2'),(3, 'March', '2'),(4, 'April', '3'),(5, 'May', '3'),(6, 'June', '3'),(7, 'July', '4'),(8, 'August', '4'),(9, 'September', '4'),(10, 'October', '1'),(11, 'November', '1'),(12, 'December', '1')";
if(!conn.rs_5.next()){
                conn1.st.executeUpdate(qr2);
}

            }
            //======================================================================================  

//set maximum concurrent connectiosn to be 1500
            
            String maxconn="set @@global.max_connections=1550";
            
            conn.st.executeUpdate(maxconn);


            //query for checking user existance in the database
            String select1 = "select * from users";




//System.out.println("worked up to here 1;");

            conn.rs = conn.st.executeQuery(select1);

            // System.out.println("username:"+uname+"  Password :"+pw );


            while (conn.rs.next()) {
                if (conn.rs.getString("username").equals(uname) && conn.rs.getString("password").equals(pw)) {

                    error_login = "";
                    if (conn.rs.getString("level").equals("0")) {
                        String ip = InetAddress.getLocalHost().getHostAddress();
                        //  System.out.println("level:"+conn.rs.getString("level"));
                        String inserter = "insert into audit set host_comp='" + computername + " " + ip + "' , action='Logged in ',time='" + current_time + "',actor_id='" + conn.rs.getString("userid") + "'";

                        //String inserter="insert into audit  (action,time,actor_id,host_comp) values ('"++"','"+"')";

                        conn.st3.executeUpdate(inserter);
                        //the next page to be opened based on user level
                        nextPage = "newClerk.jsp";


//String fulname=""+conn.rs.getString("firstname") + " "+conn.rs.getString("lastname");
//audit="Insert into audit (Action,User) values('Logged in','"+fulname+"')";



                        //save current user details into a session

                        session.setAttribute("userid", conn.rs.getString("userid"));
                        session.setAttribute("username", conn.rs.getString("username"));
                        session.setAttribute("level", conn.rs.getString("level"));


                        //get teacher details from the teacher registration table 


                        /** code for auditing  */
                        // conn.st.executeUpdate(audit);
                        break;
                    }//end of admin level
                    //****************************Clerk module**********************************************        
                    else if (conn.rs.getString("level").equals("2")) {
                        // System.out.println("level 2");      
                        nextPage = "FormWizard.jsp";



                        session.setAttribute("userid", conn.rs.getString(1));
                        session.setAttribute("username", conn.rs.getString("username"));
                        session.setAttribute("level", conn.rs.getString("level"));
                        //save other session details to dbase

                        String clerk = "select * from clerks";

                        conn.rs = conn.st.executeQuery(clerk);

                        while (conn.rs.next()) {

                            if (conn.rs.getString("clerk_id").equals(session.getAttribute("userid"))) {

                                session.setAttribute("f_name", conn.rs.getString("first_name"));
                                session.setAttribute("s_name", conn.rs.getString("sur_name"));
                                String ip = InetAddress.getLocalHost().getHostAddress();
                                String inserter = "insert into audit set host_comp='" + computername + " " + ip + "' , action='Logged in',time='" + current_time + "',actor_id='" + conn.rs.getString("clerk_id") + "'";
                                conn.st3.executeUpdate(inserter);

                                break;
                            }

                        }
                        error_login = "<b><font color=\"red\">ooops! wrong username and / or password combination</font></b>";

                        break;

                    } //         ^^^^^^^^^^^^^^^^ IF  USER EXIST  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^              
                    else if (conn.rs.getString("level").equals("5")) {
                        nextPage = "guest_home.jsp";



                        session.setAttribute("userid", conn.rs.getString(1));
                        session.setAttribute("username", conn.rs.getString("username"));
                        session.setAttribute("level", conn.rs.getString("level"));
                        //save other session details to dbase

                        String guest = "select * from guest";

                        conn.rs = conn.st.executeQuery(guest);

                        while (conn.rs.next()) {

                            if (conn.rs.getString("user_id").equals(session.getAttribute("userid"))) {
                                session.setAttribute("who", "guest");
                                session.setAttribute("f_name", conn.rs.getString("first_name"));
                                session.setAttribute("s_name", conn.rs.getString("last_name"));
                                session.setAttribute("position", conn.rs.getString("position"));
                                String ip = InetAddress.getLocalHost().getHostAddress();
                                String inserter = "insert into audit set host_comp='" + computername + " " + ip + "' , action='Logged in(guest)',time='" + current_time + "',actor_id='" + conn.rs.getString("user_id") + "'";
                                conn.st3.executeUpdate(inserter);

                                break;
                            }

                        }
                        error_login = "<b><font color=\"red\">ooops! wrong username and / or password combination</font></b>";

                        break;
                    } //****************************wrong username password                        
                    else {

                        nextPage = "index.jsp";

                        System.out.println("third level");

                        error_login = "<b><font color=\"red\">ooops! wrong username and / or password combination</font></b>";

                    }








                }//end of first if
                else {

                    //System.out.println("worked up to here 6;");

                    nextPage = "index.jsp";

                    error_login = "<b><font color=\"red\">wrong username and or password</font></b>";

                    System.out.println(">>" + nextPage);

                }



            }


            session.setAttribute("error_login", error_login);
            response.sendRedirect(nextPage);








        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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

    public void addMonth() {
    }
}
