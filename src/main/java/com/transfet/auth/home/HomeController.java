package com.transfet.auth.home;

import com.transfet.auth.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

/**
 * Controller for serving the main views.
 */
@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping
    public ModelAndView getIndexPage(Authentication authentication) {

        ModelAndView view = new ModelAndView();
        view.setViewName("index");

        String loggedInUser = authentication.getName();
        LocalDateTime lastLoggedInTime = userService.getUserLastLoggedInTimeByUserName(loggedInUser);

        if (nonNull(lastLoggedInTime)) {
            view.addObject("lastLoggedInTime", lastLoggedInTime);
        }

        return view;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority(webSecurityConfiguration.ADMIN_AUTHORITY)")
    private String getAdminPage(){

        return "admin";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAnyAuthority(webSecurityConfiguration.PROFILE_AUTHORITY)")
    private String getProfilePage(){

        return "profile";
    }

    @GetMapping("/editor")
    @PreAuthorize("hasAnyAuthority(webSecurityConfiguration.EDITOR_AUTHORITY)")
    private String getEditorPage(){

        return "editor";
    }

}
