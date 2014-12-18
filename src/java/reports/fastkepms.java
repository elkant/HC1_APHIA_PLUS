/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import hc.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author Geofrey Nyabuto
 */
public class fastkepms extends HttpServlet {
HttpSession session;
String county_name="", county_id="",year="";
String kepms_name,kepms_id="";
int report2_m,report2_f;  
    
   String partner_name,tag_name_p;
     int males1,females1,males2,females2,males3,males4,females3,females4,males,females;
     int report2_males_q1,report2_females_q1,report2_males_q2,report2_females_q2;
      int report2_males_q3,report2_females_q3,report2_males_q4,report2_females_q4; 
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
      HSSFWorkbook wb =null;
            year=request.getParameter("year");
        
            dbConn conn=new dbConn();
            session=request.getSession();
            System.out.println("_______________Servlet is fastkepms.java________________");
            String period,yea;
    //            yea=session.getAttribute("year").toString();
               yea=year;
            int prev_yea=Integer.parseInt(yea)-1;
           
         //        **********kePMS SELECTOR***************************
       
            
            String allpath = getServletContext().getRealPath("/pepfar.xls");

         System.out.println(allpath);
         
           
         
         
         
  String addtargets="CREATE TABLE IF NOT EXISTS `targets` ( "
  +" `targetid` varchar(200) NOT NULL,"
  +" `year` varchar(45) DEFAULT NULL,"
  +" `countyid` varchar(45) DEFAULT NULL,"
  +" `partnerid` varchar(45) DEFAULT NULL,"
  +" `target` varchar(45) DEFAULT '1',"
  +" `sex` varchar(45) DEFAULT 'male',"
  +" `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,"
  +" `targetpop` varchar(45) DEFAULT NULL,"
  +" PRIMARY KEY (`targetid`) "
  +" ) ENGINE=InnoDB DEFAULT CHARSET=latin1";
         
      
    String inserttargets="REPLACE INTO `targets`(`targetid`,\n" +
"`year`,\n" +
"`countyid`,\n" +
"`partnerid`,\n" +
"`target`,\n" +
"`sex`,\n" +
"`timestamp`,\n" +
"`targetpop`) VALUES ('2014114755197315665','2014','4','7','800','female','2014-11-04 04:55:19','16'),('2014114755198245665','2014','4','7','800','male','2014-11-04 04:55:19','16'),('2014114820236082893','2014','1','2','10000','female','2014-11-04 05:20:23','21'),('2014114820236707930','2014','1','3','10000','female','2014-11-04 05:20:23','24'),('2014114820237335019','2014','1','4','1200','female','2014-11-04 05:20:23','22'),('2014114820237951852','2014','1','4','1200','male','2014-11-04 05:20:23','22'),('2014114820238265949','2014','1','1','400','male','2014-11-04 12:27:17','26'),('2014114820238585344','2014','1','1','1','female','2014-11-04 12:27:17','26'),('2014114820238732262','2014','3','2','4000','female','2014-11-04 05:23:03','21'),('2014114820239515432','2014','3','1','1700','female','2014-11-04 05:20:23','25'),('2014114820239676996','2014','3','1','1700','female','2014-11-04 05:20:23','1'),('2014114820239985055','2014','2','2','2500','female','2014-11-04 05:20:23','21'),('2014114820241231550','2014','2','1','2500','female','2014-11-04 05:20:24','1'),('201411482024141090','2014','2','3','2500','female','2014-11-04 05:20:24','24'),('201411482024452594','2014','2','1','200','male','2014-11-04 05:20:24','2'),('201411482024607461','2014','2','1','2500','female','2014-11-04 05:20:24','25'),('201411482339132871','2014','3','3','4000','female','2014-11-04 05:23:03','24'),('2014114827131873','2014','1','1','1','female','2014-11-04 12:28:39','25'),('20141148548177179','2014','5','1','800','female','2014-11-04 05:05:48','25'),('20141148548795971','2014','5','2','12000','female','2014-11-04 05:05:48','21')";     
     String checktargets="select * from targets where targetid='2014114755197315665'";    
    conn.rs2=conn.st2.executeQuery(checktargets);
    
    conn.st.executeUpdate(addtargets);
    if(!conn.rs2.next()){
        conn.st.executeUpdate(inserttargets);
    }
            
            wb = new HSSFWorkbook( );
      System.out.println("generating County Report");      
            
    //     ^^^^^^^^^^^^^^^^^^^^^CREATE A WORKBOOK^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         // wb=new HSSFWorkbook( OPCPackage.open(allpath) );
         //HSSFSheet shet1=wb.createSheet("County and kePMS");
         HSSFSheet shet2=wb.createSheet("County , Partner and Target");
        // HSSFSheet shet3=wb.createSheet("Partner and Target");
       
         
         
       report2_f=report2_m=0;
        
       shet2.setColumnWidth(0, 500);
        shet2.setColumnWidth(1, 500); 
        shet2.setColumnWidth(2, 3000);
        shet2.setColumnWidth(3, 4000);
        shet2.setColumnWidth(4, 8500); 
        shet2.setColumnWidth(5, 4000);
        shet2.setColumnWidth(6, 4300);    
        shet2.setColumnWidth(7, 3000);
        shet2.setColumnWidth(8, 3000);
        shet2.setColumnWidth(9, 3000);
        shet2.setColumnWidth(10, 3000);
        shet2.setColumnWidth(11, 3000);
        shet2.setColumnWidth(12, 4000);
        
        
         HSSFFont font=wb.createFont();
        font.setFontHeightInPoints((short)12);
        font.setFontName("Cambria");
    //    font.setItalic(true);
        font.setBoldweight((short)02);
        font.setColor(HSSFColor.BLACK.index);
        CellStyle style=wb.createCellStyle();
        style.setFont(font);
        style.setWrapText(true);
        style.setFillForegroundColor(HSSFColor.GOLD.index);
        
        style.setAlignment(style.ALIGN_CENTER);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


    //%%%%%%%%%%%%%%%%HEADER FONTS AND COLORATION%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
     HSSFFont font_header=wb.createFont();
        font_header.setFontHeightInPoints((short)10);
        font_header.setFontName("Eras Bold ITC");
    //  font.setItalic(true);
        font_header.setBoldweight((short)05);
        font_header.setColor(HSSFColor.BLACK.index);
        CellStyle style_header=wb.createCellStyle();
        style_header.setFont(font_header);
        style_header.setWrapText(true);
        style_header.setFillForegroundColor(HSSFColor.LIGHT_CORNFLOWER_BLUE.index);
    style_header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    style_header.setAlignment(style_header.ALIGN_CENTER);
               
           
    //            style_header.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    //            style_header.setBorderTop(HSSFCellStyle.BORDER_THIN);
    //            style_header.setBorderRight(HSSFCellStyle.BORDER_THIN);
    //            style_header.setBorderLeft(HSSFCellStyle.BORDER_THIN);

                
     //%%%%%%%%%%%%%%%%%%%%%%%%%DATA FONT%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                
                
                //font data
                HSSFFont datafont = wb.createFont();
                datafont.setBoldweight((short) 03);
                datafont.setColor(HSSFColor.BLACK.index);
                datafont.setFontHeightInPoints((short) 10);
                datafont.setFontName("Cambria");
                
                
                    //=======INNER DATA STYLING=========================== 

                CellStyle innerdata_style = wb.createCellStyle();
                innerdata_style.setFont(datafont);
                innerdata_style.setWrapText(true);
                innerdata_style.setAlignment(innerdata_style.ALIGN_CENTER);
                innerdata_style.setFillForegroundColor(HSSFColor.WHITE.index);
                innerdata_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                innerdata_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                innerdata_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                innerdata_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                innerdata_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                innerdata_style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                
                
                
                      //=======INNER LEFT DATA STYLING=========================== 

                CellStyle innerdata_style2 = wb.createCellStyle();
                innerdata_style2.setFont(datafont);
                innerdata_style2.setWrapText(true);
                innerdata_style2.setAlignment(innerdata_style2.ALIGN_LEFT);
                innerdata_style2.setFillForegroundColor(HSSFColor.WHITE.index);
                innerdata_style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                innerdata_style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                innerdata_style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
                innerdata_style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
                innerdata_style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                innerdata_style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
                

        
         shet2.addMergedRegion(new CellRangeAddress(0,0,2,12));
        
     
        
             
          int aa=2;
         HSSFRow rw01 = shet2.createRow(0);
         rw01.setHeightInPoints(35);
         HSSFCell rw0cell01=rw01.createCell(2);
         rw0cell01.setCellValue("PEPFAR Report for the Year "+yea);
         rw0cell01.setCellStyle(style_header);
         
         
    ////     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    ////     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44
    ////     $$$$$$$$$$$$$               REPORT 2  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
    //     
    ////     HEADINGS FOR THE EXCEL REPORT
    //     int report2=a+2;
    //     int report2_conc=0;
            
        int ps=1;
         HSSFRow rws=shet2.createRow(ps);
         ps++;
         rws.setHeightInPoints(30);
         HSSFCell rw1cell21=rws.createCell(ps);
         ps++;
         HSSFCell rw1cell31=rws.createCell(ps);
         ps++;
         HSSFCell rw1cell41=rws.createCell(ps);
         ps++;
         HSSFCell rw1cell51=rws.createCell(ps);
         ps++;
         HSSFCell rw1cell52=rws.createCell(ps);
         ps++;
         HSSFCell rw1cell61=rws.createCell(ps);
         ps++;
         HSSFCell rw1cell71=rws.createCell(ps);
         ps++;
         HSSFCell rw1cell81=rws.createCell(ps);
         ps++;
         HSSFCell rw1cell91=rws.createCell(ps);
         ps++;
         HSSFCell rw1cell101=rws.createCell(ps);
         ps++;
         HSSFCell rw1cell102=rws.createCell(ps);
          ps++;
          
         rw1cell21.setCellValue("County");
         rw1cell31.setCellValue("IP");
         rw1cell41.setCellValue("Target Grouped");
         rw1cell51.setCellValue("Sex");
         rw1cell52.setCellValue("Target");
         rw1cell61.setCellValue("Oct -Dec("+prev_yea+")");
         rw1cell71.setCellValue("Jan-March");
         rw1cell81.setCellValue("April -June");
         rw1cell91.setCellValue("July-Sep");
         rw1cell101.setCellValue("Total");
         rw1cell102.setCellValue("Achieved");
         
         rw1cell21.setCellStyle(style);
          rw1cell31.setCellStyle(style);
        rw1cell41.setCellStyle(style);
             rw1cell51.setCellStyle(style);
             rw1cell52.setCellStyle(style);
             rw1cell61.setCellStyle(style);
             rw1cell71.setCellStyle(style);
             rw1cell81.setCellStyle(style);
             rw1cell91.setCellStyle(style);
             rw1cell101.setCellStyle(style);
             rw1cell102.setCellStyle(style);
    //     a+=1;
     String targ_selector=" SELECT COUNT(member_details.member_id) as PARTICIPANT,"
    +" sex as SEX,county_name as COUNTY, county.county_id as COUNTY_ID, partner_name as PARTNER, partner.partner_id as PARTNER_ID ,population_name as TARGET_POPULATION,"
    +" sessions_attended as SESSIONS_ATTENDED,no_of_lessons as EXPECTED_SESSION,"
    +" month as MONTH,year as YEAR,period as QUARTER, target_population.target_id as targetid " 
    +" FROM groups join member_details on member_details.group_id=groups.group_id "
    +" join target_population on groups.target_pop_id=target_population.target_id"
    +" join district on groups.district_id=district.district_id"
    +" join partner on groups.partner_id=partner.partner_id "
    +" join curriculum on groups.target_pop_id=curriculum.target_id,county "
    +" where district.county_id=county.county_id  "
    +" and SEX !='' and  sessions_attended >0 and sessions_attended=no_of_lessons and year ='"+year+"' group by COUNTY,PARTNER,TARGET_POPULATION,SEX, QUARTER order by  COUNTY,PARTNER,TARGET_POPULATION ,SEX DESC,QUARTER ASC ";
         
         System.out.println(targ_selector);
         
         conn.rs=conn.st.executeQuery(targ_selector);
        

         
        //============================================================
        //============================================================
        
         
         int countypos=2;
         int countyposcopy=2;
         
         String countycopy="";
         String partnercopy="";
         String targetpopcopy="";
          String countycopy1="";
         String partnercopy1="";
         String targetpopcopy1="";
         
         
         String gendercopy="";
         
         int targetpos=2;
         int targetposcopy=2;
         
         int ippos=2;
         int ipposcopy=2;
         HSSFRow rwaa=null;
         
         String targetval="";
         String targetvalcopy="";
         
         while(conn.rs.next())
             
         {
             
             
             
         if(countycopy.equals("")||   !countycopy.equals(conn.rs.getString("COUNTY"))  || !partnercopy.equals(conn.rs.getString("PARTNER")) || !targetpopcopy.equals(conn.rs.getString("TARGET_POPULATION")) || !gendercopy.equals(conn.rs.getString("SEX")) ){
           
           System.out.println("~~Male totals "+report2_m);    
           System.out.println("~~FeMale totals "+report2_f);    
           report2_m=0;
           report2_f=0;
           }      
            
             
         county_name=conn.rs.getString("COUNTY");   
         
         
         //if its the first time
         
     if(countycopy1.equals("")){countycopy1=conn.rs.getString("COUNTY");  }
     if(partnercopy1.equals("")){partnercopy1=conn.rs.getString("PARTNER");  }
     if(targetpopcopy1.equals("")){targetpopcopy1=conn.rs.getString("TARGET_POPULATION");  }
     
     
       //add merged counties 
     
     if(!countycopy1.equals(conn.rs.getString("COUNTY"))&& countypos-1>=countyposcopy){
     //then merge
     shet2.addMergedRegion(new CellRangeAddress(countyposcopy, countypos-1, 2, 2));
      countyposcopy=countypos;
      countycopy1=conn.rs.getString("COUNTY");
     }  
     
     //merge Partners
     if(!partnercopy1.equals(conn.rs.getString("PARTNER"))&& ippos-1>=ipposcopy){
     
     //then merge
         
      
    shet2.addMergedRegion(new CellRangeAddress(ipposcopy, ippos-1, 3, 3));                                  
    ipposcopy=ippos;
    partnercopy1=conn.rs.getString("PARTNER");
     }
      //merge target population
       
     if(!targetpopcopy.equals(conn.rs.getString("TARGET_POPULATION"))&& targetpos-1>=targetposcopy){
     
     //then merge    
    shet2.addMergedRegion(new CellRangeAddress(targetposcopy, targetpos-1, 4, 4));                              
              
    targetposcopy=targetpos;  
    targetpopcopy1=conn.rs.getString("TARGET_POPULATION");
     
     }
     
       
              
     //if county is different from county copy, then merge
     
          //in every partner id, find the possible ...
         //select the different target populations appearing in the reports
         
         
       
            
            
        // report2_m=report2_f=0;

         //if its the first time, then assign all values with the correct value
    //     if(countycopy.equals("")){
    //       countycopy=conn.rs.getString("COUNTY");
    //      partnercopy=conn.rs.getString("PARTNER");
    //      targetpopcopy=conn.rs.getString("TARGET_POPULATION");
    //      gendercopy=conn.rs.getString("SEX"); 
    //      
    //      
    //     
    //     }
     // System.out.println(countycopy+"~"+partnercopy+"~"+targetpopcopy+"~"+gendercopy);
     // System.out.println(conn.rs.getString("COUNTY")+"="+conn.rs.getString("PARTNER")+"="+conn.rs.getString("TARGET_POPULATION")+"="+conn.rs.getString("SEX"));
         
        for(int pe=1;pe<=4;pe++){
          
          //TARGETPOP WHILE LOOP
          
             
                 if(1==1){
                 
                    //set the target population name here 
                 tag_name_p=conn.rs.getString("TARGET_POPULATION");  
                     
    //                 System.out.println("here 222");
                 String sex=conn.rs.getString("SEX");  
                 
                 if(sex.equalsIgnoreCase("male") && pe==1 && conn.rs.getString("QUARTER").equals("1")){
                 report2_males_q1=conn.rs.getInt("PARTICIPANT");
                 report2_m+=conn.rs.getInt("PARTICIPANT");
                 }
                     
                 if(sex.equalsIgnoreCase("female") && pe==1 && conn.rs.getString("QUARTER").equals("1")){
                    report2_females_q1=conn.rs.getInt("PARTICIPANT"); 
                    report2_f+=conn.rs.getInt("PARTICIPANT");
                 } 
                 
                 if(sex.equalsIgnoreCase("male") && pe==2 && conn.rs.getString("QUARTER").equals("2")){
                 report2_males_q2=conn.rs.getInt("PARTICIPANT"); 
                 report2_m+=conn.rs.getInt("PARTICIPANT");
                 }
                     
                 if(sex.equalsIgnoreCase("female") && pe==2 && conn.rs.getString("QUARTER").equals("2")){
                    report2_females_q2=conn.rs.getInt("PARTICIPANT"); 
                    report2_f+=conn.rs.getInt("PARTICIPANT");
                 }
                 if(sex.equalsIgnoreCase("male") && pe==3 && conn.rs.getString("QUARTER").equals("3")){
                 report2_males_q3=conn.rs.getInt("PARTICIPANT");
                 report2_m+=conn.rs.getInt("PARTICIPANT");
                 }
                     
                 if(sex.equalsIgnoreCase("female") && pe==3 && conn.rs.getString("QUARTER").equals("3")){
                    report2_females_q3=conn.rs.getInt("PARTICIPANT"); 
                    report2_f+=conn.rs.getInt("PARTICIPANT");
                 } 
                 
                 if(sex.equalsIgnoreCase("male") && pe==4 && conn.rs.getString("QUARTER").equals("4")){
                 report2_males_q4=conn.rs.getInt("PARTICIPANT");
                 report2_m+=conn.rs.getInt("PARTICIPANT");
                 }
                     
                 if(sex.equalsIgnoreCase("female") && pe==4 && conn.rs.getString("QUARTER").equals("4")){
                    report2_females_q4=conn.rs.getInt("PARTICIPANT"); 
                    report2_f+=conn.rs.getInt("PARTICIPANT");
                  
                 }
                  
                 }
                 
         
       
         
         

         

          
        }
        if(report2_m>0) {
            
            //get the position of the county row
              
    //===================================================================row is created at this point          
               if(countycopy.equals("")||   !countycopy.equals(conn.rs.getString("COUNTY"))  || !partnercopy.equals(conn.rs.getString("PARTNER")) || !targetpopcopy.equals(conn.rs.getString("TARGET_POPULATION")) || !gendercopy.equals(conn.rs.getString("SEX")) ){
           
                   
                   rwaa=shet2.createRow(aa);
                    HSSFCell testcell=null;
                   for(int a=7;a<13;a++){
                    testcell= rwaa.createCell(a);        
            testcell.setCellValue("");
            testcell.setCellStyle(innerdata_style);
                   
                   }
                   
            rwaa.setHeightInPoints(20);
           aa++;
            countypos++;
            ippos++;
            targetpos++;
              }
                
           
            int posit=2;
            HSSFCell countycell= rwaa.createCell(posit);        
            countycell.setCellValue(county_name);
            countycell.setCellStyle(innerdata_style);
         posit++;
           HSSFCell ipcell= rwaa.createCell(posit);        
            ipcell.setCellValue(conn.rs.getString("PARTNER"));
            ipcell.setCellStyle(innerdata_style);
           posit++;
           HSSFCell targetcell= rwaa.createCell(posit);        
            targetcell.setCellValue(tag_name_p);
            targetcell.setCellStyle(innerdata_style);
           
          posit++;
             HSSFCell gencell= rwaa.createCell(posit);        
            gencell.setCellValue("MALE");
            gencell.setCellStyle(innerdata_style);
            posit++;
            int curtarget=1;
            
            String PARTNER_ID=conn.rs.getString("PARTNER_ID");
            String COUNTY_ID=conn.rs.getString("COUNTY_ID");
            String TARGETID=conn.rs.getString("targetid");
       //================MALE TARGETS   
             
             HSSFCell targetcells= rwaa.createCell(posit);
            //==FEMALE TARGETS
              posit++;
            if(report2_m>0){
            String qr="select target from targets where countyid='"+COUNTY_ID+"' and targetpop='"+TARGETID+"' and partnerid='"+PARTNER_ID +"' and sex='male' and year='"+year+"'";
            
             conn.rs_4=conn.st_4.executeQuery(qr);
             
             while(conn.rs_4.next()){
             
              curtarget=conn.rs_4.getInt(1);   
                 
             
                                    }//end of target  while
             
             targetcells.setCellValue(curtarget);
            targetcells.setCellStyle(innerdata_style);
            }
           
                
          
             
             
             
             
             
            if(report2_males_q1>-1){
         
             
       
           
             if(report2_males_q1>0){ 
                 HSSFCell q1cell= rwaa.createCell(posit);
                 q1cell.setCellValue(report2_males_q1);
                 q1cell.setCellStyle(innerdata_style);
             //get the target for that gender
             
             
             
             }
           else { 
                 //HSSFCell q1cell= rwaa.createCell(posit);
                 //q1cell.setCellValue("");q1cell.setCellStyle(innerdata_style);
             }  
            
              posit++;
              
            }if(report2_males_q2>-1){
          
          
            if(report2_males_q2>0){ 
                HSSFCell q2cell= rwaa.createCell(posit);        
       q2cell.setCellValue(report2_males_q2);
       q2cell.setCellStyle(innerdata_style);}
           else { //HSSFCell q2cell= rwaa.createCell(posit);        
                  //q2cell.setCellValue("");
                  //q2cell.setCellStyle(innerdata_style);
            } 
             posit++;
            }
            if(report2_males_q3>-1){
         
                  
          
            if(report2_males_q3>0){ HSSFCell q3cell= rwaa.createCell(posit);
                                   q3cell.setCellValue(report2_males_q3);
                                   q3cell.setCellStyle(innerdata_style);}
           else {  //HSSFCell q3cell= rwaa.createCell(posit);
                 //q3cell.setCellValue("");
                 //q3cell.setCellStyle(innerdata_style); 
            } 
            
            posit++;
            }if(report2_males_q4>-1){
           
                 
          
            if(report2_males_q4>0){   HSSFCell q4cell= rwaa.createCell(posit);
                                      q4cell.setCellValue(report2_males_q4);
                                      q4cell.setCellStyle(innerdata_style);}
           else {                     //HSSFCell q4cell= rwaa.createCell(posit); 
                                      //q4cell.setCellValue("");
                                      //q4cell.setCellStyle(innerdata_style); 
            }        
            
              posit++;
            }
         
           
            HSSFCell ttlcell= rwaa.createCell(posit);        
             posit++;
            if(report2_m>0){ ttlcell.setCellValue(report2_m);}
           else { ttlcell.setCellValue(""); } 
            ttlcell.setCellStyle(innerdata_style);
           
            
            //achieved cell
            HSSFCell achievedcell= rwaa.createCell(posit);        
             posit++;
            if(report2_m>0){
                
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~"+report2_m/curtarget);
                  double ans=(((report2_m*100)/curtarget));
                  //ans=new DecimalFormat("#.##").format(ans );
                  
               // ans=ans*100;
                achievedcell.setCellValue(ans+"%");}
           else { achievedcell.setCellValue(""); } 
            achievedcell.setCellStyle(innerdata_style); 
            
            //if either the gender,county,partner or terget  has changed then its when you can increment the value.
            //after incrementing, get the new value of the variables.
            if(countycopy.equals("")||   !countycopy.equals(conn.rs.getString("COUNTY"))  || !partnercopy.equals(conn.rs.getString("PARTNER")) || !targetpopcopy.equals(conn.rs.getString("TARGET_POPULATION")) || !gendercopy.equals(conn.rs.getString("SEX")) ){
           
                
                
           countycopy=conn.rs.getString("COUNTY");
          partnercopy=conn.rs.getString("PARTNER");
          targetpopcopy=conn.rs.getString("TARGET_POPULATION");
          gendercopy=conn.rs.getString("SEX");
              System.out.println(countycopy+"~"+partnercopy+"~"+targetpopcopy+"~"+gendercopy+"~");   
                
           //   aa++;
              
              
              
              
           }
               }
               if(report2_f>0) {
                   
                 
                   
                   //if the male part for this target pop was greater than 0, then merge the
                 
                         //add merged counties   

    


    //===================================================================row is created at this point          
     
             if(countycopy.equals("")||   !countycopy.equals(conn.rs.getString("COUNTY"))  || !partnercopy.equals(conn.rs.getString("PARTNER")) || !targetpopcopy.equals(conn.rs.getString("TARGET_POPULATION")) || !gendercopy.equals(conn.rs.getString("SEX")) ){
           
                 rwaa=shet2.createRow(aa);
                   HSSFCell testcell=null;
                   for(int a=7;a<13;a++){
                    testcell= rwaa.createCell(a);        
            testcell.setCellValue("");
            testcell.setCellStyle(innerdata_style);
                   
                   }
                 
                 
                    aa++;
                    
                     countypos++;                     
                     ippos++;
                     targetpos++;
                     
              }
            rwaa.setHeightInPoints(20);
            
            int posit=2;
           HSSFCell countycell= rwaa.createCell(posit);        
            countycell.setCellValue(county_name);
            countycell.setCellStyle(innerdata_style);
            posit++;
            
             HSSFCell ipcell= rwaa.createCell(posit);        
            ipcell.setCellValue(conn.rs.getString("PARTNER"));
            ipcell.setCellStyle(innerdata_style);
            posit++;
            HSSFCell targetcell= rwaa.createCell(posit);        
            targetcell.setCellValue(tag_name_p);
            targetcell.setCellStyle(innerdata_style);
            posit++;
            
           
            
            HSSFCell gencell= rwaa.createCell(posit);        
            gencell.setCellValue("FEMALE");
            
            gencell.setCellStyle(innerdata_style);
            posit++;
            int curtarget=1;
            HSSFCell targetcells= rwaa.createCell(posit);        
            posit++;
            
             String PARTNER_ID=conn.rs.getString("PARTNER_ID");
             String COUNTY_ID=conn.rs.getString("COUNTY_ID");
             String TARGETID=conn.rs.getString("targetid");
              if(report2_f>0){
             String qr="select target from targets where countyid='"+COUNTY_ID+"' and targetpop='"+TARGETID+"' and partnerid='"+PARTNER_ID +"' and sex='female' and year='"+year+"'";
            
             conn.rs_4=conn.st_4.executeQuery(qr);
             
             while(conn.rs_4.next()){
             
              curtarget=conn.rs_4.getInt(1);   
                 
             
                                    }
             targetcells.setCellValue(curtarget);
            targetcells.setCellStyle(innerdata_style);
             
             
            }
            
            
            if(report2_females_q1>-1){
           
           
                 
           
             if(report2_females_q1>0){ 
                 HSSFCell q1cell= rwaa.createCell(posit); 
                 q1cell.setCellValue(report2_females_q1);
                 q1cell.setCellStyle(innerdata_style);
             }
           else { 
                 //    HSSFCell q1cell= rwaa.createCell(posit); q1cell.setCellValue(""); q1cell.setCellStyle(innerdata_style);
             } 
           
            
             posit++;
            }if(report2_females_q2>-1){
                
            
                 
          
             if(report2_females_q2>0){  
             HSSFCell q2cell= rwaa.createCell(posit); 
             q2cell.setCellValue(report2_females_q2); 
             q2cell.setCellStyle(innerdata_style);}
           else { //  HSSFCell q2cell= rwaa.createCell(posit);  q2cell.setCellValue(""); q2cell.setCellStyle(innerdata_style); 
                 
             }
           
             posit++;
            }if(report2_females_q3>-1){
           
           
          
          
        
           // q3cell.setCellValue(report2_females_q3);
             if(report2_females_q3>0){ 
                   HSSFCell q3cell= rwaa.createCell(posit);  
                 q3cell.setCellValue(report2_females_q3);
             q3cell.setCellStyle(innerdata_style);}
           else { 
                //  HSSFCell q3cell= rwaa.createCell(posit);   q3cell.setCellValue(""); q3cell.setCellStyle(innerdata_style);
             }
                posit++;
           
            }if(report2_females_q4>-1){
          
           
           
           
           // q4cell.setCellValue(report2_females_q4);
            if(report2_females_q4>0){
                HSSFCell q4cell= rwaa.createCell(posit);
            q4cell.setCellValue(report2_females_q4);
            q4cell.setCellStyle(innerdata_style);}
           else { 
                //HSSFCell q4cell= rwaa.createCell(posit);
               // q4cell.setCellValue(""); q4cell.setCellStyle(innerdata_style);
            }
            
           posit++;
            }
         
           
           HSSFCell ttlcell= rwaa.createCell(posit);
            posit++;
            ttlcell.setCellValue(report2_f);
            ttlcell.setCellStyle(innerdata_style);
           
            
            //achieved cell
            HSSFCell achievedcell= rwaa.createCell(posit);        
             posit++;
            if(report2_f>0){
                 double ans=(((report2_f*100)/curtarget));
                          System.out.println("~f~~f~~f~~f~~~~~~~~"+report2_f+"/"+curtarget);
                 System.out.println("~~~~~~~~~~~~~~~~~~~~~~~"+report2_f/curtarget);
               
               // ans=ans*100;
                achievedcell.setCellValue(ans+"%");}
           else { achievedcell.setCellValue(""); } 
            achievedcell.setCellStyle(innerdata_style); 
            
              if( countycopy.equals("")||  !countycopy.equals(conn.rs.getString("COUNTY"))  || !partnercopy.equals(conn.rs.getString("PARTNER")) || !targetpopcopy.equals(conn.rs.getString("TARGET_POPULATION")) || !gendercopy.equals(conn.rs.getString("SEX")) ){
            countycopy=conn.rs.getString("COUNTY");
          partnercopy=conn.rs.getString("PARTNER");
          targetpopcopy=conn.rs.getString("TARGET_POPULATION");
          gendercopy=conn.rs.getString("SEX");
              //aa++;
               }
              
         }
                          
                          
         report2_males_q1=report2_males_q2=report2_males_q3=report2_males_q4=0;         
         report2_females_q1=report2_females_q2=report2_females_q3=report2_females_q4=0; 
         

         


         //do merging per target Population
       



       

   

    System.out.println("COUNTY POS is+++++++++++++++++++++++++"+countypos);
    System.out.println("COUNTY POS is+++++++++++++++++++++++++"+countyposcopy);
         }//end of the county while loop
        
         
         //merge the last columns
         shet2.addMergedRegion(new CellRangeAddress(countyposcopy, countypos-1, 2, 2));
        shet2.addMergedRegion(new CellRangeAddress(ipposcopy, ippos-1, 3, 3));
        shet2.addMergedRegion(new CellRangeAddress(targetposcopy, targetpos-1, 4, 4)); 
        
         
         Date dat= new Date();
         
         
         String date1=dat.toString().replace(" ","_");
         
         // write it as an excel attachment
    ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
    wb.write(outByteStream);
    byte [] outArray = outByteStream.toByteArray();
    response.setContentType("application/ms-excel");
    response.setContentLength(outArray.length);
    response.setHeader("Expires:", "0"); // eliminates browser caching
    response.setHeader("Content-Disposition", "attachment; filename=HC_PEPFAR_REPORT_"+date1+".xls");
    OutputStream outStream = response.getOutputStream();
    outStream.write(outArray);
    outStream.flush();
        
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
            Logger.getLogger(kepms.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(kepms.class.getName()).log(Level.SEVERE, null, ex);
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

public String curtim(){


  Date time= new Date();
             int mini=time.getMinutes();
             int sec=time.getSeconds();
             
             String val=" "+mini+":"+sec;
             return val;

}

}
