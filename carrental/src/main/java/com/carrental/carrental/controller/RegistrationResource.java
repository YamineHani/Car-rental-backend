package com.carrental.carrental.controller;

import com.carrental.carrental.exception.UserNotFoundException;
import com.carrental.carrental.model.RegistrationRequest;
import com.carrental.carrental.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
@CrossOrigin("*") //4200
public class RegistrationResource {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }
    // api/v1/registration/confirm?token=83d95fca-407c-4373-9dea-020a96fb0e18 (ex)
    @GetMapping(path = "confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
