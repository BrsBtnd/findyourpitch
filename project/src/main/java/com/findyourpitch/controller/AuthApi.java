package com.findyourpitch.controller;

import com.findyourpitch.entities.AuthRequest;
import com.findyourpitch.entities.User;
import com.findyourpitch.repository.UserRepository;
import com.findyourpitch.security.JwtTokenUtil;
import com.findyourpitch.services.CustomAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthApi {

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    private CustomAuthenticationManager authenticationManager = new CustomAuthenticationManager();

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody AuthRequest authRequest) {
        try {
            System.out.println(authRequest.getPassword() + " " + authRequest.getUserName());
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken
                            (authRequest.getUserName(), authRequest.getPassword()));

            User user = (User) authentication.getPrincipal();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                    .body(user);
        } catch (BadCredentialsException exception) {
            System.out.println("zdgdgaga");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
