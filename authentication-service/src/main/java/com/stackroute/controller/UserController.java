package com.stackroute.controller;


import com.stackroute.model.JwtResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class UserController {
    @GetMapping("/hello")
    public ResponseEntity<JwtResponse> firstPage(){
        String msg = "Hello";

        return ResponseEntity.ok(new JwtResponse(msg));
    }
}
