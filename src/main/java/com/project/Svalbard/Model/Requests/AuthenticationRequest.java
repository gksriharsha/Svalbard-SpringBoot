package com.project.Svalbard.Model.Requests;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel(description = "Authentication Request is submitted by the app to authorize the user using JWT authentication")
public class AuthenticationRequest implements Serializable {

    @NotNull
    @Size(min = 6)
    @ApiModelProperty(value = "Username must be a minimum of 6 characters", required = true)
    private String username;

    @NotNull
    @Size(min = 6)
    @ApiModelProperty(value = "Password must be a minimum of 6 characters", required = true)
    private String password;

    public AuthenticationRequest() {
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }
}
