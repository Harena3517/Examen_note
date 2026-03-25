package com.societe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.societe.model.DemandeStatus;

@Repository
public interface DemandeStatusRepository extends JpaRepository<DemandeStatus, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM DemandeStatus ds WHERE ds.demande.idDemande = ?1")
    void deleteByDemandeIdDemande(Integer idDemande);
}