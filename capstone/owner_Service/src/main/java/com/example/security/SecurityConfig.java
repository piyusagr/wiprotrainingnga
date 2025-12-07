package com.example.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	// JWT Authentication filter injection
    private final JwtAuthenticationFilter jwtAuthFilter;
    // OwnerDetailsService injection
    private final OwnerDetailsService ownerDetailsService;

    // Security filter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	// Disable CSRF, configure URL authorization, session management, user details service, and JWT filter
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/owner/login",
                        "/owner/register",
                    "/owner/categories/**",
                    "/owner/apps/public",
                    "/owner/apps/{id}/downloaders",
                    "/owner/apps/{id}/**",
                    "/owner/apps/search",
                        "/owner/auth/login",
                        "/owner/auth/register",
                        "/h2-console/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/webjars/**",
                        "/css/**",
                        "/js/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(sess ->
                    sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .userDetailsService(ownerDetailsService)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Fix H2 Console issue
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    // Authentication manager bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Password encoder bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
