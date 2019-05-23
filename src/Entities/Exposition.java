/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author souissi oussama
 */
public class Exposition extends Evenement  {
    private int nombre_rayon;

    public Exposition(int nombre_rayon, int id, String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event) {
        super(id, titre, prix, description, duree, id_user, etat, image, type_event);
        this.nombre_rayon = nombre_rayon;
    }

    public Exposition() {
    }
    

    public int getNombre_rayon() {
        return nombre_rayon;
    }

    public void setNombre_rayon(int nombre_rayon) {
        this.nombre_rayon = nombre_rayon;
    }

    @Override
    public String toString() {
        return "Exposition{"+super.toString() + "nombre_rayon=" + nombre_rayon + '}';
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
        final Exposition other = (Exposition) obj;
        if (this.nombre_rayon != other.nombre_rayon) {
            return false;
        }
        return true;
    }
    
}
