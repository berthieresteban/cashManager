package com.cashmanager.products.dao;
import com.cashmanager.products.model.Product;
import java.util.List;

public interface ProductDao {
    public List<Product>findAll();
    public Product findById(int id);
    public Product save(Product product);
    public void delete(int id);
}