package com.cagan.orderservice.outbox;

import com.fasterxml.jackson.databind.JsonNode;

public interface ExportedEvent {

    String aggregateId();

    String eventType();

    JsonNode payload();

    String eventName();
}
