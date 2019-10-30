package com.stackoverflow.signup.service;

import org.springframework.stereotype.Service;

@Service
public interface LoginService
{

    String validate(String email, String password);
}
