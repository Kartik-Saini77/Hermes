package com.hermes.Models.Interaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hermes.Models.Enums.Country;

@JsonInclude(JsonInclude.Include.ALWAYS)
public class RegistrationInteraction {
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("lastName")
    public String lastName;
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
    @JsonProperty("country")
    public Country country;
    @JsonProperty("dob")
    public String dob;
}
