package com.exemple.candidat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.candidat.model.Operateur;

@Repository
public interface OperateurRepository extends JpaRepository<Operateur, Integer> {
}
