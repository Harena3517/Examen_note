package com.societe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.societe.model.Demande;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Integer> {
}
