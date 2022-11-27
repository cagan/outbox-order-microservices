package com.cagan.orderservice.repository;

import com.cagan.orderservice.domain.entity.Outbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, String> {
    List<Outbox> findAllByCreatedOnBefore(LocalDateTime localDateTime);
}
