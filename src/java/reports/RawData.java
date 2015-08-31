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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Manuel
 */
public class RawData extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //response.setContentType("text/html;charset=UTF-8");
            
            
            
      

    
     
        HSSFWorkbook workbook;
        
     //  HSSFWorkbook wb = new HSSFWorkbook();
     XSSFWorkbook    wb1 = new XSSFWorkbook();
    SXSSFWorkbook wb = new SXSSFWorkbook(wb1, 100);
       String year=request.getParameter("year");
       String targetpopulation[]=request.getParameterValues("targetpop");
       String county="";
       String partner="";
       String period[]=request.getParameterValues("quarter");
       String quarterqr="";
       String yearsqr="";
       String datetype=request.getParameter("datetype");
       
    String startdate="";
    
    String enddate="";
    
       
       
       //=================================================================
       if(datetype.equals("quarter")){
 
       if(period.length>0){ 
       quarterqr=" and (";
           for(int a=0;a<period.length;a++){
           
           quarterqr+=" member_details.period='"+period[a]+"'";
           
           if(a<period.length-1&&period.length!=1){quarterqr+=" OR ";}
           
           }
            quarterqr+=" ) ";
       
       }
       }
       else {
           
       startdate=request.getParameter("startdate");    
       enddate=request.getParameter("enddate");    
           
       
       quarterqr=" and ( member_details.timestamp >='"+startdate+"' and member_details.timestamp <='"+enddate+"' ) ";
       
       
       }
       
       //==================================================================
       
       
       
       
       
       
       String targetqr="";
       if(targetpopulation!=null){
        if(targetpopulation.length>0){ 
            if(targetpopulation[0].equals("")&&targetpopulation.length==1){
            
            //no action
            
            } else {
       targetqr=" and (";
           for(int a=0;a<targetpopulation.length;a++){
          // System.out.println("__"+targetpopulation[a]);
           targetqr+=" population_name LIKE '"+targetpopulation[a]+"'";
           
           if(a<targetpopulation.length-1){targetqr+=" OR ";}
           System.out.println("Test condition "+a +" is a's value "+(targetpopulation.length-1));
                                                      }
       
           targetqr+=" )";
            }
       }
       }
        
        String querydata=" SELECT (CONCAT(member_details.first_name,\" \",member_details.mname,\" \",member_details.sur_name)) as PARTICIPANT, "
+" group_name as GROUPNAME,"
+" age as AGE,"
+" sex as SEX,"
+" district_name as DISTRICT, "
+" county_name as COUNTY,"
+" partner_name as PARTNER,"
+" population_name as TARGET_POPULATION,"
+" sessions_attended as SESSIONS_ATTENDED,"
+" no_of_lessons as EXPECTED_SESSION,"
+" member_details.month as MONTH,"
+" member_details.year as YEAR,"
+" member_details.period as QUARTER,"
+" CONCAT(UPPER(facilitator_details.first_name),\" \",UPPER(facilitator_details.sur_name),\"(\",phone,\")\") as FACILITATOR "
+ ",new_topic.start_date as STARTDATE, new_topic.end_date as END_DATE ,form_number as FORM ,curriculum_name as CURRICULUM,wardname as WARD "
+" FROM groups "

+" join member_details on member_details.group_id=groups.group_id "
+" join target_population on groups.target_pop_id=target_population.target_id "
+" join district on groups.district_id=district.district_id "
+" join partner on groups.partner_id=partner.partner_id "
+ "left join ward on groups.wardid = ward.wardid"
+" join curriculum on groups.target_pop_id=curriculum.target_id,county,register_attendance, facilitator_details,new_topic,forms "
+" where district.county_id=county.county_id "
+" and member_details.member_id=register_attendance.member_id "
+" and register_attendance.facilitator_id=facilitator_details.facilitator_id "
+" and register_attendance.marked_date=new_topic.marking_date "
+ " and register_attendance.form_id=forms.form_id"
+" and sessions_attended >=0   and sex !=''and member_details.year='"
+year+"' "+targetqr + quarterqr+" group by member_details.member_id ";
        
       dbConn conn= new dbConn(); 
       System.out.println(querydata); 
       
      conn.rs=conn.st.executeQuery(querydata);
       
       String columnheaders[]={"PARTICIPANT","GROUPNAME","AGE","SEX","DISTRICT","COUNTY","PARTNER","TARGET_POPULATION","SESSIONS_ATTENDED","EXPECTED_SESSION","MONTH","YEAR","QUARTER","FACILITATOR","START DATE","END DATE","FORM NO.","CURRICULUM","WARD"};
        
        
        //HSSFSheet worksheet = wb.createSheet("HC1 RAW DATA");
       Sheet worksheet = wb.createSheet("HC1 RAW DATA");


                //%%%%%%%%%%%%%%%%HEADER FONTS AND COLORATION%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                Font font_header = wb.createFont();
                font_header.setFontHeightInPoints((short) 9);
                font_header.setFontName("Arial Black");



                //    font.setItalic(true);
                font_header.setBoldweight((short) 03);
                font_header.setColor(HSSFColor.BLACK.index);

                //font data
               Font datafont = wb.createFont();
                datafont.setBoldweight((short) 03);
              
                datafont.setFontHeightInPoints((short) 10);
                datafont.setFontName("Cambria");


                //==============HEADER STYLE==================

                CellStyle style_header = wb.createCellStyle();
                style_header.setFont(font_header);
                style_header.setWrapText(true);
                style_header.setAlignment(style_header.ALIGN_CENTER_SELECTION);
               style_header.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
                style_header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


                //========TABLE HEADER STYLING===========================
                CellStyle th_style = wb.createCellStyle();
                th_style.setFont(datafont);
                th_style.setWrapText(true);
                th_style.setAlignment(th_style.ALIGN_CENTER);
               th_style.setFillForegroundColor(HSSFColor.LAVENDER.index);
                th_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                th_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                th_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                th_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                th_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);


                //=======DATA STYLING=========================== 

                CellStyle data_style = wb.createCellStyle();
                data_style.setFont(datafont);
                data_style.setWrapText(true);
                data_style.setAlignment(data_style.ALIGN_CENTER);
                
                data_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                data_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                data_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                data_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                data_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);




                //=======INNER DATA STYLING=========================== 

                CellStyle innerdata_style = wb.createCellStyle();
                innerdata_style.setFont(datafont);
                innerdata_style.setWrapText(true);
                innerdata_style.setAlignment(data_style.ALIGN_CENTER);
            
                innerdata_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
                innerdata_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
                innerdata_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
                innerdata_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
                innerdata_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                
                
                Row allsitescolumnheader = worksheet.createRow(0);
                allsitescolumnheader.setHeightInPoints(25);
               Cell rwcolheader=null;
                
              
                
                for(int d=1;d<=columnheaders.length;d++){
                 rwcolheader = allsitescolumnheader.createCell(d-1);
                 
                  rwcolheader.setCellValue(columnheaders[d-1]);
                  rwcolheader.setCellStyle(th_style);
                  
                  //worksheet.setColumnWidth(d-1, 4500);
                }
            
            
             Row rw2 = null;

           

            int rowno=0;
            
            while (conn.rs.next()) { 
            
                
                //create indivindual rows and add data in them
                
                System.out.println(rowno+"_"+conn.rs.getString(1));
                
                        rowno++;
            
            rw2=worksheet.createRow(rowno);
            rw2.setHeightInPoints(25);
            
            //OVCcount
           Cell cell1 = rw2.createCell(0);
            cell1.setCellValue(conn.rs.getString(1));
            //cell1.setCellStyle(innerdata_style);
            
            //AGE
            Cell cell2 = rw2.createCell(1);
            cell2.setCellValue(conn.rs.getString(2));
           // cell2.setCellStyle(innerdata_style);
            
            
            //Number of services
             Cell cell3 = rw2.createCell(2);
            cell3.setCellValue(conn.rs.getString(3));
           // cell3.setCellStyle(innerdata_style);
            
            
            
              //Number of services
            Cell cell4 = rw2.createCell(3);
            cell4.setCellValue(conn.rs.getString(4));
            // cell3.setCellStyle(innerdata_style);
           
            
                //Number of services
             Cell cell5 = rw2.createCell(4);
            cell5.setCellValue(conn.rs.getString(5));
            // cell3.setCellStyle(innerdata_style);
            
                //Number of services
            Cell cell6 = rw2.createCell(5);
            cell6.setCellValue(conn.rs.getString(6));
            // cell3.setCellStyle(innerdata_style);
            
            
            
                //Number of services
            Cell cell7 = rw2.createCell(6);
            cell7.setCellValue(conn.rs.getString(7));
            // cell3.setCellStyle(innerdata_style);
            
            
                Cell cell8 = rw2.createCell(7);
            cell8.setCellValue(conn.rs.getString(8));
            // cell3.setCellStyle(innerdata_style);
         
            
                  Cell cell9 = rw2.createCell(8);
            cell9.setCellValue(conn.rs.getString(9));
            // cell3.setCellStyle(innerdata_style);
            
            
            
                  Cell cell10 = rw2.createCell(9);
            cell10.setCellValue(conn.rs.getString(10));
            // cell3.setCellStyle(innerdata_style);
            
            
            
            Cell cell11 = rw2.createCell(10);
            cell11.setCellValue(conn.rs.getString(11));
            // cell3.setCellStyle(innerdata_style);
            
            
            
                  Cell cell12 = rw2.createCell(11);
            cell12.setCellValue(conn.rs.getString(12));
            // cell3.setCellStyle(innerdata_style);
            
            Cell cell13 = rw2.createCell(12);
            cell13.setCellValue(conn.rs.getString(13));
            // cell3.setCellStyle(innerdata_style);
            
            
            Cell cell14 = rw2.createCell(13);
            cell14.setCellValue(conn.rs.getString(14));
            // cell3.setCellStyle(innerdata_style);
           
            //start date
            Cell cell15 = rw2.createCell(14);
            cell15.setCellValue(conn.rs.getString(15));
            
            //end date
            Cell cell16 = rw2.createCell(15);
            cell16.setCellValue(conn.rs.getString(16));
            
            //form number
             Cell cell17 = rw2.createCell(16);
            cell17.setCellValue(conn.rs.getString(17));
            
            //curriculum
                 Cell cell18 = rw2.createCell(17);
            cell18.setCellValue(conn.rs.getString(18));
            
            
           Cell cell19 = rw2.createCell(18);
            cell19.setCellValue(conn.rs.getString(19));
            }          
                
                
          Date dat = new Date();

        String dat1 = dat.toString().replace(" ", "_");

        // write it as an excel attachment
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=HC_RAWDATA_Generated_on_" + dat1 + ".xlsx");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        wb.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(RawData.class.getName()).log(Level.SEVERE, null, ex);
        
                
                
                
       



          

        
    }}

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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
