/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backup;

import hc.IdGenerator;
import hc.dbConn;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class AutoBackup extends HttpServlet {
HttpSession session;
String today,path,ExistingPath,filePath,lastBackUp;
int foundClients,foundRegister;
dbConn conn=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        session=request.getSession();
         conn = new dbConn();
        
           //CREATE A PATH IN THE COMPUTER FOR STORING TEXT FILES
           IdGenerator IG = new IdGenerator();
           today=IG.toDay();
           foundClients=foundRegister=0;
    String allpath = getServletContext().getRealPath("/dbase.txt");
        String mydrive = allpath.substring(0, 1);
        //dbconnpath=mydrive+":\\MNHC_SYSTEM_APHIA_PLUS\\"; 
  path=mydrive+":\\APHIAPLUS\\HCDBCONNECTION\\DO_NOT_DELETE\\_\\_\\."; 
  ExistingPath=mydrive+":\\APHIAPLUS\\HCDBCONNECTION\\DO_NOT_DELETE\\_\\_\\LastBackUp.txt";     
           File f = new File(ExistingPath);
           if(f.isFile() && !f.isDirectory()){
           System.out.println("The file exist");
           
          
            String fpath = mydrive+ ":/APHIAPLUS/HCDBCONNECTION/DO_NOT_DELETE/_/_/./LastBackUp.txt";
  
            FileInputStream fstream = new FileInputStream(fpath);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String stLine;
            //Read File Line By Line
            int count = 0;
            if((stLine = br.readLine()) != null) {
            lastBackUp=stLine;
            System.out.println("last date is : "+lastBackUp);
//            Check if date has gone past 1 day and data exist for back up...............
          if(!today.equals(lastBackUp)){
//             CHEK IF DATA HAS BEEN ENTERED=================================
              String checkClients="SELECT COUNT(member_id) FROM member_details WHERE STR_TO_DATE(timestamp,'%Y-%m-%d')"
                      + "BETWEEN STR_TO_DATE('"+lastBackUp+"','%Y-%m-%d') AND STR_TO_DATE('"+today+"','%Y-%m-%d') ";
             System.out.println(checkClients);
              conn.rs=conn.st.executeQuery(checkClients);
              if(conn.rs.next()==true){
                  foundClients=conn.rs.getInt(1);
              }
             
            if(foundClients==0){
                //             CHEK IF DATA HAS BEEN ENTERED=================================
              String checkRegister="SELECT COUNT(register_id) FROM register_attendance WHERE STR_TO_DATE(timestamp,'%Y-%m-%d')"
                      + " BETWEEN STR_TO_DATE('"+lastBackUp+"','%Y-%m-%d') AND STR_TO_DATE('"+today+"','%Y-%m-%d')";
              
              System.out.println(checkRegister);
              
              conn.rs=conn.st.executeQuery(checkRegister);
              if(conn.rs.next()==true){
                  foundRegister=conn.rs.getInt(1);
              }
            } 
          System.out.println("found members :   "+foundClients+"     found register   :  "+foundRegister);
          if(foundClients>0 || foundRegister>0){
              System.out.println("===================YOUR DATA WILL BE BACKED UP==================");
            PrintWriter out1 = response.getWriter();    
         out1.println("Backup created");     
           try {
   
    } finally {            
        out1.close();
    }    
              
              
   CreateBackup(); 
    
    
    
    
    
//    UPDATE LAST BACK UP DATE------------------------------------
   filePath =path+"\\LastBackUp.txt";
             try {      
FileWriter outFile = new FileWriter(filePath ,false);
PrintWriter out = new PrintWriter(outFile);

out.print(today);
out.close();
 } catch (IOException e) {} 
    
          }  
          else{
              System.out.println("============no data to back up======================");
         
          }  
          }
          else{
              System.out.println("today's back up has already been created==============");
          }
            
            }
            in.close(); 
           }
           
           else{
               System.out.println("File not found");
              new File(path).mkdirs();
             filePath =path+"\\LastBackUp.txt";
             try {      
FileWriter outFile = new FileWriter(filePath ,false);
PrintWriter out = new PrintWriter(outFile);

out.print(today);
out.close();

   } catch (IOException e) {}
             
    CreateBackup();          
    }

        
//

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
        Logger.getLogger(AutoBackup.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
        Logger.getLogger(AutoBackup.class.getName()).log(Level.SEVERE, null, ex);
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

    
    
    
    public  void CreateBackup(){
    
    
         Date dat = new Date();
            String date = dat.toString().replace(" ", "_");
        
     //MAKE A DIRECTORY TO STORE THE BACK_UP FILE.
    //        GET CURRENT DATE:
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DATE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);

          String  full_date = "Full_backup_Created_On_" + year + "_" + month + "_" + day + "_" + hour + "_" + min + "_" + sec;
            String full_dates = day + " / " + month + " / " + year + "  :and the exact time is  " + hour + ":" + min + ":" + sec;
              String filname = "HC_AUTOBACKUP_";
    filname += date + "_";
        String found_folder1="";
      String dbpathD, dbpathE, dbpathF, dbpathG, dbpathM;
      String dbname="";
      String dbuser="";
      String dbpassword="";
      String localhost = "", localhostsplit[] = null;
       String dbpath="";
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
  String[] myalphabet = {"B", "C", "D", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
   

                for (int i = 0; i < myalphabet.length; i++) {
                 
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



                      

                        //conn.st_6.close();


    // CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             

                        if (f.exists() && f.isDirectory()) {
                            executeCmd = current_drive + ":\\wamp\\mysql\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + "   -r " + dbpath + "";
    //executeCmd = "C:\\wamp3\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump --no-create-info --skip-add-drop-table --host=localhost --user="+dbuser+" --password="+dbpassword+" "+dbname+" audit enrollment childage clientmember clientoccupation clientoparea dummy medical_form riskassessmentdetails riskassessmentmain riskreductionmain riskreductiondetails user taskauditor --where=timestamp>='"+startdate+"' -r "+dbpath+"";

                            found_folder1 = "it is old wamp";
                        }
                        if (f1.exists() && f1.isDirectory()) {


                            executeCmd = current_drive + ":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + "  -r " + dbpath + "";
                            found_folder1 = "it is new wamp";
                        }
                        if (f2.exists() && f2.isDirectory()) {
                            executeCmd = current_drive + ":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysqldump  --host=" + localhostsplit[0] + " --port=" + localhostsplit[1] + " --user=" + dbuser + " --password=" + dbpassword + " " + dbname + "  -r " + dbpath + "";
                            found_folder1 = "it is workbench";
                        }
                   
                }




                System.out.println(found_folder1);
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



                  

                    }//end of if a bacup has been created
                    else {
                        System.out.println("Could not create the backup");
                        session.setAttribute("datasend", "Backup Could <font color=\"red\">NOT</font> be created");
                    }
                } catch (Exception ex) {

                    System.out.println(ex);
                }


            }
    
    
    
    
    
    }
    
    
    
}
