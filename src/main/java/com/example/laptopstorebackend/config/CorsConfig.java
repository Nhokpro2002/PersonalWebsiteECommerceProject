package com.example.laptopstorebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Cho phép tất cả API có đường dẫn "/api/"
                .allowedOrigins("http://localhost:8081") // Cho phép frontend truy cập
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Các method HTTP cho phép
                .allowedHeaders("*"); // Cho phép tất cả headers
    }
}
