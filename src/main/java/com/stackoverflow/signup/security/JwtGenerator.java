package com.stackoverflow.signup.security;


import com.stackoverflow.signup.model.JwtUser;
import com.stackoverflow.signup.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {
      @Autowired
      private UserRepository userRepo;
    public String generate(JwtUser jwtUser) {


        Claims claims = Jwts.claims()
                .setSubject(jwtUser.getUserName());
        claims.put("userId",""+userRepo.findByUserName(jwtUser.getUserName()).getUserId());
        claims.setExpiration(new Date(System.currentTimeMillis()+3600000));//setting expiration time of 1 hour

        return Jwts.builder()
                .setClaims(claims)

                .signWith(SignatureAlgorithm.HS512, "$#12&T86")
                .compact();
    }
}
