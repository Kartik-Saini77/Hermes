package com.hermes.DbCore;

import com.hermes.Models.UserDetails;
import org.springframework.data.domain.Limit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface IUserDb extends MongoRepository<UserDetails, String> {

    @Async
    @Query("{'email': ?0, 'password': ?1}")
    CompletableFuture<List<UserDetails>> findByEmailAndPassword(String email, String password);
}