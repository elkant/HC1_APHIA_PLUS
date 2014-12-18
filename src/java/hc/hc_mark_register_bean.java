/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.Serializable;

/**
 *
 * @author SIXTYFOURBIT
 */
public class hc_mark_register_bean implements Serializable {
   
    String date,availability,reg_id,f_name,m_name,l_name,age, sex,ipath, member_id,session_id,facilitator_name,facilitator_phone;
    int   count, lesson_no;

    public String getAge() {
        return age;
    }

    public int getLesson_no() {
        return lesson_no;
    }

    public String getFacilitator_name() {
        return facilitator_name;
    }

    public void setFacilitator_name(String facilitator_name) {
        this.facilitator_name = facilitator_name;
    }

    public String getFacilitator_phone() {
        return facilitator_phone;
    }

    public void setFacilitator_phone(String facilitator_phone) {
        this.facilitator_phone = facilitator_phone;
    }

    public void setLesson_no(int lesson_no) {
        this.lesson_no = lesson_no;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getIpath() {
        return ipath;
    }

    public void setIpath(String ipath) {
        this.ipath = ipath;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getM_name() {
        return m_name;
    }

    public void setM_name(String m_name) {
        this.m_name = m_name;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getReg_id() {
        return reg_id;
    }

    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    
    
}
