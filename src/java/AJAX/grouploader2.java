/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AJAX;

import hc.dbConn;
import java.io.FileWriter;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Geofrey Nyabuto
 */
public class grouploader2 extends HttpServlet {
String partner_id,selector,groups,fname="",mname="",lname="",phone="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
      String sln="";
        
        JSONArray ar= new JSONArray();
        
        try {
         dbConn conn= new dbConn();
  partner_id=request.getParameter("partner_id");
  
  //check whether this java page has been called from the transfer one facilitator .jsp else, dont include the fnme, mname and lname and phoneno
  if(request.getParameter("fname")!=null){
  fname=request.getParameter("fname");
  }
  
    if(request.getParameter("mname")!=null){
  mname=request.getParameter("mname");
  }
      if(request.getParameter("lname")!=null){
  lname=request.getParameter("lname");
                                              }
  
      
   if(request.getParameter("phone")!=null){
  
  phone=request.getParameter("phone");
  }     
      
            groups="<option value=\"\">Choose Group</option>";
//            if(!fname.equals("")&&fname!=null){
//                
//             selector="select * from groups  where partner_id='"+partner_id+"' order by group_name ASC";
//            
//            }
            
            selector="select * from groups where partner_id='"+partner_id+"' order by group_name ASC";
            conn.rs=conn.st.executeQuery(selector);
            while(conn.rs.next())
            {
                
                String getgrp="select * from facilitator_details where groups_id like '%"+conn.rs.getString(1)+"%' and first_name LIKE ? and phone = ? and (middle_name LIKE ? or sur_name LIKE ? )";
                
                
                
                conn.pst=conn.conne.prepareCall(getgrp);
                
                //conn.pst.setString(1, );
                conn.pst.setString(1, fname);
                conn.pst.setString(2, phone);
                conn.pst.setString(3, mname);
                conn.pst.setString(4, lname);
                
                conn.rs1=conn.pst.executeQuery();
                
                
                if(conn.rs1.next()){
                    
                groups=groups+"<option title=\""+conn.rs.getString("group_name") +"\" selected value=\""+conn.rs.getString("group_id") +"\">"+conn.rs.getString("group_name") +"</option>";
               JSONObject obj= new JSONObject();
               JSONObject grpid= new JSONObject();
                obj.put("selected",true);
                obj.put("value",conn.rs.getString("group_name"));
                grpid.put(conn.rs.getString("group_id"), obj);
                  ar.put(grpid);
                }
                else{                
            
            groups=groups+"<option title=\""+conn.rs.getString("group_name") +"\" value=\""+conn.rs.getString("group_id") +"\">"+conn.rs.getString("group_name") +"</option>";
               JSONObject obj= new JSONObject();
               JSONObject grpid= new JSONObject();
                obj.put("selected",false);
                obj.put("value",conn.rs.getString("group_name"));
                grpid.put(conn.rs.getString("group_id"), obj);
                  ar.put(grpid);
                }
                }
            
            
        System.out.println(ar);
        String allpath = getServletContext().getRealPath("/data/1000.json"); 
          System.out.println(allpath);
           // out.println(allpath);
           
            FileWriter fl = new FileWriter(allpath);
        
        JSONObject bb= new JSONObject();
        bb.put("Employee", ar);
        fl.write(ar.toString());
        fl.flush();
        fl.close();
        out.println("aa=aa\nbb=bbb\ncc+=ccc+\ngnk=//Gana");
        out.println("aai=true=Arifama+true");
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
            Logger.getLogger(grouploader2.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(grouploader2.class.getName()).log(Level.SEVERE, null, ex);
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
