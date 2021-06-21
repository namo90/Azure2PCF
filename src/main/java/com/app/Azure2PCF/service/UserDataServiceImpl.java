package com.app.Azure2PCF.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
		UserData userData = userRepository
				.save(new UserData(userdata.getUsername(), bCryptPasswordEncoder.encode(userdata.getPassword())));

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
			throw new Exception("Bad Credential Exception");
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw new Exception("Bad Credential Exception");
		}

		// fine are go a head
		UserDetails userDetails = this.customuserdetailsservice.loadUserByUsername(userdto.getUsername());
		System.out.println("Hello token generater-1");
		String token = this.jwtutil.generateToken(userDetails);
		System.out.println("Hello token generater-2");
		System.out.println(token);
		return token;

	}

}
