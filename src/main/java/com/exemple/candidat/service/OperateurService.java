package com.exemple.candidat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.candidat.model.Operateur;
import com.exemple.candidat.repository.OperateurRepository;

@Service
public class OperateurService {
    @Autowired
    private OperateurRepository repo;

    public List<Operateur> getAll() {
        return repo.findAll();
    }

    public Operateur getById(Integer id) {
        return repo.findById(id).orElse(null);
    }
}
