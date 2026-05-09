package fr.melvin.workwithme_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for user registration request.
 * This class encapsulates the data required to register a new user, including their name, email, and password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    private String profession;  // Workers only

    private String domain;      // Companies only

    private long companyId;     // Workers only

}
