package com.exemple.candidat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.candidat.model.Resolution;
import com.exemple.candidat.repository.ResolutionRepository;

@Service
public class ResolutionService {
    @Autowired
    private ResolutionRepository repo;

    public List<Resolution> getAll() {
        return repo.findAll();
    }

    public Resolution getById(Integer id) {
        return repo.findById(id).orElse(null);
    }
}
