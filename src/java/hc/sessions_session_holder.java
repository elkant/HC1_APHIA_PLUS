/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

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

/**
 *
 * @author SIXTYFOURBIT
 */
public class sessions_session_holder extends HttpServlet {

 String county, dist, target_pop,group,partner;
     HttpSession session;
     String nextpage,year,period;
     int members;
     
     String formid="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            members=0;
            response.setContentType("text/html;charset=UTF-8");
                session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
       
     
     
     
     if(request.getParameter("county")!=null){
     
          county=request.getParameter("county");
          session.setAttribute("county_id",county);
     }
     else{
     
     county=session.getAttribute("county_id").toString();
     
     }
      
     //get the form id
     
     
     System.out.println("\nFORM ID FROM REQUEST IS:::"+request.getParameter("formid")+"\n");
     
      if(request.getParameter("formid")!=null && !request.getParameter("formid").equals("")){
     
          formid=request.getParameter("formid");
          
          String formparams[]=formid.split(",");
          
          session.setAttribute("formid",formparams[0]);
          session.setAttribute("formname",formparams[1]);
     }
     else{
     
          if(session.getAttribute("formid")!=null){
     formid=session.getAttribute("formid").toString();
                                                  }
          
      
      }
     System.out.println("County is__ "+county);
     
     if(request.getParameter("district")!=null){
          dist=request.getParameter("district");
          session.setAttribute("district_id",dist);
     }
     else{
         
     dist=session.getAttribute("district_id").toString();
     
     }
          
     if(request.getParameter("target_pop")!=null){
         
          target_pop=request.getParameter("target_pop");
          session.setAttribute("target_id",target_pop);
     }
     else{     
     target_pop=session.getAttribute("target_id").toString();
     }
     
     
     if(request.getParameter("group_name")!=null){
          group=request.getParameter("group_name");
          session.setAttribute("added_group_id",group);
     }
     else {
     group=session.getAttribute("added_group_id").toString();
     
     }
          
     
     if(request.getParameter("partner_name")!=null){
         
          partner=request.getParameter("partner_name");
          session.setAttribute("partner_id",partner);
     }
          else{
partner=session.getAttribute("partner_id").toString();
}
     
//          year=request.getParameter("year");
//          period=request.getParameter("period");
          
     String selected_month="";
     
     if(request.getParameter("month")!=null){
     
         selected_month=request.getParameter("month");
         session.setAttribute("selected_month",selected_month);
     }
     else {
     
     if(session.getAttribute("selected_month")!=null){


selected_month=session.getAttribute("selected_month").toString();

}
     
     }
     
     
     System.out.println("selected month"+selected_month);
//=====================================================================
     
          
           if(request.getParameter("year")!=null){
            year =request.getParameter("year");
            session.setAttribute("year",year);
            }
            else{
                
            year=session.getAttribute("year").toString();
            
            }
            if(request.getParameter("period")!=null){
            period = request.getParameter("period");
            session.setAttribute("period",period);
            }
            else{
            period= session.getAttribute("period").toString();
            }
          dbConn conn= new dbConn();
    
    
    
    
    

    //Get the names of the various chosen places
    
          conn.rs=conn.st.executeQuery("select * from county where county_id='"+county+"'");
               if(conn.rs.next()){
               session.setAttribute("sc_county", conn.rs.getString(2));
               
               }
               // district name
               conn.rs1=conn.st1.executeQuery("select * from district where district_id='"+dist+"'");
               if(conn.rs1.next()){
               session.setAttribute("sc_district", conn.rs1.getString("district_name"));
        }
               
               
               //partner
                conn.rs2=conn.st2.executeQuery("select * from partner where partner_id='"+partner+"'");
                
               if(conn.rs2.next()){
               session.setAttribute("sc1_partner", conn.rs2.getString("partner_name"));
               }
               
              
               //pop name
                conn.rs3=conn.st3.executeQuery("select * from target_population where target_id='"+target_pop+"'");
               if(conn.rs3.next()){
               session.setAttribute("sc_target_pop", conn.rs3.getString(2));
               session.setAttribute("sc_partner", conn.rs3.getString("partner_id"));
               
               }
                
//                session.setAttribute("sc1_partner", conn.rs.getString("partner_name"));
               System.out.println("Partner id: is "+session.getAttribute("sc_partner"));
               //group name
               conn.rs4=conn.st4.executeQuery("select * from groups where group_id='"+group+"' || group_name='"+group+"'");
               if(conn.rs4.next()){
               session.setAttribute("sc_group_name", conn.rs4.getString(2));
               }
               
               
            //pass the ids too
                
              session.setAttribute("s_county",county );
              session.setAttribute("s_district",dist ); 
              session.setAttribute("s_target_pop",target_pop );
              session.setAttribute("s_group_name",group );
              session.setAttribute("year",year );
              session.setAttribute("period",period ); 
              
          //==============================THIS CODE WAS EDITED ON 15th JULY 2014===========================    
              String formqry="";
            
            if(session.getAttribute("formid")!=null&&!session.getAttribute("formid").equals("")){
            
              formqry=" and register_attendance.form_id='"+session.getAttribute("formid") +"' ";
                
            
               }
              
              
              //check whether that group has been addd
              
              //=======grpchecker was changed on 19th JUNE 2014 AFTER PRESENTING SYSTEM TO FAIR===============
              
             // String grpchecker="Select * from session where group_id='"+group+"' AND marking_status='yes' && year='"+year+"' && period='"+period+"' && month='"+selected_month+"'";
              //====CHECK WHETHER ANY NUMBER OF MEMBERS FOR THAT GROUP EXIST WITH 0 sessions attended.
              
              String grpchecker1="Select * from member_details where group_id='"+group+"' AND sessions_attended='0' && year='"+year+"' && period='"+period+"' && month='"+selected_month+"'";
             
              
              System.out.println(grpchecker1);
              conn.rs1=conn.st1.executeQuery(grpchecker1);
             
              //THIS COUNT HAS BEEN USED TO STORE THE NUMBER OF SESSIONS BELONGING TO A CERTAIN GROUP
              
              
              int count=0;
              
              //====IF THERE ARE ANY UNMARKED USERS FOR THAT PERIOD, MARK REGISTER , else edit register
              if(conn.rs1.next()){
                  String member_selector="SELECT COUNT(member_id) FROM member_details WHERE  group_id='"+group+"' && year='"+year+"' && period='"+period+"' and month='"+selected_month+"'";
                 
                   System.out.println(member_selector);
                   
                   //avoid calling conn several times 
                  if(count==0){
                  conn.rs_1=conn.st_1.executeQuery(member_selector);
                  if(conn.rs_1.next()==true){
                      members=conn.rs_1.getInt(1);
                  }
                  
                   System.out.println("Members__"+members);
                  }
              
                   nextpage="topic_start_end_date.jsp";
                
              }
              else {
              //======GO TO LOAD ATTENDANCE PAGE=================
                  
                  //since one group can have more than one users, pick the details of the group which belong to a certain form id
                  
                  
                  
                  
                  String grpchecker="Select * from session join register_attendance on session.marked_date=register_attendance.marked_date where group_id='"+group+"' "+formqry+"  AND marking_status='yes' && year='"+year+"' && period='"+period+"' && month='"+selected_month+"' group by session.marked_date";
                 conn.rs2=conn.st2.executeQuery(grpchecker);
                  
              nextpage="hc_loadAttendance2";
               //go to load attendance page 
               if(conn.rs2.next()){
              session.setAttribute("hc_ssh_marked_date", conn.rs2.getString("marked_date"));
               }
              session.setAttribute("hc_ssh_group_id", group);
              }
              
              
              
              
              //get the number of lessons from the target population..
              String lessons="";
              //===default values
              
              session.setAttribute("hc_ssh_number_of_sessions", "0");
              
              if(session.getAttribute("target_id")!=null){
                  
               lessons="select no_of_lessons from curriculum where target_id='"+session.getAttribute("target_id")+"'";
             
              
              }
               
              conn.rs=conn.st.executeQuery(lessons);
              
              
              
              while(conn.rs.next()){
             //this is now the number of lessons for the selected  sessions 
               session.setAttribute("hc_ssh_number_of_sessions", conn.rs.getString(1));   
              
                                   }
              
              
              
              System.out.println("Next Page::"+nextpage);
              //close one connection
                conn.st.close();
                conn.st1.close();
                conn.st2.close();
                conn.st3.close();
                conn.st4.close();
              response.sendRedirect(nextpage);
               
               
        } catch (SQLException ex) {
            Logger.getLogger(session_holder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
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
