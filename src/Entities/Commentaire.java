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
public class Commentaire {
    private int id;
    private String contenu;
    private String date;
    private int id_user;
    private int id_event;
    private String owner; 
    private String status;
    private String image;

    public Commentaire(int id, String contenu, String date, int id_user, int id_event, String owner, String status ) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
        this.id_user = id_user;
        this.id_event = id_event;
        this.owner = owner;
        this.status = status;
        this.image=owner;
        
    }

    public Commentaire(String contenu, int id_user, int id_event) {
        this.contenu = contenu;
        this.id_user = id_user;
        this.id_event = id_event;
        
        
        
    }

    public Commentaire(int id,String contenu, String date, String owner ,String image,int id_user) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
        this.owner = owner;
        this.image = image;
        this.id_user = id_user;
    }
    
    

    public int getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDate() {
        return date;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public String getOwner() {
        return owner;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", contenu=" + contenu + ", date=" + date + ", id_user=" + id_user + ", id_event=" + id_event + ", owner=" + owner + ", status=" + status + ", image=" + image + '}';
    }
    
    
    
}
