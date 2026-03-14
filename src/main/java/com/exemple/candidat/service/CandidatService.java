package com.exemple.candidat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.candidat.model.Candidat;
import com.exemple.candidat.repository.CandidatRepository;

@Service
public class CandidatService {
    @Autowired
    private CandidatRepository repository;

    public List<Candidat> getAll() {
        return repository.findAll();
    }

    public Candidat getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Candidat save(Candidat candidat) {
        return repository.save(candidat);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
