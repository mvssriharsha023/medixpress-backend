package com.medixpress.cart_service.service;

import com.medixpress.cart_service.dto.CartItemDTO;
import com.medixpress.cart_service.exception.CartConflictException;
import com.medixpress.cart_service.exception.CartItemNotFoundException;
import com.medixpress.cart_service.exception.OutOfStockException;
import com.medixpress.cart_service.model.CartItem;
import com.medixpress.cart_service.repository.CartRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CartItem addToCart(CartItemDTO dto) {
        // Check if existing cart has items from another pharmacy
        List<CartItem> existingItems = cartRepository.findByUserId(dto.getUserId());
        if (!existingItems.isEmpty() &&
                existingItems.stream().anyMatch(item -> !item.getPharmacyId().equals(dto.getPharmacyId()))) {
            throw new CartConflictException("Cart contains items from another pharmacy. Please clear the cart first.");
        }

        CartItem existingItem = existingItems.stream()
                .filter(item -> item.getMedicineId().equals(dto.getMedicineId()))
                .findFirst()
                .orElse(null);

        int totalRequestedQuantity = dto.getQuantity();
        if (existingItem != null) {
            totalRequestedQuantity += existingItem.getQuantity();
        }

        // ✅ Check if requested quantity is available
//        int availableStock = medicineService.getAvailableQuantity(dto.getPharmacyId(), dto.getMedicineId());
        String url = "http://medicine-service/api/medicines/quantity/" + dto.getMedicineId();
        System.out.println(url);

        ResponseEntity<Integer> response = restTemplate.getForEntity(url, Integer.class);
        int availableStock = Optional.ofNullable(response.getBody()).orElse(0);

        if (totalRequestedQuantity > availableStock) {
            throw new OutOfStockException("Requested quantity exceeds available stock.");
        }

        if (existingItem != null) {
            existingItem.setQuantity(totalRequestedQuantity);
            existingItem.setAddedAt(LocalDateTime.now());
            return cartRepository.save(existingItem);
        }

        CartItem item = CartItem.builder()
                .userId(dto.getUserId())
                .pharmacyId(dto.getPharmacyId())
                .medicineId(dto.getMedicineId())
                .quantity(dto.getQuantity())
                .addedAt(LocalDateTime.now())
                .build();

        CartItem saved = cartRepository.save(item);
        dto.setId(saved.getId());
        dto.setAddedAt(saved.getAddedAt());
        return saved;
    }


    @Override
    public List<CartItemDTO> getCartItemsByUser(Long userId) {
        return cartRepository.findByUserId(userId)
                .stream()
                .map(item -> CartItemDTO.builder()
                        .id(item.getId())
                        .userId(item.getUserId())
                        .pharmacyId(item.getPharmacyId())
                        .medicineId(item.getMedicineId())
                        .quantity(item.getQuantity())
                        .addedAt(item.getAddedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void removeCartItem(String cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    @Override
    @Transactional
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }

    @Override
    public CartItemDTO updateQuantity(String cartItemId, Integer newQuantity) {
        CartItem item = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("Cart item not found"));

//        int availableStock = medicineService.getAvailableQuantity(item.getPharmacyId(), item.getMedicineId());
        String url = "http://medicine-service/api/medicines/quantity/" + item.getMedicineId();

        ResponseEntity<Integer> response = restTemplate.getForEntity(url, Integer.class);
        int availableStock = Optional.ofNullable(response.getBody()).orElse(0);
        if (newQuantity > availableStock) {
            throw new OutOfStockException("Requested quantity exceeds available stock.");
        }
        item.setQuantity(newQuantity);
        LocalDateTime now = LocalDateTime.now();
        item.setAddedAt(now);
        CartItem updated = cartRepository.save(item);

        return CartItemDTO.builder()
                .id(updated.getId())
                .userId(updated.getUserId())
                .pharmacyId(updated.getPharmacyId())
                .medicineId(updated.getMedicineId())
                .quantity(updated.getQuantity())
                .addedAt(now)
                .build();
    }
}
