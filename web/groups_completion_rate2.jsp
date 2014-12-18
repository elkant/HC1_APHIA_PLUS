<%-- 
    Document   : groups_completion_rate2
    Created on : Oct 1, 2013, 12:02:00 PM
    Author     : Geofrey Nyabuto
--%>
<%@page import="hc.dbConn"%>
<%@page import="java.util.Calendar"%>
<%@page import="org.apache.poi.hssf.util.CellRangeAddress"%>
<%@page import="org.apache.poi.hssf.usermodel.*" %><%@page import="java.io.*" %>
<%
int no_of_sessions=0,current_sessions=0;
String group_name="";
 int completed=0,partial=0,incomplete=0, total_sessions=0,sessions_attended=0,counter=0;
 double all_per=0, partial_per=0,incomplete_par=0,all=0,attended_per=0;
 String path,path2;
 String partner_name="",target_name="";
 
 Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);

String dates=year+"-"+month+"-"+date+"-"+hour+"-"+min+"-"+sec;
 String name="" ;          
 String start_date="";
String end_date ="" ;                 
            
            
            session=request.getSession();
            dbConn conn=new dbConn();
//            ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
             HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet();
  HSSFRow rw1=shet1.createRow(1);
  HSSFCell cell;
  cell=rw1.createCell(4);
  cell.setCellValue("Overall Groups Attendance Report");
//  Merge the cells
  shet1.addMergedRegion(new CellRangeAddress(1,1,4,12));
            
   HSSFRow rw4=shet1.createRow(3);
 rw4.createCell(1).setCellValue("Number");
  rw4.createCell(2).setCellValue("Partner Name");
   rw4.createCell(3).setCellValue("Target Population");
 rw4.createCell(4).setCellValue("Group Name");
rw4.createCell(5).setCellValue("Facilitator");
 rw4.createCell(6).setCellValue("# of Peers");
 rw4.createCell(7).setCellValue("End Date");
  rw4.createCell(8).setCellValue("Start Date");
 rw4.createCell(9).setCellValue("Attended All Sessions");
 rw4.createCell(10).setCellValue("over 50% attendance");  
 rw4.createCell(11).setCellValue("less than 50% attendance"); 
 rw4.createCell(12).setCellValue("Overall Attendance");             
 int i=4;
            String groups_selector="select * from groups";
            conn.rs=conn.st.executeQuery(groups_selector);
            while(conn.rs.next()){
                group_name=conn.rs.getString("group_name");
//                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                ^^^^^^^^^^^^^^^^^Get total sessions per target population^^^^^^^^^^^^^^^^^^^^^^^
                String curriculum_selector="select * from curriculum where target_id='"+conn.rs.getString("target_pop_id") +"'";
                conn.rs0=conn.st0.executeQuery(curriculum_selector);
                conn.rs0.next();
                no_of_sessions=Integer.parseInt( conn.rs0.getString("no_of_lessons").toString());
//                ^^^^^^^^^^^^^^^GET PARTNER NAME^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
       String partner_selector="select * from partners where partner_id='"+conn.rs.getString("partner_id") +"'";
                conn.rs0=conn.st0.executeQuery(partner_selector);
                conn.rs0.next();
                partner_name=conn.rs0.getString("partner_name");
//         ^^^^^^^^^^TARGET NAME^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^       
        String target_selector="select * from target_population where target_id='"+conn.rs.getString("target_pop_id") +"'";
                conn.rs0=conn.st0.executeQuery(target_selector);
                conn.rs0.next();
                target_name=conn.rs0.getString("population_name");
//                ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//                ^^^^^^^^^^^^^^^^^^^^^Get Each sessions attended for every member^^^^^^^^^^^^^^^^^^^^^^^
             String participant_selector="select * from member_details where group_id='"+conn.rs.getString("group_id") +"'"; 
             conn.rs0=conn.st0.executeQuery(participant_selector);
             while(conn.rs0.next()){
              counter++;
            current_sessions=Integer.parseInt( conn.rs0.getString("sessions_attended").toString());
              sessions_attended=sessions_attended+ current_sessions;
      if(current_sessions==no_of_sessions){
          completed++;
      }
      if(current_sessions>=(no_of_sessions/2) && current_sessions<no_of_sessions){
          partial++;
      }
      if(current_sessions<(no_of_sessions/2) && no_of_sessions>=0){
         incomplete++;
      }     
                 
        }
       if(counter*no_of_sessions==0){
         session.setAttribute("saved_path", "Failed to generate the excell sheet: Divide by Zero Error");
        
       } 
       else{
            attended_per=(sessions_attended*100)/(counter*no_of_sessions); 
            all=completed+partial+incomplete;
            all_per=(completed*100)/all;
partial_per=(partial*100)/all;
incomplete_par=(incomplete*100)/all;
String aller=String.format("%.0f", all_per);
String aller_partial=String.format("%.0f", partial_per);
String aller_incomplete=String.format("%.0f", incomplete_par);
String attended=String.format("%.0f", attended_per);

//try
System.out.println("Group name "+group_name);
System.out.println("percentage completed "+aller);
System.out.println("partially completed "+aller_partial);
System.out.println("Percentage Incomplete "+aller_incomplete);
System.out.println("Overall Group Attendance "+attended);
System.out.println("");
System.out.println("");
String member_det_selector="select * from member_details where group_id='"+conn.rs.getString("group_id") +"'";
conn.rs1=conn.st1.executeQuery(member_det_selector);
conn.rs1.next();
String member_id=conn.rs1.getString("member_id");
String register_selector="select * from register_attendance where member_id='"+member_id+"'";
conn.rs2=conn.st2.executeQuery(register_selector);
while(conn.rs2.next()){
String facilitator_id=conn.rs2.getString("facilitator_id");

String facilitator_selector="select * from facilitator_details where facilitator_id='"+facilitator_id+"'";
conn.rs3=conn.st3.executeQuery(facilitator_selector);
conn.rs3.next();
String fname=conn.rs3.getString("first_name");
String sname=conn.rs3.getString("sur_name");
 name=fname+" "+sname;

String date_selector="select * from new_topic where facilitator_id='"+facilitator_id+"'";
conn.rs3=conn.st3.executeQuery(date_selector);
conn.rs3.next();
String start_date1=conn.rs3.getString("start_date");
String[] dater1=start_date1.split("/");
String mn1=dater1[0];
String dy1=dater1[1];
String yr1=dater1[2];
start_date=dy1+" - "+mn1+" - "+yr1;
String end_date1=conn.rs3.getString("end_date");
String[] dater2=end_date1.split("/");
String mn2=dater2[0];
String dy2=dater2[1];
String yr2=dater2[2];
end_date=dy2+" - "+mn2+" - "+yr2;}
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//^^^^^^^^^^^^^^^OUTPUT TO EXCELL^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

 HSSFRow rwi=shet1.createRow(i);       
   rwi.createCell(1).setCellValue(i-3);
 rwi.createCell(2).setCellValue(partner_name);
 rwi.createCell(3).setCellValue(target_name);
 rwi.createCell(4).setCellValue(group_name);
 rwi.createCell(5).setCellValue(name);
  rwi.createCell(6).setCellValue(counter);
 rwi.createCell(7).setCellValue(start_date); 
 rwi.createCell(8).setCellValue(end_date);
rwi.createCell(9).setCellValue(aller+"%");
 rwi.createCell(10).setCellValue(aller_partial+"%");
  rwi.createCell(11).setCellValue(aller_incomplete+"%");
 rwi.createCell(12).setCellValue(attended+"%");



i++;





completed=partial=incomplete=total_sessions=0;
all_per=partial_per=incomplete_par=all=0;
attended_per=0;
sessions_attended=counter=0;

            }
            }
// write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=testxls.xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
response.sendRedirect("index.jsp");
%>