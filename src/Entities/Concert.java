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
public class Concert extends Evenement {
    private String artistes ;
    private String type_concert ;

    public Concert(String artistes, String type_concert, int id, String titre, float prix, String description, int duree, int id_user, int etat, String image, String type_event) {
        super(id, titre, prix, description, duree, id_user, etat, image, type_event);
        this.artistes = artistes;
        this.type_concert = type_concert;
    }

    public Concert() {
    }
    

    public String getArtistes() {
        return artistes;
    }

    public String getType_concert() {
        return type_concert;
    }

    public void setArtistes(String artistes) {
        this.artistes = artistes;
    }

    public void setType_concert(String type_concert) {
        this.type_concert = type_concert;
    }

    @Override
    public String toString() {
        return "Concert{"+super.toString() + "artistes=" + artistes + ", type_concert=" + type_concert + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }


    
}
