
package com.app.Azure2PCF.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.Azure2PCF.dto.UserDataConvertorDto;
import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.model.UserData;
import com.app.Azure2PCF.service.UserDataServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CheckJWTValidate {

	/*
	 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	 * 
	 * String encodedPassword =
	 * bCryptPasswordEncoder.encode(userRegistrationObject.getPassword());
	 */
	@Autowired
	UserDataConvertorDto userDataConvertorDto;
	@Autowired
	UserDataServiceImpl userDataServiceImpl;

	@GetMapping("/getallusers")
	public List<UserDataDto> findAll() {
		List<UserData> userdata = userDataServiceImpl.getAllUsers();
		return userDataConvertorDto.entityToDtoList(userdata);

	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String Welocme() {
		String text = "You are not allow this page";
		text += "I am login page to welcome your home";
		return text;
	}

	@RequestMapping("/login")
	public String Welocme1() {
		String text = "Welcome";
		text += "\n You are in Login Page";
		return text;
	}
}
