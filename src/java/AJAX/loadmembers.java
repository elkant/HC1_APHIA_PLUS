/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package AJAX;

import hc.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Manuel
 */
public class loadmembers extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
 
//   sSearch=
//   bRegex=false
//   sSearch_0=
//   &bRegex_0=false
//    &bSearchable_0=true
//    &sSearch_1=&bRegex_1=false&bSearchable_1=true&sSearch_2=&bRegex_2=false&bSearchable_2=true&sSearch_3=&bRegex_3=false&bSearchable_3=true&sSearch_4=&bRegex_4=false&bSearchable_4=true&sSearch_5=&bRegex_5=false&bSearchable_5=true&sSearch_6=&bRegex_6=false&bSearchable_6=true&sSearch_7=&bRegex_7=false&bSearchable_7=true&iSortingCols=1&iSortCol_0=0&sSortDir_0=asc&bSortable_0=true&bSortable_1=true&bSortable_2=true&bSortable_3=true&bSortable_4=true&bSortable_5=true&bSortable_6=true&bSortable_7=true&_=1415132690271' -H 'Host: localhost:8080' -H 'User-Agent: Mozilla/5.0 (Windows NT 6.2; rv:32.0) Gecko/20100101 Firefox/32.0' -H 'Accept: application/json, text/javascript, */*; q=0.01' -H 'Accept-Language: en-US,en;q=0.5' -H 'Accept-Encoding: gzip, deflate' -H 'X-Requested-With: XMLHttpRequest' -H 'Referer: http://localhost:8080/HC1_APHIA_PLUS/edit_member1.jsp' -H 'Cookie: JSESSIONID=4079E8320592D2DF5AAFB98DF5A05F6F'
//    
//    
    dbConn conn= new dbConn();
    
    String selectmembers="select member_id,first_name, mname,sur_name,sex,age,group_name,year,period,month from member_details join groups on member_details.group_id=groups.group_id limit 50";
 
        JSONArray megaarray = new JSONArray();
        JSONObject obj = new JSONObject();
        JSONObject dataobj = new JSONObject();
    
    conn.rs=conn.st.executeQuery(selectmembers);
    int count=0;
    try {
       
        
  while(conn.rs.next()){
  count++;
   JSONArray array = new JSONArray();
  JSONObject object = new JSONObject();
         for(int a=1;a<=10;a++){
             
            
            array.put(conn.rs.getString(a));
            
         }
   megaarray.put(array); 
   
  //System.out.println(count+"~~"+conn.rs.getString(2)+"  "+conn.rs.getString(3)+" "+conn.rs.getString(3));
  
  
  }
//         sEcho=1&
//   iColumns=8
//   sColumns=
//   iDisplayStart=0
//   iDisplayLength=10 
      obj.put("sEcho", "1");
      obj.put("sPaginationType", "full_numbers");
      obj.put("bJQueryUI",true);    
      obj.put("iColumns", "10");
     
      obj.put("iDisplayStart", "0");
      obj.put("iDisplayLength", "10");
      obj.put("iTotalRecords", count);
      obj.put("iSortingCols","1");
      //obj.put("iTotalDisplayRecords", "8");
      obj.put("sSortDir_0","asc");
      obj.put("aaData", megaarray);     
        
      System.out.println(obj);
      
       out.println(obj); 
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(loadmembers.class.getName()).log(Level.SEVERE, null, ex);
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
