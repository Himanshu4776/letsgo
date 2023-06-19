package com.nagarro.juiceJunction.authentication.service;

import com.nagarro.juiceJunction.authentication.payload.User;

public interface UserService {

	boolean addNewUser(User user);
	String generateToken(String username);
	void validateToken(String token);
}
