package com.fdmgroup.CharCloud.controllerTests;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.BDDMockito.given;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import com.fdmgroup.CharCloud.model.Collection;
import com.fdmgroup.CharCloud.model.Role;
import com.fdmgroup.CharCloud.model.User;
import com.fdmgroup.CharCloud.security.Roles;
import com.fdmgroup.CharCloud.service.CollectionService;
import com.fdmgroup.CharCloud.service.IUserService;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService userService;

    @MockBean
    private CollectionService collectionService;

    @MockBean
    private PasswordEncoder encoder;

    private User loggedInUser;

    @BeforeEach
    public void setup() {
        Role roleUser = new Role(Roles.User);
        loggedInUser = new User(roleUser, "Dummy", "Dan", "Dum", "D", "d@dd.com");
        given(userService.getLoggedInUser()).willReturn(Optional.of(loggedInUser));
    }

    @Test
    public void testLogin() throws Exception {
        this.mockMvc.perform(get("/login"))
            .andExpect(status().isOk())
            .andExpect(view().name("login"))
            .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testLogout() throws Exception {
        	
    	User user = new User();
        user.setUsername("testuser");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, "password", authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    	
        given(userService.getLoggedInUser()).willReturn(Optional.of(user));
    	
    		mockMvc.perform(get("/logout"))
            .andExpect(status().isOk())
            .andExpect(request().sessionAttributeDoesNotExist("user"));
    }

    @Test
   
    public void testGoToUserProfile() throws Exception {
    	User user = new User();
        user.setUsername("testuser");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, "password", authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    	
        given(userService.getLoggedInUser()).willReturn(Optional.of(user));
        
        	mockMvc.perform(get("/profile"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/profile/testuser"));
    }


    @Test
    public void testUserProfile() throws Exception {
        given(userService.getUserByUsername("username")).willReturn(Optional.of(new User()));
        this.mockMvc.perform(get("/profile/username"))
            .andExpect(status().isOk())
            .andExpect(view().name("profile"))
            .andExpect(model().attributeExists("loggedIn", "user"));
    }

    @Test
    public void testGoToPaymentPage() throws Exception {
    	User user = new User();
        user.setUsername("testuser");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, "password", authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        
        given(userService.getLoggedInUser()).willReturn(Optional.of(user));
        
        mockMvc.perform(get("/payment"))
        .andExpect(status().isOk())
        .andExpect(view().name("payment"));
}


    @Test
    public void testChangeUserDetails() throws Exception {
        given(userService.getUserByUsername("username")).willReturn(Optional.of(new User()));
        given(encoder.encode(anyString())).willReturn("encodedPassword");
        this.mockMvc.perform(post("/profile/username/changeDetails")
                .flashAttr("user", new User()))
            .andExpect(status().isOk())
            .andExpect(view().name("changeSuccess"))
            .andExpect(model().attributeExists("user"));
    }

    @Test
    public void testGoToMyCollections() throws Exception {
    
        User user = new User();
        user.setUsername("testuser");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, "password", authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
        
      
        given(userService.getLoggedInUser()).willReturn(Optional.of(user));
        
        
        mockMvc.perform(get("/myCollections"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/myCollections/testuser"));
    }

  
    public void testMyCollections() throws Exception {
    	User user = new User();
        user.setUsername("testuser");
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, "password", authorities);
        SecurityContextHolder.getContext().setAuthentication(auth);
    	
        given(userService.getLoggedInUser()).willReturn(Optional.of(user));
        given(collectionService.getDonationByOwner(any(User.class))).willReturn(new ArrayList<Collection>());
        this.mockMvc.perform(get("/myCollections/username"))
            .andExpect(status().isOk())
            .andExpect(view().name("myCollections"))
            .andExpect(model().attributeExists("myCollections"));
    }
}
