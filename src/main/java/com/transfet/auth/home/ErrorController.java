package com.transfet.auth.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller class for handling errors.
 */
@Controller
public class ErrorController {

    @RequestMapping("/access-denied")
    public String handleError() {

        return "access-denied";
    }

}
