package com.example.assurance2.Controller;

import com.example.assurance2.Model.*;
import com.example.assurance2.Repository.*;
import com.example.assurance2.config.JwtUtil;
import com.example.assurance2.service.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final EmployeRepository employeRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PasswordResetService passwordResetService;

    public AuthController(AuthenticationManager authenticationManager,
                          AdminRepository adminRepository,
                          ClientRepository clientRepository,
                          ManagerRepository managerRepository,
                          EmployeRepository employeRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          JwtUtil jwtUtil,
                          PasswordResetService passwordResetService) {
        this.authenticationManager = authenticationManager;
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.managerRepository = managerRepository;
        this.employeRepository = employeRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AuthRequestDTO authRequest) {
        if (userRepository.findByEmail(authRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }

        String encodedPassword = passwordEncoder.encode(authRequest.getMotDePasse());

        switch (authRequest.getRole()) {
            case ADMIN:
                Admin admin = new Admin();
                admin.setCIN(authRequest.getCIN());
                admin.setNom(authRequest.getNom());
                admin.setPrenom(authRequest.getPrenom());
                admin.setEmail(authRequest.getEmail());
                admin.setMotDePasse(encodedPassword);
                admin.setRole(Role.ADMIN);
                adminRepository.save(admin);
                break;
            case CLIENT:
                Client client = new Client();
                client.setCIN(authRequest.getCIN());
                client.setNom(authRequest.getNom());
                client.setPrenom(authRequest.getPrenom());
                client.setEmail(authRequest.getEmail());
                client.setMotDePasse(encodedPassword);
                client.setRole(Role.CLIENT);
                clientRepository.save(client);
                break;
            case MANAGER:
                Manager manager = new Manager();
                manager.setCIN(authRequest.getCIN());
                manager.setNom(authRequest.getNom());
                manager.setPrenom(authRequest.getPrenom());
                manager.setEmail(authRequest.getEmail());
                manager.setMotDePasse(encodedPassword);
                manager.setRole(Role.MANAGER);
                managerRepository.save(manager);
                break;
            case EMPLOYE:
                Employe employe = new Employe();
                employe.setCIN(authRequest.getCIN());
                employe.setNom(authRequest.getNom());
                employe.setPrenom(authRequest.getPrenom());
                employe.setEmail(authRequest.getEmail());
                employe.setMotDePasse(encodedPassword);
                employe.setRole(Role.EMPLOYE);
                employeRepository.save(employe);
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid role");
        }

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getMotDePasse()));
            String token = jwtUtil.generateToken(authRequest.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, jwtUtil.getExpirationTime()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid email or password");
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            passwordResetService.createPasswordResetTokenForUser(request.getEmail());
            return ResponseEntity.ok("Password reset email sent successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(500).body("Failed to send email: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody ResetPasswordRequest request) {
        try {
            passwordResetService.resetPassword(token, request.getNewPassword());
            return ResponseEntity.ok("Password reset successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/account")
    public ResponseEntity<?> getAccount(HttpServletRequest request) {
        String email = extractEmailFromToken(request);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/account/update")
    public ResponseEntity<?> updateAccount(HttpServletRequest request, @RequestBody UpdateProfileRequest updateRequest) {
        String email = extractEmailFromToken(request);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the new email is already taken by another user
        if (updateRequest.getEmail() != null && !updateRequest.getEmail().equals(email)) {
            if (userRepository.findByEmail(updateRequest.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Email is already taken");
            }
            user.setEmail(updateRequest.getEmail());
        }

        if (updateRequest.getNom() != null) user.setNom(updateRequest.getNom());
        if (updateRequest.getPrenom() != null) user.setPrenom(updateRequest.getPrenom());
        if (updateRequest.getCIN() != null) user.setCIN(updateRequest.getCIN());

        userRepository.save(user);
        return ResponseEntity.ok("Profile updated successfully");
    }

    @PostMapping("/account/change-password")
    public ResponseEntity<?> changePassword(HttpServletRequest request, @RequestBody ChangePasswordRequest changeRequest) {
        String email = extractEmailFromToken(request);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Verify old password
        if (!passwordEncoder.matches(changeRequest.getOldPassword(), user.getMotDePasse())) {
            return ResponseEntity.badRequest().body("Incorrect old password");
        }

        // Encode and set new password
        user.setMotDePasse(passwordEncoder.encode(changeRequest.getNewPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Password changed successfully");
    }

    @DeleteMapping("/account/delete")
    public ResponseEntity<?> deleteAccount(HttpServletRequest request) {
        String email = extractEmailFromToken(request);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Delete user based on role
        switch (user.getRole()) {
            case ADMIN:
                adminRepository.deleteById(user.getId());
                break;
            case CLIENT:
                clientRepository.deleteById(user.getId());
                break;
            case MANAGER:
                managerRepository.deleteById(user.getId());
                break;
            case EMPLOYE:
                employeRepository.deleteById(user.getId());
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid role");
        }

        userRepository.delete(user);
        return ResponseEntity.ok("Account deleted successfully");
    }

    private String extractEmailFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }
        String token = authHeader.substring(7);
        return jwtUtil.getEmailFromToken(token);
    }
}


