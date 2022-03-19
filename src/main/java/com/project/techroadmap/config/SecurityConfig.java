package com.project.techroadmap.config;

import com.project.techroadmap.config.jwt.JwtTokenProvider;
import com.project.techroadmap.config.jwt.JwtConfig;
import com.project.techroadmap.config.jwt.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v1/careers/new").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()
                .antMatchers("/swagger-ui/index.html", "/swagger-ui/**", "/swagger/**", "/swagger-resources/**", "/webjars/**", "/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .apply(new JwtConfig(jwtTokenProvider, restAuthenticationEntryPoint));
    }
}