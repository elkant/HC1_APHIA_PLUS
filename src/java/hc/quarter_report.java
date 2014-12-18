/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
public class quarter_report extends HttpServlet {
String county_id="", indicator_id="",year="",quarter="";
String indicator="",indicator_number="",district_id="";
String activity_title="",start_date="",end_date="";
int m_achieved=0,w_achieved=0,sub_total=0;
int m_total=0,w_total=0,total=0,j=0,i=5;
String district_name="";
HttpSession session;
String path="",path2="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn=new dbConn();
            session=request.getSession();
//            *******************RECEIVE PARAMETERS*******************************
            county_id=request.getParameter("county_id");
            indicator_id=request.getParameter("indicator_id");
            year=request.getParameter("year");
            quarter=request.getParameter("quarter");
            
//                        county_id="1";
//            indicator_id="1";
//            year="2011";
//            quarter="Q1";
            
            
            String indicator_selector="select * from indicatortitles where titleID='"+indicator_id+"'";
            conn.rs=conn.st.executeQuery(indicator_selector);
            conn.rs.next();
            indicator=conn.rs.getString("title");
//            ****************HEADING IN EXCEL**********************************************************
               HSSFWorkbook wb=new HSSFWorkbook();
   HSSFSheet shet1=wb.createSheet();
   HSSFRow rw1=shet1.createRow(1);
   HSSFCell cell;
   cell=rw1.createCell(4);
   cell.setCellValue(indicator);
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,4,12)); 
            
            
            
            
            
            indicator_number=conn.rs.getString("titleNO");
              HSSFRow rw2=shet1.createRow(2);
   HSSFCell cell2;
   cell2=rw2.createCell(4);
   cell2.setCellValue("indicator Number: "+indicator_number);
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(2,2,4,12)); 
            
  HSSFRow rw3=shet1.createRow(3);
   HSSFCell cell3;
   cell3=rw3.createCell(4);
   cell3.setCellValue("DISAGGREGATED BY : Location, event, date and Gender");
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(3,3,4,12));   
    
     HSSFRow rw4=shet1.createRow(4) ;
   rw4.createCell(4).setCellValue("Geographica Location");
   rw4.createCell(5).setCellValue("Activity title");
   rw4.createCell(6).setCellValue("start date");
   rw4.createCell(7).setCellValue("end date");
   rw4.createCell(8).setCellValue("women");
   rw4.createCell(9).setCellValue("men");
   rw4.createCell(10).setCellValue("sub total");
            i=5;
           String district_selector="select * from districts where countyID='"+county_id+"'" ;
           conn.rs=conn.st.executeQuery(district_selector);
           while(conn.rs.next()){
            district_id=conn.rs.getString("districtID") ;
            district_name=conn.rs.getString("DistrictName");
            String activities_selector="select * from indicatoractivities where DistrictID='"+district_id+"'";
            conn.rs1=conn.st1.executeQuery(activities_selector);
            while(conn.rs1.next())
            {
            activity_title=conn.rs1.getString("activityTitle").toString();
            start_date=conn.rs1.getString("startDate").toString();
           end_date= conn.rs1.getString("endDate").toString();
           w_achieved=conn.rs1.getInt("women");
            m_achieved=conn.rs1.getInt("men"); 
            sub_total=conn.rs1.getInt("subtotals");
//            ****************EXCEL FOR ONE ROW*********************************
          HSSFRow rwi=shet1.createRow(i) ;
   rwi.createCell(4).setCellValue(district_name);
   rwi.createCell(5).setCellValue(activity_title);
   rwi.createCell(6).setCellValue(start_date);
   rwi.createCell(7).setCellValue(end_date);
   rwi.createCell(8).setCellValue(w_achieved);
   rwi.createCell(9).setCellValue(m_achieved);
   rwi.createCell(10).setCellValue(sub_total); 
   
     System.out.println("vALUE OF I IS :"+i);         
            
            
            
//           ***********GET THE TOTALS******************************** 
            w_total=w_total+w_achieved;
            m_total=m_total+ m_achieved;
            total=total+sub_total;
 
            i++;
            j=i++;
            }
          
           
           }
          System.out.println("VALUE OF J IS :"+j);
                     HSSFRow rwj=shet1.createRow(j) ;
   rwj.createCell(4).setCellValue("Total");
   rwj.createCell(8).setCellValue(w_total);
   rwj.createCell(9).setCellValue(m_total);
   rwj.createCell(10).setCellValue(total);
     shet1.addMergedRegion(new CellRangeAddress(j,j,4,7)); 
     m_achieved=w_achieved=sub_total=0;
m_total=w_total=total=0;
   int k=j+1;       
  HSSFRow rwk=shet1.createRow(k) ;
   rwk.createCell(2).setCellValue("Results");

     shet1.addMergedRegion(new CellRangeAddress(k,k,2,12));     
       int l=k+1;
             
  HSSFRow rwl=shet1.createRow(l) ;
   rwl.createCell(2).setCellValue("District");
   rwl.createCell(3).setCellValue("Baseline");
   rwl.createCell(5).setCellValue("Prior results");
   rwl.createCell(7).setCellValue("this reporting period");
   rwl.createCell(11).setCellValue("fy "+year+" target");

     shet1.addMergedRegion(new CellRangeAddress(l,l,3,4));
     shet1.addMergedRegion(new CellRangeAddress(l,l,5,6));
      shet1.addMergedRegion(new CellRangeAddress(l,l,7,10));
       shet1.addMergedRegion(new CellRangeAddress(l,l,11,12));
      int m=l+1;
       HSSFRow rwm=shet1.createRow(m) ;
   rwm.createCell(5).setCellValue("Achieved");
   rwm.createCell(7).setCellValue("Target");
   rwm.createCell(9).setCellValue("Achieved");
   rwm.createCell(11).setCellValue("Target");
   int n=m+1;
          HSSFRow rwn=shet1.createRow(n) ;
   rwn.createCell(3).setCellValue("W");
   rwn.createCell(4).setCellValue("M");
   rwn.createCell(5).setCellValue("W");
   rwn.createCell(6).setCellValue("M");
   rwn.createCell(7).setCellValue("W");
   rwn.createCell(8).setCellValue("M");
   rwn.createCell(9).setCellValue("W");
   rwn.createCell(10).setCellValue("M");
   rwn.createCell(11).setCellValue("W");
   rwn.createCell(12).setCellValue("M");
  int p=n+1;    
           district_name=district_id="";
            
//            *****************SEND TO THE EXCELL FILE**************************
               
               
               
               
               
           
                       String district_selector5="select * from districts where countyID='"+county_id+"'" ;
           conn.rs=conn.st.executeQuery(district_selector5);
           while(conn.rs.next()){
           HSSFRow rwp=shet1.createRow(p) ; 
            district_id=conn.rs.getString("districtID") ;
            district_name=conn.rs.getString("DistrictName");
            int q=p;
//            **************GET RESULTS***********************************
            String result_selector="select * from baseline where District='"+conn.rs.getString("districtID")+"' && FinancialYear='"+year+"' && Quarter='"+quarter+"'";
            conn.rs1=conn.st1.executeQuery(result_selector);
            while (conn.rs1.next()){
               rwp.createCell(2).setCellValue(district_name);  
//            ***************YEARLY TARGET*************************************************
                String yr="", w_target="",m_target="";
                yr=conn.rs1.getString("FinancialYear");
                w_target=conn.rs1.getString("endTargetWomen");
                m_target=conn.rs1.getString("endTargetMen");
                
   rwp.createCell(11).setCellValue(w_target);
   rwp.createCell(12).setCellValue(m_target);

                
                
                
//                *********************BASELINE*******************************************
             String baseline_women="",baseline_men="";
             baseline_women=conn.rs1.getString("womenBaseline").toString();
             baseline_men=conn.rs1.getString("menBaseline").toString(); 
   rwp.createCell(3).setCellValue(baseline_women);
   rwp.createCell(4).setCellValue(baseline_men);
             
//             ***********targeted quarterly***************
             String targeted_women="",targeted_men="";
             targeted_women=conn.rs1.getString("QtargetWomen").toString();
             targeted_men=conn.rs1.getString("QtargetMen").toString();
            
             rwp.createCell(7).setCellValue(targeted_women);
   rwp.createCell(8).setCellValue(targeted_men);
             
//             **************GET DATA FOR PREVIOUS QUARTERS********************************
             
           if(quarter=="Q1") {
            String   p_quarter="Q4";
           int p_year=Integer.parseInt(year)-1;
         String result_selector2="select * from baseline where District='"+conn.rs.getString("districtID")+"' && FinancialYear='"+p_year+"' && Quarter='"+p_quarter+"'";
            conn.rs1=conn.st1.executeQuery(result_selector);
            while (conn.rs1.next()){
             String p_baseline_women="",p_baseline_men="";
             p_baseline_women=conn.rs1.getString("womenBaseline").toString();
             p_baseline_men=conn.rs1.getString("menBaseline").toString();
             
//               ****************DISPLAY BASELINE IN EXCELL****************************
   rwp.createCell(5).setCellValue(p_baseline_women);
   rwp.createCell(6).setCellValue(p_baseline_men);
               
               
            }   
           }  
            if(quarter=="Q4") {
            String   p_quarter="Q3";
           
         String result_selector2="select * from baseline where District='"+conn.rs.getString("districtID")+"' && FinancialYear='"+year+"' && Quarter='"+p_quarter+"'";
            conn.rs1=conn.st1.executeQuery(result_selector2);
            while (conn.rs1.next()){
             String p_baseline_women="",p_baseline_men="";
             p_baseline_women=conn.rs1.getString("womenBaseline").toString();
             p_baseline_men=conn.rs1.getString("menBaseline").toString();      
               
         //               ****************DISPLAY BASELINE IN EXCELL****************************
   rwp.createCell(5).setCellValue(p_baseline_women);
   rwp.createCell(6).setCellValue(p_baseline_men);
                     
               
               
            }   
           }    
             if(quarter=="Q3") {
            String   p_quarter="Q2";
           
         String result_selector2="select * from baseline where District='"+conn.rs.getString("districtID")+"' && FinancialYear='"+year+"' && Quarter='"+p_quarter+"'";
            conn.rs1=conn.st1.executeQuery(result_selector2);
            while (conn.rs1.next()){
             String p_baseline_women="",p_baseline_men="";
             p_baseline_women=conn.rs1.getString("womenBaseline").toString();
             p_baseline_men=conn.rs1.getString("menBaseline").toString();      
               
            //               ****************DISPLAY BASELINE IN EXCELL****************************
   rwp.createCell(5).setCellValue(p_baseline_women);
   rwp.createCell(6).setCellValue(p_baseline_men);
                  
               
               
            }   
           }    
              if(quarter=="Q2") {
            String   p_quarter="Q1";
           
         String result_selector2="select * from baseline where District='"+conn.rs.getString("districtID")+"' && FinancialYear='"+year+"' && Quarter='"+p_quarter+"'";
            conn.rs1=conn.st1.executeQuery(result_selector2);
            while (conn.rs1.next()){
             String p_baseline_women="",p_baseline_men="";
             p_baseline_women=conn.rs1.getString("womenBaseline").toString();
             p_baseline_men=conn.rs1.getString("menBaseline").toString();      
               
          //               ****************DISPLAY BASELINE IN EXCELL****************************
   rwp.createCell(5).setCellValue(p_baseline_women);
   rwp.createCell(6).setCellValue(p_baseline_men);
                    
               
               
            }   
           }    
                
//         *******************DISPLAY IN EXCELL THIS DATA FOR PREVIOUS QUARTER*******************************       
                
                
                
                
            }
            
//  ***********************TARGETED AND ACHIEVED IN THAT QUARTER ******************************************
            String result_selector2="select * from indicatorresults where districtID='"+conn.rs.getString("districtID")+"' && financialYear='"+year+"' && reportingPeriod='"+quarter+"'";
            conn.rs1=conn.st1.executeQuery(result_selector2);
            while(conn.rs1.next()){
             String menAchieved="",womenAchieved="";
             menAchieved=conn.rs1.getString("menAchieved");
             womenAchieved=conn.rs1.getString("womenAchieved");
//          ********************Write to excell the achieved***************************************      
           //               ****************DISPLAY BASELINE IN EXCELL****************************
   rwp.createCell(9).setCellValue(menAchieved);
   rwp.createCell(10).setCellValue(womenAchieved);
                    
               
            }
   p++; 
           }
            
          File[] roots = File.listRoots();

    /* Check what file roots exist and use either of them to create paths */
    for (File root : roots) {
    //  System.out.println("File system root: " + root.getAbsolutePath());
      //if drive c exists, then use it to store the file
      
      if(root.getAbsolutePath().equals("C:\\")){
       path="C:\\programprogressReports\\quarterlyReports";
//       path2="C:\\HC_REPORTS";
        session.setAttribute("saved_path", "<font color=\"grey\"><a href=\""+path+"\">"+path+"</a></font><br/> ");
        break;
      }
     else if(root.getAbsolutePath().equals("D:\\")){
       path="D:\\programprogressReports\\quarterlyReports";
//       path2="D:\\programprogressReports";
        session.setAttribute("saved_path", "<font color=\"grey\">"+path+"</font><br/> ");
       
      }
      else {
      //if no drive detected
       session.setAttribute("saved_path", "<font color=\"grey\">Error While saving Excel file!! No drives C or D in host Machine</font><br/> ");
      }
                            }//end of for 
//     new File(path2).mkdirs();
         new File(path).mkdirs();
       FileOutputStream fileOut = new FileOutputStream(path+"/new_district1.xls");
       
           wb.write(fileOut);
    fileOut.close();   
            
          response.sendRedirect(path+"/new_district1.xls");  

        } finally {            
            out.close();
        }
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
            Logger.getLogger(quarter_report.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(quarter_report.class.getName()).log(Level.SEVERE, null, ex);
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
}
