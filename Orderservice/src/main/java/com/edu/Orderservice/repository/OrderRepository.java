package com.edu.Orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.edu.Orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
