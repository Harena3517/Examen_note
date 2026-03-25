package com.societe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.societe.model.Client;
import com.societe.service.ClientService;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "client/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        clientService.findById(id).ifPresent(client -> model.addAttribute("client", client));
        return "client/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        clientService.deleteById(id);
        return "redirect:/clients";
    }
}
