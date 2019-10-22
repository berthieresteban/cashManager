package com.cashmanager.products.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;
    private float price;
    @NotBlank
    @Size(min = 3, max = 100)
    private String imgUrl;
    @NotBlank
    @Size(min = 3, max = 250)
    private String description;
    private int quantity;

    public Product() {

    }

    public Product(long id, String name, float price, String img_url, String description, int quantity) {
        this.id = id;
        this.name = name;
        this.img_url = img_url;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return img_url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImgUrl(String img_url) {
        this.img_url = img_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", price="
                + price + '\'' + ", quantity'" + quantity + '\'' + ", img_url='" + img_url + "\'}";
    }
}
