package com.hermes.Services.Auth;

import com.hermes.DbCore.IUserDb;
import com.hermes.ExceptionHandling.HermesError;
import com.hermes.Models.Interaction.RegistrationInteraction;
import com.hermes.Models.Interaction.UserDetailsInteraction;
import com.hermes.Models.UserDetails;
import com.hermes.utils.HLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthService  {
    private IUserDb userDBService;
    private HLogger<AuthService> _logger;



    public AuthService(IUserDb userDBService,HLogger<AuthService> logger) {
        this.userDBService = userDBService;
        this._logger = logger;
    }


    @Async
    public CompletableFuture<String> login(UserDetailsInteraction userDetailsInteraction) throws Exception {
        try {
            var email=userDetailsInteraction.email;
            var result=userDBService.findByEmail(email);
            if(result==null || result.get().size()==0) {
                throw new HermesError("User Not Found",HermesError.RESOURCE_NOT_FOUND);
            }
            //TODO: Check IsVerified
            if(!new BCryptPasswordEncoder(10).encode(userDetailsInteraction.password).equals(result.get().getFirst().password)) {
                throw new HermesError("Invalid Credentials",HermesError.RESOURCE_NOT_FOUND);
            }
            return CompletableFuture.completedFuture("Login Success");



        } catch (HermesError e) {
            _logger.error("Failed to Login ERROR: "+e.getMessage());
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Async
    public CompletableFuture<String> register(RegistrationInteraction registrationInteraction) throws Exception {
        try{
            var res=userDBService.findByEmail(registrationInteraction.email);
            if(res==null || res.get().size()>0){
                throw new HermesError("User Already Exists",HermesError.BAD_REQUEST);
            }
            var userDetails=new UserDetails();
            userDetails.email=registrationInteraction.email;
            userDetails.password=new BCryptPasswordEncoder(10).encode(registrationInteraction.password);
            userDetails.firstName=registrationInteraction.firstName;
            userDetails.lastName=registrationInteraction.lastName;
            userDetails.country=registrationInteraction.country;
            userDetails.dob=registrationInteraction.dob;
            userDetails.username="user_"+System.currentTimeMillis();
            userDetails.userId= UUID.randomUUID().toString();
            userDBService.save(userDetails);
            return CompletableFuture.completedFuture("Registration Success");
        } catch (HermesError e) {
            _logger.error("Failed to Register ERROR: "+e.getMessage());
            throw e;
        } catch (Exception e) {
            throw e;
        }

    }
}
