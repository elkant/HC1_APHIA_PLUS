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
public class sessions_chooser extends HttpServlet {
String curriculum_id,current_sessions,topics_row,no_of_topics="",all_select_boxes;
  int count;
  HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
             session=request.getSession();
               curriculum_id=request.getParameter("cur"); 
               no_of_topics=request.getParameter("not").trim();
   System.out.println(curriculum_id);           
             //System.out.println(" County:"+ curriculum_id); 
               count=0;
               current_sessions="";
               all_select_boxes="";
               topics_row="";
               String districts="Select * from topics where curriculum_id='"+session.getAttribute("cur_curriculum")+"' ";
               
               dbConn conn=new dbConn();
               
               conn.rs=conn.st.executeQuery(districts);
               
               //set the no of topics to an array 
               if(no_of_topics!=null&&!no_of_topics.equals("")){
                   
             count=Integer.parseInt(no_of_topics);
               }
               
               
               //initialize all the core strings
               all_select_boxes="<tr>";
               current_sessions=current_sessions+"<tr>";
              topics_row=""; 
               while(conn.rs.next()){
                 
//            session.setAttribute("hc_curriculum", conn.rs.getString("curriculum_name"));
              //dynamically add districts to the string array
              
              topics_row=topics_row+"<option  title=\""+conn.rs.getString("topic_name")+"\" value=\""+conn.rs.getString("topic_id")+"\">"+conn.rs.getString("topic_name")+"</option>";
         
             
               }
                
               //create the table at this point and include the checboxes and the select boxes
               for(int a=1;a<=count;a++){
               current_sessions=current_sessions+"<td>S"+a+"</td>";  
               //set each row of checkbox with respective column and all topics
               all_select_boxes=all_select_boxes+"<td style=\"padding:2px;\"><Select required=\"true\" class=\"multiselect\"  name=\"select"+a+"\" style=\"width:150px;height:130px;\" multiple>"+topics_row+"</select>";
               
               }
               
               all_select_boxes=all_select_boxes+"</tr>";
               current_sessions=current_sessions+"</tr>";
              //  System.out.println("size:" +dist.size());
          
             // System.out.println("My sessions:"+current_sessions);
               
               
               PrintWriter out = response.getWriter();
                
                out.println("<html>");
                out.println("<head>");           
                out.println("</head>");
                out.println("<body>");
                out.println("" +current_sessions+all_select_boxes+"");
                out.println("</body>");
                out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(sessions_chooser.class.getName()).log(Level.SEVERE, null, ex);
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
