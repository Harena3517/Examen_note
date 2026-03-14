package com.exemple.candidat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exemple.candidat.model.Matiere;
import com.exemple.candidat.service.MatiereService;

@Controller
@RequestMapping("/matieres")
public class MatiereController {
    @Autowired
    private MatiereService service;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("matieres", service.getAll());
        return "matieres/list";
    }

    @GetMapping("/nouveau")
    public String showCreateForm(Model model) {
        model.addAttribute("matiere", new Matiere());
        return "matieres/form";
    }

    @PostMapping("/enregistrer")
    public String save(@ModelAttribute Matiere matiere) {
        service.save(matiere);
        return "redirect:/matieres";
    }

    @GetMapping("/modifier/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("matiere", service.getById(id));
        return "matieres/form";
    }

    @GetMapping("/supprimer/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/matieres";
    }
}
