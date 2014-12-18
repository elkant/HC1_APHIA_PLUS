/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
/* Code for the HTTP Servlet that will return the Pie Chart as a PNG image
back to the browser after generating it using JFreeChart API */
public class PieChartServlet extends HttpServlet {
    HttpSession session;
    String group_id="";
public PieChartServlet() {
/* No code in the constructor for this demonstration */
}
public void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        OutputStream out = response.getOutputStream(); /* Get the output stream from the response object */
        try {
            session=request.getSession();
//            Declare string and integers to hold values.
             System.out.println("within the creater: ");
             group_id=session.getAttribute("group_id").toString();
             
            System.out.println("within the creater: "+group_id);
            int total_sessions=0;
            int complete=0,incomplete=0,number=0,partial=0;
            String completed="",incompleted="",partially="",group_name="";
                DefaultPieDataset myServletPieChart = new DefaultPieDataset();
                dbConn conn=new dbConn();
                
//              ^^^^^^^^^^^^^^^^^  Get the number of sessions per group.^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                String target="select * from groups where group_id='"+group_id+"' && period_id='4'";
                conn.rs=conn.st.executeQuery(target);
                while (conn.rs.next())
                {
              String  target2="select * from curriculum where target_id='"+conn.rs.getString("target_pop_id") +"'";
              System.out.println("target id:1 "+conn.rs.getString("target_pop_id"));
              conn.rs2=conn.st2.executeQuery(target2);
              while(conn.rs2.next())
              {
          String   no_of_s=conn.rs2.getString("no_of_lessons").toString();  
              System.out.println("no of  sessions:1 "+conn.rs2.getString("no_of_lessons"));   
            total_sessions=Integer.parseInt(no_of_s); 
             System.out.println("no of  sessions:2 "+total_sessions); 
  
              } 
                
                }
//           ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//^^^^^^^^^^^^^^^^^^^^^^^Get group name for display purposes ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                String grouper="select * from groups where group_id='"+group_id+"'";
                conn.rs=conn.st.executeQuery(grouper);
                while(conn.rs.next()){
                    group_name=conn.rs.getString("group_name");
                }
//          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//            ^^^^^^^    Get the member details and display them approptiately ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                String select="select * from member_details where group_id='"+group_id+"'";
                conn.rs=conn.st.executeQuery(select);
                while(conn.rs.next()){
                    number= Integer.parseInt(conn.rs.getString("sessions_attended").toString());
                    if(number==total_sessions){
                    complete=++complete;
                     completed="Attended all the sessions";   
                    }
                if(number>=(total_sessions/2) && number<total_sessions){
                       partial=++partial;    
                     partially="Attended over 50% of the total sessions but didn't complete all the sessions";   
                    }        
                     if(number<(total_sessions/2)&& number>=0){
                       incomplete=++incomplete;    
                     incompleted="Attended less than 50% of the total sessions.";   
                    }
                    }
     int total=complete+partial+incomplete;
//   ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^             
//    ^^^^^^^^^^^^^^^^^     Calculatee the percentage for each group       ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
               int perc1=0,perc2=0,perc3=0;
                perc1=(complete*100)/total;
                perc2=(partial*100)/total;
                perc3=(incomplete*100)/total;
              System.out.println(complete);
              System.out.println(partial);
              System.out.println(incomplete);
              System.out.println(total);
                System.out.println(perc1+" "+perc2 +" "+perc3);
//                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//          ^^^^^^^^^^^^^^^^^^^^^      Sections to display in the pie chart ^^^^^^^^^^^^^^^^^^^^^^^^
            myServletPieChart.setValue(perc1+"%  of the total participants "+completed,complete);
            myServletPieChart.setValue(perc2+"%  of the total participants "+partially,partial); 
             myServletPieChart.setValue(perc3+"%  of the total participants "+incompleted,incomplete);
      total_sessions=0;
      complete=0;
      incomplete=0;
      number=0;
      partial=0;
                JFreeChart mychart = ChartFactory.createPieChart(group_name+" Group Attendance Pie Chart.                Total Peers: "+total,myServletPieChart,true,true,false);  
                response.setContentType("image/png"); /* Set the HTTP Response Type */
                ChartUtilities.writeChartAsPNG(out, mychart, 950, 600);/* Write the data to the output stream */
        
        response.sendRedirect("group_pie_chart.jsp");
        
        }
        catch (Exception e) {
                System.err.println(e.toString()); /* Throw exceptions to log files */
        }
        finally {
                out.close();/* Close the output stream */
        }
        }
}