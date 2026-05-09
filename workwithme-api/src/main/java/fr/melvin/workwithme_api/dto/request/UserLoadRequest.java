package fr.melvin.workwithme_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for user loading request.
 * This class encapsulates the data required to load an user by its email and password.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoadRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
    
}
