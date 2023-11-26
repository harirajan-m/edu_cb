package com.edu.Inventoryservice;

import com.edu.Inventoryservice.model.Item;
import com.edu.Inventoryservice.repository.ItemRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/inventory")
public class InventoryserviceApplication {

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void initItems() {
		itemRepository.saveAll(Stream.of(
				new Item(1L, "Ball", 0),
				new Item(2L, "Bat", 20),
				new Item(3L, "Stump", 30)
		).collect(Collectors.toList()));
	}

	@GetMapping
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	@GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) {
		return itemRepository.findById(id).get();
	}

	@GetMapping("/name/{name}")
	public Item getItemByName(@PathVariable String name) {
		return itemRepository.findByItemName(name);
	}

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
	}

}
