package com.exemple.candidat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exemple.candidat.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByCandidatId(Integer candidatId);

    List<Note> findByCandidatIdAndMatiereId(Integer candidatId, Integer matiereId);
}
