package com.roleplace.voicechat.services;

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
                registry.addMapping("/**")  // разрешить все эндпоинты
                        .allowedOrigins("*")  // разрешить любые источники
                        .allowedMethods("*")  // разрешить все HTTP методы
                        .allowedHeaders("*")  // разрешить все заголовки
                        .allowCredentials(false);  // если не используете куки/авторизацию
            }
        };
    }
}