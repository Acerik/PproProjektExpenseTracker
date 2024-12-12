package cz.uhk.pproprojektexpensetracker.controller;

import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user/list";
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
