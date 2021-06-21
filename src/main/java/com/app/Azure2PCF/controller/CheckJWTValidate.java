
package com.app.Azure2PCF.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CheckJWTValidate {

	/*
	 * @Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	 * 
	 * String encodedPassword =
	 * bCryptPasswordEncoder.encode(userRegistrationObject.getPassword());
	 */

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
