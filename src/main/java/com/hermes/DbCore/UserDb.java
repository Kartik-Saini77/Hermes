package com.hermes.DbCore;

import com.hermes.Models.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDb extends MongoRepository<UserDetails, String> {
}