package com.hermes.Models.Interaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDetailsInteraction {
    @JsonProperty("emailOrUsername")
    @NotBlank(message = "Email or username is required")
    @Size(min = 3, message = "Email or username must be at least 3 characters long")
    @Size(max = 254, message = "Invalid email or username")
    public String emailOrUsername;

    @JsonProperty("password")
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Size(max = 128, message = "Password must be less than 128 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one lowercase letter, one uppercase letter, one number and one special character")
    public String password;
}
