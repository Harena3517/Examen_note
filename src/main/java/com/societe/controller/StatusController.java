package com.societe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.societe.model.Status;
import com.societe.service.StatusService;

@Controller
@RequestMapping("/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("statusList", statusService.findAll());
        return "status/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("status", new Status());
        return "status/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        statusService.findById(id).ifPresent(status -> model.addAttribute("status", status));
        return "status/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Status status) {
        statusService.save(status);
        return "redirect:/status";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        statusService.deleteById(id);
        return "redirect:/status";
    }

}
