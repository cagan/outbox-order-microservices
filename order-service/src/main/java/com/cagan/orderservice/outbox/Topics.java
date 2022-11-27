package com.cagan.orderservice.outbox;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Topics {
    NEW_ORDER_CREATED("new_order"),
    ORDER_STATUS_CHANGED("order_status");

    private final String topic;
}
