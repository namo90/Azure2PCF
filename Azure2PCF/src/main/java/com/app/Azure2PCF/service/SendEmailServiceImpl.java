package com.app.Azure2PCF.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.app.Azure2PCF.config.MailConfiguration;
import com.app.Azure2PCF.dto.UserDataDto;

@Service
public class SendEmailServiceImpl implements SendEmailService {

	@Autowired
	MailConfiguration mailconf;

	@Override
	public void sendEmail(UserDataDto userDataDto) {
		// create mail sender
		JavaMailSenderImpl mailsender = new JavaMailSenderImpl();
		mailsender.setHost(this.mailconf.getHost());

		mailsender.setPort(this.mailconf.getPort());

		mailsender.setUsername(this.mailconf.getUsername());

		mailsender.setPassword(this.mailconf.getPassword());

		// create email instance
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(userDataDto.getEmail());
		mailMessage.setTo("rc@feedback.com");
		mailMessage.setSubject(userDataDto.getSubject() + " " + userDataDto.getUsername());
		mailMessage.setText(userDataDto.getText());

		// send mail
		mailsender.send(mailMessage);

	}

}
