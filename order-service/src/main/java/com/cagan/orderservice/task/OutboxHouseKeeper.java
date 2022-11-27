package com.cagan.orderservice.task;

import com.cagan.orderservice.domain.entity.Outbox;
import com.cagan.orderservice.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@EnableScheduling
@Component
@RequiredArgsConstructor
@Slf4j
public class OutboxHouseKeeper {
    @Value("${order.outbox.clean.interval.days:7}")
    public Long days;

    private final OutboxRepository outboxRepository;

    // Run every night at midnight
    @Scheduled(cron = "${order.outbox.clean.cron.expression}")
    public void cleanOutboxLogsOlderThanTime() {
        log.info("Job started running at: {}", LocalDateTime.now());

        Instant daysBefore = Instant.now().minus(days, ChronoUnit.DAYS);
        List<Outbox> allByCreatedOnBefore = outboxRepository.findAllByCreatedOnBefore(LocalDateTime.from(daysBefore));
        outboxRepository.deleteAll(allByCreatedOnBefore);

        log.info("[Outbox records: {}] removed from table",
                allByCreatedOnBefore.stream().map(Outbox::getId).collect(Collectors.joining())
        );
    }
}
