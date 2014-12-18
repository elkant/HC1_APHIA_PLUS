/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import hc.dbConn;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;

/**
 *
 * @author Geofrey Nyabuto
 */
public class excel_groups_completion extends HttpServlet {

    HttpSession session;
    int max_lessons = 0, current_sessions = 0;
    String group_name = "";
    int completed = 0, partial = 0, incomplete = 0, over = 0, total_sessions = 0, sessions_attended = 0, counter = 0;
    double all_per = 0, partial_per = 0, incomplete_par = 0, all = 0, attended_per = 0, over_per = 0;
    String path, path2;
    String partner_name = "", target_name = "";
    private String nextpage;
    String start_date = "";
    String end_date = "", name = "", facilitator_id = "", pe2 = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DATE);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);

        String dates = year + "-" + month + "-" + date + "-" + hour + "-" + min + "-" + sec;
        String facilitator_name = "";
        String start_date = "";
        String end_date = "";


        session = request.getSession();
        dbConn conn = new dbConn();

        String period = "", yea = "";
//            period="1";
//            yea="2012";

        String periods[] = session.getAttribute("period").toString().split(",");
        yea = session.getAttribute("year").toString();
        int prev_year = Integer.parseInt(yea) - 1;
        String pe = ",";
        for (int i = 0; i < periods.length; i++) {

            if (periods[i] != null && !periods[i].equals("")) {
                pe += periods[i] + ",";
            }

        }
        pe2 = "   Period  :   ";
        if (pe.contains("1") && pe.contains("2") && pe.contains("3") && pe.contains("4")) {
            pe2 += "Oct " + prev_year + " - Sept";
        } else if (!pe.contains("1") && pe.contains("2") && pe.contains("3") && pe.contains("4")) {
            pe2 += "Jan - Sept";
        } else if (!pe.contains("1") && !pe.contains("2") && pe.contains("3") && pe.contains("4")) {
            pe2 += "April - Sept";
        } else if (!pe.contains("1") && !pe.contains("2") && !pe.contains("3") && pe.contains("4")) {
            pe2 += "July - Sept";
        } else if (pe.contains("1") && pe.contains("2") && pe.contains("3") && !pe.contains("4")) {
            pe2 += "Oct " + prev_year + " - June";
        } else if (pe.contains("1") && pe.contains("2") && !pe.contains("3") && !pe.contains("4")) {
            pe2 += "Oct " + prev_year + " - April";
        } else if (pe.contains("1") && !pe.contains("2") && !pe.contains("3") && !pe.contains("4")) {
            pe2 += "Oct - Dec " + prev_year + "";
        } else if (!pe.contains("1") && pe.contains("2") && pe.contains("3") && !pe.contains("4")) {
            pe2 += "Jan - June";
        } else {
            pe2 = "   Periods : ";
            for (int i = 0; i < periods.length; i++) {
                String pe1 = periods[i];
                if (pe1 != null && !pe1.equals("")) {
                    String ct_name = "SELECT * FROM period WHERE id='" + pe1 + "'";
                    conn.rs = conn.st.executeQuery(ct_name);
                    if (conn.rs.next() == true) {
                        if (i + 1 < periods.length) {
                            pe2 += conn.rs.getString(2) + ", ";
                        }
                        if (i + 1 == periods.length) {
                            pe2 += conn.rs.getString(2);
                        }
                    }
                }
            }
        }


//            ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet shet1 = wb.createSheet();
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 14);
        font.setFontName("Arial Black");
//    font.setItalic(true);
//    font.setBoldweight((short)24);
        font.setColor((short) 0000);
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setWrapText(true);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        HSSFFont font2 = wb.createFont();
        font2.setFontHeightInPoints((short) 18);
        font2.setFontName("Arial Black");
//    font.setItalic(true);
        font2.setBoldweight((short) 24);
        font2.setColor((short) 0000);
        CellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//  HSSFSheet sheet1 = wb.getSheetAt(0);
        shet1.setColumnWidth(0, 3000);
        shet1.setColumnWidth(1, 6000);
        shet1.setColumnWidth(2, 9000);
        shet1.setColumnWidth(3, 5000);
        shet1.setColumnWidth(4, 3000);
        shet1.setColumnWidth(5, 3000);
        shet1.setColumnWidth(6, 3000);
        shet1.setColumnWidth(7, 5000);
        shet1.setColumnWidth(8, 5000);
        shet1.setColumnWidth(9, 5000);
//    shet1.setColumnWidth(10, 4000); 
//    shet1.setColumnWidth(11, 4000); 
        HSSFRow rw1 = shet1.createRow(0);
        HSSFCell cell;
        cell = rw1.createCell(0);
        cell.setCellValue("Overall Groups Attendance Report for " + pe2 + "  and pepfar year : " + yea);
        cell.setCellStyle(style);
        rw1.setHeightInPoints(30);


//  Merge the cells
        shet1.addMergedRegion(new CellRangeAddress(0, 0, 0, 9));

        HSSFRow rw4 = shet1.createRow(1);
        rw4.setHeightInPoints(35);
//    rw4.setRowStyle(style2);
// rw4.createCell(1).setCellValue("Number");
        HSSFCell cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9, cell10;

        cell1 = rw4.createCell(0);
        cell2 = rw4.createCell(1);
        cell3 = rw4.createCell(2);
        cell4 = rw4.createCell(3);
        cell5 = rw4.createCell(4);
        cell6 = rw4.createCell(5);
        cell7 = rw4.createCell(6);
        cell8 = rw4.createCell(7);
        cell9 = rw4.createCell(8);
        cell10 = rw4.createCell(9);
//   cell11=rw4.createCell(10);
//   cell12=rw4.createCell(11);
//   cell13=rw4.createCell(12);

        cell1.setCellValue("Partner Name");
        cell2.setCellValue("Target Population");
        cell3.setCellValue("Group Name");
        cell4.setCellValue("Facilitator Name");
        cell5.setCellValue("Total Individuals");
        cell6.setCellValue("Start Date");
        cell7.setCellValue("End Date");
        cell8.setCellValue("Completed All Sessions");
        cell9.setCellValue("Attended 50% and over but not all sessions");
        cell10.setCellValue("Attended less 50% of total sessions");
// cell11.setCellValue("Overall Attendance"); 

        HSSFCellStyle stylex = wb.createCellStyle();
        stylex.setFillForegroundColor(HSSFColor.LIME.index);
        stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

        HSSFFont fontx = wb.createFont();
        fontx.setColor(HSSFColor.DARK_BLUE.index);
        stylex.setFont(fontx);
        stylex.setWrapText(true);
        stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cell1.setCellStyle(stylex);
        cell2.setCellStyle(stylex);
        cell3.setCellStyle(stylex);
        cell4.setCellStyle(stylex);
        cell5.setCellStyle(stylex);
        cell6.setCellStyle(stylex);
        cell7.setCellStyle(stylex);
        cell8.setCellStyle(stylex);
        cell9.setCellStyle(stylex);
        cell10.setCellStyle(stylex);
//cell11.setCellStyle(stylex);
//cell12.setCellStyle(stylex);


        HSSFCellStyle style_border = wb.createCellStyle();

        style_border.setWrapText(true);
        style_border.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style_border.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style_border.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style_border.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style_border.setAlignment(style_border.ALIGN_CENTER);
         style_border.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
 HSSFCellStyle style_border2 = wb.createCellStyle();

        style_border2.setWrapText(false);
        style_border2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style_border2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style_border2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style_border2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style_border2.setAlignment(style_border2.ALIGN_LEFT);
        style_border2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        int i = 2;
        conn.rs_6=conn.st_6.executeQuery("Select distinct (groups.partner_id) from groups order by partner_id");
        
        
         int partnerloop=2;
        int partnerloopcopy=2; 
     
         int tagloop=2;
        int tagloopcopy=2; 
        String prevtag="";
        
        while (conn.rs_6.next()){
        
          
            //^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^GROOUP NAMES WHILE^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
        String groups_selector = "select * from groups where partner_id='"+conn.rs_6.getString(1) +"' order by group_name";
        conn.rs = conn.st.executeQuery(groups_selector);
       
        
        
        while (conn.rs.next()) {
      System.out.println("^^^^^^^^^^^^^^Partner loop is :"+partnerloop+"  Partnerloop copy__"+partnerloopcopy+" group is "+conn.rs.getString("group_name")+" Partner is ::"+partner_name);
            
          
            group_name = conn.rs.getString("group_name");
//                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                ^^^^^^^^^^^^^^^^^Get total sessions per target population^^^^^^^^^^^^^^^^^^^^^^^
            String curriculum_selector = "select * from curriculum where target_id='" + conn.rs.getString("target_pop_id") + "'";
            conn.rs0 = conn.st0.executeQuery(curriculum_selector);
            if (conn.rs0.next() == true) {
                max_lessons = conn.rs0.getInt("no_of_lessons");
            }
//                ^^^^^^^^^^^^^^^GET PARTNER NAME^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            String partner_selector = "select * from partner where partner_id='" + conn.rs.getString("partner_id") + "' order by partner_name";
            conn.rs0 = conn.st0.executeQuery(partner_selector);
            if (conn.rs0.next() == true) {
                partner_name = conn.rs0.getString("partner_name");
                
       //^^^^^everytime a target population changes, merge the          
                
                if(!partner_name.equals(prevtag)){
                
                 //dont merge if its the firt time  
              if(!prevtag.equals("")){
                 if(tagloopcopy+1<=tagloop-1){
                System.out.println(" Target Pop loop if%%%%%%%%%%%%%%%%%%%%%%%%%%%%%Should have merged the cell at this point.."+(partnerloopcopy+1)+" ** "+(partnerloop-1));
            
                  // shet1.addMergedRegion(new CellRangeAddress(tagloopcopy,tagloop-1,1,1));
                   
                    }
               tagloopcopy=tagloop;
              }
                  prevtag=partner_name;
                      
                 
                       
                       
                       
                }else{
                
               System.out.println(" Target Pop loop else========================================================================Should have merged the cell at this point.."+(tagloopcopy+1)+" ** "+(tagloop-1));
             
                }
                
                
            }
//         ^^^^^^^^^^TARGET NAME^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^       
            String target_selector = "select * from target_population where target_id='" + conn.rs.getString("target_pop_id") + "'";
            conn.rs0 = conn.st0.executeQuery(target_selector);
            if (conn.rs0.next() == true) {
                target_name = conn.rs0.getString("population_name");
            }
//                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//  ^^^^^^^^^^^^^^^^^^^^GET NUMBER OF SESSIONS ATTENDED BY EACH MEMBER^^^^^^^^^^^^^^^^^
                ArrayList facilitatorsid=new ArrayList(); 
            
            if (periods != null) {

                for (int m = 0; m < periods.length; m++) {
                    if (periods[m] != null && !periods[m].equals("")) {
                        period = periods[m];
                        System.out.println(" the period is  :  " + period + " and the year is  :  " + yea);

                        String participant_selector = "select count(member_id) from member_details where group_id='" + conn.rs.getString("group_id") + "' && sessions_attended>'" + max_lessons + "' && year='" + yea + "' && period='" + period + "'";
                        conn.rs4 = conn.st4.executeQuery(participant_selector);
                        if (conn.rs4.next() == true) {
                            over += conn.rs4.getInt(1);
//           sessions_attended+=conn.rs4.getInt(2);
                        }
                        String participant_selector1 = "select count(member_id) from member_details where group_id='" + conn.rs.getString("group_id") + "' && sessions_attended='" + max_lessons + "' && year='" + yea + "' && period='" + period + "'";
                        conn.rs4 = conn.st4.executeQuery(participant_selector1);
                        if (conn.rs4.next() == true) {
                            completed += conn.rs4.getInt(1);
//           sessions_attended+=conn.rs4.getInt(2);
                        }
                        String participant_selector2 = "select count(member_id) from member_details where group_id='" + conn.rs.getString("group_id") + "' && sessions_attended<'" + max_lessons + "' && sessions_attended>='" + max_lessons / 2 + "' && year='" + yea + "' && period='" + period + "'";
                        conn.rs4 = conn.st4.executeQuery(participant_selector2);
                        if (conn.rs4.next() == true) {
                            partial += conn.rs4.getInt(1);
//           sessions_attended+=conn.rs4.getInt(2);
                        }
                        String participant_selector3 = "select count(member_id) from member_details where group_id='" + conn.rs.getString("group_id") + "' && sessions_attended<'" + max_lessons / 2 + "' && year='" + yea + "' && period='" + period + "'";
                        conn.rs4 = conn.st4.executeQuery(participant_selector3);
                        if (conn.rs4.next() == true) {
                            incomplete += conn.rs4.getInt(1);
//          sessions_attended+=conn.rs4.getInt(2);
                        }
                        String member_det_selector = "select * from member_details where group_id='" + conn.rs.getString("group_id") + "'&& year='" + yea + "' && period='" + period + "' ";
                        conn.rs1 = conn.st1.executeQuery(member_det_selector);
                        String member_id = "";
                    ArrayList membersid= new ArrayList();    
                        //be able to pick more than one facilitators who taught that class
                   
                        
                        while (conn.rs1.next()) {
                            
                            member_id = conn.rs1.getString("member_id");
                            membersid.add(conn.rs1.getString("member_id"));
                                              
                        
                        }
                      
                        for(int p=0;p<membersid.size();p++){
                            
                      String register_selector = "select * from register_attendance where member_id='" + membersid.get(p) + "'";
                        conn.rs2 = conn.st2.executeQuery(register_selector);
                        if (conn.rs2.next() == true) {
                            
                            facilitator_id = conn.rs2.getString("facilitator_id");
                            if(!facilitatorsid.contains(conn.rs2.getString("facilitator_id"))){
                            facilitatorsid.add(conn.rs2.getString("facilitator_id"));
                            }
                            
                            
                        }
                    
                        
                        
                        }//end of for loop
               
                  }
                }
            }
            all = completed + partial + incomplete;

            if (all * max_lessons == 0) {
                session.setAttribute("saved_path", "Failed to generate the excell sheet: Divide by Zero Error");
                nextpage = "groups_completion_rate.jsp";
            }
            if ((all * max_lessons) > 0) {
//            attended_per=(sessions_attended*100)/(all*max_lessons); 
                all_per = (completed * 100) / all;
                partial_per = (partial * 100) / all;
                incomplete_par = (incomplete * 100) / all;
                String aller = String.format("%.0f", all_per);
                String aller_partial = String.format("%.0f", partial_per);
                String aller_incomplete = String.format("%.0f", incomplete_par);
//String attended=String.format("%.0f", attended_per);
                String over_100 = String.format("%.0f", over_per);
//try
                System.out.println("Group name " + group_name);
                System.out.println("percentage over " + over_100);
                System.out.println("percentage completed " + aller);
                System.out.println("partially completed " + aller_partial);
                System.out.println("Percentage Incomplete " + aller_incomplete);
//System.out.println("Overall Group Attendance "+attended);
                System.out.println("");
                System.out.println("++++++++====FACILITATORS ARRAYLIST SIZE IS"+facilitatorsid.size());
                
                
                
                
                
                //Get the list of facilitators
                for (int a=0;a<facilitatorsid.size();a++){
                
                String facilitator_selector = "select * from facilitator_details where facilitator_id ='" + facilitatorsid.get(a) + "'";
               
                System.out.println("FACILITITATORS QUERY"+facilitator_selector);
                
                conn.rs3 = conn.st3.executeQuery(facilitator_selector);
                String sname = "", fname = "";
                
                if (conn.rs3.next() == true) {
                    fname = conn.rs3.getString("first_name");
                    sname = conn.rs3.getString("sur_name");
                                             }
                
                facilitator_name += fname + " " + sname;
                //add comma if the document hasnt reached the end
                
                if(a<facilitatorsid.size()-1){facilitator_name +=",";}
                
               
                
                
                String date_selector = "select * from new_topic where facilitator_id='" +  facilitatorsid.get(a)  + "'";
                conn.rs3 = conn.st3.executeQuery(date_selector);
                while (conn.rs3.next() == true) {
                    System.out.println("start date  :  " + start_date);
                    System.out.println("end date  :  " + end_date);
                    String start_date1 = conn.rs3.getString("start_date");
                    String[] dater1 = start_date1.split("/");
                    String mn1 = dater1[0];
                    String dy1 = dater1[1];
                    String yr1 = dater1[2];
//start_date=conn.rs3.getString("start_date");
                    
                    String tempstartdate= dy1 + "/" + mn1 + "/" + yr1;
                    start_date = tempstartdate;
                    String end_date1 = conn.rs3.getString("end_date");
                    String[] dater2 = end_date1.split("/");
                    String mn2 = "00", dy2 = "00", yr2 = "0000";
                    if (end_date1 != null && !end_date1.equals("")) {
                        mn2 = dater2[0];
                        dy2 = dater2[1];
                        yr2 = dater2[2];
                    }
                    
                    String tempenddate=dy2 + "/" + mn2 + "/" + yr2;
                    end_date = tempenddate;
//end_date=conn.rs3.getString("end_date");
                }
                
                 }//end of facilitators for loop
                
                
                facilitator_id = "";
               // empty the facilitators array list
                if(facilitatorsid.size()>0){facilitatorsid.clear();}
               
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//^^^^^^^^^^^^^^^OUTPUT TO EXCELL^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                if (all > 0) {
                    HSSFRow rwi = shet1.createRow(i);
//   rwi.createCell(1).setCellValue(i-3);
                    rwi.setHeightInPoints(20);
                    HSSFCell c0, c1, c2, c3, c4, c5, c6, c7, c8, c9;
                    c0 = rwi.createCell(0);
                    c1 = rwi.createCell(1);
                    c2 = rwi.createCell(2);
                    c3 = rwi.createCell(3);
                    c4 = rwi.createCell(4);
                    c4.setCellValue(all);
                    c5 = rwi.createCell(5);
                    c6 = rwi.createCell(6);
                    c7 = rwi.createCell(7);
                    c8 = rwi.createCell(8);
                    c9 = rwi.createCell(9);
            partnerloop++;
            tagloop++;
                    c0.setCellValue(partner_name.toUpperCase());
                    c1.setCellValue(target_name.toUpperCase());
                    c2.setCellValue(group_name.toUpperCase());
                    c3.setCellValue(facilitator_name.toUpperCase());
                    c5.setCellValue(start_date);
                    c6.setCellValue(end_date);
                    c7.setCellValue(aller + "%");
                    c8.setCellValue(aller_partial + "%");
                    
                    c9.setCellValue(aller_incomplete + "%");

                    c0.setCellStyle(style_border);
                    c1.setCellStyle(style_border);
                    c2.setCellStyle(style_border2);
                    c3.setCellStyle(style_border2);
                    c4.setCellStyle(style_border);
                    c5.setCellStyle(style_border);
                    c6.setCellStyle(style_border);
                    c7.setCellStyle(style_border);
                    c8.setCellStyle(style_border);
                    c9.setCellStyle(style_border);
                    // rwi.createCell(10).setCellValue(attended+"%");
                     facilitator_name="";


                    i++;

                }
            }

            completed = partial = incomplete = total_sessions = 0;
            all_per = partial_per = incomplete_par = all = 0;
            attended_per = 0;
            sessions_attended = counter = 0;
            over = 0;
            over_per = 0;
            
           
            
        }//end of group while 

         
    
            
           if(partnerloopcopy+1<=partnerloop-1){
              
                   shet1.addMergedRegion(new CellRangeAddress(partnerloopcopy,partnerloop-1,0,0));
                   //shet1.addMergedRegion(new CellRangeAddress(partnerloopcopy,partnerloop-1,1,1));
        
                                               }
             
             partnerloopcopy=partnerloop; 
        }//end o partners while
        
        
        
        //^^^^^^^^^^^^^^^^^^^^^Merge the last column
        
          if(tagloopcopy+1<=tagloop-1){
                
                  // shet1.addMergedRegion(new CellRangeAddress(tagloopcopy,tagloop-1,1,1));
                   
                                      }
               tagloopcopy=tagloop;
        
        
// write it as an excel attachment
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=Overall_Groups_Completion_Rate.xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
//response.sendRedirect("index.jsp");
    
    
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
            Logger.getLogger(reports.excel_groups_completion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(reports.excel_groups_completion.class.getName()).log(Level.SEVERE, null, ex);
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
