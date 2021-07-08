package com.app.Azure2PCF;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.Azure2PCF.model.Order;
import com.app.Azure2PCF.repository.OrderRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
 public class OrderPlaceTests {
	@Autowired
	OrderRepository repo;
	@Test
	@Rollback(false)
public	 void testCreateOrder() {
		Order order=new Order(70, "rose",1,200,21);
	Order saveOrder=	repo.save(order);
		assertNotNull(saveOrder);
	}
	
	@Test
public	void testFindOrderByIdExit() {
		int id=63;
	Optional<Order> orders=	repo.findById(id);
	assertNotNull(orders);
	}
	
	public	void testFindOrderByIdNotExit() {
		int id=70;
	Optional<Order> orders=	repo.findById(id);
	assertNull(orders);
	}
	
	@Test
	public void testUpdateOrder() {
		Order order=new Order();
		order.setId(63);
		order.setProductName("Mango");
		order.setPrice(230);
		order.setQty(10);
		order.setUi_fk(21);
	Order orders=	repo.save(order);
		assertNotNull(orders);
	}

	@Test
	public void testListOrders() {
	List<Order> order=	repo.findAll();
	for(Order o:order) {
		System.out.println(o.getId()+" "+" "+o.getProductName() +" "+o.getPrice() +" "+ o.getQty()+" "+ o.getUi_fk());
		
	}
	assertNotNull(order);
	}
	
	
	  @Test public void testDeleteOrder() {
	  int id =63;
boolean isExitBeforeDelete	 = repo.findById(id).isPresent();
repo.deleteById(id);
boolean notExitAfterDelete	 = repo.findById(id).isPresent();
assertTrue(isExitBeforeDelete);
assertTrue(notExitAfterDelete);

	  }
	 
}
