package com.project.Svalbard.Model.Requests;

import javax.validation.constraints.NotNull;

public class ApiAuthenticationResponse {

    @NotNull
    private String token;

    public ApiAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
