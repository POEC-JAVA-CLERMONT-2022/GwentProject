package com.example.gwent_projet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gwent_projet.dto.CreateUser;
import com.example.gwent_projet.models.User;
import com.example.gwent_projet.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void createUser(CreateUser createUser) {
		User user = new User(0, createUser.username, createUser.email, createUser.password);
		userRepository.save(user);
	}
}
