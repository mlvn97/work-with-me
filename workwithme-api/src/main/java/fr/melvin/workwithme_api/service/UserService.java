package fr.melvin.workwithme_api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.melvin.workwithme_api.model.User;
import fr.melvin.workwithme_api.repository.UserRepository;
import lombok.AllArgsConstructor;

/**
 * Service class responsible for handling user-related operations, such as loading user details for authentication,
 * registering new users, and checking if a user already exists.
 * 
 * <p>
 * Implements the {@link UserDetailsService} interface to provide user details for Spring Security authentication.
 * </p>
 * 
 * @see UserRepository
 */
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name);
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public void registerUser(User user) {
        userRepository.save(user);
    }

    public boolean userExists(String email) {
        return userRepository.findByEmail(email) != null;
    }
    
}
