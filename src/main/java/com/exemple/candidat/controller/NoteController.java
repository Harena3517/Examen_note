package com.exemple.candidat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exemple.candidat.model.Note;
import com.exemple.candidat.service.CandidatService;
import com.exemple.candidat.service.CorrecteurService;
import com.exemple.candidat.service.MatiereService;
import com.exemple.candidat.service.NoteService;

@Controller
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    private NoteService service;
    @Autowired
    private CandidatService candidatService;
    @Autowired
    private MatiereService matiereService;
    @Autowired
    private CorrecteurService correcteurService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("notes", service.getAll());
        return "notes/list";
    }

    @GetMapping("/nouveau")
    public String showCreateForm(Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("candidats", candidatService.getAll());
        model.addAttribute("matieres", matiereService.getAll());
        model.addAttribute("correcteurs", correcteurService.getAll());
        return "notes/form";
    }

    @PostMapping("/enregistrer")
    public String save(@ModelAttribute Note note) {
        service.save(note);
        return "redirect:/notes";
    }

    @GetMapping("/modifier/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        model.addAttribute("note", service.getById(id));
        model.addAttribute("candidats", candidatService.getAll());
        model.addAttribute("matieres", matiereService.getAll());
        model.addAttribute("correcteurs", correcteurService.getAll());
        return "notes/form";
    }

    @GetMapping("/supprimer/{id}")
    public String delete(@PathVariable Integer id) {
        service.delete(id);
        return "redirect:/notes";
    }
}
