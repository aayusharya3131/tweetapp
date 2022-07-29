import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../user.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  signupForm:FormGroup;
  registerMsg:string = null;

  constructor(private userService:UserService) { }

  ngOnInit(): void {
    this.signupForm = new FormGroup({
      'firstName' : new FormControl('',[Validators.required]),
      'lastName' : new FormControl('',[Validators.required]),
      'email' : new FormControl('',[Validators.required]),
      'userName' : new FormControl('',[Validators.required]),
      'password' : new FormControl('',[Validators.required]),
      'confirmPassword' : new FormControl('',[Validators.required]),
      'contactNum' : new FormControl('',[Validators.required,Validators.maxLength(10),Validators.minLength(10)])
    })
  }

  signupSubmit(signupForm){
    this.userService.registerUser(signupForm).subscribe((response:any)=>{
      this.registerMsg=response.statusMsg;
      if(response.statusMsg=='Registration successfull'){
        this.registerMsg = 'Registration successful. Please Login!'
      }
    })
  }

}
