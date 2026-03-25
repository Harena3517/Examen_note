package com.societe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "t_typedevis")
public class TypeDevis {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_typedevis")
    private Integer idTypeDevis;
 
    @Column(nullable = false)
    private String libelle;
 
    public TypeDevis() {
    }
 
    public Integer getIdTypeDevis() {
        return idTypeDevis;
    }
 
    public void setIdTypeDevis(Integer idTypeDevis) {
        this.idTypeDevis = idTypeDevis;
    }
 
    public String getLibelle() {
        return libelle;
    }
 
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}