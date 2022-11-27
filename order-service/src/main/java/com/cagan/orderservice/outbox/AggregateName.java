package com.cagan.orderservice.outbox;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum AggregateName {
    ORDER("ORDER");
    private final String aggregate;
}
