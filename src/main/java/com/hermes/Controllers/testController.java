package com.hermes.Controllers;

import com.hermes.ExceptionHandling.HermesError;
import com.hermes.Models.UserDetails;
import com.hermes.Services.test;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class testController {

    private final test testService;

    public testController(test testService) {
        this.testService = testService;
    }

    @GetMapping("/saveUser")
    public ResponseEntity<UserDetails> saveUser() throws HermesError {
        return ResponseEntity.ok(testService.saveUser());
//        throw new HermesError("User not found", 404);
    }

    @GetMapping("/test")
    public ResponseEntity<List<UserDetails>> test() throws Exception {
        return ResponseEntity.ok(testService.findAll().get());
    }
}