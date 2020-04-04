package com.pink.unicorn.repositories;

import com.pink.unicorn.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Andrii Hubarenko
 * Spring JPA Repository for access to user's entitites in DB
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
