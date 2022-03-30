package com.example.gwent_projet.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
	
	// ---------------------------------------------------------------------------------------

	@Override
	public UserDTO createUser(User createUser) {
		userRepository.save(createUser);
		// DTO to be returned, populated with new user values
		UserDTO newUser = new UserDTO(createUser.getUsername(), createUser.getEmail());
		return newUser;
	}
	
	@Override
	public List<UserDTO> getAllUsers() {
		// get the entire list of users from the repo
		List<User> repoList = userRepository.findAll();
		// new, empty list of UserDTOs
		List<UserDTO> returnList = new ArrayList<>();
		
		for (int sweeper = 0; sweeper < repoList.size(); sweeper++) {
			UserDTO returnUserDTO  = new UserDTO(null, null);
			User repoUser;
			// get user at index
			repoUser = repoList.get(sweeper);
			// populate DTO values with retrieved user values
			returnUserDTO.username = repoUser.getUsername();
			returnUserDTO.email = repoUser.getEmail();
			// add newly populated DTO to DTO list
			returnList.add(sweeper, returnUserDTO);
		}
		// then return the new DTO list
		return returnList;
	}
	
    @Override
	public UserDTO getUserById(Long id) {
    	// getById only returns a reference to the user, and not the actual entry in the DB.
    		// this is not what we need and so we do not use it
    	// instead we use findById which also lets us return a null value if nothing is found
    	User userSearch = userRepository.findById(id).orElse(null);
		// DTO to be returned, populated with new user values
    	UserDTO searchResult = new UserDTO(userSearch.getUsername(), userSearch.getEmail());
    	return searchResult;
	}
    

    @Override
    public void deleteUserById(Long id) {
    	userRepository.deleteById(id);
    }
	
	@Override
    public UserDTO updateUser(Long id, User newUser) {
		// copy at the specified index the newUser received as parameter
		
		BeanUtils.copyProperties(newUser, userRepository.getById(id));
		userRepository.save(userRepository.getById(id));
		
		// DTO to be returned, populated with new user values
		UserDTO newUserReturn = new UserDTO(newUser.getUsername(), newUser.getEmail());
		
		return newUserReturn;
    }
}
