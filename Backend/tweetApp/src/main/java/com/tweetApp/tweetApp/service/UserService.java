package com.tweetApp.tweetApp.service;

import java.util.List;

import com.tweetApp.tweetApp.DTO.RegisterDTO;
import com.tweetApp.tweetApp.DTO.ResetPasswordDTO;
import com.tweetApp.tweetApp.model.Register;

public interface UserService {

	RegisterDTO newRegistration(RegisterDTO registerdto);
	RegisterDTO loginUser(RegisterDTO registerdto);
	boolean resetPassword(String userName,ResetPasswordDTO resetPasswordDTO);
	List<Register> getAllUsers();
}
