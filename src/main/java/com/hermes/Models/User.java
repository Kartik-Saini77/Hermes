package com.hermes.Models;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.Instant;
import java.util.UUID;

public class User {

    @BsonProperty("firstName")
    public String firstName;
    @BsonProperty("lastName")
    public String lastName;
    @BsonProperty("email")
    public String email;
    @BsonProperty("role")
    public Role role;
    @BsonProperty("password")
    public String password;
    @BsonProperty("createdAt")
    public Instant createdAt;
    @BsonProperty("isVerified")
    public boolean isVerified=false;

    public enum Role{
        USER,MODERATOR,ADMIN
    }
}
