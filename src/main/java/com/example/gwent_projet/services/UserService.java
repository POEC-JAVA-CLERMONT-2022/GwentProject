package com.example.gwent_projet.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.gwent_projet.services.dto.user.UserCreationDTO;
import com.example.gwent_projet.services.dto.user.UserDTO;

@Service
public interface UserService {

	UserDTO createUser(UserCreationDTO createUser);

	List<UserDTO> getAllUsers();

	UserDTO getUserById(Long id);

	UserDTO updateUser(Long id, UserCreationDTO user);

	void deleteUserById(Long id);

}
