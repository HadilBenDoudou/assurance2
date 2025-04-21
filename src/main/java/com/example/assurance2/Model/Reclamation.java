package com.example.assurance2.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reclamation;
    private String contenu;
    private LocalDate dateRéclamation;
    private String statut;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "employeAffecté_id")
    private Employe employeAffecté;

    public void consulterRéclamation() {
        // Logique
    }

    public void traiterRéclamation() {
        // Logique
    }

    public void assignerRéclamation() {
        // Logique
    }
}