package com.app.Azure2PCF.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Azure2PCF.dto.UserDataConvertorDto;
import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.helper.JwtUtil;
import com.app.Azure2PCF.model.JwtRequest;
import com.app.Azure2PCF.model.JwtResponce;
import com.app.Azure2PCF.model.UserData;
import com.app.Azure2PCF.service.CustomUserDetailService;
import com.app.Azure2PCF.service.UserDataServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@ResponseBody
public class JwtController {

	@org.springframework.beans.factory.annotation.Autowired(required = true)
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailService customuserdetailsservice;

	@Autowired
	UserDataServiceImpl userDataServiceImpl;

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	UserDataConvertorDto userDataConvertorDto;

//	@RequestMapping(value = "/token", method = RequestMethod.POST)
	@PostMapping("/token")
	public ResponseEntity<Object> generateToken(@RequestBody UserDataDto userdto) throws Exception {
	String token=	userDataServiceImpl.tokenGenerateForUser(userdto);
		/*
		 * System.out.println("Hello Controller");
		 * System.out.println(jwtRequest.toString()); try { System.out.println("Hi");
		 * this.authenticationManager.authenticate( new
		 * UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
		 * jwtRequest.getPassword())); System.out.println("Hi"); } catch
		 * (UsernameNotFoundException user) { user.printStackTrace(); throw new
		 * Exception("Bad Credential Exception"); } catch (BadCredentialsException e) {
		 * e.printStackTrace(); throw new Exception("Bad Credential Exception"); }
		 * 
		 * // fine are go a head UserDetails userDetails =
		 * this.customuserdetailsservice.loadUserByUsername(jwtRequest.getUsername());
		 * System.out.println("Hello token generater-1"); String token =
		 * this.jwtutil.generateToken(userDetails);
		 * System.out.println("Hello token generater-2"); System.out.println(token)
		 */;

		return ResponseEntity.ok(new JwtResponce(token));
	}

	@PostMapping("/save")
	public void registerNewUser(@RequestBody UserDataDto userDataDto) throws Exception {

		UserData userData = userDataConvertorDto.dtoToEntityUserData(userDataDto);
		userDataServiceImpl.save(userData);


	}
}
