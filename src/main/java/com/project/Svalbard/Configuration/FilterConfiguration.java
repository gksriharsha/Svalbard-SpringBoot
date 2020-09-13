package com.project.Svalbard.Configuration;

import com.project.Svalbard.Filters.AgentFilter;
import com.project.Svalbard.Filters.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

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
