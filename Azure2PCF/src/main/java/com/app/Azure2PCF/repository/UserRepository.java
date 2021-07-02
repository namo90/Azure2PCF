package com.app.Azure2PCF.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.Azure2PCF.model.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, Integer> {
	// and lower(u.password)=lower(:Password)
	//,@Param("Password")String password
	Optional<UserData> findByusername(String username);
	@Query("select u.id from UserData u where lower(u.username)=lower(:userName)")
	String selectRecordByuserId(@Param("userName")String name);

	//Optional findById(String username);

}
