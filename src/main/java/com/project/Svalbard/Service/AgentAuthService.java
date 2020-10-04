package com.project.Svalbard.Service;

import com.project.Svalbard.Annotations.LogExecutionTime;
import com.project.Svalbard.Dao.APIRepository;
import com.project.Svalbard.Model.Requests.ApiAuthenticationRequest;
import com.project.Svalbard.Model.db.API;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AgentAuthService {
    @Autowired
    private APIRepository apiRepository;

    private ConcurrentHashMap<String, String> agentAccess = new ConcurrentHashMap<>();

    public String getKey(String program) {
        return apiRepository.findByProgram(program).getApikey();
    }

    @LogExecutionTime
    public boolean authenticateAgent(ApiAuthenticationRequest request) throws BadCredentialsException {
        try {
            API api = apiRepository.findByProgram(request.getPlatform());
            return api.getApikey().equals(request.getApikey());
        } catch (Exception e) {
            throw new BadCredentialsException("Agent not found");
        }
    }

    public String generateAPItoken(String program) {
        String token = UUID.randomUUID().toString();

        agentAccess.put(program, token);

        return token;
    }
    @LogExecutionTime
    public boolean verifyAgent(String program, String token) {
        return agentAccess.containsKey(program) && agentAccess.get(program).equals(token);
    }
}
