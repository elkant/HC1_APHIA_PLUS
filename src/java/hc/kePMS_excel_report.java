/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class kePMS_excel_report extends HttpServlet {
HttpSession session;
String county_name="", county_id="";
String kepms_name,kepms_id="";
    String workplace ="";
    String marps ="";
    String AB ="";
    String FEMALES_1524 ="";
    String FISHERFOLK ="";
    
    int FEMALES_Q1,FISHERFOLK_Q1_M,FISHERFOLK_Q1_F, AB_Q1_M,AB_Q1_F,workplace_q1_m,workplace_q1_f,marps_q1_f,marps_q1_m;
    int FEMALES_Q2,FISHERFOLK_Q2_M,FISHERFOLK_Q2_F, AB_Q2_M,AB_Q2_F,workplace_q2_m,workplace_q2_f,marps_q2_f,marps_q2_m;
    String quarter="",target_id="";
    int total_Q2=0;
     int total_Q1=0;
     String partner_name,tag_name_p;
     int males1,females1,males2,females2,males3,males4,females3,females4,males,females;
     int report2_males_q1,report2_females_q1,report2_males_q2,report2_females_q2;
      int report2_males_q3,report2_females_q3,report2_males_q4,report2_females_q4;
     ArrayList tag_names= new ArrayList();
     String year="";
     int FEMALES_F,FISHERFOLK_M,FISHERFOLK_F,AB_F,AB_M,marp_f,marp_m,workplace_m,workplace_f,max_lessons;
     int report2_m,report2_f;
     
     String iprpt2="";
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        year=request.getParameter("year");
        tag_names.clear();
        dbConn conn=new dbConn();
        session=request.getSession();
        
        String period,yea;
//            yea=session.getAttribute("year").toString();
           yea=year;
        int prev_yea=Integer.parseInt(yea)-1;
        AB_F=AB_M=marp_f=marp_m=workplace_m=workplace_f=0;
            
            
        FEMALES_Q1=FISHERFOLK_Q1_M=FISHERFOLK_Q1_F=AB_Q1_M=AB_Q1_F=workplace_q1_m=workplace_q1_f=marps_q1_f=marps_q1_m=0;
        FEMALES_Q2=FISHERFOLK_Q2_M=FISHERFOLK_Q2_F=AB_Q2_M=AB_Q2_F=workplace_q2_m=workplace_q2_f=marps_q2_f=marps_q2_m=0;
        workplace="";
        marps="";
        AB="";
        FEMALES_1524="";
        FISHERFOLK="";
            total_Q2=males1=females1=males2=females2=0;
            report2_males_q1=report2_females_q1=0;
            report2_males_q2=report2_females_q2=0;
            report2_males_q3=report2_females_q3=0;
            report2_males_q4=report2_females_q4=0;
     total_Q1=0;
     males3=males4=females3=females4=males=females=0;
     //        **********kePMS SELECTOR***************************
        String kepms_selector="select * from kepms";
        conn.rs=conn.st.executeQuery(kepms_selector);
        while(conn.rs.next()){
         int id_holder=conn.rs.getInt(1);
         String tag_ids=conn.rs.getString(3);
         
         if(id_holder==1){
          workplace=tag_ids;  
         }
         if(id_holder==2){
            marps=tag_ids;  
         }
         if(id_holder==3){
          AB=tag_ids;    
         }
         
         if(id_holder==4){
             
          FEMALES_1524=tag_ids;    
         
                        }
          if(id_holder==5){
             
          FISHERFOLK=tag_ids;    
         
                        }
         
         
        }
        
        
  System.out.println("generating County Report");      
        
//     ^^^^^^^^^^^^^^^^^^^^^CREATE A WORKBOOK^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     HSSFWorkbook wb=new HSSFWorkbook();
     HSSFSheet shet1=wb.createSheet("County and kePMS");
     HSSFSheet shet2=wb.createSheet("County , Partner and Target");
     HSSFSheet shet3=wb.createSheet("Partner and Target");
     for(int mj=1;mj<=3;mj++){
     
     
    shet1.setColumnWidth(0, 500);
    shet1.setColumnWidth(1, 500); 
    shet1.setColumnWidth(2, 3000);
    shet1.setColumnWidth(3, 8500);
    shet1.setColumnWidth(4, 4000);
    shet1.setColumnWidth(5, 4300);
    shet1.setColumnWidth(6, 3000);
    shet1.setColumnWidth(7, 3000);
    shet1.setColumnWidth(8, 3000);
    shet1.setColumnWidth(9, 3000);
    
    shet2.setColumnWidth(0, 500);
    shet2.setColumnWidth(1, 500); 
    shet2.setColumnWidth(2, 3000);
    shet2.setColumnWidth(3, 4000);
    shet2.setColumnWidth(4, 8500); 
    shet2.setColumnWidth(5, 3000);
    shet2.setColumnWidth(6, 4300);    
    shet2.setColumnWidth(7, 3000);
    shet2.setColumnWidth(8, 3000);
    shet2.setColumnWidth(9, 3000);
    shet2.setColumnWidth(10, 3000);
    
    shet3.setColumnWidth(0, 500);
    shet3.setColumnWidth(1, 500); 
    shet3.setColumnWidth(2, 3000);
    shet3.setColumnWidth(3, 8500);
    shet3.setColumnWidth(4, 4000);
    shet3.setColumnWidth(5, 4300);
    shet3.setColumnWidth(6, 3000);
    shet3.setColumnWidth(7, 3000);
    shet3.setColumnWidth(8, 3000);
    shet3.setColumnWidth(9, 3000);
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
//    font.setItalic(true);
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
            

     shet1.addMergedRegion(new CellRangeAddress(0,0,2,9));
     shet2.addMergedRegion(new CellRangeAddress(0,0,2,10));
     shet3.addMergedRegion(new CellRangeAddress(0,0,2,9));
    if(mj==1) {
     HSSFRow rw0 = shet1.createRow(0);
     rw0.setHeightInPoints(35);
     HSSFCell rw0cell0=rw0.createCell(2);
     rw0cell0.setCellValue("PEPfar Report for the Year "+yea);
     rw0cell0.setCellStyle(style_header);
    
     HSSFRow rw1=shet1.createRow(1);
     rw1.setHeightInPoints(30);
     HSSFCell rw1cell2=rw1.createCell(2);
     HSSFCell rw1cell3=rw1.createCell(3);
     HSSFCell rw1cell4=rw1.createCell(4);
     HSSFCell rw1cell5=rw1.createCell(5);
     HSSFCell rw1cell6=rw1.createCell(6);
     HSSFCell rw1cell7=rw1.createCell(7);
     HSSFCell rw1cell8=rw1.createCell(8);
     HSSFCell rw1cell9=rw1.createCell(9);
     rw1cell2.setCellValue("County");
     rw1cell3.setCellValue("kePMS");
     rw1cell4.setCellValue("Sex");
     rw1cell5.setCellValue("Oct -Dec("+prev_yea+")");
     rw1cell6.setCellValue("Jan-March");
     rw1cell7.setCellValue("April -June");
     rw1cell8.setCellValue("July-Sep");
     rw1cell9.setCellValue("Totals");
     
     rw1cell2.setCellStyle(style);
      rw1cell3.setCellStyle(style);
       rw1cell4.setCellStyle(style);
        rw1cell5.setCellStyle(style);
         rw1cell6.setCellStyle(style);
          rw1cell7.setCellStyle(style);
     rw1cell8.setCellStyle(style);
          rw1cell9.setCellStyle(style);
//     System.out.println("workplace ids are   :     "+workplace);   
//     System.out.println("marps ids are   :     "+marps);
//     System.out.println("AB ids are   :     "+AB); 

     int a=-4;
     
      
//          System.out.println("here with period   :  "+pe);
//        ********COUNTY SELECTOR*******************************
        String county_selector="select * from county";
        conn.rs=conn.st.executeQuery(county_selector);
        while(conn.rs.next()){
            a+=6;
            county_name=conn.rs.getString(2);
            county_id=conn.rs.getString(1);
             System.out.println("COUNTY___"+county_name+curtim()); 
             
             
               int w=a; 
       HSSFRow rww=shet1.createRow(w);
        rww.setHeightInPoints(20);
          int  x=w+1;
        
             HSSFRow rwx=shet1.createRow(x);
        rwx.setHeightInPoints(20);    
          
         int  y=x+1;
        
        HSSFRow rwy=shet1.createRow(y);
        rwy.setHeightInPoints(20);
        
        
        
       int z=y+1; 
       HSSFRow rwa=shet1.createRow(z);
        rwa.setHeightInPoints(20);
        int   b=z+1;
      
        
               
        
       HSSFRow rwb=shet1.createRow(b);
       rwb.setHeightInPoints(20);
       
       
       int c=b+1;
       HSSFRow rwc=shet1.createRow(c);
       rwc.setHeightInPoints(20);
          int   d=c+1;
       
       HSSFRow rwd=shet1.createRow(d);
       rwd.setHeightInPoints(20);
       
          int  e=d+1;
                   HSSFRow rwe=shet1.createRow(e);
                   rwe.setHeightInPoints(20);
                   
                       int   f=e+1;
       HSSFRow rwf=shet1.createRow(f);
       rwf.setHeightInPoints(20);
         shet1.addMergedRegion(new CellRangeAddress(z,b,3,3));
         shet1.addMergedRegion(new CellRangeAddress(c,d,3,3));
         shet1.addMergedRegion(new CellRangeAddress(e,f,3,3));
         shet1.addMergedRegion(new CellRangeAddress(z,z+5,2,2));
            
            
            for (int pe=1;pe<=4;pe++){
          a-=6;
            String district_selector="select * from district where county_id='"+county_id+"'";
            conn.rs1=conn.st1.executeQuery(district_selector);
            while(conn.rs1.next()){
             String district_id=conn.rs1.getString(1);
             quarter="";
             target_id="";
            
           
             
             
             
             System.out.println("District is : _"+conn.rs1.getString(3)+curtim());
             
             String group_selector="select * from groups where district_id='"+district_id+"'";
             conn.rs2=conn.st2.executeQuery(group_selector);
             while(conn.rs2.next()){
              
                System.out.println("Group is : _"+conn.rs2.getString(2)+curtim());    
                 
                 
               target_id=","+conn.rs2.getString(4)+",";
//System.out.println("target id is  :  "+target_id);
String curriculum_selector="select * from curriculum where target_id='"+conn.rs2.getString(4)+"'";
                conn.rs0=conn.st0.executeQuery(curriculum_selector);
                conn.rs0.next();
                max_lessons=conn.rs0.getInt("no_of_lessons");

              String member_selector="select * from member_details where sessions_attended>='"+max_lessons+"' && group_id='"+conn.rs2.getString(1) +"' && year='"+yea+"' && period='"+pe+"' ";
              conn.rs3=conn.st3.executeQuery(member_selector);
              while(conn.rs3.next()){
                  
               String sex= conn.rs3.getString(6);
               
              //=====add females 
               if(FEMALES_1524.contains(target_id) && sex.equalsIgnoreCase("female")){
               FEMALES_Q1++;
               FEMALES_F++;
               } 
               
                if(FISHERFOLK.contains(target_id) && sex.equalsIgnoreCase("female")){
               FISHERFOLK_Q1_F++;
               FISHERFOLK_F++;
               } 
               
                
                 if(FISHERFOLK.contains(target_id) && sex.equalsIgnoreCase("male")){
               FISHERFOLK_Q1_M++;
               FISHERFOLK_M++;
               }
               
               if(AB.contains(target_id) && sex.equalsIgnoreCase("female")){
               AB_Q1_F++;
               AB_F++;
               } 
              if(AB.contains(target_id) && sex.equalsIgnoreCase("male")){
               AB_Q1_M++;
                AB_M++;
               }
              
              if(marps.contains(target_id)&& sex.equalsIgnoreCase("male")){
               marps_q1_m++;
                marp_m++;
              }
              if(marps.contains(target_id)&& sex.equalsIgnoreCase("female")){
               marps_q1_f++;
               marp_f++;
               }
              
              if(workplace.contains(target_id) && sex.equalsIgnoreCase("male")){
               workplace_q1_m++;
              workplace_m++;
              }
              if(workplace.contains(target_id) && sex.equalsIgnoreCase("female")){
               workplace_q1_f++;
               workplace_f++;
               }
             
              
              }}}

            
       total_Q1+=FISHERFOLK_Q1_M+FISHERFOLK_Q1_F+FEMALES_Q1+AB_Q1_M+AB_Q1_F+workplace_q1_m+workplace_q1_f+marps_q1_f+marps_q1_m; 
       
//       &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
//       &&&&&&&&&&&&&&&output to an excel file the data here in&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
       //rwa.createCell(2).setCellValue(county_name);
       
       
     //==========================FEMALES 15to24=============  
   
        
       HSSFCell cell211w= rww.createCell(2);
       cell211w.setCellValue(county_name);
       cell211w.setCellStyle(innerdata_style);
       
      
       HSSFCell cell311w= rww.createCell(3);
       cell311w.setCellValue("FEMALES (15 to 24)");
       cell311w.setCellStyle(innerdata_style);
       
       
    
        
       HSSFCell cell411w= rww.createCell(4);
       cell411w.setCellValue("FEMALE");
       cell411w.setCellStyle(innerdata_style);
        
       if(pe==1 && FEMALES_Q1>=0){
      // rwc.createCell(5).setCellValue(marps_q1_f);
       
        HSSFCell cellfem= rww.createCell(5);
       if(FEMALES_Q1>0){cellfem.setCellValue(FEMALES_Q1);}
       else{cellfem.setCellValue("");}
       cellfem.setCellStyle(innerdata_style);
       
       
        } 
       
       if(pe==2 && FEMALES_Q1>=0){
     
        HSSFCell cellfem= rww.createCell(6);
       if(FEMALES_Q1>0){cellfem.setCellValue(FEMALES_Q1);}
       else{cellfem.setCellValue("");}
       cellfem.setCellStyle(innerdata_style);
       
        } 
       
       if(pe==3 && FEMALES_Q1>=0){
      
       
       HSSFCell cellfem= rww.createCell(7);
       if(FEMALES_Q1>0){cellfem.setCellValue(FEMALES_Q1);}
       else{cellfem.setCellValue("");}
       cellfem.setCellStyle(innerdata_style);
       
        } 
       if(pe==4 && FEMALES_Q1>=0){
       HSSFCell cellfem= rww.createCell(8);
       if(FEMALES_Q1>0){cellfem.setCellValue(FEMALES_Q1);}
       else{cellfem.setCellValue("");}
       cellfem.setCellStyle(innerdata_style);
       
       
        }       
        
     
       HSSFCell cellfem= rww.createCell(9);
       if(FEMALES_F>0){ cellfem.setCellValue(FEMALES_F);}
       else{cellfem.setCellValue("");}
       cellfem.setCellStyle(innerdata_style);
       
       a++;
       
       
       
     //===================females================  
       
       
       
       
       HSSFCell cell2= rwa.createCell(2);
       cell2.setCellValue(county_name);
       cell2.setCellStyle(innerdata_style);
       
      
       HSSFCell cell3= rwa.createCell(3);
       cell3.setCellValue("AB");
       cell3.setCellStyle(innerdata_style);
       
       
    
        
       HSSFCell cell4= rwa.createCell(4);
       cell4.setCellValue("FEMALE");
       cell4.setCellStyle(innerdata_style);
       
        
        if(pe==1 && AB_Q1_F>=0){
       
       HSSFCell cell5= rwa.createCell(5);
       if(AB_Q1_F>0){cell5.setCellValue(AB_Q1_F);}
       else{cell5.setCellValue("");}
       cell5.setCellStyle(innerdata_style);
        
        } 
        
        if(pe==2 && AB_Q1_F>=0){
      
       
        HSSFCell cell5= rwa.createCell(6);
       if(AB_Q1_F>0){cell5.setCellValue(AB_Q1_F);}
       else{cell5.setCellValue("");}
       cell5.setCellStyle(innerdata_style);
       
        } if(pe==3 && AB_Q1_F>=0){
    
       
        HSSFCell cell5= rwa.createCell(7);
       if(AB_Q1_F>0){cell5.setCellValue(AB_Q1_F);}
       else{cell5.setCellValue("");}
       cell5.setCellStyle(innerdata_style);
       
        } if(pe==4 && AB_Q1_F>=0){
    
       
       HSSFCell cell5= rwa.createCell(8);
       if(AB_Q1_F>0){cell5.setCellValue(AB_Q1_F);}
       else{cell5.setCellValue("");}
       cell5.setCellStyle(innerdata_style);
       
        }
      
        
       HSSFCell cell5= rwa.createCell(9);
       if(AB_F>0){cell5.setCellValue(AB_F);}
       else{cell5.setCellValue("");}
       cell5.setCellStyle(innerdata_style);
       
       
       a+=1;
    
     
          HSSFCell cell21= rwb.createCell(2);
       cell21.setCellValue(county_name);
       cell21.setCellStyle(innerdata_style);
       
      
       HSSFCell cell31= rwb.createCell(3);
       cell31.setCellValue("AB");
       cell31.setCellStyle(innerdata_style);
       
       
    
        
       HSSFCell cell41= rwb.createCell(4);
       cell41.setCellValue("MALE");
       cell41.setCellStyle(innerdata_style);
        
        
       if(pe==1 && AB_Q1_M>=0){
 
       
        HSSFCell cell= rwb.createCell(5);
       if(AB_Q1_M>0){cell.setCellValue(AB_Q1_M);}
       else{cell.setCellValue("");}
       cell.setCellStyle(innerdata_style);
       
       
        } if(pe==2 && AB_Q1_M>=0){
    
        HSSFCell cell= rwb.createCell(6);
       if(AB_Q1_M>0){cell.setCellValue(AB_Q1_M);}
       else{cell.setCellValue("");}
       cell.setCellStyle(innerdata_style);
       
        } 
        if(pe==3 && AB_Q1_M>=0){
       
       
        HSSFCell cell= rwb.createCell(7);
       if(AB_Q1_M>0){cell.setCellValue(AB_Q1_M);}
       else{cell.setCellValue("");}
       cell.setCellStyle(innerdata_style);
       
        } if(pe==4 && AB_Q1_M>=0){
   
       
        HSSFCell cell= rwb.createCell(8);
       if(AB_Q1_M>0){cell.setCellValue(AB_Q1_M);}
       else{cell.setCellValue("");}
       cell.setCellStyle(innerdata_style);
       
        }
      
        HSSFCell cell= rwb.createCell(9);
       if(AB_M>0){cell.setCellValue(AB_M);}
       else{cell.setCellValue("");}
       cell.setCellStyle(innerdata_style);
       
       
       a++;
       
   
        
       HSSFCell cell211= rwc.createCell(2);
       cell211.setCellValue(county_name);
       cell211.setCellStyle(innerdata_style);
       
      
       HSSFCell cell311= rwc.createCell(3);
       cell311.setCellValue("MARPS (MSM, FSW)");
       cell311.setCellStyle(innerdata_style);
       
       
    
        
       HSSFCell cell411= rwc.createCell(4);
       cell411.setCellValue("FEMALE");
       cell411.setCellStyle(innerdata_style);
        
       if(pe==1 && marps_q1_f>=0){
      // rwc.createCell(5).setCellValue(marps_q1_f);
       
        HSSFCell cellmarp= rwc.createCell(5);
       if(marps_q1_f>0){cellmarp.setCellValue(marps_q1_f);}
       else{cellmarp.setCellValue("");}
       cellmarp.setCellStyle(innerdata_style);
       
       
        } 
       
       if(pe==2 && marps_q1_f>=0){
     
        HSSFCell cellmarp= rwc.createCell(6);
       if(marps_q1_f>0){cellmarp.setCellValue(marps_q1_f);}
       else{cellmarp.setCellValue("");}
       cellmarp.setCellStyle(innerdata_style);
       
        } 
       
       if(pe==3 && marps_q1_f>=0){
      
       
       HSSFCell cellmarp= rwc.createCell(7);
       if(marps_q1_f>0){cellmarp.setCellValue(marps_q1_f);}
       else{cellmarp.setCellValue("");}
       cellmarp.setCellStyle(innerdata_style);
       
        } 
       if(pe==4 && marps_q1_f>=0){
       HSSFCell cellmarp= rwc.createCell(8);
       if(marps_q1_f>0){cellmarp.setCellValue(marps_q1_f);}
       else{cellmarp.setCellValue("");}
       cellmarp.setCellStyle(innerdata_style);
       
       
        }
        
        
     
       HSSFCell cellmarp= rwc.createCell(9);
       if(marp_f>0){cellmarp.setCellValue(marp_f);}
       else{cellmarp.setCellValue("");}
       cellmarp.setCellStyle(innerdata_style);
       
       a++;

       
       
        HSSFCell cell2111= rwd.createCell(2);
       cell2111.setCellValue(county_name);
       cell2111.setCellStyle(innerdata_style);
       
      
       HSSFCell cell3111= rwd.createCell(3);
       cell3111.setCellValue("MARPS  (MSM, FSW)");
       cell3111.setCellStyle(innerdata_style);
       
       
    
        
       HSSFCell cell4111= rwd.createCell(4);
       cell4111.setCellValue("MALE");
       cell4111.setCellStyle(innerdata_style);
       
        if(pe==1 && marps_q1_m>=0){
      
    // rwd.createCell(5).setCellValue(marps_q1_m);
       
     HSSFCell cellmarpf= rwd.createCell(5);
       if(marps_q1_m>0){cellmarpf.setCellValue(marps_q1_m);}
       else{cellmarpf.setCellValue("");}
       cellmarpf.setCellStyle(innerdata_style);
       
        } if(pe==2 && marps_q1_m>=0){
    
        HSSFCell cellmarpf= rwd.createCell(6);
       if(marps_q1_m>0){cellmarpf.setCellValue(marps_q1_m);}
       else{cellmarpf.setCellValue("");}
       cellmarpf.setCellStyle(innerdata_style);
       
        } if(pe==3 && marps_q1_m>=0){
       rwd.createCell(7).setCellValue(marps_q1_m);
       
        HSSFCell cellmarpf= rwd.createCell(7);
       if(marps_q1_m>0){cellmarpf.setCellValue(marps_q1_m);}
       else{cellmarpf.setCellValue("");}
       cellmarpf.setCellStyle(innerdata_style);
       
        } if(pe==4 && marps_q1_m>=0){
     
        HSSFCell cellmarpf= rwd.createCell(8);
       if(marps_q1_m>0){cellmarpf.setCellValue(marps_q1_m);}
       else{cellmarpf.setCellValue("");}
       cellmarpf.setCellStyle(innerdata_style);
       
        }
      
       
        HSSFCell cellmarpf= rwd.createCell(9);
       if(marps_q1_m>0){cellmarpf.setCellValue(marp_m);}
       else{cellmarpf.setCellValue("");}
       cellmarpf.setCellStyle(innerdata_style);
       
       a++;
  
     
       
        HSSFCell cell21111= rwe.createCell(2);
       cell21111.setCellValue(county_name);
       cell21111.setCellStyle(innerdata_style);
       
      
       HSSFCell cell31111= rwe.createCell(3);
       cell31111.setCellValue("WORKPLACE");
       cell31111.setCellStyle(innerdata_style);
       
       
    
        
       HSSFCell cell41111= rwe.createCell(4);
       cell41111.setCellValue("FEMALE");
       cell41111.setCellStyle(innerdata_style);
       
       
       
       
       
        if(pe==1 && workplace_q1_f>=0){
      // rwe.createCell(5).setCellValue(workplace_q1_f);
            
            
        HSSFCell cellmarpf1= rwe.createCell(5);
       if(workplace_q1_f>0){
           cellmarpf1.setCellValue(workplace_q1_f);}
       else{cellmarpf1.setCellValue("");
       }
       cellmarpf1.setCellStyle(innerdata_style);
       
        } 
        if(pe==2 && workplace_q1_f>=0){
       
       HSSFCell cellmarpf1= rwe.createCell(6);
       if(workplace_q1_f>0){
           cellmarpf1.setCellValue(workplace_q1_f);}
       else{cellmarpf1.setCellValue("");
       }
       cellmarpf1.setCellStyle(innerdata_style);
       
       
        } if(pe==3 && workplace_q1_f>=0){
     
       
       HSSFCell cellmarpf1= rwe.createCell(7);
       if(workplace_q1_f>0){
       cellmarpf1.setCellValue(workplace_q1_f);}
       else{cellmarpf1.setCellValue("");
       }
       cellmarpf1.setCellStyle(innerdata_style);
       
       
        } if(pe==4 && workplace_q1_f>=0){
       
       HSSFCell cellmarpf1= rwe.createCell(8);
       if(workplace_q1_f>0){
       cellmarpf1.setCellValue(workplace_q1_f);
         }
       else{cellmarpf1.setCellValue("");
           }
       cellmarpf1.setCellStyle(innerdata_style);
       
           }
       
       HSSFCell cellmarpf1= rwe.createCell(9);
     if(workplace_f>0){
       cellmarpf1.setCellValue(workplace_f);
        }
     else{
     cellmarpf1.setCellValue("");
     }
             
       cellmarpf1.setCellStyle(innerdata_style);
       
      a++;

      
      
        
       HSSFCell cell211111= rwf.createCell(2);
       cell211111.setCellValue(county_name);
       cell211111.setCellStyle(innerdata_style);
       
      
       HSSFCell cell311111= rwf.createCell(3);
       cell311111.setCellValue("WORKPLACE");
       cell311111.setCellStyle(innerdata_style);
       
       
    
        
       HSSFCell cell411111= rwf.createCell(4);
       cell411111.setCellValue("MALE");
       cell411111.setCellStyle(innerdata_style);
        
        
      if(pe==1 && workplace_q1_m>=0){
       //rwf.createCell(5).setCellValue(workplace_q1_m);
       
       HSSFCell cellmarpf2= rwf.createCell(5);
       if(workplace_q1_m>0){
       cellmarpf2.setCellValue(workplace_q1_m);                          
                           }
       else                {
           cellmarpf2.setCellValue("");
                           }
       cellmarpf2.setCellStyle(innerdata_style);
       
       
       
        } if(pe==2 && workplace_q1_m>=0){
       
            
       HSSFCell cellmarpf2= rwf.createCell(6);
       if(workplace_q1_m>0){
       cellmarpf2.setCellValue(workplace_q1_m);                           }
       else{cellmarpf2.setCellValue("");
           }
       cellmarpf2.setCellStyle(innerdata_style);
       
       
        } 
        
        if(pe==3 && workplace_q1_m>=0){
       //rwf.createCell(7).setCellValue(workplace_q1_m);  
       HSSFCell cellmarpf2= rwf.createCell(7);
       if(workplace_q1_m>0){
       cellmarpf2.setCellValue(workplace_q1_m);                           }
       else{cellmarpf2.setCellValue("");
           }
       cellmarpf2.setCellStyle(innerdata_style);
       
       
        } 
        
        if(pe==4 && workplace_q1_m>=0){
              
       HSSFCell cellmarpf2= rwf.createCell(8);
       if(workplace_q1_m>0){
       cellmarpf2.setCellValue(workplace_q1_m);                           }
       else{cellmarpf2.setCellValue("");
           }
       cellmarpf2.setCellStyle(innerdata_style);
       
       
        }
      // rwf.createCell(9).setCellValue(workplace_m);
       
       
       HSSFCell cellmarpf2= rwf.createCell(9);
       if(workplace_m>0){
       cellmarpf2.setCellValue(workplace_m);                           }
       else{cellmarpf2.setCellValue("");
           }
       cellmarpf2.setCellStyle(innerdata_style);
       
       
//            &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
//            &&&&&&&&&&    RESET ALL COUNTERS      &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

    AB_Q1_M=AB_Q1_F=workplace_q1_m=workplace_q1_f=marps_q1_f=marps_q1_m=0;
       a++;    
        }
            AB_F=AB_M=marp_f=marp_m=workplace_f=workplace_m=0;
//      System.out.println("Total in Quarter 1 is   :  "+total_Q1);      
     total_Q1=0;
     }
    }
     if(mj==2){
         
 int aa=2;
         HSSFRow rw01 = shet2.createRow(0);
     rw01.setHeightInPoints(35);
     HSSFCell rw0cell01=rw01.createCell(2);
     rw0cell01.setCellValue("PEPfar Report for the Year "+yea);
     rw0cell01.setCellStyle(style_header);
     
     
////     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
////     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44
////     $$$$$$$$$$$$$               REPORT 2  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//     
////     HEADINGS FOR THE EXCEL REPORT
//     int report2=a+2;
//     int report2_conc=0;
        
   HSSFRow rws=shet2.createRow(1);
     rws.setHeightInPoints(30);
     HSSFCell rw1cell21=rws.createCell(2);
     HSSFCell rw1cell31=rws.createCell(3);
     HSSFCell rw1cell41=rws.createCell(4);
     HSSFCell rw1cell51=rws.createCell(5);
     HSSFCell rw1cell61=rws.createCell(6);
     HSSFCell rw1cell71=rws.createCell(7);
     HSSFCell rw1cell81=rws.createCell(8);
     HSSFCell rw1cell91=rws.createCell(9);
     HSSFCell rw1cell101=rws.createCell(10);
     rw1cell21.setCellValue("County");
     rw1cell31.setCellValue("IP");
     rw1cell41.setCellValue("Target Grouped");
     rw1cell51.setCellValue("Sex");
     rw1cell61.setCellValue("Oct -Dec("+prev_yea+")");
     rw1cell71.setCellValue("Jan-March");
     rw1cell81.setCellValue("April -June");
     rw1cell91.setCellValue("July-Sep");
     rw1cell101.setCellValue("Totals");
     
     rw1cell21.setCellStyle(style);
      rw1cell31.setCellStyle(style);
    rw1cell41.setCellStyle(style);
         rw1cell51.setCellStyle(style);
            rw1cell61.setCellStyle(style);
           rw1cell71.setCellStyle(style);
     rw1cell81.setCellStyle(style);
          rw1cell91.setCellStyle(style);
          rw1cell101.setCellStyle(style);
//     a+=1;
     String targ_selector="SELECT DISTINCT (population_name) FROM target_population order by population_name";
     conn.rs=conn.st.executeQuery(targ_selector);
     while(conn.rs.next()){
        tag_names.add(conn.rs.getString(1)); 
     }
     
     String count_selector="select * from county order by county_name";
     conn.rs=conn.st.executeQuery(count_selector);
     
    //============================================================
    //============================================================
    
     
     int countypos=2;
     int countyposcopy=2;
     
     
     int targetpos=2;
     int targetposcopy=2;
     
     int ippos=2;
     int ipposcopy=2;
     
     
     String targetval="";
     String targetvalcopy="";
     
     while(conn.rs.next())
         
     {
         
         
         
         
         String getpartners="select * from partner  order by partner_name";
         
         ArrayList partnersid=new ArrayList();
         ArrayList partnersname=new ArrayList();
         
         conn.rs_6=conn.st_6.executeQuery(getpartners);
         
         while(conn.rs_6.next()){
            //add partnerid to arraylist 
         partnersid.add(conn.rs_6.getString(1));
         partnersname.add(conn.rs_6.getString(2));
         
                                 }
         
         //countypos++;
         
     county_name=conn.rs.getString(2);   
     
     
     
    for (int z=0; z<partnersid.size();z++){
        
      //in every partner id, find the possible ...
     //select the different target populations appearing in the reports
     
     
     String uniqtargets="select distinct(target_id) from target_population where partner_id='"+partnersid.get(z)+"' ";

     
     conn.rs_5=conn.st_5.executeQuery(uniqtargets);
     
     while(conn.rs_5.next()){     
        
        
     report2_m=report2_f=0;

     
     
 
     
    for(int pe=1;pe<=4;pe++){
      String tag_selector="select target_id,population_name from target_population WHERE  target_id='"+conn.rs_5.getString(1) +"' order by population_name";
     
      
      conn.rs2=conn.st2.executeQuery(tag_selector);
      
      
      //TARGETPOP WHILE LOOP
      
      
      while(conn.rs2.next()){
          
          
          
          
          
       
         String curriculum_selector="select * from curriculum where target_id='"+conn.rs2.getString(1)+"'";
                conn.rs0=conn.st0.executeQuery(curriculum_selector);
               if (conn.rs0.next()==true){
                max_lessons=conn.rs0.getInt("no_of_lessons");
                                         }
     String district_selector="select * from district WHERE county_id='"+conn.rs.getString(1) +"'";
     
     
     
     conn.rs1=conn.st1.executeQuery(district_selector);
     while(conn.rs1.next()){
         String group_selector="select group_id from groups  where target_pop_id='"+conn.rs2.getString(1)+"' && district_id='"+conn.rs1.getString(1) +"'";
         conn.rs3=conn.st3.executeQuery(group_selector);
         while(conn.rs3.next()){
         
             if(max_lessons>0){
             String member_selector="select * from member_details where sessions_attended>='"+max_lessons+"' && group_id='"+conn.rs3.getString(1) +"' && year='"+yea+"' && period='"+pe+"' ";
             conn.rs4=conn.st4.executeQuery(member_selector);
             while(conn.rs4.next()){
                //set the target population name here 
                  tag_name_p=conn.rs2.getString(2);  
                 
//                 System.out.println("here 222");
             String sex=conn.rs4.getString(6);  
             
             if(sex.equalsIgnoreCase("male") && pe==1){
             report2_males_q1++;
             report2_m++;
             }
                 
             if(sex.equalsIgnoreCase("female") && pe==1){
                report2_females_q1++; 
                report2_f++;
             } 
             
             if(sex.equalsIgnoreCase("male") && pe==2){
             report2_males_q2++; 
             report2_m++;
             }
                 
             if(sex.equalsIgnoreCase("female") && pe==2){
                report2_females_q2++; 
                report2_f++;
             }
             if(sex.equalsIgnoreCase("male") && pe==3){
             report2_males_q3++;
             report2_m++;
             }
                 
             if(sex.equalsIgnoreCase("female") && pe==3){
                report2_females_q3++; 
                report2_f++;
             } 
             
             if(sex.equalsIgnoreCase("male") && pe==4){
             report2_males_q4++;
             report2_m++;
             }
                 
             if(sex.equalsIgnoreCase("female") && pe==4){
                report2_females_q4++; 
                report2_f++;
              
             }
             }    
             }
             
         }
      }   //end of district 
     
     

     
     }//end of partner target pop
      
    }
    if(report2_m>0) {
        //get the position of the county row
          countypos++;
          targetpos++;
          ippos++;
          
          HSSFRow rwaa=shet2.createRow(aa);
        rwaa.setHeightInPoints(20);
        
        HSSFCell countycell= rwaa.createCell(2);        
        countycell.setCellValue(county_name);
        countycell.setCellStyle(innerdata_style);
     
       HSSFCell ipcell= rwaa.createCell(3);        
        ipcell.setCellValue(partnersname.get(z).toString());
        ipcell.setCellStyle(innerdata_style);
       
       HSSFCell targetcell= rwaa.createCell(4);        
        targetcell.setCellValue(tag_name_p);
        targetcell.setCellStyle(innerdata_style);
       
   
         HSSFCell gencell= rwaa.createCell(5);        
        gencell.setCellValue("MALE");
        gencell.setCellStyle(innerdata_style);
        
        if(report2_males_q1>-1){
     
         
        HSSFCell q1cell= rwaa.createCell(6);        
         if(report2_males_q1>0){ q1cell.setCellValue(report2_males_q1);}
       else { q1cell.setCellValue(""); }  
        q1cell.setCellStyle(innerdata_style);
        
        }if(report2_males_q2>-1){
      
       HSSFCell q2cell= rwaa.createCell(7);        
    
        if(report2_males_q2>0){ q2cell.setCellValue(report2_males_q2);}
       else { q2cell.setCellValue(""); } 
        q2cell.setCellStyle(innerdata_style);
        }
        if(report2_males_q3>-1){
     
       HSSFCell q3cell= rwaa.createCell(8);        
      
        if(report2_males_q3>0){ q3cell.setCellValue(report2_males_q3);}
       else { q3cell.setCellValue(""); } 
        q3cell.setCellStyle(innerdata_style);
       
        }if(report2_males_q4>-1){
       
       HSSFCell q4cell= rwaa.createCell(9);        
        
        if(report2_males_q4>0){ q4cell.setCellValue(report2_males_q4);}
       else { q4cell.setCellValue(""); }        
        q4cell.setCellStyle(innerdata_style);
       
        }
     
       
        HSSFCell ttlcell= rwaa.createCell(10);        
        
        if(report2_m>0){ ttlcell.setCellValue(report2_m);}
       else { ttlcell.setCellValue(""); } 
        ttlcell.setCellStyle(innerdata_style);
       
          aa++;
           }
           if(report2_f>0) {
               
               countypos++;
               targetpos++;
               ippos++;
               
               //if the male part for this target pop was greater than 0, then merge the
               if(report2_m>0){
                     //add merged counties   

if(targetpos-1>=targetposcopy){
shet2.addMergedRegion(new CellRangeAddress(targetposcopy, targetpos-1, 4, 4));
                              }

               
               }
          
targetposcopy=targetpos;               
               
         HSSFRow rwaa=shet2.createRow(aa);
        rwaa.setHeightInPoints(20);
        
       HSSFCell countycell= rwaa.createCell(2);        
        countycell.setCellValue(county_name);
        countycell.setCellStyle(innerdata_style);
        
        
         HSSFCell ipcell= rwaa.createCell(3);        
        ipcell.setCellValue(partnersname.get(z).toString());
        ipcell.setCellStyle(innerdata_style);
        
        HSSFCell targetcell= rwaa.createCell(4);        
        targetcell.setCellValue(tag_name_p);
        targetcell.setCellStyle(innerdata_style);
        
        
       
        
        HSSFCell gencell= rwaa.createCell(5);        
        gencell.setCellValue("FEMALE");
        
        gencell.setCellStyle(innerdata_style);
        if(report2_females_q1>-1){
       
       
        HSSFCell q1cell= rwaa.createCell(6);        
        
         if(report2_females_q1>0){ q1cell.setCellValue(report2_females_q1);}
       else { q1cell.setCellValue(""); } 
        q1cell.setCellStyle(innerdata_style);
        
        
        }if(report2_females_q2>-1){
            
        
        HSSFCell q2cell= rwaa.createCell(7);        
        
         if(report2_females_q2>0){ q2cell.setCellValue(report2_females_q2);}
       else { q2cell.setCellValue(""); }
        q2cell.setCellStyle(innerdata_style);
       
        }if(report2_females_q3>-1){
       
       
      
        HSSFCell q3cell= rwaa.createCell(8);        
       // q3cell.setCellValue(report2_females_q3);
         if(report2_females_q3>0){ q3cell.setCellValue(report2_females_q3);}
       else { q3cell.setCellValue(""); }
        q3cell.setCellStyle(innerdata_style);
       
        }if(report2_females_q4>-1){
      
       
       HSSFCell q4cell= rwaa.createCell(9);        
       // q4cell.setCellValue(report2_females_q4);
        if(report2_females_q4>0){ q4cell.setCellValue(report2_females_q4);}
       else { q4cell.setCellValue(""); }
        q4cell.setCellStyle(innerdata_style);
       
        }
     
       
       HSSFCell ttlcell= rwaa.createCell(10);        
        ttlcell.setCellValue(report2_f);
        ttlcell.setCellStyle(innerdata_style);
       
          aa++;
           }
                      
                      
     report2_males_q1=report2_males_q2=report2_males_q3=report2_males_q4=report2_m =0;         
     report2_females_q1=report2_females_q2=report2_females_q3=report2_females_q4=report2_f =0; 
     
}//end of partners id size for loop
     


     //do merging per target Population
     if(ippos-1>=ipposcopy){
shet2.addMergedRegion(new CellRangeAddress(ipposcopy, ippos-1, 3, 3));
                              }
ipposcopy=ippos;



    }//end of target pop  while loop

 //add merged counties   
if(countypos-1>=countyposcopy){
shet2.addMergedRegion(new CellRangeAddress(countyposcopy, countypos-1, 2, 2));
                            }
countyposcopy=countypos;

System.out.print("COUNTY POS is+++++++++++++++++++++++++"+countypos);
System.out.print("COUNTY POS is+++++++++++++++++++++++++"+countyposcopy);
     }//end of the county while loop
     
    
    
    
     }
     
     if(mj==3){
//     //     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
////     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44
////     $$$$$$$$$$$$$               REPORT 3  $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
//     
         HSSFRow rw0 = shet3.createRow(0);
     rw0.setHeightInPoints(35);
     HSSFCell rw0cell0=rw0.createCell(2);
     rw0cell0.setCellValue("PEPfar Report for the Year "+yea);
     rw0cell0.setCellStyle(style_header);

   HSSFRow rws=shet3.createRow(1);
     rws.setHeightInPoints(30);
     HSSFCell rw1cell21=rws.createCell(2);
     HSSFCell rw1cell31=rws.createCell(3);
     HSSFCell rw1cell41=rws.createCell(4);
     HSSFCell rw1cell51=rws.createCell(5);
     HSSFCell rw1cell61=rws.createCell(6);
     HSSFCell rw1cell71=rws.createCell(7);
     HSSFCell rw1cell81=rws.createCell(8);
     HSSFCell rw1cell91=rws.createCell(9);
     rw1cell21.setCellValue("IP");
     rw1cell31.setCellValue("Target Grouped");
     rw1cell41.setCellValue("Sex");
     rw1cell51.setCellValue("Oct -Dec("+prev_yea+")");
     rw1cell61.setCellValue("Jan-March");
     rw1cell71.setCellValue("April -June");
     rw1cell81.setCellValue("July-Sep");
     rw1cell91.setCellValue("Totals");
     
     rw1cell21.setCellStyle(style);
      rw1cell31.setCellStyle(style);
       rw1cell41.setCellStyle(style);
        rw1cell51.setCellStyle(style);
         rw1cell61.setCellStyle(style);
          rw1cell71.setCellStyle(style);
         rw1cell81.setCellStyle(style);
          rw1cell91.setCellStyle(style);
      int aaa=2;
     String partner_selector="select * from partner order by partner_name";
     conn.rs=conn.st.executeQuery(partner_selector);
     
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
     //partner position while loop
     
     int partnerposi=2;
     int partnerposicopy=2;
     
     int targetposi=2;
     int targetposicopy=2;
     String targetnamecopy="";
     
     while(conn.rs.next())
     {
         
         partner_name=conn.rs.getString(2);
     String target_selector="select * from target_population where partner_id='"+conn.rs.getString(1) +"' order by population_name " ;
     conn.rs2=conn.st2.executeQuery(target_selector);
     while(conn.rs2.next()){
             System.out.println("here within target looped");
     
          males1=males2=females1=females2=males3=males4=females3=females4=males=females=0;
          for(int pe=1;pe<=4;pe++){
       tag_name_p=conn.rs2.getString(2);  
       String group_selector="select * from groups where partner_id='"+conn.rs.getString(1)+"' && target_pop_id='"+conn.rs2.getString(1)+"'" ;
       conn.rs3=conn.st3.executeQuery(group_selector);
       while(conn.rs3.next()){
           String group_id=conn.rs3.getString(1);
           String curriculum_selector="select * from curriculum where target_id='"+conn.rs3.getString(4)+"'";
                conn.rs0=conn.st0.executeQuery(curriculum_selector);
               if (conn.rs0.next()==true){
                max_lessons=conn.rs0.getInt("no_of_lessons");
               }
           String member_selector="select * from member_details where sessions_attended>='"+max_lessons+"' && group_id='"+group_id+"'&& year='"+yea+"' && period='"+pe+"' ";
           conn.rs4=conn.st4.executeQuery(member_selector);
           while(conn.rs4.next()){
           String sex=conn.rs4.getString(6);
           
           if(sex.equalsIgnoreCase("male") && pe==1){
               males1++;
               males++;
           }
           if(sex.equalsIgnoreCase("female") && pe==1) {
              females++; 
              females1++; 
           } 
           if(sex.equalsIgnoreCase("male") && pe==2){
               males2++;
               males++;
           }
           if(sex.equalsIgnoreCase("female") && pe==2) {
             females++;   
              females2++; 
           }
           if(sex.equalsIgnoreCase("male") && pe==3){
               males3++;
               males++;
           }
           if(sex.equalsIgnoreCase("female") && pe==3) {
             females++;   
              females3++; 
           } 
           
           if(sex.equalsIgnoreCase("male") && pe==4){
               males4++;
               males++;
           }
           if(sex.equalsIgnoreCase("female") && pe==4) {
               females++; 
              females4++; 
           }
               
           }
       }
          } 
//       report3++;
//  System.out.println("partner  :  "+partner_name+" target pop  :  "+tag_name_p+" period  :  "+males1+"   female  : "+females1);     
        
          if(males>0){
              
              partnerposi++;
              targetposi++;
               System.out.println("here within target    "+tag_name_p+"   total males "+males);
         System.out.println("q1    total males "+males1);
           System.out.println("q2      total males "+males2);
             System.out.println("q3      total males "+males3);
               System.out.println("q4       total males "+males4);
             
       HSSFRow rowbbb=shet3.createRow(aaa);
        rowbbb.setHeightInPoints(20);
       
       
       HSSFCell partnercell=rowbbb.createCell(2);         
       partnercell.setCellValue(partner_name);
       partnercell.setCellStyle(innerdata_style);
      
       
       
        HSSFCell targetcell=rowbbb.createCell(3);         
       targetcell.setCellValue(tag_name_p);
       targetcell.setCellStyle(innerdata_style);
        targetnamecopy=tag_name_p;
        
        
         HSSFCell gendercell=rowbbb.createCell(4);         
       gendercell.setCellValue("MALE");
       gendercell.setCellStyle(innerdata_style);
        if(males1>=0){
      
        HSSFCell cell= rowbbb.createCell(5);      
        if(males1>0){ cell.setCellValue(males1);}
        else { cell.setCellValue(""); }
        cell.setCellStyle(innerdata_style);
       
      
        }
        if(males2>=0){
      
        
        HSSFCell cell= rowbbb.createCell(6);      
        if(males2>0){ cell.setCellValue(males2);}
        else { cell.setCellValue(""); }
        cell.setCellStyle(innerdata_style);
        
        } if(males3>=0){
      
       
        HSSFCell cell= rowbbb.createCell(7);      
        if(males3>0){ cell.setCellValue(males3);}
        else { cell.setCellValue(""); }
        cell.setCellStyle(innerdata_style);
       
        } if(males4>=0){
      
       
       
       HSSFCell cell= rowbbb.createCell(8);      
        if(males4>0){ cell.setCellValue(males4);}
        else { cell.setCellValue(""); }
        cell.setCellStyle(innerdata_style);
       
        }
      
          HSSFCell cell= rowbbb.createCell(9);      
        if(males>0){ cell.setCellValue(males);}
        else { cell.setCellValue(""); }
        cell.setCellStyle(innerdata_style);
       
          aaa++;
          } 
          
       if(females>0){
           
           
            partnerposi++;
             targetposi++;
            
             //if the male part for this target pop was greater than 2, then merge the
               if(tag_name_p.equals(targetnamecopy)){
                     //add merged counties   
if(targetposi-1>=targetposicopy){
shet3.addMergedRegion(new CellRangeAddress(targetposicopy, targetposi-1, 3, 3));
                                }
                 
               
               }
               targetposicopy=targetposi;
             
            
               System.out.println("here within target     "+tag_name_p+"   total fem "+females);
                 System.out.println("q1    total males "+females1);
           System.out.println("q2      total males "+females2);
             System.out.println("q3      total males "+females3);
               System.out.println("q4       total males "+females4);
         HSSFRow rwaaa=shet3.createRow(aaa);
        rwaaa.setHeightInPoints(20);
        HSSFCell partnercell=rwaaa.createCell(2);         
       partnercell.setCellValue(partner_name);
       partnercell.setCellStyle(innerdata_style);
       
       
       
        HSSFCell targetcell=rwaaa.createCell(3);         
       targetcell.setCellValue(tag_name_p);
       targetcell.setCellStyle(innerdata_style);
       
        
        
         HSSFCell gendercell=rwaaa.createCell(4);         
       gendercell.setCellValue("FEMALE");
       gendercell.setCellStyle(innerdata_style);
       
        if(females1>=0){
    
       HSSFCell cell= rwaaa.createCell(5);      
        if(females1>0){ cell.setCellValue(females1);}
        else { cell.setCellValue(""); }
        cell.setCellStyle(innerdata_style);
        }
        if(females2>=0){
       
       HSSFCell cell= rwaaa.createCell(6);      
        if(females2>0){ cell.setCellValue(females2);}
        else { cell.setCellValue(""); }
        cell.setCellStyle(innerdata_style);
       
         }if(females3>=0){
       
       
       HSSFCell cell= rwaaa.createCell(7);      
       if(females3>0){ cell.setCellValue(females3);}
       else { cell.setCellValue(""); }
       cell.setCellStyle(innerdata_style);
       
         }if(females4>=0){
    
       HSSFCell cell= rwaaa.createCell(8);      
        if(females4>0){ cell.setCellValue(females4);}
        else { cell.setCellValue(""); }
        cell.setCellStyle(innerdata_style);
        
         }
       
       
        HSSFCell cell= rwaaa.createCell(9);      
        if(females>0){ cell.setCellValue(females);}
        else { cell.setCellValue(""); }
        cell.setCellStyle(innerdata_style);
       
        aaa++;
       }
       
     
//     END OF EACH SHEET.
     }
     
     
     
     
     if(partnerposi-1>=partnerposicopy){
shet3.addMergedRegion(new CellRangeAddress(partnerposicopy, partnerposi-1, 2, 2));
                                       }
   partnerposicopy=partnerposi;
     
     }//end of partners while loop
     }
     } 
     
     
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
            Logger.getLogger(kePMS_excel_report.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(kePMS_excel_report.class.getName()).log(Level.SEVERE, null, ex);
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
