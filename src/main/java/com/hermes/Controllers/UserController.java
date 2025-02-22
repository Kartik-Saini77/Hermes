package com.hermes.Controllers;

import com.hermes.Models.Interaction.RegistrationInteraction;
import com.hermes.Models.Interaction.UserDetailsInteraction;
import com.hermes.Services.Auth.AuthService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0")
public class UserController {

   private final AuthService authService;

   public UserController(AuthService authService) {
       this.authService = authService;
   }

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<String> saveUser(@RequestBody RegistrationInteraction registrationInteraction) throws Exception {

        return ResponseEntity.ok(authService.register(registrationInteraction).get());

    }

    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<String> login(@RequestBody UserDetailsInteraction userDetailsInteraction) throws Exception {

        return ResponseEntity.ok(authService.login(userDetailsInteraction).get());
    }
}