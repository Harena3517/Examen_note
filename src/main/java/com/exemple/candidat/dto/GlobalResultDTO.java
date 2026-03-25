package com.exemple.candidat.dto;

import java.util.List;

import lombok.Data;

@Data
public class GlobalResultDTO {
    private String nomCandidat;
    private String prenomCandidat;
    private String matricule;
    private List<ResultatDTO> details;
    private Double reliquatGlobal;
    private String statusGlobal;
    private Double moyenneGenerale;
}
