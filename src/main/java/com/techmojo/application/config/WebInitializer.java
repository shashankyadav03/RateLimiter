package com.techmojo.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.techmojo.RateLimiterApplication;

public class WebInitializer extends SpringBootServletInitializer {   
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(RateLimiterApplication.class);
    }    
}
