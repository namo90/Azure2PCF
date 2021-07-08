package com.app.Azure2PCF.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.app.Azure2PCF.config.MailConfiguration;
import com.app.Azure2PCF.dto.UserDataConvertorDto;
import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.helper.JwtUtil;
import com.app.Azure2PCF.model.JwtResponce;
import com.app.Azure2PCF.model.UserData;
import com.app.Azure2PCF.service.CustomUserDetailService;
import com.app.Azure2PCF.service.SendEmailServiceImpl;
import com.app.Azure2PCF.service.UserDataServiceImpl;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@ResponseBody
@SessionAttributes("name,password")
public class JwtController {

	/*
	 * @org.springframework.beans.factory.annotation.Autowired(required = true)
	 * private BCryptPasswordEncoder bCryptPasswordEncoder;
	 */
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
	public ResponseEntity<Object> generateToken(@RequestBody UserDataDto userdto,ModelMap model,HttpServletRequest request) throws Exception {
		System.out.println(userdto.getUsername()+"-----"+userdto.getPassword());
		String token = userDataServiceImpl.tokenGenerateForUser(userdto);
		if(token !=null) {
			System.out.println("---------inside bec token got-----------");
	String userId	=	userDataServiceImpl.getUserid(userdto.getUsername());
	String id="namo11";
	System.out.println("!!!!!!!!!!!!!!!!!!!!"+userId);
	model.put("name","id");
HttpSession session	=request.getSession();
session.setAttribute("userid", userId);
	System.out.println("******************"+userId);
		}
		/*
		 * model.put("name",userdto.getUsername());
		 * model.put("password",userdto.getPassword());
		 */
System.out.println("getting all values of model"+"-----"+model);
		return ResponseEntity.ok(new JwtResponce(token));
	}

	/*
	 * @PostMapping("/save") public void registerNewUser(@RequestBody UserDataDto
	 * userDataDto) throws Exception {
	 * System.out.println("printing values of save"+"----"+userDataDto);
	 * 
	 * UserData userData = userDataConvertorDto.dtoToEntityUserData(userDataDto);
	 * System.out.println("printing values of save"+"----"+userData);
	 * userDataServiceImpl.save(userData);
	 * 
	 * }
	 */

	@PostMapping("/sendmail")
	public String sendemail(@RequestBody UserDataDto userDataDto, BindingResult bindingresult) {

		sendEmailServiceImpl.sendEmail(userDataDto);

		return "Email sent Successfully";
	}
}
