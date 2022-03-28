package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gwent_projet.dto.UserDTO;
import com.example.gwent_projet.models.User;
import com.example.gwent_projet.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public void createUser(UserDTO createUser) {
		User user = new User(0, createUser.username, createUser.email, createUser.password);
		userRepository.save(user);
	}
	
	// WIP ------------------------------------------------------------------------
	
    public User updateUser(User user) {
		return user;
    }

    public void deleteUserById(int id) {
    	
    }
	
	public List<User> getAllUsers() {
		return null;
	}
	
	public User getCardById(int id) {
		return null;
	}
	
	public User saveUser(User user) {
		return user;
	}
	
}
