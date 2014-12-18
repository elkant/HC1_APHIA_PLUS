/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataimport;

import hc.dbConn;
import hc.tempdbConn;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;  
import org.apache.commons.fileupload.disk.DiskFileItemFactory;  
import org.apache.commons.fileupload.servlet.ServletFileUpload;  


/**
 *
 * @author SIXTYFOURBIT
 */
public class importdata extends HttpServlet {
    
    
    dbConn conn=null;
    String dbname,dbuser,dbpassword;
boolean executed=false;
String localhost="";
String[] localhostsplit;
String dbpath="";
String found_folder,full_date,path,computername,senderofmail;
HttpSession session;
String [] myalphabet={"B","C","D","F","G","H","I","J","K","L","M","N","O","Q","R","S","T","U","V","W","X","Y","Z"};

    

    private static final long serialVersionUID = 1L;

    private static final String DATA_DIRECTORY = "HCDATA";
    private static final int MAX_MEMORY_SIZE = 1024 * 1024 * 4;
    //maximum is 20MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024*20;
    
    String filename="";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
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
        try {
            processRequest(request, response);
            
            
            
            
              filename=request.getParameter("filename");
            System.out.println("file name is"+filename);
            
             // Check that we have a file upload request
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (!isMultipart) {
                return;
            }

            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Sets the size threshold beyond which files are written directly to
            // disk.
            factory.setSizeThreshold(MAX_MEMORY_SIZE);

            // Sets the directory used to temporarily store files that are larger
            // than the configured size threshold. We use temporary directory for
            // java
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            // constructs the folder where uploaded file will be stored
            String uploadFolder = getServletContext().getRealPath("")+ File.separator + DATA_DIRECTORY;

            new File(uploadFolder).mkdirs();
            
            
            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Set overall request size constraint
            upload.setSizeMax(MAX_REQUEST_SIZE);

            
                // Parse the request
                List /* FileItem */ items = upload.parseRequest(request);  
                Iterator iter = items.iterator();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();

                    if (!item.isFormField()) {
                  
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadFolder + File.separator + fileName;
                        File uploadedFile = new File(filePath);
                        
                        dbpath=filePath;
                        System.out.println(filePath);
                        // saves the file to upload directory
                        item.write(uploadedFile);
                        
                   
                    }}
        } catch (Exception ex) {
            Logger.getLogger(importdata.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        //======now begin uploading the data into the database
        
        //the the drive of current location
          URL location = MergeData.class.getProtectionDomain().getCodeSource().getLocation();
 String   mydriv = location.getFile().substring(1, 2);  
        



        
        
        session=request.getSession();
         conn = new dbConn();
        
         
         createtables();
        //====call the method that creates the tables at this stage....
        
        //this database name should be static to avoid replicationg the master database
        dbname="temphc";
        dbuser="root";
        dbpassword="";

        found_folder="";



    
    if(1==1){
//         
          String  executeCmd="";  
String[] executeCmd1=null;
        
             if(conn.dbsetup[3]!=null){
             dbpassword=conn.dbsetup[3];
                 
             
             }
             
             
             
             if(conn.dbsetup[2]!=null){
                 
             dbuser=conn.dbsetup[2];
                 
             
             }
             
             
             
//             if(conn.dbsetup[1]!=null){
//                 
//             dbname=conn.dbsetup[1];                 
//             
//             }
             
                if (conn.dbsetup[0] != null) {

                localhost = conn.dbsetup[0];
                localhostsplit = localhost.split(":");

            }
             
             
             System.out.println("PASSWORD IS :"+dbpassword);
             System.out.println("USER IS :"+dbuser);
             System.out.println("DBNAME IS :"+dbname);
             System.out.println("HOST :"+localhost);
             System.out.println("PORT :"+localhostsplit[1]);
  
             
                for (int i=0;i<myalphabet.length;i++){
                    System.out.println("at position  :  "+myalphabet[i]);
              String current_drive=myalphabet[i];
              File f =  new File(current_drive+":\\wamp\\mysql\\bin\\");
              File f1 = new File(current_drive+":\\wamp\\bin\\mysql\\mysql5.6.12\\bin");
              File f2 = new File(current_drive+":\\Program Files\\MySQL\\MySQL Server 5.5\\bin");
             

        
  
// CHECK WHICH PROGRAM IS INSTALLED TO ENSURE THAT DATA IS BACKED UP SUCCESSFULLY.             
              
if (f.exists() && f.isDirectory()){

executeCmd1 = new String[]{current_drive+":\\wamp\\mysql\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+dbpath};  


System.out.println(executeCmd);
found_folder="it is old wamp";
}
if (f1.exists() && f1.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\wamp\\bin\\mysql\\mysql5.6.12\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname,"-e", " source "+dbpath};  


System.out.println(executeCmd);

found_folder="it is new wamp";
}
 if (f2.exists() && f2.isDirectory()){

 executeCmd1 = new String[]{current_drive+":\\Program Files\\MySQL\\MySQL Server 5.5\\bin\\mysql","--host=" + localhostsplit[0], "--port=" + localhostsplit[1], "--user=" + dbuser, "--password=" + dbpassword, dbname, "-e", " source "+dbpath};  


found_folder="it is workbench";
System.out.println(executeCmd);
}
 
 
}      
             
             
             
             
System.out.println(found_folder);

Process runtimeProcess;
        try {
 System.out.println("trying to import the data.");
 for(int a=0;a<executeCmd1.length;a++){
 System.out.println(""+executeCmd1[a]);
 
 }
 
 //if the selected database is biometric_master_db,do not import a database since 
 
 
runtimeProcess = Runtime.getRuntime().exec(executeCmd1);



System.out.println("process started");
             int processComplete = runtimeProcess.waitFor();
            System.out.println("at 1 is "+processComplete);
            if (processComplete == 0) {

                System.out.println("Import completed successfully");
           
                
                session.setAttribute("datasend1", "<font color=\"green\">Data has been imported successfully</font>");
               
              
				 
            } else {
                System.out.println("Could not import data");
	session.setAttribute("datasend1", "Data not imported");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    
    response.sendRedirect("MergeData");
        
    }//end of dopost

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
  public void createtables(){  
        try {
            //create connection
              
              tempdbConn conn1= new tempdbConn();
              
            System.out.println("CREATE TABLE CALLED___");  
              conn1.st.executeUpdate("CREATE DATABASE IF NOT EXISTS `temphc`");
              
              conn1.st.executeUpdate("DROP TABLE IF EXISTS `facilitator_details`");
              
              
  conn1.st.executeUpdate("CREATE TABLE IF NOT EXISTS `facilitator_details` ("
  +"`facilitator_id` varchar(200) NOT NULL,"
  +"`first_name` varchar(50) NOT NULL,"
  +"`sur_name` varchar(50) NOT NULL,"
  +"`phone` varchar(50) NOT NULL,"
  +"`partner_id` int(50) NOT NULL,"
  +"`groups_id` varchar(1000) NOT NULL,"
  +"`middle_name` varchar(50) NOT NULL,"
  +"`timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,"
  +"PRIMARY KEY  (`facilitator_id`)"
  +") ENGINE=InnoDB DEFAULT CHARSET=latin1");
             
   //table two//           
   conn1.st.executeUpdate("DROP TABLE IF EXISTS `forms`");           
            
   conn1.st.executeUpdate("CREATE TABLE IF NOT EXISTS `forms` ("
  +"`form_id` varchar(200) NOT NULL,"
  +"`form_number` varchar(100) NOT NULL,"
  +"`year` varchar(10) NOT NULL,"
  +"`period` varchar(10) NOT NULL,"
  +"`timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,"
//  +"`month` varchar(100) NOT NULL,"
  +"PRIMARY KEY  (`form_id`)"
+") ENGINE=InnoDB DEFAULT CHARSET=utf8");           
              
          
   
    conn1.st.executeUpdate("DROP TABLE IF EXISTS `groups`"); 
    
    conn1.st.executeUpdate("CREATE TABLE IF NOT EXISTS `groups` ("
  +"`group_id` varchar(200) NOT NULL,"
  +"`group_name` varchar(100) NOT NULL,"
  +"`partner_id` int(50) NOT NULL,"
  +"`target_pop_id` int(50) NOT NULL,"
  +"`district_id` int(50) NOT NULL default '0',"
  +"`timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,"
  +"PRIMARY KEY  (`group_id`),"
  +"KEY `target_pop_id` (`target_pop_id`),"
  +"KEY `district_id` (`partner_id`),"
  +"KEY `partner_id` (`partner_id`)"
+") ENGINE=InnoDB DEFAULT CHARSET=latin1"); 
   
 //===========================================================TABLE    
    
    conn1.st.executeUpdate("DROP TABLE IF  EXISTS `member_details`"); 
    
    conn1.st.executeUpdate("CREATE TABLE IF NOT EXISTS `member_details` ("
 +" `member_id` varchar(200) NOT NULL,"
  +"`first_name` varchar(50) NOT NULL,"
  +"`mname` text NOT NULL,"
  +"`sur_name` varchar(50) NOT NULL,"
  +"`age` int(50) NOT NULL,"
  +"`sex` text NOT NULL,"
  +"`group_id` varchar(200) NOT NULL,"
  +"`sessions_attended` int(11) default '0',"
  +"`year` int(10) NOT NULL default '0',"
  +"`period` int(10) NOT NULL default '0',"
  +"`timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,"
//  +"`month` varchar(100) NOT NULL,"
  +"PRIMARY KEY  (`member_id`),"
  +"KEY `group_id` (`group_id`),"
  +"KEY `sur_name` (`sur_name`),"
  +"KEY `sessions_attended` (`sessions_attended`),"
  +"KEY `year` (`year`),"
  +"KEY `period` (`period`)"
+") ENGINE=InnoDB DEFAULT CHARSET=latin1"); 
    
   
       conn1.st.executeUpdate("DROP TABLE IF EXISTS `new_topic`");
       
       
       conn1.st.executeUpdate("CREATE TABLE IF NOT EXISTS `new_topic` ("
  +"`new_topic_id` varchar(200) NOT NULL,"
  +"`topic_id` varchar(2000) NOT NULL,"
  +"`expected_sessions` varchar(50) NOT NULL,"
  +"`start_date` varchar(50) NOT NULL,"
  +"`end_date` varchar(50) NOT NULL,"
  +"`marking_status` varchar(50) NOT NULL,"
  +"`facilitator_id` varchar(200) NOT NULL,"
  +"`year` varchar(45) default NULL,"
  +"`marking_date` text NOT NULL,"
  +"`year2` varchar(50) NOT NULL,"
  +"`period` varchar(50) NOT NULL,"
  +"`timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,"
//  +"`month` varchar(100) NOT NULL,"
  +"PRIMARY KEY  (`new_topic_id`),"
  +"KEY `facil` (`facilitator_id`)"
+") ENGINE=InnoDB DEFAULT CHARSET=latin1;");
    
       
       
       
       
       
         conn1.st.executeUpdate("DROP TABLE IF EXISTS `register_attendance`");
         
         conn1.st.executeUpdate("CREATE TABLE IF NOT EXISTS `register_attendance` ("
  +"`register_id` varchar(200) NOT NULL,"
  +"`member_id` varchar(200) NOT NULL,"
  +"`session_id` varchar(1000) NOT NULL,"
  +"`facilitator_id` varchar(200) NOT NULL,"
  +"`image_path` varchar(45) NOT NULL,"
  +"`end_date` varchar(45) NOT NULL,"
  +"`availability` int(45) NOT NULL,"
  +"`marked_date` text NOT NULL,"
  +"`form_id` varchar(1000) NOT NULL,"
  +"`submission_date` text NOT NULL,"
  +"`reviewer_name` text NOT NULL,"
  +"`timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,"
  +"`order_id` int(11) NOT NULL auto_increment,"
  +"PRIMARY KEY  (`register_id`),"
  +"UNIQUE KEY `order_id` (`order_id`),"
  +"KEY `member_id` (`member_id`),"
  +"KEY `facilitator_id` (`facilitator_id`),"
  +"KEY `availability` (`availability`),"
  +"KEY `form_id` (`form_id`(767))"
+") ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;");
         
  
          conn1.st.executeUpdate("DROP TABLE IF EXISTS `session`");
          
          
          conn1.st.executeUpdate("CREATE TABLE IF NOT EXISTS `session` ("
  +"`session_id` varchar(700) NOT NULL,"
  +"`date` varchar(50) NOT NULL,"
  +"`duration` varchar(50) NOT NULL,"
  +"`male_cds` varchar(50) NOT NULL,"
  +"`female_cds` varchar(50) NOT NULL,"
  +"`topic_id` varchar(500) NOT NULL,"
  +"`group_id` varchar(200) NOT NULL,"
  +"`method_id` text NOT NULL,"
  +"`end_date` varchar(50) NOT NULL,"
  +"`marked_date` text NOT NULL,"
  +"`marking_status` text NOT NULL,"
  +"`year` varchar(50) NOT NULL,"
  +"`period` varchar(50) NOT NULL,"
  +"`timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,"
//  +"`month` varchar(100) NOT NULL,"
  +"PRIMARY KEY  (`session_id`),"
  +"KEY `group_id` (`group_id`)"
  +") ENGINE=InnoDB DEFAULT CHARSET=latin1");
         
          
          
            conn1.st.executeUpdate("DROP TABLE IF EXISTS `topics`");
            conn1.st.executeUpdate("CREATE TABLE IF NOT EXISTS `topics` ("
 +" `topic_id` varchar(200) NOT NULL,"
  +"`topic_name` varchar(500) NOT NULL,"
  +"`curriculum_id` int(50) NOT NULL,"
  +"`timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,"
  +"PRIMARY KEY  (`topic_id`),"
  +"KEY `curriculum_id` (`curriculum_id`)"
+") ENGINE=InnoDB DEFAULT CHARSET=latin1");
          
    
            
            
      conn1.st.executeUpdate("DROP TABLE IF EXISTS `transfer_facilitator`");  
      
      conn1.st.executeUpdate("CREATE TABLE IF NOT EXISTS `transfer_facilitator` ("
  +"`tf_id` varchar(200) NOT NULL,"
  +"`facilitator_id` varchar(200) NOT NULL,"
  +"`partner_id` int(50) NOT NULL,"
  +"`groups_ids` varchar(1000) NOT NULL,"
  +"`timestamp` timestamp NOT NULL default CURRENT_TIMESTAMP,"
  +"PRIMARY KEY  (`tf_id`)"
+") ENGINE=InnoDB DEFAULT CHARSET=latin1");      
            
            
        } catch (SQLException ex) {
            Logger.getLogger(importdata.class.getName()).log(Level.SEVERE, null, ex);
        }
      
      
      
    
  }
    
    
}
