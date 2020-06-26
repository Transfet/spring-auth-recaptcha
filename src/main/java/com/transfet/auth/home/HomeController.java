package com.transfet.auth.home;

import com.transfet.auth.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

import static java.util.Objects.nonNull;

/**
 * Controller for serving the main views.
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping("/")
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
    @PreAuthorize("hasAuthority('ACCESS_ADMIN')")
    public String getAdminPage(){

        return "admin";
    }

    @GetMapping("/profile")
    @PreAuthorize("hasAuthority('ACCESS_PROFILE')")
    public String getProfilePage(){

        return "profile";
    }

    @GetMapping("/editor")
    @PreAuthorize("hasAuthority('ACCESS_EDITOR')")
    public String getEditorPage(){

        return "editor";
    }

}
