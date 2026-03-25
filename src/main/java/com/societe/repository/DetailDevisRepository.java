package com.societe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.societe.model.DetailDevis;

@Repository
public interface DetailDevisRepository extends JpaRepository<DetailDevis, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM DetailDevis d WHERE d.devis.idDevis = ?1")
    void deleteByDevisId(Integer idDevis);
}
