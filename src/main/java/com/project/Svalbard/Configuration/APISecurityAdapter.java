package com.project.Svalbard.Configuration;

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
public class APISecurityAdapter extends WebSecurityConfigurerAdapter {


    @Autowired
    private AgentAuthProvider agentAuthProvider;

    //TODO: This filter should be developed for multiple headers
    //@Autowired
    //private CustomAgentAuthProvider customAgentAuthProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(agentAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        AgentFilter agentFilter = new AgentFilter();
        //CustomAgentFilter customAgentFilter = new CustomAgentFilter();
        agentFilter.setAuthenticationManager(authenticationManager());
        //customAgentFilter.setAuthenticationManager(authenticationManager());

        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/authenticate").permitAll()
                .antMatchers("/api/**").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authenticationProvider(agentAuthProvider);
        //httpSecurity.authenticationProvider(customAgentAuthProvider);

        httpSecurity.cors().disable();

        httpSecurity.addFilterBefore(agentFilter, UsernamePasswordAuthenticationFilter.class);
        //httpSecurity.addFilterBefore(customAgentFilter, UsernamePasswordAuthenticationFilter.class);
    }


}