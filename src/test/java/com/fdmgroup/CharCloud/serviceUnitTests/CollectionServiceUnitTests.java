package com.fdmgroup.CharCloud.serviceUnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.repository.CollectionRepository;
import com.fdmgroup.CharCloud.service.CollectionService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
	public class CollectionServiceUnitTests{

	    @Mock
	    private CollectionRepository repo;

	    @InjectMocks
	    private CollectionService service;

	    private Collection donation;
	    private User owner;

	    @BeforeEach
	    public void setUp() {
	        owner = new User("Ben");
	        donation = new Collection();
	        donation.setId(1);
	        donation.setTitle("SaveMockPandas");
	        donation.setDonor(owner);
	    }

	    @Test
	    public void testGetAllDonations_callsFindAllMethodOfCollectionRepository() {
	        ArrayList<Collection> donations = new ArrayList<Collection>();
	        donations.add(donation);

	        when(repo.findAll()).thenReturn(donations);

	        ArrayList<Collection> result = service.getAllDonations();

	        assertEquals(donations, result);
	        verify(repo, times(1)).findAll();
	    }

	    @Test
	    public void testCreateNewCollection_callsSaveMethodOfCollectionRepository() {
	        service.createNewCollection(donation);

	        assertTrue(donation.getId() != null && donation.getId() > 0);
	        verify(repo, times(1)).save(donation);
	    }

	    @Test
	    public void testGetCollectionByID_callsFindByIdMethodOfCollectionRepository() {
	        when(repo.findById(donation.getId())).thenReturn(Optional.of(donation));

	        Optional<Collection> result = service.getCollectionByID(donation.getId());

	        assertTrue(result.isPresent());
	        assertEquals(donation, result.get());
	        verify(repo, times(1)).findById(donation.getId());
	    }

	    @Test
	    public void testGetDonationByOwner_callsFindByOwnerMethodOfCollectionRepository() {
	        ArrayList<Collection> donations = new ArrayList<Collection>();
	        donations.add(donation);

	        when(repo.findByOwner(owner)).thenReturn(donations);

	        ArrayList<Collection> result = service.getDonationByOwner(owner);

	        assertEquals(donations, result);
	        verify(repo, times(1)).findByOwner(owner);
	    }

	    @Test
	    public void testDeleteDonation_callsDeleteMethodOfCollectionRepository() {
	        service.deleteDonation(donation);
	        
	        verify(repo, times(1)).delete(donation);
	    }
}
