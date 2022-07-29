import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TweetService } from '../tweet/tweetservice';

@Component({
  selector: 'app-my-tweet',
  templateUrl: './my-tweet.component.html',
  styleUrls: ['./my-tweet.component.css']
})
export class MyTweetComponent implements OnInit {

  tweet:any;
  updateForm:FormGroup;
  msg:string;
  replyFlag:Array<boolean> = [false];
  updateStatus:Array<boolean> = [false];

  constructor(private tweetService:TweetService,private router:Router) { }

  ngOnInit(): void {
    sessionStorage.setItem('twtflag',"mytweet");
    this.tweetService.getUserTweet().subscribe((response)=>{
      this.tweet=response;
      this.dateConv(this.tweet);
      this.tweet.forEach(twt => {
        this.replydateConv(twt.replyList);
      });
    })
   
   this.updateForm = new FormGroup({
      'id' : new FormControl(''),
      'message' : new FormControl('',[Validators.required])
    })
  }

  dateConv(tweets){
    if(tweets){
    tweets.forEach(tweet => {
      var tdate = new Date(tweet.tweetDate).getTime();
      var date = new Date().getTime();
      var time = date-tdate;
      var mins = Math.floor(time / 60000);
      var hrs = Math.floor(mins / 60);
      var days = Math.floor(hrs / 24);
      var yrs = Math.floor(days / 365);
      if (mins < 60) {
        tweet.tweetDate = mins + ' mins ago';
      }
      else if (mins > 60 && hrs <= 24) {
        tweet.tweetDate = hrs + ' hours ago';
      }
      else {
        tweet.tweetDate = days + ' days ago';
      }
    });
  }
  }

  replydateConv(replyList){
    if(replyList){
    replyList.forEach(reply => {
      var tdate = new Date(reply.date).getTime();
      var date = new Date().getTime();
      var time = date-tdate;
      var mins = Math.floor(time / 60000);
      var hrs = Math.floor(mins / 60);
      var days = Math.floor(hrs / 24);
      var yrs = Math.floor(days / 365);
      if (mins < 60) {
        reply.date = mins + ' mins ago';
      }
      else if (mins > 60 && hrs <= 24) {
        reply.date = hrs + ' hours ago';
      }
      else {
        reply.date = days + ' days ago';
      }
    });
  }
  }

  delete(id){
    this.tweetService.deleteTweet(id).subscribe((response)=>{
      if(response){
        this.ngOnInit();
      }
    })
  }

  update(id,tweetmessage){
  this.updateStatus[id]=true;
  this.updateForm.patchValue({
    message: tweetmessage
  })
  }

  updateTweet(id,updateForm){
    updateForm.value.id = id;
    this.tweetService.updateTweet(updateForm).subscribe((response:any)=>{
      if(response){
        this.updateStatus[id]=false;
        this.ngOnInit();
      }
    })
  }

  viewReply(id){
    this.replyFlag[id] = true;
  }

  hideReply(id){
    this.replyFlag[id] = false;
  }

}
