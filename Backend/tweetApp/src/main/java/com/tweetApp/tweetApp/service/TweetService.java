package com.tweetApp.tweetApp.service;

import java.util.List;

import com.tweetApp.tweetApp.DTO.ReplyDTO;
import com.tweetApp.tweetApp.DTO.TweetDTO;
import com.tweetApp.tweetApp.DTO.UpdateTweetDTO;
import com.tweetApp.tweetApp.model.Tweet;

public interface TweetService {

boolean postNewTweet(String userName, TweetDTO tweetDto);
	
	List<Tweet> getAllTweet();
	
	List<Tweet> getUserTweet(String userName);
	
	boolean updateTweet(String id,UpdateTweetDTO updateTweetDto); 
	
	boolean replyTweet(String userName,String id,ReplyDTO replyDto);
	
	void likeTweet(String id);
	
	void unLikeTweet(String id);
	
	boolean deleteTweet(String id);
}
