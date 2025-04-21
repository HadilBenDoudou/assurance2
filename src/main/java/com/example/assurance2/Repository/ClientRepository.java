package com.example.assurance2.Repository;

import com.example.assurance2.Model.Admin;
import com.example.assurance2.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Admin> findByEmail(String email);
}
