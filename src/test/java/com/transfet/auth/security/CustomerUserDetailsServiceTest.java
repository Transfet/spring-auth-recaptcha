package com.transfet.auth.security;

import com.transfet.auth.user.User;
import com.transfet.auth.user.UserService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Unit test for {@link CustomUserDetailsService}.
 */
public class CustomerUserDetailsServiceTest {

    private static final String ROLE_1 = "ROLE_1";
    private static final long ROLE_ID_1 = 1L;
    private static final String ROLE_2 = "ROLE_2";
    private static final long ROLE_ID_2 = 2L;
    private static final String PERMISSION_1 = "PERMISSION_1";
    private static final long PERMISSION_ID_1 = 1L;
    private static final String PERMISSION_2 = "PERMISSION_2";
    private static final long PERMISSION_ID_2 = 2L;
    private static final String PERMISSION_3 = "PERMISSION_3";
    private static final long PERMISSION_ID_3 = 3L;
    private static final String PERMISSION_4 = "PERMISSION_4";
    private static final long PERMISSION_ID_4 = 4L;
    private static final String TEST_NAME = "test_name";
    private static final String TEST_PASSWORD = "test_password";
    private static final long NUMBER_OF_EXPECTED_AUTHORITIES = 4L;


    @Mock
    private UserService userService;

    private CustomUserDetailsService underTest;

    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        underTest = new CustomUserDetailsService(userService);
    }

    @Test
    public void testLoadUserByUsernameShouldLoadUserWithAuthorities() {

        // given
        List<String> expectedPermissionUnion = List.of(PERMISSION_1, PERMISSION_2, PERMISSION_3, PERMISSION_4);
        given(userService.getUserByUserName(TEST_NAME)).willReturn(createTestUser());

        // when
        UserDetails userDetails = underTest.loadUserByUsername(TEST_NAME);

        // then
        assertThat(userDetails.getPassword(), is(TEST_PASSWORD));
        assertThat(userDetails.getUsername(), is(TEST_NAME));
        assertThat(userDetails.getPassword(), is(TEST_PASSWORD));

        long numberOfContainedPermissions = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .filter(expectedPermissionUnion::contains)
                .count();


        assertThat(numberOfContainedPermissions, is(NUMBER_OF_EXPECTED_AUTHORITIES));
    }

    private User createTestUser() {
        return User.builder()
                .firstName(TEST_NAME)
                .lastName(TEST_NAME)
                .userName(TEST_NAME)
                .password(TEST_PASSWORD)
                .roles(createTestRoles())
                .build();
    }

    private Set<Role> createTestRoles() {
        Role role1 = Role.builder()
                .name(ROLE_1)
                .roleId(ROLE_ID_1)
                .permissions(createTestPermissionsForRole1())
                .build();

        Role role2 = Role.builder()
                .name(ROLE_2)
                .roleId(ROLE_ID_2)
                .permissions(createTestPermissionsForRole2())
                .build();

        return Set.of(role1, role2);
    }

    private Set<Permission> createTestPermissionsForRole1() {
        Permission permission1 = Permission.builder()
                .name(PERMISSION_1)
                .permissionId(PERMISSION_ID_1)
                .build();

        Permission permission2 = Permission.builder()
                .name(PERMISSION_2)
                .permissionId(PERMISSION_ID_2)
                .build();

        return Set.of(permission1, permission2);
    }

    private Set<Permission> createTestPermissionsForRole2() {
        Permission permission3 = Permission.builder()
                .name(PERMISSION_3)
                .permissionId(PERMISSION_ID_3)
                .build();

        Permission permission4 = Permission.builder()
                .name(PERMISSION_4)
                .permissionId(PERMISSION_ID_4)
                .build();

        return Set.of(permission3, permission4);
    }
}
