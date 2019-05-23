/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author souissi oussama
 */
public class Evenement {
    private int id;
    private String titre ;
    private float prix;
    private String description;
    private int duree ;
    private int id_user;
    private int etat;
    private String image ;    
    private String type_event ;
    private String type;

    public Evenement(int id, String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.duree = duree;
        this.id_user = id_user;
        this.etat = etat;
        this.image = image;
        this.type_event = type_event;
    }

    public Evenement(String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event, String type) {
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.duree = duree;
        this.id_user = id_user;
        this.etat = etat;
        this.image = image;
        this.type_event = type_event;
        this.type = type;
    }

    public Evenement(int id, String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event, String type) {
        this.id = id;
        this.titre = titre;
        this.prix = prix;
        this.description = description;
        this.duree = duree;
        this.id_user = id_user;
        this.etat = etat;
        this.image = image;
        this.type_event = type_event;
        this.type = type;
    }
    

    public Evenement() {
    }
    

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public float getPrix() {
        return prix;
    }

    public String getDescription() {
        return description;
    }

    public int getDuree() {
        return duree;
    }

    public int getId_user() {
        return id_user;
    }

    public int getEtat() {
        return etat;
    }

    public String getImage() {
        return image;
    }

    public String getType_event() {
        return type_event;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setType_event(String type_event) {
        this.type_event = type_event;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", titre=" + titre + ", prix=" + prix + ", description=" + description + ", duree=" + duree + ", id_user=" + id_user + ", etat=" + etat + ", image=" + image + ", type_event=" + type_event + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Evenement other = (Evenement) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (this.duree != other.duree) {
            return false;
        }
        if (this.id_user != other.id_user) {
            return false;
        }
        if (this.etat != other.etat) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.type_event, other.type_event)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }
    
    
}
