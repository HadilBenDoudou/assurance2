package com.example.assurance2.Repository;

import com.example.assurance2.Model.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
