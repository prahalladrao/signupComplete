package com.stackoverflow.signup.controller;


import com.stackoverflow.signup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public boolean checkUser(@RequestBody Long userId)
    {
        //System.out.println(userId);
          if(userService.checkUser(userId))
              return true;
          return false;
    }
}
