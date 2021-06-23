package com.app.Azure2PCF.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.app.Azure2PCF.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, String> {

	//Optional findById(String username);

}
