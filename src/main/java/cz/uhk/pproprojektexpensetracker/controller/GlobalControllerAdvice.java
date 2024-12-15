package cz.uhk.pproprojektexpensetracker.controller;

import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.model.UserRole;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {


    @ModelAttribute
    public void addAttributes(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("isAdmin", user != null
                && user.getUserRoles().contains(UserRole.ADMIN));
        if (user != null) {
            model.addAttribute("userId", user.getId());
        }
    }
}
