package com.tweetApp.tweetApp.serviceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tweetApp.tweetApp.DTO.RegisterDTO;
import com.tweetApp.tweetApp.DTO.ResetPasswordDTO;
import com.tweetApp.tweetApp.model.Register;
import com.tweetApp.tweetApp.repository.UserRepository;
import com.tweetApp.tweetApp.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	//@Autowired
	//ModelMapper modelmapper;
	
	@Override
	public RegisterDTO newRegistration(RegisterDTO registerdto) {
		if(checkUnique(registerdto.getEmail(),registerdto.getUserName())) {
			registerdto.setStatusMsg("EmailID / LoginID already exists");
			return registerdto;
		}
		userRepository.save(convertDTOToEntity(registerdto));
		registerdto.setStatusMsg("Registration successfull");
		return registerdto;
	}

	private Register convertDTOToEntity(RegisterDTO registerdto) {
		//Register register =  new Register();
		ModelMapper modelmapper= new ModelMapper();
		Register register = modelmapper.map(registerdto,Register.class);
		return register;
	}

	private boolean checkUnique(String email, String userName) {
		System.out.println(userName);
		boolean status = false;
		Register register = null;
		Register registeremail = null;
		if(!userName.isEmpty() && userName != null) {
			register = userRepository.findByUserName(userName);
			if(!email.isEmpty() && email != null) {
				registeremail = userRepository.findByEmail(email);
			}
		}
		if(register != null || registeremail != null) {
			status = true;
		}
		return status;
	}

	@Override
	public RegisterDTO loginUser(RegisterDTO registerdto) {
		Register reg = userRepository.findByUserName(registerdto.getUserName());
		System.out.println(reg);
		if(reg != null) {
			if(registerdto.getPassword().equals(reg.getPassword())) {
				registerdto.setStatusMsg("Login Successful");
			} else {
				registerdto.setStatusMsg("Incorrect username/ password");
			}
		}
		else {
			registerdto.setStatusMsg("User not found.Please signup");
		}
		System.out.println(registerdto.toString());
		return registerdto;
	}

	@Override
	public boolean resetPassword(String userName, ResetPasswordDTO resetPasswordDTO) {
		System.out.println(userName);
		boolean status = false;
		Register temp = userRepository.findByUserName(userName);
		Register register = temp;
		register.setPassword(resetPasswordDTO.getNewPassword());
		userRepository.save(register);
		status=true;
		return status;
	}

	@Override
	public List<Register> getAllUsers() {
		return (List<Register>) userRepository.findAll();
	}

}
