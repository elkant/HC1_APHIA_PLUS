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
public class add_partner extends HttpServlet {
HttpSession session;
int inserted,found,exist1,exist2;
 String insert,already_exist,partner,query00,nextpage="";
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
dbConn conn=new dbConn();
     session=request.getSession();
     if (session.getAttribute("userid") == null) {
        nextpage="index.jsp";
    } 
//receive parameters

//district_id=session.getAttribute("district_id").toString();
partner=request.getParameter("dist1");
exist1=exist2=inserted=found=0;

insert=already_exist="";
for(int i=1;i<=15;i++){
 partner = request.getParameter("dist"+i);
 System.out.println("current partner issss    : "+partner);
if(!partner.equals("")){
 partner=partner.toUpperCase();
 String partner_checker1="select COUNT(partner_id) from partner where partner_name='"+partner+"'" ;
  conn.rs=conn.st.executeQuery(partner_checker1);
   if(conn.rs.next()==true) {
       exist1=conn.rs.getInt(1);
   }
   if(exist1>0) {
       found++;
  already_exist+=partner+",";
   }
  if(exist1==0) {
      partner=partner.toUpperCase();
      query00="insert into partner set partner_name='"+partner+"'";
    inserted+= conn.st.executeUpdate(query00);
  insert+=partner+",";
  }
 exist1=0; 
}
}
if(inserted>0){
  session.setAttribute("dist_exist", "<font color=\"Green\">"+insert+" Added Successfully.</font>");   
}
if(found>0){
  session.setAttribute("dist_exist2", "<font color=\"red\">"+already_exist+" Not added. Already exist in the system</font>");   
}
      
 nextpage="add_partner2.jsp";
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
            Logger.getLogger(add_partner.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_partner.class.getName()).log(Level.SEVERE, null, ex);
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

