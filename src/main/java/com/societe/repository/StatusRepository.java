package com.societe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.societe.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {
    Status findByLibelle(String libelle);
}
