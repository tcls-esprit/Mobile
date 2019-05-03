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
public class Rate {
     
    private int idrate;
    private Double ratee;
    private int idfilm;

    public Rate(Double ratee, int idfilm) {
        this.ratee = ratee;
        this.idfilm = idfilm;
    }

    public Rate(int idrate, Double ratee, int idfilm) {
        this.idrate = idrate;
        this.ratee = ratee;
        this.idfilm = idfilm;
    }

    public Rate() {
       
    }

  
    

   public int getIdrate() {
        return idrate;
    }

    public Double getRatee() {
        return ratee;
    }

    public int getIdfilm() {
        return idfilm;
    }

    

    public void setIdrate(int idrate) {
        this.idrate = idrate;
    }

    public void setRatee(Double ratee) {
        this.ratee = ratee;
    }

    public void setIdfilm(int idfilm) {
        this.idfilm = idfilm;
    }

    
   
  
    
    
}
