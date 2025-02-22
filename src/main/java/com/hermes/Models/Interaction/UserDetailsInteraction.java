package com.hermes.Models.Interaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetailsInteraction {
    @JsonProperty("email")
    public String email;
    @JsonProperty("password")
    public String password;
}
