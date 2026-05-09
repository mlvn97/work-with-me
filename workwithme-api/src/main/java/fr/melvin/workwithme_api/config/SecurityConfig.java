package fr.melvin.workwithme_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;

/**
 * Configuration class for setting up security-related beans.
 */
@AllArgsConstructor
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    /**
     * Defines the authentication manager bean used to authenticate users in the application.
     * @param http The {@link HttpSecurity} object used to configure the security of the application.
     * @param userService The {@link UserDetailsService} used to load user details for authentication.
     * @param passwordEncoder The {@link PasswordEncoder} used to encode user passwords for authentication.
     * @return An {@link AuthenticationManager} instance.
     * @throws Exception
     */
    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http, UserDetailsService userService, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authBuilder
            .userDetailsService(userService)
            .passwordEncoder(passwordEncoder);

        return authBuilder.build();
    }
}
