/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

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
public class getduplicates extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
          Date dat= new Date();         
         String date1=dat.toString().replace(" ","_");
         date1=date1.replace(":","_");
        String outputFile = "C:\\APHIAPLUS\\hcduplicates"+date1+".csv";
		
		// before we open the file check to see if it already exists
		boolean alreadyExists = new File(outputFile).exists();
             
                CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
    
    String year="2014";
    String targetid="21";
    
    
     if (!alreadyExists)
			{
				csvOutput.write("PARTICIPANT");
				csvOutput.write("CONCATENATED FIELDS");				
				csvOutput.write("GROUP");
                                csvOutput.write("AGE");
				csvOutput.write("SEX");				
				csvOutput.write("DISTRICT");
				csvOutput.write("TARGET POPULATION");                                
				//csvOutput.write("COUNTY");
				csvOutput.write("MONTH");
				csvOutput.write("YEAR");
				csvOutput.write("QUARTER");
				
			}
                                csvOutput.endRecord(); 
    
    
    dbConn conn= new dbConn();
    
    String simpleqry=" "
            + "select member_id, first_name,mname,sur_name,age,sex,group_name FROM member_details "
            +" join groups on member_details.group_id=groups.group_id where target_pop_id='25' ";
    
    String getduplicates
="SELECT (CONCAT(member_details.first_name,\" \",member_details.mname,\" \",member_details.sur_name)) as PARTICIPANT,"
+" count( CONCAT(member_details.first_name,member_details.mname,member_details.sur_name,AGE,group_name)) as occurance, "
+ " member_details.member_id, CONCAT(member_details.first_name,member_details.mname,member_details.sur_name,AGE,group_name) as CONCAT,"
+" group_name as GROUPNAME,age as AGE,sex as SEX,"
+" district_name as DISTRICT, "
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
+" join curriculum on groups.target_pop_id=curriculum.target_id"
+" where sessions_attended >0  and target_population.target_id='"+targetid+"' "
+" and ( member_details.year='"+year+"' ) group by PARTICIPANT having occurance >  1 ";
    
    conn.rs= conn.st.executeQuery(simpleqry);
    String alltable="<tr><td>PARTICIPANT</td><td>GROUP</td><td>AGE</td><td>DISTRICT</td><td>COUNTY</td></tr><tr>";
     while(conn.rs.next()){
  //search for possible duplicates where names , district and age are similar 
         
     System.out.println(conn.rs.getString("member_id")+"~~~~"+conn.rs.getString("first_name")+" "+conn.rs.getString("mname"));    
     
         
 
    
    
  // conn.rs1=conn.st1.executeQuery(query2); 
    
   // while (conn.rs1.next()){
    
        //write to csv
                                csvOutput.write(conn.rs1.getString("MEMBER"));
                                csvOutput.write(conn.rs1.getString("CONCAT"));				
				csvOutput.write(conn.rs1.getString("GROUPNAME"));
                                csvOutput.write(conn.rs1.getString("AGE"));
				csvOutput.write(conn.rs1.getString("SEX"));				
//				csvOutput.write(conn.rs1.getString("DISTRICT"));
//				csvOutput.write(conn.rs1.getString("TARGET_POPULATION"));                                
//				//csvOutput.write(conn.rs1.getString("COUNTY"));
//				csvOutput.write(conn.rs1.getString("MONTH"));
//				csvOutput.write(conn.rs1.getString("YEAR"));
//				csvOutput.write(conn.rs1.getString("QUARTER")); 
                                csvOutput.endRecord(); 
        
    
    
   // }
    //continue with the query now...
    
    
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
            Logger.getLogger(getduplicates.class.getName()).log(Level.SEVERE, null, ex);
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
