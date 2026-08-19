package fr.melvin.workwithme_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.melvin.workwithme_api.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Finds a user by their email address.
     *
     * @param email the email address of the user to find
     * @return the user with the specified username, or null if no such user exists
     */
    public User findByEmail(String email);

    /**
     * Finds a user by their name.
     * @param name the name of the user to find
     * @return the user with the specified name, or null if no such user exists
     */
    public User findByName(String name);

    /**
     * Saves a user to the database.
     *
     * @param user the user to save
     * @return the saved user
     */
    public <S extends User> S save(S user);

    /**
     * Deletes a user from the database.
     *
     * @param user the user to delete
     */
    public void delete(User user);
}
