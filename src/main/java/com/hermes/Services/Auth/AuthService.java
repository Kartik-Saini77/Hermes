package com.hermes.Services.Auth;

import com.hermes.DbCore.IUserDb;
import com.hermes.ExceptionHandling.HermesError;
import com.hermes.Models.Interaction.RegistrationInteraction;
import com.hermes.Models.Interaction.UserDetailsInteraction;
import com.hermes.Models.RefreshToken;
import com.hermes.Models.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    private final RefreshTokenService refreshTokenService;

    @Value("${jwt.token-expiration}")
    private long ACCESS_TOKEN_VALIDITY;

    public AuthService(IUserDb userDBService, BCryptPasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, RefreshTokenService refreshTokenService) {
        this.userDBService = userDBService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.refreshTokenService = refreshTokenService;
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
            userDetails.setPassword(passwordEncoder.encode(registrationInteraction.password));
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

            String accessToken = generateAccessToken(authentication);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authentication.getName()).get();

            Map<String, Object> response = new HashMap<>();
            response.put("accessToken", accessToken);
            response.put("refreshToken", refreshToken.getToken());
            return CompletableFuture.completedFuture(response);
        } catch (AuthenticationException e) {
            _logger.info("Failed to Login ERROR: {}", e.getMessage());
            throw new HermesError("Bad credentials", HermesError.RESOURCE_NOT_FOUND);
        } catch (Exception e) {
            _logger.error("Internal Server Error in login() : {}", e.getMessage());
            throw new Exception("Internal Server Error");
        }
    }

    @Async
    public CompletableFuture<Map<String, Object>> refreshToken(Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken == null) {
            _logger.error("Refresh token is required for token refresh");
            throw new HermesError("Refresh token is required", HermesError.BAD_REQUEST);
        }
        return refreshTokenService.verifyRefreshToken(refreshToken)
                .thenCompose(token -> userDBService.findByUserId(token.getUserId())
                        .thenApply(users -> {
                            if (users.isEmpty()) {
                                _logger.error("User not found for refresh token");
                                throw new HermesError("User not found", HermesError.RESOURCE_NOT_FOUND);
                            }
                            return users.getFirst();
                        }))
                .thenApply(user -> {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(
                            user.getUserId(), user.getPassword()
                    );
                    String newAccessToken = generateAccessToken(authentication);
                    Map<String, Object> response = new HashMap<>();
                    response.put("accessToken", newAccessToken);
                    response.put("expiresIn", ACCESS_TOKEN_VALIDITY);
                    return response;
                });
    }

    private String generateAccessToken(Authentication authentication) {
        var claims = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuer("hermes")
                .claim("roles", authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(ACCESS_TOKEN_VALIDITY))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
