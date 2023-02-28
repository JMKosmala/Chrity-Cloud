package com.fdmgroup.CharCloud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.fdmgroup.CharCloud.exceptions.UserNotFoundException;
import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.User;

public interface IUserService extends UserDetailsService {
	boolean isLoggedInUser(User user);
	boolean isLoggedInUser(Optional<User> user);
	void createNewUser(User user);
	void updateUser(User user);
	void deleteUser(Integer id) throws UserNotFoundException;
	User getCurrentUser();
	ArrayList<User> getUserByName(String name);
	Optional<User> getUserByUsername(String username);
	Optional<User> getLoggedInUser();
	Optional<User> getUserByEmail(String email);
	User getUserById(int id) throws UserNotFoundException;
	void reauthenticateUser(User user);
	boolean checkOwnership(User user, Collection collection);
	Integer donateSomeMoney(User user, Collection collection,Integer donation);
	List<User> getAllUsers();
}

