package com.protectx.iam.interfaces.rest;

import com.protectx.iam.domain.model.User;
import com.protectx.iam.infrastructure.persistence.UserRepository;
import com.protectx.iam.infrastructure.security.JwtService;
import com.protectx.iam.interfaces.rest.dto.LoginRequest;
import com.protectx.iam.interfaces.rest.dto.LoginResponse;
import com.protectx.iam.interfaces.rest.dto.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")

public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body("El username ya existe");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("El email ya existe");
        }

        try {
            User newUser = User.builder()
                    .name(request.getName())
                    .username(request.getUsername())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .email(request.getEmail())
                    .build();

            User savedUser = userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar usuario: " + e.getMessage());
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElse(null);

        if (user != null && passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String token = jwtService.generateToken(user.getUsername());
            return ResponseEntity.ok(new LoginResponse(token, user));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}