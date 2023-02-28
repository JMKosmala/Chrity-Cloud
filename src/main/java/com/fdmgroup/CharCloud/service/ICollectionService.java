package com.fdmgroup.CharCloud.service;

import java.util.ArrayList;
import java.util.Optional;

import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.User;

public interface ICollectionService {

		ArrayList<Collection> getAllDonations();
		void createNewCollection (Collection collection);
		Optional<Collection> getCollectionByID(int id);
		ArrayList<Collection> getDonationByOwner (User owner);
		void deleteDonation(Collection donation);

}
