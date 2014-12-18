/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AJAX;

import com.csvreader.CsvWriter;
import hc.dbConn;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manuel
 */
public class getExactDuplicates extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
          Date dat= new Date();         
         String date1=dat.toString().replace(" ","_");
         date1=date1.replace(":","_");
        String outputFile = "C:\\APHIAPLUS\\Cleanrawdata"+date1+".csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
             
                CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
    
    String year="2014";
    String targetid="21";
    
    
     if (!alreadyExists)
			{
				csvOutput.write("PARTICIPANT");
				csvOutput.write("UNIQ KEY");				
				csvOutput.write("IS DUPLICATE");				
				csvOutput.write("GROUP");
                                csvOutput.write("AGE");
				csvOutput.write("SEX");				
				csvOutput.write("DISTRICT");
				csvOutput.write("TARGET POPULATION");                                
				csvOutput.write("COUNTY");
				csvOutput.write("MONTH");
				csvOutput.write("YEAR");
				csvOutput.write("QUARTER");
				csvOutput.write("START DATE");
				csvOutput.write("END DATE");
				csvOutput.write("FORM");
				
			}
                                csvOutput.endRecord(); 
    
    
    dbConn conn= new dbConn();
    
    String getduplicates
="SELECT (CONCAT(member_details.first_name,\" \",member_details.mname,\" \",member_details.sur_name)) as PARTICIPANT,"
+" count( CONCAT(member_details.first_name,member_details.mname,member_details.sur_name,AGE)) as occurance, "
+ " member_details.member_id, CONCAT(member_details.first_name,member_details.mname,member_details.sur_name,AGE) as CONCAT,"
+" group_name as GROUPNAME,age as AGE,sex as SEX,"
+" district_name as DISTRICT, "
+"  county_name as COUNTY, "
+" partner_name as PARTNER,"
+" population_name as TARGET_POPULATION, "
+" sessions_attended as SESSIONS_ATTENDED, "
+" no_of_lessons as EXPECTED_SESSION,  "
+" member_details.month as MONTH, "
+" member_details.year as YEAR, "
+" member_details.period as QUARTER "
+" FROM groups "
+" join member_details on member_details.group_id=groups.group_id "
+" join target_population on groups.target_pop_id=target_population.target_id "
+" join district on groups.district_id=district.district_id" 
+" join partner on groups.partner_id=partner.partner_id "
+" join curriculum on groups.target_pop_id=curriculum.target_id,county"
+" where district.county_id=county.county_id and  sessions_attended >0  and target_population.target_id='"+targetid+"' "
+" and ( member_details.year='"+year+"' ) group by CONCAT(member_details.first_name,member_details.mname,member_details.sur_name,AGE) having occurance >  0 ";
    
    conn.rs= conn.st.executeQuery(getduplicates);
    String alltable="<tr><td>PARTICIPANT</td><td>GROUP</td><td>AGE</td><td>DISTRICT</td><td>COUNTY</td></tr><tr>";
   
    int w=0;
    while(conn.rs.next()){
  //search for possible duplicates where names , district and age are similar 
        w++; 
   // System.out.println("( ` `)");  
     alltable+="<td>"+conn.rs.getString("PARTICIPANT")+"</td>";
     alltable+="<td>"+conn.rs.getString("GROUPNAME")+"</td>";
     alltable+="<td>"+conn.rs.getString("AGE")+"</td>";
     alltable+="<td>"+conn.rs.getString("DISTRICT")+"</td>";
//     alltable+="<td>"+conn.rs.getString("COUNTY")+"</td>";
         
    
   //conn.rs1=conn.st1.executeQuery(query2); 
    
   // while (conn.rs1.next()){
    
        //write to csv
                               
        String getregattendance="select new_topic.start_date as SDATE, new_topic.end_date as EDATE,form_number, register_id,member_id,marked_date,forms.form_id from register_attendance left join forms on register_attendance.form_id=forms.form_id join new_topic on register_attendance.marked_date=new_topic.marking_date where member_id='"+conn.rs.getString("member_details.member_id")+"' limit 1 ";
    conn.rs2=conn.st2.executeQuery(getregattendance);
    
   // }
    //continue with the query now...
   
    
    
                                csvOutput.write(conn.rs.getString("PARTICIPANT"));
                                csvOutput.write(conn.rs.getString("CONCAT"));
                                
                                if(conn.rs.getString("occurance").equals("1")){
                                 csvOutput.write("NO");
                                }else {
                                 csvOutput.write("YES");
                                }
				csvOutput.write(conn.rs.getString("GROUPNAME"));
                                csvOutput.write(conn.rs.getString("AGE"));
				csvOutput.write(conn.rs.getString("SEX"));				
				csvOutput.write(conn.rs.getString("DISTRICT"));
				csvOutput.write(conn.rs.getString("TARGET_POPULATION"));                                
				csvOutput.write(conn.rs.getString("COUNTY"));
				csvOutput.write(conn.rs.getString("MONTH"));
				csvOutput.write(conn.rs.getString("YEAR"));
				csvOutput.write(conn.rs.getString("QUARTER"));
                                
    if(conn.rs2.next()){
    
        csvOutput.write(conn.rs2.getString("SDATE"));
        csvOutput.write(conn.rs2.getString("EDATE"));
        csvOutput.write(conn.rs2.getString("form_number"));
    }
         System.out.println(" ~~~~ "+w+"  "+conn.rs.getString("PARTICIPANT")+"~~~~"+conn.rs.getString("occurance"));    
                               
                                csvOutput.endRecord(); 
    
    
     }
    csvOutput.close();
    
    
    
    alltable+="</tr>";
    PrintWriter out = response.getWriter();
    try {
       out.println("<html>"); 
       out.println("<body><head></head>"); 
       out.println("<table>"+alltable+"</table>"); 
       out.println("</body></html>"); 
       
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(getExactDuplicates.class.getName()).log(Level.SEVERE, null, ex);
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
