package fr.melvin.workwithme_api.dto.response;

import fr.melvin.workwithme_api.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO for user information response.
 * This class encapsulates the data returned when retrieving user information, including their name, profession, domain, and company ID.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    
    private String name;

    private String profession;  // Workers only

    private String domain;      // Companies only

    private long companyId;   // Workers only

    private Role role;

}
