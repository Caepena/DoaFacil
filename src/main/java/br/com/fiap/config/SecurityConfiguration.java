package br.com.fiap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests( auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2Login(login -> login.defaultSuccessUrl("/campanhas"))
                .build();
    }

}
