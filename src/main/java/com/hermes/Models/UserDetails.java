package com.hermes.Models;

import com.mongodb.lang.Nullable;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

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

    public enum Country{
        INDIA,PAKISTAN,IRAN,RUSSIA,CHINA,NORTH_KORIA,USA,UK
    }

    public UserDetails() {
        this.id = UUID.randomUUID().toString();
    }
}
