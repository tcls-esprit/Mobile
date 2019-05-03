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
public class Filmfav {
    private int id;
    private int iduser;
    private int idfilm;

    public Filmfav(int iduser, int idfilm) {
        this.iduser = iduser;
        this.idfilm = idfilm;
    }

    public Filmfav(int id, int iduser, int idfilm) {
        this.id = id;
        this.iduser = iduser;
        this.idfilm = idfilm;
    }

    public Filmfav() {
        
    }

    
    

    public int getId() {
        return id;
    }

    public int getIduser() {
        return iduser;
    }

    public int getIdfilm() {
        return idfilm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public void setIdfilm(int idfilm) {
        this.idfilm = idfilm;
    }
    
}
