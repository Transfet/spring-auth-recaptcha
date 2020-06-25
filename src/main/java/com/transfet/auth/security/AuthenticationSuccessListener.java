package com.transfet.auth.security;

import com.transfet.auth.DateTimeService;
import com.transfet.auth.user.User;
import com.transfet.auth.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * Custom application listener that update the logged in timestamp, after the successful login attempt.
 */
@Component
@RequiredArgsConstructor
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private final UserService userService;
    private final DateTimeService dateTimeService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String userName = ((UserDetails) event.getAuthentication()
                .getPrincipal()).getUsername();

        User user = userService.getUserByUserName(userName);
        user.setLastLoggedInTime(dateTimeService.currentUTCLocalDateTime());

        userService.saveUser(user);
    }
}
