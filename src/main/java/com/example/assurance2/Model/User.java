package com.example.assurance2.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String CIN;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    @Enumerated(EnumType.STRING)
    private Role role;

    // Getters
    public Long getId() {
        return id;
    }

    public String getCIN() {
        return CIN;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public Role getRole() {
        return role;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    // Your existing methods
    public void getPassword() {
        // Implementation
    }

    public void seConnecter() {
        // Logique de connexion
    }

    public void modifierInfosPerso() {
        // Logique de modification
    }

    public void changerMotDePasse() {
        // Logique de changement de mot de passe
    }
}