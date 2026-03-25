package com.exemple.candidat.dto;

import lombok.Data;

@Data
public class ResultatDTO {
    private String nomCandidat;
    private String prenomCandidat;
    private String matricule;
    private String nomMatiere;
    private Double seuil;
    private String operateur;
    private Double noteCalculee;
    private Double reliquat;
    private String status;
    private Double diff;
    private String resolution;
}
