package com.stackroute.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.exception.UserAlreadyExistsException;
import com.stackroute.model.DAOUser;
import com.stackroute.service.JwtUserDetailsService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;



public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private DAOUser user;
    @MockBean
    private JwtUserDetailsService userDetailsService;
    @InjectMocks
    private AuthenticationController authenticationController;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
        user = new DAOUser();
        user.setUserEmail("febin");
        user.setPassword(BCrypt.hashpw("password", BCrypt.gensalt()));
        user.setLoginType("general");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void registerTest() throws Exception {
//        System.out.println(user.toString());
//        when(userDetailsService.save(any())).thenReturn(user);
//        System.out.println(userDetailsService.save(any()).toString());
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void registerTestFailure() throws Exception {
        //when(userDetailsService.save(any())).thenThrow(UserAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void loginTest() throws Exception {
        UserDetails user1 = new User("febin","password",new ArrayList<>());
        //when(userDetailsService.loadUserByUsername(any())).thenReturn(user1);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void loginTestFailure() throws Exception {
        //  when(userDetailsService.loadUserByUsername(any())).thenThrow(UsernameNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}