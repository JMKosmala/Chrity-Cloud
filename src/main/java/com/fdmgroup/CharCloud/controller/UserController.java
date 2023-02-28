package com.fdmgroup.CharCloud.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.security.Roles;
import com.fdmgroup.CharCloud.service.CollectionService;
import com.fdmgroup.CharCloud.service.ICollectionService;
import com.fdmgroup.CharCloud.service.IUserService;
import com.fdmgroup.CharCloud.service.RoleService;

@Controller
public class UserController {


	@Autowired
	private IUserService userService;

	
	@Autowired
	private CollectionService collectionService;


	@Autowired
	private PasswordEncoder encoder;
	
	
	
	@GetMapping("/login")
	public String login(ModelMap model) {
		User user = (User) model.getAttribute("user");
		if(user == null) {
			user = new User();
		}
		
		model.addAttribute("user", user);
		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/";
	}
	
	@GetMapping("/profile")
	public String goToUserProfile(ModelMap model) {
	
		Optional<User> optUser =userService.getLoggedInUser();
		if(optUser.isEmpty()) {
			return "redirect:/";
		}
		User user=optUser.get();
		return "redirect:/profile/" +user.getUsername();
	}

	@GetMapping("/profile/{username}")
	public String userProfile (ModelMap model, @PathVariable String username) {
	
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
	model.addAttribute("loggedIn", loggedIn);
	
	Optional<User>optUser=userService.getUserByUsername(username);
	User user= optUser.orElse(new User());
	model.addAttribute("user",user);
	return "profile";
	}
	
	@GetMapping("/payment")
	public String goToPaymentPage() {
		return "payment";
	}
	
	@GetMapping("/profile/{username}/changeDetails")
	public String changeUserDetails (ModelMap model, @PathVariable String username) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		Optional<User> optUser=userService.getUserByUsername(username);
		if(optUser.isEmpty()) {
			return "index";
		}
		User user= optUser.get();
		model.addAttribute("user", user);

		return "changeDetails";
	}
	
	@PostMapping("/profile/{username}/changeDetails")
	public String makeDonation (ModelMap model, @ModelAttribute User user, @PathVariable String username) {
		
		user.setUsername(username);
		
		Optional<User> optUser=userService.getUserByUsername(username);
		if(optUser.isEmpty()) {
			return "/profile";
		}
		User dbUser=optUser.get();
		
		dbUser.setName(user.getName());
		dbUser.setSurname(user.getSurname());
		dbUser.setEmail(user.getEmail());
		dbUser.setUsername(user.getUsername());
		dbUser.setConfirmPassword(user.getConfirmPassword());
		dbUser.setPassword(encoder.encode(user.getPassword()));
		dbUser.setImg(user.getImg());
		
		userService.updateUser(dbUser);
		model.addAttribute("user",dbUser);
		
		return "changeSuccess";
	}
	
	@GetMapping("/myCollections")
	public String goToMyCollections(ModelMap model) {
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		Optional<User> optUser =userService.getLoggedInUser();
		if(optUser.isEmpty()) {
			return "redirect:/";
		}
		User user=optUser.get();
		return "redirect:/myCollections/" +user.getUsername();
	}
	
	@GetMapping("/myCollections/{username}")
	public String myCollections (ModelMap model, @PathVariable String username) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		Optional<User>optUser=userService.getUserByUsername(username);
		User user= optUser.orElse(new User());
		model.addAttribute("myCollections",collectionService.getDonationByOwner(user));
		return "myCollections";
	}
	
	


}
