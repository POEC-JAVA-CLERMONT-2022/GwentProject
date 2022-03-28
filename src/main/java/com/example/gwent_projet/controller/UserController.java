package com.example.gwent_projet.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.gwent_projet.dto.UserDTO;
import com.example.gwent_projet.models.User;
import com.example.gwent_projet.services.UserService;

@Controller 
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/HelloWorld")
	public String test() {
	    return "helloworld";
	}
	
	  @GetMapping("/user/new")
	  public String createUserForm(Model model) {
		  
		// new user via DTO to hold data to be passed  
		UserDTO createUser = new UserDTO();
		// passing data to model
	    model.addAttribute("username", createUser.username);
	    model.addAttribute("email", createUser.email);
	    model.addAttribute("password", createUser.password);
	    return "user/new";
	  }
	
/*
	// map GET requests from this web page to this method
	@GetMapping("/users/new")
	public String createUserForm(HttpServletRequest req, Model model) {
		// new user using DTO
		UserDTO createUser = new UserDTO();
		//model.addAttribute("user", userService.createUser(null));

		// get data provided by user form
		createUser.username=req.getParameter("username"); 
		createUser.email=req.getParameter("email"); 
		createUser.password=req.getParameter("password"); 
		
		
		// add user to model
		model.addAttribute("user", createUser);
		return "create_user";
	}
*/
/*
	// Map ONLY POST Requests
	// path is the corresponding URL address for the operation
	@PostMapping(path="/newAccount")
	public @ResponseBody String createUser (@RequestParam String username, @RequestParam String email, @RequestParam String password) {
		// @ResponseBody means the returned String is the response, not a view name
		// in this case, "User created successfully." is the return value
		// @RequestParam means it is a parameter from the GET or POST request

		// new user
		CreateUser createUser = new CreateUser();

		// build user




		// save user
		userService.createUser(createUser);
		return "User created successfully.";
	}

*/

}
