/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class atten_getMember_details extends HttpServlet {

    ArrayList allpartis=new ArrayList();
    HttpSession session;
    
    String age,sur_name,group_id;
  String nextpage="";  
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
                 session=request.getSession();
     if (session.getAttribute("userid") == null || session.getAttribute("year")==null) {
        nextpage="index.jsp";
    } 
     else{
            if(allpartis.size()>0){
            allpartis.clear();
            }
            
          
            dbConn conn=new dbConn();
            
            
            //load students
          //String getteachers="Select * from member_details where class='"+session.getAttribute("clas")+"' AND school_id='"+session.getAttribute("school_name")+"'";
      
//___________________________________________________________________________________________________________            
            
            
            System.out.println("YEAR IS : "+session.getAttribute("year"));
            System.out.println("period IS : "+session.getAttribute("period"));
            System.out.println("group IS : "+session.getAttribute("s_group_name"));
//            
            String getmembers="Select * from member_details where sessions_attended='0' && group_id='"+session.getAttribute("s_group_name")+"'&& year='"+session.getAttribute("year")+"' && period='"+session.getAttribute("period")+"'" ;
              
           

           
            conn.rs=conn.st.executeQuery(getmembers);
            
            
            
            
//___________________________________________________________________________________________________________________          
          
              
            int count=1;
            
            //excequte querry to get all teachers details
           
            while(conn.rs.next()){
                //add the values in the database to a bean
          getParticipantsBean tb=new getParticipantsBean();
           tb.setUserid(conn.rs.getString(1));
            tb.setFname(conn.rs.getString(2));
            tb.setMname(conn.rs.getString(3));
            tb.setLname(conn.rs.getString(4));
            tb.setSex(conn.rs.getString(6));
            tb.setAge(conn.rs.getString(5));
            tb.setCount(count);
            tb.setGroup_id(conn.rs.getString(7));
           String targetpop="select group_name from groups where group_id='"+conn.rs.getString(7)+"'";
           conn.rs1=conn.st2.executeQuery(targetpop);
           conn.rs1.next();
           tb.setGroup(conn.rs1.getString(1));
           // tb.setLessons(4);
            //add the bean object to an arraylist
            allpartis.add(tb);
            count++;
            }//end of while
            
            System.out.println(" the count is : "+count);
            session.setAttribute("allpartisAL", allpartis);
            //clear arraylist
            //allteachersAL.clear();
            session.setAttribute("hc_reg_no_of_participants", count);

            if(count<=1){
              session.setAttribute("hc_no_students","<font color=\"red\"><b>No Participants have been registered in the database for this specific group. Kindly"
                      
                      + "<a href=\"filter_member.jsp\" class=\"linkstyle\">Add them</a>");
            
            }
            System.out.println("count "+count);
            nextpage="hc_mark_register.jsp";
     }
            response.sendRedirect(nextpage);
            
        } catch (SQLException ex) {
            Logger.getLogger(atten_getMember_details.class.getName()).log(Level.SEVERE, null, ex);
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
