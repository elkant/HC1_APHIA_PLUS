/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;


import SCRIPTS.copytemplates;
import hc.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Manuel
 */
public class agebasedkepms extends HttpServlet {


    int maxmerging=0;
    String maincountqry="";
    String year="";
    String targetpop ="";
    String pathtodelete=null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            XSSFWorkbook wb;
        
        try {
            response.setContentType("text/html;charset=UTF-8");

 String allpath = getServletContext().getRealPath("/Females 15to24.xlsm");

 System.out.println(allpath);
 
    XSSFWorkbook workbook;
      String mydrive = allpath.substring(0, 1);
   // wb = new XSSFWorkbook( OPCPackage.open(allpath) );
     
 Date da= new Date();
String dat2 = da.toString().replace(" ", "_");
 dat2 = dat2.toString().replace(":", "_");

      
       String np=mydrive+":\\APHIAPLUS\\HC_SYSTEM\\MACROS\\Females"+dat2+".xlsm";
     
             // String desteepath1 = getServletContext().getRealPath("/Females 15to24.xlsm");
              String sr = getServletContext().getRealPath("/Females 15to24.xlsm");
    //check if file exists
              
   //first time , it should create those folders that host the macro file
    File f = new File(np);
if(!f.exists()&& !f.isDirectory() ) { /* do something */
copytemplates ct= new copytemplates();
    ct.transfermacros(sr,np);
 //rem np is the destination file name  
   
    System.out.println("Copying macros first time ..");
    
}
else 
  //copy the file alone  
{
copytemplates ct= new copytemplates();
//copy the agebased file only
ct.copymacros(sr,np);

}
String filepth=np;      

  File allpathfile= new File(filepth);
     
      OPCPackage pkg = OPCPackage.open(allpathfile);

            pathtodelete=filepth;
    wb = new XSSFWorkbook(pkg);

   String columnheaders[]={"PARTICIPANT","SEX","AGE_BRACKET","PARTNER","TARGET_POPULATION","MONTHS","YEAR","COUNTY"};
        
  //  PARTICIPANT	SEX	AGE_BRACKET	PARTNER	TARGET_POPULATION	MONTHS	YEAR	COUNTY

    XSSFSheet rawdata = wb.getSheet("Sheet1");


            //%%%%%%%%%%%%%%%%HEADER FONTS AND COLORATION%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            XSSFFont font_header = wb.createFont();
            font_header.setFontHeightInPoints((short) 9);
            font_header.setFontName("Adobe Garamond Pro Bold");



            //    font.setItalic(true);
            font_header.setBoldweight((short) 03);
            font_header.setColor(HSSFColor.BLACK.index);

            //font data
            XSSFFont datafont = wb.createFont();
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
           th_style.setFillForegroundColor(HSSFColor.GOLD.index);
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

            String targetpopulation[]=request.getParameterValues("targetpop");
           
            String targetqr="";
       if(targetpopulation!=null){
        if(targetpopulation.length>0){ 
            if(targetpopulation[0].equals("")&&targetpopulation.length==1){
            
            //no action
            
            } else {
       targetqr=" and (";
           for(int a=0;a<targetpopulation.length;a++){
           System.out.println("__"+targetpopulation);
           targetqr+=" population_name LIKE '"+targetpopulation[a]+"'";
           
           if(a<targetpopulation.length-1){targetqr+=" OR ";}
           System.out.println("Test condition "+a +" is a's value "+(targetpopulation.length-1));
                                                      }
       
           targetqr+=" )";
            }
       }
       }
            
            
            
            targetpop="FEMALES (15 to 24)";
            year="2014";
            
            if(request.getParameter("year")!=null){
            year = request.getParameter("year");}
            
          

 
    String qry="SELECT count(member_details.member_id) as PARTICIPANT,"

+" CASE "
+" when SEX LIKE 'female' THEN 'F'"
+" when SEX LIKE 'male' THEN 'M'"
+" ELSE 'NO SEX'"
+" END AS SEX,"
+" case "
+" when age between 0 and 14 then '0-14'"
+" when age between 15 and 19 then '15-19'"
+" when age between 20 and 24 then '20-24'"
+" when age >= 25 then '25+'"
+" end as AGE_BRACKET "
+" , partner_name as PARTNER,population_name as TARGET_POPULATION,"


+" case "
+" when MONTH =1 THEN 'JAN'"
+" when MONTH =2 THEN 'FEB'"
+" when MONTH =3 THEN 'MAR'"
+" when MONTH=4 THEN 'APR'"
+" when MONTH=5 THEN 'MAY'"
+" when MONTH=6 THEN 'JUN'"
+" when MONTH=7 THEN 'JUL'"
+" when MONTH=8 THEN 'AUG'"
+" when MONTH=9 THEN 'SEP'"
+" when MONTH=10 THEN 'OCT'"
+" when MONTH=11 THEN 'NOV'"
+" when MONTH=12 THEn 'DEC'"
+" END AS MONTHS"

+" ,year as YEAR,Upper(county_name) as COUNTY  "
+" FROM groups "
+" join member_details on member_details.group_id=groups.group_id "
+" join target_population on groups.target_pop_id=target_population.target_id"
+" join district on groups.district_id=district.district_id"
+" join partner on groups.partner_id=partner.partner_id "
+" join curriculum on groups.target_pop_id=curriculum.target_id,county"
+" where district.county_id=county.county_id"  
+" and  sex !='' and sessions_attended >0 "
+" and sessions_attended=no_of_lessons  "
+" and YEAR='"+year+"' "+targetqr  
+" group by PARTNER,TARGET_POPULATION,COUNTY,SEX,AGE_BRACKET,MONTHS"  

+" order by  PARTNER,TARGET_POPULATION,SEX DESC";
    
    
    
    
    
    
    
    System.out.println(qry);
    
    
    
    
    
            


            dbConn conn = new dbConn();
            conn.rs=conn.st.executeQuery(qry);

        //create the headers of the report. NB;Data will be inserted into a table which is already defined and awaits only data    
           
            
              XSSFRow allsitescolumnheader = rawdata.createRow(0);
               // allsitescolumnheader.setHeightInPoints(30);
                XSSFCell rwcolheader=null;
                
              maxmerging=columnheaders.length;
                
                for(int d=0;d<maxmerging;d++){
                 rwcolheader = allsitescolumnheader.createCell(d);
                  rwcolheader.setCellValue(columnheaders[d]);
                  //rwcolheader.setCellStyle(th_style);
                  
                  
                }
            
            
              XSSFRow rw2 = null;

           

            int rowno=0;
            
        
                
                while (conn.rs.next()) {
                    // read the data
      System.out.println("==================" + conn.rs.getString(1) + "_" + conn.rs.getString(2) + " _ " + conn.rs.getString(3) + " _ " + conn.rs.getString(4) + " _ " + conn.rs.getString(5) + " _ " + conn.rs.getString(6));

                    rowno++;
            
            rw2=rawdata.createRow(rowno);
            rw2.setHeightInPoints(25);
            
            //Participant
            XSSFCell cell1 = rw2.createCell(0);
            cell1.setCellValue(conn.rs.getInt(1));
            //cell1.setCellStyle(innerdata_style);
            
            //SEX
            XSSFCell cell2 = rw2.createCell(1);
            cell2.setCellValue(conn.rs.getString(2));
           // cell2.setCellStyle(innerdata_style);
            
            
            //AGE BRACKET
             XSSFCell cell3 = rw2.createCell(2);
            cell3.setCellValue(conn.rs.getString(3));
           // cell3.setCellStyle(innerdata_style);
            
            //Partner
             XSSFCell cell4 = rw2.createCell(3);
            cell4.setCellValue(conn.rs.getString(4));
           // cell4.setCellStyle(innerdata_style);
            
            //Target Population
             XSSFCell cell5 = rw2.createCell(4);
            cell5.setCellValue(conn.rs.getString(5));
            //cell5.setCellStyle(innerdata_style);
            
            //Months
            XSSFCell cell6 = rw2.createCell(5);
            cell6.setCellValue(conn.rs.getString(6));
           // cell6.setCellStyle(innerdata_style);
            
            //Year
            XSSFCell cell7 = rw2.createCell(6);
            cell7.setCellValue(conn.rs.getInt(7));
          //  cell7.setCellStyle(innerdata_style);
            
            //County
            XSSFCell cell8 = rw2.createCell(7);
            cell8.setCellValue(conn.rs.getString(8));
          //  cell8.setCellStyle(innerdata_style);
            //districtid
           
            
           
            
                }
         


            System.out.println("Finished query");

            
           
            Date dat = new Date();

        String dat1 = dat.toString().replace(" ", "_");

        // write it as an excel attachment
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=kepms_format_age_brackets_report" + dat1 + ".xlsm");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
        pkg.close();
        
       // response.sendRedirect("index.jsp");
        
         File file= new File(pathtodelete);
        
        if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}

        
        
            
        } catch (InvalidFormatException ex) {
            Logger.getLogger(agebasedkepms.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(agebasedkepms.class.getName()).log(Level.SEVERE, null, ex);
        }
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
