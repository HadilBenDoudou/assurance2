package com.example.assurance2.Controller;


import com.example.assurance2.Model.Role;
import lombok.Data;

@Data
public class AuthRequestDTO {
    private String CIN;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role;
}