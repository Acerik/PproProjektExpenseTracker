package cz.uhk.pproprojektexpensetracker.controller;

import cz.uhk.pproprojektexpensetracker.model.TransactionTag;
import cz.uhk.pproprojektexpensetracker.model.User;
import cz.uhk.pproprojektexpensetracker.service.transaction.TransactionTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/transaction-tag")
@RequiredArgsConstructor
public class TransactionTagController {

    private final TransactionTagService transactionTagService;

    @GetMapping
    public String getTransactionTags(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("transactionTags", transactionTagService.getAllByUserId(user.getId()));
        return "transaction/tag/list";
    }

    @GetMapping("/new")
    public String getNewTemplate(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("transactionTag", new TransactionTag());
        return "transaction/tag/editor";
    }

    @PostMapping
    public String createNewTag(@ModelAttribute TransactionTag transactionTag, Model model, @AuthenticationPrincipal User user) {
        transactionTag.setUser(user);
        TransactionTag saved = transactionTag.getId() == null
                ? transactionTagService.create(transactionTag)
                : transactionTagService.update(transactionTag);
        if (saved == null) {
            //todo udělat obecně error jako objekt pro šablony + jako fragment
            model.addAttribute("error", true);
            model.addAttribute("transactionTag", transactionTag);
            return "transaction/tag/editor";
        }
        return "redirect:/transaction-tag";
    }

    @GetMapping("/edit/{id}")
    public String getEditTag(Model model, @AuthenticationPrincipal User user, @PathVariable Long id) {
        model.addAttribute("transactionTag", transactionTagService.findOne(id).orElse(null));
        return "transaction/tag/editor";
    }

    @DeleteMapping("/{id}")
    public String deleteTag(Model model, @AuthenticationPrincipal User user, @PathVariable Long id) {
        Boolean canBeDeleted = transactionTagService.isTagDeletable(id);
        if (canBeDeleted) {
            transactionTagService.delete(id);
            return "redirect:/transaction-tag";
        }
        //todo dodělat error
        model.addAttribute("error", true);
        return "redirect:/transaction-tag";
    }
}
