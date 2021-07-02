package com.app.Azure2PCF.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Azure2PCF.model.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {

}
