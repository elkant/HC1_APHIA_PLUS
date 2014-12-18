/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Geofrey Nyabuto
 */
public class overall_session extends HttpServlet {
HttpSession session;
String src,year,period,nextpage;
String county_id, partner_id,district_id,group_id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      session=request.getSession();
      src=year=period="";
      src=request.getParameter("src");
      year=request.getParameter("year");
      period=request.getParameter("period");
      
      session.setAttribute("year", year);
      session.setAttribute("period", period);
      if(src.equals("county_pie_chart2.jsp")){
county_id=request.getParameter("county_id");
session.setAttribute("county_id", county_id);
System.out.println("The county id is  : "+county_id);
      }
      if(src.equals("partner_pie_chart2.jsp")){
          partner_id=request.getParameter("partner_id");
           district_id=request.getParameter("district");
           session.setAttribute("partner_id", partner_id);
           session.setAttribute("district_id", district_id);
      }
      if(src.equals("kepms_barchart_report.jsp")){
         period=year="";
          String periods[]=request.getParameterValues("period");
          if(periods!=null){
            for (int j=0;j<periods.length;j++) { 
            if(periods[j]!=null && !periods[j].equals("")){
                period+=periods[j]+",";
              
                        }}}
          year=request.getParameter("year");
           session.setAttribute("period", period);
           session.setAttribute("year", year);
                     
                     
      }
      
      if(src.equals("group_report")){
          group_id=request.getParameter("group_name");
           session.setAttribute("group_report_id", group_id);
      }
      if(src.equals("excel_groups_completion")){
          period=year="";
          String periods[]=request.getParameterValues("period");
          if(periods!=null){
            for (int j=0;j<periods.length;j++) { 
            if(periods[j]!=null && !periods[j].equals("")){
                period+=periods[j]+",";
              
                        }}}
          year=request.getParameter("year");
           session.setAttribute("period", period);
           session.setAttribute("year", year);
      }
      if(src.equals("target_pie2.jsp")){
          period=year="";
          String target=request.getParameter("target");
          String periods[]=request.getParameterValues("period");
          String counties[]=request.getParameterValues("county");
          String partners[]=request.getParameterValues("partner");
          partner_id=period=county_id="";
          if(periods!=null){
            for (int j=0;j<periods.length;j++) { 
            if(periods[j]!=null && !periods[j].equals("")){
                period+=periods[j]+",";
              
                        }}}
  
          if(counties!=null){
            for (int j=0;j<counties.length;j++) { 
            if(counties[j]!=null && !counties[j].equals("")){
                county_id+=counties[j]+",";
              
                        }}}
    
          if(partners!=null){
            for (int j=0;j<partners.length;j++) { 
            if(partners[j]!=null && !partners[j].equals("")){
                partner_id+=partners[j]+",";
              
                        }}}
          year=request.getParameter("year");
           session.setAttribute("period", period);
           session.setAttribute("counties", county_id);
           session.setAttribute("partners", partner_id);
           session.setAttribute("year", year);
           session.setAttribute("target", target);
      }
      if(src.equals("county_bar2.jsp")){
          period=year="";
          String target=request.getParameter("target");
          String periods[]=request.getParameterValues("period");
          partner_id=period=county_id="";
          if(periods!=null){
            for (int j=0;j<periods.length;j++) { 
            if(periods[j]!=null && !periods[j].equals("")){
                period+=periods[j]+",";
              
                        }}}
          year=request.getParameter("year");
           session.setAttribute("period", period);
           session.setAttribute("counties", county_id);
           session.setAttribute("partners", partner_id);
           session.setAttribute("year", year);
           session.setAttribute("target", target);
      }
      
      
      response.sendRedirect(src);
      System.out.println(" year  : "+year+"  period   :  "+period+"   src  :  "+src);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
