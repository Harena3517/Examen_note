package com.societe.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.societe.model.Demande;
import com.societe.model.DemandeStatus;
import com.societe.model.Status;
import com.societe.repository.DemandeStatusRepository;
import com.societe.repository.StatusRepository;

@Service
public class DemandeStatusService {

    private final DemandeStatusRepository demandeStatusRepository;
    private final StatusRepository statusRepository;

    public DemandeStatusService(DemandeStatusRepository demandeStatusRepository,
            StatusRepository statusRepository) {
        this.demandeStatusRepository = demandeStatusRepository;
        this.statusRepository = statusRepository;
    }

    public List<DemandeStatus> findAll() {
        return demandeStatusRepository.findAll();
    }

    public DemandeStatus findById(Long id) {
        return demandeStatusRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        demandeStatusRepository.deleteById(id);
    }

    public DemandeStatus createForDemande(Demande demande) {
        // Recherche du statut "cree"
        Status statusCree = statusRepository.findByLibelle("cree");

        // Si le statut n'existe pas, on le COUCHE en base pour éviter un crash
        // (id_status NOT NULL)
        if (statusCree == null) {
            statusCree = new Status();
            statusCree.setLibelle("cree");
            statusCree = statusRepository.save(statusCree);
        }

        DemandeStatus ds = new DemandeStatus();
        ds.setDemande(demande);
        ds.setStatus(statusCree);
        ds.setDateStatus(LocalDateTime.now());
        return demandeStatusRepository.save(ds);
    }

    public void deleteByDemandeId(Integer idDemande) {
        demandeStatusRepository.deleteByDemandeIdDemande(idDemande);
    }
}