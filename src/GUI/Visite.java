/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;

import java.util.Date;

/**
 *
 * @author Bilel
 */
public class Visite {
    private int idVisite;
    private int idGuide;
    private int nbPers;
    private Date dateV;
    private Date hdeb;
    private Date hfin;

    public Visite() {
    }

    public Visite(int idGuide, int nbPers, Date dateV, Date hdeb, Date hfin) {
        this.idGuide = idGuide;
        this.nbPers = nbPers;
        this.dateV = dateV;
        this.hdeb = hdeb;
        this.hfin = hfin;
    }

    
    
    public Visite(int nbPers, Date dateV, Date hdeb, Date hfin) {
        this.nbPers = nbPers;
        this.dateV = dateV;
        this.hdeb = hdeb;
        this.hfin = hfin;
    }

    
    
    
    public Visite(int idVisite, int idGuide, int nbPers, Date dateV, Date hdeb, Date hfin) {
        this.idVisite = idVisite;
        this.idGuide = idGuide;
        this.nbPers = nbPers;
        this.dateV = dateV;
        this.hdeb = hdeb;
        this.hfin = hfin;
    }

    public int getIdVisite() {
        return idVisite;
    }

    public void setIdVisite(int idVisite) {
        this.idVisite = idVisite;
    }

    public int getIdGuide() {
        return idGuide;
    }

    public void setIdGuide(int idGuide) {
        this.idGuide = idGuide;
    }

    public int getNbPers() {
        return nbPers;
    }

    public void setNbPers(int nbPers) {
        this.nbPers = nbPers;
    }

    public Date getDateV() {
        return dateV;
    }

    public void setDateV(Date dateV) {
        this.dateV = dateV;
    }

    public Date getHdeb() {
        return hdeb;
    }

    public void setHdeb(Date hdeb) {
        this.hdeb = hdeb;
    }

    public Date getHfin() {
        return hfin;
    }

    public void setHfin(Date hfin) {
        this.hfin = hfin;
    }

    @Override
    public String toString() {
        return "Visite{" + "idVisite=" + idVisite + ", idGuide=" + idGuide + ", nbPers=" + nbPers + ", dateV=" + dateV + ", hdeb=" + hdeb + ", hfin=" + hfin + '}';
    }
    
    
}
