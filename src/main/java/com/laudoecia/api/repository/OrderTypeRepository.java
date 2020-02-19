package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laudoecia.api.domain.OrderType;

@Repository
public interface OrderTypeRepository extends JpaRepository<OrderType, Long>{
	OrderType getByName(String name);
}
