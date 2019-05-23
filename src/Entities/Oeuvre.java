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
public class Oeuvre {
    private int id;
    private String type ;
    private float prix ;
    private String DateCreate;
    private String titre ;
    private int id_exposition ;

    public Oeuvre(int id, String type, float prix, String DateCreate, String titre, int id_exposition) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.DateCreate = DateCreate;
        this.titre = titre;
        this.id_exposition = id_exposition;
    }

    public Oeuvre(float prix, String DateCreate, String titre) {
        this.prix = prix;
        this.DateCreate = DateCreate;
        this.titre = titre;
    }
    

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public float getPrix() {
        return prix;
    }

    public String getDateCreate() {
        return DateCreate;
    }

    public String getTitre() {
        return titre;
    }

    public int getId_exposition() {
        return id_exposition;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDateCreate(String DateCreate) {
        this.DateCreate = DateCreate;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setId_exposition(int id_exposition) {
        this.id_exposition = id_exposition;
    }

    @Override
    public String toString() {
        return "Oeuvre{" + "id=" + id + ", type=" + type + ", prix=" + prix + ", DateCreate=" + DateCreate + ", titre=" + titre + ", id_exposition=" + id_exposition + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    
}
