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
public class view_facilitators extends HttpServlet {
String selector;
HttpSession session;
ArrayList vlist=new ArrayList();
String group_id="",group_name="",namer="";
String sur_name="",partner="",group="";
String s,p,g,sp,sg,pg,spg;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
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
            
            
                           session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
            dbConn conn =new dbConn();
       
            sur_name=request.getParameter("sname");
            partner=request.getParameter("partner");
            group=request.getParameter("phone");
            if(sur_name==null){
            sur_name="";
            }
            if(partner==null){
            partner="";
            }
            if(group==null){
            group="";
            }
          System.out.println(sur_name);
           System.out.println(partner);
            System.out.println(group);
            vlist.clear();
            selector="select * from facilitator_details";
            s="select * from facilitator_details where first_name='"+sur_name+"' || sur_name='"+sur_name+"' || middle_name='"+sur_name+"'";
            p="select * from facilitator_details where partner_id='"+partner+"'";
//            g="select * from facilitator_details where phone='"+group+"'";
        String    phone="select * from facilitator_details where phone='"+group+"'";
            sp="select * from facilitator_details where first_name='"+sur_name+"' || sur_name='"+sur_name+"' || middle_name='"+sur_name+"' AND partner_id='"+partner+"'";
            sg="select * from facilitator_details where first_name='"+sur_name+"' || sur_name='"+sur_name+"' || middle_name='"+sur_name+"' AND phone='"+group+"'";
            pg="select * from facilitator_details where partner_id='"+partner+"' AND phone='"+group+"'";
            spg="select * from facilitator_details where first_name='"+sur_name+"' || sur_name='"+sur_name+"' || middle_name='"+sur_name+"' AND partner_id='"+partner+"' AND phone='"+group+"'";
          if(group.equals("") && partner.equals("") && sur_name.equals("")){ 
            conn.rs=conn.st.executeQuery(selector);}
          
 if(!group.equals("") && !partner.equals("") && !sur_name.equals("")){ 
            conn.rs=conn.st.executeQuery(spg);}
 if(sur_name.equals("") && partner.equals("") && !group.equals("")){
conn.rs=conn.st.executeQuery(phone);
}
if(sur_name.equals("") && group.equals("") && !partner.equals("")){
conn.rs=conn.st.executeQuery(p);
}
if(group.equals("") && partner.equals("") && !sur_name.equals("") ){
conn.rs=conn.st.executeQuery(s);
}
 if(sur_name.equals("") && partner!=null && !group.equals("")){
conn.rs=conn.st.executeQuery(pg);
} 
if(partner.equals("") && sur_name!=null && !group.equals("")){
conn.rs=conn.st.executeQuery(sg);
}
if(group.equals("") && partner!=null && !sur_name.equals("")){
conn.rs=conn.st.executeQuery(sp);
}
while(conn.rs.next()){
    System.out.println(conn.rs.getString("first_name"));
      view_facilitator_bean view_facilitator_bn=new view_facilitator_bean();
//    Set First Name, Last Name, Phone  Number,facilitator_id,partner _id in  A Bean
     view_facilitator_bn.setFname(conn.rs.getString("first_name"));
  view_facilitator_bn.setMname(conn.rs.getString("middle_name"));
     view_facilitator_bn.setId(conn.rs.getString("facilitator_id"));
     view_facilitator_bn.setLname(conn.rs.getString("sur_name"));
     view_facilitator_bn.setPhone(conn.rs.getString("phone"));
     view_facilitator_bn.setPartner_id(conn.rs.getString("partner_id"));
        System.out.println(conn.rs.getString("first_name"));
//        Get the parners name and set into a bean object.
       String selector2=" select * from partner where partner_id='"+conn.rs.getString("partner_id").toString()+"'";
       conn.rs1=conn.st1.executeQuery(selector2);
       while(conn.rs1.next()){
        view_facilitator_bn.setPartner_name(conn.rs1.getString("partner_name"));
        break;
       }
//   Get  Split the groups ids from the database 
//       Put each group id in a bean
//       Get their respective names and store them in a bean
       String [] ids=conn.rs.getString("groups_id").split(",");
       for(int i=0; i<ids.length; i++){
       group_id=ids[i];
//       Set group id to the bean
        view_facilitator_bn.setGroup_id(group_id);
       String selector3="select * from groups where group_id='"+group_id+"'";
       conn.rs2=conn.st2.executeQuery(selector3);
       while(conn.rs2.next()){
           namer=conn.rs2.getString("group_name")+",";
           group_name=group_name+namer;
//           set group name to the bean
        
       
       }
     
       }
         view_facilitator_bn.setGroup_name(group_name); 
          namer="";
        group_name="";
        //    add all to the arrayList
       vlist.add(view_facilitator_bn);
}
        
  String inserter="insert into audit set host_comp='"+session.getAttribute("computer_name") +"' , action='Viewed Facilitators',time='"+current_time+"',actor_id='"+session.getAttribute("userid") +"'";
                     conn.st3.executeUpdate(inserter);       
session.setAttribute("vlist", vlist);
response.sendRedirect("view_facilitators.jsp");
       sur_name="";
       partner="";
       group="";

        } finally {            
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
            Logger.getLogger(view_facilitators.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(view_facilitators.class.getName()).log(Level.SEVERE, null, ex);
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
