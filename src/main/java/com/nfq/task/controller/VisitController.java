package com.nfq.task.controller;

import com.nfq.task.domain.Reservation;
import com.nfq.task.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisitController {
    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/dashboard")
    public String showVisits(Model model) {
        model.addAttribute("visits", visitService.getVisitsByUser());
        return "dashboard";
    }
}
