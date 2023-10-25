package com.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();

        try {
            authCredentials = new ObjectMapper().readValue(httpServletRequest.getReader(), AuthCredentials.class);
        } catch (IOException exception) {
        }

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authCredentials.getEmailUser(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );

        return getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authResult.getPrincipal();
        String token = CohelumToken.createToken(userDetailsImpl.getUsername(), userDetailsImpl.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }
}
