/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SCRIPTS;

import hc.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MANUEL
 */
public class addhclivedata extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
       
       
        try {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
   dbConn conn= new dbConn(); 
    
   
  // String loadhclive="select * from finalhclive ";
   //String loadhclive="select * from finalhclive where ( livehc_id>=5874 and livehc_id < 20000 )";
  // String loadhclive="select * from finalhclive where ( livehc_id>=20000 and livehc_id < 30000 )";
 //String loadhclive="select * from finalhclive where ( livehc_id>=44024 and livehc_id < 50000 )";
   
  // String loadhclive="select * from finalhclive where ( livehc_id>=39850 and livehc_id < 50000 )";
  // String loadhclive="select * from finalhclive where ( livehc_id>=66755 and livehc_id < 70000 )";
   
  // String loadhclive="select * from finalhclive where ( livehc_id>=70000 and livehc_id < 90000 )";
   String loadhclive="select * from finalhclive where ( livehc_id>=86860 )";
   
   conn.rs=conn.st.executeQuery(loadhclive);
   
   
   int rowcount=0;
   
   int numberofgroupsunadded=0;
   //livehc_id	FormNo	IP	District	Target	GroupName	Facil_fname	Facil_mname	Phone	Curriculum	Expected	CoFacilitator	AgeGroup	StartDate	EndDate	FName	MName	Sex	Age	S1	S2	S3	S4	S5	S6	S7	S8	S9	S10	SessionEndMonth	SessionY	RMth	ages	Present	Absent	Makeup	FxPresent	FxAbsent	FxMakeup	CompletionRate	Cty	Qtr	KePMS	TargetRegrouped

   
   
   int possibleduplicates=0;
   
  while(conn.rs.next()){
  rowcount++;
  System.out.println(rowcount+" ====");
   

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
  String distid="";

      //===============check whether all the districts have been added

      String checkdistrict = "select * from district where district_name LIKE '"+conn.rs.getString("District")+"'";

      conn.rs1 = conn.st1.executeQuery(checkdistrict);

      if (conn.rs1.next()) {

          distid = conn.rs1.getString("district_id");

      } else {
          System.out.println("DS This district hasnt been added yet");
      }
      
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

      
      
      //  2   target population
      
    
      
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //check whether a target population name is added if yes, get the target id

 String targetid="";
 
 String checktarget="select * from target_population where population_name LIKE '%"+conn.rs.getString("Target")+"%'";
  

 conn.rs1=conn.st1.executeQuery(checktarget);
 
 if(conn.rs1.next()){ 
     
    targetid=conn.rs1.getString("target_id");
     
 }
 else 
 {
     
 System.out.println("TP  Target "+conn.rs.getString("Target")+" doesnt exist");
 
 }
 
   //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

                    
                         //3. Implementing Partners
 
                                   
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
  //livehc_id	FormNo	IP	District	Target	GroupName	Facil_fname	Facil_mname	Phone	Curriculum	Expected	CoFacilitator	AgeGroup	StartDate	EndDate	FName	MName	Sex	Age	S1	S2	S3	S4	S5	S6	S7	S8	S9	S10	SessionEndMonth	SessionY	RMth	ages	Present	Absent	Makeup	FxPresent	FxAbsent	FxMakeup	CompletionRate	Cty	Qtr	KePMS	TargetRegrouped

 String partner_id="";
 
 
 String getpartnerid="select partner_id from partner where partner_name LIKE ?";
 
 conn.pst2=conn.conne.prepareStatement(getpartnerid);
 conn.pst2.setString(1, conn.rs.getString("IP"));
 
 conn.rs1=conn.pst2.executeQuery();
 
 if(conn.rs1.next()){
 
 partner_id=conn.rs1.getString(1);
 
 }
 else {
  System.out.println("IP  "+conn.rs.getString("IP")+" doesnt exist");
 
 
 }
 
 
 
 
 
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
 
 //4. Groups
 
 
 
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
 
 
 
 String group_id="";
 
 String checkgroup="select * from groups where group_name LIKE ?";
 
 
 
 conn.pst=conn.conne.prepareStatement(checkgroup);
   conn.pst.setString(1, "%"+conn.rs.getString("GroupName")+"%");
 conn.rs1=conn.pst.executeQuery();
   
 
 
 if(conn.rs1.next()){ 
    //get the group id  
     
   group_id=conn.rs1.getString("group_id");
   
   
 }
 else {


    group_id=uniqueid().toString().trim();
     
  numberofgroupsunadded++;   
  // groupid=conn.rs1.getString("target_id");
   System.out.println("Group "+conn.rs.getString("GroupName")+" NOT ADDED");
   
   String insertgrp="insert into groups(group_id,group_name,partner_id,target_pop_id,district_id) values (?,?,?,?,?)";
   
   conn.pst1=conn.conne.prepareStatement(insertgrp);
   conn.pst1.setString(1,group_id );
   conn.pst1.setString(2,conn.rs.getString("GroupName").toString());
   conn.pst1.setString(3, partner_id);
   conn.pst1.setString(4, targetid);
   conn.pst1.setString(5, distid);
  // conn.pst1.executeUpdate();
 
 
 }
 
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

 
 
 
 
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
                              //facilitator details.
 
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
 //now read the facilitator details
  //livehc_id	FormNo	IP	District	Target	GroupName	Facil_fname	Facil_mname	Phone	Curriculum	Expected	CoFacilitator	AgeGroup	StartDate	EndDate	FName	MName	Sex	Age	S1	S2	S3	S4	S5	S6	S7	S8	S9	S10	SessionEndMonth	SessionY	RMth	ages	Present	Absent	Makeup	FxPresent	FxAbsent	FxMakeup	CompletionRate	Cty	Qtr	KePMS	TargetRegrouped

 String facilitator_id="";
 
  String getfacilitator="select * from facilitator_details where phone = ? and (first_name LIKE ? || sur_name LIKE ? )  ";  
  
  
  String phone="0";
 if(conn.rs.getString("Phone")!=null){
  if(!conn.rs.getString("Phone").toString().equalsIgnoreCase("")){
  
  phone=conn.rs.getString("Phone").toString().toString().trim();
  
  }}
  
  if(1==1){ 

  
  
  conn.pst=conn.conne.prepareStatement(getfacilitator);
  conn.pst.setString(1, phone);
  conn.pst.setString(2, conn.rs.getString("Facil_fname").toString());
  conn.pst.setString(3, conn.rs.getString("Facil_mname").toString());
  
  conn.rs1=conn.pst.executeQuery();
  
 // if the facilitator exists 
  
  
 
  
  if(conn.rs1.next()){
  //get the facilitor id
      
      //check whether He teaches that group if not , append the current group id to his list
  facilitator_id=conn.rs1.getString("facilitator_id").toString().trim();
      String getfacilid="select * from facilitator_details where facilitator_id='"+conn.rs1.getString("facilitator_id") +"' and groups_id like '%"+group_id+"%'";
      
      
  // System.out.println(" Searching for this facil "+getfacilid);
            
            conn.rs2=conn.st2.executeQuery(getfacilid);
          
            
            if(!conn.rs2.next()){
            
                
              String upda="update facilitator_details set groups_id=CONCAT( groups_id,'"+group_id+","+"') where facilitator_id='"+conn.rs1.getString("facilitator_id")+"'";  
             
            //  System.out.println(" ^^ Updated Facilitaor===="+upda);
              
              conn.st3.executeUpdate(upda);
            
            }
            else {
            
            //  System.out.println(" XX Facilitator "+conn.rs.getString("Facil_fname")+"_"+conn.rs.getString("Facil_mname")+" ALREADY ADDED!!!");
            
            }
      
            
  
  }//end of checking if facilitaor exists
  
  //if facilitator isnt added, add them and then add the current group
  else {
      
      facilitator_id=uniqueid().trim();
      
      
  String insertfacilt="insert into facilitator_details (facilitator_id,first_name,sur_name,phone,partner_id,groups_id,middle_name) values (?,?,?,?,?,?,?)";
  
  conn.pst1=conn.conne.prepareStatement(insertfacilt);
  conn.pst1.setString(1, facilitator_id);
  conn.pst1.setString(2, conn.rs.getString("Facil_fname"));
  conn.pst1.setString(3, conn.rs.getString("Facil_mname"));
  conn.pst1.setString(4,phone);
  conn.pst1.setString(5, partner_id);
  conn.pst1.setString(6, group_id+",");
  conn.pst1.setString(7, "");
  conn.pst1.executeUpdate();
  
  
 System.out.println(">> Inserted Facilitator "+conn.rs.getString("Facil_fname").toString()+"_"+conn.rs.getString("Facil_mname").toString());
  
  
  
  
  
        }//end of insert faciitaor else
  
                }
  
 
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
 
                                //member details
  
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
  //livehc_id	FormNo	IP	District	Target	GroupName	Facil_fname	Facil_mname	Phone	Curriculum	Expected	CoFacilitator	AgeGroup	StartDate	EndDate	FName	MName	Sex	Age	S1	S2	S3	S4	S5	S6	S7	S8	S9	S10	SessionEndMonth	SessionY	RMth	ages	Present	Absent	Makeup	FxPresent	FxAbsent	FxMakeup	CompletionRate	Cty	Qtr	KePMS	TargetRegrouped
 
  String memberid="";
  String period="";
  if(1==1){ 
  int membersession=0;
 
  //======determine period
  
  
  
  if(conn.rs.getString("Qtr").toString().trim().equals("1. Jan-Mar")){
  
  period="2";
  
  }
     else   if(conn.rs.getString("Qtr").toString().trim().equals("2. Apr-Jun")){
  
  period="3";
  
  }
     else   if(conn.rs.getString("Qtr").toString().trim().equals("3. Jul-Sep")){
  period="4";
  
  }
     else   if(conn.rs.getString("Qtr").toString().trim().equals("4. Oct-Dec")){
  period="1";
  
  }
   else {
  
  period="0";
  
  
  System.out.println("No Month selected");
  
  }
  
   //for(int sess=19;sess<=29;sess++){
  //if(cells.get(sess).toString().equals("1")){
  
  //membersession++;    
  
  //}
  
      
  
  //}
  

  
   
     //check if a member exists
  
  String checkmem="select * from member_details where first_name LIKE ? and sur_name LIKE ?   and group_id like ? and month = ? and year = ? and age Like ? and sex like ?";
  
   conn.pst1=conn.conne.prepareStatement(checkmem);
   conn.pst1.setString(1, conn.rs.getString("FName").toString());
   conn.pst1.setString(2, conn.rs.getString("MName").toString());
   conn.pst1.setString(3, group_id);
   conn.pst1.setString(4, conn.rs.getString("SessionEndMonth").toString());
   conn.pst1.setString(5, conn.rs.getString("SessionY").toString());
   conn.pst1.setString(6, conn.rs.getString("Age").toString());
   conn.pst1.setString(7, conn.rs.getString("Sex").toString());
 
    conn.rs1=conn.pst1.executeQuery();
    if(1==2){
   if(!conn.rs1.next()){
   
 System.out.println(">>> MEMBER "+conn.rs.getString("FName").toString()+" "+conn.rs.getString("MName").toString()+" attended "+conn.rs.getString("Present").toString()+" sessions");
  
   
   
   
   String insertmember=" insert into member_details (member_id,first_name,mname,sur_name,age,sex,group_id,sessions_attended,year,period,month) values (?,?,?,?,?,?,?,?,?,?,?) ";
   
   
  conn.pst=conn.conne.prepareStatement(insertmember);
  
  memberid=uniqueid().trim();
  
  conn.pst.setString(1, memberid);
  conn.pst.setString(2,  conn.rs.getString("FName").toString());
  conn.pst.setString(3, "");//no middle name
  conn.pst.setString(4,  conn.rs.getString("MName").toString());
  conn.pst.setString(5,  conn.rs.getString("Age").toString());
  conn.pst.setString(6,  conn.rs.getString("Sex").toString());
  conn.pst.setString(7,  group_id);
  conn.pst.setString(8,  conn.rs.getString("Present").toString());//sessions attended
  conn.pst.setString(9,  conn.rs.getString("SessionY").toString());//year
  conn.pst.setString(10, period);//period
  conn.pst.setString(11, conn.rs.getString("SessionEndMonth").toString());//month
  
  

  
  conn.pst.executeUpdate();
  //now count the number of sessions attended
  
}//end of members if
    }
   if(conn.rs1.next()){
       
possibleduplicates++;
 System.out.println(possibleduplicates+"  ### MEMBER "+conn.rs.getString("FName").toString()+" "+conn.rs.getString("MName").toString()+" a possible duplicate");
  memberid=conn.rs1.getString("member_id");
   

     }


  }

  
 //now import data to the other tables
  
 
  //enter data into the register
  String sess[]={"S1","S2","S3","S4","S5","S6","S7","S8","S9","S10"};
  
  String markeddate=curtime().trim();
  
  
    //this is trhe form id
  String formid=uniqueid().trim();
  
  for(int s=0;s<sess.length;s++){
  String insertattendance=" INSERT INTO `register_attendance` "
+ "(`register_id`,`member_id`,`session_id`,`facilitator_id`,`image_path`,`end_date`,`availability`,`marked_date`,`form_id`,`submission_date`,`reviewer_name`) "
          + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
  
  
 conn.pst=conn.conne.prepareStatement(insertattendance);
  
  String registerid=uniqueid().trim();
  

  //this is the session number. 
  String sessno=conn.rs.getString(sess[s]);
  
  String path="";
  
  
  if(sessno.equals("1")){
  path="images/present.png";
  }
  else if(sessno.equals("2"))
  {
  path="images/absent.png";
  }
  else if (sessno.equals("3"))
  {
  
  path="images/makeup.png";
  
  }
  //if the symbol is either present, absent pr makeup, then insert data
  if(sessno!=null&&!sessno.equals("")){
  conn.pst.setString(1, registerid);
  conn.pst.setString(2, memberid);
  conn.pst.setString(3,  "no_session_id_");
  conn.pst.setString(4, facilitator_id);//no middle name
  conn.pst.setString(5, path);
  conn.pst.setString(6, conn.rs.getString("EndDate").toString());
  conn.pst.setString(7, sessno);
  conn.pst.setString(8, markeddate);
  conn.pst.setString(9, formid);
  conn.pst.setString(10, conn.rs.getString("EndDate").toString());
  conn.pst.setString(11, "no reviewer");
 
  
  conn.pst.executeUpdate();
   System.out.println("inserting register attendance");
  }
      }//end of sessions for loop
  
  
  
  
  //insert the sessions too
  
  String sessiontableid="";
  
  if(1==1){
  
      sessiontableid=uniqueid().trim();
      
  String addsession="INSERT INTO `session`(`session_id`,`date`,`duration`,`male_cds`,`female_cds`,`topic_id`,`group_id`,`method_id`,`end_date`,`marked_date`,`marking_status`,`year`,`period`,`month`)"
+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
     
  
   conn.pst=conn.conne.prepareStatement(addsession);
  
  conn.pst.setString(1, sessiontableid);
  conn.pst.setString(2,"session_date");
  conn.pst.setString(3,"60");
  conn.pst.setString(4,"0");//male cds
  conn.pst.setString(5,"0");//female cds
  conn.pst.setString(6,"_");//topicid
  conn.pst.setString(7,group_id);//groupid
  conn.pst.setString(8,"_");//methodid
  conn.pst.setString(9,conn.rs.getString("EndDate").toString());//
  conn.pst.setString(10,markeddate);//
  conn.pst.setString(11,"yes");//marking status
  conn.pst.setString(12,conn.rs.getString("SessionY").toString());//
  conn.pst.setString(13,period);//
  conn.pst.setString(14,conn.rs.getString("SessionEndMonth").toString());//
  
  
  conn.pst.executeUpdate(); 
  
  System.out.println("inserting sessions");
  
  }//end of sessions if
  
  
  
  
  
  //insert the forms too
  
  if(1==1){
  
  String insertform="INSERT INTO `forms`(`form_id`,`form_number`,`year`,`period`,`month`) "
+" VALUES (?,?,?,?,?)";
  
   conn.pst=conn.conne.prepareStatement(insertform);

  
  conn.pst.setString(1, formid);
  conn.pst.setString(2, conn.rs.getString("FormNo").toString());
  conn.pst.setString(3, conn.rs.getString("SessionY").toString());
  conn.pst.setString(4, period);
  conn.pst.setString(5, conn.rs.getString("SessionEndMonth").toString());
  conn.pst.executeUpdate();
  System.out.println("inserting forms");
  }//insert into forms
  
  
  //insert new topics too
  
  if(1==1){
  
  String insertnewtopics="INSERT INTO `new_topic` "
+ "(`new_topic_id`,`topic_id`,`expected_sessions`,`start_date`,`end_date`,`marking_status`,`facilitator_id`,`year`,`marking_date`,`year2`,`period`,`month`) "
+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
  
   conn.pst=conn.conne.prepareStatement(insertnewtopics);
String newtopicid=uniqueid().trim();
  
  conn.pst.setString(1, newtopicid);
  conn.pst.setString(2, "_");
  conn.pst.setString(3, conn.rs.getString("Expected").toString());
  conn.pst.setString(4, conn.rs.getString("StartDate").toString());
  conn.pst.setString(5, conn.rs.getString("EndDate").toString());
  conn.pst.setString(6, "yes");
  conn.pst.setString(7, facilitator_id);
  conn.pst.setString(8, conn.rs.getString("EndDate").toString());
  conn.pst.setString(9, markeddate);
  conn.pst.setString(10, conn.rs.getString("SessionY").toString());
  conn.pst.setString(11, period);
  conn.pst.setString(12, conn.rs.getString("SessionEndMonth").toString());
  
  conn.pst.executeUpdate();
  
   System.out.println("inserting newtopic");
  }//end of new topic for loop
  
                      }//the last while loop
   
   
  
  System.out.println("  ^^  Finished loop ");
    try {
   
    } finally {            
        out.close();
    }
}       catch (SQLException ex) {
            Logger.getLogger(addhclivedata.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    
    
    
      //====================random id functions================================ 

 public String uniqueid() {

        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int sec = cal.get(Calendar.SECOND);
        int milsec=cal.get(Calendar.MILLISECOND);
        
        
        return generateRandomNumber(800, 8000)+year+""+month+""+date+hour+min+sec+milsec;
    }
    
   
 
   public int generateRandomNumber(int start, int end ){
        Random random = new Random();
        long fraction = (long) ((end - start + 1 ) * random.nextDouble());
        return ((int)(fraction + start));
                                                        }
 
//==========================================================================
    
   
   
   
   public String curtime(){
   
   
    Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
            int date = cal.get(Calendar.DATE);
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int min = cal.get(Calendar.MINUTE);
            int sec = cal.get(Calendar.SECOND);
            int microsec = cal.get(Calendar.MILLISECOND);
            String yr, mnth, dater, hr, mn, sc, action = "";

            yr = Integer.toString(year);
            mnth = Integer.toString(month);
            dater = Integer.toString(date);
            hr = Integer.toString(hour);
            mn = Integer.toString(min);
            sc = Integer.toString(sec);
            String current_time = yr + "-" + mnth + "-" + dater + "-" + hr + ":" + mn + ":" + sc+":"+microsec;
           return current_time;
   
   }
    
}
