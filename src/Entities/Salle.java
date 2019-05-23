/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author asus
 */
public class Salle {
        private  int id ;
    private String Nom ; 
    private int Capacite ; 
    private TypeSalle Type ; 
    private EtatSalle Etat ;

    public Salle(  String Nom, int Capacite, TypeSalle Type, EtatSalle Etat) {
        this.Nom = Nom;
        this.Capacite = Capacite;
        this.Type = Type;
        this.Etat = Etat;
    }

    public Salle(int id, int Capacite , String Nom, TypeSalle Type, EtatSalle Etat) {
        this.id = id;
        this.Nom = Nom;
        this.Capacite = Capacite;
        this.Type = Type;
        this.Etat = Etat;
    }

    public Salle() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public int getCapacite() {
        return Capacite;
    }

    public void setCapacite(int Capacite) {
        this.Capacite = Capacite;
    }

    @Override
    public String toString() {
        return "Salle{" + "id=" + id + ", Nom=" + Nom + ", Capacite=" + Capacite + ", Type=" + Type + ", Etat=" + Etat + '}';
    }

    public TypeSalle getType() {
        return Type;
    }

    public void setType(TypeSalle Type) {
        this.Type = Type;
    }

    public EtatSalle getEtat() {
        return Etat;
    }

    public void setEtat(EtatSalle Etat) {
        this.Etat = Etat;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Salle other = (Salle) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
}
