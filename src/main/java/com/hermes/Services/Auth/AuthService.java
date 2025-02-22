package com.hermes.Services.Auth;

import com.hermes.DbCore.IUserDb;
import com.hermes.ExceptionHandling.HermesError;
import com.hermes.Models.Interaction.RegistrationInteraction;
import com.hermes.Models.Interaction.UserDetailsInteraction;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public class AuthService implements IAuthService {
    private IUserDb userDBService;
    public AuthService(IUserDb userDBService) {
        this.userDBService = userDBService;
    }

    @Override
    @Async
    public CompletableFuture<String> login(UserDetailsInteraction userDetailsInteraction) throws Exception {
        try {
            var email=userDetailsInteraction.email;
            var password=userDetailsInteraction.password;
            throw new HermesError("User not found", 404);
        } catch (HermesError e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public CompletableFuture<String> register(RegistrationInteraction userDetailsInteraction) {
        return null;
    }
}
