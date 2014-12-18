/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
  import java.io.*;
import java.sql.SQLException;
import java.awt.BasicStroke;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.data.jdbc.JDBCPieDataset;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Geofrey Nyabuto
 */
public class group_chart extends HttpServlet {
 String dater,group_id,start_date,end_date,report,Rmonth="",nextpage="";
//    int dater;
     String start_customDate,end_customDate;
HttpSession session;
String path,path2;
          String s_day,s_month,s_year;
     String e_day,e_day1,e_month,e_year; 
   ArrayList alist=new ArrayList();
   Connection connection=null;
    public  ResultSet rs,rs1,rs2,rs3,rs4;
   public Statement st,st1,st2,st3,st4;
   PreparedStatement pst;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       // response.setContentType("text/html;charset=UTF-8");
       // PrintWriter out = response.getWriter();
        response.setContentType("image/png");
OutputStream out=response.getOutputStream();
try{
     connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hc_aphiaplus","root", "geof@compsci09");
            st = connection.createStatement();
            st1 = connection.createStatement();
            st2= connection.createStatement();
            st3 = connection.createStatement();
            st4= connection.createStatement();
  System.out.println("Start date "+start_date+" End date "+end_date);
  System.out.println("Group id is : "+group_id);
       
//String selector="select * from member_details where group_id='"+group_id+"'";
//conn.rs=conn.st.executeQuery(selector);
//  
// while(conn.rs.next()){
//     
// 
// }
    JDBCPieDataset dataset = new JDBCPieDataset(connection); 
 dataset.executeQuery("select * from member_details where group_id='7'"); 
JFreeChart chart = ChartFactory.createPieChart
         ("Members Chart", dataset, true, true, false);
chart.setBorderPaint(Color.black);
chart.setBorderStroke(new BasicStroke(10.0f));
chart.setBorderVisible(true);
if (chart != null) {
int width = 500;
int height = 350;
final ChartRenderingInfo info = new ChartRenderingInfo
                               (new StandardEntityCollection());
ChartUtilities.writeChartAsPNG(out, chart, width, height,info);
}    } finally {            
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
            Logger.getLogger(group_report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(group_report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
