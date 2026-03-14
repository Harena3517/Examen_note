package com.exemple.candidat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemple.candidat.model.Note;
import com.exemple.candidat.repository.NoteRepository;

@Service
public class NoteService {
    @Autowired
    private NoteRepository repository;

    public List<Note> getAll() {
        return repository.findAll();
    }

    public List<Note> getByCandidatId(Integer id) {
        return repository.findByCandidatId(id);
    }

    public Note getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public Note save(Note note) {
        return repository.save(note);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
