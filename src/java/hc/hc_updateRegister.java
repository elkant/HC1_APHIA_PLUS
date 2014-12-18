/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class hc_updateRegister extends HttpServlet {

   
    
    String status,reg_id,img,return_msg,member_id;
    
    HttpSession session;
    int total_rows,monitor_session=0,no_of_sessions;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
   Calendar now = Calendar.getInstance();
int year = now.get(Calendar.YEAR);
int month = now.get(Calendar.MONTH)+1; // Note: zero based!
int day = now.get(Calendar.DAY_OF_MONTH);
int hour = now.get(Calendar.HOUR_OF_DAY);
int min=now.get(Calendar.MINUTE);
int sec=now.get(Calendar.SECOND);        
        
 String hr=hour+":"+min+":"+sec;          
        
        
          session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
       //get the no of lessons and the no of students which determine the looping 
       total_rows=Integer.parseInt(session.getAttribute("hc_upd_reg_no_of_student").toString());
      
     no_of_sessions=Integer.parseInt(session.getAttribute("session_count").toString());
       
       
       dbConn conn= new dbConn();
        // System.out.println(" rows "+total_rows);
      
       //begin my loop
       
       
       
       //=================================Update the session content =====================================================
for(int srow=1;srow<=no_of_sessions;srow++){
            try {
                String stopic="",smethod="",stime="",smalecondoms="",sfemalecondoms="",datepicker="",session_id="";
                String stopicarray[],smethodarray[];

                //smethod=request.getParameter("method"+srow);
                stime=request.getParameter("time"+srow);
                session_id=request.getParameter("regid"+srow);
                datepicker=request.getParameter("datepicker"+srow);
                if(request.getParameter("malecondoms"+srow)!=null){
                smalecondoms=request.getParameter("malecondoms"+srow);
                }
                else{
                smalecondoms="0";
                }

                if(request.getParameter("femalecondoms"+srow)!=null){
                    
                sfemalecondoms=request.getParameter("femalecondoms"+srow);

                }else{
                sfemalecondoms="0";
                }

                stopicarray=request.getParameterValues("topic"+srow);
                for(int ar=0;ar<stopicarray.length;ar++){
                stopic=stopic+stopicarray[ar];

                }
                
                 smethodarray=request.getParameterValues("method"+srow);
                for(int ar=0;ar<smethodarray.length;ar++){
                smethod=smethod+smethodarray[ar];

                }
                //all strings for that row have alredy been picked

                
                     
                    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
               // timestamp='"+mdate+"',
                
                
                String upd="update session set timestamp='"+mdate+"', date='"+datepicker+"' , duration='"+stime+"' , male_cds='"+smalecondoms+"' , female_cds='"+sfemalecondoms+"', topic_id='"+stopic+"', method_id='"+smethod+"' where session_id='"+session_id+"' ";
            smethod="";
            
                conn.st4.executeUpdate(upd);
                
                
                
            } catch (SQLException ex) {
                Logger.getLogger(hc_updateRegister2.class.getName()).log(Level.SEVERE, null, ex);
            }



}       
       
       
       
       
//=================================================================================================================    
       
int session_count=0,count_everything=0;

          for(int row=1;row<=total_rows;row++){
      
     // System.out.println(" row "+row+": "+request.getParameter("cb"+row));
                
         if(request.getParameter("cb"+row)!=null){
               
                    count_everything++;
                    
                         //if the dropdown is checked 
           
                           try {
                               //get the current register id which determines the row to be updated                   
                              reg_id=request.getParameter("rid"+row);                       
                                              
                                                    
                                                  //remove the underscore and join the entire word
                                                   status=request.getParameter("cb"+row);
                                                   
                                      if(status.equals("2")){
                                          //absent
                                      img="images/absent.png";
                                      
                                      }else if(status.equals("1")){
                                          session_count++;
                                          //present
                                       img="images/present.png";
                                      
                                      } 
                                      else{
                                          
                                          //makeup
                                          session_count++;
                                       img="images/ABPRE.png";
                                      }
                                        
                                           Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
               // timestamp='"+mdate+"',
                                      
                              String update="update register_attendance SET timestamp='"+mdate+"', availability='"+status+"' , image_path='"+img+"' where register_id='"+reg_id+"'";
                                   session.setAttribute("l_e_d",day+"-"+month+"-"+year);            
                                      conn.st.executeUpdate(update);
                                      monitor_session++;
                                      return_msg="<font color=\"green\">Register updated succesfully</font>";
                                     
                                      if(count_everything%no_of_sessions==0&&count_everything!=0){
                                           System.out.println("if worked in row "+row);
                                          //get the meber id where to update
                                          member_id=request.getParameter("memid"+row).trim();
                                          System.out.println("member id "+member_id);
                                      conn.st3.executeUpdate("update member_details set timestamp='"+mdate+"', sessions_attended ='"+session_count+"' where member_id='"+member_id+"'");
                                      session_count=0;
                                      count_everything=0;
                                      }
                                      
                           } //end of if
                           catch (SQLException ex) {
                               Logger.getLogger(hc_updateRegister.class.getName()).log(Level.SEVERE, null, ex);
                           }
                           
                              
                 
                    
                }
     //this else will happen rarely.                              
                    else { 
  if(request.getParameter("cb"+row)==null|| request.getParameter("cb"+row).equals("")){
  
  
  }
  else {
 
  
  }
}
                    //execute update
                  
                   
                   
                                                   } //end of for
                
                
               
                
               
     
    if(monitor_session>0){     
            try {
                session.setAttribute("hc_register_updated",return_msg);   
                String com=InetAddress.getLocalHost().getHostName()+" "+InetAddress.getLocalHost().getHostAddress();
                                  
                                  
                                 conn.st1.executeUpdate("insert into audit (action,time,actor_id,host_comp) values('updated register for group "+session.getAttribute("hc_ssh_group_id")+"','"+day+"-"+month+"-"+year+" "+hr+"','"+session.getAttribute("userid")+"','"+com+"')");
            } catch (SQLException ex) {
                Logger.getLogger(hc_updateRegister.class.getName()).log(Level.SEVERE, null, ex);
            }
               
    }
    else{
     session.setAttribute("hc_register_updated","no update was made"); 
    
    
    }
          
         response.sendRedirect("hc_loadAttendance");
        
        
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
