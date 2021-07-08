package com.app.Azure2PCF;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.model.UserData;
import com.app.Azure2PCF.repository.OrderRepository;
import com.app.Azure2PCF.repository.UserRepository;
import com.app.Azure2PCF.service.UserDataServiceImpl;

@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserTests {
	@Autowired
	UserRepository repo;
	
	UserDataServiceImpl userDataService;
	@Qualifier("userDataService")
	@Test
	@Rollback(false)
	public void getValidToken() throws Exception {
		UserDataDto data =new UserDataDto();
		String name="om";
		String password="12345";
		data.setUsername(name);
		data.setPassword(password);
		String token=	userDataService.tokenGenerateForUser(data);
		System.out.println("geting token"+" "+token);
	}
}
