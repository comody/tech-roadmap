package com.project.techroadmap.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            if (token == null) {
                request.setAttribute("exception", "ExpiredJwtException");
            }

            if (!jwtTokenProvider.validateToken(token)) {
                request.setAttribute("exception", "ExpiredJwtException");
            }
        } catch (AuthenticationException authenticationException){
            SecurityContextHolder.clearContext();
            restAuthenticationEntryPoint.commence((HttpServletRequest) request, (HttpServletResponse) response, authenticationException);
        }

        chain.doFilter(request, response);
    }
}