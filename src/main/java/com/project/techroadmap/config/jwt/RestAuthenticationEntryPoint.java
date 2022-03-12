package com.project.techroadmap.config.jwt;

import com.project.techroadmap.constant.ErrorCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String) request.getAttribute("exception");
        ErrorCode errorCode;

        if(exception == null) {
            errorCode = ErrorCode.UNAUTHORIZEDException;
            response.sendError(errorCode.getCode(), errorCode.getMessage());
        } else if(exception.equals("ExpiredJwtException")) {
            errorCode = ErrorCode.ExpiredJwtException;
            response.sendError(errorCode.getCode(), errorCode.getMessage());
        }
    }
}