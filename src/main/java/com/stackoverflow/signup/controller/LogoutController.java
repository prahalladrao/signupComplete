package com.stackoverflow.signup.controller;

import com.stackoverflow.signup.model.LoginResponse;
import com.stackoverflow.signup.model.LogoutResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/logout")
public class LogoutController {
    @Autowired
    private LogoutResponse logoutResponse;
    @Autowired
    private HttpHeaders headers;
    @PostMapping
    public ResponseEntity<LogoutResponse> logout(@RequestHeader("Authorisation") String token)
    {
        try
        {
            token = token.substring(6);
            logoutResponse.setStatusCode(200);
            logoutResponse.setMessage("SUCCESSFUL");
            headers.add("token",token.substring(token.length()/2));
            return new ResponseEntity<LogoutResponse>(logoutResponse,headers, HttpStatus.OK);

        } catch(Exception e)
        {
            logoutResponse.setStatusCode(400);
            logoutResponse.setMessage("UNSUCCESSFUL");
            return new ResponseEntity<LogoutResponse>(logoutResponse, HttpStatus.BAD_REQUEST);
        }
    }

}
