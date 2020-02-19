package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
	Order findByOrderUuid(String orderUuid);
}
