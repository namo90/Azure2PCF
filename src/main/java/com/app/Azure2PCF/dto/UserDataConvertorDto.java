package com.app.Azure2PCF.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.app.Azure2PCF.model.UserData;

@Component
public class UserDataConvertorDto {

	public UserData dtoToEntityUserData(UserDataDto userdto) {

		UserData userData = new UserData();
		userData.setUsername(userdto.getUsername());
		userData.setPassword(userdto.getPassword());
		return userData;

	}

	public UserDataDto entityToDtoUserDataDto(UserData userData) {
		UserDataDto userdto = new UserDataDto();
		userdto.setUsername(userData.getUsername());
		userdto.setPassword(userData.getPassword());
		return userdto;

	}

}
