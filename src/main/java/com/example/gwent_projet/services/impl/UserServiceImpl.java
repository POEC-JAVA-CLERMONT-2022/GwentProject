package com.example.gwent_projet.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.dto.UserDTO;
import com.example.gwent_projet.models.User;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceImpl (UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public void createUser(UserDTO createUser) {
		User user = new User(0, createUser.username, createUser.email, createUser.password);
		userRepository.save(user);
	}
	
	// WIP ------------------------------------------------------------------------
	
	@Override
    public User updateUser(User user) {
		return user;
    }

    @Override
    public void deleteUserById(UUID id) {
    	
    }
	
    @Override
	public User getUserById(int id) {
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	
}
