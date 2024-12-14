package cz.uhk.pproprojektexpensetracker.repository;

import cz.uhk.pproprojektexpensetracker.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
