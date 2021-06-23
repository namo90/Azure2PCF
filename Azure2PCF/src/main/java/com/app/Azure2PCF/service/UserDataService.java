package com.app.Azure2PCF.service;

import java.util.List;

import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.model.UserData;

public interface UserDataService {
	public UserData save(UserData userdata);
	public String tokenGenerateForUser(UserDataDto userdto) throws Exception;
public List<UserData> getAllUsers();
}
