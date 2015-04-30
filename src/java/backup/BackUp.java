/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package backup;

import hc.dbConn;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class BackUp extends HttpServlet {

    String dbname, dbuser, dbpassword;
    String localhost = "", localhostsplit[];
    boolean executed = false;
    String dbpath, dbpathD, dbpathE, dbpathF, dbpathG, dbpathM;
    String found_folder, full_date, path, computername, senderofmail;
    HttpSession session;
    String[] myalphabet = {"B", "C", "D", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String filname = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            session = request.getSession();


            filname = "";

            dbConn conn = new dbConn();
            Date dat = new Date();
            String date = dat.toString().replace(" ", "_");

            String lasttimestampid = "1";
            String lasttimestamp = "2014-03-12 11:25:20";




            dbname = "hc";
            dbuser = "root";
            dbpassword = "";
            String nextpage = "";
            found_folder = "";

            
            
            
            
            
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
            
            
            
            
            
            
            
            

               String getversion=" select version_name,date from version";
                      String versionno="";
                      conn.rs=conn.st.executeQuery(getversion);
                      
                      while(conn.rs.next()){
                      //get the part of version with version
                          int begpos=conn.rs.getString(1).trim().indexOf("version");
                       //get the version number   
                      versionno=conn.rs.getString(1)+""+conn.rs.getString(2);
                          
                      }
                      
            

    //MAKE A DIRECTORY TO STORE THE BACK_UP FILE.
    //        GET CURRENT DATE:
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DATE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);

            full_date = "Created_On_" + year + "_" + month + "_" + day + "_" + hour + "_" + min + "_" + sec;
            String full_dates = day + " / " + month + " / " + year + "  :and the exact time is  " + hour + ":" + min + ":" + sec;
            URL location = dbConn.class.getProtectionDomain().getCodeSource().getLocation();
            if (session.getAttribute("username") != null) {

                filname = session.getAttribute("username").toString() + "_";

                senderofmail = " which has been send by user :: " + session.getAttribute("username").toString() + " ";

            } else {
                senderofmail = "";
            }

            filname += versionno + "_";
            filname += date + "_";


            computername = InetAddress.getLocalHost().getHostName();
            if (1 == 1) {
    //         
                String executeCmd = "";


                if (conn.dbsetup[3] != null) {
                    dbpassword = conn.dbsetup[3];


                }



                if (conn.dbsetup[2] != null) {

                    dbuser = conn.dbsetup[2];


                }



                if (conn.dbsetup[1] != null) {

                    dbname = conn.dbsetup[1];

                }



                if (conn.dbsetup[0] != null) {

                    localhost = conn.dbsetup[0];
                    localhostsplit = localhost.split(":");

                }


                System.out.println("PASSWORD IS :" + dbpassword);
                System.out.println("USER IS :" + dbuser);
                System.out.println("DBNAME IS :" + dbname);


                for (int i = 0; i < myalphabet.length; i++) {
                    try {
                        System.out.println("at position  :  " + myalphabet[i]);
                        String current_drive = myalphabet[i];
                        File f = new File(current_drive + ":\\wamp\\mysql\\bin\\");
                        File f1 = new File(current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin");
                        File f2 = new File(current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin");
                        File f3 = new File(current_drive + ":\\APHIAPLUS\\HC_SYSTEM");

                        //     CREATE A DIRECTORY AND THE FILE TO HOLD DATA
                        if (f3.exists() && f3.isDirectory()) {
                            path = current_drive + ":\\APHIAPLUS\\HC_SYSTEM\\DATABACKUP\\SENDBACKUP";
                            new File(path).mkdirs();
                            dbpath = path + "\\" + full_date + "_Hc.sql";
                        }

                        //select the last timestamp which a backup was picked from.....



                        conn.rs_6 = conn.st_6.executeQuery("select MAX(timestampid) from timestamp");
                        if (conn.rs_6.next()) {


                            conn.rs_5 = conn.st_5.executeQuery("select timestamp from timestamp where timestampid='" + conn.rs_6.getString(1) + "'");

                            if (conn.rs_5.next()) {
                                lasttimestamp = conn.rs_5.getString("timestamp");

                                System.out.println(conn.rs_5.getString("timestamp"));

                            }


                            System.out.println("Timestamp id::" + conn.rs_6.getString(1));


                            lasttimestampid = conn.rs_6.getString(1);

                        }

                        //conn.st_6.close();


    // CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             

                        if (f.exists() && f.isDirectory()) {
                            executeCmd = current_drive + ":\\wamp\\mysql\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " facilitator_details transfer_facilitator forms groups member_details new_topic register_attendance session topics deleted_register_attendance deleted_member_details --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
    //executeCmd = "C:\\wamp3\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table --host=localhost --user="+dbuser+" --password="+dbpassword+" "+dbname+" audit enrollment childage clientmember clientoccupation clientoparea dummy medical_form riskassessmentdetails riskassessmentmain riskreductionmain riskreductiondetails user taskauditor --where=timestamp>='"+startdate+"' -r "+dbpath+"";

                            found_folder = "it is old wamp";
                        }
                        if (f1.exists() && f1.isDirectory()) {


                            executeCmd = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " facilitator_details transfer_facilitator forms groups member_details new_topic register_attendance session topics deleted_register_attendance deleted_member_details --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
                            found_folder = "it is new wamp";
                        }
                        if (f2.exists() && f2.isDirectory()) {
                            executeCmd = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + " facilitator_details transfer_facilitator forms groups member_details new_topic register_attendance session topics deleted_register_attendance deleted_member_details --where=timestamp>='" + lasttimestamp + "' -r " + dbpath + "";
                            found_folder = "it is workbench";
                        }
                    } catch (SQLException ex) {


                        session.setAttribute("datasend", "<font color=\"red\">an SQL Error occured while sending data</font>");

                        Logger.getLogger(BackUp.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }




                System.out.println(found_folder);
                System.out.println(executeCmd);
                Process runtimeProcess;
                try {
                    System.out.println("trying to back up the data.");
                    runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                    int processComplete = runtimeProcess.waitFor();
                    System.out.println("at 1 is " + processComplete);
                    if (processComplete == 0) {
    //                SEND TO THE MAIL BACKED UP DATA.
                        System.out.println("Backup created successfully");



                        mailtom_and_e me = new mailtom_and_e();


                        if (isInternetReachable()) {  //if there is internet connection


                            //============at this point, if the data is send, then do a new timestamp into the database
                            if (me.Sendattachment(full_dates, dbpath, computername, senderofmail, filname) == true) {


                                //do an insert
                                conn.st.executeUpdate("update timestamp set datasend='yes' where timestampid='" + lasttimestampid + "'");



                                Format formatter = new SimpleDateFormat("yyyy-MM-dd");
                                String mdate;

                                Date mydate = new Date();
                                mdate = formatter.format(mydate);


                                String daytime = "" + year + "-" + month + "-" + day;
                                // a new timestamp that will be called next time a backup is being created.

                                conn.st.executeUpdate("insert into timestamp (timestamp,datasend) values('" + mdate + "','No')");


                                session.setAttribute("datasend", "<font color=\"green\">Backup Created  successfully</font>");
                            }//end of if..
                            else {

                                 session.setAttribute("datasend", "<font color=\"yellow\">Backup Created but not send via mail</font>");

                                System.out.println("Data not send via mail");

                            }

                        }//if internet connection is available,
                        else {
    session.setAttribute("datasend", "<font color=\"red\">Backup Created but NOT send via email</font>");
                        }


                         session.setAttribute("datasend", "<font color=\"green\">Backup has been created Successfully</font>");

                    }//end of if a bacup has been created
                    else {
                        System.out.println("Could not create the backup");
                        session.setAttribute("datasend", "Backup Could <font color=\"red\">NOT</font> be created");
                    }
                } catch (Exception ex) {

                    System.out.println(ex);
                }


            }

    //
    //        File f = new File(dbpath);
    //        FileInputStream fileInputStream = new FileInputStream(f);
    //        ByteArrayOutputStream bos = new ByteArrayOutputStream();
    //        byte[] buf = new byte[1024];
    //        try {
    //            for (int readNum; (readNum = fileInputStream.read(buf)) != -1;) {
    //                bos.write(buf, 0, readNum);
    //            }
    //        } catch (IOException ex) {
    //            System.out.println(ex);
    //        }
    //        byte[] outArray = bos.toByteArray();
    //
    //        response.setContentType("application/sql");
    //        response.setContentLength(outArray.length);
    //        response.setHeader("Expires:", "0"); // eliminates browser caching
    //        response.setHeader("Content-Disposition", "attachment; filename=backup_" + date + ".sql");
    //
    //        OutputStream outStream = response.getOutputStream();
    //        outStream.write(outArray);
    //        outStream.flush();
    //
    //        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/localbackup.jsp");
    //        dispatcher.forward(request, response);

             response.sendRedirect("localbackup.jsp");
        }
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        catch (SQLException ex) {
            Logger.getLogger(BackUp.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static boolean isInternetReachable() {
        try {
            //make a URL to a known source
            URL url = new URL("http://www.google.com");

            //open a connection to that source
            URLConnection urlConnect = url.openConnection();

            //trying to retrieve data from the source. If there
            //is no connection, this line will fail
            urlConnect.getInputStream();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            System.out.println("Unknown host");
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println(":Error in internet connection");
            return false;
        }
        return true;
    }
}
