package com.exemple.candidat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.candidat.model.Candidat;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Integer> {
}
