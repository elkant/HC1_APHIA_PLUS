/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SCRIPTS;

import hc.dbConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Manuel
 */
public class PULLDB extends HttpServlet {

    
      private static final long serialVersionUID = 1L;
    private static final String DATA_DIRECTORY = "HCDATA";
    private static final int MAX_MEMORY_SIZE = 1024 * 2;
    private static final int MAX_REQUEST_SIZE = 2048 * 2048 * 10;
   
     String dbpath = "";
    HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    session=request.getSession();
    response.sendRedirect("uploadhclivedata.jsp");
    
    
     
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
        try {
            processRequest(request, response);
            
            
            
            
            
            
            
         //=======================================================================================   

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
                String uploadFolder = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;

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

                        dbpath = filePath;
                        System.out.println(filePath);
                        // saves the file to upload directory
                        item.write(uploadedFile);


                    }
                }



    //======now begin uploading the data into the database

    //the the drive of current location
                URL location = PULLDB.class.getProtectionDomain().getCodeSource().getLocation();
                String mydriv = location.getFile().substring(1, 2);





                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                dbConn conn = new dbConn();
                ArrayList cells = new ArrayList();

                ArrayList allcells = new ArrayList();


                String itemName = "";
              

                itemName = request.getParameter("fname");
    //____________________GET COMPUTER NAME____________________________________
                String computername = InetAddress.getLocalHost().getHostName();
    //System.out.println("Computer name "+computername);










    //create a directory if not exists

    //FileInputStream inputFile = new FileInputStream("//Users//suk//Documents/tes//testexcel.xlsx");

    //now initializing the Workbook with this inputFie

    System.out.println("dbpath::"+dbpath);
                FileInputStream inputFile = new FileInputStream(dbpath);
    //FileInputStream inputFile = new FileInputStream("//Users//suk//Documents/tes//testexcel.xlsx");

    //now initializing the Workbook with this inputFie


    // Create workbook using WorkbookFactory util method

                Workbook wb = WorkbookFactory.create(inputFile);

    // creating helper for writing cells

                CreationHelper createHelper = wb.getCreationHelper();

    // setting the workbook to handle null

                wb.setMissingCellPolicy(Row.CREATE_NULL_AS_BLANK);


                Sheet sheet = wb.getSheetAt(0);

int count=0;
int numberofgroupsunadded=0;

                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
    //For each row, iterate through all the columns
    //                Iterator<Cell> cellIterator = row.cellIterator();
                    if (row.getRowNum() == 0) {
                        continue; //just skip the rows if row number is 0
                    }
                    if (cells.size() > 0 && cells != null) {
                        cells.clear();
                    }
                    if (allcells.size() > 0 && allcells != null) {
                        allcells.clear();
                    }
    //                 String value="";

                    int lastCellNo = row.getLastCellNum();
                    int firstCellNo = row.getFirstCellNum();

                    int rowNo = row.getRowNum();
 
//rem to change the 8 since am forcing it to accept the situation

                    for (int i = 0; i < 31; i++) {
   
                        Cell cell = row.getCell(i);
                        int colIndex = cell.getColumnIndex();
                        if (cell == null || getCellValue(row.getCell(i)).trim().isEmpty()) {
                            cell.setCellValue("");
                            
                            
    //   System.out.println(" The Cell:"+colIndex+" for row "+row.getRowNum()+" is NULL");
                            //cells.add(cell.getStringCellValue());

                            //System.out.println("NULL CELLS    "+cell.getRichStringCellValue());
                        }

                        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                            String stringvalues = cell.getStringCellValue();
                            cells.add(stringvalues);
         //   System.out.println("STRING CELLS  "+stringvalues);

                        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

                            if (DateUtil.isCellDateFormatted(cell)) {
                                HSSFDataFormatter formatter;
                                formatter = new HSSFDataFormatter();
                                String temp = formatter.formatCellValue(cell);
        //System.out.println("DATE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+temp);
                                cells.add(temp);
                            } else {
                                HSSFDataFormatter formatter;
                                formatter = new HSSFDataFormatter();
                                String value = formatter.formatCellValue(cell);
    // int value=(int)cell.getNumericCellValue();
                                cells.add(value);
    // System.out.println("NUMERIC CELLS "+value);
                            }





                        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                            cells.add(cell.getBooleanCellValue());
  //System.out.println("BOOLEAN CELLS"+cell.getBooleanCellValue());
                        }


    //   System.out.println(" column  index  = "+colIndex);


                        int cellType = cell.getCellType();

   // System.out.println(" cell type ="+cellType);
                        allcells.add(cells);
    //System.out.println("cells___________"+cells.get(i).toString());




                    }
                    allcells.add(cells);

    //                      allcells2.add(allcells);
                   // System.out.println("cells___________" + allcells.get(0).toString());
   // System.out.println("COUNTY___"+cells.get(4).toString()+"___Partner___"+cells.get(0).toString()+" District___"+cells.get(1).toString()+"Target Population__"+cells.get(2).toString()+"_GROUP______"+cells.get(3).toString());

 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                       //county
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
 //888county is only used for filtering the districts but does not appear anywhere.   
//   String getcounty="select county_id from county where county_name LIKE '"+cells.get(4).toString()+"'";
//
//conn.rs=conn.st.executeQuery(getcounty);
//
//while(conn.rs.next()){
//
//  
//}
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                       //Districts
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


//===initialize district id name to be used
String distid="";

   //===============check whether all the districts have been added
String checkdistrict="select * from district where district_name LIKE '"+cells.get(2).toString()+"'";
    
 conn.rs1=conn.st1.executeQuery(checkdistrict);
 
 if(conn.rs1.next()){
 
    distid=conn.rs1.getString("district_id");
     
 }
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                       //target population
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //check whether a target population name is added if yes, get the target id
 
 String targetid="";
 
 String checktarget="select * from target_population where population_name LIKE '%"+cells.get(3).toString()+"%'";
  
// System.out.println(checktarget);
 
 conn.rs1=conn.st1.executeQuery(checktarget);
 
 if(conn.rs1.next()){ 
     
    targetid=conn.rs1.getString("target_id");
     
 }
 
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                       //load partners
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
 String partner_id="";
 
 
 String getpartnerid="select partner_id from partner where partner_name LIKE ?";
 
 conn.pst2=conn.conne.prepareStatement(getpartnerid);
 conn.pst2.setString(1, cells.get(1).toString());
 
 conn.rs1=conn.pst2.executeQuery();
 
 if(conn.rs1.next()){
 
 partner_id=conn.rs1.getString(1);
 
 }
 
 
 
 
 
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                                       //groups
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //check whether a target population name is added if yes, get the target id
 
 String group_id="";
 
 String checkgroup="select * from groups where group_name LIKE ?";
 
 
 
 conn.pst=conn.conne.prepareStatement(checkgroup);
   conn.pst.setString(1, "%"+cells.get(4).toString()+"%");
 conn.rs1=conn.pst.executeQuery();
   count++;
 System.out.println(checkgroup+"__"+count);


 
 
 if(conn.rs1.next()){ 
    //get the group id  
     
   group_id=conn.rs1.getString("group_id");
   
   
 }
 else {


    group_id=uniqueid().toString().trim();
     
  numberofgroupsunadded++;   
  // groupid=conn.rs1.getString("target_id");
   System.out.println("Group "+cells.get(3).toString()+" NOT ADDED");
   
   String insertgrp="insert into groups(group_id,group_name,partner_id,target_pop_id,district_id) values (?,?,?,?,?)";
   
   conn.pst1=conn.conne.prepareStatement(insertgrp);
   conn.pst1.setString(1,group_id );
   conn.pst1.setString(2, cells.get(3).toString());
   conn.pst1.setString(3, partner_id);
   conn.pst1.setString(4, targetid);
   conn.pst1.setString(5, distid);
  // conn.pst1.executeUpdate();
 
 
 }
 
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
                              //facilitator details.
 
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
 //now read the facilitator details
 
 String facilitator_id="";
 
  String getfacilitator="select * from facilitator_details where phone = ? and (first_name LIKE ? || sur_name LIKE ? )  ";  
  
  
  String phone="0";
 
  if(!cells.get(8).toString().equalsIgnoreCase("")){
  
  phone=cells.get(8).toString().trim();
  
  }
  
  if(2==1){ 

  
  
  conn.pst=conn.conne.prepareStatement(getfacilitator);
  conn.pst.setString(1, phone);
  conn.pst.setString(2, cells.get(5).toString());
  conn.pst.setString(3, cells.get(6).toString());
  
  conn.rs1=conn.pst.executeQuery();
  
 // if the facilitator exists 
  
  
 
  
  if(conn.rs1.next()){
  //get the facilitor id
      
      //check whether He teaches that group if not , append the current group id to his list
  facilitator_id=conn.rs1.getString("facilitator_id").toString().trim();
      String getfacilid="select * from facilitator_details where facilitator_id='"+conn.rs1.getString("facilitator_id") +"' and groups_id like '%"+group_id+"%'";
      
      
   System.out.println(" Searching for this facil "+getfacilid);
            
            conn.rs2=conn.st2.executeQuery(getfacilid);
          
            
            if(!conn.rs2.next()){
            
                
              String upda="update facilitator_details set groups_id=CONCAT( groups_id,'"+group_id+","+"') where facilitator_id='"+conn.rs1.getString("facilitator_id")+"'";  
             
              System.out.println(" ^^ Updated Facilitaor===="+upda);
              
              conn.st3.executeUpdate(upda);
            
            }
            else {
            
              System.out.println(" XX Facilitator "+cells.get(5).toString()+"_"+cells.get(6).toString()+" ALREADY ADDED!!!");
            
            }
      
            
  
  }//end of checking if facilitaor exists
  
  //if facilitator isnt added, add them and then add the current group
  else {
      
      facilitator_id=uniqueid().trim();
      
      
  String insertfacilt="insert into facilitator_details (facilitator_id,first_name,sur_name,phone,partner_id,groups_id,middle_name) values (?,?,?,?,?,?,?)";
  
  conn.pst1=conn.conne.prepareStatement(insertfacilt);
  conn.pst1.setString(1, facilitator_id);
  conn.pst1.setString(2, cells.get(5).toString());
  conn.pst1.setString(3, cells.get(6).toString());
  conn.pst1.setString(4,phone);
  conn.pst1.setString(5, partner_id);
  conn.pst1.setString(6, group_id+",");
  conn.pst1.setString(7, cells.get(7).toString());
  conn.pst1.executeUpdate();
  
  
 System.out.println(">> Inserted Facilitator "+cells.get(5).toString()+"_"+cells.get(6).toString());
  
  
  
  
  
        }//end of insert faciitaor else
  
                }
  
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
 
                                //member details
  
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
  
  if(1==2){ 
  int membersession=0;
 
  String memberid="";
  //======determine period
  
  String period="";
  
  if(cells.get(29).toString().trim().equals("1")||cells.get(29).toString().trim().equals("2")||cells.get(29).toString().trim().equals("3")){
  
  period="2";
  
  }
  else   if(cells.get(29).toString().trim().equals("4")||cells.get(29).toString().trim().equals("5")||cells.get(29).toString().trim().equals("6")){
  
  period="3";
  
  }
   else if(cells.get(29).toString().trim().equals("7")||cells.get(29).toString().trim().equals("8")||cells.get(29).toString().trim().equals("9")){
  
  period="4";
  
  }
   else if(cells.get(29).toString().trim().equals("10")||cells.get(29).toString().trim().equals("11")||cells.get(29).toString().trim().equals("12")){
  
  period="1";
  
  }
   else {
  
  period="1";
  
  
  System.out.println("No Month selected");
  
  }
  
   for(int sess=19;sess<=29;sess++){
  if(cells.get(sess).toString().equals("1")){
  
  membersession++;    
  
  }
  
      
  
  }
  
  
  
   
     //check if a member exists
  
  String checkfacil="select * from member_details where first_name LIKE ? and sur_name LIKE ? and group_id like ? and month = ? and year = ?";
  
   conn.pst1=conn.conne.prepareStatement(checkfacil);
   
   conn.pst1.setString(1, cells.get(15).toString());
   conn.pst1.setString(2, cells.get(16).toString());
   conn.pst1.setString(3, group_id);
   conn.pst1.setString(4, cells.get(29).toString());
   conn.pst1.setString(5, cells.get(30).toString());
conn.rs1=conn.pst1.executeQuery();


if(!conn.rs1.next()){

 System.out.println(">>> MEMBER "+cells.get(15).toString()+" "+cells.get(16).toString()+" attended "+membersession+" sessions");
  
   
   
   
   String insertmember=" insert into member_details (member_id,first_name,mname,sur_name,age,sex,group_id,sessions_attended,year,period,month) values (?,?,?,?,?,?,?,?,?,?,?) ";
   
   
  conn.pst=conn.conne.prepareStatement(insertmember);
  
  memberid=uniqueid().trim();
  
  conn.pst.setString(1, memberid);
  conn.pst.setString(2, cells.get(15).toString());
  conn.pst.setString(3, "");//no middle name
  conn.pst.setString(4, cells.get(16).toString());
  conn.pst.setString(5, cells.get(18).toString());
  conn.pst.setString(6, cells.get(17).toString());
  conn.pst.setString(7, group_id);
  conn.pst.setString(8, ""+membersession);
  conn.pst.setString(9, ""+cells.get(30).toString());//year
  conn.pst.setString(10, period);//period
  conn.pst.setString(11, ""+cells.get(29).toString());//month
  
  

  
  conn.pst.executeUpdate();
  //now count the number of sessions attended
  
}//end of members if

else{

 System.out.println("### MEMBER "+cells.get(15).toString()+" "+cells.get(16).toString()+" attended "+membersession+" sessions");
  
   

}


  }

  
  
  //==========================================================================================================
  //==========================================================================================================
  //==========================================================================================================
                                      //NEW TOPIC ID
  //==========================================================================================================
  //==========================================================================================================
  
  
  String newtopicid=uniqueid();
  
  String insertnewtopic="insert into INSERT INTO `new_topic`(`new_topic_id`,`topic_id`,`expected_sessions`,`start_date`,`end_date`,`marking_status`,`facilitator_id`,`year`,`marking_date`,`year2`,`period`,`timestamp`,`month`)"
          + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
  
  

       
        }//end of iterration while
                
                System.out.println("_______________END OF QUERY__________________");
                System.out.println("_______________GROUPS NOT ADDED______________"+numberofgroupsunadded);
                
                
        } catch (Exception ex) {
            Logger.getLogger(PULLDB.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
        
        
        
        
        
        
        
        
        
        
    }

    
    
    
    
    
       public static String getCellValue(Cell cell) {
        if (cell == null) {
            return null;
        }
        if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return cell.getStringCellValue();

        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return cell.getNumericCellValue() + "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return cell.getBooleanCellValue() + "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            return cell.getErrorCellValue() + "";
        } else {
            return null;
        }


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
        int milsec=cal.get(Calendar.MILLISECOND);
        
        
        return generateRandomNumber(800, 8000)+year+""+month+""+date+hour+min+sec+milsec;
    }
    
   
 
   public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
 
//========================================================================== 
    
}
