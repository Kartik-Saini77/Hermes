package com.hermes.DbCore;

import com.hermes.Models.RefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IRefreshTokenDb extends MongoRepository<RefreshToken, String> {
    @Async
    CompletableFuture<List<RefreshToken>> findByToken(String token);

    @Async
    CompletableFuture<List<RefreshToken>> findByUserId(String userId);

    @Async
    CompletableFuture<Void> deleteByUserId(String userId);
}
