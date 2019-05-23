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
public class TicketsTheatre {
        private int idTicket ; 
    private int ownerId ; 
    private int idsession ;

    public TicketsTheatre( int idTicket , int ownerId, int idsession )
    {
        this.idTicket = idTicket;
        this.ownerId = ownerId;
        this.idsession = idsession;
    }

    public TicketsTheatre() {
    }



    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }


    public int getIdsession() {
        return idsession;
    }

    public void setIdsession(int idsession) {
        this.idsession = idsession;
    }

    @Override
    public String toString() {
        return "Ticket{" + "idTicket=" + idTicket + ", ownerId=" + ownerId + ", idsession=" + idsession + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TicketsTheatre other = (TicketsTheatre) obj;
        if (this.idTicket != other.idTicket) {
            return false;
        }
        return true;
    }
}
