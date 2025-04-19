package com.medixpress.order_service.model;

import com.medixpress.order_service.dto.OrderItemDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;

    private Long userId;

    private Long pharmacyId;

    private Double totalAmount;

    private LocalDateTime orderDateTime;

    private OrderStatus status;

    private List<OrderItem> items;
}

