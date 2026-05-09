package fr.melvin.workwithme_api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.melvin.workwithme_api.model.User;
import fr.melvin.workwithme_api.repository.UserRepo;
import lombok.AllArgsConstructor;

/**
 * Service class responsible for handling user-related operations, such as loading user details for authentication,
 * registering new users, and checking if a user already exists.
 * 
 * <p>
 * Implements the {@link UserDetailsService} interface to provide user details for Spring Security authentication.
 * </p>
 * 
 * @see UserRepo
 */
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        // TODO
        throw new UnsupportedOperationException("Unimplemented method 'loadUserByEmail'");
    }

    public void registerUser(User user) {
        // TODO
    }

    public boolean userExists(String email) {
        // TODO
        return false;
    }
    
}
