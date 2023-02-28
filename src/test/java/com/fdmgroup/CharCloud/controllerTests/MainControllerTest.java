package com.fdmgroup.CharCloud.controllerTests;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fdmgroup.CharCloud.Application;
import com.fdmgroup.CharCloud.controller.MainController;
import com.fdmgroup.CharCloud.controller.UserController;
import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.service.ICollectionService;
import com.fdmgroup.CharCloud.service.IUserService;
import com.fdmgroup.CharCloud.service.RoleService;
import com.fdmgroup.CharCloud.model.User;

@SpringBootTest
@ExtendWith (SpringExtension.class)
@AutoConfigureMockMvc (addFilters=false)
@ContextConfiguration (classes=Application.class)
public class MainControllerTest {

	
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
	
	
	@Test
	@WithMockUser
	public void test_goToIndex_statusOk() throws Exception {
		
			User user1=new User();
			Collection collection= new Collection("aa", 1000, user1, "dd", "ss");
			ArrayList <Collection> collections=new ArrayList<>();
			collections.add(collection);
			when(collectionService.getAllDonations()).thenReturn(collections);
			when(userService.getLoggedInUser()).thenReturn(Optional.of(user1));
	
		
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}
	
	@Test
	@WithMockUser
	public void test_goToReqisterPage() throws Exception {
		mockMvc.perform(get("/register")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void test_ReqisterSubmit() throws Exception {
		mockMvc.perform(post("/register")
				.param("a","a","a@o.com","a","a","a"))
				.andExpect(view().name("login"));
	}
	
	@Test
	@WithMockUser
	public void test_ErrorPage() throws Exception {
		mockMvc.perform(get("/error")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void test_goToAboutUsPage() throws Exception {
		mockMvc.perform(get("/aboutUs")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void test_goToContactPage() throws Exception {
		mockMvc.perform(get("/contact")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void test_goToFAQPage() throws Exception {
		mockMvc.perform(get("/help")).andExpect(status().isOk());
	}
	
}
