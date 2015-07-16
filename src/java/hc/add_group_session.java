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
public class add_group_session extends HttpServlet {
String county,district,county_id,district_id,county_name,district_name,partner,partner_name,partner_id,no_of_groups;
String [] all_county,all_district,all_partner;
HttpSession session;
String groups,selector,target_pop_name,groupname;
String year,period;
String nextpage="",msg="";
String prevpage="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String ward="";
        
       nextpage="FormWizard_facils.jsp"; 
       
       
       
       msg="";
       
       groupname="";
       
        try {
 session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
     
     //get the groupname
     
     groups="";
     if(request.getParameter("newgroup_name")!=null&&!request.getParameter("newgroup_name").equals("")){
         
     groups=request.getParameter("newgroup_name");
     //session.setAttribute("grpid", groups);
     System.out.println("THE SELECTED / ENTERED GROUP NAME IS "+groups);
     groupname=request.getParameter("newgroup_name");
     
     }
     else if(request.getParameter("group_name")!=null&&(request.getParameter("newgroup_name")==null||request.getParameter("newgroup_name").equals(""))){
   
         
     groups=request.getParameter("group_name");
       //System.out.println("SELECTED GROUP ID IS "+groups);
     
     }
     else{
     
     groups="";
     
     
      // System.out.println("GROUPS else"+groups);
     }
   
   //System.out.println("Group Contains  "+groups);  
     
   //session.setAttribute("", out);  
   
   //set the sesion id
   String wardid[]=null;
     
   if(request.getParameter("ward")!=null){
   
   ward=request.getParameter("ward");
   wardid=ward.split(",");
   session.setAttribute("wardid", wardid[0]);
   session.setAttribute("wardname", wardid[1]);
   }
   
   
     
     
   if(request.getParameter("no_of_groups")!=null){
       
     
no_of_groups=request.getParameter("no_of_groups");
                                                  }
   
   if(request.getParameter("county")!=null){
  
county_id=request.getParameter("county");
   }
   else{
   county_id=session.getAttribute("county_id").toString();
   
   }
   
   if(request.getParameter("district")!=null){
district=request.getParameter("district");
   }
   
   if(request.getParameter("partner_name")!=null){
partner_id=request.getParameter("partner_name");
      }
   String target_pop="";
   
   if(request.getParameter("target_pop")!=null){
 target_pop=request.getParameter("target_pop");
   }
if(request.getParameter("year")!=null){
year=request.getParameter("year");
}

if(request.getParameter("period")!=null){
period=request.getParameter("period");
}

if(request.getParameter("prevpage")!=null){
prevpage=request.getParameter("prevpage");
                                          }




System.out.println("county: "+county_id);
System.out.println("district: "+district);
System.out.println("partner: "+partner_id);
System.out.println("target_pop: "+target_pop);



//all_county=county.split(",");
all_district=district.split(",");
//county_id=all_county[0];
//county_name=all_county[1];
district_id=all_district[0];
district_name=all_district[1];

//set in session
session.setAttribute("target_id", target_pop);
session.setAttribute("district_id", district_id);
session.setAttribute("district_name", district_name );
session.setAttribute("partner_id", partner_id);
session.setAttribute("year", year);
session.setAttribute("period", period);
session.setAttribute("county_id", county_id);






 //========================Add the group to the database then get the id====================
    
       String uniqid=uniqueid().trim();  
     
     dbConn conn= new dbConn();
     
     
     //check if ward column exists in the group table
     
     String checkward="SELECT * FROM information_schema.COLUMNS WHERE   TABLE_SCHEMA = '"+conn.dbsetup[1]+"' AND TABLE_NAME = 'groups' AND COLUMN_NAME = 'wardid'";
     
     conn.rs=conn.st.executeQuery(checkward);
     //if the column doesnt exist, then add it
     if(!conn.rs.next()){
     
         String altertable="ALTER TABLE `groups` ADD COLUMN `wardid` VARCHAR(45) NULL DEFAULT 0 AFTER `timestamp`";
         
         conn.st.executeUpdate(altertable);
     
     }
     
   //now we are sure the ward column exists  
     if(request.getParameter("grpcat")!=null){
//if the group name exists, check the value of the ward in the database
//if it has not been updated, update it.
         if(request.getParameter("grpcat").equals("existing")){
    String checkexistance="select wardid from groups where group_name='"+groupname+"' or group_id='"+groups+"' and wardid='0'";
         conn.rs=conn.st.executeQuery(checkexistance);
         
         if(conn.rs.next()){
         //update the ward id
             
             String wardiid="";   
         
      if(session.getAttribute("wardid")!=null){
      String wardi[]=session.getAttribute("wardid").toString().split(",");
      wardiid=wardi[0];
      
      }  
      
         
             String updgroup=" update groups set wardid='"+wardiid+"' group_name='"+groupname+"' or group_id='"+groups+"'";
             conn.st2.executeUpdate(updgroup);
             System.out.println(updgroup);
         }
         
         }
}
     
     
     
     conn.rs=conn.st.executeQuery("select * from groups where group_name='"+groupname+"' or group_id='"+groups+"'");
     
     //do this only when the group being added is one and the name is specified in wizard form..
     
     if(no_of_groups.equals("1")&&!groups.equals("")){
     if(conn.rs.next()){
     nextpage="FormWizard_facils.jsp";
      msg=" <font color=\"green\">Choose next to add a facilitator and a core facilitator.</font>";
     session.setAttribute("added_group_id",groups);
     session.setAttribute("grpmsg", msg);
     }
     
     //insert the group name afresh
     else{
      String wardiid="";   
         
      if(session.getAttribute("wardid")!=null){
      String wardi[]=session.getAttribute("wardid").toString().split(",");
      wardiid=wardi[0];
      
      }  
         
         String qry="insert into groups(group_id,group_name,partner_id,target_pop_id,district_id,wardid) values('"+uniqid+"','"+groups+"','"+partner_id+"','"+target_pop+"','"+district_id+"','"+wardiid+"')";
     
    conn.st.executeUpdate(qry);
          
    session.setAttribute("added_group_id",uniqid);
    msg="<font color=\"green\">"+groups+"  added successfully.Choose next to add a facilitator and a core facilitator </font>";
     
    session.setAttribute("grpmsg", msg);
     
    nextpage="FormWizard_facils.jsp";
     
     }
     }
     else{
         
         System.out.println("Add Group 2 jsp is selected");
     
         //when adding group in the ordinary way
         
    nextpage="add_group2.jsp";
     
     }





//System.out.println("COUNTY ID ____SESSION"+session.getAttribute("county_id"));

//get partner name
String selector="select * from partner where partner_id='"+partner_id+"'";

conn.rs=conn.st.executeQuery(selector);
conn.rs.next();
partner_name=conn.rs.getString("partner_name").toString();
session.setAttribute("partner_name", partner_name);

//get target pop name
String selector3="select * from target_population where target_id='"+target_pop+"'";
conn.rs=conn.st.executeQuery(selector3);
conn.rs.next();
target_pop_name=conn.rs.getString("population_name").toString();
//System.out.println("inside the while: "+target_pop_name);
session.setAttribute("target_pop_name", target_pop_name);

session.setAttribute("prevpage",prevpage);


System.out.println("Target pop "+session.getAttribute("target_pop_name"));
//System.out.println("District "+session.getAttribute("district_name"));
System.out.println("Partner "+session.getAttribute("partner_name"));
//add
int noofgroups=0;


String createdtable="";

if(request.getParameter("no_of_groups")!=null){

noofgroups=Integer.parseInt(no_of_groups);

}

for(int a=1;a<=noofgroups;a++){
createdtable+="<tr><td>"+a+"</td><td><p>Enter Group name:</p></td><td><input type=\"text\" name=\"grp"+a+"\" id=\"grp"+a+"\"  placeholder=\"eg. Makutano\" class=\"textbox\" style=\"background: #6699ff\"/></td></tr>";
    
    
    

}
createdtable+="<tr><td colspan=\"3\"><input type=\"hidden\" value=\""+no_of_groups+"\" name=\"no_of_groups\" id=\"no_of_groups\"><input type=\"submit\"  name=\"sub\" value=\"Save Group\" class=\"textbox1\" style=\"background: orange; color: #ffffff\"></td></tr>";



session.setAttribute("created_groups", createdtable);

System.out.println("CURRENT GROUP ID IS _____"+session.getAttribute("added_group_id"));

response.sendRedirect(nextpage);
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
            Logger.getLogger(add_group_session.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_group_session.class.getName()).log(Level.SEVERE, null, ex);
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

        int year1 = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int milsec=cal.get(Calendar.MILLISECOND);
        
        
        return generateRandomNumber(800, 8000)+year1+""+month+""+date+hour+min+sec+milsec;
    }

 
 
   public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
    }
 
//==========================================================================




}
