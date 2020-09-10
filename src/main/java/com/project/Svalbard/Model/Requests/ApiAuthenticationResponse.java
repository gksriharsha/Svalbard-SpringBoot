package com.project.Svalbard.Model.Requests;

public class ApiAuthenticationResponse {
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
