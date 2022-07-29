import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  forgotForm: FormGroup;
  loginStaus:string = null;
  resetFlag:string = '';
  constructor(private userService:UserService,private router:Router) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      'userName': new FormControl('',[Validators.required]),
      'password': new FormControl('',[Validators.required])
    })
    this.forgotForm = new FormGroup({
      'userName': new FormControl('',[Validators.required]),
      'password': new FormControl('',[Validators.required]),
      'confirmPassword': new FormControl('',[Validators.required])
    })
    this.resetFlag='';
  }
  
  loginSubmit(loginForm:any){
    this.userService.loginUser(loginForm.value).subscribe((response:any)=>{
      this.loginStaus = response.statusMsg;
      if(response.statusMsg=="Login Successful"){
        sessionStorage.setItem('userName', response.userName);
        this.router.navigate(['/tweet'])
      }
    },
    (error) => {
      console.log("error");
      console.log(error);
    })
  }

  resetPassword(forgotForm){
    this.userService.resetPassword(forgotForm).subscribe(
      (response:any)=>{
       if(!response){
         this.resetFlag = 'true';
       }
       else{
        this.resetFlag = 'false';
       }
    })
  }

}
