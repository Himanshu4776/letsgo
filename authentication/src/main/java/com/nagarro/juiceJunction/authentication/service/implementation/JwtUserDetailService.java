/**
 * 
 */
package com.nagarro.juiceJunction.authentication.service.implementation;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nagarro.juiceJunction.authentication.converter.Converter;
import com.nagarro.juiceJunction.authentication.entity.UserEntity;
import com.nagarro.juiceJunction.authentication.repository.UserRepository;

@Service
public class JwtUserDetailService implements UserDetailsService {
	@Autowired
	Converter converter;

	@Autowired
	UserRepository userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userDao.findByEmail(username);
		if (userEntity == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		Set<SimpleGrantedAuthority> authorities = userEntity.getRoleEntities().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
		UserDetails user = new org.springframework.security.core.userdetails.User(username, userEntity.getPassword(), authorities);
		
		return user;
	}

}
