package com.stackoverflow.signup.controller;

import com.stackoverflow.signup.model.JwtUser;
import com.stackoverflow.signup.model.LoginRequest;
import com.stackoverflow.signup.model.LoginResponse;
import com.stackoverflow.signup.security.JwtGenerator;
import com.stackoverflow.signup.service.LoginService;
import com.stackoverflow.signup.token.TokenHandler;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private TokenHandler tokenHandler;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private JwtUser jwtUser;
    @Autowired
    private LoginResponse loginResponse;
    @Autowired
    private HttpHeaders headers;
   @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) throws JoseException
   {
       String userName=loginService.validate(loginRequest.getEmail(),loginRequest.getPassword());
       jwtUser.setUserName(userName);

       if(userName!=null)
       {
           loginResponse.setStatusCode(200);
           loginResponse.setMessage("SUCCESSFUL");
           headers.add("token",jwtGenerator.generate(jwtUser));
           return new ResponseEntity<LoginResponse>(loginResponse,headers,HttpStatus.OK);
       }
       loginResponse.setStatusCode(401);
       loginResponse.setMessage("UNSUCCESSFUL");
       return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.UNAUTHORIZED);
   }

}
