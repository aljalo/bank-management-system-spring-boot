package org.wscict.bank.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {

        Role userRole = roleRepository
                .findByName("ROLE_USER")
               .orElseThrow(() -> new RuntimeException("Role not found"));


        User user = new User(request.getUsername(),
                passwordEncoder.encode(request.getPassword()));

        user.addRole(userRole);

        userRepository.save(user);

        return "User registered successfully";
    }
}
