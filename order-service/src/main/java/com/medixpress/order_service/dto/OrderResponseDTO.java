package com.medixpress.order_service.dto;

import com.medixpress.order_service.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private String id;
    private Long userId;
    private Long pharmacyId;
    private Double totalAmount;
    private OrderStatus status;
    private LocalDateTime orderDateTime;
    private List<OrderItemDTO> items;
}

