package com.hermes.Services.Auth;

import com.hermes.DbCore.IRefreshTokenDb;
import com.hermes.ExceptionHandling.HermesError;
import com.hermes.Models.RefreshToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class RefreshTokenService {
    private final IRefreshTokenDb refreshTokenDb;
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    @Value("${jwt.refresh-token-expiration}")
    private long REFRESH_TOKEN_VALIDITY;

    public RefreshTokenService(IRefreshTokenDb refreshTokenDb) {
        this.refreshTokenDb = refreshTokenDb;
    }

    public CompletableFuture<RefreshToken> createRefreshToken(String userId) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(userId);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusSeconds(REFRESH_TOKEN_VALIDITY));

        return CompletableFuture.completedFuture(refreshTokenDb.save(refreshToken));
    }

    public CompletableFuture<RefreshToken> verifyRefreshToken(String token) {
        return refreshTokenDb.findByToken(token)
                .thenCompose(refreshTokens -> {
                    if (refreshTokens.isEmpty()) {
                        _logger.error("Refresh token not found");
                        throw new HermesError("Invalid refresh token", HermesError.RESOURCE_NOT_FOUND);
                    }
                    RefreshToken refreshToken = refreshTokens.getFirst();
                    if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
                        refreshTokenDb.delete(refreshToken);
                        _logger.error("Refresh token expired");
                        throw new HermesError("Refresh token expired", HermesError.BAD_REQUEST);
                    }
                    if (refreshToken.isRevoked()) {
                        refreshTokenDb.delete(refreshToken);
                        _logger.error("Refresh token revoked");
                        throw new HermesError("Refresh token revoked", HermesError.BAD_REQUEST);
                    }
                    return CompletableFuture.completedFuture(refreshToken);
                });
    }

    public CompletableFuture<Void> revokeRefreshToken(String userId) {
        return refreshTokenDb.deleteByUserId(userId);
    }
}