package com.cashmanager.products.model;

public class Product {
    private int id;
    private String name;
    private int price;
    private String imgUrl;
    private String description;

    public Product() {

    }

    public Product(int id, String name, int price, String imgUrl, String description) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.price = price;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price=price;
    }

    @Override
    public String toString(){
        return "Product{"+
                "id=" + id +
                ", name='"+ name + '\'' +
                ", description='"+ description + '\'' +
                ", imgUrl='"+ imgUrl + '\'' +
                ", price=" + price + '}';
    }
}

