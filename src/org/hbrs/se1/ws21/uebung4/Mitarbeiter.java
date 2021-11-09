package org.hbrs.se1.ws21.uebung4;

import java.util.HashMap;
import java.util.Map;

/*
* Klasse zur verwaltung von Mitarbeiter objekten
 */
public class Mitarbeiter {

    private Integer id;
    private HashMap<String,Integer> expertise;
    private boolean verfuegbar;
    private String vorname;
    private String lastname;
    private String role;
    private String abteilung;


    public Mitarbeiter() {
    }

    public void setExpertise(HashMap<String,Integer> exp) {
       expertise = exp;

    }
    public String getExpertise() {
        String expertisen = "";
        for(Map.Entry<String,Integer> expertise: expertise.entrySet()){
            expertisen += expertise.getKey() + ":" + expertise.getValue() + "|";
        }
        return expertisen;
    }

    public void setRole(String r){
        role = r;
    }
    public String getRole(){
        return role;
    }

    public String getVorname() {
        return vorname;
    }
    public void setVorname(String vn){
        vorname = vn;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String ln){
        lastname = ln;
    }

    public String getAbteilung() {
        return abteilung;
    }
    public void setAbteilung(String abteilung1){
        this.abteilung = abteilung1;
    }

    public Integer getId() {
        return id;
    }
    public void setID(int id1){
        this.id = id1;
    }


    public void setVerfuegbar(Boolean vb){
        verfuegbar = vb;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "id=" + id +
                ", expertise=" + expertise +
                ", verfuegbar=" + verfuegbar +
                ", vorname='" + vorname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", role='" + role + '\'' +
                ", abteilung='" + abteilung + '\'' +
                '}';
    }
}
