package com.findyourpitch.services;

import com.findyourpitch.entities.User;
import com.findyourpitch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Transient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaRepositories
public class CustomAuthenticationManager implements AuthenticationManager {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userName = String.valueOf(authentication.getPrincipal());
        String password = String.valueOf(authentication.getCredentials());
        //System.out.println(userName);
        UserDetails user = userDetailsService().loadUserByUsername(userName);
        if(user == null) {
            throw new BadCredentialsException("User with username " + userName + " not found");
        }

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Password don't match");
        }

        return new UsernamePasswordAuthenticationToken(userName, passwordEncoder.encode(password));
    }

    @Bean
    public MyUserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
}
