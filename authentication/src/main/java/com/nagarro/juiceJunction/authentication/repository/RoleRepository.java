/**
 * 
 */
package com.nagarro.juiceJunction.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.juiceJunction.authentication.entity.RoleEntity;

/**
 * @author palak
 *
 */
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{

}
