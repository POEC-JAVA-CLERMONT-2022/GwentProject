package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.entity.User;
import com.example.gwent_projet.services.dto.UserDTO;

@Service
public interface UserService {

	UserDTO createUser(User createUser);

	List<UserDTO> getAllUsers();

	UserDTO getUserById(Long id);

	UserDTO updateUser(Long id, User user);

	void deleteUserById(Long id);

}
