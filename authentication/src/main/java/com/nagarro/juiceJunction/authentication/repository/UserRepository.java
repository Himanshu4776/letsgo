/**
 * 
 */
package com.nagarro.juiceJunction.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.juiceJunction.authentication.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
	UserEntity findByEmail(String email);
}
