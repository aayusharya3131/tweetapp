package com.tweetApp.tweetApp.repository;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tweetApp.tweetApp.model.Register;

@EnableScan
@Repository
public interface UserRepository extends CrudRepository<Register, Integer>{
	
	Register findByEmail(String email);
	
	Register findByUserName(String userName);

}
