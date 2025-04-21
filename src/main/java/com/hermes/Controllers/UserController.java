package com.hermes.Controllers;

import com.hermes.Models.Interaction.RegistrationInteraction;
import com.hermes.Models.Interaction.UserDetailsInteraction;
import com.hermes.Services.Auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1.0")
public class UserController {

   private final AuthService authService;

   public UserController(AuthService authService) {
       this.authService = authService;
   }

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@Valid @RequestBody RegistrationInteraction registrationInteraction) throws Exception {
       return ResponseEntity.ok(authService.register(registrationInteraction).get());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@Valid @RequestBody UserDetailsInteraction userDetailsInteraction) throws Exception {
        return ResponseEntity.ok(authService.login(userDetailsInteraction).get());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<Map<String, Object>> refreshToken(@RequestBody Map<String, String> request) throws Exception {
        return ResponseEntity.ok(authService.refreshToken(request).get());
    }
}