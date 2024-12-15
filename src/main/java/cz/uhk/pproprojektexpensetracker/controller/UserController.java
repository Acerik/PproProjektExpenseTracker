package cz.uhk.pproprojektexpensetracker.controller;

import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.model.UserRole;
import cz.uhk.pproprojektexpensetracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Secured("ROLE_" + UserRole.Fields.ADMIN)
    public String index(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user/list";
    }

    @PostMapping
    public String createUser(@ModelAttribute User user) {
        user.setId(null);
        user.setUserRoles(Set.of(UserRole.USER));
        userService.create(user);
        return "redirect:/login";
    }

    @GetMapping("/{id}")
    public String userDetail(@PathVariable Long id, Model model) {
        Optional<User> userOptional = userService.findOne(id);
        if (userOptional.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("user", userOptional.get());
        return "user/detail";
    }
}
