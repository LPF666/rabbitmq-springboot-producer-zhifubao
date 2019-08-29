package com.bfxy.springboot;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bfxy.springboot.entity.Order;
import com.bfxy.springboot.producer.RabbitSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	private RabbitSender rabbitSender;
    //SimpleDateFormat有线程安全问题，并发会出现问题
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@Test
	public void testSender1() throws Exception {
		 Map<String, Object> properties = new HashMap<>();
		 properties.put("number", "12345");
		 String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS").format(LocalDateTime.now());
         //simpleDateFormat.format(new Date())
		 properties.put("send_time",format);
		 rabbitSender.send("Hello RabbitMQ For Spring Boot!", properties);
	}
	
	@Test
	public void testSender2() throws Exception {
		 Order order = new Order("001", "第一个订单");
		 rabbitSender.sendOrder(order);
	}
	
	
	
}
