package com.transfet.auth.security;

import com.transfet.auth.user.User;
import com.transfet.auth.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * User details service for populating an instance of {@link UserDetails} with the necessary information.
 * If a user has multiple roles the authorities will be the permissions's union
 * @see Role
 * @see Permission
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userService.getUserByUserName(username);

        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .password(user.getPassword())
                .authorities(getAuthoritiesByRoles(user.getRoles()))
                .build();
    }

    private List<GrantedAuthority> getAuthoritiesByRoles(Set<Role> roles) {

        return roles.stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream)
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());

    }
}
