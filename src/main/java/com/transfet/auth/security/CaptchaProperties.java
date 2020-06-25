package com.transfet.auth.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Property class for the Google reCAPTCHA.
 */
@Configuration
@Getter
public class CaptchaProperties {

    @Value("${recaptcha.validation.site-key}")
    private String site;
}
