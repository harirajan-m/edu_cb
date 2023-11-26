package com.edu.Inventoryservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Item {
    @Id
    Long itemId;
    String itemName;
    int quantity;

    public Item() {

    }

    public Item(long l, String bat, int i) {
        this.itemId = l;
        this.itemName = bat;
        this.quantity = i;
    }
}
