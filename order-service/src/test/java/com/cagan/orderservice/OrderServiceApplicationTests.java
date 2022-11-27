package com.cagan.orderservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@SpringBootTest
class OrderServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void outboxLocalTimeCheck() {
		Instant daysBefore = Instant.now().minus(6, ChronoUnit.DAYS);
		System.out.println("DAYS BEFORE: " + daysBefore);
	}
}
