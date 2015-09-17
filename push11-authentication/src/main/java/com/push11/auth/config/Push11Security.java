package com.push11.auth.config;

import com.push11.auth.filter.Push11SSORequestHeaderAuthenticationFilter;
import com.push11.auth.service.Push11UserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
@EnableWebMvcSecurity
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class Push11Security extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(Push11Security.class);


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(ssoFilter(), RequestHeaderAuthenticationFilter.class)
                .authenticationProvider(
                        preAuthProvider())
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(preAuthProvider());
    }

    @Bean
    public UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> userDetailsServiceWrapper() {
        UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken> wrapper =
                new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>();
        wrapper.setUserDetailsService(new Push11UserDetailsService());
        return wrapper;
    }

    @Bean
    public PreAuthenticatedAuthenticationProvider preAuthProvider() {
        PreAuthenticatedAuthenticationProvider preAuthProvider =
                new PreAuthenticatedAuthenticationProvider();
        preAuthProvider.setPreAuthenticatedUserDetailsService(userDetailsServiceWrapper());
        return preAuthProvider;
    }

    @Bean
    public Push11SSORequestHeaderAuthenticationFilter ssoFilter() throws Exception {
        Push11SSORequestHeaderAuthenticationFilter filter = new Push11SSORequestHeaderAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

}
