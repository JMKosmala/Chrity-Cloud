package com.fdmgroup.CharCloud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.CharCloud.exceptions.UserNotFoundException;
import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.model.UserPrincipal;
import com.fdmgroup.CharCloud.repository.CollectionRepository;
import com.fdmgroup.CharCloud.repository.UserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CollectionRepository collectionRepo;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> optUser = getUserByUsername(username);
		User user = optUser.orElseThrow(() -> new UsernameNotFoundException(username));
		return new UserPrincipal(user);
	}

	@Override
	public boolean isLoggedInUser(User user) {
		User loggedInUser = getLoggedInUser().orElse(new User());
		return user.equals(loggedInUser);
	}

	@Override
	public boolean isLoggedInUser(Optional<User> userOpt) {
		return userOpt != null && isLoggedInUser(userOpt.orElse(new User()));
	}

	@Override
	public void createNewUser(User user) {
		if(getUserByUsername(user.getUsername()).isEmpty()) {
			userRepo.save(user);
		}
		else {
			System.out.println("ERROR: User already exists!");
		}
		
	}

	@Override
	public void updateUser(User user) {
		userRepo.save(user);
		
	}

	@Override
	public void deleteUser(Integer id) throws UserNotFoundException {
		userRepo.delete(getUserById(id));
		
	}

	@Override
	public User getCurrentUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    Optional<User> user = userRepo.findByUsername(username);
	    return user.orElse(null);
	}

	@Override
	public ArrayList<User> getUserByName(String name) {
		ArrayList<User> filteredUsersByName = userRepo.findByName(name);
		return filteredUsersByName;
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public Optional<User> getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return getUserByUsername(auth.getName());
	}

	@Override
	public User getUserById(int id) throws UserNotFoundException {
		Optional<User> optUser = userRepo.findById(id);
		return optUser.orElseThrow(() -> new UserNotFoundException(id));
	}
	@Override
	public void reauthenticateUser(User user) {
		Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
		UsernamePasswordAuthenticationToken updatedAuth = 
				new UsernamePasswordAuthenticationToken(user, currentAuth.getCredentials(), currentAuth.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(updatedAuth);
	}


	@Override
	public boolean checkOwnership(User user, Collection collection) {
		
		return user == collection.getDonor();
	}
	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public Integer donateSomeMoney(User user, Collection collection, Integer donation) {
		
		
		
		if((donation>user.getCharPoints())) {
				return collection.getCollected();	
		}
		else if((donation+collection.getCollected())>collection.getAmount()) {
			
			Integer diff=collection.getAmount()-collection.getCollected();
			user.setDonatedTotal(user.getDonatedTotal()+diff);
			user.setCharPoints(user.getCharPoints()-diff);;
			user.setLastDonation(diff);
			collection.setCollected(collection.getAmount());
			
			
			userRepo.save(user);
			collectionRepo.save(collection);
			return collection.getCollected();
			}
		
		user.setDonatedTotal(user.getDonatedTotal()+donation);
		user.setCharPoints(user.getCharPoints()-donation);;
		user.setLastDonation(donation);
		collection.setCollected(collection.getCollected()+donation);
		
		userRepo.save(user);
		collectionRepo.save(collection);
		
		return collection.getCollected();
	}

	@Override
	public List<User> getAllUsers() {
		
		return userRepo.findAll();
		
	}
	

	
}

