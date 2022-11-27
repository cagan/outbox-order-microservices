package com.cagan.orderservice.service;

import com.cagan.orderservice.domain.entity.Order;
import com.cagan.orderservice.domain.entity.Status;
import com.cagan.orderservice.outbox.OutboxEventPublisher;
import com.cagan.orderservice.outbox.OutboxEventsUtil;
import com.cagan.orderservice.repository.OrderRepository;
import com.cagan.orderservice.rest.dto.OrderDTO;
import com.cagan.orderservice.rest.mapper.OrderMapper;
import com.cagan.orderservice.rest.request.CreateOrderRequest;
import com.cagan.orderservice.rest.response.OrderResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Data
@Slf4j
@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OutboxEventPublisher publisher;

    @Transactional
    public void createNewOrder(CreateOrderRequest request) {
        Order order = orderMapper.createOrderRequestToOrder(request);
        order.setStatus(Status.CREATED);
        orderRepository.save(order);

        OrderDTO orderDTO = orderMapper.createOrderToOrderDTO(order);
        publisher.publishEvent(OutboxEventsUtil.createNewOrderEvent(orderDTO));
        log.info("[Order: {}] created successfully", order);
    }

    public void changeStatus(String orderId, Status status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Order not found with id %s", status)));
        order.setStatus(status);
        orderRepository.save(order);

        OrderDTO orderDTO = orderMapper.createOrderToOrderDTO(order);
        publisher.publishEvent(OutboxEventsUtil.updateOrderStatusEvent(orderDTO));
        log.info("[OrderId: {}] status has been changed to {}", order.getId(), status);
    }

    public List<OrderResponse> getOrders() {
        return orderMapper.ordersToOrderResponseList(orderRepository.findAll());
    }
}
