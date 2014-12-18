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
public class view_groups extends HttpServlet {
    ArrayList glist=new ArrayList();
    HttpSession session;
    String partner_id;
       int count=1;
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
            dbConn conn=new dbConn();
            glist.clear();
         
            partner_id=request.getParameter("partner_id");
            String selector="select * from groups";
            String selector2="select * from groups where partner_id='"+partner_id+"'";
            if(partner_id!=null && !partner_id.equals("")){
            conn.rs=conn.st.executeQuery(selector2);
            }
            else{
             conn.rs=conn.st.executeQuery(selector);
            }
            
            while(conn.rs.next()){
            view_group_bean vgb=new view_group_bean();
           vgb.setCount(count); 
           vgb.setGroup_name(conn.rs.getString("group_name"));
 String selector3="select * from partners where partner_id='"+conn.rs.getString("partner_id") +"'";
 conn.rs2=conn.st2.executeQuery(selector3);
 while(conn.rs2.next()){
           vgb.setPartner_name(conn.rs2.getString("partner_name"));
         
 }
     String selector4="select * from target_population where target_id='"+conn.rs.getString("target_pop_id") +"'";
 conn.rs3=conn.st3.executeQuery(selector4);
 while(conn.rs3.next()){
           vgb.setPopulation_name(conn.rs3.getString("population_name"));
    System.out.println("population name"+conn.rs3.getString("population_name"));        
 }
 glist.add(vgb);
 count++;
            }
      if(count==1){
            session.setAttribute("missing_group","<font color=\"red\">No groups Under that partner.</font>");   
           }
        String inserter="insert into audit set host_comp='"+session.getAttribute("computer_name") +"' , action='Viewed Groups',time='"+current_time+"',actor_id='"+session.getAttribute("userid") +"'";
                     conn.st3.executeUpdate(inserter); 
            session.setAttribute("glist", glist);
            response.sendRedirect("view_groups.jsp");
count=1;
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
            Logger.getLogger(view_groups.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(view_groups.class.getName()).log(Level.SEVERE, null, ex);
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
