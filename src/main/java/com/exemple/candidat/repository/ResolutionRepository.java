package com.exemple.candidat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.candidat.model.Resolution;

@Repository
public interface ResolutionRepository extends JpaRepository<Resolution, Integer> {
}
