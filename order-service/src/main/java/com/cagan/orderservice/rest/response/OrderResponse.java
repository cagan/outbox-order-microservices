package com.cagan.orderservice.rest.response;

import com.cagan.orderservice.domain.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderResponse {
    private String id;
    private String productId;
    private Status status;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
