package com.cagan.orderservice.outbox;

import com.cagan.orderservice.rest.dto.OrderDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public final class OutboxEventsUtil {

    public static OutboxEvent createNewOrderEvent(OrderDTO orderDTO) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        JsonNode payload = mapper.convertValue(orderDTO, JsonNode.class);
        return new OutboxEvent(
                orderDTO.getId(),
                EventType.INSERT.getType(),
                payload, Topics.NEW_ORDER_CREATED.getTopic(),
                AggregateName.ORDER.getAggregate()
        );
    }

    public static OutboxEvent updateOrderStatusEvent(OrderDTO orderDTO) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        JsonNode payload = mapper.convertValue(orderDTO, JsonNode.class);
        return new OutboxEvent(
                orderDTO.getId(),
                EventType.UPDATE.getType(),
                payload,
                Topics.ORDER_STATUS_CHANGED.getTopic(),
                AggregateName.ORDER.getAggregate()
        );
    }
}
