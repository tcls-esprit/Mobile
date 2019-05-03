/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;



/**
 *
 * @author mouna
 */
public class SessionFilm {
   private String date;
   private String  heure;
   private String  heurefin;
   private int idsalle;
   private int idfilm;
   private int idsession;
   private int prix;

   

    public SessionFilm(String date, String heure, String heurefin, int idsalle, int idfilm, int idsession, int prix) {
        this.date = date;
        this.heure = heure;
        this.heurefin = heurefin;
        this.idsalle = idsalle;
        this.idfilm = idfilm;
        this.idsession = idsession;
        this.prix = prix;
    }

    public SessionFilm(String date, String heure, String heurefin) {
        this.date = date;
        this.heure = heure;
        this.heurefin = heurefin;
    }

    public SessionFilm() {
     
    }

    
    public String getDate() {
        return date;
    }

    public String getHeure() {
        return heure;
    }

    public int getIdsalle() {
        return idsalle;
    }

    public int getIdfilm() {
        return idfilm;
    }

    public int getIdsession() {
        return idsession;
    }

    public int getPrix() {
        return prix;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public void setIdsalle(int idsalle) {
        this.idsalle = idsalle;
    }

    public void setIdfilm(int id_film) {
        this.idfilm = id_film;
    }

    public void setIdsession(int id_session) {
        this.idsession = id_session;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getHeurefin() {
        return heurefin;
    }

    public void setHeurefin(String heurefin) {
        this.heurefin = heurefin;
    }
    

    @Override
    public String toString() {
        return "SessionFilm{" + "date=" + date + ", heure=" + heure + ", heurefin=" + heurefin + ", idsalle=" + idsalle + ", idfilm=" + idfilm + ", idsession=" + idsession + ", prix=" + prix + '}';
    }

   
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SessionFilm other = (SessionFilm) obj;
        if (this.idsession != other.idsession) {
            return false;
        }
        return true;
    }


    
}
