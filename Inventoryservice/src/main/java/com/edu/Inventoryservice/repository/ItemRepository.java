package com.edu.Inventoryservice.repository;

import com.edu.Inventoryservice.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
    Item findByItemName(String name);
}
