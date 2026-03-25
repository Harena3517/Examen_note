package com.exemple.candidat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exemple.candidat.model.Correcteur;
import com.exemple.candidat.service.CorrecteurService;

@Controller
@RequestMapping("/correcteurs")
public class CorrecteurController {
    @Autowired
    private CorrecteurService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("correcteurs", service.getAll());
        return "correcteurs/list";
    }

    @GetMapping("/nouveau")
    public String showCreateForm(Model model) {
        model.addAttribute("correcteur", new Correcteur());
        return "correcteurs/form";
    }

    @PostMapping("/enregistrer")
    public String save(@ModelAttribute Correcteur correcteur) {
        service.save(correcteur);
        return "redirect:/correcteurs";
    }

    @GetMapping("/modifier/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("correcteur", service.getById(id));
        return "correcteurs/form";
    }

    @GetMapping("/supprimer/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/correcteurs";
    }
}
