package com.tweetApp.tweetApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tweetApp.tweetApp.DTO.RegisterDTO;
import com.tweetApp.tweetApp.DTO.ResetPasswordDTO;
import com.tweetApp.tweetApp.model.Register;
import com.tweetApp.tweetApp.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "UserController" })
@RestController
@RequestMapping(value="/api/v1.0/tweets")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful operation"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized access"),
			@ApiResponse(code = 500, message = "Internal server error") })
	@PostMapping("/register")
	public ResponseEntity<RegisterDTO> newRegistration(@RequestBody RegisterDTO register) {
		register = userService.newRegistration(register);
		return ResponseEntity.ok().body(register);
	}
	
	@PostMapping("/login")
	public RegisterDTO loginUser(@RequestBody RegisterDTO register) {
		System.out.println(register);
		RegisterDTO registerDTO = userService.loginUser(register);
		System.out.println(registerDTO.toString());
		return registerDTO;
	}
	
	@PutMapping("{username}/forgot")
	public boolean resetPassword(@PathVariable String username,@RequestBody ResetPasswordDTO resetPasswordDTO) {
		System.out.println(username);
		return userService.resetPassword(username, resetPasswordDTO);
	}

	@GetMapping("/users/all")
	public List<Register> getAllUsers() {
		return userService.getAllUsers();
	}
}
