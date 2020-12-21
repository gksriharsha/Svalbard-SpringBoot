package com.project.Svalbard.Configuration;

import com.project.Svalbard.Filters.AgentAuthenticationSuccessHandler;
import com.project.Svalbard.Filters.AgentFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(1)
public class ApiSecurityAdapter extends WebSecurityConfigurerAdapter {


    @Autowired
    private AgentAuthProvider agentAuthProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(agentAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        AgentFilter agentFilter = new AgentFilter();
        agentFilter.setAuthenticationManager(authenticationManager());
        agentFilter.setAuthenticationSuccessHandler(new AgentAuthenticationSuccessHandler());

        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/ping").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authenticationProvider(agentAuthProvider);

        httpSecurity.cors().disable();

        httpSecurity.addFilterBefore(agentFilter, UsernamePasswordAuthenticationFilter.class);
    }


}