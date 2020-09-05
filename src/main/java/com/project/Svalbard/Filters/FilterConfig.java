package com.project.Svalbard.Filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<AgentFilter> apiBean() {
        FilterRegistrationBean<AgentFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AgentFilter());
        registrationBean.addUrlPatterns("/api/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<JwtFilter> appBean() {
        FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/app/*");
        return registrationBean;
    }
}
