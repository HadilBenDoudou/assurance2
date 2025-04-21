package com.example.assurance2.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Sinistre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private String type;
    private LocalDate dateDéclaration;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private Contrat contrat;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public void ajouterSinistre() {
        // Logique
    }

    public void modifierSinistre() {
        // Logique
    }

    public void supprimerSinistre() {
        // Logique
    }

    public void validerSinistre() {
        // Logique
    }

    public void rejeterSinistre() {
        // Logique
    }
}