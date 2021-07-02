package com.app.Azure2PCF.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.Azure2PCF.customException.EmptyInputException;
import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.helper.JwtUtil;
import com.app.Azure2PCF.model.UserData;
import com.app.Azure2PCF.repository.UserRepository;

@Service

public class UserDataServiceImpl implements UserDataService {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailService customuserdetailsservice;
	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	UserRepository userRepository;
	@org.springframework.beans.factory.annotation.Autowired(required = true)
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserData save(UserData userdata) {
		
		if(userdata.getUsername().isEmpty()|| userdata.getUsername().length()==0) {
			throw new EmptyInputException("500","user is empty input");
		}
		UserData userData =userRepository.save(userdata);
		return userData;
	}

	@Override
	public String tokenGenerateForUser(UserDataDto userdto) throws Exception {
		System.out.println("Hello Controller");
		System.out.println(userdto.toString());
		try {
			System.out.println("Hi");
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(userdto.getUsername(), userdto.getPassword()));
			System.out.println("Hi");
		} catch (UsernameNotFoundException user) {
			user.printStackTrace();
			throw new Exception("username Credential Exception");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credential Exception");
		}

		// fine are go a head
		UserDetails userDetails = this.customuserdetailsservice.loadUserByUsername(userdto.getUsername());
		if (userDetails.getUsername().isEmpty() || userDetails.getUsername().length()==0) {
			throw new EmptyInputException("700", "getting username here empty");
		}
		String token = this.jwtutil.generateToken(userDetails);
		System.out.println("Hello token generater-2");
		System.out.println(token);
		return token;

	}

	@Override
	public List<UserData> getAllUsers() {
		List<UserData> userdata=userRepository.findAll();
		return userdata;
	}

	@Override
	public String getUserid(String name) {
		if (name.isEmpty() || name.length()==0) {
			throw new EmptyInputException("700", "getting username here empty");
		}
		return userRepository.selectRecordByuserId(name);
	}

}
