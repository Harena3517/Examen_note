package com.exemple.candidat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.candidat.model.Parametre;
import com.exemple.candidat.repository.ParametreRepository;

@Service
public class ParametreService {
    @Autowired
    private ParametreRepository repository;

    public List<Parametre> getAll() {
        return repository.findAll();
    }

    public Parametre getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Parametre save(Parametre parametre) {
        return repository.save(parametre);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
