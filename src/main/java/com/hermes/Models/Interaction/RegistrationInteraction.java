package com.hermes.Models.Interaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hermes.Models.Enums.Country;
import com.hermes.Models.Enums.Gender;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class RegistrationInteraction {
    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    public String email;

    @JsonProperty("password")
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Size(max = 128, message = "Password must be less than 128 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "Password must contain at least one lowercase letter, one uppercase letter, one number and one special character")
    public String password;

    @JsonProperty("firstName")
    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must be less than 50 characters long")
    public String firstName;

    @JsonProperty("lastName")
    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must be less than 50 characters long")
    public String lastName;

    @JsonProperty("gender")
    @NotNull(message = "Gender is required")
    public Gender gender = Gender.PREFER_NOT_TO_SAY;

    @JsonProperty("country")
    @NotNull(message = "Country is required")
    public Country country = Country.INDIA;

    @JsonProperty("dob")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Date of birth is required")
    @Past(message = "Invalid date of birth")
    public LocalDate dob;
}
