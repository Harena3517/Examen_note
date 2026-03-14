package com.exemple.candidat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to forward all non-API and non-static requests to the SPA root.
 */
@Controller
public class ForwardController {

    @RequestMapping(value = { "/candidats", "/correcteurs", "/matieres", "/parametres", "/notes", "/resultats" })
    public String forward() {
        // Forward to the root where index.html is served
        return "forward:/";
    }
}
