/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

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

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_target_pie extends HttpServlet {
ArrayList alist = new ArrayList();
String pop_ids,all_pops;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            alist.clear();
            pop_ids="";
            dbConn conn = new dbConn();
             String target1_selector="select DISTINCT population_name from target_population";
             conn.rs=conn.st.executeQuery(target1_selector);
             while(conn.rs.next()){
    alist.add(conn.rs.getString(1)); 
 }
             all_pops="";
              all_pops+="<option value=\"\">Choose Target Population</option>"; 
              System.out.println("no of targets  :  "+alist.size());
  for(int i=0;i<alist.size();i++){
//             String pop_names="SELECT * FROM target_population WHERE population_name='"+alist.get(i).toString()+"'";
//             conn.rs=conn.st.executeQuery(pop_names);
//             while(conn.rs.next()){
//              pop_ids+=conn.rs.getString(1)+"-";   
//             }
           all_pops+="<option value=\""+alist.get(i).toString()+"\">"+alist.get(i).toString()+"</option>"; 
//            pop_ids="";
            
  }
       System.out.println("all pops  :  "+all_pops);
  /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet load_target_pie</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +all_pops+ "</h1>");
            out.println("</body>");
            out.println("</html>");
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
            Logger.getLogger(load_target_pie.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_target_pie.class.getName()).log(Level.SEVERE, null, ex);
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
