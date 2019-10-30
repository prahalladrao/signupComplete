package com.stackoverflow.signup.service;

import com.stackoverflow.signup.exceptions.UserExistException;
import com.stackoverflow.signup.model.User;
import com.stackoverflow.signup.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.net.URISyntaxException;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService
{
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private SequenceGenerator sequenceGenerator;
   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public boolean signUp(User user) throws URISyntaxException {
        if(userRepository.findByUserName(user.getUserName())!=null)
            throw new UserExistException("UserName already exists",600);
        else if(userRepository.findByUserName(user.getEmail())!=null)
            throw new UserExistException("Email already exists",601);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> set= validator.validate(user);
        for(ConstraintViolation c:set)
            throw new UserExistException(c.getMessage(),400);
        user.setUserId(sequenceGenerator.generateSequence(User.user_seq));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if(userRepository.save(user)==null)
            return false;
        return true;
    }

    @Override
    public boolean checkUser(Long userId) {
        if(userRepository.findById(userId)!=null)
            return true;
        return false;
    }
}
