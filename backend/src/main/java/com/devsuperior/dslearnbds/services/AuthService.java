package com.devsuperior.dslearnbds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;
import com.devsuperior.dslearnbds.services.exceptions.ForbiddenException;
import com.devsuperior.dslearnbds.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User authenticated() { 
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName(); // (pega usuario logado)chamada estatica pega o usuario que ja foi reconhecido pelo spring security
			return userRepository.findByEmail(username);
		}
		catch(Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}

	public void validateSelfOrAdmin(Long userid) {
		User user = authenticated();
		if (!user.getId().equals(userid) && !user.hasHole("ROLE_ADMIN")) {
			throw new ForbiddenException("Acess denied");
		}
	}
	
}
