package com.medixpress.order_service.response;

import com.medixpress.order_service.model.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailResponseDTO {
    private String customerName;
    private String pharmacyName;
    private Double totalAmount;
    private OrderStatus status;
    private LocalDateTime orderDateTime;
    private List<ItemResponseDTO> items;
}
