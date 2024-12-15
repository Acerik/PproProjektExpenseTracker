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
        model.addAttribute("project", new Project());
        return "project/editor";
    }

    @GetMapping("/{id}/edit")
    public String editProjectGet(Model model, @AuthenticationPrincipal User user, @PathVariable Long id) {
        Project project = projectService.findOneByIdAndUserId(id, user.getId()).orElse(null);
        if (project == null || !user.getId().equals(project.getUser().getId())) {
            return "redirect:/project";
        }
        model.addAttribute("project", project);
        return "project/editor";
    }

    @PostMapping
    public String createProject(@ModelAttribute Project project, @AuthenticationPrincipal User user, Model model) {
        project.setUser(user);
        if (project.getId() != null && projectService.findOneByIdAndUserId(project.getId(), user.getId()).isEmpty()) {
            //todo add error cannot update this project
            model.addAttribute("project", project);
            return "project/editor";
        }
        Project saved = project.getId() == null
                ? projectService.create(project)
                : projectService.update(project);
        if (saved == null) {
            model.addAttribute("project", project);
            return "project/editor";
        }
        return "redirect:/project";
    }
}
