package com.project.Svalbard.Service;

import com.project.Svalbard.Dao.APIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    @Autowired
    private APIRepository apiRepository;

    public String getKey(String program) {
        return apiRepository.findByProgram(program).getApikey();
    }
}
