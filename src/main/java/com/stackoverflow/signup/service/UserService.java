package com.stackoverflow.signup.service;

import com.stackoverflow.signup.model.User;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public interface UserService
{

    boolean signUp(User user) throws URISyntaxException;

    boolean checkUser(Long userId);
}
