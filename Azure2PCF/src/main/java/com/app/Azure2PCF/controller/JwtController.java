package com.app.Azure2PCF.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Azure2PCF.config.MailConfiguration;
import com.app.Azure2PCF.dto.UserDataConvertorDto;
import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.helper.JwtUtil;
import com.app.Azure2PCF.model.JwtResponce;
import com.app.Azure2PCF.model.UserData;
import com.app.Azure2PCF.service.CustomUserDetailService;
import com.app.Azure2PCF.service.SendEmailServiceImpl;
import com.app.Azure2PCF.service.UserDataServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@ResponseBody
public class JwtController {

	@org.springframework.beans.factory.annotation.Autowired(required = true)
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	public JavaMailSender emailSender;
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
	@Autowired
	MailConfiguration mailconf;
	@Autowired
	SendEmailServiceImpl sendEmailServiceImpl;

	@PostMapping("/token")
	public ResponseEntity<Object> generateToken(@RequestBody UserDataDto userdto) throws Exception {
		String token = userDataServiceImpl.tokenGenerateForUser(userdto);

		return ResponseEntity.ok(new JwtResponce(token));
	}

	@PostMapping("/save")
	public void registerNewUser(@RequestBody UserDataDto userDataDto) throws Exception {

		UserData userData = userDataConvertorDto.dtoToEntityUserData(userDataDto);
		userDataServiceImpl.save(userData);

	}

	@PostMapping("/sendmail")
	public String sendemail(@RequestBody UserDataDto userDataDto, BindingResult bindingresult) {

		sendEmailServiceImpl.sendEmail(userDataDto);

		return "Email sent Successfully";
	}
}
