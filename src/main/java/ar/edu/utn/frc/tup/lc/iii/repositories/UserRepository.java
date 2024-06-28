package ar.edu.utn.frc.tup.lc.iii.repositories;

import ar.edu.utn.frc.tup.lc.iii.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find a user by email in the database.
     *
     * @param email The email to search
     * @return An optional with the user if it exists
     */
    Optional<UserEntity> findByEmail(String email);

    /**
     * Find a user by email and password in the database.
     *
     * @param email
     * @param password
     * @return An optional with the user if it exists
     */
    Optional<UserEntity> findByEmailAndPassword(String email, String password);
}
