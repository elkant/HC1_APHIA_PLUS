/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AJAX;

import hc.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author Elkant
 */
public class loadgroups extends HttpServlet {
HttpSession session=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        session=request.getSession();
        
        
        PrintWriter out = response.getWriter();
        try {
           
          dbConn conn=new dbConn();
            
          String county=request.getParameter("county");
          
          String countyar[]=county.split(",");
          String cnty=countyar[0];
          
          
          
          
           String wards="<option value=''>Select Ward</option>";
           String dists="{'':'Select sub-county'";
           String allpdts="{'':'Select ward'";
           String targets="{'':'Select target pop'";
           ArrayList wardname=new ArrayList();
           ArrayList distid=new ArrayList();
           ArrayList wardid=new ArrayList();
           
               String getwards="select * from ward ";
              
                conn.rs1=conn.st1.executeQuery(getwards);
              
        //load wards in an arraylist   
         while(conn.rs1.next()){
       
          // wards+="<option value='"+conn.rs1.getString(1)+"'> "+conn.rs1.getString(2)+" </option>";
           allpdts+=","+"'"+conn.rs1.getString(1) +"':'"+conn.rs1.getString(2).toUpperCase()+"'";
                 
         }
           
           allpdts+="}";
           
         
           
                String getdist="select * from district where county_id='"+cnty+"'";
              
                conn.rs1=conn.st1.executeQuery(getdist);
              
        //load wards in an arraylist   
         while(conn.rs1.next()){
       
          // wards+="<option value='"+conn.rs1.getString(1)+"'> "+conn.rs1.getString(2)+" </option>";
           dists+=","+"'"+conn.rs1.getString(1) +"':'"+conn.rs1.getString("district_name").toUpperCase()+"'";
                 
         }
           
           dists+="}";
           
           
           
           ///load targets
           
             String gettagrget="select * from target_population";
              
                conn.rs1=conn.st1.executeQuery(gettagrget);
              
        //load wards in an arraylist   
         while(conn.rs1.next()){
       
          // wards+="<option value='"+conn.rs1.getString(1)+"'> "+conn.rs1.getString(2)+" </option>";
           targets+=","+"'"+conn.rs1.getString(1) +"':'"+conn.rs1.getString(2).toUpperCase()+"'";
                 
         }
           
           targets+="}";
           
           
             String table=" <thead>"
		            +"<tr>"
                           +"<th style='text-align:left;'>Group</th>"
                           +"<th style='text-align:left;'>Partner</th>"                         
                           +"<th style='text-align:left;'>Target Pop.</th>"
                           +"<th style='text-align:left;'>Ward</th>"
                           +"<th style='text-align:left;'>Sub-county</th>"
                           +"<th style='text-align:left;'>Edit</th>"
                            +"</tr>"		                
		            +"</thead>"
		            +"";
            
            String getdetails="select group_id, group_name as groupname, partner_name as partner, population_name as target,district_name as district, wardname as ward,groups.district_id as distid,groups.wardid as wardid,groups.target_pop_id as targetpopid,groups.partner_id as partnerid from groups join district on groups.district_id=district.district_id  join partner on groups.partner_id=partner.partner_id left join ward on groups.wardid=ward.wardid  join target_population on groups.target_pop_id=target_population.target_id where county_id='"+cnty+"'";
            
            conn.rs=conn.st.executeQuery(getdetails);            
            
            while(conn.rs.next()){
                //this is the name of a ward
                 String ward="";
                if(conn.rs.getString("ward")!=null){
                ward=conn.rs.getString("ward").toUpperCase();
                }
                
            //now filter the wards that belong to certain district only
                
                //for(int a=0;a<wardid.size();a++){
                //if(distid.get(a).equals(conn.rs.getString("distid"))){
                
               // wards+="<option value='"+wardid.get(a) +"'> "+wardname.get(a) +" </option>";
               // }
                
                //}
               
                
                table+="<tr id='"+conn.rs.getString(1)+"'>"
            + "<td style='text-align:left;width:280px;'>"+conn.rs.getString("groupname").toUpperCase()+"</td>"
            + "<td style='align:left;'>"+conn.rs.getString("partner")+"</td>"           
            + "<td style='align:left;width:300px;'>"+conn.rs.getString("target")+"</td>" 
            + "<td style='align:center;'>"+ward+"</td>"  
            + "<td style='align:left;width:200px;'>"+conn.rs.getString("district")+"</td>"           
            + "<td style='align:left;width:200px;'><a class='sbmit' target='_blank' href=\"edit_group_session?county="+county+"&district="+conn.rs.getString("distid")+","+conn.rs.getString("district")+"&partner="+conn.rs.getString("partnerid")+"&group="+conn.rs.getString("group_id")+"&ward="+conn.rs.getString("wardid")+"><b>Edit group</b></a></td>"           
                    
            + "</tr>";
                
            
            }    
            
            table+=" <tfoot>"
		            +"<tr>"
                           +"<th>Group</th>"
                           +"<th>Partner</th>"                         
                           +"<th>Target Pop.</th>"
                           +"<th>Ward</th>"
                           +"<th>Sub-county</th>"
                           +"<th>Edit</th>"
                            +"</tr>"		                
		            +"</tfoot>";
            //session.setAttribute("groupstb", table);
            
            JSONObject jo= new JSONObject();
            jo.put("tbl", table);
            jo.put("wards",allpdts);
            jo.put("dists",dists);
            jo.put("targets",targets);
            
            out.println(jo);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(loadgroups.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
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
