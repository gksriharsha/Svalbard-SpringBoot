package com.project.Svalbard.Configuration;

import com.project.Svalbard.Dao.APIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class AgentAuthProvider implements AuthenticationProvider {

    @Autowired
    private APIRepository apiRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String principal = (String) authentication.getPrincipal();
        String credentials = (String) authentication.getCredentials();
        if (apiRepository.findByProgram(principal).getProgram().equals(principal) && apiRepository.findByProgram(principal).getApikey().equals(credentials)) {
            authentication.setAuthenticated(true);
            return authentication;
        }
        throw new BadCredentialsException("The API key was not found or not the expected value.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(PreAuthenticatedAuthenticationToken.class);
    }
}
