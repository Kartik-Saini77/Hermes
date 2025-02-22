package com.hermes.Services.Auth;

import com.hermes.ExceptionHandling.HermesError;
import com.hermes.Models.Interaction.RegistrationInteraction;
import com.hermes.Models.Interaction.UserDetailsInteraction;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public interface IAuthService {
    public CompletableFuture<String> login(UserDetailsInteraction userDetailsInteraction) throws Exception;
    public CompletableFuture<String> register(RegistrationInteraction userDetailsInteraction);
}
