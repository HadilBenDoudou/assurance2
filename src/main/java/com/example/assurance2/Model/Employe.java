package com.example.assurance2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Employe extends User {
    @OneToMany(mappedBy = "employeAffecté")
    private List<Reclamation> reclamations;

    public Employe() {
        setRole(Role.EMPLOYE);
    }
}