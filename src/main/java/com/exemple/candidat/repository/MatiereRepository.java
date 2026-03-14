package com.exemple.candidat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.candidat.model.Matiere;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Integer> {
}
