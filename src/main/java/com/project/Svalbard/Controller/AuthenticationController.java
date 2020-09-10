package com.project.Svalbard.Controller;

import com.project.Svalbard.Model.Requests.ApiAuthenticationRequest;
import com.project.Svalbard.Model.Requests.ApiAuthenticationResponse;
import com.project.Svalbard.Model.Requests.AuthenticationRequest;
import com.project.Svalbard.Model.Requests.AuthenticationResponse;
import com.project.Svalbard.Service.AgentAuthService;
import com.project.Svalbard.Service.MyUserDetailsService;
import com.project.Svalbard.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private AgentAuthService agentAuthService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/app/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAppAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Username or Password");
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt, authenticationRequest.getUsername(), jwtUtil.extractExpiration(jwt)));
    }

    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createApiAuthenticationToken(@RequestBody ApiAuthenticationRequest authenticationRequest) throws Exception {
        try {
            if (agentAuthService.authenticateAgent(authenticationRequest)) {
                String token = agentAuthService.generateAPItoken(authenticationRequest.getPlatform());
                return ResponseEntity.ok(new ApiAuthenticationResponse(token));
            }
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Username or Password");
        }

        return ResponseEntity.badRequest().build();
    }
}
