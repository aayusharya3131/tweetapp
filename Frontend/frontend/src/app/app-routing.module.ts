import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { MyTweetComponent } from './my-tweet/my-tweet.component';
import { SignupComponent } from './signup/signup.component';
import { TweetComponent } from './tweet/tweet.component';
import { WelcomeComponent } from './welcome/welcome.component';


const routes: Routes = [
  {path:"login",component:LoginComponent},
  {path:"",component:WelcomeComponent},
  {path:"header",component:HeaderComponent},
  {path:"tweet",component:TweetComponent},
  {path:"mytweet",component:MyTweetComponent},
  {path:"signup",component:SignupComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
