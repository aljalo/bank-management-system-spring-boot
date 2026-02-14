package org.wscict.bank.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.wscict.bank.payload.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, null, "Username already exists"));
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        User user = new User(request.getUsername(),
                passwordEncoder.encode(request.getPassword()));
        user.addRole(userRole);

        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse<>(true, null, "User registered successfully"));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/promote/{username}")
    public ResponseEntity<ApiResponse<Void>> promoteToAdmin(@PathVariable String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("ROLE_ADMIN not found"));

        if (user.getRoles().contains(adminRole)) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(false, null, "User is already an admin"));
        }

        user.addRole(adminRole);
        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse<>(true, null, "User promoted to ADMIN successfully"));
    }
}
