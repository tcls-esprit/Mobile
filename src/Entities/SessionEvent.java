/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;



/**
 *
 * @author souissi oussama
 */
public class SessionEvent {
    private int ID_salle;
    private String date_deb ;
    private String date_fin ;
    private int ID_event;
    private int ID_session;

    public SessionEvent(int ID_salle, String date_deb, String date_fin, int ID_event, int ID_session) {
        this.ID_salle = ID_salle;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.ID_event = ID_event;
        this.ID_session = ID_session;
    }

    public SessionEvent(String date_deb, String date_fin) {
        this.date_deb = date_deb;
        this.date_fin = date_fin;
    }
    

    public int getID_salle() {
        return ID_salle;
    }

    public String getDate_deb() {
        return date_deb;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public int getID_event() {
        return ID_event;
    }

    public int getID_session() {
        return ID_session;
    }

    public void setID_salle(int ID_salle) {
        this.ID_salle = ID_salle;
    }

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setID_event(int ID_event) {
        this.ID_event = ID_event;
    }

    public void setID_session(int ID_session) {
        this.ID_session = ID_session;
    }
    
}
