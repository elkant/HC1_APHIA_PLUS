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
 * @author SIXTYFOURBIT
 */
public class session_holder extends HttpServlet {

    String county, dist, target_pop, group, number_of_members;
    HttpSession session;
    String year, period, month = "";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            session = request.getSession();



System.out.println("@@session_holder java page");

            if (session.getAttribute("userid") == null) {
                response.sendRedirect("index.jsp");
            }
            number_of_members = "0";

            if (request.getParameter("number_of_members") != null) {
                number_of_members = request.getParameter("number_of_members");
            } else {
                number_of_members = "3";

            }

            //========THIS PART HAS CHANGED====================  
            if (request.getParameter("month")!=null) {

                month=request.getParameter("month");
                System.out.println("month is" + month);

                session.setAttribute("selected_month",month);

            } else {

                if (session.getAttribute("selected_month")!= null) {
                    month =session.getAttribute("selected_month").toString();
                }
            }



            if (request.getParameter("county") != null) {
                county = request.getParameter("county");
            } else {
                county = session.getAttribute("county_id").toString();

            }

            if (request.getParameter("district") != null) {
                dist = request.getParameter("district");
            } 
            else {
                dist = session.getAttribute("district_id").toString();
            }

            if (request.getParameter("target_pop") != null) {
                target_pop = request.getParameter("target_pop");
            } else {
                target_pop = session.getAttribute("target_id").toString();

            }


            if (request.getParameter("group_name") != null&&!request.getParameter("group_name").equals("") ) {
                group = request.getParameter("group_name");
                session.setAttribute("added_group_id", group);
            } else {

                group = session.getAttribute("added_group_id").toString();

            }

//session.setAttribute("year"

            if (request.getParameter("year") != null) {
                year = request.getParameter("year");
            } else {

                year = session.getAttribute("year").toString();

            }
            if (request.getParameter("period") != null) {
                period = request.getParameter("period");
            } else {
                period = session.getAttribute("period").toString();
            }

            session.setAttribute("year", year);

            session.setAttribute("period", period);

            dbConn conn = new dbConn();
         
            
            
            
            
            
            
            
            
            //Get the names of the various chosen places

            conn.rs = conn.st.executeQuery("select * from county where county_id='" + county + "'");
            conn.rs.next();
            session.setAttribute("cc_county", conn.rs.getString("county_name"));
            // district name
            conn.rs = conn.st.executeQuery("select * from district where district_id='" + dist + "'");
            conn.rs.next();
            session.setAttribute("cc_district", conn.rs.getString("district_name"));
            //pop name
            conn.rs = conn.st.executeQuery("select * from target_population where target_id='" + target_pop + "'");
            if(conn.rs.next()){
            session.setAttribute("cc_target_pop", conn.rs.getString("population_name"));
            }
          
            
            //group name
            String qr1="select * from groups where group_id='" + group + "' || group_name='" + group + "'";
            
            System.out.println("************* THIS QUERY HAD AN ERROR"+qr1);
            conn.rs = conn.st.executeQuery(qr1);
            if(conn.rs.next()){
            session.setAttribute("cc_group_name", conn.rs.getString("group_name"));
            }

            //pass the ids too

            session.setAttribute("c_county", county);
            session.setAttribute("c_district", dist);
            session.setAttribute("c_target_pop", target_pop);
            session.setAttribute("c_group_name", group);
System.out.println("target population name is "+session.getAttribute("cc_target_pop"));

            int noofrows = 0;

//========CHECK OF THE TARGET POP IS GENDER SENSITIVE========
            
            String gen=" <option value=\"\" selected>Choose Sex</option>";
            
            if(target_pop.equals("1")||target_pop.equals("13")||session.getAttribute("cc_target_pop").equals("FEMALES (15 to 24)")||session.getAttribute("cc_target_pop").equals("FSW")||session.getAttribute("cc_target_pop").equals("FSW (SISTER TO SISTER)")){
            
            
                        gen+= " <option value=\"female\">Female</option>";
            }
            else if(target_pop.equals("2")||session.getAttribute("cc_target_pop").equals("MSM (SAFE IN THE CITY)")||session.getAttribute("cc_target_pop").equals("MSM")){
            
            gen+= " <option value=\"male\">Male</option>";
            
            }
            else{
                
            gen+= " <option value=\"male\">Male</option>";
            gen+= " <option value=\"female\">Female</option>";
            
            }
            
            
            
            String createdtable = "<tr><th>No.</th><th><font color=\"RED\">*</font> First name</th><th> Middle name</th><th><font color=\"RED\">*</font> Last name</th><th><font color=\"RED\">*</font> Gender</th><th><font color=\"RED\">*</font> Age</th></tr>";

            if (!request.getParameter("number_of_members").equals("")) {
                noofrows = Integer.parseInt(request.getParameter("number_of_members"));
            }
            for (int a = 1; a <= noofrows; a++) {

                createdtable += "<tr><td>" + a + "</td>"
                        + "<td class=\"align_button_left\"> <input type=\"text\" name=\"fname" + a + "\"  placeholder=\"First Name\" class=\"textbox1\" id=\"fname" + a + "\"/></td>"
                        + "<td class=\"align_button_left\"><input type=\"text\" name=\"mname" + a + "\"  placeholder=\"Middle Name\" class=\"textbox1\" id=\"mname" + a + "\"/></td>"
                        + "<td class=\"align_button_left\"><input type=\"text\" name=\"lname" + a + "\"   placeholder=\"Last Name\" class=\"textbox1\" id=\"lname" + a + "\"/></td>"
                        + "<td class=\"align_button_left\"><select name=\"sex" + a + "\" class=\"textbox10\" id=\"sex" + a + "\" >"
                        + gen                       
                        + "</select></td>"
                        + "<td class=\"align_button_left\"><input type=\"text\" onkeypress=\"return numbers(event)\" maxlength=\"2\" name=\"age" + a + "\"   placeholder=\"Age\" id=\"age" + a + "\" class=\"textbox1\" pattern=\"[0-9]{1,2}\" autofocus title=\"The field must have atleast 1 and atmost 2 Integers only\"/></td>"
                        + "</tr>";

            }


            createdtable += " "
                    + "<tr><td></td></tr>"
                    + " <tr align=\"center\" > <td class=\"align_button_left\" colspan=\"5\">"
                    + "<input type=\"hidden\" value=\"" + number_of_members + "\" name=\"noofrows\" id=\"noofrows\">"
                    + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"reset\" name=\"submit\" value=\"Clear\" style=\"width:100px;height:35px;background-color:orange;\"></td>"
                    + "<td>&nbsp;<input type=\"submit\" onmouseover=\"vali();\" name=\"submit\" value=\"Save and Continue\" style=\"width:130px;height:35px;background-color:orange;\"/></td></tr> ";

            session.setAttribute("memberscreatedtable", createdtable);

            response.sendRedirect("add_members.jsp");


        } catch (SQLException ex) {
            Logger.getLogger(session_holder.class.getName()).log(Level.SEVERE, null, ex);
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
