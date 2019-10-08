package com.cashmanager.products.web.controller;
import com.cashmanager.products.dao.CartDao;
import com.cashmanager.products.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartDao cartDao;

    @GetMapping(value="/Carts")
    public List<Cart>getCarts() {
        return cartDao.findAll();
    }

    @GetMapping(value="/Carts/{id}")
    public Cart getCartById(@PathVariable int id) {
        return cartDao.findById(id);
    }

    @PostMapping(value = "/Carts")
    public void addCart(@RequestBody Cart cart) {
        cartDao.save(cart);
    }

    @DeleteMapping(value="/Carts/{id}")
    public Cart deleteCart(@PathVariable int id) {
        Cart prod = cartDao.findById(id);
        if (prod != null) {
            cartDao.delete(id);
        }
        return null;
    }

    @PutMapping(value="/Carts/{id}")
    public Cart updateMapping(@PathVariable int id, @RequestBody Cart cart) {
        System.out.println(cart);
        Cart c = cartDao.findById(id);
        if (c == null | cart == null) {
            return null;
        }
        if (cart.getTotalBill() != 0) {
            c.setTotalBill(cart.getTotalBill());
        }
        if (cart.getArticles() != null) {
            c.setArticles(cart.getArticles());
        }
        return null;
    }
}