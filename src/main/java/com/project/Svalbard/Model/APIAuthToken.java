package com.project.Svalbard.Model;

import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import javax.security.auth.Subject;

public class APIAuthToken extends PreAuthenticatedAuthenticationToken {

    private String key;
    private String program;

    public APIAuthToken(Object aPrincipal, Object aCredentials) {
        super(aPrincipal, aCredentials);
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
