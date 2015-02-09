/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class add_facilitator extends HttpServlet {
String  group1[];
String query1,phone1,lname1,mname1,fname1,phone="",name="",groups1,success,fail,fullname="",nextpage="";
HttpSession session;
int found,exist,added;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           dbConn conn= new dbConn();
              session=request.getSession();
     if (session.getAttribute("userid")==null) {
        nextpage="index.jsp";
    } else{
          groups1="";
           success=fail="";
           found=exist=added=0;
           
           
           int rows=0;
           
           if(request.getParameter("no_of_facils")!=null&&!request.getParameter("no_of_facils").equals("")){
           
               rows=Integer.parseInt(request.getParameter("no_of_facils"));
               
           }
           
           
           for (int j=1;j<=rows;j++){
               groups1="";
           group1=request.getParameterValues("groups"+j);
           if(request.getParameterValues("groups"+j)!=null){
           for(int i=0; i<group1.length; i++){
               if(group1[i]!=null && !group1[i].equals("")){
           groups1=groups1+group1[i];
           }}
           System.out.println(" groups are  :  "+groups1);
            fname1=request.getParameter("fname"+j);
            fname1=fname1.replace("'", "");
            
            mname1=request.getParameter("mname"+j);
            mname1=mname1.replace("'", "");
            
            lname1=request.getParameter("lname"+j);
            lname1=lname1.replace("'", "");
            
            
            
            
            if(request.getParameter("phone"+j)!=null){
            phone1=request.getParameter("phone"+j);
            }
            else{
            phone="";
            }
            System.out.println("fname  :  "+fname1);
            if(!fname1.equals("")){
          fullname=fname1+" "+lname1;
            String check_phone="SELECT * FROM facilitator_details WHERE phone='"+phone1+"' and first_name LIKE '"+fname1+"' and sur_name LIKE '"+lname1+"'";
            conn.rs=conn.st.executeQuery(check_phone);
            if(conn.rs.next()==true){
              //found=2; 
              found=2; 
             phone=conn.rs.getString("phone");
            name=conn.rs.getString("first_name")+" "+conn.rs.getString("sur_name");
            //now check whether the facilitator list of groups contains a certain group id..
            
            String searchfacil="select * from facilitator_details WHERE facilitator_id='"+conn.rs.getString(1) +"' and  groups_id LIKE '%"+groups1+"%'";
            
            System.out.println(" Searching for this facil "+searchfacil);
            
            conn.rs2=conn.st2.executeQuery(searchfacil);
            
            
            //if that group id isnt on the list of the admin, then append it.
           
            
            
            if(!conn.rs2.next()){
            
              String upda="update facilitator_details set groups_id=CONCAT( groups_id,'"+groups1+"') where facilitator_id='"+conn.rs.getString(1)+"'";  
             
              System.out.println("FACILITATOR EXISTS?===="+upda);
              
              conn.st3.executeUpdate(upda);
            
            }
            
            
            }//end of if that checks for duplicate facilitator
            
            
            if(found==0){
                
          String id=uniqueid().trim();
         System.out.println("Unique id"+id);       
           
         //add a confrimation that some facilitators have been added indeed.
         session.setAttribute("isfacilsadded", "1");
         
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String mdate;

                    Date mydate = new Date();
                    mdate = formatter.format(mydate);
                        
         
             query1="insert into facilitator_details set facilitator_id='"+id+"', first_name='"+fname1+"', middle_name='"+mname1+"',sur_name='"+lname1+"', phone='"+phone1+"',partner_id='"+session.getAttribute("partner_id") +"',groups_id='"+groups1+"', timestamp='"+mdate+"'";
          added+=conn.st.executeUpdate(query1);
//             added++;
            success=fullname+",";
            }
            
            if(found>0){
            exist++;
            fail+="Facilitator <font color=\"black\">"+name+"</font><font color=\"red\"> has alredy been registered to the system , ";
            } 
            found=0;
           }
           
           }
           if(added>0){
           session.setAttribute("success", "<font color=\"green\">"+success+" Added successfully");
           }if(exist>0){
           session.setAttribute("fail", "<font color=\"red\">"+fail);
           }
           nextpage="FormWizard_members.jsp";
           }
           
           //if this session is active, it means that we are adding a long form.
           
           if(session.getAttribute("prevpage")!=null){
           nextpage="FormWizard_members.jsp";
           }
           
           
           response.sendRedirect(nextpage);
           phone="";
           name="";
           }
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
            Logger.getLogger(add_facilitator.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_facilitator.class.getName()).log(Level.SEVERE, null, ex);
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
