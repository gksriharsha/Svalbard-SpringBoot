package com.project.Svalbard.Configuration;

import com.project.Svalbard.Dao.APIRepository;
import com.project.Svalbard.Model.AgentAuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAgentAuthProvider implements AuthenticationProvider {

    @Autowired
    private APIRepository apiRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AgentAuthToken token = (AgentAuthToken) authentication;
        String principal = (String) token.getPrincipal();
        String credentials = (String) token.getCredentials();
        if (apiRepository.findByProgram(principal).getProgram().equals(principal) &&
                apiRepository.findByProgram(principal).getApikey().equals(credentials) &&
                "1".equals(token.getToken())) {
            authentication.setAuthenticated(true);
            return authentication;
        }
        throw new BadCredentialsException("The API key was not found or not the expected value.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(AgentAuthToken.class);
    }
}
