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
 * @author Geofrey Nyabuto
 */
public class add_facilitator_session extends HttpServlet {
String county,district,county_id,district_id,county_name,district_name,partner,partner_name,partner_id;
String [] all_county,all_district,all_partner;
HttpSession session;
String groups,selector,target_pop_name,no_of_facils;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        groups="";
        selector="";
target_pop_name="";
no_of_facils="";
     
 county="";
district="";
county_id="";
district_id="";
county_name="";
district_name="";
partner="";
partner_name="";
        partner_id="";


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
     session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
     
     if(request.getParameter("no_of_facils")!=null){
no_of_facils=request.getParameter("no_of_facils");  
     }
     else{
         //default value
     no_of_facils="2";
     
     }
     
     if(request.getParameter("county")!=null){
county_id=request.getParameter("county");
     }
     else{
     
     county_id=session.getAttribute("county_id").toString();
     
     }
     
     if(request.getParameter("district")!=null){
         
         
         
district=request.getParameter("district");
all_district=district.split(",");
district_id=all_district[0];
district_name=all_district[1];

     }
     else{
         
    district_id=session.getAttribute("district_id").toString();
     district_name=session.getAttribute("district_name").toString();
     System.out.print("DISTRICT NAME:"+district_name);
     }
     
     if(request.getParameter("partner_name")!=null){
partner_id=request.getParameter("partner_name");

     }else{
     
     partner_id=  session.getAttribute("partner_id").toString();  
     
     }
     String target_pop="";

if(request.getParameter("target_pop")!=null){
 target_pop=request.getParameter("target_pop");
}
else{
target_pop=session.getAttribute("target_id").toString();
}

System.out.println("county: "+county_id);
System.out.println("district: "+district_id);
System.out.println("partner: "+partner_id);
System.out.println("target_pop: "+target_pop);



//get partner name
String selector_="select * from partner where partner_id='"+partner_id+"'";
dbConn conn= new dbConn();
conn.rs=conn.st.executeQuery(selector_);
while (conn.rs.next()){
partner_name=conn.rs.getString("partner_name").toString();

}
//get target pop name
String selector3="select * from target_population where target_id='"+target_pop+"'";
conn.rs=conn.st.executeQuery(selector3);
while (conn.rs.next()){
target_pop_name=conn.rs.getString("population_name").toString();
}
session.setAttribute("target_id", target_pop);
session.setAttribute("district_id", district_id);
session.setAttribute("district_name", district_name );
session.setAttribute("partner_id", partner_id);
session.setAttribute("partner_name", partner_name);
session.setAttribute("target_pop_name", target_pop_name);

System.out.println("Target pop "+session.getAttribute("target_pop_name")+"__"+target_pop_name);
System.out.println("District "+session.getAttribute("district_name"));
System.out.println("Partner "+session.getAttribute("partner_name"));

            //groups="<option value=\"\">Choose Group(s)</option>";
            //selector="select* from groups where target_pop_id='"+session.getAttribute("target_id")+"' and district_id='"+district_id+"' order by group_name ";
            selector="select* from groups where  district_id='"+district_id+"' order by group_name ";
            conn.rs=conn.st.executeQuery(selector);
            while(conn.rs.next())
            {
                if(session.getAttribute("added_group_id")!=null){
                if(session.getAttribute("added_group_id").equals(conn.rs.getString("group_id"))){
                
               groups=groups+"<option selected value=\""+conn.rs.getString("group_id") +","+"\">"+conn.rs.getString("group_name") +"</option>";
            
                
                }
                else{
                
            groups=groups+"<option value=\""+conn.rs.getString("group_id") +","+"\">"+conn.rs.getString("group_name") +"</option>";
            
                }}
                else{
                
                      
            groups=groups+"<option value=\""+conn.rs.getString("group_id") +","+"\">"+conn.rs.getString("group_name") +"</option>";
            
                
                }
            
            
            }
            session.setAttribute("groups",groups);
          
            
            //session.setAttribute("prevpage","FormWizard_facils.jsp");
            
            
            
            String createdtable="";
            
            int noofrows=0;
            if(request.getParameter("no_of_facils")!=null){
            
            noofrows=Integer.parseInt(no_of_facils);
            }
            
            for(int a=1;a<=noofrows;a++){
            
            createdtable+="<tr><td>"+a+"</td><td >" 
                                    +"<select title=\"To unselect a selected group, hold the control button and click at the group name \" name=\"groups"+a+"\" id=\"groups"+a+"\" class=\"textbox4\" multiple=\"true\" style=\"background: #EOEOFF;\">" 
                                 +session.getAttribute("groups");
                                createdtable+="</select> </td>"
                                +"<td><input type=\"text\" name=\"fname"+a+"\"  id=\"fname"+a+"\"  placeholder=\"First name\" class=\"textbox1\" style=\"background: #EOEOFF\"/></td>" 
                                +"<td><input type=\"text\" name=\"mname"+a+"\"  id=\"mname"+a+"\"  placeholder=\"Middle name\" class=\"textbox1\" style=\"background: #EOEOFF\"/></td>"
                                +"<td> <input type=\"text\" name=\"lname"+a+"\"  id=\"lname"+a+"\"  placeholder=\"Last name\" class=\"textbox1\" style=\"background: #EOEOFF\"/> </td>" 
                                +"<td><input type=\"text\" name=\"phone"+a+"\" id=\"phone"+a+"\" pattern=\".{10,10}\" maxlength=\"10\" value=\"\"  onkeypress=\"return numbers(event)\" placeholder=\"Phone\" class=\"textbox1\" style=\"background: #EOEOFF\"/></td></tr>";
            
            
            
            }
            
            createdtable+="<input type=\"hidden\" value=\""+noofrows+"\" name=\"no_of_facils\" id=\"no_of_facils\">";
            
            session.setAttribute("createdfacilitatorstable",createdtable);
             System.out.println("GROUPS_____:::"+session.getAttribute("groups"));
             
             String next="add_facilitator2.jsp";
             
             if(no_of_facils.equals("0")){
             next="FormWizard_members.jsp";
             }
              
             
              response.sendRedirect(next);
           
            
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
            Logger.getLogger(add_facilitator_session.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_facilitator_session.class.getName()).log(Level.SEVERE, null, ex);
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
