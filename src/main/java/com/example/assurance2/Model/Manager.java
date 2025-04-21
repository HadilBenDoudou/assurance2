package com.example.assurance2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Manager extends User {
    @OneToMany(mappedBy = "manager")
    private List<Rapport> rapports;

    public Manager() {
        setRole(Role.MANAGER);
    }

    public void superviserEmployés() {
        // Logique
    }

    public void validerDécisions() {
        // Logique
    }

    public void gérerRapport() {
        // Logique
    }

    public void validerDemandeContrat() {
        // Logique
    }
}