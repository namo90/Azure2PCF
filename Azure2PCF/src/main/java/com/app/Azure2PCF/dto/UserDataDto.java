package com.app.Azure2PCF.dto;

import java.util.ArrayList;
import java.util.List;

import com.app.Azure2PCF.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDto {

	public String username;
	public String password;
	
	public String email;
	public String text;
	public String subject;
	private List<Order>orders=new ArrayList<>();
}
