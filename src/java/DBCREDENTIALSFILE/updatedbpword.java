/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBCREDENTIALSFILE;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hc.dbConn;

/**
 *
 * @author SIXTYFOURBIT
 */
public class updatedbpword extends HttpServlet {

   String host,dbase,user,password; 
  static   String dbsetup,masterdbsetup,tempdb;
 String url,dbconnpath;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       if(request.getParameter("hostname")==null){
       
           host="localhost:3306";
           
       }else{
       
        host=request.getParameter("hostname");
        
       }
        
        
        if(request.getParameter("database")==null){
       dbase="hc_aphiaplus";
        }
        else{
         dbase=request.getParameter("database");
        }
        if(request.getParameter("user")==null){
        user="root";
        }
        else{
        user=request.getParameter("user");
        }
          if(request.getParameter("password")==null){
          
          password="";
          }else{
        password=request.getParameter("password");
          }
      
//CREATE A PATH IN THE COMPUTER FOR STORING TEXT FILES
                            
    String allpath = getServletContext().getRealPath("/dbase.txt");
        String mydrive = allpath.substring(0, 1);
        //dbconnpath=mydrive+":\\MNHC_SYSTEM_APHIA_PLUS\\"; 
      dbconnpath=mydrive+":\\APHIAPLUS\\HC_SYSTEM\\HCDBCONNECTION\\DO_NOT_DELETE\\_\\_\\."; 
       
      //create a directory
      
      // new File(dbconnpath).mkdir();
     new File(dbconnpath).mkdirs();
        
        
        

    dbsetup =dbconnpath+"\\dbconnection.txt";
    masterdbsetup =dbconnpath+"\\masterdbconnection.txt";
    tempdb =dbconnpath+"\\tempdbconnection.txt";
        
    //dbsetup=ctx.getRealPath("/dbase.txt");
        
       
        
try {
// System.out.println("===============================context "+getServletContext().getRealPath("/dbsettings.txt"));

 //dbsetup = getClass().getResource("dbase.txt").getFile();
      
       
FileWriter outFile = new FileWriter(dbsetup ,false);
PrintWriter out = new PrintWriter(outFile);
out.print(host+"\n"+dbase+"\n"+user+"\n"+password.trim());
out.close();
String masterdb="masterhc";


FileWriter outFile1 = new FileWriter(masterdbsetup ,false);
PrintWriter out1 = new PrintWriter(outFile1);
out1.print(host+"\n"+masterdb+"\n"+user+"\n"+password.trim());
out1.close();


//create temp db

String tempdbname="temphc";

FileWriter outFile2 = new FileWriter(tempdb ,false);
PrintWriter out2 = new PrintWriter(outFile2);
out2.print(host+"\n"+tempdbname+"\n"+user+"\n"+password.trim());
out2.close();




   } catch (IOException e) {
    
    
}
   
   
   
//   System.out.println("Number of lines:========="+getLineCount(dbsetup));
   
   getLineCount(dbsetup);
   
   System.out.println("MASTER DATABASE SETTINGS");
   getLineCount1(masterdbsetup);
   
   System.out.println("TEMP DATABASE SETTINGS");
   getLineCount2(tempdb);
   
        response.setContentType("text/html;charset=UTF-8");
        
        
        
        
response.sendRedirect("index.jsp");
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

 public  void getLineCount (String filename) throws FileNotFoundException, IOException
    {
        
        //URL url3= getClass().getResource("/db.txt");
File file = new File(dbsetup);
        
 FileInputStream fstream = new FileInputStream(file);
  // Get the object of DataInputStream
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  String strLine;
  //Read File Line By Line
  while ((strLine = br.readLine()) != null)   {
  // Print the content on the console
  System.out.println ("=="+strLine);
  }
  //Close the input stream
  in.close();
           
    }
 
 //=======================================================================
  public  void getLineCount1 (String filename) throws FileNotFoundException, IOException
    {
        
        //URL url3= getClass().getResource("/db.txt");
File file = new File(masterdbsetup);
        
 FileInputStream fstream = new FileInputStream(file);
  // Get the object of DataInputStream
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  String strLine;
  //Read File Line By Line
  while ((strLine = br.readLine()) != null)   {
  // Print the content on the console
  System.out.println ("=="+strLine);
  }
  //Close the input stream
  in.close();
           
    }
 
    
 //=======================================================================
  public  void getLineCount2 (String filename) throws FileNotFoundException, IOException
    {
        
 
File file = new File(tempdb);
        
 FileInputStream fstream = new FileInputStream(file);
 
  DataInputStream in = new DataInputStream(fstream);
  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  String strLine;
  //Read File Line By Line
  while ((strLine = br.readLine()) != null)   {
  // Print the content on the console
  System.out.println ("=="+strLine);
  }
  //Close the input stream
  in.close();
           
    }
 
    


 
 
 
 
}

