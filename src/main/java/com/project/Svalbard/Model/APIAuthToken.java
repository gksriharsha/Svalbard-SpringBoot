package com.project.Svalbard.Model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import javax.security.auth.Subject;

public class APIAuthToken extends AbstractAuthenticationToken {

    private String key;
    private String program;

    public APIAuthToken(String APIkey, String program) {
        super(null);
        this.key = APIkey;
        this.program = program;
        super.setAuthenticated(true);
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }

    @Override
    public Object getCredentials() {
        return this.key;
    }

    @Override
    public Object getPrincipal() {
        return this.program;
    }
}
