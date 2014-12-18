/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import hc.dbConn;
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
public class newkePMS_excel_report extends HttpServlet {
HttpSession session;
String county_name="", county_id="";
String kepms_name,kepms_id="";
    String workplace ="";
    String marps ="";
    String AB ="";
    int AB_Q1_M,AB_Q1_F,workplace_q1_m,workplace_q1_f,marps_q1_f,marps_q1_m;
    int AB_Q2_M,AB_Q2_F,workplace_q2_m,workplace_q2_f,marps_q2_f,marps_q2_m;
    String quarter="",target_id="";
    int total_Q2=0;
     int total_Q1=0;
     String partner_name,tag_name_p;
     int males1,females1,males2,females2,males3,males4,females3,females4,males,females;
     int report2_males_q1,report2_females_q1,report2_males_q2,report2_females_q2;
      int report2_males_q3,report2_females_q3,report2_males_q4,report2_females_q4;
     ArrayList tag_names= new ArrayList();
     String year="";
     int AB_F,AB_M,marp_f,marp_m,workplace_m,workplace_f,max_lessons;
     int report2_m,report2_f;
     
     String iprpt2="";
      String member_details="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        year=request.getParameter("year");
        tag_names.clear();
        dbConn conn=new dbConn();
        session=request.getSession();
        member_details="";
        String period,yea;
//            yea=session.getAttribute("year").toString();
           yea=year;
            int prev_yea=Integer.parseInt(yea)-1;
            AB_F=AB_M=marp_f=marp_m=workplace_m=workplace_f=0;
            AB_Q1_M=AB_Q1_F=workplace_q1_m=workplace_q1_f=marps_q1_f=marps_q1_m=0;
    AB_Q2_M=AB_Q2_F=workplace_q2_m=workplace_q2_f=marps_q2_f=marps_q2_m=0;
        workplace="";
        marps="";
        AB="";
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
            
        }
        
        
  System.out.println("generating County Report");      
        
//     ^^^^^^^^^^^^^^^^^^^^^CREATE A WORKBOOK^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
     HSSFWorkbook wb=new HSSFWorkbook();
    
     HSSFSheet shet2=wb.createSheet("County , Partner and Target");
  
  
     
     
   
    
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
            

  
     shet2.addMergedRegion(new CellRangeAddress(0,0,2,10));
 
   
     
         
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
          
          member_details="member_details"+yea;
          
          if(yea.equals("2014")){member_details="member_details";}
          
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
        
    //===========================CHANGED AFTER MERGING HCLIVE MEMBER DETAILS on 3rd AUGUST 2014==============
                //if its quarter 4, then choose the previous year
//                if(pe==4){yea=""+prev_yea; member_details="member_details"+yea;}
//                  else {yea=year; member_details="member_details"+yea;}
        
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
         String group_selector="select distinct(groups.group_id) as group_id from groups join "+member_details+" on groups.group_id="+member_details+".group_id  where target_pop_id='"+conn.rs2.getString(1)+"' && district_id='"+conn.rs1.getString(1) +"' and year='"+yea+"' and period='"+pe+"'";
         conn.rs3=conn.st3.executeQuery(group_selector);
         while(conn.rs3.next()){
         
             if(max_lessons>0){
             String member_selector="select * from "+member_details+"  where sessions_attended>='"+max_lessons+"' && group_id='"+conn.rs3.getString(1) +"' && year='"+yea+"' && period='"+pe+"' ";
            
             
               System.out.println(member_selector);
             conn.rs4=conn.st4.executeQuery(member_selector);
             while(conn.rs4.next()){
                //set the target population name here 
                  tag_name_p=conn.rs2.getString(2);  
                 
               
                 
                 
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
            Logger.getLogger(newkePMS_excel_report.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(newkePMS_excel_report.class.getName()).log(Level.SEVERE, null, ex);
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
