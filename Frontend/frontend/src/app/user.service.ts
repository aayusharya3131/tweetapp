import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../environments/environment';
import { Register } from './login/register';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  // baseUrl = `http://localhost:8090/api/v1.0/tweets`
  constructor(private httpClient:HttpClient) { }

  loginUser(user:Register){
   return this.httpClient.post(environment.baseUrl + '/login', user);
  }

  getAllUsers(){
    return this.httpClient.get(environment.baseUrl  + '/users/all');
  }

  resetPassword(resetForm:any){
    let body = {
   userName : resetForm.value.userName,
   newPassword : resetForm.value.password
    }
    console.log(body)
    return this.httpClient.put(environment.baseUrl+ '/'+resetForm.value.userName+'/forgot',body);
  }

  registerUser(signupForm){
    let body={
      firstName:signupForm.value.firstName,
      lastName:signupForm.value.lastName,
      email:signupForm.value.email,
      userName:signupForm.value.userName,
      password:signupForm.value.password,
      contactNum:signupForm.value.contactNum
    }
    return this.httpClient.post(environment.baseUrl + "/register",body);
  }
}
