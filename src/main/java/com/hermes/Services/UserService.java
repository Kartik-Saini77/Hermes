package com.hermes.Services;

import com.hermes.DbCore.IUserDb;
import com.hermes.ExceptionHandling.HermesError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final IUserDb userDb;
    private final Logger _logger = LoggerFactory.getLogger(getClass());

    public UserService(IUserDb userDb) {
        this.userDb = userDb;
    }

    @Override
    public UserDetails loadUserByUsername(String emailOrUsername) throws UsernameNotFoundException {
        try {
            var users = userDb.findByEmailOrPublicUsername(emailOrUsername).get();
            if (users.isEmpty()) {
                throw new HermesError("Bad credentials", HermesError.RESOURCE_NOT_FOUND);
            }
            com.hermes.Models.UserDetails userDetails = users.getFirst();

            return new User(
                    userDetails.getEmail(),
                    userDetails.getPassword(),
                    List.of(new SimpleGrantedAuthority(userDetails.getRole().toString()))
            );
        } catch (HermesError e) {
            _logger.error("Error fetching user: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            _logger.error("Internal Server Error in loadUserByUsername() : {}", e.getMessage());
            throw new HermesError("Internal Server Error", HermesError.INTERNAL_SERVER_ERROR);
        }
    }
}
