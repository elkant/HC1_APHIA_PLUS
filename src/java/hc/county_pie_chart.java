/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Geofrey Nyabuto
 */
public class county_pie_chart extends HttpServlet {
HttpSession session;
 String county_name="";
 int max_lessons=0,current_lessons=0,completed=0,partial=0,incomplete=0, total_sessions=0,total=0;
 double all_per=0, partial_per=0,incomplete_par=0,all=0,over_per=0; 
 int over=0;
    public county_pie_chart(){
    
}
 protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
          OutputStream out = response.getOutputStream();
        try {
            session=request.getSession();
String county_id=session.getAttribute("county_id").toString();
String period,yea;
            period=session.getAttribute("period").toString();
            yea=session.getAttribute("year").toString();
dbConn conn=new dbConn();

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//^^^^^^^^^^^^^^GET COUNTY NAME^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
String county_selector="select * from county Where county_id='"+county_id+"'";
conn.rs=conn.st.executeQuery(county_selector);
conn.rs.next();
county_name=conn.rs.getString("county_name");

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//^^^^^^^^^^^^^^^^^^^^^GET ALL THE DETAILS AND RELATIONS HERE^^^^^^^^^^^^^^^^^^^^^^
String district_selector2="select * from district where county_id='"+county_id+"'";
conn.rs=conn.st.executeQuery(district_selector2);
while(conn.rs.next()){
//     ^^^^^^^^^^^^^^^^ GET GROUP DETAILS I.E GROUP ID AND TARGET POPULATION ID
 String group_selector="select * from groups where district_id='"+conn.rs.getString("district_id") +"'";
 conn.rs3=conn.st3.executeQuery(group_selector);
 while(conn.rs3.next()){
//     ^^^^^^^^^^^^^^^^^^^^^ GET TOTAL LESSONS^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
  String curriculum_selector="select * from curriculum where target_id='"+conn.rs3.getString("target_pop_id") +"'";
  conn.rs4=conn.st4.executeQuery(curriculum_selector);
  conn.rs4.next();
  max_lessons=Integer.parseInt(conn.rs4.getString("no_of_lessons").toString());
//  ^^^^^^^^^^^^^^^^^^^^GET NUMBER OF SESSIONS ATTENDED BY EACH MEMBER^^^^^^^^^^^^^^^^^
  String participant_selector="select count(member_id) from member_details where group_id='"+conn.rs3.getString("group_id") +"' && sessions_attended>'"+max_lessons+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector);
if(conn.rs4.next()==true){
          over+=conn.rs4.getInt(1);
}
  String participant_selector1="select count(member_id) from member_details where group_id='"+conn.rs3.getString("group_id") +"' && sessions_attended='"+max_lessons+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector1);
if(conn.rs4.next()==true){
          completed+=conn.rs4.getInt(1);
}
  String participant_selector2="select count(member_id) from member_details where group_id='"+conn.rs3.getString("group_id") +"' && sessions_attended<'"+max_lessons+"' && sessions_attended>='"+max_lessons/2+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector2);
if(conn.rs4.next()==true){
          partial+=conn.rs4.getInt(1);
}
  String participant_selector3="select count(member_id) from member_details where group_id='"+conn.rs3.getString("group_id") +"' && sessions_attended<'"+max_lessons/2+"' && year='"+yea+"' && period='"+period+"'";
  conn.rs4=conn.st4.executeQuery(participant_selector3);
if(conn.rs4.next()==true){
          incomplete+=conn.rs4.getInt(1);
}
    
 }
  
   
}
all=completed+partial+incomplete+over;
total=completed+partial+incomplete+over;
if(all>0){
        over_per=(over*100)/all;
all_per=(completed*100)/all;
partial_per=(partial*100)/all;
incomplete_par=(incomplete*100)/all;
String overer=String.format("%.0f",over_per);
String aller=String.format("%.0f", all_per);
String aller_partial=String.format("%.0f", partial_per);
String aller_incomplete=String.format("%.0f", incomplete_par);

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//^^^^^^^^^^^WRITE DATA TO THE CHART^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
DefaultPieDataset county_chart = new DefaultPieDataset();
county_chart.setValue(overer+"% of the total participants attended over 100% sessions", over);
county_chart.setValue(aller+"% of the total participants attended all sessions", completed);
county_chart.setValue(aller_partial+"% of the total participants attended over 50% of the sessions but didnt complete all sessions", partial);
county_chart.setValue(aller_incomplete+"% of the total participants attended less than 50% of the total sessions", incomplete);

String period_name="";
String quarter_selector="SELECT period_name FROM period WHERE id='"+period+"'";
conn.rs=conn.st.executeQuery(quarter_selector);
if(conn.rs.next()==true){
    period_name=conn.rs.getString(1);
}

JFreeChart chart = ChartFactory.createPieChart(county_name+" County Completion Rate Pie Chart.("+period_name+")"+yea+"         Total Peers: "+total,county_chart,true,true,false);
 response.setContentType("image/png");
  ChartUtilities.writeChartAsPNG(out,chart, 950, 600);

//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^CLEAR ALL variables ready to hold next data^^^^^^^^^^^^^^^^^^^^^^^
completed=partial=incomplete=total_sessions=over=0;
all_per=partial_per=incomplete_par=all=over_per=0;

}
else{
//     session.setAttribute("county_select","<font color=\"red\">"+county_name+" County has no registered member whose attendance has been marked.</font>");
//    response.sendRedirect("county_pie_chart.jsp");
}
        } 
        finally {            
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
            Logger.getLogger(county_pie_chart.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(county_pie_chart.class.getName()).log(Level.SEVERE, null, ex);
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
