package com.findyourpitch.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.findyourpitch.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private int userID;

    private String firstName;

    private String lastName;

    private int userAge;

    @JsonIgnore
    private String userPassword;

    private String userName;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Integer userID, String firstName, String lastName, int userAge,
                           String userPassword, String userName, Collection<? extends GrantedAuthority> authorities) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAge = userAge;
        this.userPassword = userPassword;
        this.userName = userName;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole()));

        return new UserDetailsImpl(
                user.getUserID(), user.getFirstName(), user.getLastName(),
                user.getUserAge(), user.getPassword(), user.getUsername(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
