package com.example.assurance2.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Client extends User {
    private String adresse;
    private String telephone;

    @OneToMany(mappedBy = "client")
    private List<Contrat> contrats;

    @OneToMany(mappedBy = "client")
    private List<Paiement> paiements;

    @OneToMany(mappedBy = "client")
    private List<Sinistre> sinistres;

    @OneToMany(mappedBy = "client")
    private List<Reclamation> reclamations;

    public Client() {
        setRole(Role.CLIENT);
    }

    public void creeCompte() {
        // Logique
    }
}