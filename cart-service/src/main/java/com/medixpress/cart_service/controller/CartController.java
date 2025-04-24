package com.medixpress.cart_service.controller;

import com.medixpress.cart_service.dto.CartItemDTO;
import com.medixpress.cart_service.exception.AuthenticationException;
import com.medixpress.cart_service.model.CartItem;
import com.medixpress.cart_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(@RequestHeader("id") Long userId, @RequestBody CartItemDTO dto) {
        if (userId == null) {
            throw new AuthenticationException("Please login to add medicines into cart");
        }
        dto.setUserId(userId);
        return cartService.addToCart(dto);
    }

    @GetMapping("/user")
    public List<CartItemDTO> getUserCart(@RequestHeader("id") Long userId) {
        if (userId == null) {
            throw new AuthenticationException("Please login to view cart items");
        }
        return cartService.getCartItemsByUser(userId);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeItem(@PathVariable String cartItemId) {
        cartService.removeCartItem(cartItemId);
        return ResponseEntity.ok("Removed " + cartItemId + " from cart successfully.");
    }

    @DeleteMapping("/clear/{userId}")
    public ResponseEntity<String> clearCart(@PathVariable Long userId) {
        if (userId == null) {
            throw new AuthenticationException("Please login to add medicines into cart");
        }
        System.out.println("CLEAR CART for userId = " + userId);
        cartService.clearCart(userId);
        return ResponseEntity.ok("Clear cart successfully.");
    }

    @PutMapping("/update/{cartItemId}")
    public CartItemDTO updateQuantity(
            @PathVariable String cartItemId,
            @RequestBody Integer quantity) {
        return cartService.updateQuantity(cartItemId, quantity);
    }
}
