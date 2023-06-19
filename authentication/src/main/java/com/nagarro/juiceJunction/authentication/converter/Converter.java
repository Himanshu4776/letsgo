package com.nagarro.juiceJunction.authentication.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.nagarro.juiceJunction.authentication.entity.RoleEntity;
import com.nagarro.juiceJunction.authentication.entity.UserEntity;
import com.nagarro.juiceJunction.authentication.payload.Role;
import com.nagarro.juiceJunction.authentication.payload.User;

@Component
public class Converter {

	public UserEntity convertToUserEntity(User user) {
		Set<RoleEntity> roleEntity=convertToSetOfRoleEntity(user.getRoles());
		UserEntity userEntity=new UserEntity(user.getEmail(),user.getPassword(),user.getName(),roleEntity);
		return userEntity;
	}

	public User convertToUser(UserEntity userEntity) {
		Set<Role> roles=convertToSetOfRole(userEntity.getRoleEntities());
		User user=new User(userEntity.getEmail(), userEntity.getPassword(),userEntity.getName(),roles);
		return user;
	}
	
	public RoleEntity convertToRoleEntity(Role role) {
		
		return new RoleEntity(role.getId(), role.getName());
	}
	
	public Role convertToRole(RoleEntity roleEntity) {
		
		return new Role(roleEntity.getId(), roleEntity.getName());
	}

	public Set<Role> convertToSetOfRole(Set<RoleEntity> roleEntities){
		Set<Role> roles=new HashSet<>();
		roleEntities.forEach((roleEntity) ->{roles.add(new Role(roleEntity.getId(),roleEntity.getName()));});
		return roles;
	}
	
	public Set<RoleEntity> convertToSetOfRoleEntity(Set<Role> roles){
		Set<RoleEntity> roleEntity=new HashSet<>();
		roles.forEach((role) ->{roleEntity.add(new RoleEntity(role.getId(),role.getName()));});
		return roleEntity;
	}
}
