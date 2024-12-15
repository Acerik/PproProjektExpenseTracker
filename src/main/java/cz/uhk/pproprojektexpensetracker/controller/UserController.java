package cz.uhk.pproprojektexpensetracker.controller;

import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.model.UserRole;
import cz.uhk.pproprojektexpensetracker.service.project.ProjectService;
import cz.uhk.pproprojektexpensetracker.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ProjectService projectService;

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
        model.addAttribute("projects", projectService.getProjectsListByUserId(id));
        return "user/detail";
    }

    @GetMapping("/{id}/edit")
    public String userGetEdit(@PathVariable Long id, Model model, @AuthenticationPrincipal User user) {
        Optional<User> userOptional = userService.findOne(id);
        if (userOptional.isEmpty()) {
            return "redirect:/";
        }
        if (!userOptional.get().getId().equals(user.getId())) {
            return "redirect:/";
        }
        model.addAttribute("user", userOptional.get().toBuilder().password("").build());
        return "user/edit";
    }

    @PostMapping("/{id}/edit")
    public String userPostEdit(@PathVariable Long id, @ModelAttribute User user, Model model, @AuthenticationPrincipal User loggedUser) {
        if (!loggedUser.getId().equals(id)) {
            model.addAttribute("errors", List.of("Nelze upravit jiného uživatele, nežli přihlášeného."));
            return "user/edit";
        }
        user.setId(id);
        User saved = userService.editUser(user, loggedUser);
        if (saved == null) {
            model.addAttribute("errors", List.of("Došlo k chybě při ukládání."));

            return "user/edit";
        }
        return "redirect:/";
    }
}
