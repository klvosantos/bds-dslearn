package com.devsuperior.dslearnbds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dslearnbds.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByEmail(String email); // retorna o username no userdatails no spring security
}
