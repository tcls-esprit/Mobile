/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author mouna
 */
public class Film {
    

    private int id;
    private String titre;
    private String nomrealisateur;
    private String description;
    private String duree;
    private String anneesortie;
    private String categorie;
    private String image;
    private Double rate;
    private String video;

    

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setNomrealisateur(String nomrealisateur) {
        this.nomrealisateur = nomrealisateur;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public void setAnneesortie(String anneesortie) {
        this.anneesortie = anneesortie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getNomrealisateur() {
        return nomrealisateur;
    }

    public String getDescription() {
        return description;
    }

    public String getDuree() {
        return duree;
    }

    public String getAnneesortie() {
        return anneesortie;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getImage() {
        return image;
    }

    public Double getRate() {
        return rate;
    }

    public String getVideo() {
        return video;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
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
        final Film other = (Film) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Film{" + "id=" + id + ", titre=" + titre + ", nomrealisateur=" + nomrealisateur + ", description=" + description + ", duree=" + duree + ", anneesortie=" + anneesortie + ", categorie=" + categorie + ", image=" + image + ", rate=" + rate + ", video=" + video + '}';
    }
 
    
    
    
}