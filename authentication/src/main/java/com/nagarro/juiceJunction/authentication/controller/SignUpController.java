package com.nagarro.juiceJunction.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.juiceJunction.authentication.payload.User;
import com.nagarro.juiceJunction.authentication.service.UserService;

@RestController
@RequestMapping("/user")
public class SignUpController {
	
	@Autowired
	@Qualifier("UserServiceImpl")
	UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<Boolean> createUser(@RequestBody User user){
		HttpStatus status=HttpStatus.ALREADY_REPORTED;
		boolean userRegisterStatus=userService.addNewUser(user);
		if(userRegisterStatus) {
			status=HttpStatus.OK;
		}
		
		return new ResponseEntity<Boolean>(userRegisterStatus,status);
	}
}
