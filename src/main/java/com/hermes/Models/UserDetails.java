package com.hermes.Models;

import com.hermes.Models.Enums.Country;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

@org.springframework.data.mongodb.core.mapping.Document(collection = "UsersDb")
public class UserDetails extends User {
    @BsonProperty("_id")
    public String id;
    @BsonProperty("userId")
    public String userId;
    @BsonProperty("username")
    public String username;
    @BsonProperty("country")
    public Country country;
    @BsonProperty("dob")
    public String dob;

    public UserDetails() {
        this.id = UUID.randomUUID().toString();
    }
    public static UserDetails RedactUserDetails(UserDetails user){
        user.password = "[REDACTED]";
        return user;
    }
}

