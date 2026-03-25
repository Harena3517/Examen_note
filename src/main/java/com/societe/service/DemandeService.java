package com.societe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.societe.model.Demande;
import com.societe.repository.DemandeRepository;

@Service
public class DemandeService {

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DemandeStatusService demandeStatusService;

    public List<Demande> findAll() {
        return demandeRepository.findAll();
    }

    public Optional<Demande> findById(Integer id) {
        return demandeRepository.findById(id);
    }

    public Demande save(Demande demande) {
        boolean isNew = (demande.getIdDemande() == null);
        Demande savedDemande = demandeRepository.save(demande);

        if (isNew) {
            demandeStatusService.createForDemande(savedDemande);
        }

        return savedDemande;
    }

    @org.springframework.transaction.annotation.Transactional
    public void deleteById(Integer id) {
        demandeStatusService.deleteByDemandeId(id);
        demandeRepository.deleteById(id);
    }
}
