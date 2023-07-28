package com.example.zproject.support;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
@CrossOrigin
public class SecurityConfiguration implements WebMvcConfigurer {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(t->t.disable());
        http.sessionManagement(t->t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.anonymous(t->t.disable());
        http.authorizeHttpRequests(t-> t //
                .requestMatchers("/**").permitAll() );
        http.formLogin(Customizer.withDefaults());
        http.cors(t->t.disable());
        return http.build();
    }

}
