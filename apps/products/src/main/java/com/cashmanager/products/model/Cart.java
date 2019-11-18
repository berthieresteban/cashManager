package com.cashmanager.products.model;

import java.beans.JavaBean;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name = "carts")
@JavaBean
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String articles;
    private float totalBill;
    private boolean paid;
    private String paymentMode;
    private String username;

    public Cart() { }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public void setArticles(String articles) {
        this.articles = articles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTotalBill(float totalBill) {
        this.totalBill = totalBill;
    }

    public String getUsername() {
        return username;
    }

    public boolean getPaid() {
        return paid;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public float getTotalBill() {
        return totalBill;
    }

    public long getId() {
        return id;
    }

    public String getArticles() {
        return articles;
    }

    @Override
    public String toString() {
        String Cart =
                "Cart{" +
                        "id=" + id +
                        ", totalBill='" + totalBill + '\'' +
                        ", paid='" + paid + '\'' +
                        ", paymentMode=" + paymentMode + '\'' +
                        ", articles=[";
//        for (int id : articles) {
//            Cart += id + ",";
//        }
        Cart += "]}";
        return Cart;
    }
}