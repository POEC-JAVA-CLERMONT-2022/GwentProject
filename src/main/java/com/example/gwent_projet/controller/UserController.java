package com.example.gwent_projet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.gwent_projet.models.User;
import com.example.gwent_projet.services.UserService;

@Controller 
public class UserController {
	// http inputs, to sort later

	/*

	// get bean "userRepository" created by Spring
	@Autowired
	UserService userService;

	// Map ONLY POST Requests
	// path is the corresponding URL address for the operation
	@PostMapping(path="/add")
	public @ResponseBody String createUser (@RequestParam String username, @RequestParam String email, @RequestParam String password) {
		// @ResponseBody means the returned String is the response, not a view name
			// in this case, "User created successfully." is the return value
		// @RequestParam means it is a parameter from the GET or POST request

		// new user
		User user = new User(0, username, email, password);

		// build user
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);

		// save user
		userService.createAccount();
		return "User created successfully.";
	}


	 */

}
