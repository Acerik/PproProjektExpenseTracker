package cz.uhk.pproprojektexpensetracker.controller;

import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.model.UserRole;
import cz.uhk.pproprojektexpensetracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping()
    public String index(Model model) {
        return "redirect:/project";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute User user, Model model) {
        user.setId(null);
        user.setUserRoles(Set.of(UserRole.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<String> errors = userService.validateRegistration(user);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            return "registration";
        }
        User saved = userService.create(user);
        if (saved != null) {
            return "redirect:/login";
        }
        return "registration";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }
}
