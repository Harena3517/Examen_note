package com.exemple.candidat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.candidat.model.Correcteur;
import com.exemple.candidat.repository.CorrecteurRepository;

@Service
public class CorrecteurService {
    @Autowired
    private CorrecteurRepository repository;

    public List<Correcteur> getAll() {
        return repository.findAll();
    }

    public Correcteur save(Correcteur correcteur) {
        return repository.save(correcteur);
    }

    public Correcteur getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
