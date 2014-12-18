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
public class edit_group_session extends HttpServlet {
String county,district,group,county_id,district_id,county_name,district_name,partner,partner_name,partner_id,group_id,group_name;
String [] all_county,all_district,all_partner,all_group;
HttpSession session;
String groups,selector;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
               session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
county=request.getParameter("county");
district=request.getParameter("district");
partner_id=request.getParameter("partner");
String selector="select * from partner where partner_id='"+partner_id+"'";
dbConn conn= new dbConn();
conn.rs=conn.st.executeQuery(selector);
while (conn.rs.next()){
partner_name=conn.rs.getString("partner_name").toString();

}

group_id=request.getParameter("group");
String selector2="select * from groups where group_id='"+group_id+"'";
String targetid="";
conn.rs=conn.st.executeQuery(selector2);
while (conn.rs.next()){
group_name=conn.rs.getString("group_name").toString();
targetid=conn.rs.getString("target_pop_id").toString();
}
all_county=county.split(",");
all_district=district.split(",");

county_id=all_county[0];
county_name=all_county[1];
district_id=all_district[0];
district_name=all_district[1];


session.setAttribute("targetid", targetid );
session.setAttribute("county_id", county_id );
session.setAttribute("county_name", county_name);
session.setAttribute("district_id", district_id);
session.setAttribute("district_id_rare", district_id);
session.setAttribute("district_name", district_name );
session.setAttribute("partner_id", partner_id);
session.setAttribute("partner_name", partner_name);

session.setAttribute("group_id", group_id);
session.setAttribute("group_name", group_name);
response.sendRedirect("edit_group2.jsp");

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
            Logger.getLogger(edit_group_session.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(edit_group_session.class.getName()).log(Level.SEVERE, null, ex);
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
