package com.hermes.Models;

import com.hermes.Models.Enums.Country;
import com.hermes.Models.Enums.Gender;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "UsersDb")
public class UserDetails extends User {
    @Id
    @BsonProperty("_id")
    private String id;
    @BsonProperty("publicUsername")
    private String publicUsername;
    @BsonProperty("userId")
    private String userId;
    @BsonProperty("gender")
    private Gender gender;
    @BsonProperty("country")
    private Country country;
    @BsonProperty("dob")
    private String dob;

    public UserDetails() {
        super();
    }

    public static UserDetails RedactUserDetails(UserDetails user){
        user.setPassword("[REDACTED]");
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPublicUsername() {
        return publicUsername;
    }

    public void setPublicUsername(String publicUsername) {
        this.publicUsername = publicUsername;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "id='" + id + '\'' +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", publicUsername='" + publicUsername + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", userId='" + userId + '\'' +
                ", role=" + getRole() +
                ", createdAt=" + getCreatedAt() +
                ", isVerified=" + isVerified() +
                ", gender=" + gender +
                ", country=" + country +
                ", dob='" + dob + '\'' +
                '}';
    }
}

