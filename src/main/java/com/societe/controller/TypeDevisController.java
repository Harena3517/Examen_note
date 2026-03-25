package com.societe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.societe.model.TypeDevis;
import com.societe.service.TypeDevisService;

@Controller
@RequestMapping("/typedevis")
public class TypeDevisController {

    @Autowired
    private TypeDevisService typeDevisService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("typeDevisList", typeDevisService.findAll());
        return "typedevis/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("typeDevis", new TypeDevis());
        return "typedevis/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        typeDevisService.findById(id).ifPresent(typeDevis -> model.addAttribute("typeDevis", typeDevis));
        return "typedevis/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute TypeDevis typeDevis) {
        typeDevisService.save(typeDevis);
        return "redirect:/typedevis";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        typeDevisService.deleteById(id);
        return "redirect:/typedevis";
    }
}
