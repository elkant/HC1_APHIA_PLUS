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

/**
 *
 * @author Geofrey Nyabuto
 */
public class forms_calculator extends HttpServlet {
int nos=0;
String all_inputs="";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            all_inputs="<font size=\"03px\">Enter form serial number.</font> ";
            System.out.println("here it is");
            
            if(!request.getParameter("nos").trim().equals("")){
            nos=Integer.parseInt(request.getParameter("nos").toString());
            }
            System.out.println("number is :"+nos);
            for(int i=0;i<nos;i++) {
           all_inputs=all_inputs+"<input type=\"text\" autofocus title=\"enter serial number of the form here\" name=\"number"+i+"\" value=\"\" pattern=\"[0-9]{1,7}\" onkeypress=\"return numbers(event)\" maxlength=\"7\" class=\"longinput\" style=\"background:white;border-color:red; width:90px;height:35px;\" placeholder=\"e.g  3214"+i+"\" pattern=\"[0-9]{1,10}\"  required> ";     
   
            }       
                    
                    
                    
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet forms_calculator</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+all_inputs+"</h1>");
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
