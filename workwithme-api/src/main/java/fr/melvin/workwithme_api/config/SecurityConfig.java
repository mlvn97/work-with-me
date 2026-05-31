package fr.melvin.workwithme_api.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.HttpServletResponse;
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

     /**
     * Defines the security filter chain bean used to configure the security of the application.
     * @param http The {@link HttpSecurity} object used to configure the security of the application.
     * @return A {@link SecurityFilterChain} instance.
     * @throws Exception If an error occurs while configuring the security of the application.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()
        )
        .logout(logout -> logout
        .logoutUrl("/auth/logout")
        .logoutSuccessHandler((request, response, authentication) -> {
            response.setStatus(HttpServletResponse.SC_OK);
        })
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .deleteCookies("JSESSIONID")
        )
        .httpBasic(Customizer.withDefaults());
        return http.build();
    }

     /**
     * Defines the CORS configuration source bean used to allow cross-origin requests from the frontend application.
     * @return A {@link CorsConfigurationSource} instance.
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(List.of("*"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
