package com.findyourpitch.services;

import com.findyourpitch.entities.User;
import com.findyourpitch.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {
        try {
            List<User> user = userRepository.findAll();
            System.out.println(user.get(0).getUsername());
            return user.get(0);
        } catch (Exception exception) {
            throw new UsernameNotFoundException("User with username " + userName + " not found" + exception.getMessage());
        }
    }

}
