package com.societe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.societe.model.Devis;
import com.societe.service.DemandeService;
import com.societe.service.DevisService;
import com.societe.service.TypeDevisService;

@Controller
@RequestMapping("/devis")
public class DevisController {

    @Autowired
    private DevisService devisService;

    @Autowired
    private DemandeService demandeService;

    @Autowired
    private TypeDevisService typeDevisService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("devisList", devisService.findAll());
        return "devis/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("devis", new Devis());
        model.addAttribute("demandes", demandeService.findAll());
        model.addAttribute("typeDevisList", typeDevisService.findAll());
        return "devis/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        return devisService.findById(id).map(devis -> {
            model.addAttribute("devis", devis);
            model.addAttribute("demandes", demandeService.findAll());
            model.addAttribute("typeDevisList", typeDevisService.findAll());
            return "devis/form";
        }).orElse("redirect:/devis");
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Devis devis) {
        devisService.save(devis);
        return "redirect:/devis";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        devisService.deleteById(id);
        return "redirect:/devis";
    }
}
