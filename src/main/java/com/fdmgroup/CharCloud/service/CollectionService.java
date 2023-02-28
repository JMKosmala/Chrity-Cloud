package com.fdmgroup.CharCloud.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.repository.CollectionRepository;

@Service
public class CollectionService implements ICollectionService {

	@Autowired
	private CollectionRepository repo;

	@Override
	public ArrayList<Collection> getAllDonations() {
		return new ArrayList<Collection>(repo.findAll());
	}

	@Override
	public void createNewCollection(Collection collection) {
		repo.save(collection);
		
	}

	@Override
	public Optional<Collection> getCollectionByID(int id) {
		return repo.findById(id);
	}

	@Override
	public ArrayList<Collection> getDonationByOwner(User owner) {
		return repo.findByOwner(owner);
	}

	@Override
	public void deleteDonation(Collection donation) {
		repo.delete(donation);
		
	}
	
	


}
