/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

/**
 *
 * @author Geofrey Nyabuto
 */
public class kepms_method {
 String AB,target_id,quarter,sex,marps,workplace;
 int AB_Q1_F,AB_Q1_M,AB_Q2_F,AB_Q2_M;
 int marps_q1_m,marps_q1_f,marps_q2_m,marps_q2_f,workplace_q1_m,workplace_q2_m,workplace_q1_f,workplace_q2_f;
 
  public String get_Students_all(String AB, String target_id, String quarter, String sex, String marps, String workplace) {
       AB_Q1_F=AB_Q1_M=AB_Q2_F=AB_Q2_M=0;
 marps_q1_m=marps_q1_f=marps_q2_m=marps_q2_f=workplace_q1_m=workplace_q2_m=workplace_q1_f=workplace_q2_f=0;
      
      
      if(AB.contains(target_id) && quarter.equals("3") && sex.equals("FEMALE")){
               AB_Q1_F++;
               } 
              if(AB.contains(target_id) && quarter.equals("3") && sex.equals("MALE")){
               AB_Q1_M++;
               }
              if(AB.contains(target_id) && quarter.equals("4") && sex.equals("FEMALE")){
               AB_Q2_F++;
               } 
              if(AB.contains(target_id) && quarter.equals("4") && sex.equals("MALE")){
               AB_Q2_M++;
               }
              
              
              
              if(marps.contains(target_id) && quarter.equals("3") && sex.equals("MALE")){
               marps_q1_m++;
               }
              if(marps.contains(target_id) && quarter.equals("3") && sex.equals("FEMALE")){
               marps_q1_f++;
               }
              if(marps.contains(target_id) && quarter.equals("4") && sex.equals("MALE")){
               marps_q2_m++;
               }
              if(marps.contains(target_id) && quarter.equals("4") && sex.equals("FEMALE")){
               marps_q2_f++;
               }
               
              
              
              if(workplace.contains(target_id) && quarter.equals("3") && sex.equals("MALE")){
               workplace_q1_m++;
               }
              if(workplace.contains(target_id) && quarter.equals("3") && sex.equals("FEMALE")){
               workplace_q1_f++;
               }
              if(workplace.contains(target_id) && quarter.equals("4") && sex.equals("MALE")){
               workplace_q2_m++;
               }
              if(workplace.contains(target_id) && quarter.equals("4") && sex.equals("FEMALE")){
               workplace_q2_f++;
               }
      
   String aller=AB_Q1_F+","+AB_Q1_M+","+AB_Q2_F+","+AB_Q2_M+","+marps_q1_m+","+marps_q1_f+","+marps_q2_m+","+marps_q2_f+","+workplace_q1_m+","+workplace_q2_m+","+workplace_q1_f+","+workplace_q2_f;   
      
      return aller;
      
      
  } 
    
    
}


// String all_details=  kpm.get_Students_all(AB, target_id, quarter, sex, marps, workplace);