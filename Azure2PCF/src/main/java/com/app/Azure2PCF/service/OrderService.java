package com.app.Azure2PCF.service;

import java.util.List;
import java.util.Optional;

import com.app.Azure2PCF.dto.orderRequestDto;
import com.app.Azure2PCF.dto.orderResponseDto;
import com.app.Azure2PCF.model.Order;

public interface OrderService {
	public Order saveOrder(Order order) ;
	public List<Order> getAllOrders();
	/* public List<orderResponseDto> getJoinInfo(); */
	public Optional<Order> getOrderRecord(int userid);
	public void deleteRecord(int id);
	public Order updateRecord(Order order);
	public List<Order>getAllOrderRecords(int id);

}
