package com.cashmanager.products.dao;
import com.cashmanager.products.model.Cart;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {
    public static List<Cart>carts=new ArrayList<>();
//    public Cart(int id, List<Integer> articles, float totalBill) {


    static {
        List<Integer> articles = new ArrayList<Integer>();
        articles.add(1);
        articles.add(2);
        carts.add(new Cart(1, articles, 850));
        articles.remove(1);
        articles.add(3);
        carts.add(new Cart(1, articles, 1100));
    }

    @Override
    public List<Cart>findAll() {
        return carts;
    }

    @Override
    public Cart findById(int id) {
        for (Cart cart : carts) {
            if(cart.getId() ==id){
                return cart;
            }
        }
        return null;
    }

    @Override
    public Cart save(Cart cart) {
        int lastId = carts.get(carts.size() - 1).getId();
        cart.setId(lastId);
        carts.add(cart);
        return cart;
    }

    @Override
    public void delete(int id) {
        int idx = 0;
        for (Cart cart : carts) {
            if(cart.getId() ==id){
                break;
            }
            idx += 1;
        }
        carts.remove(idx);
    }
}