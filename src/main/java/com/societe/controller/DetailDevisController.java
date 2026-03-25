package com.societe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.societe.model.DetailDevis;
import com.societe.service.DetailDevisService;
import com.societe.service.DevisService;

@Controller
@RequestMapping("/detaildevis")
public class DetailDevisController {

    @Autowired
    private DetailDevisService detailDevisService;

    @Autowired
    private DevisService devisService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("details", detailDevisService.findAll());
        return "detaildevis/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("detail", new DetailDevis());
        model.addAttribute("devisList", devisService.findAll());
        return "detaildevis/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        detailDevisService.findById(id).ifPresent(detail -> {
            model.addAttribute("detail", detail);
            model.addAttribute("devisList", devisService.findAll());
        });
        return "detaildevis/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute DetailDevis detail) {
        detailDevisService.save(detail);
        return "redirect:/detaildevis";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        detailDevisService.deleteById(id);
        return "redirect:/detaildevis";
    }
}
