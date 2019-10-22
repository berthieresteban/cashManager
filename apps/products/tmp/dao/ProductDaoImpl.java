package com.cashmanager.products.dao;
import com.cashmanager.products.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
public class ProductDaoImpl implements ProductDao {
    public static List<Product>products=new ArrayList<>();
    static {
        products.add(new Product(1, new String("Ordinateur portable"), 350, new String("url"), new String("C pa 1 mac"), 10));
        products.add(new Product(2, new String("Aspirateur Robot"), 500 , new String("url"), new String("il marche tout seul a ce prix"), 3));
        products.add(new Product(3, new String("Table de Ping Pong"), 750 , new String("url"), new String("NE MARCHE QUE POUR LE BIERE PONG"), 7));
    }
    @Override
    public List<Product>findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        for (Product product : products) {
            if(product.getId() ==id){
                return product;
            }
        }
        return null;
    }

    @Override
    public Product save(Product product) {
        long lastId = products.get(products.size() - 1).getId();
        product.setId(lastId);
        products.add(product);
        return product;
    }

    @Override
    public void delete(int id) {
        int idx = 0;
        for (Product product : products) {
            if(product.getId() ==id){
                break;
            }
            idx += 1;
        }
        products.remove(idx);
    }
}