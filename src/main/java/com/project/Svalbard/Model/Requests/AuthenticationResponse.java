package com.project.Svalbard.Model.Requests;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class AuthenticationResponse {
    @NotNull
    private String jwt;
    @NotNull
    private String Username;
    @NotNull
    private Date ExpirationDate;

    public AuthenticationResponse(String jwt, String username, Date expirationDate) {
        this.jwt = jwt;
        Username = username;
        ExpirationDate = expirationDate;
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

    public String getJwt() {

        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
