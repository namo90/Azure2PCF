package com.app.Azure2PCF.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailConfiguration {
@Value("${spring.mail.host}")	
private String host;
@Value("${spring.mail.port}")	
private int port;
@Value("${spring.mail.username}")	
private String username;
@Value("${spring.mail.password}")	
private String password;
}
