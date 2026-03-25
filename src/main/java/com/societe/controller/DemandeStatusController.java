package com.societe.controller;

import com.societe.model.Demande;
import com.societe.model.DemandeStatus;
import com.societe.service.DemandeStatusService;
import com.societe.model.Status;
import com.societe.service.StatusService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/demande-status")
public class DemandeStatusController {
    @Autowired
    private StatusService statusService;
    private final DemandeStatusService service;
    
    public DemandeStatusController(DemandeStatusService service) {
        this.service = service;
    }
    @GetMapping
    public String getAll(Model model) {
        List<DemandeStatus> liste = service.findAll();
        model.addAttribute("liste", liste);
        return "demande-status/list"; // fichier HTML
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("demandeStatus", new DemandeStatus());
        return "demande-status/form";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute DemandeStatus demandeStatus) {
        Demande demande = demandeStatus.getDemande();
        service.createForDemande(demande);

        return "redirect:/demande-status";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("demandeStatus", service.findById(id));
        return "demande-status/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/demande-status";
    }
}