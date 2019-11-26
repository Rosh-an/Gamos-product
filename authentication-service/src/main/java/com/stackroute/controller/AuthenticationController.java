package com.stackroute.controller;

import com.stackroute.config.JwtTokenUtil;
import com.stackroute.model.DAOUser;
import com.stackroute.model.JwtResponse;
import com.stackroute.model.UserDTO;
import com.stackroute.service.JwtUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody UserDTO user) {
        try {
            user.setLoginType("general");
            jwtUserDetailsService.save(user);
            return ResponseEntity.ok(new JwtResponse("User Saved"));
        }catch (Exception e){
            return ResponseEntity.ok(new JwtResponse("UserEmail already Exists"));
        }

    }
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserDTO user) {
        try{
            if(user.getLoginType().equalsIgnoreCase("google") || user.getLoginType().equalsIgnoreCase("facebook")){
                try {
                    jwtUserDetailsService.save(user);
                }catch (Exception e){
                    logger.info("No need to save");
                }
           }
            final UserDetails userDetails = jwtUserDetailsService
                    .loadUserByUsername(user.getUserEmail());
           logger.info("Details: {}",userDetails);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if(!encoder.matches(user.getPassword(),userDetails.getPassword())){
                return ResponseEntity.ok(new JwtResponse("Login failed wrong Password"));
            }
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        }catch (Exception e){
            return ResponseEntity.ok(new JwtResponse("Login failed check Email"));
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<JwtResponse> deleteUser(@RequestBody DAOUser user){
        jwtUserDetailsService.delete(user);
        return ResponseEntity.ok(new JwtResponse("Deleted Succcessfully"));
    }
    private void authenticate(String username, String password) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new IOException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new IOException("INVALID_CREDENTIALS", e);
        }
    }
}
