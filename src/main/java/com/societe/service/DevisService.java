package com.societe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.societe.model.Devis;
import com.societe.repository.DetailDevisRepository;
import com.societe.repository.DevisRepository;

@Service
public class DevisService {

    @Autowired
    private DevisRepository devisRepository;

    @Autowired
    private DetailDevisRepository detailDevisRepository;

    public List<Devis> findAll() {
        return devisRepository.findAll();
    }

    public Optional<Devis> findById(Integer id) {
        return devisRepository.findById(id);
    }

    public Devis save(Devis devis) {
        return devisRepository.save(devis);
    }

    @org.springframework.transaction.annotation.Transactional
    public void deleteById(Integer id) {
        detailDevisRepository.deleteByDevisId(id);
        devisRepository.deleteById(id);
    }
}
