package com.example.gwent_projet.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.dto.UserDTO;
import com.example.gwent_projet.models.User;

@Service
public interface UserService {
	

	void createUser(UserDTO createUser);
	
	// WIP ------------------------------------------------------------------------
	
    User updateUser(User user);

    void deleteUserById(UUID id);
	
	List<User> getAllUsers();
	
	User getUserById(int id);
	
}
