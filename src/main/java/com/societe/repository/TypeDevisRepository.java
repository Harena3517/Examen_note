package com.societe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.societe.model.TypeDevis;

@Repository
public interface TypeDevisRepository extends JpaRepository<TypeDevis, Integer> {
}
