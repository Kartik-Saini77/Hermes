package com.hermes.Models;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;

import java.time.Instant;

public class RefreshToken {
    @Id
    @BsonProperty("_id")
    private String id;
    @BsonProperty("userId")
    private String userId;
    @BsonProperty("token")
    private String token;
    @BsonProperty("expiryDate")
    private Instant expiryDate;
    @BsonProperty("revoked")
    private boolean revoked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Instant expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }
}
