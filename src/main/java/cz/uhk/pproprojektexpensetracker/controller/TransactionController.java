package cz.uhk.pproprojektexpensetracker.controller;

import cz.uhk.pproprojektexpensetracker.model.Project;
import cz.uhk.pproprojektexpensetracker.model.Transaction;
import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.service.project.ProjectService;
import cz.uhk.pproprojektexpensetracker.service.transaction.TransactionService;
import cz.uhk.pproprojektexpensetracker.service.transaction.TransactionTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final ProjectService projectService;

    private final TransactionService transactionService;

    private final TransactionTagService transactionTagService;

    @GetMapping({"/{id}", "/project/{projId}/{id}"})
    public String getDetail(@PathVariable Long id, Model model, @AuthenticationPrincipal User user, @PathVariable(required = false) Long projId) {
        Optional<Transaction> transaction = transactionService.findOneByIdAndUserId(id, user.getId());
        if (transaction.isEmpty()) {
            if (projId != null) {
                return "redirect:/project/{projId}";
            }
            return "redirect:/project";
        }
        model.addAttribute("transaction", transaction.get());
        return "transaction/detail";
    }

    @GetMapping("/project/{id}/new")
    public String getNewTransactionEditor(Model model, @PathVariable Long id, @AuthenticationPrincipal User user) {
        Optional<Project> project = projectService.findOneByIdAndUserId(id, user.getId());
        if (project.isEmpty()) {
            return "redirect:/project";
        }
        model.addAttribute("transaction",
                Transaction.builder()
                        .project(project.get())
                        .date(LocalDate.now())
                        .build());
        model.addAttribute("tags", transactionTagService.getAllByUserId(user.getId()));
        model.addAttribute("projects", projectService.getProjectsListByUserId(user.getId()));
        return "transaction/editor";
    }

    @GetMapping("/{id}/edit")
    public String getEditTransactionEditor(Model model, @PathVariable Long id, @AuthenticationPrincipal User user) {
        Optional<Transaction> transaction = transactionService.findOneByIdAndUserId(id, user.getId());
        if (transaction.isEmpty()) {
            return "project/list";
        }
        model.addAttribute("transaction", transaction.get());
        model.addAttribute("tags", transactionTagService.getAllByUserId(user.getId()));
        model.addAttribute("projects", projectService.getProjectsListByUserId(user.getId()));
        return "transaction/editor";
    }

    @PostMapping
    public String createTransaction(@ModelAttribute Transaction transaction, Model model, @AuthenticationPrincipal User user) {
        //check if user can create transaction for that project
        Transaction saved = transaction.getId() == null
                ? transactionService.create(transaction)
                : transactionService.update(transaction);
        if (saved == null) {
            model.addAttribute("transaction", transaction);
            model.addAttribute("tags", transactionTagService.getAllByUserId(user.getId()));
            model.addAttribute("projects", projectService.getProjectsListByUserId(user.getId()));
            //todo add error
            return "transaction/editor";
        }
        return "redirect:/project/" + saved.getProject().getId();
    }

}
