package com.example.assurance2.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private Double montant;
    private String méthode;

    @ManyToOne
    @JoinColumn(name = "contrat_id")
    private Contrat contrat;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public void effectuerPaiement() {
        // Logique
    }

    public void consulterHistorique() {
        // Logique
    }
}