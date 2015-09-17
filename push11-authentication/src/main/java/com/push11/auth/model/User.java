package com.push11.auth.model;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

//TODO turgay : user mongo or eh cache
public class User implements UserDetails {

    private static final long serialVersionUID = 1924132530173910545L;

    private Collection authorities;
    private String password;
    private String username;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, Collection authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    //NOTE If any of these things can be retrieved from your SSO Authentiation point, you may want to use them.

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