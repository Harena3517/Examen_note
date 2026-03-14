package com.exemple.candidat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exemple.candidat.model.Candidat;
import com.exemple.candidat.service.CandidatService;

@Controller
@RequestMapping("/candidats")
public class CandidatController {
    @Autowired
    private CandidatService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("candidats", service.getAll());
        return "candidats/list";
    }

    @GetMapping("/nouveau")
    public String showCreateForm(Model model) {
        model.addAttribute("candidat", new Candidat());
        return "candidats/form";
    }

    @PostMapping("/enregistrer")
    public String save(@ModelAttribute Candidat candidat) {
        service.save(candidat);
        return "redirect:/candidats";
    }

    @GetMapping("/modifier/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("candidat", service.getById(id));
        return "candidats/form";
    }

    @GetMapping("/supprimer/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/candidats";
    }
}
