package com.stackoverflow.signup.controller;

import com.stackoverflow.signup.model.User;
import com.stackoverflow.signup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@CrossOrigin
@RequestMapping("/register")
public class SignupController
{
     @Autowired
     private UserService userService;
     @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody User user) throws URISyntaxException {
       if(userService.signUp(user))
           return new ResponseEntity(HttpStatus.OK);
         return new ResponseEntity(HttpStatus.BAD_REQUEST);
     }
}
