package com.fdmgroup.CharCloud.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.User;

public interface CollectionRepository extends JpaRepository<Collection, Integer> {
	
	Optional<Collection> findById(Integer id);
	public ArrayList<Collection> findAll();
	public ArrayList<Collection> findByOwner(User donor);
}
