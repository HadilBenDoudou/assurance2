package com.example.assurance2.Controller;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String nom;
    private String prenom;
    private String email;
    private String CIN;
}