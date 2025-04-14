package com.hermes.Services.Auth;

import com.hermes.DbCore.IUserDb;
import com.hermes.ExceptionHandling.HermesError;
import com.hermes.Models.Interaction.RegistrationInteraction;
import com.hermes.Models.Interaction.UserDetailsInteraction;
import com.hermes.Models.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthService  {
    private final IUserDb userDBService;
    private final Logger _logger = LoggerFactory.getLogger(getClass());
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    public AuthService(IUserDb userDBService, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtEncoder jwtEncoder) {
        this.userDBService = userDBService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
    }

    @Async
    public CompletableFuture<String> register(RegistrationInteraction registrationInteraction) throws Exception {
        try{
            var res=userDBService.findByEmail(registrationInteraction.email);
            if(res==null || !res.get().isEmpty()){
                throw new HermesError("User Already Exists", HermesError.BAD_REQUEST);
            }

            var userDetails=new UserDetails();
            userDetails.setEmail(registrationInteraction.email);
            userDetails.setPassword(passwordEncoder.encode(registrationInteraction.password)); // new BCryptPasswordEncoder(10).encode(registrationInteraction.password);
            userDetails.setFirstName(registrationInteraction.firstName);
            userDetails.setLastName(registrationInteraction.lastName);
            userDetails.setCountry(registrationInteraction.country);
            userDetails.setGender(registrationInteraction.gender);
            userDetails.setDob(registrationInteraction.dob);
            userDetails.setPublicUsername("user_" + System.currentTimeMillis());
            userDetails.setUserId(UUID.randomUUID().toString());

            userDBService.save(userDetails);
            return CompletableFuture.completedFuture("Registered Successfully");
        } catch (HermesError e) {
            _logger.error("Failed to Register ERROR: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            _logger.error("Internal Server Error in register() : {}", e.getMessage());
            throw new Exception("Internal Server Error");
        }
    }

    @Async
    public CompletableFuture<Map<String, Object>> login(UserDetailsInteraction userDetailsInteraction) throws Exception {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDetailsInteraction.emailOrUsername, userDetailsInteraction.password)
            );

            var claims = JwtClaimsSet.builder()
                    .subject(userDetailsInteraction.emailOrUsername)
                    .issuer("hermes")
                    .claim("roles", authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList())
                    .issuedAt(Instant.now())
                    .expiresAt(Instant.now().plusSeconds(60))
                    .build();

            JwtEncoderParameters parameters = JwtEncoderParameters.from(claims);

            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtEncoder.encode(parameters).getTokenValue());
//            _logger.warn(claims.getClaim("roles").toString());
            return CompletableFuture.completedFuture(response);
        } catch (AuthenticationException e) {
            _logger.info("Failed to Login ERROR: {}", e.getMessage());
            throw new HermesError("Bad credentials", HermesError.RESOURCE_NOT_FOUND);
        } catch (Exception e) {
            _logger.error("Internal Server Error in login() : {}", e.getMessage());
            throw new Exception("Internal Server Error");
        }
    }

//    @Async
//    public CompletableFuture<String> login(UserDetailsInteraction userDetailsInteraction) throws Exception {
//        try {
//            var email=userDetailsInteraction.email;
//            var result=userDBService.findByEmail(email);
//            if(result==null || result.get().isEmpty()) {
//                throw new HermesError("User Not Found",HermesError.RESOURCE_NOT_FOUND);
//            }
//            //TODO: Check IsVerified
//            if(!new BCryptPasswordEncoder(10).encode(userDetailsInteraction.password).equals(result.get().getFirst().getPassword())) {
//                throw new HermesError("Invalid Credentials",HermesError.RESOURCE_NOT_FOUND);
//            }
//            return CompletableFuture.completedFuture("Login Success");
//        } catch (HermesError e) {
//            _logger.info("Failed to Login ERROR: {}", e.getMessage());
//            throw e;
//        }
//    }
}
