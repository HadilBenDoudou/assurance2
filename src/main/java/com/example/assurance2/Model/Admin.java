package com.example.assurance2.Model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Admin extends User {
    public Admin() {
        setRole(Role.ADMIN);
    }

    public void creerUtilisateur() {
        // Logique
    }

    public void modifierUtilisateur() {
        // Logique
    }

    public void désactiverCompte() {
        // Logique
    }

    public void réinitialiserMotDePasse() {
        // Logique
    }

    public void ajouterRole() {
        // Logique
    }

    public void gererContrats() {
        // Logique
    }

    public void supprimerSinistres() {
        // Logique
    }

    public void gérerRapports() {
        // Logique
    }
}