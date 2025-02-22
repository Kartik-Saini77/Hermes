package com.hermes.Services;

import com.hermes.DbCore.UserDb;
import com.hermes.Models.User;
import com.hermes.Models.UserDetails;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class test {

    private UserDb userDb;

    public test(UserDb user){
        this.userDb=user;
    }

    @Async
    public CompletableFuture<UserDetails> saveUser(){
        try {
            var user = new UserDetails();
            user.firstName = "test";
            user.lastName = "Test";
            user.email = "dfsa@gmadsgfa";
            user.role = User.Role.USER;
            user.password = "1234";
            user.createdAt = java.time.Instant.now();
            user.isVerified = true;
            user.userId = "1234";
            user.username = "test";
            user.country = UserDetails.Country.INDIA;
            user.dob = "12-12-1999";

            userDb.insert(user);
            return CompletableFuture.completedFuture(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }
       throw  new RuntimeException("hello");
    }

    public List<UserDetails> findAll() {
        return userDb.findAll();
    }
}
