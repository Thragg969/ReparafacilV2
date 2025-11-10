package com.Reparafacil.ReparafacilV2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;              // 👈 importa esto
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {  // 👈 agrégalo aquí
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("*");
    }
}