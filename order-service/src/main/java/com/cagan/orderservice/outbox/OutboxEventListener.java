package com.cagan.orderservice.outbox;

import com.cagan.orderservice.domain.entity.Outbox;
import com.cagan.orderservice.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutboxEventListener  {
    private final OutboxRepository outboxRepository;

    @EventListener
    public void handleOutboxEvent(OutboxEvent outboxEvent) {
        Outbox outbox = new Outbox();
        outbox.setEventName(outboxEvent.eventName());
        outbox.setEventType(outboxEvent.eventType());
        outbox.setPayload(outboxEvent.payload());
        outbox.setAggregateId(outboxEvent.aggregateId());
        outbox.setAggregateName(outboxEvent.aggregateName());

        outboxRepository.save(outbox);
        log.info("[Event: {}] saved to the outbox table", outboxEvent);
        outboxRepository.delete(outbox);
        log.info("[Event: {}] remove from the outbox table", outboxEvent);
    }
}
