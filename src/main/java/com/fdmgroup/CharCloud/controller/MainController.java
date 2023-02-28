package com.fdmgroup.CharCloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.security.Roles;
import com.fdmgroup.CharCloud.service.ICollectionService;
import com.fdmgroup.CharCloud.service.IUserService;
import com.fdmgroup.CharCloud.service.RoleService;

@Controller
public class MainController {


	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICollectionService collectionService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PasswordEncoder encoder;
	
		
	@GetMapping("/")
	public String goToIndex(ModelMap model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		if(loggedIn) {
			model.addAttribute("user", userService.getLoggedInUser().get());
		}
		
			model.addAttribute("collections", collectionService.getAllDonations());


		return "index";
	}
	
	@GetMapping("/register")
	public String register(ModelMap model) {
		
		User user = new User();
		
		model.addAttribute("user" , user);
	    return "register";
	}
	
	
	@PostMapping("/register")
	public String registerSubmit(ModelMap model, @RequestParam String name, @RequestParam String surname, @RequestParam String email, @RequestParam String username, @RequestParam String password,@RequestParam String confirmPassword) {
		
		User user = new User();
		user.setName(name);
		user.setSurname(surname);
		user.setPassword(password);
		user.setEmail(email);
		user.setUsername(username);
		user.setConfirmPassword(confirmPassword);
		
		user.setRole(roleService.findRoleByName(Roles.User));
		user.setPassword(encoder.encode(user.getPassword()));
				
		userService.createNewUser(user);
			
		return "redirect:/login";
	}
	
	
	@GetMapping("/error")
	public String error(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		return "error";
	}
	
	
	@GetMapping("/help")
	public String goToFAQ(ModelMap model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		return "help";
	}
	
	
	@GetMapping("/aboutUs")
	public String goToAboutUs(ModelMap model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		return "aboutUs";
	}
	
	
	@GetMapping("/contact")
	public String goToContactPage(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		return "contact";
	}
	
}
