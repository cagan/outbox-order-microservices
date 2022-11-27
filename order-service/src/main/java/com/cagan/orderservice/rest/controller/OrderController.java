package com.cagan.orderservice.rest.controller;

import com.cagan.orderservice.rest.request.CreateOrderRequest;
import com.cagan.orderservice.rest.request.UpdateStatusRequest;
import com.cagan.orderservice.rest.response.OrderResponse;
import com.cagan.orderservice.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> create(@Validated @RequestBody CreateOrderRequest request) {
        orderService.createNewOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@RequestBody UpdateStatusRequest request, @PathVariable(value = "id") String orderId) {
        orderService.changeStatus(orderId, request.getStatus());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders());
    }
}
