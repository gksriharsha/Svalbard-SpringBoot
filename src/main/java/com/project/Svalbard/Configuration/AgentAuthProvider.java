package com.project.Svalbard.Configuration;

import com.project.Svalbard.Model.APIAuthToken;
import com.project.Svalbard.Service.AgentAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AgentAuthProvider implements AuthenticationProvider {

    @Autowired
    private AgentAuthService authService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String principal = (String) authentication.getPrincipal();
        String credentials = (String) authentication.getCredentials();
        if (authService.verifyAgent(principal, credentials)) {
            authentication.setAuthenticated(true);
            return authentication;
        }
        throw new BadCredentialsException("The API key was not found or not the expected value.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(APIAuthToken.class);
    }
}
