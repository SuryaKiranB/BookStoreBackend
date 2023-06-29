package com.example.RegAndLoginApi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/auth/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
                registry.addMapping("/demo/hi")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET","POST", "PUT", "DELETE")
                        .allowedHeaders("*");
                registry.addMapping("/order-store/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
                registry.addMapping("/book-store/**")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("GET","POST", "PUT", "DELETE")
                        .allowedHeaders("*");
//                registry.addMapping("/api/v1/auth/**")
//                        .allowedOrigins("*")  // Allow requests from any origin
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*");
//                registry.addMapping("/demo/hi")
//                        .allowedOrigins("*")  // Allow requests from any origin
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*");
//                registry.addMapping("/order-details/**")
//                        .allowedOrigins("*")  // Allow requests from any origin
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*");
//                registry.addMapping("/book-store/**")
//                        .allowedOrigins("*")  // Allow requests from any origin
//                        .allowedMethods("GET", "POST", "PUT", "DELETE")
//                        .allowedHeaders("*");
            }
        };
    }
}
