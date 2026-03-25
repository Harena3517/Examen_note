package com.exemple.candidat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.candidat.model.Parametre;

@Repository
public interface ParametreRepository extends JpaRepository<Parametre, Integer> {
    List<Parametre> findByMatiereId(Integer matiereId);
}
