package com.nagarro.juiceJunction.authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.juiceJunction.authentication.exception.WrongCredentialException;
import com.nagarro.juiceJunction.authentication.payload.JwtAuthResponse;
import com.nagarro.juiceJunction.authentication.payload.User;
import com.nagarro.juiceJunction.authentication.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class AuthController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody User request) throws WrongCredentialException{
		this.authenticate(request.getEmail(), request.getPassword());
		String token = userService.generateToken(request.getEmail());
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setToken(token);
		return new ResponseEntity<>(jwtAuthResponse,HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws WrongCredentialException {
		UsernamePasswordAuthenticationToken autenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(autenticationToken);
		}catch(BadCredentialsException e) {
			throw new WrongCredentialException("Wrong username or password!!");
		}
	}
	@GetMapping("validate")
	public ResponseEntity<String> validateToken(@RequestParam("token") String token){
		userService.validateToken(token);
		return ResponseEntity.ok("token valid");
	}
}
