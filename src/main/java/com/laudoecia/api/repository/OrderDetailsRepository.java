package com.laudoecia.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laudoecia.api.domain.OrderDetails;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{

}
