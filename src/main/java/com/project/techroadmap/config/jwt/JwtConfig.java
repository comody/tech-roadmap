package com.project.techroadmap.config.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
    private final JwtTokenProvider jwtTokenProvider;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    public JwtConfig (JwtTokenProvider jwtTokenProvider, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Override
    public void configure(HttpSecurity builder) {
        JwtTokenFilter filter = new JwtTokenFilter(jwtTokenProvider, restAuthenticationEntryPoint);
        builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}