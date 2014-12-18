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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Manuel
 */
public class membersperfacilitator extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    
    dbConn conn= new dbConn();
    
    ArrayList Facilrecords= new ArrayList();
    ArrayList Facilnames= new ArrayList();
    ArrayList Facilnum= new ArrayList();
    ArrayList Facilmembers= new ArrayList();
    
    
    
    
    String distid="8";
    
    if(request.getParameter("distid")!=null)
    {
        
    distid=request.getParameter("distid");
    
    }
    
    
    //partnerid2 is facilitators for nope.
    String mainqr="SELECT distinct(phone) FROM masterhc.facilitator_details join groups on groups.partner_id=facilitator_details.partner_id where facilitator_details.partner_id='2' and district_id='"+distid+"' ";
    conn.rs=conn.st.executeQuery(mainqr);
    
    while(conn.rs.next()){
    //select all facilitator ids for that person whose phone number is identified
        String facilid="select facilitator_id,first_name ,sur_name,middle_name from facilitator_details where phone='"+conn.rs.getString(1)+"' ";
        
        //System.out.println(conn.rs.getString(1));
        //System.out.println(facilid);
        
        ArrayList facils= new ArrayList();
        
        conn.rs1=conn.st1.executeQuery(facilid);
        int members=0;
        
        String fname="";
        String mname="";
        while(conn.rs1.next()){
        
        String gettotal="select count(*) from register_attendance where facilitator_id='"+conn.rs1.getString(1) +"'";
        conn.rs2=conn.st2.executeQuery(gettotal);
        
        if(conn.rs2.next()){
        
            members=members+conn.rs2.getInt(1);
            
            
        }
        
        fname=conn.rs1.getString(2).toUpperCase();
        mname=conn.rs1.getString(3).toUpperCase();
        
        }
       
        
        //String getgrps="select * from groups where group_id like '"+groupid+"' ";
        if(members>0){
            
        System.out.println("NAME__"+fname+" "+mname+"   PHONENO_"+conn.rs.getString(1)+"__MEMBERS::"+members+"\n");
        
        Facilrecords.add("NAME__"+fname+" "+mname+"   PHONENO_"+conn.rs.getString(1)+"__MEMBERS::"+members);
        Facilnum.add(conn.rs.getString(1));
        
        Facilmembers.add(members);
        Facilnames.add(fname+" "+mname);
        
        }
        
        //String groups="SELECT count(*) FROM  groups join member_details on groups.group_id=member_details.group_id where groups.district_id='8' and groups.target_pop_id='21' and groups='"++"' limit 10000";
        
    
    
    }
    
      HSSFWorkbook wb= new HSSFWorkbook();
    
    
    HSSFSheet members= wb.createSheet("MEMBERS");
    
    
      //%%%%%%%%%%%%%%%%HEADER FONTS AND COLORATION%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            HSSFFont font_header = wb.createFont();
            font_header.setFontHeightInPoints((short) 10);
            font_header.setFontName("Eras Bold ITC");



            //    font.setItalic(true);
            font_header.setBoldweight((short) 03);
            font_header.setColor(HSSFColor.BLACK.index);

            //font data
            HSSFFont datafont = wb.createFont();
            datafont.setBoldweight((short) 03);
            datafont.setColor(HSSFColor.BLACK.index);
            datafont.setFontHeightInPoints((short) 10);
            datafont.setFontName("Cambria");


            //==============HEADER STYLE==================

            CellStyle style_header = wb.createCellStyle();
            style_header.setFont(font_header);
            style_header.setWrapText(true);
            style_header.setAlignment(style_header.ALIGN_CENTER_SELECTION);
            style_header.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
            style_header.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);


            //========TABLE HEADER STYLING===========================
            CellStyle th_style = wb.createCellStyle();
            th_style.setFont(datafont);
            th_style.setWrapText(true);
            th_style.setAlignment(th_style.ALIGN_CENTER);
            th_style.setFillForegroundColor(HSSFColor.LIGHT_ORANGE.index);
            th_style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            th_style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            th_style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            th_style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            th_style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    
  
            
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
  
    
    HSSFRow rw= members.createRow(1);
    HSSFRow rw0= members.createRow(0);
     rw0.setHeightInPoints(23);
    HSSFCell hd1= rw0.createCell(1);
    hd1.setCellValue("FACILITATORS NAME");
    hd1.setCellStyle(th_style);
    members.setColumnWidth(1, 5000);
    members.setColumnWidth(2, 4000);
    members.setColumnWidth(3, 5000);
    
    HSSFCell hd2= rw0.createCell(2);
    hd2.setCellValue("PHONE NUMBER");
       hd2.setCellStyle(th_style);
       
    HSSFCell hd3= rw0.createCell(3);
    hd3.setCellValue("MEMBERS REACHED");
       hd3.setCellStyle(th_style);
    
    
    
    for(int a=0;a<Facilmembers.size();a++){
    
    rw= members.createRow(a+1);
    
    rw.setHeightInPoints(23);
    HSSFCell cell= rw.createCell(1);
    
    cell.setCellValue(Facilnames.get(a).toString());
    cell.setCellStyle(innerdata_style);
    
    
    
     HSSFCell cell2= rw.createCell(2);
    
    cell2.setCellValue(Facilnum.get(a).toString());
    cell2.setCellStyle(innerdata_style);
    
    HSSFCell cell3= rw.createCell(3);
    
    cell3.setCellValue(Facilmembers.get(a).toString());
    
    cell3.setCellStyle(innerdata_style);
    }//end of for loop
    
    
     Date dat = new Date();

        String dat1 = dat.toString().replace(" ", "_");

        // write it as an excel attachment
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=FACILITATORS_PER_MEMBER_" + dat1 + ".xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();
    
    
    
 
}       catch (SQLException ex) {
            Logger.getLogger(membersperfacilitator.class.getName()).log(Level.SEVERE, null, ex);
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
