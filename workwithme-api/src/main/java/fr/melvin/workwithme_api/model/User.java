package fr.melvin.workwithme_api.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a user in the system, which can be either a worker or a company.
 * 
 * <p>
 * Implements the {@link UserDetails} interface to integrate with Spring Security for authentication and authorization.
 * </p>
 * 
 * <p>
 * The user has the following attributes:
 * <ul>
 *   <li><b>id</b>: The unique identifier of the user.</li>
 *   <li><b>role</b>: The role of the user, either "USER" for workers or "COMPANY" for companies. This field is required.</li>
 *   <li><b>name</b>: The name of the user. This field is required.</li>
 *   <li><b>password</b>: The password of the user. This field is required.</li>
 *   <li><b>email</b>: The email of the user. This field is required.</li>
 *   <li><b>description</b>: A description of the user.</li>
 *   <li><b>profession</b>: The profession of the user, applicable only for workers.</li>
 *   <li><b>companyId</b>: The identifier of the company associated with the user, applicable only for workers.</li>
 *   <li><b>domain</b>: The working domain of the user, applicable only for companies.</li>
 * </ul>
 * </p>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
class User implements UserDetails {

    /** The user id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The user role, either USER or COMPANY. Can not be null. */
    @NotNull
    @Column(nullable = false)
    private String role;

    /** The user name. Can not be null. */
    @NotBlank
    @Column(nullable = false)
    private String name;

    /** The user password. Can not be null. */
    @NotBlank
    @Column(nullable = false)
    private String password;

    /** The user email. Can not be null. */
    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;

    /** The user description. */
    private String description;

    /** The user profession, only for workers. */
    private String profession;

    /** The company id associated to the user, only for workers. */
    private long companyId;

    /** The user working domain, only for companies. */
    private String domain;

    /**
     * Returns the user's name.
     * @return The name of the user.
     */
    @Override
    public String getUsername() {
        return this.name;
    }

    /**
     * Returns the user's password.
     * @return The password of the user.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Returns the authorities granted to the user.
     * @return A collection of authorities granted to the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role));
    }
    
}
