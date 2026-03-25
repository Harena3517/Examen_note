package com.societe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.societe.model.DetailDevis;
import com.societe.repository.DetailDevisRepository;

@Service
public class DetailDevisService {

    @Autowired
    private DetailDevisRepository detailDevisRepository;

    public List<DetailDevis> findAll() {
        return detailDevisRepository.findAll();
    }

    public Optional<DetailDevis> findById(Integer id) {
        return detailDevisRepository.findById(id);
    }

    public DetailDevis save(DetailDevis detailDevis) {
        return detailDevisRepository.save(detailDevis);
    }

    public void deleteById(Integer id) {
        detailDevisRepository.deleteById(id);
    }
}
