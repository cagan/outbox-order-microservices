package com.cagan.orderservice.rest.dto;

import com.cagan.orderservice.domain.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private String id;
    private String productId;
    private Status status;
    private LocalDateTime createdOn;
    public LocalDateTime updatedOn;
}
