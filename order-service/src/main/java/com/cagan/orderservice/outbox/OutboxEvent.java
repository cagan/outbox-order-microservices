package com.cagan.orderservice.outbox;

import com.fasterxml.jackson.databind.JsonNode;

public record OutboxEvent(
        String aggregateId,
        String eventType,
        JsonNode payload,
        String eventName,
        String aggregateName
) implements ExportedEvent {
}
