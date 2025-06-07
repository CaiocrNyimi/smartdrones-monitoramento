package br.com.fiap.smartdrones.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                    "/api/auth/**",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/api-docs/**"
                ).permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/drones/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/sensors/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/leituras/**").permitAll()
                .requestMatchers("/api/users/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/drones/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/drones/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/drones/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/sensors/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/sensors/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/sensors/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/leituras/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/leituras/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/leituras/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}