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
public class geMemDetails extends HttpServlet {

    ArrayList allstudents=new ArrayList();
    HttpSession session;
    int positioner;
    String age,sur_name,group_id,fname,mname;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
               session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
          
     positioner=0;
            if(allstudents.size()>0){
            allstudents.clear();
            }
            
            if(request.getParameter("sur_name")==null){
            sur_name="";
            }
            else{
            sur_name=request.getParameter("sur_name");}
            
            if(request.getParameter("fname")==null){
            fname="";
            }
            else{
            fname=request.getParameter("fname");
            }
            
            if(request.getParameter("mname")==null){
            mname="";
            }
            else{
            mname=request.getParameter("mname");}
            
            
            if(request.getParameter("group_id")==null){
            group_id="";
            }
            else{
          group_id=request.getParameter("group_id");
            }
            
            if(request.getParameter("age")==null){
            age="";
            }
            else{
          age=request.getParameter("age");
            }
          
          System.out.println("age : "+age+"g id: "+group_id+" fname "+sur_name+" mname "+mname+" fname "+fname);
          
            dbConn conn=new dbConn();
            
            
            //load students
          //String getteachers="Select * from member_details where class='"+session.getAttribute("clas")+"' AND school_id='"+session.getAttribute("school_name")+"'";
      
//___________________________________________________________________________________________________________            
            
            String fnameqr="";
            String mnameqr="";
            if(!fname.equals("")){  fnameqr=" and first_name LIKE '"+fname+"' ";  }
            if(!mname.equals("")){  mnameqr=" and mname LIKE '"+mname+"' ";  }
            
//            
            String ag="Select * from member_details where age='"+age+"' "+fname+mname+" order by first_name" ;
            String snm="Select * from member_details  where sur_name='"+sur_name+"' "+fnameqr+mnameqr+" order by first_name";
            String scl="Select * from member_details join groups on member_details.group_id=groups.group_id  where group_name='"+group_id+"' "+fnameqr+mnameqr+" order by first_name";
            String snm_scl="Select * from member_details join groups on member_details.group_id=groups.group_id  where sur_name='"+sur_name+"' "+fnameqr+mnameqr+" AND group_name='"+group_id+"' order by first_name";
            String snm_pn="Select * from member_details  where sur_name='"+sur_name+"' "+fnameqr+mnameqr+" AND age='"+age+"' order by first_name";
            String scl_pn="Select * from member_details join groups on member_details.group_id=groups.group_id  where group_name='"+group_id+"' "+fnameqr+mnameqr+" AND age='"+age+"' order by first_name";
            String all="Select * from member_details  join groups on member_details.group_id=groups.group_id where group_name='"+group_id+"' "+fnameqr+mnameqr+" AND age='"+age+"' AND sur_name='"+sur_name+"' order by first_name";
            String getteachers="Select *  from  member_details order by timestamp desc  limit 1000";
              
            
            if(sur_name.equals("") && age.equals("")  && group_id.equals("") ){
            conn.rs=conn.st.executeQuery(getteachers);
            System.out.println(getteachers);
            positioner=1;
            }     
           else if(sur_name.equals("") && age.equals("")&& !group_id.equals("")){
            conn.rs=conn.st.executeQuery(scl);            
            }
      else if( !sur_name.equals("") && age.equals("")  && group_id.equals("")){
            conn.rs=conn.st.executeQuery(snm); 
            System.out.println(snm);
            }
          else  if(sur_name.equals("") && !age.equals("")  && group_id.equals("")){
            conn.rs=conn.st.executeQuery(ag);            
            }
   else if(  sur_name.equals("") && !age.equals("")  && !group_id.equals("")){
            conn.rs=conn.st.executeQuery(scl_pn);            
            }
     else   if( !sur_name.equals("") && age.equals("")  && !group_id.equals("")){
            conn.rs=conn.st.executeQuery(snm_scl);            
            }
       else  if(!sur_name.equals("") &&! age.equals("")  && group_id.equals("")){
            conn.rs=conn.st.executeQuery(snm_pn);            
            }
        else   if(!"".equals(sur_name) && !"".equals(age) && !"".equals(group_id)){
            conn.rs=conn.st.executeQuery(all);            
            }

           
            
            
            
            
            
//___________________________________________________________________________________________________________________          
          
        
          
             //load students
          // String getteachers="Select * from student_registration where class='3' AND school_id='3'";
           
            
          //String getteachers="Select * from student_registration";
              
            int count=1;
            
            //excequte querry to get all teachers details
           
            while(conn.rs.next()){
                //add the values in the database to a bean
           editmembers_bean tb=new editmembers_bean();
           tb.setUserid(conn.rs.getString(1));
            tb.setFname(conn.rs.getString(2).trim());
            tb.setMname(conn.rs.getString(3).trim());
            tb.setLname(conn.rs.getString(4).trim());
            tb.setSex(conn.rs.getString(6));
            tb.setAge(conn.rs.getString(5));
            tb.setYear(conn.rs.getString("year"));
            tb.setPeriod(conn.rs.getString("period"));
            tb.setMonth(conn.rs.getString("month"));
            tb.setAtten(conn.rs.getString("sessions_attended"));
            
            tb.setCount(count);
            tb.setGroup_id(conn.rs.getString(7));
           String targetpop="select group_name,district_name from groups join district on groups.district_id=district.district_id where group_id='"+conn.rs.getString(7)+"' or group_name='"+conn.rs.getString(7)+"' ";
           System.out.println(targetpop);
           
           conn.rs1=conn.st2.executeQuery(targetpop);
           if(conn.rs1.next()){
           tb.setGroup(conn.rs1.getString(1));
           tb.setDistrict(conn.rs1.getString(2));
           
           }
           // tb.setLessons(4);
            //add the bean object to an arraylist
            allstudents.add(tb);
            count++;
//           if(positioner==1 && count==500){
//               break;
//           }
            }//end of while
            session.setAttribute("allmembersAL", allstudents);
            //clear arraylist
            //allteachersAL.clear();
            session.setAttribute("reg_no_of_members", count);
            
            //this is the session that is being used to determine the row and column of the users
           // session.setAttribute("reg_no_of_lessons", "4");
            
            //session.setAttribute("reg_topic","Self Awareness");
            if(count==1){
              session.setAttribute("no_students","<font color=\"red\"><b>No Members have been registered in the database for this group</font><br>"
                      + "<a href=\"filter_member.jsp\" class=\"linkstyle\">Add Members</a>");
            
            }
            System.out.println("count"+count);
            
            response.sendRedirect("edit_member1.jsp");
            
        } catch (SQLException ex) {
            Logger.getLogger(geMemDetails.class.getName()).log(Level.SEVERE, null, ex);
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
