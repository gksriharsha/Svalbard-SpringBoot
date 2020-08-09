package com.project.Svalbard.Model.Requests;

import java.util.Date;

public class AuthenticationResponse {

    private String jwt;
    private String Username;
    private Date ExpirationDate;

    public AuthenticationResponse(String jwt, String username, Date expirationDate) {
        this.jwt = jwt;
        Username = username;
        ExpirationDate = expirationDate;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public Date getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        ExpirationDate = expirationDate;
    }
}
