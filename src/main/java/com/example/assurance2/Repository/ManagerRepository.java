package com.example.assurance2.Repository;

import com.example.assurance2.Model.Admin;
import com.example.assurance2.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Admin> findByEmail(String email);
}