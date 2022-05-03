package com.example.gwent_projet.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.gwent_projet.entity.user.User;
import com.example.gwent_projet.repository.UserRepository;
import com.example.gwent_projet.services.UserService;
import com.example.gwent_projet.services.dto.user.UserCreationDTO;
import com.example.gwent_projet.services.dto.user.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceImpl (UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	// ---------------------------------------------------------------------------------------

	@Override
	public UserDTO createUser(UserCreationDTO createUser) {
		
		User user = new User(0, "", "", ""); // always set role to 0 on creation. might change if more roles are added
		BeanUtils.copyProperties(createUser, user);
		
		userRepository.save(user);
		// DTO to be returned, populated with new user values
		UserDTO newUser = new UserDTO();
		BeanUtils.copyProperties(user, newUser);
		return newUser;
	}
	
	@Override
	public List<UserDTO> getAllUsers() {
		// get the entire list of users from the repo
		List<User> repoList = userRepository.findAll();
		// new, empty list of UserDTOs
		List<UserDTO> returnList = new ArrayList<>();
		
		// for every user in the list, populate a DTO and add it to the DTO list
		for (User repoUser : repoList) {
			UserDTO returnUserDTO  = new UserDTO();
			BeanUtils.copyProperties(repoUser, returnUserDTO);
			returnList.add(returnUserDTO);
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
    	UserDTO searchResult = new UserDTO();
    	BeanUtils.copyProperties(userSearch, searchResult);
    	return searchResult;
	}
    

    @Override
    public void deleteUserById(Long id) {
    	userRepository.deleteById(id);
    }
	
	@Override
	@Transactional
    public UserDTO updateUser(Long id, UserCreationDTO newUser) {
		User user = userRepository.getById(id);
		
        BeanUtils.copyProperties(newUser, user); // important: DTO needs getters and setters for this to work
        userRepository.save(user);

        UserDTO userReturn = new UserDTO();
        BeanUtils.copyProperties(user, userReturn);

        return userReturn;
    }
}
