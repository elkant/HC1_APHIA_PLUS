/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

/**
 *
 * @author Geofrey Nyabuto
 */
public class view_participant_bean {
 private String fname,mname,lname,age,sex,group_name,sessions_attended;
 private int count;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getSessions_attended() {
        return sessions_attended;
    }

    public void setSessions_attended(String sessions_attended) {
        this.sessions_attended = sessions_attended;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
 
}
