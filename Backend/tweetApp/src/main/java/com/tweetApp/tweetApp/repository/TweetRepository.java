package com.tweetApp.tweetApp.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.tweetApp.tweetApp.model.Tweet;

@EnableScan
@Repository
@Component
public interface TweetRepository extends CrudRepository<Tweet, String>{

	List<Tweet> findByRecActive(String status);
		
//	List<Tweet> findByRecActiveOrderByTweetDateDesc(String status);
	
	List<Tweet> findByRecActiveAndUserName(String status,String userName);
	
//	List<Tweet> findByRecActiveAndUserNameOrderByTweetDateDesc(String status,String userName);
	
}
