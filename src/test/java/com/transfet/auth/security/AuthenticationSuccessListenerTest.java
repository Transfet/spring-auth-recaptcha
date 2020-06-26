package com.transfet.auth.security;

import com.transfet.auth.DateTimeService;
import com.transfet.auth.user.User;
import com.transfet.auth.user.UserService;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

/**
 * Unit test for {@link AuthenticationSuccessListener}.
 */
public class AuthenticationSuccessListenerTest {

    private static final LocalDateTime TEST_TIMESTAMP = LocalDateTime.of(2020, Month.JUNE, 25, 23,0,0);
    private static final String TEST_USER_NAME = "test_user_name";
    private static final String TEST_PASSWORD = "password";
    private static final String TEST_AUTHORITY = "ACCESS_TEST";

    @Mock
    private UserService userService;

    @Mock
    private DateTimeService dateTimeService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    private AuthenticationSuccessListener underTest;

    @BeforeMethod
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        underTest = new AuthenticationSuccessListener(userService, dateTimeService);
    }

    @Test
    public void testOnApplicationEventShouldUpdateUserLastLoggedInTime() {

        // given
        given(dateTimeService.currentUTCLocalDateTime()).willReturn(TEST_TIMESTAMP);
        given(userService.getUserByUserName(TEST_USER_NAME)).willReturn(new User());

        UserDetails userPrincipal = org.springframework.security.core.userdetails.User.builder()
                .username(TEST_USER_NAME)
                .password(TEST_PASSWORD)
                .authorities(TEST_AUTHORITY)
                .build();
        Authentication userAuthentication = new TestingAuthenticationToken(userPrincipal, null);
        AuthenticationSuccessEvent event = new AuthenticationSuccessEvent(userAuthentication);


        // when
        underTest.onApplicationEvent(event);

        // then
        verify(userService).saveUser(userArgumentCaptor.capture());
        User savedUser = userArgumentCaptor.getValue();
        assertThat(savedUser.getLastLoggedInTime(), is(TEST_TIMESTAMP));
    }

}
