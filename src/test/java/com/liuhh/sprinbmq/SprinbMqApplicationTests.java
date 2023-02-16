package com.liuhh.sprinbmq;

import com.liuhh.mq.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SprinbMqApplicationTests {
	@Autowired
	OrderService orderService;

	@Test
	void contextLoads() {
		orderService.makeOrder("1000","100", 10);
	}
	@Test
	void direct() {
		orderService.directOrder("1000","100", 10);
	}

	@Test
	void topic(){
		orderService.topicOrder(UUID.randomUUID().toString());
	}
	@Test
	void ttl(){
		orderService.ttlOrder(UUID.randomUUID().toString());
	}

	@Test
	void normal(){
		orderService.normalOrder(UUID.randomUUID().toString());
	}
}
