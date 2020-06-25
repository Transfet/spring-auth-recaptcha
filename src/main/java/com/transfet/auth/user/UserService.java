package com.transfet.auth.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Service for the different operation of a user.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public User getUserByUserName(String userName) {
        Optional<User> user = userRepository.getUserByUserName(userName);

        if (user.isEmpty()) {
            log.error("Could not find user for user name={}", userName);
            throw new UsernameNotFoundException("User not found");
        }

        return user.get();
    }

    public LocalDateTime getUserLastLoggedInTimeByUserName(String userName) {
        return userRepository.getLastLoggedInTimeByUserName(userName);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
