package com.cashmanager.products.web.controller;
import com.cashmanager.products.exception.ResourceNotFoundException;
import com.cashmanager.products.model.Cart;
import com.cashmanager.products.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CartController {

    @Autowired
    private CartRepository CartRepository;

    @GetMapping(value = "/Carts")
    public Page<Cart> getCarts(Pageable pageable) {
        return CartRepository.findAll(pageable);
    }

    @GetMapping(value = "/Carts/{cartId}")
    public Cart getCartById(@PathVariable long cartId) {
        return CartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));
    }

    @PostMapping("/Carts")
    public Cart createCart(@Valid @RequestBody Cart cart) {
        return CartRepository.save(cart);
    }

    @PutMapping("/Carts/{cartId}")
    public Cart updateCart(@PathVariable long cartId,
                           @Valid @RequestBody Cart cartRequest) {
        return CartRepository.findById(cartId)
                .map(cart -> {
                    cart.setTotalBill(cartRequest.getTotalBill());
                    cart.setArticles(cartRequest.getArticles());
                    cart.setPaid(cartRequest.getPaid());
                    cart.setPaymentMode(cartRequest.getPaymentMode());
                    return CartRepository.save(cart);
                }).orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));
    }

    @DeleteMapping("/Carts/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable long cartId) {
        return CartRepository.findById(cartId)
                .map(cart -> {
                    CartRepository.delete(cart);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));
    }
}