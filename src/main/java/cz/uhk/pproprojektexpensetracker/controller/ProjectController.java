package cz.uhk.pproprojektexpensetracker.controller;

import cz.uhk.pproprojektexpensetracker.model.Project;
import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public String getProjects(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("projects", projectService.getProjectsListByUserId(user.getId()));
        return "project/list";
    }

    @GetMapping("/{id}")
    public String getProjectDetail(Model model, @AuthenticationPrincipal User user, @PathVariable Long id) {
        Project project = projectService.getProjectByIdAndUserId(id, user.getId());
        if (project == null) {
            return "redirect:/project";
        }
        model.addAttribute("project", project);
        return "project/detail";
    }
}
