package com.cashmanager.products.web.controller;
import com.cashmanager.products.exception.ResourceNotFoundException;
import com.cashmanager.products.exception.ForbiddenException;
import com.cashmanager.products.model.Cart;
import com.cashmanager.products.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.cashmanager.products.constants.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @Autowired
    private CartRepository CartRepository;

    @GetMapping(value = "/Carts")
    public Page<Cart> getCarts(Pageable pageable) {
        return CartRepository.findAll(pageable);
    }

    @GetMapping(value = "/Carts/{cartId}")
    public Cart getCartById(@RequestHeader String Authorization, @PathVariable long cartId) {
        Cart cart = CartRepository.findById(cartId)
            .orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));
        String username = getUsername(Authorization);
        if (username.compareTo(cart.getUsername()) == 0) {
            return cart;
        } else {
            throw new ForbiddenException("Can't access to cart no: " + cartId);
        }
    }

    @PostMapping("/Carts")
    public Cart createCart(@Valid @RequestBody Cart cart) {
        return CartRepository.save(cart);
    }

    @PutMapping("/Carts/{cartId}")
    public Cart updateCart(@RequestHeader String Authorization, @PathVariable long cartId, @Valid @RequestBody Cart cartRequest) {
        Cart cartRet = CartRepository.findById(cartId)
            .map(cart -> {
                cart.setTotalBill(cartRequest.getTotalBill());
                cart.setArticles(cartRequest.getArticles());
                cart.setPaid(cartRequest.getPaid());
                cart.setPaymentMode(cartRequest.getPaymentMode());
                return CartRepository.save(cart);
            }).orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));

        String username = getUsername(Authorization);
        if (username.compareTo(cartRet.getUsername()) == 0) {
            return cartRet;
        } else {
            throw new ForbiddenException("Can't access to cart no: " + cartId);
        }
    }

    @DeleteMapping("/Carts/{cartId}")
    public ResponseEntity<?> deleteCart(@PathVariable long cartId) {
        return CartRepository.findById(cartId)
            .map(cart -> {
                CartRepository.delete(cart);
                return ResponseEntity.ok().build();
            }).orElseThrow(() -> new ResourceNotFoundException("Cart not found with id " + cartId));
    }

    private String getUsername(String token) {
        byte[] signingKey = SecurityConstants.JWT_SECRET.getBytes();
        Jws<Claims> parsedToken = Jwts.parser()
            .setSigningKey(signingKey)
            .parseClaimsJws(token.replace("Bearer ", ""));
        String username = parsedToken
            .getBody()
            .getSubject();
        return username;
    }
}