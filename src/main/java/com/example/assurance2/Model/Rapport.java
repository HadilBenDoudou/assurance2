package com.example.assurance2.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Rapport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private LocalDate dateCréation;
    private LocalDate dateModification;
    private String contenu;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    public void générerPDF() {
        // Logique
    }

    public void générerExcel() {
        // Logique
    }

    public void générerParMail() {
        // Logique
    }
}