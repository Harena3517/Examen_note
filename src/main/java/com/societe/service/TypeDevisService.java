package com.societe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.societe.model.TypeDevis;
import com.societe.repository.TypeDevisRepository;

@Service
public class TypeDevisService {

    @Autowired
    private TypeDevisRepository typeDevisRepository;

    public List<TypeDevis> findAll() {
        return typeDevisRepository.findAll();
    }

    public Optional<TypeDevis> findById(Integer id) {
        return typeDevisRepository.findById(id);
    }

    public TypeDevis save(TypeDevis typeDevis) {
        return typeDevisRepository.save(typeDevis);
    }

    public void deleteById(Integer id) {
        typeDevisRepository.deleteById(id);
    }
}
