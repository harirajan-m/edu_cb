package com.edu.Orderservice;

import com.edu.Orderservice.model.Item;
import com.edu.Orderservice.model.Order;
import com.edu.Orderservice.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class OrderserviceApplication {

	@Autowired
	public OrderRepository orderRepository;

	@Autowired
	@Lazy
	private RestTemplate restTemplate;

	@Bean
    public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static final String ORDER_SERVICE="orderService";

	private static final String BASEURL = "http://localhost:8081/inventory";

	@PostConstruct
    public void init() {
	}

	@GetMapping
    public List<Order> getOrders() {
		return orderRepository.findAll();
	}

	@GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	@GetMapping("/item/{name}")
	@CircuitBreaker(name = ORDER_SERVICE, fallbackMethod = "fallbackMethodForItemName")
    public Item getItem(@PathVariable String name) {
		String url = BASEURL + "/name/" + name;
		return restTemplate.getForObject(url,Item.class);
	}

	public Item fallbackMethodForItemName(String name, Exception e) {
		return new Item(0L,"fallbackItem",0);
	}



	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}

}
