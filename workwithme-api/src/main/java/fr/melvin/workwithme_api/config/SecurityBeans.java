package fr.melvin.workwithme_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class for defining security-related beans.
 *
 * <p>
 * This class provides the following beans:
 * <ul>
 *   <li>{@link PasswordEncoder} bean used to encode user passwords before saving them in the database and to check if a raw password matches an encoded password</li>
 * </ul>
 * </p>
 */
@Configuration
public class SecurityBeans {
    
    /**
     * Defines the password encoder bean used to encode user passwords before saving them in the database
     * and to check if a raw password matches an encoded password.
     * @return A {@link BCryptPasswordEncoder} instance.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
