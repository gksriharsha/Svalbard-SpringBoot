package com.project.Svalbard.Model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import javax.security.auth.Subject;

public class AgentAuthToken extends AbstractAuthenticationToken {

    private String key;
    private String program;
    private String token;

    public AgentAuthToken(String program, String key, String token) {
        super(null);
        this.key = key;
        this.program = program;
        this.token = token;
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

    public String getToken() {
        return this.token;
    }
}
