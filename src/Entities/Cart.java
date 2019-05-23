/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author PlusUltra
 */
public class Cart {
    private int id;
    private int id_p;
    private int id_u;
    private String name;
    private int quantity;
    private Double price;
    private Double total;
    
    public Cart(){}
    
    public Cart(String name, int quantity, Double price, Double total) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public Cart(int id, String name, int quantity, Double price, Double total) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public Cart(int id_u, int id_p, String name, int quantity, Double price) {
        this.id_u = id_u;
        this.id_p = id_p;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = price * quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                " TD, total=" + total +
                " TD}";
    }
}
