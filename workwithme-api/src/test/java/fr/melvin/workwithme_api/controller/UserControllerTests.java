package fr.melvin.workwithme_api.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.melvin.workwithme_api.model.User;
import fr.melvin.workwithme_api.service.UserService;

/**
 * Tests for the UserController endpoints.
 */
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private PasswordEncoder passwordEncoder;

    @MockitoBean
    private AuthenticationManager authManager;

    private User user;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1);
        user.setEmail("user1@test.com");
        user.setPassword("test_password");
    }

    @Test
    void registerUserReturnsCreated() throws Exception {
        when(userService.userExists("user1@test.com")).thenReturn(false);
        mockMvc.perform(post("/auth/register?role=USER")
                .with(csrf())
                .with(user(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "name": "User 1",
                        "email": "user1@test.com",
                        "password": "test_password"
                    }
                    """
                ))
                .andExpect(status().isCreated());
    }

    @Test
    void registerUserReturnsConflict() throws Exception {
        when(userService.userExists("user1@test.com")).thenReturn(true);
        mockMvc.perform(post("/auth/register?role=USER")
                .with(csrf())
                .with(user(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "name": "User 1",
                        "email": "user1@test.com",
                        "password": "test_password"
                    }
                    """
                ))
                .andExpect(status().isConflict());
    }

    @Test
    void loadUserReturnsOk() throws Exception {
        // when(userService.loadUserByEmail("user1@test.com")).thenReturn(user);
        mockMvc.perform(post("/auth/login")
                .with(csrf())
                .with(user(user))
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    """
                    {
                        "email": "user1@test.com",
                        "password": "test_password"
                    }
                    """
                ))
                .andExpect(status().isOk());
    }

    // TODO: Get user by ID
    
}
