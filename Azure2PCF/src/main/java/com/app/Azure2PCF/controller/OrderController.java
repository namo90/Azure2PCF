package com.app.Azure2PCF.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.Azure2PCF.dto.UserDataConvertorDto;
import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.dto.orderRequestDto;
import com.app.Azure2PCF.dto.orderResponseDto;
import com.app.Azure2PCF.model.JwtResponce;
import com.app.Azure2PCF.model.Order;
import com.app.Azure2PCF.model.UserData;
import com.app.Azure2PCF.service.OrderServiceImpl;
import com.app.Azure2PCF.service.SendEmailServiceImpl;
import com.app.Azure2PCF.service.UserDataServiceImpl;

@RestController
@SessionAttributes("name")
public class OrderController {
	@Autowired
	OrderServiceImpl customerOrderServiceImpl;
	@Autowired
	UserDataConvertorDto userDataConvertorDto;
	@Autowired
	UserDataServiceImpl userDataServiceImpl;
	@Autowired
	SendEmailServiceImpl sendEmailServiceImpl;

	@PostMapping("/save")
	/*
	 * public ResponseEntity<Object> registerNewUser(@RequestBody UserDataDto
	 * userDataDto,HttpSession session) throws Exception {
	 * System.out.println("printing values of save" + "----" + userDataDto); String
	 * userid=(String)session.getAttribute("userid");
	 * 
	 * UserData userData = userDataConvertorDto.dtoToEntityUserData(userDataDto);
	 * 
	 * System.out.println("printing values of save" + "----" + userData); UserData u
	 * = userDataServiceImpl.save(userData); UserDataDto userDataDto1 =
	 * userDataConvertorDto.entityToDtoUserDataDto(u); return
	 * ResponseEntity.ok(userDataDto1); }
	 */
	
	public ResponseEntity<Object> registerNewUser(@RequestBody orderRequestDto orderDto,HttpSession session) throws Exception {
		
		String userid=(String)session.getAttribute("userid");
		//int user_id=Integer.valueOf(userid);
		orderDto.setUi_fk(Integer.valueOf(userid));
		
		var order = userDataConvertorDto.dtoToEntityOrderData(orderDto);
		
		var u = customerOrderServiceImpl.saveOrder(order);
		orderRequestDto orderDtoVal = userDataConvertorDto.entityToDtoOrderDataDto(u);
		UserDataDto userDataDto=new UserDataDto();
		userDataDto.setEmail("namo.shelke95@gmail.com");
		userDataDto.setText("Hello its sending first mail");
		userDataDto.setSubject("Trial Mail");
		userDataDto.setUsername("namo");
		sendEmailServiceImpl.sendEmail(userDataDto);
		return ResponseEntity.ok(orderDtoVal);
		
	}

	@PostMapping("/placeOrder")
	public Order placeOrder(@RequestBody orderRequestDto dto) {

		Order order = userDataConvertorDto.dtoToEntityOrderData(dto);
		return customerOrderServiceImpl.saveOrder(order);
	}

	@GetMapping("/findAllOrders")
	public List<Order> findAllOrders() {
	
		return customerOrderServiceImpl.getAllOrders();
	}

	@GetMapping("/findByOrder")
	public Optional<Order> getRecord(@RequestParam("id") int uId) {
		System.out.println("======= "+uId);
		return customerOrderServiceImpl.getOrderRecord(uId);

	}
	
	@GetMapping("/findByUser")
	public ResponseEntity<Object> getAllrecords(@RequestParam("id") int uId) {
		System.out.println("======= "+uId);
	List<Order> ListOfUserOrder	=customerOrderServiceImpl.getAllOrderRecords(uId);
	return ResponseEntity.ok(ListOfUserOrder);

	}

	@DeleteMapping("/deleterecord")
	public String deleteOrder(@RequestParam("id") int id) {

		Optional<Order> orderid = customerOrderServiceImpl.getOrderRecord(id);
		if (orderid.isPresent()) {
			customerOrderServiceImpl.deleteRecord(id);
			return "Order deleted with id " + id;
		} else {
			throw new RuntimeException("Order not found for the id" + id);
		}
	}

	@PutMapping("/updateorder")
	public Order updateOrder(@RequestBody orderRequestDto dto) {
		Order updateorder = userDataConvertorDto.dtoToEntityOrderForUpdate(dto);
		return customerOrderServiceImpl.updateRecord(updateorder);
	}


}
