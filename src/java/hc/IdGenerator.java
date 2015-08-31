/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author Geofrey Nyabuto
 */
public class IdGenerator {
//    CURRENT DATE
 Calendar cal = Calendar.getInstance();
int year=cal.get(Calendar.YEAR);
int month=cal.get(Calendar.MONTH)+1;
public int date=cal.get(Calendar.DATE);
int hour = cal.get(Calendar.HOUR_OF_DAY);
int min=cal.get(Calendar.MINUTE);
int sec=cal.get(Calendar.SECOND);
int micro=cal.get(Calendar.MILLISECOND);
String yr,mnth,dater,hr,mn,sc,action="";
String date2="";


                        //RANDOM NUMBER GENERATOR
  Random random = new Random();
 long fraction = (long) ((98725 - 219 ) * random.nextDouble());
 
    public String current_id(){
       
String full_date2=hour+""+min+""+sec+""+micro;
String db=Double.toString(fraction);
String tableID=(db+""+full_date2).replace(".", ""); 
 String id=year+""+month+""+date+""+hour+""+min+""+sec+""+micro;       
       return  tableID;
    }
    public String timestamp(){
       
String full_date2=hour+""+min+""+sec+""+micro;
String db=Double.toString(fraction);

 String id=year+"_"+month+"_"+date+"_"+hour+"_"+min+"_"+sec+"_"+micro;       
       return  id;
    }
    
    public String toDay(){
        if(date<10){
    date2="0"+date;
}
  if(date>=10){
    date2=""+date;
}
   String full_date=year+"-"+month+"-"+date2;     
   return full_date;
    }
}
