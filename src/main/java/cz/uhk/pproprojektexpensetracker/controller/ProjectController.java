package cz.uhk.pproprojektexpensetracker.controller;

import cz.uhk.pproprojektexpensetracker.dto.ProjectListItemDto;
import cz.uhk.pproprojektexpensetracker.model.Project;
import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.service.project.ProjectService;
import cz.uhk.pproprojektexpensetracker.service.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final TransactionService transactionService;

    @GetMapping
    public String getProjects(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("projects", projectService.getProjectsListByUserId(user.getId()));
        return "project/list";
    }

    @GetMapping("/{id}")
    public String getProjectDetail(Model model, @AuthenticationPrincipal User user, @PathVariable Long id) {
        ProjectListItemDto project = projectService.getProjectByIdAndUserId(id, user.getId());
        if (project == null) {
            return "redirect:/project";
        }
        model.addAttribute("transactions", transactionService.getAllByProjectId(id));
        model.addAttribute("project", project);
        return "project/detail";
    }

    @GetMapping("/new")
    public String newProjectGet(Model model, @AuthenticationPrincipal User user) {
        return "project/new";
    }

    @PostMapping
    public String createProject(@ModelAttribute Project project, @AuthenticationPrincipal User user) {
        project.setUser(user);
        Project saved = projectService.create(project);
        if (saved == null) {
            return "project/new";
        }
        return "redirect:/project";
    }
}
