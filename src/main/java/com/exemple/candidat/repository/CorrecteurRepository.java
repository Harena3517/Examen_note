package com.exemple.candidat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.candidat.model.Correcteur;

@Repository
public interface CorrecteurRepository extends JpaRepository<Correcteur, Integer> {
}
