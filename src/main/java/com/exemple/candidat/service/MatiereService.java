package com.exemple.candidat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.candidat.model.Matiere;
import com.exemple.candidat.repository.MatiereRepository;

@Service
public class MatiereService {
    @Autowired
    private MatiereRepository repository;

    public List<Matiere> getAll() {
        return repository.findAll();
    }

    public Matiere save(Matiere matiere) {
        return repository.save(matiere);
    }

    public Matiere getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
