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

/**
 *
 * @author Geofrey Nyabuto
 */
public class load_partner_pie extends HttpServlet {
String loaded_partners="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            loaded_partners="";
            String ids = request.getParameter("ids");
//            System.out.println("ids aRE  :  "+ids);
            
//            String  all_ids []= ids.split("-");
            
//            if(all_ids!=null){
                
//             for (int i=0;i<all_ids.length;i++)  {
//               if(all_ids[i]!=null && !all_ids[i].equals("")){
//                 String target_id=all_ids[i];
                 System.out.println("id are   :  are : "+ids);
                 String partner_selector="SELECT partner.partner_id,partner.partner_name FROM partner JOIN target_population ON target_population.partner_id=partner.partner_id"
                         + " WHERE target_population.population_name='"+ids+"'";
                 conn.rs=conn.st.executeQuery(partner_selector);
                 while(conn.rs.next()){
                    loaded_partners+="<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>"; 
                 }
                 
//             } 
                
//             }  
//            }
           System.out.println("lloaded partners   :  "+loaded_partners); 
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet load_partner_pie</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" +loaded_partners+"</h1>");
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
            Logger.getLogger(load_partner_pie.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(load_partner_pie.class.getName()).log(Level.SEVERE, null, ex);
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
