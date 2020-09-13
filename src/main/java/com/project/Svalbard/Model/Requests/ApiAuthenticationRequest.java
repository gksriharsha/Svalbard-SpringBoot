package com.project.Svalbard.Model.Requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "Agents are authenticated using unique authentication algorithm through this endpoint")
public class ApiAuthenticationRequest {

    @NotNull
    @ApiModelProperty(value = "Platform is the language in which the task is performed.", allowableValues = "Python,R,Octave", required = true)
    private String platform;
    @NotNull
    @ApiModelProperty(value = "A unique key will be given to each of the platform", dataType = "UUID", required = true)
    private String apikey;
    @NotNull
    @ApiModelProperty(value = "Token is currently not being used but will be used in custom authentication.")
    private String token;

    public ApiAuthenticationRequest() {
    }

    public String getPlatform() {
        return platform;
    }

    public String getApikey() {
        return apikey;
    }

    public String getToken() {
        return token;
    }
}
