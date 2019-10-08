package com.cashmanager.products.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int id;
    private List<Integer> articles = new ArrayList<>();
    private float totalBill;


    public Cart() {

    }

    public Cart(int id, List<Integer> articles, float totalBill) {
        this.id = id;
        this.articles = articles;
        this.totalBill = totalBill;
    }

    public void setArticles(List<Integer> articles) {
        this.articles = articles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTotalBill(float totalBill) {
        this.totalBill = totalBill;
    }

    public float getTotalBill() {
        return totalBill;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getArticles() {
        return articles;
    }

    @Override
    public String toString(){
        String product =
                "Cart{"+
                "id=" + id +
                ", totalBill='"+ totalBill + '\'' +
        ", articles= [" ;
        for (int id : articles) {
            product += id + ",";
        }
        product += "]}";
        return product;
    }
}