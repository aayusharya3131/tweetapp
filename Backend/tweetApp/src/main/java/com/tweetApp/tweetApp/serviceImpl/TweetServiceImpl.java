package com.tweetApp.tweetApp.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetApp.tweetApp.DTO.ReplyDTO;
import com.tweetApp.tweetApp.DTO.TweetDTO;
import com.tweetApp.tweetApp.DTO.UpdateTweetDTO;
import com.tweetApp.tweetApp.model.Reply;
import com.tweetApp.tweetApp.model.Tweet;
import com.tweetApp.tweetApp.repository.TweetRepository;
import com.tweetApp.tweetApp.service.TweetService;

@Service
public class TweetServiceImpl implements TweetService{

	@Autowired
	TweetRepository tweetRepository;
	
	
	@Override
	public boolean postNewTweet(String userName, TweetDTO tweetDto) {
		boolean status = false;
		ModelMapper modelmapper= new ModelMapper();
		Tweet tweetObj =  modelmapper.map(tweetDto,Tweet.class);
		if(tweetObj.getReplyList() == null) {
			tweetObj.setReplyList(new ArrayList<Reply>());
		}
		List<Tweet> temp = tweetRepository.findByRecActive("y");
//		if(temp.isEmpty()) {
//			tweetObj.setId(1);
//		} else {
//			tweetObj.setId(temp.size() + 1);
//		}
		if(temp.isEmpty()) {
			tweetObj.setId(Integer.toString(1));
		} else {
			tweetObj.setId(Integer.toString(temp.size() + 1));
		}
		tweetObj.setTweetDate(LocalDateTime.now().toString());
		tweetRepository.save(tweetObj);
		status=true;
		return status;
	}

	@Override
	public List<Tweet> getAllTweet() {
		return tweetRepository.findByRecActive("y");
	}

	@Override
	public List<Tweet> getUserTweet(String userName) {
		return tweetRepository.findByRecActiveAndUserName("y", userName);
	}

	@Override
	public boolean updateTweet(String id,UpdateTweetDTO updateTweetDto) {
		boolean status = false;
		Tweet tweetobj = tweetRepository.findById(id).get();
		tweetobj.setTweetDes(updateTweetDto.getMessage());
		tweetobj.setTweetDate(LocalDateTime.now().toString());
		tweetRepository.save(tweetobj);
		status=true;
		return status;
	}

	@Override
	public boolean replyTweet(String userName, String id, ReplyDTO replyDto) {
		boolean status = false;
		Tweet tweetObj = tweetRepository.findById(id).get();
		List<Reply> replyObj = tweetObj.getReplyList();
		Reply tempReply = new Reply();
		tempReply.setUserName(userName);
		tempReply.setReplyDesc(replyDto.getReplyDesc());
		tempReply.setDate(LocalDateTime.now().toString());
		if (replyObj != null)
		replyObj.add(tempReply);
		tweetObj.setReplyList(replyObj);
		tweetRepository.save(tweetObj);
		status = true;
		return status;
	}

	@Override
	public void likeTweet(String id) {
		Tweet tweetObj = tweetRepository.findById(id).get();
		int count = tweetObj.getLike();
		tweetObj.setLike(count+1);
		tweetRepository.save(tweetObj);
	}
	
	@Override
	public void unLikeTweet(String id) {
		Tweet tweetObj = tweetRepository.findById(id).get();
		int count = tweetObj.getLike();
		tweetObj.setLike(count-1);
		tweetRepository.save(tweetObj);
	}

	@Override
	public boolean deleteTweet(String id) {
		boolean status = false;
		tweetRepository.deleteById(id);
		status = true;
		return status;
	}

}
