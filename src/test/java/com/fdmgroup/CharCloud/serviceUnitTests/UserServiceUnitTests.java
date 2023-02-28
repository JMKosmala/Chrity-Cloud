package com.fdmgroup.CharCloud.serviceUnitTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.CharCloud.exceptions.UserNotFoundException;
import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.Role;
import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.repository.CollectionRepository;
import com.fdmgroup.CharCloud.repository.UserRepository;
import com.fdmgroup.CharCloud.security.Roles;
import com.fdmgroup.CharCloud.security.SecurityConfig;
import com.fdmgroup.CharCloud.service.CollectionService;
import com.fdmgroup.CharCloud.service.UserService;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceUnitTests {

	@InjectMocks
	UserService userService;
	@InjectMocks
	CollectionService collectionService;
	@MockBean
	UserRepository mockUserRepo;
	@MockBean
	CollectionRepository mockCollectionRepo;
	
	User user;
	Optional<User> optUser;
	ArrayList<User> users;
	
	@BeforeEach
	public void Setup() {
		user = new User();
		optUser = Optional.of(user);
		users = new ArrayList<User>();
		users.add(user);	
	}
	
	@Test
	public void test_getUserByID_expectSucces() throws UserNotFoundException{
		
		Integer userId = 3;
        User user = new User();
        user.setId(userId);
        Optional<User> expectedUser = Optional.of(user);
        when(mockUserRepo.findById(userId)).thenReturn(expectedUser);

        User actualUser = userService.getUserById(userId);
        assertEquals(actualUser,expectedUser.get());
        
    }

	@Test
	public void test_throwsUserNotFoundExceptionWhenUserIDNotExists() throws UserNotFoundException{
	     
	        try {
			Integer userId = 1;
	        Optional<User> expectedUser = Optional.empty();
	        when(mockUserRepo.findById(userId)).thenReturn(expectedUser);
	        userService.getUserById(userId);
	        }
	        catch(UserNotFoundException e) {
	        }
	    }
	
	@Test
	public void test_getUserByUsername_expectSuccess() throws UserNotFoundException {
		String username = "admin";
		user.setUsername(username);
		when(mockUserRepo.findByUsername(username)).thenReturn(optUser);
		Optional<User> admin = userService.getUserByUsername(username);
		assertTrue(admin.isPresent());
		assertEquals(user, admin.get());
	}
	
	@Test
	public void test_getUserById_expectSuccess() throws UserNotFoundException {
		when(mockUserRepo.findById(0)).thenReturn(optUser);
		User admin = userService.getUserById(0);
		assertEquals(user, admin);
	}
	
	@Test
	public void test_getUserByName_expectSuccess() throws UserNotFoundException {
		user.setName("Jan");
	    when(mockUserRepo.findByName("Jan")).thenReturn(users);
	    List<User> usersList = userService.getUserByName("Jan");
	    assertTrue(usersList.size() > 0);
	    assertEquals("Jan", usersList.get(0).getName());
	}
	
	@Test
	public void test_getUserByEmail_expectSuccess() {
		String email = "JJ@gmail.com";
		user.setEmail(email);
		when(mockUserRepo.findByEmail(email)).thenReturn(optUser);
		Optional<User>dummy=userService.getUserByEmail(email);
		assertTrue(dummy.isPresent());
		assertEquals(user, dummy.get());
		
	}
	
	@Test
	public void test_checkOwnership_expectSuccess() {
		ArrayList<Collection> collections = new ArrayList<>();
		Collection donation = new Collection();
		donation.setDonor(user);
		when(mockCollectionRepo.findByOwner(user)).thenReturn(collections);
		
		assertTrue(userService.checkOwnership(user, donation));
	}
	
	@Test
	public void test_createNewUser_expectSuccess() {
		when(mockUserRepo.findByUsername("Jan")).thenReturn(Optional.empty());
		user.setUsername("Jan");
		userService.createNewUser(user);
		verify(mockUserRepo, times(1)).save(user);
	}
	
	@Test
	public void test_createNewUser_expectFailure() {
		user.setUsername("Jan");
		when(mockUserRepo.findByUsername("Jan")).thenReturn(optUser);
		userService.createNewUser(user);
		verify(mockUserRepo, never()).save(user);
	}
	
	@Test
	public void test_updateUser_expectSuccess() {
		userService.updateUser(user);
		verify(mockUserRepo, times(1)).save(user);
	}
	
	@Test
	public void test_getAllUsers_expectSucces(){
	userService.getAllUsers();
	verify(mockUserRepo,times(1)).findAll();
	}
	
	@Test
	public void test_deleteExistingUser_expectSucces() throws UserNotFoundException{
	
		Integer userId = 3;
        User user = new User();
        user.setId(userId);
        Optional<User> deletedUser = Optional.of(user);
        when(mockUserRepo.findById(userId)).thenReturn(deletedUser);
        userService.deleteUser(userId);
        
        verify(mockUserRepo,times(1)).delete(user);
	}
	
	@Test
	public void test_donateSomeMoneyIsDonatingMoney_expectSuccess() {
		
		Role roleUser = new Role(Roles.User);
		User dummyUser1=new User(roleUser, "Dummy", "Dan", "Dum", "D", "d@dd.com" );
		User dummyUser2=new User(roleUser, "Dummy2", "Danny", "Dummy", "DD", "dd@d.com");


		Collection collection1=new Collection("TestCollection", (int) 2000.0, dummyUser1,"aa" , "aa.img");
		dummyUser1.setCharPoints((int) 1000.0);
		dummyUser2.setCharPoints((int) 2000.0);

		
		userService.donateSomeMoney(dummyUser2, collection1,500);
		
		verify(mockUserRepo,times(1)).save(dummyUser2);
		verify(mockCollectionRepo,times(1)).save(collection1);
		
		assertEquals(collection1.getCollected(),dummyUser2.getDonatedTotal(),0.1);
		assertEquals(collection1.getCollected(),dummyUser2.getLastDonation(),0.1);
		assertEquals(dummyUser2.getLastDonation(),dummyUser2.getDonatedTotal(),0.1);
		assertEquals(500,collection1.getCollected(),0.1);
		assertEquals(1500,dummyUser2.getCharPoints(),0.1);
		
	}
	
	@Test
	public void test_donateSomeMoneyDontDonateMoneyWhenUserDontHaveEnoughCoins_expectSuccess() {
		
		Role roleUser = new Role(Roles.User);
		User dummyUser1=new User(roleUser, "Dummy", "Dan", "Dum", "D", "d@dd.com");
		User dummyUser2=new User(roleUser, "Dummy2", "Danny", "Dummy", "DD", "dd@d.com");

		Collection collection1=new Collection("TestCollection", (int) 2000.0, dummyUser2,"aa" , "aa.img");
		dummyUser1.setCharPoints((int) 1000.0);
		dummyUser2.setCharPoints((int) 2000.0);

		
		
		userService.donateSomeMoney(dummyUser1, collection1,1500);
		
		verify(mockUserRepo,times(0)).save(dummyUser1);
		verify(mockCollectionRepo,times(0)).save(collection1);
		
		assertEquals(0,collection1.getCollected(),0.1);
		assertEquals(1000,dummyUser1.getCharPoints(),0.1);
		
	}
	
	@Test
	public void test_donateSomeMoneyDonateOnlyMissingMoneyWhenDonationIsHigerThanCollectionAmount_expectSuccess() {
		
		Role roleUser = new Role(Roles.User);
		User dummyUser1=new User(roleUser, "Dummy", "Dan", "Dum", "D", "d@dd.com");
		User dummyUser2=new User(roleUser, "Dummy2", "Danny", "Dummy", "DD", "dd@d.com");

		Collection collection1=new Collection("TestCollection", 1000, dummyUser1,"aa" , "aa.img");
		dummyUser1.setCharPoints((int) 1000);
		dummyUser2.setCharPoints((int) 2000);

		
		userService.donateSomeMoney(dummyUser2, collection1,1500);

		assertEquals(1000,collection1.getCollected(),0.1);
		assertEquals(1000,dummyUser2.getCharPoints(),0.1);
		
	}
	
	
	@Test
	public void test_deleteNotExistingUser_expectSucces() throws UserNotFoundException{
	
	try {
			Integer userId = 1;
	        Optional<User> expectedUser = Optional.empty();
	        when(mockUserRepo.findById(userId)).thenReturn(expectedUser);
	        userService.getUserById(userId);
	        
	        userService.deleteUser(user.getId());
	        verify(mockUserRepo,times(0)).delete(user);
	}
    catch(UserNotFoundException e) {
    }
	}
	
	public void test_reauthenticateUser_expectSuccess() {
		
		
	}
	public void test_isLoggedInUser_expectSuccess() {
		
	}
	public void test_loadUserByUsername_expectSuccess() {
		
	}
	public void test_getCurrentUser_expectSuccess() {
		
		
	}
	
	public void test_getLogedInUser_exoectSuccess() {
		
	}




}
