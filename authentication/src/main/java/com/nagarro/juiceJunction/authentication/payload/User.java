/**
 * 
 */
package com.nagarro.juiceJunction.authentication.payload;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String email;
	private String password;
	private String name;
	private Set<Role> roles = new HashSet<>();

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
}
