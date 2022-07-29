import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { TweetService } from './tweetservice';

@Component({
  selector: 'app-tweet',
  templateUrl: './tweet.component.html',
  styleUrls: ['./tweet.component.css']
})
export class TweetComponent implements OnInit {

  postTweetForm:FormGroup;
  tweets:any;
  reply: Array<boolean> = [false];
  replyDesc: String;
  likeStatus: Array<boolean> = [false];
  userName:string = ''
  postFlag:boolean = false;

  constructor(private tweetService:TweetService) {
    this.replyDesc= '';
   }

  ngOnInit(): void {
    this.userName = sessionStorage.getItem('userName');
    sessionStorage.setItem('twtflag',"alltweet");
    this.postTweetForm = new FormGroup({
      'userName': new FormControl(sessionStorage.getItem('userName'),[Validators.required]),
      'tweetDes': new FormControl('',[Validators.required,Validators.maxLength(144)]),
    })
    this.tweetService.getAllTweets().subscribe((response:any)=>{
      this.tweets=response;
      this.dateConv(this.tweets);
      this.tweets.forEach(tweet => {
        this.replydateConv(tweet.replyList);
      });
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

  post(){
    this.postFlag=true;
  }

  posttweet(postTweetForm) {
    this.tweetService.postNewTweet(postTweetForm).subscribe((response: any) => {
      this.ngOnInit();
    })
  }

  postcomment(index: any, i: any) {
    const request = ({
      userName: sessionStorage.getItem('userName'),
      tweetId: index,
      replyDesc: this.replyDesc
    });
    this.reply[i] = false;
    this.tweetService.replyTweet(request).subscribe((response: any) => {
      this.ngOnInit();
    })
  }

  likeTweet(id){
    this.tweetService.likeTweet(id).subscribe();
    this.likeStatus[id] = true;
    this.ngOnInit();
     }

    
  unlike(id: any) {
    this.tweetService.unLikeTweet(id).subscribe();
    this.likeStatus[id] = false;
    this.ngOnInit();
     }
 

}
