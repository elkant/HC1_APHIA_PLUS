/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import hc.dbConn;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 *
 * @author SIXTYFOURBIT
 */
public class db_backup implements Job{

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            URL location = db_backup.class.getProtectionDomain().getCodeSource().getLocation();
             String  mydrive = location.getFile().substring(1, 2);
             
             String password="";
             String user="";
             String dbname="";   
             String storagepath=mydrive+"MHC_UPLOADS/DO_NOT_DELETE/";
             
             dbConn conn= new dbConn();
             
             
             if(conn.dbsetup[3]!=null){
             password=conn.dbsetup[3];
                 
             
             }
             
             
             
             if(conn.dbsetup[2]!=null){
                 
             user=conn.dbsetup[2];
                 
             
             }
             
             
             
             if(conn.dbsetup[1]!=null){
                 
             dbname=conn.dbsetup[1];                 
             
             }
             
System.out.println("PASSWORD IS :"+password);
             System.out.println("USER IS :"+user);
             System.out.println("DBNAME IS :"+dbname);
             
             Calendar cal = Calendar.getInstance();

     int   year = cal.get(Calendar.YEAR);
      int  month = cal.get(Calendar.MONTH) + 1;
      int  date = cal.get(Calendar.DAY_OF_MONTH);
      int  hour = cal.get(Calendar.HOUR_OF_DAY);
      int  min = cal.get(Calendar.MINUTE);
      int  sec = cal.get(Calendar.SECOND);
             
      String dat=year+"_"+month+"_"+date+"_"+hour+"_"+min+"_"+sec;
             
      //String prepa="set path=M:\\wamp\\bin\\mysql\\mysql5.1.36\\bin";       
      
               String command="mysqldump.exe -u"+user+" -p"+password+" --add-drop-database "+dbname+" -r "+"M:/"+dbname+".sql";
              
               System.out.println(command);
               
               
               String command2=mydrive+":/wamp/bin/mysql/mysql5.6.12/bin mysqldump -u "+user+" -p "+password+" "+storagepath+"  "+dbname+"_"+dat+".sql";
               String command3=mydrive+":/wamp/bin/mysql/bin mysqldump -u "+user+" -p "+password+" "+storagepath+"  "+dbname+"_"+dat+".sql";
            // Process prs=  Runtime.getRuntime().exec(prepa);
               Process prs =Runtime.getRuntime().exec(command);
                int processComplete = prs.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Backup created successfully");
                
            } else {
                System.out.println("Could not create the backup");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(db_backup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.println("ERROR WHILE PERFORMING DATA BACKUP   : "+ex);
            Logger.getLogger(db_backup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
