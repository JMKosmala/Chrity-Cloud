package com.fdmgroup.CharCloud.controllerTests;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fdmgroup.CharCloud.Application;
import com.fdmgroup.CharCloud.controller.MainController;
import com.fdmgroup.CharCloud.controller.UserController;
import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.Role;
import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.security.Roles;
import com.fdmgroup.CharCloud.service.ICollectionService;
import com.fdmgroup.CharCloud.service.IUserService;
import com.fdmgroup.CharCloud.service.RoleService;

@SpringBootTest
@ExtendWith (SpringExtension.class)
@AutoConfigureMockMvc (addFilters=false)
@ContextConfiguration (classes=Application.class)
public class CollectionControllerTests {

	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	IUserService userService;
	@MockBean
	ICollectionService collectionService;
	@MockBean
	private UserController userController;
	@MockBean
	private RoleService roleService;
	@MockBean
	private PasswordEncoder encoder;
	@InjectMocks
	private MainController mainController;
	
	@BeforeEach
    public void setUp() {
		Role roleUser = new Role(Roles.User);
		User user1=new User(roleUser,"Dummy","Dan","Dan","Dan","d@dan.com");
		
		user1.setCharPoints(1000);
		Collection collection= new Collection("aa", 1000, user1, "dd", "ss");
		ArrayList <Collection> collections=new ArrayList<>();
		collections.add(collection);
		when(collectionService.getAllDonations()).thenReturn(collections);
		when(userService.getLoggedInUser()).thenReturn(Optional.of(user1));
    }
	
	@Test
	@WithMockUser
	public void test_goToCreateCollection() throws Exception {
		
		User user1=new User();
		Integer id=1;
		Collection collection = new Collection("a", 11, user1, "", "");
		collection.setId(id);
		
		
		mockMvc.perform(get("/createCollection")).andExpect(status().isOk());
		
	}
	
	
	@Test
	@WithMockUser
	public void test_DonateSubmit() throws Exception {
		User user1=new User();
		Integer id=1;
		Collection collection = new Collection("a", 11, user1, "", "");
		collection.setId(id);
		when(userService.getLoggedInUser()).thenReturn(Optional.of(user1));
		when(collectionService.getCollectionByID(id)).thenReturn(Optional.of(collection));
		
		
		given(collectionService.getCollectionByID(id)).willReturn(Optional.of(new Collection()));
		given(userService.getUserByUsername("username")).willReturn(Optional.of(new User()));
        given(encoder.encode(anyString())).willReturn("encodedPassword");
        this.mockMvc.perform( post("/collections/id/donateSuccess"))
            .andExpect(status().isOk())
            .andExpect(view().name("success"))
            .andExpect(model().attributeExists("collection"));
		
	}
	
	@Test
	@WithMockUser
    public void testGoToDonatePage() throws Exception {
		Role roleUser = new Role(Roles.User);
		User user1=new User(roleUser,"Dummy","Dan","Dan","Dan","d@dan.com");
		Integer id=1;
		Collection collection = new Collection("a", 11, user1, "", "");
		collection.setId(id);
		
		when(collectionService.getCollectionByID(id)).thenReturn(Optional.of(collection));
        given(userService.getLoggedInUser()).willReturn(Optional.of(user1));
        given(collectionService.getCollectionByID(id)).willReturn(Optional.of(collection));
        
        mockMvc.perform(get("/collections/id/donate"))
        .andExpect(status().isOk())
        .andExpect(view().name("payment"));
	}
	@Test
	@WithMockUser
    public void testSingleCollection() throws Exception {
		
		User user = new User();
        user.setUsername("testuser");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, "password", authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        given(userService.getLoggedInUser()).willReturn(Optional.of(user));
		Collection collection = new Collection("a", 11, user, "", "");
		collection.setId(1);
		given(collectionService.getCollectionByID(1)).willReturn(Optional.of(collection));
        this.mockMvc.perform(get("/collections/id"))
            .andExpect(status().isOk());
    }
}