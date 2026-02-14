package org.wscict.bank.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


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

    // ============================
    // Register new user
    // ============================
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {

        //
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        //
        User user = new User(request.getUsername(), passwordEncoder.encode(request.getPassword()));

        //
        user.addRole(userRole);

        //
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    // ============================
    // Promote User to Admin
    // ============================
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/promote/{username}")
    public ResponseEntity<String> promoteToAdmin(@PathVariable String username) {

        //
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //
        Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role not found"));

        //
        user.addRole(adminRole);

       //
        userRepository.save(user);

        return ResponseEntity.ok("User promoted to ADMIN successfully");
    }
}
