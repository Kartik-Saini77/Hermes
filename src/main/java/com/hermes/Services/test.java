package com.hermes.Services;

import com.hermes.DbCore.IUserDb;
import com.hermes.Models.Enums.Country;
import com.hermes.Models.User;
import com.hermes.Models.UserDetails;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class test {

    private IUserDb userDb;

    public test(IUserDb user){
        this.userDb=user;
    }

    public UserDetails saveUser(){
        var user = new UserDetails() ;
        user.firstName="test";
        user.lastName="Test";
        user.email="dfsa@gmadsgfa";
        user.role= User.Role.USER;
        user.password="1234";
        user.createdAt=java.time.Instant.now();
        user.isVerified=true;
        user.userId="1234";
        user.username="test";
        user.country = Country.INDIA;
        user.dob="12-12-1999";

        userDb.insert(user);
        return user;
    }

    @Async
    public CompletableFuture<List<UserDetails>> findAll() {
        return userDb.findByEmailAndPassword("dfsa@gmadsgfa","1234");
    }
}
