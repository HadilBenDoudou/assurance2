package com.example.assurance2.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private LocalDate dateDébut;
    private LocalDate dateFin;
    private String statut;
    private Double montant;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public void créerContrat() {
        // Logique
    }

    public void modifierContrat() {
        // Logique
    }

    public void renouvelerContrat() {
        // Logique
    }

    public void annulerContrat() {
        // Logique
    }
}