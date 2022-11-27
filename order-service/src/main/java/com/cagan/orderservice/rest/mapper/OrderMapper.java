package com.cagan.orderservice.rest.mapper;

import com.cagan.orderservice.domain.entity.Order;
import com.cagan.orderservice.rest.dto.OrderDTO;
import com.cagan.orderservice.rest.request.CreateOrderRequest;
import com.cagan.orderservice.rest.response.OrderResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order createOrderRequestToOrder(CreateOrderRequest request);

    OrderDTO createOrderToOrderDTO(Order order);

    List<OrderResponse> ordersToOrderResponseList(List<Order> orders);
}
