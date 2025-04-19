package com.medixpress.cart_service.service;

import com.medixpress.cart_service.dto.CartItemDTO;
import com.medixpress.cart_service.model.CartItem;

import java.util.List;

public interface CartService {

    CartItem addToCart(CartItemDTO cartItemDTO);
    List<CartItemDTO> getCartItemsByUser(Long userId);
    void removeCartItem(String cartItemId);
    void clearCart(Long userId);
    CartItemDTO updateQuantity(String cartItemId, Integer newQuantity);
}
