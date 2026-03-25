package com.societe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.societe.model.Devis;

@Repository
public interface DevisRepository extends JpaRepository<Devis, Integer> {
}
