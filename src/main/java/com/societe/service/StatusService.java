package com.societe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.societe.model.Status;
import com.societe.repository.StatusRepository;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    public Optional<Status> findById(Integer id) {
        return statusRepository.findById(id);
    }

    public Status save(Status status) {
        return statusRepository.save(status);
    }

    public void deleteById(Integer id) {
        statusRepository.deleteById(id);
    }
    //mijery anle cree anaty status
     public Status getStatusCree() {
        return statusRepository.findByLibelle("cree");
    }
}
