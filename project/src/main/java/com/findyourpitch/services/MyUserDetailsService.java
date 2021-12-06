package com.findyourpitch.services;

import com.findyourpitch.entities.User;
import com.findyourpitch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUserName(userName);
            return user;
        } catch (Exception exception) {
            throw new UsernameNotFoundException("User with username " + userName + " not found");
        }
    }

}
