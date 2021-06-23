package com.app.Azure2PCF.service;

import com.app.Azure2PCF.dto.UserDataDto;

public interface SendEmailService {
	
	public void sendEmail(UserDataDto dto);

}
