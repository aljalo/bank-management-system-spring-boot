package org.wscict.bank.auth;

import jakarta.validation.constraints.*;

public class RegisterRequest {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$",
            message = "Username can only contain letters, numbers, dots, underscores and hyphens")
    private String username;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;

    // Default constructor for Spring
    public RegisterRequest() {}

    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
