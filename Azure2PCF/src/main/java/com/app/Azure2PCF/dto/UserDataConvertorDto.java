package com.app.Azure2PCF.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.app.Azure2PCF.model.Order;
import com.app.Azure2PCF.model.UserData;

@Component
public class UserDataConvertorDto {
	 @org.springframework.beans.factory.annotation.Autowired(required=true)
	  private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserData dtoToEntityUserData(UserDataDto userdto) {

		UserData userData = new UserData();
		userData.setUsername(userdto.getUsername());
		userData.setPassword(bCryptPasswordEncoder.encode(userdto.getPassword()));
		userData.setOrders(userdto.getOrders());
		return userData;

	}

	public UserDataDto entityToDtoUserDataDto(UserData userData) {
		UserDataDto userdto = new UserDataDto();
		userdto.setUsername(userData.getUsername());
		userdto.setPassword(userData.getPassword());
		userdto.setOrders(userData.getOrders());
		return userdto;

	}
	
	public List<UserDataDto> entityToDtoList(List<UserData> userdata)
	{
		return userdata.stream().map(x->entityToDtoUserDataDto(x)).collect(Collectors.toList());
		
	}
	
	public List<UserData> dtoToEntityList(List<UserDataDto> userdatadto)
	{
		return userdatadto.stream().map(x->dtoToEntityUserData(x)).collect(Collectors.toList());
		
	}
	
	//order 
	
	public Order dtoToEntityOrderData(orderRequestDto dto) {

		Order order = new Order();
		order.setPrice(dto.getPrice());
		order.setProductName(dto.getProductName());
		order.setQty(dto.getQty());
		order.setUi_fk(dto.getUi_fk());
		return order;

	}
	public orderRequestDto entityToDtoOrderDataDto(Order order) {
		orderRequestDto dto = new orderRequestDto();
		dto.setId(order.getId());
		dto.setPrice(order.getPrice());
		dto.setProductName(order.getProductName());
		dto.setQty(order.getQty());
		dto.setUi_fk(order.getUi_fk());
		return dto;

	}
	public Order dtoToEntityOrderForUpdate(orderRequestDto dto) {

		Order order = new Order();
		order.setId(dto.getId());
		order.setPrice(dto.getPrice());
		order.setProductName(dto.getProductName());
		order.setQty(dto.getQty());
		order.setUi_fk(dto.getUi_fk());
		return order;

	}

}
