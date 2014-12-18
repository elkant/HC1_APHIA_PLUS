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
public class add_target_population extends HttpServlet {
String tg1,tg2,tg3,tg4,tg5;
String age1[],age2[],age3[],age4[],age5[];
String query1,query2,query3,query4,query5;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
dbConn conn= new dbConn();
     session=request.getSession();
     if (session.getAttribute("userid") == null) {
        response.sendRedirect("index.jsp");
    } 
age1=request.getParameterValues("age1");
age2=request.getParameterValues("age2");
age3=request.getParameterValues("age3");
age4=request.getParameterValues("age4");
age5=request.getParameterValues("age5");


String age1val="",age2val="",age3val="",age4val="",age5val="";
//get the length of your array
int arr1length=0;
if(age1!=null){
arr1length=age1.length;

for(int a=0;a<age1.length;a++){

age1val+=age1[a];
if(a+1<arr1length){
age1val+=",";
}

}//end of forloop
}

//===========AGE2=============

//get the length of your array
int arr2length=0;
if(age2!=null){
arr2length=age2.length;

for(int a=0;a<age2.length;a++){

age2val+=age2[a];
if(a+1<arr2length){
age2val+=",";
}

}//end of forloop
}

//==========AGE3=================




//get the length of your array
int arr3length=0;
if(age3!=null){
        arr3length=age3.length;

for(int a=0;a<age3.length;a++){

age3val+=age3[a];
if(a+1<arr3length){
age3val+=",";
}

}//end of forloop//get the length of your array
}


//=============age 4======
int arr4length=0;

if(age4!=null){
arr4length=age4.length;

for(int a=0;a<age4.length;a++){

age1val+=age4[a];
if(a+1<arr4length){
age4val+=",";
}

}//end of forloop
}

//===========age5============

//get the length of your array
int arr5length=0;
if(age5!=null){
arr5length=age5.length;



for(int a=0;a<age5.length;a++){

age5val+=age5[a];
if(a+1<arr5length){
age5val+=",";
}

}//end of forloop
}



tg1=request.getParameter("tg1");
tg2=request.getParameter("tg2");
tg3=request.getParameter("tg3");
tg4=request.getParameter("tg4");
tg5=request.getParameter("tg5");
tg1=tg1.toUpperCase();
tg2=tg2.toUpperCase();
tg3=tg4.toUpperCase();
tg4=tg4.toUpperCase();
query1="INSERT into target_population SET population_name='"+tg1+"', age_group_id='"+age1val+"',partner_id='"+session.getAttribute("partner_id")+"'";
query2="INSERT into target_population SET population_name='"+tg2+"', age_group_id='"+age2val+"',partner_id='"+session.getAttribute("partner_id")+"'";
query3="INSERT into target_population SET population_name='"+tg3+"', age_group_id='"+age3val+"',partner_id='"+session.getAttribute("partner_id")+"'";
query4="INSERT into target_population SET population_name='"+tg4+"', age_group_id='"+age4val+"',partner_id='"+session.getAttribute("partner_id")+"'";
query5="INSERT into target_population SET population_name='"+tg5+"', age_group_id='"+age5val+"',partner_id='"+session.getAttribute("partner_id")+"'";
System.out.println("Age group is :"+age1val);
System.out.println("Target population is :"+tg1);
System.out.println("Partner id is :"+session.getAttribute("partner_id"));
if(!age1val.equals("")){
conn.st.executeUpdate(query1);
}
if(!age2val.equals("")){
conn.st.executeUpdate(query2);
}
if(!age3val.equals("")){
conn.st.executeUpdate(query3);
}
if(!age4val.equals("")){
conn.st.executeUpdate(query4);
}
if(!age5val.equals("")){
conn.st.executeUpdate(query5);
}
session.setAttribute("target_pop", "<font color=\"green\">Target Population Added Successfully</font>");
response.sendRedirect("add_target_population.jsp");
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
            Logger.getLogger(add_target_population.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(add_target_population.class.getName()).log(Level.SEVERE, null, ex);
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
