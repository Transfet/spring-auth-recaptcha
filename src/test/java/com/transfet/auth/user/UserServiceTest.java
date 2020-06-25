package com.transfet.auth.user;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

/**
 * Unit test for {@link UserService}.
 */
public class UserServiceTest {

    private static final LocalDateTime TEST_TIMESTAMP = LocalDateTime.of(2020, Month.JUNE, 25, 23, 0, 0);
    private static final String TEST_USER_NAME = "test_user_name";
    private static final User TEST_USER = new User();


    @Mock
    private UserRepository userRepository;

    private UserService underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new UserService(userRepository);
    }

    @Test
    public void testGetUserByUserNameShouldReturnUserIfExists() {

        // given
        given(userRepository.getUserByUserName(TEST_USER_NAME)).willReturn(Optional.of(TEST_USER));

        // when
        User user = underTest.getUserByUserName(TEST_USER_NAME);

        // then
        assertThat(user, is(TEST_USER));

    }


    @Test(expectedExceptions = UsernameNotFoundException.class)
    public void testGetUserByUserNameShouldReturnThrowExceptionIfUserNotExists() {

        // given
        given(userRepository.getUserByUserName(TEST_USER_NAME)).willReturn(Optional.empty());

        // when
        underTest.getUserByUserName(TEST_USER_NAME);

        // then throw exception
    }

    @Test
    public void testGetUserLastLoggedInTimeByUserNameShouldReturnLastLoggedInTimestampOfAUser() {

        // given
        given(userRepository.getLastLoggedInTimeByUserName(TEST_USER_NAME)).willReturn(TEST_TIMESTAMP);

        // when
        LocalDateTime lastLoggedInTimestamp = underTest.getUserLastLoggedInTimeByUserName(TEST_USER_NAME);

        // then
        assertThat(lastLoggedInTimestamp, is(TEST_TIMESTAMP));
    }

    @Test
    public void testSaveUserShouldSaveUser() {

        // given
        User user = new User();
        // when
        underTest.saveUser(user);

        // then
        Mockito.verify(userRepository).save(user);
    }
}
