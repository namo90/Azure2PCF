package com.app.Azure2PCF.dto;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<UserDataDto> entityToDtoList(List<UserData> userdata)
	{
		return userdata.stream().map(x->entityToDtoUserDataDto(x)).collect(Collectors.toList());
		
	}
	
	public List<UserData> dtoToEntityList(List<UserDataDto> userdatadto)
	{
		return userdatadto.stream().map(x->dtoToEntityUserData(x)).collect(Collectors.toList());
		
	}

}
