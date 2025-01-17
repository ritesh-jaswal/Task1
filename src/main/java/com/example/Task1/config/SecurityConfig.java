package com.example.Task1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( requests->
                        requests.requestMatchers("/v3/api-docs/**", "/swagger-ui.html/**", "/swagger-ui/**")
                                .permitAll()
                                .anyRequest().authenticated());

        http.sessionManagement(session
                ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.headers(headers->
                        headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}