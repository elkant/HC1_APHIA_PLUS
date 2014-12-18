<%-- 
    Document   : reporter
    Created on : Sep 22, 2013, 1:55:45 PM
    Author     : The Aluodos
--%>

<%@page import="hc.dbConn"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.poi.hssf.util.CellRangeAddress"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFCell"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFRow"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFSheet"%>
<%@page import="org.apache.poi.hssf.usermodel.HSSFWorkbook"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>District 1st quarter report</title>
    </head>
    <body>
      
        <%
        
String district_id="",district_name="",financial_year="",quarter="";
String path="",path2="";






            dbConn conn=new dbConn();
//            RECEIVE PARAMETERS
//            *****************************************
//        district_id=request.getParameter("district_id");
//        financial_year=request.getParameter("financial_year");
//        quarter=request.getParameter("quarter");
        
        
                district_id="2";
        financial_year="2011";
        quarter="Q1";
        
//        ***********creaate excell workbook, sheet and the first row******************
        
   HSSFWorkbook wb=new HSSFWorkbook();
   HSSFSheet shet1=wb.createSheet();
   HSSFRow rw1=shet1.createRow(1);
   HSSFCell cell;
   cell=rw1.createCell(4);
   cell.setCellValue("Frst Quaerter Report");
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,4,12));    
        
   HSSFRow rw2=shet1.createRow(2) ;
   rw2.createCell(2).setCellValue("District");
   rw2.createCell(3).setCellValue("Year");
   rw2.createCell(4).setCellValue("Qurter");

      HSSFRow rw6=shet1.createRow(6) ;
 rw6.createCell(2).setCellValue("Achieved Men");
  rw6.createCell(3).setCellValue("Achieved Women");
   rw6.createCell(4).setCellValue("Total Achieved");
   
        
        String district_selector="select * from districts where districtID='"+district_id+"'";
        conn.rs=conn.st.executeQuery(district_selector);
        conn.rs.next();
        district_name=conn.rs.getString("DistrictName");
        String results_selector="select * from indicatorresults where districtID='"+district_id+"' && financialYear='"+financial_year+"' && reportingPeriod='"+quarter+"'";
        conn.rs=conn.st.executeQuery(results_selector);
        while(conn.rs.next()){
         int menAchieved=conn.rs.getInt("menAchieved");
         int womenAchieved=conn.rs.getInt("womenAchieved");
         int totalAchieved=menAchieved+womenAchieved;
         
     HSSFRow rw7=shet1.createRow(7) ;
    rw7.createCell(2).setCellValue(menAchieved);
    rw7.createCell(3).setCellValue(womenAchieved);
    rw7.createCell(4).setCellValue(totalAchieved);  
}
        
     String baseline_selector="select * from baseline where District='"+district_id+"' && FinancialYear='"+financial_year+"' && Quarter='"+quarter+"'";
        conn.rs=conn.st.executeQuery(results_selector);
        while(conn.rs.next()){
         int targetedmen=conn.rs.getInt("QtargetMen");
         int targetedwomen=conn.rs.getInt("QtargetWomen");
      
//         *****************create row for display ************************
         
         HSSFRow rw3=shet1.createRow(4) ;
 rw3.createCell(2).setCellValue(district_name);
  rw3.createCell(3).setCellValue(targetedmen);
   rw3.createCell(4).setCellValue(targetedwomen);   
}
         File[] roots = File.listRoots();

    /* Check what file roots exist and use either of them to create paths */
    for (File root : roots) {
    //  System.out.println("File system root: " + root.getAbsolutePath());
      //if drive c exists, then use it to store the file
      
      if(root.getAbsolutePath().equals("C:\\")){
       path="C:\\programprogressReports\\quarterlyReports";
       path2="C:\\HC_REPORTS";
        session.setAttribute("saved_path", "<font color=\"grey\"><a href=\""+path+"\">"+path+"</a></font><br/> ");
        break;
      }
     else if(root.getAbsolutePath().equals("D:\\")){
       path="D:\\programprogressReports\\quarterlyReports";
       path2="D:\\programprogressReports";
        session.setAttribute("saved_path", "<font color=\"grey\">"+path+"</font><br/> ");
       
      }
      else {
      //if no drive detected
       session.setAttribute("saved_path", "<font color=\"grey\">Error While saving Excel file!! No drives C or D in host Machine</font><br/> ");
      }
                            }//end of for 
     new File(path2).mkdir();
         new File(path).mkdir();
       FileOutputStream fileOut1 = new FileOutputStream(path+"/district.xls");
     %>
    </body>
</html>