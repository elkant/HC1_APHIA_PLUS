
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
public class addpart extends HttpServlet {
String dist1,dist2,dist3,dist4,dist5,dist6,dist7,dist8,dist9,dist10,dist11,dist12,dist13,dist14,dist15,district_id,nextpage="",county_name="";
String query0,query1,query2,query3,query4,query5,query6,query7,query8,query9,query10,query11,query12,query13,query14,query15;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
dbConn conn=new dbConn();
     session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
//receive parameters

district_id=session.getAttribute("district_id").toString();
dist1=request.getParameter("dist1");
dist2=request.getParameter("dist2");
dist3=request.getParameter("dist3");
dist4=request.getParameter("dist4");
dist5=request.getParameter("dist5");
dist6=request.getParameter("dist6");
dist7=request.getParameter("dist7");
dist8=request.getParameter("dist8");
dist9=request.getParameter("dist9");
dist10=request.getParameter("dist10");
dist11=request.getParameter("dist11");
dist12=request.getParameter("dist12");
dist13=request.getParameter("dist13");
dist14=request.getParameter("dist14");
dist15=request.getParameter("dist15");

query0="select * from partners where partner_name='"+dist1+"' || '"+dist2+"'||  '"+dist3+"' || '"+dist4+"' || '"+dist5+"'||  "
        + "'"+dist6+"' '"+dist7+"' || '"+dist8+"'||  '"+dist9+"' '"+dist10+"' || '"+dist11+"'||  '"+dist12+"' '"+dist13+"' || '"+dist14+"'||  '"+dist15+"'";

query1="insert into partners set partner_name='"+dist1+"', district_id='"+district_id+"' ";
query2="insert into partners set partner_name='"+dist2+"', district_id='"+district_id+"' ";
query3="insert into partners set partner_name='"+dist3+"', district_id='"+district_id+"' ";
query4="insert into partners set partner_name='"+dist4+"', district_id='"+district_id+"' ";
query5="insert into partners set partner_name='"+dist5+"', district_id='"+district_id+"' ";
query6="insert into partners set partner_name='"+dist6+"', district_id='"+district_id+"' ";
query7="insert into partners set partner_name='"+dist7+"', district_id='"+district_id+"' ";
query8="insert into partners set partner_name='"+dist8+"', district_id='"+district_id+"' ";
query9="insert into partners set partner_name='"+dist9+"', district_id='"+district_id+"' ";
query10="insert into partners set partner_name='"+dist10+"', district_id='"+district_id+"' ";
query11="insert into partners set partner_name='"+dist11+"', district_id='"+district_id+"' ";
query12="insert into partners set partner_name='"+dist12+"', district_id='"+district_id+"' ";
query13="insert into partners set partner_name='"+dist13+"', district_id='"+district_id+"' ";
query14="insert into partners set partner_name='"+dist14+"', district_id='"+district_id+"' ";
query15="insert into partners set partners_name='"+dist15+"', district_id='"+district_id+"' ";
int counter=0;
conn.rs=conn.st.executeQuery(query0);
if(conn.rs.next()){
    String namer=conn.rs.getString(2).toString();
    session.setAttribute("dist_exist","<font color=\"red\">"+namer+" Partner Already exist.</font>");    
       nextpage="add_partner2.jsp" ;
        }
else if (!conn.rs.next()) {
if(dist1!=null && !dist1.equals("")){
conn.st2.executeUpdate(query1);
counter++;
}
if(dist2!=null && !dist2.equals("")){
conn.st2.executeUpdate(query2);
counter++;
}
if(dist3!=null && !dist3.equals("")){
conn.st2.executeUpdate(query3);
counter++;
}
if(dist4!=null && !dist4.equals("")){
conn.st2.executeUpdate(query4);
counter++;
}
if(dist5!=null && !dist5.equals("")){
conn.st2.executeUpdate(query5);
counter++;
}
if(dist6!=null && !dist6.equals("")){
conn.st2.executeUpdate(query6);
counter++;
}
if(dist7!=null && !dist7.equals("")){
conn.st2.executeUpdate(query7);
counter++;
}
if(dist8!=null && !dist8.equals("")){
conn.st2.executeUpdate(query8);
counter++;
}
if(dist9!=null && !dist9.equals("")){
conn.st2.executeUpdate(query9);
counter++;
}
if(dist10!=null && !dist10.equals("")){
conn.st2.executeUpdate(query10);
counter++;
}
if(dist11!=null && !dist11.equals("")){
conn.st2.executeUpdate(query11);
counter++;
}
if(dist12!=null && !dist12.equals("")){
conn.st2.executeUpdate(query12);
counter++;
}
if(dist13!=null && !dist13.equals("")){
conn.st2.executeUpdate(query13);
counter++;
}
if(dist14!=null && !dist14.equals("")){
conn.st2.executeUpdate(query14);
counter++;
}
if(dist15!=null && !dist15.equals("")){
conn.st2.executeUpdate(query15);
counter++;
}
nextpage="add_partner2.jsp";
if(counter==1){
 session.setAttribute("dist_exist", "<font color=\"Green\">Partner Added Successfully.</font>");   
}
if(counter > 1){
 session.setAttribute("dist_exist", "<font color=\"Green\">Partners Added Successfully.</font>");   
}
}

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
            Logger.getLogger(addpart.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(addpart.class.getName()).log(Level.SEVERE, null, ex);
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
