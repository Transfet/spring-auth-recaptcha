package com.transfet.auth.security;

import com.github.mkopylec.recaptcha.security.login.FormLoginConfigurerEnhancer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Security configuration with reCaptcha support
 */
@Configuration
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] ENABLED_PATHS = {"/login*", "/resources/**", "/h2-console/**"};
    private static final String LOGIN_PATH = "/login";
    private static final String[] COOKIES = {"JSESSIONID"};
    private static final String ACCESS_DENIED_URL = "/access-denied";
    private final FormLoginConfigurerEnhancer formLoginConfigurerEnhancer;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        formLoginConfigurerEnhancer.addRecaptchaSupport(http.formLogin())
                .loginPage(LOGIN_PATH)
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers(ENABLED_PATHS).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage(ACCESS_DENIED_URL)
                .and()
                .logout()
                .deleteCookies(COOKIES)
                .invalidateHttpSession(false);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}
