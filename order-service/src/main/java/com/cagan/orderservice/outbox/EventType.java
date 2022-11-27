package com.cagan.orderservice.outbox;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventType {
    INSERT("I"),
    UPDATE("U"),
    DELETE("D"),
    SOFT_DELETE("S");

    private final String type;
}
