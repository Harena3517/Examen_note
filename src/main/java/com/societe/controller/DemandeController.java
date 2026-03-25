package com.societe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.societe.model.Demande;
import com.societe.service.ClientService;
import com.societe.service.DemandeService;

@Controller
@RequestMapping("/demandes")
public class DemandeController {

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("demandes", demandeService.findAll());
        return "demande/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("demande", new Demande());
        model.addAttribute("clients", clientService.findAll());
        return "demande/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        return demandeService.findById(id).map(demande -> {
            model.addAttribute("demande", demande);
            model.addAttribute("clients", clientService.findAll());
            return "demande/form";
        }).orElse("redirect:/demandes");
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Demande demande) {
        demandeService.save(demande);
        return "redirect:/demandes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        demandeService.deleteById(id);
        return "redirect:/demandes";
    }
}
