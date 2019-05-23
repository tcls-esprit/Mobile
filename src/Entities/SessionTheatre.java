/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author AQuel
 */
public class SessionTheatre {
     private int id_sess;
    private int id_scene;
    private int id_salle;
    private String date_debut;
    private String date_fin ;
    private float prix ;
     
    public SessionTheatre(int id_sess,int id_salle,  String date_debut, String date_fin, float prix,int id_scene) {
        this.id_sess = id_sess;
        this.id_scene = id_scene;
        this.id_salle = id_salle ;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.prix = prix;
        
    }
    public SessionTheatre(){}
    
    public int getId_sess() {
        return id_sess;
    }

    public void setId_sess(int id_sess) {
        this.id_sess = id_sess;
    }

    public int getId_scene() {
        return id_scene;
    }

    public void setId_scene(int id_scene) {
        this.id_scene = id_scene;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }
    
    @Override
    public String toString() {
        return "SessionTheatre: " + " id_sess: " + id_sess + "   id_scene: " + id_scene + "   id_salle: " + id_salle + "   date_debut: " + date_debut + "   date_fin:  " + date_fin + "   prix: " + prix;
    }
    
}
