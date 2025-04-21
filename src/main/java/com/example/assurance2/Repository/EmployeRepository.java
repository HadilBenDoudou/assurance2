package com.example.assurance2.Repository;

import com.example.assurance2.Model.Admin;
import com.example.assurance2.Model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeRepository extends JpaRepository<Employe, Long> {
    Optional<Admin> findByEmail(String email);
}
