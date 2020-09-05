package com.project.Svalbard.Filters;

import com.project.Svalbard.Model.AgentAuthToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAgentFilter extends AbstractAuthenticationProcessingFilter {

    public CustomAgentFilter() {
        super("/*");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (httpServletRequest.getHeader("Program").equals("Python") && httpServletRequest.getHeader("APIkey").equals("1") && httpServletRequest.getHeader("token").equals("1")) {
            return this.getAuthenticationManager().authenticate(new AgentAuthToken("Python", "1", "1"));
        }
        return null;
    }

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }
}
