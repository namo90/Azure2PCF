package com.app.Azure2PCF.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.Azure2PCF.dto.orderResponseDto;
import com.app.Azure2PCF.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	/*
	 * @Query("SELECT new com.app.Azure2PCF.dto.orderResponse(c.name , p.productName) FROM CustomerOrder c JOIN c.ProductOrder p"
	 * ) public List<orderResponseDto> getJoinInformation();
	 */
	@Query("from Order o where lower(o.ui_fk)=lower(:userId)")
	public List<Order> selectAllRecord(@Param("userId") int id);
}
