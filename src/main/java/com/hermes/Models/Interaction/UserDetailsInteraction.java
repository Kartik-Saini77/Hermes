package com.hermes.Models.Interaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetailsInteraction {
    @JsonProperty("emailOrUsername")
    public String emailOrUsername;
    @JsonProperty("password")
    public String password;
}
