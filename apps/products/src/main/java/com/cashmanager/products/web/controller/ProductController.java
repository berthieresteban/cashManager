package com.cashmanager.products.web.controller;
import com.cashmanager.products.dao.ProductDao;
import com.cashmanager.products.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @GetMapping(value="/Products")
    public List<Product>getProducts() {
        return productDao.findAll();
    }

    @GetMapping(value="/Products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDao.findById(id);
    }

    @PostMapping(value = "/Products")
    public void addProduct(@RequestBody Product product) {
        productDao.save(product);
    }

    @DeleteMapping(value="/Products/{id}")
    public Product deleteProduct(@PathVariable int id) {
        Product prod = productDao.findById(id);
        if (prod != null) {
            productDao.delete(id);
        }
        return null;
    }

    @PutMapping(value="/Products/{id}")
    public Product updateMapping(@PathVariable int id, @RequestBody Product product) {
        System.out.println(product);
        Product prod = productDao.findById(id);
        if (prod == null | product == null) {
            return null;
        }
        if (product.getDescription() != null) {
            prod.setDescription(product.getDescription());
        }
        if (product.getDescription() != null) {
            prod.setDescription(product.getDescription());
        }
        if (product.getImgUrl() != null) {
            prod.setImgUrl(product.getImgUrl());
        }
        if (product.getName() != null) {
            prod.setName(product.getName());
        }
        if (product.getPrice() != 0) {
            prod.setPrice(product.getPrice());
        }
        return null;
    }
}