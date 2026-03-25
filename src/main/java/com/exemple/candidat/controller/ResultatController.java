package com.exemple.candidat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exemple.candidat.service.CalculResultatService;

@Controller
@RequestMapping("/resultats")
public class ResultatController {
    @Autowired
    private CalculResultatService service;

    @GetMapping
    public String listAll(Model model) {
        model.addAttribute("resultats", service.calculerTousLesResultats());
        return "resultats/list";
    }

    @GetMapping("/{candidatId}")
    public String detail(@PathVariable Integer candidatId, Model model) {
        model.addAttribute("resultat", service.getResultatCandidat(candidatId));
        return "resultats/detail";
    }

    @GetMapping("/detail")
    public String search(@RequestParam Integer id) {
        return "redirect:/resultats/" + id;
    }
}
