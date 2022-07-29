import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TweetService {

  constructor(private httpClient:HttpClient) { }

  //done
  postNewTweet(postTweetForm){
    let body={
      userName: postTweetForm.value.userName,
      tweetDes: postTweetForm.value.tweetDes,
      recActive: "y"
    }
    return this.httpClient.post(environment.baseUrl + "/" + postTweetForm.value.userName + "/add", body);
  }

  //done
  getAllTweets(){
    return this.httpClient.get(environment.baseUrl + "/all");
  }

  
  replyTweet(request){
    return this.httpClient.put(environment.baseUrl + request.userName + "/reply/" + request.tweetId, request);
  }

  //done
  getUserTweet(){
    return this.httpClient.get(environment.baseUrl + "/username", {params:{userName: sessionStorage.getItem('userName')}});
  }

  //done
  deleteTweet(id){
    return this.httpClient.delete(environment.baseUrl + sessionStorage.getItem('userName') + "/delete/" + id);
  }

  //done
  likeTweet(id){
     return this.httpClient.put(environment.baseUrl + "/like/" + id,sessionStorage.getItem('userName'));
  }

  //done
  unLikeTweet(id){
    return this.httpClient.put(environment.baseUrl + "/unLike/" + id,sessionStorage.getItem('userName'));
 }

 //done
 updateTweet(updateTweet){
  return this.httpClient.put(environment.baseUrl + "/update/" + updateTweet.value.id,updateTweet.value);
 }
}
