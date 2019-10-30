package com.stackoverflow.signup.service;

import com.stackoverflow.signup.model.User;
import com.stackoverflow.signup.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String validate(String email, String password) {
        User user=userRepository.findByEmail(email);
        if(user!=null)
        {
            if(bCryptPasswordEncoder.matches(password,user.getPassword()))
                return user.getUserName();
        }
        return null;
    }
}
