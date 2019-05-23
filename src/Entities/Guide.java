/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Bilel
 */
public class Guide {
    private int idGuide;
    private String nom;
    private String prenom;
    private Date date;
    private Date h_deb;
    private String description;

    public Guide() {
    }

    
    
    public Guide(String nom, String prenom, Date date, Date h_deb, String description) {
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.h_deb = h_deb;
        this.description = description;
    }

    
    
    public Guide(int idGuide, String nom, String prenom, Date date, Date h_deb, String description) {
        this.idGuide = idGuide;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.h_deb = h_deb;
        this.description = description;
    }

    public int getIdGuide() {
        return idGuide;
    }

    public void setIdGuide(int idGuide) {
        this.idGuide = idGuide;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getH_deb() {
        return h_deb;
    }

    public void setH_deb(Date h_deb) {
        this.h_deb = h_deb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Guide{" + "idGuide=" + idGuide + ", nom=" + nom + ", prenom=" + prenom + ", date=" + date + ", h_deb=" + h_deb + ", description=" + description + '}';
    }
    
    
    
}
