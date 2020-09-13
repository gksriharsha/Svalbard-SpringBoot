package com.project.Svalbard.Controller;

import com.project.Svalbard.Model.Requests.ApiAuthenticationRequest;
import com.project.Svalbard.Model.Requests.ApiAuthenticationResponse;
import com.project.Svalbard.Model.Requests.AuthenticationRequest;
import com.project.Svalbard.Model.Requests.AuthenticationResponse;
import com.project.Svalbard.Service.AgentAuthService;
import com.project.Svalbard.Service.MyUserDetailsService;
import com.project.Svalbard.Util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(tags = "Authentication Endpoint", value = "Authentication endpoint", description = "Endpoint for authenticating users and agents")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AgentAuthService agentAuthService;

    @Autowired
    private JwtUtil jwtUtil;

    @ApiOperation("Application Authentication endpoint")
    @RequestMapping(value = "/app/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> createAppAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, authenticationRequest.getUsername(), jwtUtil.extractExpiration(jwt)));
    }

    @ApiOperation("Agents Authentication endpoint")
    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<ApiAuthenticationResponse> createApiAuthenticationToken(@RequestBody ApiAuthenticationRequest authenticationRequest) {
        try {
            if (agentAuthService.authenticateAgent(authenticationRequest)) {
                String token = agentAuthService.generateAPItoken(authenticationRequest.getPlatform());
                return ResponseEntity.ok(new ApiAuthenticationResponse(token));
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return null;
    }
}
