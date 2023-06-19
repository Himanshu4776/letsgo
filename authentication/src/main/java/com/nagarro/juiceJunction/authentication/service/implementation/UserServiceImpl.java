package com.nagarro.juiceJunction.authentication.service.implementation;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.juiceJunction.authentication.converter.Converter;
import com.nagarro.juiceJunction.authentication.entity.UserEntity;
import com.nagarro.juiceJunction.authentication.payload.Role;
import com.nagarro.juiceJunction.authentication.payload.User;
import com.nagarro.juiceJunction.authentication.repository.RoleRepository;
import com.nagarro.juiceJunction.authentication.repository.UserRepository;
import com.nagarro.juiceJunction.authentication.security.JwtTokenHelper;
import com.nagarro.juiceJunction.authentication.service.UserService;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private Converter converter;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private JwtTokenHelper jwtService;

	public boolean addNewUser(User user) {
		boolean status = false;
		try {

			if (userRepo.findById(user.getEmail()) !=null) {
				if (user.getRoles().isEmpty()) {
					Set<Role> set = new HashSet<Role>();
					set.add(converter.convertToRole(roleRepo.findById(2).get()));
					user.setRoles(set);
				}
				UserEntity userEntity = converter.convertToUserEntity(user);
				userEntity.setPassword(bcryptEncoder.encode(user.getPassword()));
				userRepo.save(userEntity);
				status = true;

			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}

	public void validateToken(String token) {
		jwtService.validateToken(token);
	}
}
