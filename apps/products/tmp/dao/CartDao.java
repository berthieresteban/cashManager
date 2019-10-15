package com.cashmanager.products.dao;
import com.cashmanager.products.model.Cart;
import java.util.List;

public interface CartDao {
    public List<Cart>findAll();
    public Cart findById(int id);
    public Cart save(Cart cart);
    public void delete(int id);
}