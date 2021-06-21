package com.app.Azure2PCF.service;

import com.app.Azure2PCF.dto.UserDataDto;
import com.app.Azure2PCF.model.UserData;

public interface UserDataService {
	public UserData save(UserData userdata);
	public String tokenGenerateForUser(UserDataDto userdto) throws Exception;

}
