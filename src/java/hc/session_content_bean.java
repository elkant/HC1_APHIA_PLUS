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
public class session_content_bean implements Serializable {
    
    String time,method,mcondoms,fcondoms,date,session_id,topic_id;

    public int getSess_count() {
        return sess_count;
    }

    public void setSess_count(int sess_count) {
        this.sess_count = sess_count;
    }

    int sess_count;
    public String getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(String topic_id) {
        this.topic_id = topic_id;
    }
    
    int session_count;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFcondoms() {
        return fcondoms;
    }

    public void setFcondoms(String fcondoms) {
        this.fcondoms = fcondoms;
    }

    public String getMcondoms() {
        return mcondoms;
    }

    public void setMcondoms(String mcondoms) {
        this.mcondoms = mcondoms;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getSession_count() {
        return session_count;
    }

    public void setSession_count(int session_count) {
        this.session_count = session_count;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
}
