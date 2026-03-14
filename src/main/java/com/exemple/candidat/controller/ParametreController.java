package com.exemple.candidat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exemple.candidat.model.Parametre;
import com.exemple.candidat.service.MatiereService;
import com.exemple.candidat.service.OperateurService;
import com.exemple.candidat.service.ParametreService;
import com.exemple.candidat.service.ResolutionService;

@Controller
@RequestMapping("/parametres")
public class ParametreController {
    @Autowired
    private ParametreService service;
    @Autowired
    private MatiereService matiereService;
    @Autowired
    private OperateurService operateurService;
    @Autowired
    private ResolutionService resolutionService;

    @GetMapping
    public String list(Model model) {
        try {
            model.addAttribute("parametres", service.getAll());
        } catch (Exception e) {
            System.err.println("--- CRITICAL ERROR IN PARAMETER LIST ---");
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
        }
        return "parametres/list";
    }

    @GetMapping("/nouveau")
    public String showCreateForm(Model model) {
        model.addAttribute("parametre", new Parametre());
        model.addAttribute("matieres", matiereService.getAll());
        model.addAttribute("operateurs", operateurService.getAll());
        model.addAttribute("resolutions", resolutionService.getAll());
        return "parametres/form";
    }

    @PostMapping("/enregistrer")
    public String save(@ModelAttribute Parametre parametre) {
        service.save(parametre);
        return "redirect:/parametres";
    }

    @GetMapping("/modifier/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("parametre", service.getById(id));
        model.addAttribute("matieres", matiereService.getAll());
        model.addAttribute("operateurs", operateurService.getAll());
        model.addAttribute("resolutions", resolutionService.getAll());
        return "parametres/form";
    }

    @GetMapping("/supprimer/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/parametres";
    }
}
