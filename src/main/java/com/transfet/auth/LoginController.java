package com.transfet.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Login controller for serving the login view.
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @GetMapping
    public String loginPage(){
        return "login";
    }
}
