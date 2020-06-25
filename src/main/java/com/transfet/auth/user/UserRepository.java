package com.transfet.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Repository for {@link User}.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUserName(String userName);

    @Query(value = "select u.lastLoggedInTime from User u where u.userName = ?1")
    LocalDateTime getLastLoggedInTimeByUserName(String userName);
}
