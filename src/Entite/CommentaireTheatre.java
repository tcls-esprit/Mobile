/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author AQuel
 */
public class CommentaireTheatre {
   
    private int id ;
    private String scene;
    private String commentaire;

    public CommentaireTheatre(){}
    
    public CommentaireTheatre(int id, String scene, String commentaire) {
        this.id = id;
        this.scene = scene;
        this.commentaire = commentaire;
    }
    
    public CommentaireTheatre(String scene, String commentaire) {
        
        this.scene = scene;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "CommentaireTheatre:  " + " scene: " + scene + "  commentaire: " + commentaire ;
    }
    
     
}
