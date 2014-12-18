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

/**
 *
 * @author Manuel
 */
public class loadforms extends HttpServlet {


 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
     String formoptions="";
    formoptions="<option value=\"\">select form</option>";
    
    String facilitatorids="";
    String month="";
    String year="";
    
    facilitatorids=request.getParameter("facils");
    month=request.getParameter("month");
    year=request.getParameter("year");
    
    String facilitatorsAR[]=facilitatorids.split(",");
    
    String subqry="where  (";
    
    
    for(int a=0;a<facilitatorsAR.length;a++){
    
    if(!facilitatorsAR[a].equals("")){
    
        if(a<facilitatorsAR.length-1){
        
        subqry+=" facilitator_id LIKE '"+facilitatorsAR[a]+"' or ";
    
                                     }
        
         else{
        
            subqry+=" facilitator_id LIKE '"+facilitatorsAR[a]+"' ) and forms.year='"+year+"' and forms.month='"+month+"'";
        
            }
        
    }
    
    
    }
    
    
    dbConn conn= new dbConn();
        int count=0;
    
    String getform="select distinct forms.form_id , form_number from register_attendance join forms on register_attendance.form_id=forms.form_id "+subqry+"";
    
    if(!subqry.equals("where  (")){
    
    conn.rs=conn.st.executeQuery(getform);
    
    
    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+getform);
    

    
    while(conn.rs.next()){
    
    formoptions+="<option value=\""+conn.rs.getString(1) +","+conn.rs.getString(2)+"\">"+conn.rs.getString(2).replace("-", "") +"</option>";
    
    count++;
    
    }
    
    if(count==0){
    
    formoptions+="<option value=\"\">no added forms yet</option>";
    
    }
    
    }
    PrintWriter out = response.getWriter();
    try {
       
        out.println(formoptions);
    
        
        
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(loadforms.class.getName()).log(Level.SEVERE, null, ex);
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
