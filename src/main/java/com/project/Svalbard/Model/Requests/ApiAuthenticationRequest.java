package com.project.Svalbard.Model.Requests;

public class ApiAuthenticationRequest {

    private String platform;
    private String APIkey;
    private String token;

    public ApiAuthenticationRequest() {
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAPIkey() {
        return APIkey;
    }

    public void setAPIkey(String APIkey) {
        this.APIkey = APIkey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
