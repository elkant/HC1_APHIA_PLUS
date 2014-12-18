/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
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
public class add_group extends HttpServlet {
String grp1,nextpage="",groups="";
String query1, added, existing;
HttpSession session;
String year,period;
String no_of_groups="";
int found;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
     session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
     session.getAttribute("district_id");
     
dbConn conn= new dbConn();


added=existing="";
int totalgrps=0;

no_of_groups=request.getParameter("no_of_groups");

if(request.getParameter("no_of_groups")!=null){

totalgrps=Integer.parseInt(no_of_groups);

}



for(int i=1;i<=totalgrps;i++){
    found=0;
grp1=request.getParameter("grp"+i);




System.out.println("group names are  :  "+grp1);
if(!grp1.equals("")){
    System.out.println("entered group names are  :  "+grp1);
String existence_checker="SELECT * FROM groups WHERE group_name LIKE '"+grp1.trim()+"'&& partner_id='"+session.getAttribute("partner_id")+"' && target_pop_id='"+session.getAttribute("target_id") +"'&& district_id='"+session.getAttribute("district_id") +"'";
conn.rs=conn.st.executeQuery(existence_checker);
if(conn.rs.next()==true){
found++;
existing+=","+grp1;
}
if(found==0){
    grp1=grp1.toUpperCase();
    
    
    
    String grpid=uniqueid().trim();
   session.setAttribute("added_group_id", grpid); 
query1="insert into groups (group_id,group_name,partner_id,target_pop_id,district_id) VALUES('"+grpid+"','"+grp1+"','"+session.getAttribute("partner_id")+"','"+session.getAttribute("target_id") +"','"+session.getAttribute("district_id") +"')";
conn.st.executeUpdate(query1);
added+=","+grp1;
System.out.println("groups added successfully"+query1);
}
}
}
if(!added.equals("")){
session.setAttribute("added_groups", "<font color=\"green\">"+added+" Added Successfully.</font>");
}
if(!existing.equals("")){
session.setAttribute("existing_groups", "<font color=\"red\">"+existing+"  not added to the system..They already exist.</font>");
}
response.sendRedirect("add_group2.jsp");
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
            Logger.getLogger(add_group.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_group.class.getName()).log(Level.SEVERE, null, ex);
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



//====================random id functions================================ 

 public String uniqueid() {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int milsec=cal.get(Calendar.MILLISECOND);
        
        
        return generateRandomNumber(800, 8000)+year+""+month+""+date+hour+min+sec+milsec;
    }

 
 
   public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
 
//==========================================================================



}
