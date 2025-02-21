package com.hermes.Services;

import com.hermes.DbCore.UserDb;
import com.hermes.Models.User;
import com.hermes.Models.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class test {

    private UserDb userDb;

    public test(UserDb user){
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
        user.country= UserDetails.Country.INDIA;
        user.dob="12-12-1999";

        userDb.insert(user);
        return user;
    }

    public List<UserDetails> findAll() {
        return userDb.findAll();
    }
}
