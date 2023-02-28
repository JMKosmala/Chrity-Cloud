package com.fdmgroup.CharCloud.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.CharCloud.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
		Optional<User> findByUsername(String username);
		Optional<User> findById(Integer id);
		ArrayList<User> findByName(String name);
		ArrayList<User> findBySurname(String surname);
		Optional<User> findByEmail(String email);
			
	}


