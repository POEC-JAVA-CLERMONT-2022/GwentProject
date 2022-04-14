package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.dto.UserDTO;
import com.example.gwent_projet.entity.User;

@Service
public interface UserService {

	UserDTO createUser(User createUser);

	List<UserDTO> getAllUsers();

	UserDTO getUserById(Long id);

	UserDTO updateUser(Long id, User user);

	void deleteUserById(Long id);

}
