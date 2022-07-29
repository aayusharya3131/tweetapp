package com.tweetApp.tweetApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tweetApp.tweetApp.DTO.ReplyDTO;
import com.tweetApp.tweetApp.DTO.TweetDTO;
import com.tweetApp.tweetApp.DTO.UpdateTweetDTO;
import com.tweetApp.tweetApp.model.Tweet;
import com.tweetApp.tweetApp.service.TweetService;

import io.swagger.annotations.Api;

@Api(tags = { "TweetController" })
@RestController
@RequestMapping(value="/api/v1.0/tweets")
public class TweetController {
	
	@Autowired
	TweetService tweetService;
	
	@PostMapping("/{username}/add")
	public boolean postNewTweet(@PathVariable String username,@RequestBody TweetDTO tweetDTO) {
		boolean status = tweetService.postNewTweet(username, tweetDTO);
		return status;
	}
	
	@GetMapping("/all")
	public List<Tweet> getAllTweet() {
		return tweetService.getAllTweet();
	}

	@GetMapping("/username")
	public List<Tweet> getUserTweet(@RequestParam("userName") String userName) {
		return tweetService.getUserTweet(userName);
	}
	
	@PutMapping("/update/{id}")
	public boolean updateTweet(@PathVariable String id,@RequestBody UpdateTweetDTO updateTweetDto) {
		return tweetService.updateTweet(id, updateTweetDto);
	}
	
	@PutMapping("/{username}/reply/{id}")
	public boolean replyTweet(@PathVariable String username,@PathVariable String id,@RequestBody ReplyDTO replyDto) {
		return tweetService.replyTweet(username, id, replyDto);
	}
	
	@PutMapping("/like/{id}")
	public void likeTweet(@PathVariable String id,@RequestBody String userName) {
		 tweetService.likeTweet(id);
	}
	
	@PutMapping("/unLike/{id}")
	public void unLikeTweet(@PathVariable String id,@RequestBody String userName) {
		 tweetService.unLikeTweet(id);
	}
	
	@DeleteMapping("/{username}/delete/{id}")
	public boolean deleteTweet(@PathVariable String username,@PathVariable String id) {
		return tweetService.deleteTweet(id);
	}
}
