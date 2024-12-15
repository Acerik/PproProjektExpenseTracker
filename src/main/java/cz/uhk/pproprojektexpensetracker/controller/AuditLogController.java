package cz.uhk.pproprojektexpensetracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.uhk.pproprojektexpensetracker.dto.AuditLogDto;
import cz.uhk.pproprojektexpensetracker.model.UserRole;
import cz.uhk.pproprojektexpensetracker.service.auditlog.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/audit-log")
@Secured("ROLE_" + UserRole.Fields.ADMIN)
@RequiredArgsConstructor
public class AuditLogController {

    private final AuditLogService auditLogService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("auditLogs", auditLogService.findAll());
        return "auditlog/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        AuditLogDto auditLog = auditLogService.findById(id).orElse(null);
        if (auditLog == null) {
            return "redirect:/audit-log";
        }
        model.addAttribute("auditLog", auditLog);
        model.addAttribute("formattedJson", formatJson(auditLog.getEntityJson()));
        return "auditlog/detail";
    }

    private String formatJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            Object jsonObject = objectMapper.readValue(json, Object.class);
            return objectMapper.writeValueAsString(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
            return json;
        }
    }

}
