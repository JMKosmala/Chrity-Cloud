package com.fdmgroup.CharCloud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.CharCloud.exceptions.CommentValidationException;
import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.Comment;
import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.service.CommentService;
import com.fdmgroup.CharCloud.service.ICollectionService;
import com.fdmgroup.CharCloud.service.IUserService;

@Controller
public class CollectionsController {


	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICollectionService collectionService;
	
	@Autowired
	private CommentService commentService;
	
	
	
	@PostMapping("/collections/{id}")
	public String goToSingleCollection(ModelMap model, @PathVariable Integer id) {
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		if(loggedIn) {
			model.addAttribute("user", userService.getLoggedInUser().get());
		}
		
		Optional<Collection> optCollection=collectionService.getCollectionByID(id);
		if(optCollection.isEmpty()) {
			return "index";
		}
		Collection collection =optCollection.get();
		model.addAttribute("collection", collection);
		
		List<Comment> comments = commentService.findByCollectionId(collection.getId());
	    model.addAttribute("comments", comments);

		return "singleCollection";
	}
	
	@GetMapping("/createCollection")
	public String createCollection(ModelMap model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		Optional<User> optUser=userService.getLoggedInUser();
		User user =optUser.get();
		
		Collection collection = new Collection();
		
		model.addAttribute("collection" , collection);
		
	    return "createCollection";
	}
	
	
	@PostMapping("/createCollection")
	public String createSubmit(ModelMap model, @RequestParam String title, @RequestParam Integer amount, @RequestParam String img, @RequestParam String description) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		Optional<User> optUser=userService.getLoggedInUser();
		User user =optUser.get();
		
		Collection collection = new Collection();
		collection.setTitle(title);
		collection.setAmount(amount);
		collection.setImg(img);
		collection.setDescription(description);
		collection.setDonor(user);
		
				
		collectionService.createNewCollection(collection);
			
		return "createSuccess";
	}

	
	@GetMapping("/collections/{id}/donate")
	public String goToDonatePage (ModelMap model, @PathVariable Integer id) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		Optional<Collection> optCollection=collectionService.getCollectionByID(id);
		if(optCollection.isEmpty()) {
			return "index";
		}
		Collection collection =optCollection.get();
		model.addAttribute("collection",collection);
		model.addAttribute("user", userService.getLoggedInUser().get());

		return "donate";
	}
	
	@PostMapping("/collections/{id}/donateSuccess")
	public String makeDonation (ModelMap model, @PathVariable Integer id, @RequestParam(defaultValue = "0") Integer donation) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
			
		Optional<Collection> optCollection=collectionService.getCollectionByID(id);
		if(optCollection.isEmpty()) {
			return "index";
		}
		Optional<User> optUser=userService.getLoggedInUser();
		User user =optUser.get();
		Collection collection =optCollection.get();
		
		if(user.getCharPoints()<donation) {
			return "dontEnoughPoints";
		}
		if(donation<1) {
			return "cantEnterNegativeValues";
		}
			
		userService.donateSomeMoney(user, collection, donation);
		
		model.addAttribute("collection",collection);
		model.addAttribute("user",user);
		
		return "success";
	}
	@PostMapping("/collections/{id}/comment")
	public String submitComment(ModelMap model, @PathVariable Integer id, @ModelAttribute Comment comment, BindingResult result) {
	    
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		boolean loggedIn = auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken);
		model.addAttribute("loggedIn", loggedIn);
		
		Optional<Collection> optCollection = collectionService.getCollectionByID(id);
	    if (optCollection.isEmpty()) {
	        return "index";
	    }
	    if (result.hasErrors()) {
	        model.addAttribute("errors", result.getAllErrors());
	        return "error";
	    }
	    comment.setCollection(optCollection.get());
	    Optional<User> optUser = userService.getLoggedInUser();
	    if (optUser.isPresent()) {
	        User user = optUser.get();
	        comment.setUser(user);
	    }
	    try {
	        commentService.saveComment(comment);
	    } catch (CommentValidationException e) {
	        result.rejectValue("text", "comment.text", e.getMessage());
	        model.addAttribute("errors", result.getAllErrors());
	        return "error";
	    }
	    return "commentSuccess";
	}

	
	
}
