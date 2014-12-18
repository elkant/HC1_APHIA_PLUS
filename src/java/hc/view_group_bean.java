/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hc;

import java.io.Serializable;

/**
 *
 * @author Geofrey Nyabuto
 */
public class view_group_bean implements Serializable{
    private String group_name,partner_name,population_name;
private int count; 

    public String getPopulation_name() {
        return population_name;
    }

    public void setPopulation_name(String population_name) {
        this.population_name = population_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
