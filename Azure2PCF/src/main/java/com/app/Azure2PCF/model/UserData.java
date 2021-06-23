package com.app.Azure2PCF.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="login")
@Entity

public class UserData {
	
	@Id
	@NonNull
	private String username;
	private String password;

}