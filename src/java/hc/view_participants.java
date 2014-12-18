/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class view_participants extends HttpServlet {
ArrayList mlist=new ArrayList();
HttpSession session;
String group_id,name;
int count;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);
String yr,mnth,dater,hr,mn,sc,action="";
yr=Integer.toString(year);
mnth=Integer.toString(month);
dater=Integer.toString(date);
hr=Integer.toString(hour);
mn=Integer.toString(min);
sc=Integer.toString(sec);   
String current_time=yr+"-"+mnth+"-"+dater+"-"+hr+":"+mn+":"+sc;
        
        
group_id=request.getParameter("group_id");
name=request.getParameter("name");
if(name==null){
    name="";
}
if(group_id==null){
    group_id="";
}
     session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
dbConn conn=new dbConn();
count=1;
mlist.clear();
String selector="select * from member_details";
String selector2="select * from member_details where group_id='"+group_id+"'";
 String namer="select * from member_details where first_name='"+name+"' || sur_name='"+name+"' || mname='"+name+"'";
 String namer_group="select * from member_details where group_id='"+group_id+"' AND first_name='"+name+"' || sur_name='"+name+"' || mname='"+name+"'";

 if(!group_id.equals("")&&name.equals("")){
conn.rs=conn.st.executeQuery(selector2);
}
if(group_id.equals("")&& name.equals("")){
conn.rs=conn.st.executeQuery(selector);
}
if(group_id.equals("") && !name.equals("")){
conn.rs=conn.st.executeQuery(namer);
}
if(!group_id.equals("") && !name.equals("")){
conn.rs=conn.st.executeQuery(namer_group);
}
while(conn.rs.next()){
 view_participant_bean vmb=new view_participant_bean();
 vmb.setAge(conn.rs.getString("age"));
  vmb.setCount(count);  
  vmb.setFname(conn.rs.getString("first_name"));  
  vmb.setLname(conn.rs.getString("sur_name"));
  vmb.setMname(conn.rs.getString("mname"));
  vmb.setSessions_attended(conn.rs.getString("sessions_attended"));
  vmb.setSex(conn.rs.getString("sex"));
  String selector3="select * from groups where group_id='"+conn.rs.getString("group_id") +"'";
  conn.rs2=conn.st2.executeQuery(selector3);
  while(conn.rs2.next()){
      vmb.setGroup_name(conn.rs2.getString("group_name"));
  }
  count++;
  mlist.add(vmb);
  
}
  String inserter="insert into audit set host_comp='"+session.getAttribute("computer_name") +"' , action='Viewed Participants',time='"+current_time+"',actor_id='"+session.getAttribute("userid") +"'";
                     conn.st3.executeUpdate(inserter); 
session.setAttribute("mlist", mlist);
count=1;
response.sendRedirect("view_participants.jsp");
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
            Logger.getLogger(view_participants.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(view_participants.class.getName()).log(Level.SEVERE, null, ex);
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
