package com.exemple.candidat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.candidat.dto.GlobalResultDTO;
import com.exemple.candidat.model.Candidat;
import com.exemple.candidat.model.Correcteur;
import com.exemple.candidat.model.Matiere;
import com.exemple.candidat.model.Note;
import com.exemple.candidat.model.Operateur;
import com.exemple.candidat.model.Parametre;
import com.exemple.candidat.model.Resolution;
import com.exemple.candidat.service.CalculResultatService;
import com.exemple.candidat.service.CandidatService;
import com.exemple.candidat.service.CorrecteurService;
import com.exemple.candidat.service.MatiereService;
import com.exemple.candidat.service.NoteService;
import com.exemple.candidat.service.OperateurService;
import com.exemple.candidat.service.ParametreService;
import com.exemple.candidat.service.ResolutionService;

@RestController
@RequestMapping("/api")
public class RestApiController {

    @Autowired
    private CandidatService candidatService;
    @Autowired
    private CorrecteurService correcteurService;
    @Autowired
    private MatiereService matiereService;
    @Autowired
    private ParametreService parametreService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private OperateurService operateurService;
    @Autowired
    private ResolutionService resolutionService;
    @Autowired
    private CalculResultatService calculService;

    // Candidats
    @GetMapping("/candidats")
    public List<Candidat> getCandidats() {
        return candidatService.getAll();
    }

    @PostMapping("/candidats")
    public void saveCandidat(@RequestBody Candidat c) {
        candidatService.save(c);
    }

    @DeleteMapping("/candidats/{id}")
    public void deleteCandidat(@PathVariable Integer id) {
        candidatService.delete(id);
    }

    // Correcteurs
    @GetMapping("/correcteurs")
    public List<Correcteur> getCorrecteurs() {
        return correcteurService.getAll();
    }

    @PostMapping("/correcteurs")
    public void saveCorrecteur(@RequestBody Correcteur c) {
        correcteurService.save(c);
    }

    @DeleteMapping("/correcteurs/{id}")
    public void deleteCorrecteur(@PathVariable Integer id) {
        correcteurService.delete(id);
    }

    // Matieres
    @GetMapping("/matieres")
    public List<Matiere> getMatieres() {
        return matiereService.getAll();
    }

    @PostMapping("/matieres")
    public void saveMatiere(@RequestBody Matiere m) {
        matiereService.save(m);
    }

    @DeleteMapping("/matieres/{id}")
    public void deleteMatiere(@PathVariable Integer id) {
        matiereService.delete(id);
    }

    // Parametres
    @GetMapping("/parametres")
    public List<Parametre> getParametres() {
        return parametreService.getAll();
    }

    @PostMapping("/parametres")
    public void saveParametre(@RequestBody Parametre p) {
        parametreService.save(p);
    }

    @DeleteMapping("/parametres/{id}")
    public void deleteParametre(@PathVariable Integer id) {
        parametreService.delete(id);
    }

    // Notes
    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteService.getAll();
    }

    @PostMapping("/notes")
    public void saveNote(@RequestBody Note n) {
        noteService.save(n);
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable Integer id) {
        noteService.delete(id);
    }

    // Reference Data
    @GetMapping("/operateurs")
    public List<Operateur> getOperateurs() {
        return operateurService.getAll();
    }

    @GetMapping("/resolutions")
    public List<Resolution> getResolutions() {
        return resolutionService.getAll();
    }

    // Resultats
    @GetMapping("/resultats")
    public List<GlobalResultDTO> getResultats() {
        return calculService.calculerTousLesResultats();
    }

    @GetMapping("/resultats/{id}")
    public GlobalResultDTO getResultatCandidat(@PathVariable Integer id) {
        return calculService.getResultatCandidat(id);
    }
}
