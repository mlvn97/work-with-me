package fr.melvin.workwithme_api.controller;

import org.springframework.web.bind.annotation.RestController;

import fr.melvin.workwithme_api.dto.request.UserLoadRequest;
import fr.melvin.workwithme_api.dto.request.UserRegisterRequest;
import fr.melvin.workwithme_api.enums.Role;
import fr.melvin.workwithme_api.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import fr.melvin.workwithme_api.model.User;
import lombok.AllArgsConstructor;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller responsible of handling HTTP requests related to users.
 * 
 * <p>
 * Provides endpoints to:
 * <ul>
 *   <li>Register an user in the database.</li>
 *   <li>Load an user from the database.</li>
 * </ul>
 * </p>
 * 
 * @see UserService
 */
@AllArgsConstructor
@RestController
public class UserController {
    
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authManager;

    /**
     * Registers an user in the database.
     * @param userRequest The {@link UserRegisterRequest} containing user data.
     * @param role The user {@link Role}, either USER or COMPANY.
     * @return A {@link ResponseEntity} containing the user created.
     * @apiNote <code>409 CONFLICT</code> An user with this email already exists.
     * @apiNote <code>201 CREATED</code> The user has been created successfully.
     */
    @PostMapping("/auth/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegisterRequest userRequest, @RequestParam Role role) {
        // Checks if the given email is available
        if (userService.userExists(userRequest.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User with this email already exists.");
        }

        // Initializes a User instance
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(role);
        user.setDomain(userRequest.getDomain());
        user.setProfession(userRequest.getProfession());
        user.setCompanyId(userRequest.getCompanyId());

        userService.registerUser(user);                       // Registers the user
        URI location = URI.create("/users/" + user.getId());  // Creates an URI for the new resource created
        
        return ResponseEntity.created(location).body(user);
    }

    /**
     * Loads an user from the database.
     * @param userLoadRequest The {@link UserLoadRequest} containing the user email and password.
     * @return A {@link ResponseEntity} with the appropriate HTTP status code.
     * @apiNote <code>401 UNAUTHORIZED</code> The given credentials are invalid.
     * @apiNote <code>200 OK</code> The user has been loaded successfully.
     */
    @PostMapping("/auth/login")
    public ResponseEntity<?> loadUser(@Valid @RequestBody UserLoadRequest userLoadRequest, HttpServletRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoadRequest.getEmail(), userLoadRequest.getPassword())
            );
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);

            request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }
    }
    
}
